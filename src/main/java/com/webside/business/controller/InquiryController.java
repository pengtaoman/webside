package com.webside.business.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.webside.base.basecontroller.BaseController;
import com.webside.base.basemodel.PairMulti;
import com.webside.business.model.InquiryDetailsEntity;
import com.webside.business.model.InquiryEntity;
import com.webside.business.service.CompanyAuthService;
import com.webside.business.service.FacilityFeeService;
import com.webside.business.service.InquiryService;
import com.webside.business.service.PropertyService;
import com.webside.common.Common;
import com.webside.dtgrid.model.Pager;
import com.webside.dtgrid.util.ExportUtils;
import com.webside.exception.AjaxException;
import com.webside.exception.ServiceException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.user.model.UserEntity;
import com.webside.user.service.SmsService;
import com.webside.user.service.UserService;
import com.webside.util.BasePK;
import com.webside.util.PageUtil;
import com.webside.util.RoleType;

@Controller
@Scope("prototype")
@RequestMapping("/inquiry/")
public class InquiryController extends BaseController {

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyAuthService companyAuthService;

	@Autowired
	private FacilityFeeService facilityFeeService;
	
	@Autowired
	private SmsService smsService;

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
			return Common.BACKGROUND_PATH + "/inquiry/list";
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
	@RequestMapping(value = "/listInquiry.html", method = RequestMethod.POST)
	@ResponseBody
	public Object listInquiry(String gridPager) throws Exception{
		Map<String, Object> parameters = null;
		//1、映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		//2、设置查询参数
		parameters = pager.getParameters();
		List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
		if(companyIds.isEmpty()) {
			companyIds.add(0L);
		}
		
		Long cid = null;
		try {
			cid = Long.parseLong(parameters.get("cid").toString());
		} catch(Exception e) {}
		if (cid != null) {
			if (!companyIds.contains(cid)) {
				companyIds.clear();
				companyIds.add(0L);
			} else {
				companyIds.clear();
				companyIds.add(cid);
			}
		} 
		
		if (RoleType.ROLE_PLATFORM != ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			companyIds.clear();
			parameters.put("companyId", companyIds);
		} 
		parameters.put("companyIds", companyIds);
		
		//设置分页，page里面包含了分页信息
		Page<Object> page = PageHelper.startPage(pager.getNowPage(),pager.getPageSize(), true);
		List<InquiryEntity> list = inquiryService.queryListByPage(parameters);
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
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@ResponseBody
	public Object list(String gridPager, HttpServletResponse response) throws Exception{
		Map<String, Object> parameters = null;
		//1、映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		//2、设置查询参数
		parameters = pager.getParameters();
		
		List<Long> companyIds = companyAuthService.queryCompanyIdByUserId(ShiroAuthenticationManager.getUserId());
		if(companyIds.isEmpty()) {
			companyIds.add(0L);
		}
		
		Long cid = null;
		try {
			cid = Long.parseLong(parameters.get("cid").toString());
		} catch(Exception e) {}
		if (cid != null) {
			if (!companyIds.contains(cid)) {
				companyIds.clear();
				companyIds.add(0L);
			} else {
				companyIds.clear();
				companyIds.add(cid);
			}
		} 
		
		if (RoleType.ROLE_PLATFORM != ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
			companyIds.clear();
			companyIds.add(ShiroAuthenticationManager.getUserEntity().getCompanyId());
		}
		parameters.put("companyIds", companyIds);
		
		//3、判断是否是导出操作
		if(pager.getIsExport())
		{
			if(pager.getExportAllData())
			{
				//3.1、导出全部数据
				List<InquiryEntity> list = inquiryService.queryListAll(parameters);
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
			List<InquiryEntity> list = inquiryService.queryListByPage(parameters);
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
	
	/**
	 * ajax分页动态加载模式
	 * @param dtGridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/listDetails.html", method = RequestMethod.POST)
	@ResponseBody
	public Object listDetails(String gridPager, HttpServletResponse response) throws Exception{
		Map<String, Object> parameters = null;
		//1、映射Pager对象
		Pager pager = JSON.parseObject(gridPager, Pager.class);
		//2、设置查询参数
		parameters = pager.getParameters();
		
		if (parameters.isEmpty() || !parameters.containsKey("id")) {
			return null;
		} 
		
		String id = parameters.get("id").toString();
		
		//3、判断是否是导出操作
		if(pager.getIsExport())
		{
			if(pager.getExportAllData())
			{
				//3.1、导出全部数据
				List<InquiryDetailsEntity> list = inquiryService.queryDetailsList(id);
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
			List<InquiryDetailsEntity> list = inquiryService.queryDetailsList(id);
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
	
	/**
	 * ajax分页动态加载模式
	 * @param dtGridPager Pager对象
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDetails.html", method = RequestMethod.POST)
	@ResponseBody
	public Object getDetails(@RequestParam(value="id") String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
	
		try	{
			List<InquiryDetailsEntity> list = inquiryService.queryDetailsList(id);
			map.put("success", Boolean.TRUE);
			map.put("data", list);
		} catch(Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "操作失败");
		}
		return map;
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
			String data = createInquiryPrintData(id);
			map.put("success", Boolean.TRUE);
			map.put("data", data);
		} catch(Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("message", "操作失败");
		}
		return map;
	}
	
	private String createInquiryPrintData(String id) {
		String inquiryHtml = propertyService.findById(PropertyService.KEY_Inquiry_TEMPLATE).getContent();
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
	
	@RequestMapping("replyUI.html")
	public String replyUI(Model model, HttpServletRequest request, String id) {
		try
		{
			InquiryEntity entity = inquiryService.findById(id);
			if (entity.getReplyUserId() != null && entity.getReplyUserId() == 0L) {
				try {
					UserEntity user = userService.findById(entity.getReplyUserId());
					if(user != null) {
						entity.setReplyUserName(user.getUserName());
					}
				} catch(Exception e2) {}
				
			} 
			
			model.addAttribute("entity", entity);
			return Common.BACKGROUND_PATH + "/inquiry/reply";
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
	}
	
	@RequestMapping("updateReply.html")	
	@ResponseBody
	public Object updateReply(@RequestBody Map<String, Object> data) throws AjaxException
	{
		List<InquiryDetailsEntity> detailsList = JSONObject.parseArray(data.get("detailsList").toString(), InquiryDetailsEntity.class);
		InquiryEntity entity = JSONObject.toJavaObject((JSONObject)data.get("entity"), InquiryEntity.class);
		
		int result = -1;
		try
		{	
			if (entity.getId() == null || entity.getId().isEmpty()) {
				data.clear();
				data.put("success", Boolean.FALSE);
				data.put("data", null);
				data.put("message", "无法查找报价ID");
				
				return data;
			}else {
				// TODO 保存  报价人? 
				entity.setReplyUserId((ShiroAuthenticationManager.getUserId()));
				entity.setStatus(1);
				result = inquiryService.updateInquiry(entity, detailsList);
				
				if(result != -1)
				{
					if (entity.getUserId() != null) {
						List<Long> userIds = new ArrayList<Long>();
						userIds.add(entity.getUserId());
						// 创建一个字符串列表，每个字符串长度大于2
						smsService.insertSms(userIds, "报价消息: "+entity.getNote(), 
								String.format("收到来自%s的报价消息，询价单编号%s",
										ShiroAuthenticationManager.getUserEntity().getUserName(), entity.getId()));
					}
					
					
					data.put("success", Boolean.TRUE);
					data.put("data", null);
					data.put("message", "操作成功");
				}else
				{
					data.put("success", Boolean.FALSE);
					data.put("data", null);
					data.put("message", "操作失败");
				}
				return data;
			}
		}catch(ServiceException e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
	}

	@RequestMapping("editUI.html")
	public String editUI(Model model, HttpServletRequest request, String id) {
		try
		{
			InquiryEntity entity = inquiryService.findById(id);
			model.addAttribute("entity", entity);
			return Common.BACKGROUND_PATH + "/inquiry/add";
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
			if (RoleType.ROLE_PLATFORM == ShiroAuthenticationManager.getUserEntity().getRole().getType()) {
				model.addAttribute("selectcid", Boolean.TRUE);
			}
			return Common.BACKGROUND_PATH + "/inquiry/add";
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
		
	}
	
	@RequestMapping("update.html")	
	@ResponseBody
	public Object update(@RequestBody Map<String, Object> data) throws AjaxException
	{
		List<InquiryDetailsEntity> detailsList = JSONObject.parseArray(data.get("detailsList").toString(), InquiryDetailsEntity.class);
		InquiryEntity entity = JSONObject.toJavaObject((JSONObject)data.get("entity"), InquiryEntity.class);
		
		int result = -1;
		try
		{	
			if (entity.getId() == null || entity.getId().isEmpty()) {
				String title = entity.getNote();
				if (title == null || title.isEmpty()) {
					title = "询价单"+ (new SimpleDateFormat("yyyy-MM-dd_HHmm")).format(new Date());
				}
				
				entity.setCompanyId(ShiroAuthenticationManager.getCompanyId());
				entity.setUserId(ShiroAuthenticationManager.getUserId());
				String inquiryId = BasePK.getPK();
				entity.setId(inquiryId);
				entity.setStatus(0);
				entity.setNote(title);

				return insert(entity, detailsList);
			}else {
				entity.setUserId(ShiroAuthenticationManager.getUserId());
				result = inquiryService.updateInquiry(entity, detailsList);
				
				data.clear();
				if(result != -1)
				{
					List<Long> authUserIds = companyAuthService.queryUserIdByCompanyId(entity.getCompanyId());
					smsService.insertSms(authUserIds, "询价单变更消息: "+entity.getNote(), 
							String.format("收到来自%s~%s的询价修改消息，询价单编号%s", 
									ShiroAuthenticationManager.getUserEntity().getCompanyName(), 
									ShiroAuthenticationManager.getUserEntity().getUserName(),
									entity.getId()));
					
					data.put("success", Boolean.TRUE);
					data.put("data", null);
					data.put("message", "操作成功");
				}else
				{
					data.put("success", Boolean.FALSE);
					data.put("data", null);
					data.put("message", "操作失败");
				}
				return data;
			}
		}catch(ServiceException e)
		{
			e.printStackTrace();
			throw new AjaxException(e);
		}
	}
	
	public Object insert(InquiryEntity entity, List<InquiryDetailsEntity> detailsList) throws AjaxException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{		
			List<String> fids = new ArrayList<String>();
			List<InquiryDetailsEntity> noPriceList = new ArrayList<InquiryDetailsEntity>();
			
			for (InquiryDetailsEntity obj : detailsList) {
				obj.setInquiryId(entity.getId());
				
				// 如果已经有报价了则使用该报价，否则匹配标准报价
				if (obj.getPrice() == null || obj.getPrice() == BigDecimal.ZERO) {
					fids.add(obj.getId());
					noPriceList.add(obj);
				}
			}
			
			// 询价时，自动把报价填好 取值规则：  上次报价>标准报价
			if (!fids.isEmpty()) {
				List<PairMulti> finfo = facilityFeeService.queryFeePriceAndUnit(fids);
				Map<String, PairMulti> feeMap = new HashMap<String, PairMulti>(); 
				for (PairMulti obj : finfo) {
					feeMap.put(obj.getId(), obj);
				}
				for (InquiryDetailsEntity obj : noPriceList) {
					if (feeMap.containsKey(obj.getId())) {
						obj.setPrice(feeMap.get(obj.getId()).getVal2().toString());
						obj.setUnit(feeMap.get(obj.getId()).getVal2().toString());
					}
				}
			}
			
			
			int result =  0;
			
			result = inquiryService.insertDetailsBatch(entity, detailsList);
			
			if(result >= 1)
			{
				List<Long> authUserIds = companyAuthService.queryUserIdByCompanyId(entity.getCompanyId());
				// 创建一个字符串列表，每个字符串长度大于2
				smsService.insertSms(authUserIds, "询价单新建消息: "+entity.getNote(), 
						String.format("收到来自%s~%s的询价消息，询价单编号%s", 
								ShiroAuthenticationManager.getUserEntity().getCompanyName(), 
								ShiroAuthenticationManager.getUserEntity().getUserName(),
								entity.getId()));
				
				map.put("success", Boolean.TRUE);
				map.put("data", entity.getId());
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
			int cnt = inquiryService.deleteBatchById(Arrays.asList(userIds));
			if(cnt == userIds.length)
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
	
	@RequestMapping("addFacility.html")
	@ResponseBody
	public Object addFacility(@RequestBody Map<String, Object> data){
		Map<String, Object> result = new HashMap<String, Object>();
		try
		{
//			List<String> ids = JSONObject.parseArray(data.get("fids").toString(), String.class);
//			
//			{
//				result.put("success", false);
//				result.put("data", null);
//				result.put("message", "删除失败");
//			}
		}catch(Exception e)
		{
			throw new AjaxException(e);
		}
		return result;
	}
	
}
