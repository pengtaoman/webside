<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/search.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/inquirylist.js"></script>

<div class="page-header">
	<!-- 按钮权限需要根据需求添加控制 -->
	<shiro:hasPermission name="inquiry:ModifyButton">
	<button id="btnAdd" type="button" onclick="webside.common.addModel('/inquiry/addUI.html')" class="btn btn-primary btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;添加
	</button>
	<button id="btnEdit" type="button" onclick="webside.common.editModel('/inquiry/editUI.html')"	class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>
	</shiro:hasPermission>
	
	<shiro:hasPermission name="inquiry:ModifyButton">
	<button id="btnDel" type="button" onclick="webside.common.delBatchModel('/inquiry/deleteBatch.html', customSearch)" class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除
	</button>
	</shiro:hasPermission>
	
<%-- 	<shiro:hasPermission name="inquiry:ReplyButton"> --%>
<!-- 	<button id="btnReply" type="button" onclick="webside.common.editModel('/inquiry/replyUI.html')" class="btn btn-info btn-sm"> -->
<!-- 		<i class="fa fa-pencil-square-o"></i>&nbsp;报价 -->
<!-- 	</button> -->
<%-- 	</shiro:hasPermission> --%>
</div>

<div id="searchPanelArea" class="widget-header">
	<div id="searchPanel1" class="col-xs-12">	
	</div>
</div>

<div class="row" style="margin-top:5px;">
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


