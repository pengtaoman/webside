package com.webside.business.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.base.basemodel.PairName;
import com.webside.business.model.CompanyAuthEntity;
import com.webside.business.service.CompanyAuthService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.service.UserService;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/companyauth/")
public class CompanyAuthController extends BaseController {

	@Autowired
	private CompanyAuthService companyAuthService;
	
	@Autowired
	private UserService userService;

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
			return Common.BACKGROUND_PATH + "/company/authlist";
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
			parameters.put("name", null);
		}
		
		//3、判断是否是导出操作
		if(pager.getIsExport())
		{
			if(pager.getExportAllData())
			{
				//3.1、导出全部数据
				List<CompanyAuthEntity> list = companyAuthService.queryListAll(parameters);
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
			List<CompanyAuthEntity> list = companyAuthService.queryListAll(parameters);
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
	
	private void setAvailableUserAndCompany(Model model, CompanyAuthEntity entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		//params 放入平台用户查询条件
		params.put("userType", "subplatform");
		List<PairName> list = userService.queryNames(params);
		model.addAttribute("selectList1", JSON.toJSONString(list));
		
		params.clear();
		params.put("queryNotAuth", Boolean.TRUE);
		List<PairName> list2 = companyAuthService.queryNames(params);
		if(entity != null) {
			list2.add(new PairName(entity.getCompanyId(), entity.getCompanyName()));
		}
		model.addAttribute("selectList2", JSON.toJSONString(list2));
	}
		
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, Integer id) {
		try
		{
			CompanyAuthEntity entity = companyAuthService.findById(id);

			model.addAttribute("entity", entity);
			setAvailableUserAndCompany(model, entity);
			
			return Common.BACKGROUND_PATH + "/company/authform";
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{
			model.addAttribute("entity", new CompanyAuthEntity());
			setAvailableUserAndCompany(model, null);
			
			return Common.BACKGROUND_PATH + "/company/authform";
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("update.html")	
	@ResponseBody
	public Object update(CompanyAuthEntity entity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			entity.setCreatorId(ShiroAuthenticationManager.getUserEntity().getId());
			
			
			if(!ShiroAuthenticationManager.isAdminUser()) {	
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "无操作权限");
				return map;
			} 	
			
			int result =  0;
			
			if (entity.getId() == null) {
				entity.setCreateTime(new Date());
				result = companyAuthService.insert(entity);
			}else {
				result = companyAuthService.update(entity);
			}
			
			if(result == 1)
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
			e.printStackTrace();
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
			List<Integer> list = new ArrayList<Integer>();
			for (String string : userIds) {
				list.add(Integer.valueOf(string));
			}
			int cnt = companyAuthService.deleteBatchById(list);
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
