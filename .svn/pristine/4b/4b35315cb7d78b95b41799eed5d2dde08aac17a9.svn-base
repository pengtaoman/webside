package com.webside.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.webside.business.model.EnforceEntrustEntity;
import com.webside.business.model.ExamineOrganEntity;
import com.webside.business.model.TaskEntity;
import com.webside.business.service.CompanyAuthService;
import com.webside.business.service.EntrustService;
import com.webside.business.service.ExamineOrganService;
import com.webside.business.service.FacilityService;
import com.webside.business.service.PropertyService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.exception.AjaxException;
import com.webside.exception.SystemException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.service.CompanyService;
import com.webside.util.PageUtil;
import com.webside.util.RoleType;

@Controller
@Scope("prototype")
@RequestMapping("/entrustMgr/")
public class EntrustMgrController extends BaseController {

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private EntrustService entrustService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyAuthService companyAuthService;
	
	@Autowired
	private ExamineOrganService examineOrganService;
	
	@RequestMapping("taskListUI.html")
	public String taskListUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/entrustMgr/taskList";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	@RequestMapping("taskList.html")
	@ResponseBody
	public Object taskList(String gridPager) {
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		parameters.put("ifValid", "2");
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
/*
		if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
			if(!companyIds.isEmpty()) {
				parameters.put("companyIds", companyIds);
			} else {
				parameters.put("cid", 0L);
			}
		} else {
			parameters.put("cid", ShiroAuthenticationManager.getCompanyId());
		}
		*/
		

		if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
			if(!companyIds.isEmpty()) {
				if(parameters.containsKey("companyName")){	
					List<Long> queryCids = companyService.queryCompanyIdByCname(parameters);
					List<Long> tempCids = new ArrayList();
					//如果没有交集，让公司ID作为查询条件起作用
					for(Long id:companyIds){
						if(queryCids.contains(id)){
							tempCids.add(id);
						}
					}
					if(tempCids.isEmpty()){
						tempCids.add(0L);
					}
					companyIds = tempCids;
				}
				parameters.put("companyIds", companyIds);
			} else {
				parameters.put("companyIds", 0L);
			}
		} else {
			parameters.put("cid", ShiroAuthenticationManager.getCompanyId());
		}
	
		List<TaskEntity> list = entrustService.queryTaskListByPage(parameters);
		List<TaskEntity> backList = new ArrayList();
		
