<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/inquiryFeeSearch.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/inquiryreply.js"></script>
<script type="text/javascript">
$(function() {
	InquirySearchPanel.InitSearchPanel($("#inquirySearchPanelEmbed"), 3, null);
	$("#btnPrint").click(function(){ inquiryPrint($("#inquiryId").val())} );
	$("#btnSave").click(function(){ inquirySubmit()} );
	$("#btnReturn").click(function(){ onReturnClick()} );
});

</script>

<div id="inquirySearchPanelEmbed" class="row" style="margin-top:5px;">
</div>

<div class="row" style="margin-top:2px;">
	<input id="inquiryId" type="hidden" value="${entity.id }">
	<input id="inquiryUserId" type="hidden" value="${entity.userId }">
	<input id="inquiryUserName" type="hidden" value="${entity.userName }">
	<input id="inquiryCompanyName" type="hidden" value="${entity.companyName }">
	<input id="inquiryReplyUserName" type="hidden" value="${entity.replyUserName }">
	<input id="inquiryCreateTime" type="hidden" value="<fmt:formatDate value='${entity.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
	<input id="inquiryNote" type="hidden" value="${entity.note }">
	
	<input id="status" type="hidden" value="${entity.status }">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="btnGroup2" class="center">
 	<shiro:hasPermission name="inquiry:ReplyButton">
		<button id="btnPrint" type="button"  class="btn btn-primary btn-sm">
			<i class="fa fa-print"></i>&nbsp;打印
		</button>
		<button id="btnSave" type="button"  class="btn btn-success btn-sm">
			<i class="fa fa-save"></i>&nbsp;保存
		</button>
	</shiro:hasPermission>
		<button id="btnReturn" type="button" class="btn btn-info btn-sm">
			<i class="fa fa-reply"></i>&nbsp;返回
		</button>
	</div>
</div>
