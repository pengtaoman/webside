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
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.base.basemodel.PairName;
import com.webside.business.model.CodeEntity;
import com.webside.business.model.FacilityEntity;
import com.webside.business.model.RepairEntity;
import com.webside.business.service.CodeService;
import com.webside.business.service.CompanyAuthService;
import com.webside.business.service.FacilityFeeService;
import com.webside.business.service.FacilityService;
import com.webside.business.service.RepairService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
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
@RequestMapping("/facility/")
public class FacilityController extends BaseController {

	@Autowired
	private CompanyAuthService companyAuthService;

	@Autowired
	private FacilityFeeService facilityFeeService;
	
	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private RepairService repairService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CodeService codeService;
	
	/*String[] facilityState = {"新购入","正常","不合格","维修","封存","报废","借出","调修"};
	String[] ifTest = {"不可检测 ","可检测"};*/
	
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
			return Common.BACKGROUND_PATH + "/facility/list";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("list.html")
	@ResponseBody
	public Object list(String gridPager, HttpServletResponse response) throws Exception{
	
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();

		if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
			if(!companyIds.isEmpty()) {
				if(parameters.containsKey("companyName")){	
					List<Long> queryCids = companyService.queryCompanyIdByCname(parameters);
					List<Long> tempCids = new ArrayList<Long>();
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
		

		
		// 3、判断是否是导出操作
		if (pager.getIsExport()) {
			if (pager.getExportAllData()) {
				// 3.1、导出全部数据
				List<FacilityEntity> list = facilityService.queryListByPage(parameters);
				ExportUtils.exportAll(response, pager, list);
				return null;
			} else {
				// 3.2、导出当前页数据
				ExportUtils.export(response, pager);
				return null;
			}
		} else {
			//设置分页，page里面包含了分页信息
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
	}
	
	@RequestMapping("repairListUI.html")
	public String repairListUI(Model model, HttpServletRequest request) {
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
			return Common.BACKGROUND_PATH + "/facility/repairList";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("repairList.html")
	@ResponseBody
	public Object repairList(String gridPager){
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("name", null);
		}
		
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
		
		// 设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
	
		List<RepairEntity> list = repairService.queryListByPage(parameters);
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
	/*
	@RequestMapping("listGridUI.html")
	public String listTreeUI(Model model, HttpServletRequest request) {
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
			return Common.BACKGROUND_PATH + "/resource/listGrid";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	
	@RequestMapping("listGrid.html")
	@ResponseBody
	public Object listTree(String gridPager) {
		Map<String,Object> parameters = null;
		// 映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		// 判断是否包含自定义参数
		parameters = pager.getParameters();
		if (parameters.size() < 0) {
			parameters.put("name", null);
			parameters.put("parentId", null);
		}else if(!parameters.containsKey("name"))
		{
			parameters.put("name", null);
		}
		Page<Object> page = null;
		if(null == parameters.get("parentId") || "".equals(parameters.get("parentId")))
		{
			// 设置分页，page里面包含了分页信息
			page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		}
		List<ResourceEntity> list = resourceService.queryTreeGridListByPage(parameters);
		parameters.clear();
		parameters.put("isSuccess", Boolean.TRUE);
		if(null != page)
		{
			parameters.put("nowPage", pager.getNowPage());
			parameters.put("pageSize", pager.getPageSize());
			parameters.put("pageCount", page.getPages());
			parameters.put("recordCount", page.getTotal());
			parameters.put("startRecord", page.getStartRow());
		}
		//列表展示数据
		parameters.put("exhibitDatas", list);
		return parameters;
	}
	
	
	@RequestMapping("withoutAuth/resourceTree.html")
	@ResponseBody
	public Object resourceTree(int roleId) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		List<JSTreeEntity> jstreeList = null;
		try{
			parameter.put("isHide", 0);
			//parameter.put("type", 0);
			parameter.put("roleId", roleId);
			List<ResourceEntity> list = resourceService.queryResourceList(parameter);
			jstreeList = new TreeUtil().generateJSTree(list);
		}
		catch (Exception e) {
			jstreeList = new ArrayList<JSTreeEntity>();
			logger.error(e.getMessage(), e);
		}
		return jstreeList;
	}
	
	
	@RequestMapping("withoutAuth/resourceSelectTree.html")
	@ResponseBody
	public Object resourceSelectTree() {
		List<Select2Entity> select2Entity = null;
		try
		{
			Map<String,Object> parameter = new HashMap<String,Object>();
			List<ResourceEntity> list = resourceService.queryListByPage(parameter);
			select2Entity = new TreeUtil().getSelectTree(list, null);
			parameter.clear();
			parameter.put("items", select2Entity);
			return parameter;
		}catch (Exception e) {
			throw new AjaxException(e);
		}
	}
	*/

	@RequestMapping("uploadUI.html")
	public String uploadUI(Model model, HttpServletRequest request) {
		try
		{
			if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
				model.addAttribute("selectcid", Boolean.TRUE);
			}
			return Common.BACKGROUND_PATH + "/facility/upload";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping("upload.html")
	@ResponseBody
	public String upload(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/facility/upload";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("uploadSave.html")
	@ResponseBody
	public Object uploadSave(@RequestBody Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			Long cid = 0L;
			if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
				if(data.containsKey("cid")) {
					cid = Long.parseLong(data.get("cid").toString());
				} else {
					map.put("success", Boolean.FALSE);
					map.put("message", "无法取得器具的公司编号");
					return map;
				}
			} else {
				cid = ShiroAuthenticationManager.getCompanyId();
			}
			
			map.put("cid", cid);	
			
			List<FacilityEntity> detailsList = JSONObject.parseArray(data.get("detailsList").toString(), FacilityEntity.class);
			List<String> fids = new ArrayList<String>();
			
			for (FacilityEntity entity : detailsList) {
				fids.add(entity.getFactoryNo());
			}
			map.put("fids", fids);
			
			// 取得出厂编号，一个公司下出厂编号是唯一
			List<String> existFids = facilityService.selectFactoryId(map);
			List<FacilityEntity> newList = new ArrayList<FacilityEntity>();
			
			for (FacilityEntity entity : detailsList) {
				String fno = entity.getFactoryNo();
				if (fno == null || fno.trim().isEmpty()) {
					entity.setId("保存错误:出厂编号为空");
				} else if(existFids.contains(fno)) {
					entity.setId("保存错误:已存在");
				} else {
					existFids.add(entity.getFactoryNo());
					entity.setId(BasePK.getPK());
					entity.setOperater(ShiroAuthenticationManager.getUserAccountName());
					entity.setCid(cid);
					entity.setOperater(ShiroAuthenticationManager.getUserAccountName());
					
					newList.add(entity);
				}
			}
			
			if(!newList.isEmpty()) {
				int result = facilityService.insertBatch(newList);
				if(result > 0) {
					map.put("success", Boolean.TRUE);
					map.put("data", detailsList);
					map.put("message", "保存成功");
				} else {
					map.put("success", Boolean.FALSE);
					map.put("data", detailsList);
					map.put("message", "保存失败");
				}
				return map;
			}
			map.put("success", Boolean.TRUE);
			map.put("data", detailsList);
			map.put("message", "请检查保存结果");
			
		} catch(BadSqlGrammarException e){
			map.put("success", Boolean.FALSE);
			map.put("message", "保存数据失败，请修改错误的数据记录。");
			
		} catch(Exception e)
		{
			throw new SystemException(e);
		}
		return map;
	}
	
//	@RequestMapping("uploadSave.html")
//	@ResponseBody
//	public Object uploadSave(@RequestBody Map<String, Object> data) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		try
//		{
//			List<FacilityEntity> detailsList = JSONObject.parseArray(data.get("detailsList").toString(), FacilityEntity.class);
//			for (FacilityEntity entity : detailsList) {
//				try {
//					entity.setId(BasePK.getPK());
//					entity.setOperater(ShiroAuthenticationManager.getUserAccountName());
//					if(facilityService.insert(entity) != 1) {
//						entity.setId("保存错误");
//					} 
//				} catch(Exception e) {
//					if(e.getCause() instanceof DuplicateKeyException) {
//						entity.setId("保存错误:已存在");
//					} else {
//						entity.setId("保存错误:未知错误");
//					}
//				}
//			}
//			
//			map.put("success", Boolean.TRUE);
//			map.put("data", detailsList);
//			map.put("message", "添加成功");
//		}catch(Exception e)
//		{
//			throw new SystemException(e);
//		}
//		return map;
//	}
	
	@RequestMapping("addUI.html")
	public String addUI(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/facility/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

	@RequestMapping("add.html")
	@ResponseBody
	public Object add(FacilityEntity facilityEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{   facilityEntity.setId(BasePK.getPK());
			facilityEntity.setOperater(ShiroAuthenticationManager.getUserAccountName());
			facilityEntity.setPriceUnit("个");
			int result = facilityService.insert(facilityEntity);
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
	
	@RequestMapping("addCopyUI.html")
	public String addCopyUI(Model model, HttpServletRequest request, String id) {
		try
		{
			FacilityEntity facilityEntity = facilityService.findById(id);
			facilityEntity.setId(null);
			facilityEntity.setFactoryNo("");
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("facilityEntity", facilityEntity);
			CompanyEntity companyEntity = companyService.findById(facilityEntity.getCid().longValue());
			model.addAttribute("cidNameInit", companyEntity.getName());
			
			CodeEntity codeEntity = codeService.query(facilityEntity.getFacilityState(), "facilityState");
			if (codeEntity == null) {
				model.addAttribute("facilityStateName", "未知");
			} else {
				model.addAttribute("facilityStateName", facilityEntity.getFacilityState());
			}
			
			CodeEntity codeEntity2 = codeService.query(facilityEntity.getIfTest(), "ifTest");
			if (codeEntity2 == null) {
				model.addAttribute("ifTestName", "未知");
			} else {
				model.addAttribute("ifTestName", facilityEntity.getIfTest());
			}
			return Common.BACKGROUND_PATH + "/facility/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{
			FacilityEntity facilityEntity = facilityService.findById(id);
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			model.addAttribute("page", page);
			model.addAttribute("facilityEntity", facilityEntity);
			CompanyEntity companyEntity = companyService.findById(facilityEntity.getCid().longValue());
			model.addAttribute("cidNameInit", companyEntity.getName());
			
			CodeEntity codeEntity = codeService.query(facilityEntity.getFacilityState(), "facilityState");
			if (codeEntity == null) {
				model.addAttribute("facilityStateName", "未知");
			} else {
				model.addAttribute("facilityStateName", facilityEntity.getFacilityState());
			}
			
			CodeEntity codeEntity2 = codeService.query(facilityEntity.getIfTest(), "ifTest");
			if (codeEntity2 == null) {
				model.addAttribute("ifTestName", "未知");
			} else {
				model.addAttribute("ifTestName", facilityEntity.getIfTest());
			}
			return Common.BACKGROUND_PATH + "/facility/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("edit.html")
	@ResponseBody
	public Object update(FacilityEntity facilityEntity)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			
			int result = facilityService.updateByPrimaryKeySelective(facilityEntity);
			if(result > 0)
			{
				map.put("success", Boolean.TRUE);
				map.put("data", null);
				map.put("message", "编辑成功");
			}else
			{
				map.put("success", Boolean.FALSE);
				map.put("data", null);
				map.put("message", "编辑失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return map;
	}

	
	@RequestMapping("deleteBatch.html")
	@ResponseBody
	public Object deleteBatch(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
			String[] facilityIds = ids.split(",");
			List<String> list = new ArrayList<String>();
			for (String string : facilityIds) {
				list.add(string);
			}
			boolean flag = facilityService.deleteBatchByIds(list);
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
	@RequestMapping("repairUI.html")
	public String repairUI(Model model, HttpServletRequest request, String id) {
		try
		{  
			List<RepairEntity> repairEntityList = repairService.findRepairingById(id);
			if(repairEntityList.size()>1){
				throw new Exception("联系系统管理员");
			}
			PageUtil page = new PageUtil();
			page.setPageNum(Integer.valueOf(request.getParameter("page")));
			page.setPageSize(Integer.valueOf(request.getParameter("rows")));
			page.setOrderByColumn(request.getParameter("sidx"));
			page.setOrderByType(request.getParameter("sord"));
			if(repairEntityList.size()==1){
				repairEntityList.get(0).setIfNew("0");  //有在途调修记录
				model.addAttribute("repairEntity", repairEntityList.get(0));
			} else {
				FacilityEntity facilityEntity = facilityService.findById(id);
				RepairEntity repairEntity = new RepairEntity();
				repairEntity.setCid(facilityEntity.getCid());
				repairEntity.setFacilityId(facilityEntity.getId());
				repairEntity.setFacilityName(facilityEntity.getFacilityName());
				repairEntity.setFactoryNo(facilityEntity.getFactoryNo());
				repairEntity.setId(id);
				model.addAttribute("repairEntity", repairEntity);
				
			}
			model.addAttribute("page", page);
			return Common.BACKGROUND_PATH + "/facility/repairForm";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping("repair.html")
	@ResponseBody
	@Transactional
	public Object repair(RepairEntity repairEntity) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = false;
		try
		{   
			String facilityState = "";
			if ("0".equals(repairEntity.getIfNew())){
				repairEntity.setOperator(ShiroAuthenticationManager.getUserAccountName());
				repairEntity.setUpdateTime(new Date());
				flag = repairService.saveRepairRecordChange(repairEntity);
				facilityState = "1";
			}
			if ("1".equals(repairEntity.getIfNew())){
				repairEntity.setId(BasePK.getPK());
				repairEntity.setOperator(ShiroAuthenticationManager.getUserAccountName());
				facilityState = "7";
				flag = repairService.saveRepairRecord(repairEntity);
			}
			
			FacilityEntity facilityEntity = new FacilityEntity();
			facilityEntity.setId(repairEntity.getFacilityId());
			facilityEntity.setFacilityState(facilityState);
			flag = facilityService.updateFacilityStateById(facilityEntity);

			if(flag)
			{
				result.put("success", true);
				result.put("data", null);
				result.put("message", "操作成功");
			}else
			{
				result.put("success", false);
				result.put("data", null);
				result.put("message", "操作失败");
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}
	
	/*
	
	@RequestMapping("withoutAuth/icon.html")
	public String icon() {
		return Common.BACKGROUND_PATH + "/resource/icon";
	}
	
	
	@RequestMapping("withoutAuth/validateResource.html")
	@ResponseBody
	public Object validateResource(Integer resourceId){
		try
		{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", resourceId);
			int count = resourceService.count(param);
			if(count > 0)
			{
				return true;
			}else
			{
				return false;
			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	*/
	@RequestMapping("company/list")
	@ResponseBody
	public Object companylist(){
		try
		{
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", ShiroAuthenticationManager.getUserEntity().getId());
			
			if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
				List<PairName> list = companyAuthService.queryNames(param);
				param.put("results", list);
				param.put("selectcid", true);
			} else {
				List<PairName> list = new ArrayList<PairName>();
				list.add(new PairName(ShiroAuthenticationManager.getCompanyId(), ShiroAuthenticationManager.getCompanyName()));
				param.put("results", list);
				param.put("selectcid", false);
			}
			return param;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	@RequestMapping("facilityType/list")
	@ResponseBody
	public Object facilityTypelist(){
		try
		{
			Map<String, Object> result = new HashMap<String, Object>();
			List<PairName> list = facilityFeeService.queryFields(result);
			result.put("results", list);
			return result;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("facilityName/list")
	@ResponseBody
	public Object facilityNamelist(String facilityType){
		try
		{ 
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("field", facilityType);
			List<PairName> list = facilityFeeService.queryFacilityNames(result);
			result.clear();
			result.put("results", list);
			return result;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	//所属专业 计量器具 规格型号 准确度等级 测量范围
	@RequestMapping("model/list")
	@ResponseBody
	public Object modellist(String facilityType, String facilityName){
		try
		{   
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("field", facilityType);
			result.put("name", facilityName);
			List<PairName> list = facilityFeeService.queryModels(result);
			
			result.clear();
			result.put("results", list);
			return result;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	@RequestMapping("accuracy/list")
	@ResponseBody
	public Object accuracylist(String facilityType, String facilityName, String model){
		try
		{   
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("field", facilityType);
			result.put("name", facilityName);
			result.put("model", model);
			List<PairName> list = facilityFeeService.queryAccuracys(result);
			
			result.clear();
			result.put("results", list);
			return result;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("testRange/list")
	@ResponseBody
	public Object testrangelist(String facilityType, String facilityName, String model, String accuracy){
		try
		{   
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("field", facilityType);
			result.put("name", facilityName);
			result.put("model", model);
			result.put("accuracy", accuracy);
			List<PairName> list = facilityFeeService.queryTestRanges(result);
			
			result.clear();
			result.put("results", list);
			return result;
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("facilityState")
	@ResponseBody
	public Object facilityState() {
		try {
			List<CodeEntity> back = codeService.valueAndName("facilityState");
			ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < back.size(); i++) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("id", back.get(i).getValue());
				param.put("text", back.get(i).getName());
				list.add(param);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("results", list);
			return result;
		} catch (Exception e) {
			throw new AjaxException(e);
		}
	}
	
}
