package com.webside.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.base.basemodel.PairName;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.model.SmsDTO;
import com.webside.user.model.SmsInfoEntity;
import com.webside.user.service.SmsService;
import com.webside.user.service.UserService;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/sms/")
public class SmsController extends BaseController {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private UserService userService;
		
	@Value("${shiro.hashIterations}")
	private String hashIterations;
	
	@RequestMapping("listUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try
		{
			PageUtil page = new PageUtil();
			if(request.getParameterMap().containsKey("page")){
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/user/smslist";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	
	/**
	 * ajax分页动态加载模式
	 * @param dtGridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager, HttpServletResponse response) throws Exception{
		Map<String, Object> parameters = null;
		//1、映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		//2、设置查询参数
		parameters = pager.getParameters();
			
		if(!ShiroAuthenticationManager.isAdminUser()) {	
			parameters.put("owner", ShiroAuthenticationManager.getUserId());
		} 
		
		if (parameters.size() < 0) {
			parameters.put("userName", null);
		}
		//3、判断是否是导出操作
		if(pager.getIsExport())
		{
			if(pager.getExportAllData())
			{
				//3.1、导出全部数据
				List<SmsDTO> list = smsService.queryListByPage(parameters);
				ExportUtils.exportAll(response, pager, list);
				return null;
			}else
			{
				//3.2、导出当前页数据
				ExportUtils.export(response, pager);
				return null;
			}
		}else
		{
			//设置分页，page里面包含了分页信息
			Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
			List<SmsDTO> list = smsService.queryListByPage(parameters);
			
			parameters.clear();
			parameters.put("isSuccess", Boolean.TRUE);
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", page.getPages());
			parameters.put("recordCount", page.getTotal());
			parameters.put("startRecord", page.getStartRow());
			//列表展示数据
			parameters.put("exhibitDatas", list);
			return parameters;
		}
	}
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{
			model.addAttribute("entity", new SmsDTO());
			
			List<PairName> list = new ArrayList<PairName>();
			list.add(new PairName(0L, "全部"));
			list.add(new PairName(39L, "33121500"));
			model.addAttribute("selectList", JSON.toJSONString(list));
			
			return Common.BACKGROUND_PATH + "/user/smsform";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("insert.html")	
	@ResponseBody
	public Object insert(@RequestParam(value="owner")Long owner,
						@RequestParam(value="title")String title, 
						@RequestParam("content")String content) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			
			if(!ShiroAuthenticationManager.isAdminUser()) {	
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "操作失败");
				return map;
			} 	
			
			SmsDTO entity = new SmsDTO();
			if(owner == 0L) {
				//TODO
				entity.setOwner(4L);
				
			} else {
				entity.setOwner(owner);
			}
			
			entity.setStatus(0);
			SmsInfoEntity smsInfo = new SmsInfoEntity();
			smsInfo.setTitle(title);
			smsInfo.setType(0);
			smsInfo.setContent(content);
			smsInfo.setCreateTime(new Date());
			
			entity.setSmsInfo(smsInfo);
			
			if(1 == smsService.insert(entity))
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "操作成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "操作失败");
			}
			return map;
			
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] userIds = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String string : userIds) {
				list.add(Long.valueOf(string));
			}
			int cnt = smsService.deleteBatchById(list);
			if(cnt == list.size())
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "删除成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "删除失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}
	
	
	@RequestMapping("updateStatusBatch.html")	
	@ResponseBody
	public Object updateStatusBatch(String ids) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{	
			String[] userIds = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String string : userIds) {
				list.add(Long.valueOf(string));
			} 
			
			if(!list.isEmpty()) {
				int cnt = smsService.updateStatus(list);
				
				map.put("success", Boolean.TRUE);
				map.put("data", cnt);
				map.put("message", "操作成功");
				return map;
			} else {
				map.put("success", Boolean.FALSE);
				map.put("message", "没有未读消息");
				return map;
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("unreadcnt.html")	
	@ResponseBody
	public Object unreadcnt() throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			if(!ShiroAuthenticationManager.isAdminUser()) {	
				map.put("owner", ShiroAuthenticationManager.getUserId());
				
			} 	
			
			map.put("status", 0);
			
			int cnt = smsService.countUnread(map);
			map.put("success", Boolean.TRUE);
			map.put("data", cnt);
			map.put("message", "操作成功");
			return map;
			
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
	}
}
