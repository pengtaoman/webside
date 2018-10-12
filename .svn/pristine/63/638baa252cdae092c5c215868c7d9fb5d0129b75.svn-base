package com.webside.business.controller;

import java.util.Arrays;
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
import com.webside.business.model.PropertyEntity;
import com.webside.business.service.PropertyService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/property/")
public class PropertyController extends BaseController {

	@Autowired
	private PropertyService propertyService;

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
			return Common.BACKGROUND_PATH + "/property/list";
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
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		List<PropertyEntity> list = propertyService.queryListByPage(parameters);
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
		
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{
			PropertyEntity entity = propertyService.findById(id);
			model.addAttribute("entity", entity);
			model.addAttribute("editTag", "1");
			return Common.BACKGROUND_PATH + "/property/form";
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
			model.addAttribute("entity", null);
			return Common.BACKGROUND_PATH + "/property/form";
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("update.html")	
	@ResponseBody
	public Object update(PropertyEntity entity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			int result =  0;
			if(ShiroAuthenticationManager.isAdminUser()) {	
				result = propertyService.update(entity);
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
	
	@RequestMapping("insert.html")	
	@ResponseBody
	public Object insert(PropertyEntity entity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			int result =  0;
			if(ShiroAuthenticationManager.isAdminUser()) {	
				result = propertyService.insert(entity);
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
			List<String> list = Arrays.asList(ids.split(","));
			int cnt = propertyService.deleteBatchById(list);
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
	
	@RequestMapping(value = "getProperty.html", method = RequestMethod.GET)
	@ResponseBody
	public Object getProperty(String key){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			PropertyEntity entity = propertyService.findById(key);
			if(entity != null)
			{
				result.put("success", true);
				result.put("data", entity);
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "无法获取值");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}
}
