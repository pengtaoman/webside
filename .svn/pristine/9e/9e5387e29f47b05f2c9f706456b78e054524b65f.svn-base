package com.webside.business.controller;

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
import com.webside.business.model.FacilityFeeEntity;
import com.webside.business.service.FacilityFeeService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/fee/")
public class FacilityFeeController extends BaseController {

	@Autowired
	private FacilityFeeService facilityFeeService;
			
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
			return Common.BACKGROUND_PATH + "/fee/list";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("listfilterUI.html")
	public String listfilterUI(Model model, HttpServletRequest request) {
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
			return Common.BACKGROUND_PATH + "/fee/listfilter";
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
	@RequestMapping(value = "/listSearch.html", method = RequestMethod.POST)
	@ResponseBody
	public Object listSearch(String gridPager, HttpServletResponse response) throws Exception{
		Map<String, Object> parameters = null;
		//1、映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		//2、设置查询参数
		parameters = pager.getParameters();
		
		if (parameters.size() < 0) {
			parameters.put("name", null);
		}
		
		//设置分页，page里面包含了分页信息
		boolean needCount = true;
		if(pager.getNowPage() > 1) {
			needCount = false;
		}
		
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), needCount);
		List<FacilityFeeEntity> list = facilityFeeService.queryListByPage(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		parameters.put("exhibitDatas", list);
		return parameters;
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
				List<FacilityFeeEntity> list = facilityFeeService.queryListByPage(parameters);
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
			List<FacilityFeeEntity> list = facilityFeeService.queryListByPage(parameters);
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
	
	@RequestMapping("addCopyUI.html")
	public String addCopyUI(Model model, HttpServletRequest request, Long id) {
		try
		{
			FacilityFeeEntity entity = facilityFeeService.findById(id);
			entity.setId(null);
			model.addAttribute("entity", entity);
			List<PairName> list = new ArrayList<PairName>();
			list.add(new PairName("1","选择1"));
			list.add(new PairName("2","选择2"));
			list.add(new PairName("3","选择3"));
			list.add(new PairName("4","选择4"));
			
			
			model.addAttribute("selectList", JSON.toJSONString(list));
			return Common.BACKGROUND_PATH + "/fee/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, Long id) {
		try
		{
			FacilityFeeEntity entity = facilityFeeService.findById(id);

			model.addAttribute("entity", entity);
			List<PairName> list = new ArrayList<PairName>();
			list.add(new PairName("1","选择1"));
			list.add(new PairName("2","选择2"));
			list.add(new PairName("3","选择3"));
			list.add(new PairName("4","选择4"));
			
			
			model.addAttribute("selectList", JSON.toJSONString(list));
			return Common.BACKGROUND_PATH + "/fee/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("addUI.html")
	public String addUI(Model model) {
		try
		{
			model.addAttribute("entity", new FacilityFeeEntity());
			
			List<PairName> list = new ArrayList<PairName>();
			list.add(new PairName("1","选择1"));
			list.add(new PairName("2","选择2"));
			list.add(new PairName("3","选择3"));
			list.add(new PairName("4","选择4"));
			model.addAttribute("selectList", JSON.toJSONString(list));
			
			return Common.BACKGROUND_PATH + "/fee/form";
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("update.html")	
	@ResponseBody
	public Object update(FacilityFeeEntity entity) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{			
			entity.setOperater(String.valueOf(ShiroAuthenticationManager.getUserEntity().getId()));
			
			
			if(!ShiroAuthenticationManager.isAdminUser()) {	
			} 	
			
			int result =  0;
			
			if (entity.getId() == null) {
				entity.setOperateTime(new Date());
				result = facilityFeeService.insert(entity);
			}else {
				result = facilityFeeService.update(entity);
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
			int cnt = facilityFeeService.deleteBatchById(list);
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
	
	
	@RequestMapping("listFields")
	@ResponseBody
	public Object listFields(){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			List<PairName> list = facilityFeeService.queryFields(result);
			
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("listFacilityNames")
	@ResponseBody
	public Map<String, Object> listFacilityNames(String field){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			result.put("field", field);
			List<PairName> list = facilityFeeService.queryFacilityNames(result);
			
			result.clear();
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.clear();
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("listModels")
	@ResponseBody
	public Map<String, Object> listModels(String field, String fname){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			result.put("field", field);
			result.put("name", fname);
			List<PairName> list = facilityFeeService.queryModels(result);
			
			result.clear();
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.clear();
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("listAccuracys")
	@ResponseBody
	public Map<String, Object> listAccuracys(String field, String fname, String model){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			result.put("field", field);
			result.put("name", fname);
			result.put("model", model);
			List<PairName> list = facilityFeeService.queryAccuracys(result);
			
			result.clear();
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.clear();
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("listTestRanges")
	@ResponseBody
	public Map<String, Object> listTestRanges(String field, String fname, String model, String accuracy){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			result.put("field", field);
			result.put("name", fname);
			result.put("model", model);
			result.put("accuracy", accuracy);
			List<PairName> list = facilityFeeService.queryTestRanges(result);
			
			result.clear();
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.clear();
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("listFeeUnits")
	@ResponseBody
	public Map<String, Object> listFeeUnits(String field, String fname, String model, String accuracy, String testRange){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			result.put("field", field);
			result.put("name", fname);
			result.put("model", model);
			result.put("accuracy", accuracy);
			result.put("testRange", testRange);
			List<PairName> list = facilityFeeService.queryFeeUnits(result);
			
			result.clear();
			result.put("data", list);
			result.put("success", true);
			
		}catch(Exception e)
		{
			result.clear();
			result.put("success", false);
			result.put("data", null);
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return result;
	}
}
