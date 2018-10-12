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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.business.mapper.FacilityFeeMapper;
import com.webside.business.model.CodeEntity;
import com.webside.business.model.EnforceEntrustEntity;
import com.webside.business.model.ExamineOrganEntity;
import com.webside.business.model.FacilityEntity;
import com.webside.business.model.FacilityFeeEntity;
import com.webside.business.model.FacilityFeeEntityExample;
import com.webside.business.model.FacilityFeeEntityExample.Criteria;
import com.webside.business.model.InquiryEntity;
import com.webside.business.model.TaskEntity;
import com.webside.business.model.TaskFacilityEntity;
import com.webside.business.service.CodeService;
import com.webside.business.service.CompanyAuthService;
import com.webside.business.service.EntrustService;
import com.webside.business.service.ExamineOrganService;
import com.webside.business.service.FacilityService;
import com.webside.business.service.InquiryService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.exception.AjaxException;
import com.webside.exception.SystemException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.model.CompanyEntity;
import com.webside.user.service.CompanyService;
import com.webside.util.BasePK;
import com.webside.util.PageUtil;
import com.webside.util.RoleType;

@Controller
@Scope("prototype")
@RequestMapping("/entrust/")
public class EntrustController extends BaseController {

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private EntrustService entrustService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private InquiryService inquriyService;
	
	@Autowired
	private ExamineOrganService examineOrganService;
	
	@Autowired
	private CompanyAuthService companyAuthService;
	
	@Autowired
	private FacilityFeeMapper facilityFeeMapper;
	
	
	
	
	
	@RequestMapping("taskUI.html")
	public String listUI(Model model, HttpServletRequest request) {
		try {
			PageUtil page = new PageUtil();
			if (request.getParameterMap().containsKey("page")) {
				page.setPageNum(Integer.valueOf(request.getParameter("page")));
				page.setPageSize(Integer.valueOf(request.getParameter("rows")));
				page.setOrderByColumn(request.getParameter("sidx"));
				page.setOrderByType(request.getParameter("sord"));
			}
			TaskEntity taskEntity = new TaskEntity();
			String taskId = request.getParameter("taskId");
			if( null!=taskId && !"".equals(taskId)){
				taskEntity = entrustService.selectTaskById(taskId);
				CompanyEntity companyEntity = companyService.findById(taskEntity.getCid().longValue());
				model.addAttribute("cidNameInit", companyEntity.getName());
				CodeEntity codeEntity = codeService.query(taskEntity.getStrategy(), "strategy");
				model.addAttribute("strategyNameInit",codeEntity.getName());
				if(null!=taskEntity.getInquiryId()&&!"".equals(taskEntity.getInquiryId())){
					InquiryEntity inquiryEntity = inquriyService.findById(taskEntity.getInquiryId());
					model.addAttribute("inquiryNoteInit", inquiryEntity.getNote());
				}
			}
			model.addAttribute("page", page);
			model.addAttribute("taskEntity", taskEntity);
			return Common.BACKGROUND_PATH + "/entrust/task";
		} catch (Exception e) {
			throw new SystemException(e);
		}
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
			return Common.BACKGROUND_PATH + "/entrust/newEntrust";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
	
	
	@RequestMapping("organ.html")
	@ResponseBody
	public ExamineOrganEntity organ(Model model, HttpServletRequest request) {
		try {
			String organId = request.getParameter("organId");
			ExamineOrganEntity organEntity = examineOrganService.findById(Long.valueOf(organId));
			return organEntity;
		} catch (Exception e) {
			throw new SystemException(e);
		}
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
			model.addAttribute("page", page);
			model.addAttribute("taskId", taskId);
			model.addAttribute("cid", cid);
			return Common.BACKGROUND_PATH + "/entrust/entrust";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}
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
			return Common.BACKGROUND_PATH + "/entrust/taskList";
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
		parameters.put("ifValid0or1", "0");
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		
		if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
			if(!companyIds.isEmpty()) {
				parameters.put("companyIds", companyIds);
			} else {
				parameters.put("companyIds", 0L);
			}
		} else {
			parameters.put("cid", ShiroAuthenticationManager.getCompanyId());
		}
	
		List<TaskEntity> list = entrustService.queryTaskListByPage(parameters);
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
	
