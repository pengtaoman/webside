<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/feeSearch.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/search.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/feelist.js"></script>
<script type="text/javascript">
$(function() {
	FeeSearchPanel.InitSearchPanel($("#feeSearchPanelEmbed"), 5);
});
</script>

<div id="feeSearchPanelEmbed" class="row" style="margin-top:5px;">

</div>

<div id="searchPanelArea" class="widget-header">
	<div id="searchPanel1" class="col-xs-12">	
	</div>
	<div id="searchPanelAdv" class="col-xs-12" style="display: none;">	
		<div class="col-sm-1" style="margin-top: 5px;width: 80px;">
			<label >所属专业</label>
		</div>
		<div class="col-sm-1">
			<select class="form-control" id="selectField"></select>
		</div>
		<div class="col-sm-1" style="margin-top: 5px;margin-left: 5px;height:100%;width: 80px;">
			<label class="vertical-align:middle;display:inline-block;">型号</label>
		</div>
		<div class="col-sm-2">
			<select class="form-control" id="selectModel"></select>
		</div>	
		<div class="col-sm-1" style="margin-top: 5px;margin-left: 5px;height:100%;width: 80px;">
			<label >准确度</label>
		</div>
		<div class="col-sm-2">
			<select class="form-control" id="selectAccuracy"></select>
		</div>	
		<div class="col-sm-1" style="margin-top: 5px;margin-left: 5px;height:100%;width: 80px;">
			<label >测量范围</label>
		</div>
		<div class="col-sm-2">
			<select class="form-control" id="selectTestRange"></select>
		</div>	 
		<div class="col-sm-1">
			<span class="input-group-btn">
				<button id="btnSearchGo" class="btn btn-primary btn-sm" type="button"> 
					<i class="fa fa-search"></i>&nbsp;搜索
				</button>
				<button id="btnSearchReturn" style="margin-left:5px;" class="btn btn-warn btn-sm" type="button"> 
					<i class="fa fa-angle-double-left"></i>&nbsp;简单搜索
				</button>
			</span>
		</div>	  	
	</div>
</div>
<div class="row" style="margin-top:2px;">
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
</div>
