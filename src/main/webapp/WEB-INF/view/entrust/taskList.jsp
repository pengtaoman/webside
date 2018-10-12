<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/taskList.js"></script>
<div class="page-header">
	<button id="btnAdd" type="button"
		onclick="addTask()"
		class="btn btn-primary btn-sm">
		<i class="fa fa-plus-square-o"></i>&nbsp;新建任务
	</button>
	<button id="btnEdit" type="button"
		onclick="editTask()"
		class="btn btn-danger btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑任务
	</button>
	<button id="btnDel" type="button"
		onclick="deleteTask()"
		class="btn btn-info btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除任务
	</button>
	<button id="btnEditEntrust" type="button"
		onclick="editEntrust()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑委托单
	</button>

</div>
<div class="input-group">
	<input id="searchKey" type="text" class="input form-control" placeholder="资源名称..."> 
	<span class="input-group-btn">
		<button id="btnSearch" class="btn btn-primary btn-sm" type="button">
			<i class="fa fa-search"></i> 搜索
		</button>
		<button id="btnReset" class="btn btn-primary btn-sm" type="button">
			<i class="fa fa-reset"></i> 重置
		</button>
	</span>
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