	@RequestMapping("taskDetailList.html")
	@ResponseBody
	public Object taskDetailList(String gridPager) { 
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();

		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
	
		List<TaskFacilityEntity> list = entrustService.queryTaskFacilityListByPage(parameters);
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
	@RequestMapping("addTask.html")
	@ResponseBody
	public Object add(TaskEntity taskEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			taskEntity.setId(BasePK.getPK());
			taskEntity.setOperator(ShiroAuthenticationManager.getUserAccountName());
			taskEntity.setIfValid("0");
			int result = entrustService.saveTask(taskEntity);
			if (result == 1) {
				map.put("success", Boolean.TRUE);
				map.put("id", taskEntity.getId());
				map.put("strategy", taskEntity.getStrategy());
				map.put("inquiryId", taskEntity.getInquiryId());
				map.put("message", "添加成功");
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
	@RequestMapping("saveExpectPrice.html")
	@ResponseBody
	public Object saveExpectPrice(String expectPrice, String taskId, String facilityId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   
			int result = entrustService.saveExpectPrice(expectPrice, taskId, facilityId);
			if(result==1)
			{
				map.put("success", Boolean.TRUE);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	
	@RequestMapping("showFee.html")
	@ResponseBody
	public Object showFee(String facilityId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   
			FacilityEntity facilityEntity = facilityService.findById(facilityId);
			String lastFee = "无";
			String fee = "无";
			if( facilityEntity!=null){
				if(facilityEntity.getLastRealPrice()!=null){
					lastFee = facilityEntity.getLastRealPrice().toString();
				}
				String facilityName = facilityEntity.getFacilityName();
				String facilityType = facilityEntity.getFacilityType();
				String model = facilityEntity.getModel();
				String accuracy = facilityEntity.getAccuracy();
				String testRange = facilityEntity.getTestRange();
				
				FacilityFeeEntityExample example = new FacilityFeeEntityExample();
				Criteria criteria = example.createCriteria();
				if(!StringUtils.isEmpty(facilityName)){
					criteria.andNameEqualTo(facilityName);
				}
				if(!StringUtils.isEmpty(facilityType)){
					criteria.andFieldEqualTo(facilityType);
				}				
				if(!StringUtils.isEmpty(model)){
					criteria.andModelEqualTo(model);
				}				
				if(!StringUtils.isEmpty(accuracy)){
					criteria.andAccuracyEqualTo(accuracy);
				}				
				if(!StringUtils.isEmpty(testRange)){
					criteria.andTestRangeEqualTo(testRange);
				}				
				List<FacilityFeeEntity> facilityFeeList = facilityFeeMapper.selectByExample(example);
				
				if(facilityFeeList!=null && facilityFeeList.size()>0){
					if(facilityFeeList.get(0).getFeePrice()!=null){
						fee = facilityFeeList.get(0).getFeePrice().toString();
					}
				}
			}

			map.put("success", Boolean.TRUE);
			map.put("lastFee", lastFee);
			map.put("fee", fee);
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("addEntrust.html")
	@ResponseBody
	public Object addEntrust(EnforceEntrustEntity enforceEntrustEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   
			String id = enforceEntrustEntity.getId();
			String optType = "";
			if(id!=null&&!"".equals(id)){
				optType = "change";
			} else {
				optType = "add";
				enforceEntrustEntity.setId(BasePK.getPK());
			}
			
			int result = entrustService.saveEnforceEntrust(enforceEntrustEntity,optType);
			if(result==1)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	@RequestMapping("addTaskDetail.html")
	@ResponseBody
	public Object addTaskDetail(String facilityIds, String taskId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   System.out.println(facilityIds);
			boolean result = entrustService.saveTaskDetail(facilityIds, taskId);
			if(result)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("addFacilitysToEntrust.html")
	@ResponseBody
	public Object addFacilitysToEntrust(String facilityIds, String taskId, String entrustId, String expectPrices, int cid)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   System.out.println(facilityIds);
			boolean result = entrustService.saveFacilitysToEntrust(facilityIds, taskId, entrustId,expectPrices, cid);
			if(result)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "添加成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "添加失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}
	
	@RequestMapping("setTask.html")
	@ResponseBody
	public Object setTask(String taskId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = entrustService.updateTask(taskId,"1");
			if (result == 1) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "任务发布成功！");
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
	@RequestMapping("releaseTask.html")
	@ResponseBody
	public Object releaseTask(String taskId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = entrustService.updateTask(taskId,"2");
			if (result == 1) {
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "任务发布成功！");
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
	
	@RequestMapping("deleteTasks.html")
	@ResponseBody
	public Object deleteTasks(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] taskIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : taskIds) {
				list.add(string);
			}
			boolean flag = entrustService.deleteTaskByIds(list);
			if(flag)
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
	
	@RequestMapping("deleteTaskFacility.html")
	@ResponseBody
	public Object deleteTaskFacility(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			boolean flag = entrustService.deleteTaskFacility(id);
			if(flag)
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
	@RequestMapping("deleteEntrusts.html")
	@ResponseBody
	public Object deleteEntrusts(String entrustIds) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] entrustsIdArr = entrustIds.split(",");
			List<String> entrustsIdList = new ArrayList<String>();
			for (String string : entrustsIdArr) {
				entrustsIdList.add(string);
			}
			boolean flag = entrustService.deleteEntrustByIds(entrustsIdList);
			if(flag)
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
	@RequestMapping("entrustFacilityList.html")
	@ResponseBody
	public Object list(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		System.out.println(parameters.get("taskId"));
		System.out.println(parameters.get("entrustId"));

		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
	
		List<Map> list = entrustService.facilityListByPage(parameters);
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
	
	@RequestMapping("facilityList.html")
	@ResponseBody
	public Object facilityList(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
	
		List<FacilityEntity> list = facilityService.queryListByPage(parameters);
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
	
	
	@RequestMapping("taskEntrusts.html")
	@ResponseBody
	public Object taskEntrusts(String taskId) {
		try {

			Map<String, Object> result = new HashMap<String, Object>();
			ArrayList list = new ArrayList();
			List<EnforceEntrustEntity> enforceEntrustList = entrustService.selectEnforceEntrustListByTaskId(taskId);
			for(EnforceEntrustEntity enforceEntrustEntity:enforceEntrustList){
				Map<String, String> param1 = new HashMap<String, String>();
				param1.put("id", enforceEntrustEntity.getId());
				param1.put("text", enforceEntrustEntity.getName());
				list.add(param1);
			}
			result.put("results", list);
			return result;
		} catch (Exception e) {
			throw new AjaxException(e);
		}
	}
	
	
	@RequestMapping("strategy/list")
	@ResponseBody
	public Object strategyList(String type) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			ArrayList backList = new ArrayList();
			List<CodeEntity> list = codeService.valueAndName(type);
			
			for(CodeEntity codeEntity: list){
				HashMap map = new HashMap();
				map.put("id", codeEntity.getValue());
				map.put("text", codeEntity.getName());
				backList.add(map);
			}
			result.put("results", backList);
			return result;
		} catch (Exception e) {
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("inquiry/list")
	@ResponseBody
	public Object inquiryList(String cid) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			ArrayList backList = new ArrayList();
			HashMap pmap = new HashMap();
			pmap.put("cid", cid);
			pmap.put("status", "1");
			List<Map> list = entrustService.inquiryList(pmap);

			result.put("results", list);
			return result;
		} catch (Exception e) {
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("facilitList.html")
	@ResponseBody
	public Object facilitList(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		String cid = (String) parameters.get("cid");
		String strategy = (String) parameters.get("strategy");
		String inquiryId = (String) parameters.get("inquiryId");
		List<FacilityEntity> list = new ArrayList<FacilityEntity>();
		if (null != parameters.get("strategy") && !"".equals(parameters.get("strategy"))
				&& null != parameters.get("cid") && !"".equals(parameters.get("cid"))) {
			if ("1".equals(parameters.get("strategy"))) {
				list = facilityService.queryListByPage(parameters);
			} else if ("2".equals(parameters.get("strategy")) && null != parameters.get("inquiryId") 
					&& !"".equals(parameters.get("inquiryId"))) {
				list = facilityService.queryInquriyListByPage(parameters);
			} else if ("3".equals(parameters.get("strategy"))){
				
			}
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
	
}
