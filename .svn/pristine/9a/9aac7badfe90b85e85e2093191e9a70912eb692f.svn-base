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
import com.webside.business.service.CompanyAuthService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.model.CompanyEntity;
import com.webside.user.model.UserEntity;
import com.webside.user.service.CompanyService;
import com.webside.user.service.UserService;
import com.webside.user.service.impl.UserSessionServiceImpl;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/company/")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyAuthService companyAuthService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionServiceImpl userSessionService;
		
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
			return Common.BACKGROUND_PATH + "/company/list";
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
			
		
		if (parameters.size() < 0) {
			parameters.put("userName", null);
		}
		//3、判断是否是导出操作
		if(pager.getIsExport())
		{
			if(pager.getExportAllData())
			{
				//3.1、导出全部数据
				List<CompanyEntity> list = companyService.queryListByPage(parameters);
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
			List<CompanyEntity> list = companyService.queryListByPage(parameters);
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
	
	@RequestMapping("editUI.html")
	public String editUI(Model model) {
		try
		{
			Long companyId = ShiroAuthenticationManager.getCompanyId();
			
			if(companyId != null && companyId > 0) {
				CompanyEntity entity = companyService.findById(companyId);
				model.addAttribute("entity", entity);
			} else {
				CompanyEntity entity = new CompanyEntity();
				entity.setId(0L);
				entity.setName("");
				model.addAttribute("entity", entity);
			}
			
			return Common.BACKGROUND_PATH + "/company/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("examineBatch.html")
	@ResponseBody
	public Object examineBatch(@RequestParam(value="ids") String ids,
			@RequestParam(value="status") String status, 
			@RequestParam(value="remark") String reason) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] cIds = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String string : cIds) {
				list.add(Long.valueOf(string));
			}
			
			if (!ShiroAuthenticationManager.isAdminUser()) {
				result.put("isSuccess", Boolean.FALSE);
				result.put("msg", "无权操作");
			} else {
				if ("1".equals(status)) {
					for (Long id : list) {
						CompanyEntity en = companyService.findById(id);
						if(!"1".equals(en.getStatus())) {
							userSessionService.kickoutUserByAccount(en.getCreatorName());
						}
					}
				}
				
				companyService.updateStatusBatch(list, status, reason);
				result.put("isSuccess", Boolean.TRUE);
			}			
		}catch(Exception e)
		{
			result.put("isSuccess", Boolean.FALSE);
			result.put("msg", e.getMessage());
			throw new AjaxException(e);
		}
		return result;
	}
	
	@RequestMapping("examine.html")
	@ResponseBody
	public Object examine(@RequestParam(value="id") Long id,
			@RequestParam(value="status") String status, 
			@RequestParam(value="remark") String reason) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			if (!ShiroAuthenticationManager.isAdminUser()) {
				result.put("isSuccess", Boolean.FALSE);
				result.put("msg", "无权操作");
			} else {
				if ("1".equals(status)) {
					CompanyEntity en = companyService.findById(id);
					if (!"1".equals(en.getStatus())) {
						userSessionService.kickoutUserByAccount(en.getCreatorName());
					}
					
				}
				companyService.updateStatus(id, status, reason);
				
				result.put("isSuccess", Boolean.TRUE);
			}			
		}catch(Exception e)
		{
			result.put("isSuccess", Boolean.FALSE);
			result.put("msg", e.getMessage());
			throw new AjaxException(e);
		}
		return result;
	}
	
	@RequestMapping("infoUI.html")
	public String infoUI(Model model) {
		try
		{
			Long userId = ShiroAuthenticationManager.getUserId();
			Long companyId = userService.findById(userId).getCompanyId();
			
			if(companyId != null && companyId > 0) {
				CompanyEntity entity = companyService.findById(companyId);
				model.addAttribute("entity", entity);
			} else {
				CompanyEntity entity = new CompanyEntity();
				entity.setId(0L);
				entity.setName("");
				model.addAttribute("entity", entity);
			}
			
			return Common.BACKGROUND_PATH + "/company/info";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("info.html")	
	@ResponseBody
	public Object info(CompanyEntity entity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			Long userId = ShiroAuthenticationManager.getUserId();
			Long companyId = userService.findById(userId).getCompanyId();
			
			entity.setRootId(userId);			
			entity.setCreatorName(ShiroAuthenticationManager.getUserAccountName());
			
			if(!ShiroAuthenticationManager.isAdminUser()) {	
			} 	
			
			if(companyId != null && companyId > 0) {
				entity.setId(companyId);
				int result = companyService.update(entity);
				if(result == 1)
				{
					map.put("success", Boolean.TRUE);
					map.put("data", null);
					map.put("message", "更新成功");
				}else
				{
					map.put("success", Boolean.FALSE);
					map.put("data", null);
					map.put("message", "更新失败");
				}
				return map;
			};
			
			entity.setCreateTime(new Date());
			int ret = companyService.insertOne(entity);
			if(ret == 1)
			{
				UserEntity userEntity = ShiroAuthenticationManager.getUserEntity();
				userEntity.setCompanyId(entity.getId());
				userEntity.setCompanyName(entity.getName());
				ShiroAuthenticationManager.refreshUserSession(userEntity);
				
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加申请认证成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加申请认证失败");
			}
		}catch(ServiceException e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("delete.html")
	@ResponseBody
	public Object delete(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			int cnt = companyService.deleteById(Long.parseLong(id));
			if(cnt == 1)
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
		
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] cids = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String string : cids) {
				list.add(Long.valueOf(string));
			}
			
			
			int cnt = companyService.deleteById(list.get(0));
			if(cnt == 1)
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
	
	@RequestMapping("list")
	@ResponseBody
	public Object companylist(){
		try
		{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", ShiroAuthenticationManager.getUserEntity().getId());
			List<PairName> list = companyAuthService.queryNames(param);
			param.put("results", list);
			return param;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
}
