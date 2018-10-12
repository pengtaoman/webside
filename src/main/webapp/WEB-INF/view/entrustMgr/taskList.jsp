<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/search.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrustMgr/taskList.js"></script>
<div class="page-header">
	<button id="btnAdd" type="button"
		onclick="backTask()"
		class="btn btn-primary btn-sm">
		<i class="fa fa-undo"></i>&nbsp;任务回退
	</button>
	<button id="btnEdit" type="button"
		onclick="editEntrust()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;委托单管理
	</button>
</div>
<div id="searchPanelArea" class="widget-header">
	<div id="searchPanel1" class="col-xs-12">	
	</div>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden"	value="${page.orderByColumn }">
					<input id="orderByType"	type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
</script>