		if(parameters.containsKey("ifOver") &&
			parameters.get("ifOver") != null &&
				!"".equals(parameters.get("ifOver"))){
			for(int i=0; i<list.size(); i++){
				if("完检".contains(parameters.get("ifOver").toString())
						&&"0".equals(list.get(i).getIfOver())){
					backList.add(list.get(i));
				} else if("进行中".contains(parameters.get("ifOver").toString())
						&&!"0".equals(list.get(i).getIfOver())){
					backList.add(list.get(i));
				} 
			}			
		} else {
			backList = list;
		}
		
		
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		parameters.put("nowPage", pager.getNowPage());
		parameters.put("pageSize", pager.getPageSize());
		parameters.put("pageCount", page.getPages());
		parameters.put("recordCount", page.getTotal());
		parameters.put("startRecord", page.getStartRow());
		//列表展示数据
		parameters.put("exhibitDatas", backList);
		return parameters;
	}
	
	@RequestMapping("backTask.html")
	@ResponseBody
	public Object releaseTask(String taskIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String []taskArr = taskIds.split(",");
			int count=0;
			for(String taskId : taskArr){
				count += entrustService.updateTask(taskId,"1");
			}
	
			if (count == taskArr.length) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "任务全部回退成功！");
			} else if(count >=0 && count < taskArr.length) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "任务回退部分成功！");
			} else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return map;
	}

	@RequestMapping("saveEntrustStatus.html")
	@ResponseBody
	public Object saveEntrustStatus(String entrustId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int count = entrustService.saveEntrustStatus(entrustId);
	
			if (1 == count) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "更新成功！");
			} else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "更新失败！");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return map;
	}	
	
	@RequestMapping("saveTestResult.html")
	@ResponseBody
	public Object saveTestResult(String facilityIds, String testResult, String entrustId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String []facilityIdArr = facilityIds.split(",");
			int count=0;
			for(String facilityId : facilityIdArr){
				count += entrustService.updateEntrustFicilityTestResult(entrustId,facilityId,testResult);
			}
	
			if (count == facilityIdArr.length) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "检测结果全部更新成功！");
			} else if(count >=0 && count < facilityIdArr.length) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "检测结果部分更新成功！");
			} else {
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "更新失败");
			}
		} catch (Exception e) {
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("entrustUI.html")
	public String entrustUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			String taskId = request.getParameter("taskId");
			String cid = request.getParameter("cid");
			String companyName = request.getParameter("companyName");
			HashMap map = new HashMap();
			map.put("taskId", taskId);
			List<EnforceEntrustEntity> list = entrustService.selectEnforceEntrust(map);
			if(list!=null && list.size()>0){
				model.addAttribute("entrustId", list.get(0).getId());
				model.addAttribute("entrustName", list.get(0).getName());
				model.addAttribute("ifEnforce", list.get(0).getIfEnforce());
			}
			model.addAttribute("companyName", companyName);
			model.addAttribute("page", page);
			model.addAttribute("taskId", taskId);
			model.addAttribute("cid", cid);
			return Common.BACKGROUND_PATH + "/entrustMgr/entrust";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("entrustFacilityList.html")
	@ResponseBody
	public Object list(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		Object obj = parameters.get("testResult");
		if(obj!=null&&!"".equals(obj.toString())){
			String testResult = obj.toString();
			if("未检测".contains(testResult)){
				parameters.put("testResult", "0");
			} else if (testResult.contains("不")){
				parameters.put("testResult", "2");
			} else {
				parameters.put("testResult", "1");
			}
		}
	
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		
		List<Map> list = new ArrayList();
		if (null != parameters.get("entrustId").toString() && !"".equals(parameters.get("entrustId").toString())) {
			list = entrustService.entrustFacilityListByPage(parameters);
		}
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
	
	@RequestMapping("taskEntrustList.html")
	@ResponseBody
	public Object taskEntrustList(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
	
		List<Map> list = entrustService.taskEntrustList(parameters);
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
	
	/**
	 * ajax分页动态加载模式
	 * @param dtGridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPrintData.html", method = RequestMethod.POST)
	@ResponseBody
	public Object getPrintData(@RequestParam(value="id") String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
	
		try	{
			String data = getPrintTemplate(id);
			map.put("success", Boolean.TRUE);
			map.put("data", data);
		} catch(Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "操作失败");
		}
		return map;
	}
	
	private String getPrintTemplate(String id) {
		String inquiryHtml = propertyService.findById(id).getContent();
//		List<InquiryDetailsEntity> list = inquiryService.queryDetailsList(id);
//		
//		StringBuilder sb = new StringBuilder();
//		for (InquiryDetailsEntity entity : list) {
//		}
//		Document doc = Jsoup.parse(inquiryHtml);
//		Elements links = doc.select("a[href]");
//		// 替换html中的table，其它内容
		
		
		return inquiryHtml;
	}
	@RequestMapping("newEntrustUI.html")
	public String newEntrust(Model model, HttpServletRequest request) {
		try {
			String taskId = request.getParameter("taskId");
			String cid = request.getParameter("cid");
			String entrustId = request.getParameter("entrustId");
			EnforceEntrustEntity entrustEntity = new EnforceEntrustEntity();
			ExamineOrganEntity organEntity = new ExamineOrganEntity();
			if(null!=entrustId && !"".equals(entrustId)){
				entrustEntity = entrustService.selectEnforceEntrustById(entrustId);
				organEntity = examineOrganService.findById(Long.valueOf(entrustEntity.getOrganId()));
			}
			model.addAttribute("taskId", taskId);
			model.addAttribute("enforceEntrustEntity",entrustEntity);
			model.addAttribute("organName",organEntity.getName());
			model.addAttribute("cid", cid);
			return Common.BACKGROUND_PATH + "/entrustMgr/newEntrust";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
}
