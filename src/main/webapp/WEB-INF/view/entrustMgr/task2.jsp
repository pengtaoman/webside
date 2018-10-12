<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<head>
	<meta charset="utf-8" />
    <link href="${pageContext.request.contextPath }/resources/js/bwizard/css/bootstrap.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath }/resources/js/bwizard/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath }/resources/js/bwizard/css/bwizard.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bwizard/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bwizard/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bwizard/js/bwizard.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/task.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/facilityGrid.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/taskDetailGrid.js"></script>
</head>
<script type="text/javascript">


</script>
<body>

	<div class="container">
		<h2>请完成任务单流程</h2>
		<div id="wizard">
			<ol>
				<li>任务单基本信息</li>
				<li>选定待检测仪器</li>
				<li>发布任务</li>
			</ol>
			<div name="step1" class="row" style="margin-top: 5px;">
				<div class="left" style="margin-left: 50px;">
					<button id="btnAdd" type="button"
						onclick="entrust.addTask('/entrust/addTask.html');"
						class="btn btn-success btn-sm">
						<i class="glyphicon glyphicon-floppy-save"></i>&nbsp; 保存任务单
					</button>
				</div>
				<div class="col-xs-12">
					<form id="taskForm" name="taskForm" class="form-horizontal"	role="form" method="post">
						<c:if test="${!empty taskEntity}">
							<input type="hidden" name="id" id="id" value="${taskEntity.id }">
							<input type="hidden" name="cidInit" id="cidInit"
								value="${taskEntity.cid }">
							<input type="hidden" name="ifEnforceInit" id="ifEnforceInit"
								value="${taskEntity.ifEnforce }">
							<input type="hidden" name="noteInit" id="noteInit"
								value="${taskEntity.note }">
						</c:if>
						<div class="form-group">
							<label class="control-label col-sm-1 no-padding-right" for="cid">企业</label>
							<div class="col-sm-10">
								<div class="clearfix">
									<select class="form-control" id="cid" name="cid"
										style="width: 100%">
										<option value=""></option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 no-padding-right"
								for="ifEnforce">是否强检</label>
							<div class="col-sm-10">
								<div class="clearfix">
									<select class="form-control" id="ifEnforce" name="ifEnforce"
										style="width: 100%">
										<option value="1" selected="true">强检</option>
										<option value="0">非强检</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 no-padding-right" for="note">备注</label>
							<div class="col-sm-10">
								<div class="clearfix">
									<textarea class="form-control" name="note" id="note"
										value="${taskEntity.note }" placeholder="备注..." />
								</div>
							</div>
						</div>
						
					</form>
				</div>
			</div>
			<div name="step2">
				<div class="left">
					<button id="btnAdd" type="button"
						onclick="javascript:$('#facilityForm').submit();"
						class="btn btn-success btn-sm">
						<i class="glyphicon glyphicon-log-out"></i>&nbsp; 添加清单
					</button>
				</div>
				<div class="row" style="margin-top: 5px;">
					<div class="col-xs-12 widget-container-col ui-sortable"
						style="min-height: 127px;">
						<!-- #section:custom/widget-box.options.transparent -->
						<div class="widget-box transparent ui-sortable-handle"
							style="opacity: 1; z-index: 0;">
							<div class="widget-body" style="display: block;">
								<div
									class="widget-main padding-6 no-padding-left no-padding-right">
									<input id="pageNum" type="hidden" value="${page.pageNum }">
									<input id="pageSize" type="hidden" value="${page.pageSize }">
									<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
									<input id="orderByType" type="hidden" value="${page.orderByType }">
									<div id="facilityContainer" class="dlshouwen-grid-container"></div>
									<div id="facilityToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div name="step3">
				<div class="left">
					<button id="btnAdd" type="button"
						onclick="javascript:$('#facilityForm').submit();"
						class="btn btn-success btn-sm">
						<i class="glyphicon glyphicon-log-out"></i>&nbsp; 任务发布
					</button>
				</div>
				<div class="row" style="margin-top: 5px;">
					<div class="col-xs-12 widget-container-col ui-sortable"
						style="min-height: 127px;">
						<!-- #section:custom/widget-box.options.transparent -->
						<div class="widget-box transparent ui-sortable-handle"
							style="opacity: 1; z-index: 0;">
							<div class="widget-body" style="display: block;">
								<div class="widget-main padding-6 no-padding-left no-padding-right">
									<input name="pageNum" type="hidden" value="1">
									<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
									<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
		var zard = $("#wizard").bwizard();
		$(document).ready(function() {
			entrust.initCid();
			entrust.initIfEnfore();
		});
	</script>
</body>
</html>