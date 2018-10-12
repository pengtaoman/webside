<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
	<meta charset="utf-8" />
	
    <link href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/task.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

</head>
<div class="tabbable">
	<ul class="nav nav-tabs" id="myTab">
		<li class="active">
			<a data-toggle="tab" href="#step1">
				任务单基本信息
			</a>
		</li>
		<li>
			<a data-toggle="tab" href="#step2">
				仪器筛选
			</a>
		</li>
		<li>
			<a data-toggle="tab" href="#step3">
				任务待检仪器列表 <span id="facilityCountSpan"
				class="badge badge-info">0</span>
			</a>
		</li>
	</ul>

	<div class="tab-content">
		<div id="step1" class="tab-pane fade in active">
			<div class="left" style="margin-left: 30px;">
				<button id="btnAdd" type="button"
					onclick="entrust.addTask('/entrust/addTask.html',facilitySearch);"
					class="btn btn-primary btn-sm">
					<i class="fa fa-save"></i>&nbsp; 保存任务单
				</button>
			</div>
			<br/>
				<form id="taskForm" name="taskForm" class="form-horizontal"	role="form" method="post">
					<c:if test="${!empty taskEntity}">
						<input type="hidden" name="id" id="id" value="${taskEntity.id }">
						<input type="hidden" name="cidInit" id="cidInit" value="${taskEntity.cid }">
						<input type="hidden" name="cidNameInit" id="cidNameInit" value="${cidNameInit }">
						<input type="hidden" name="ifEnforceInit" id="ifEnforceInit" value="${taskEntity.ifEnforce }">
						<input type="hidden" name="noteInit" id="noteInit" value="${taskEntity.note }">
						<input type="hidden" name="strategyInit" id="strategyInit" value="${taskEntity.strategy }">
						<input type="hidden" name="strategyNameInit" id="strategyNameInit" value="${strategyNameInit }">
						<input type="hidden" name="inquiryIdInit" id="inquiryIdInit" value="${taskEntity.inquiryId }">
						<input type="hidden" name="inquiryNoteInit" id="inquiryNoteInit" value="${inquiryNoteInit }">
					</c:if>
					<div class="form-group">
						<label class="control-label col-sm-1 no-padding-right" for="taskName">任务名称</label>
						<div class="col-sm-10">
						<div class="clearfix">
							<input class="form-control" style="height: 34px;" name="taskName" id="taskName" type="text"
								value="${taskEntity.taskName }" placeholder="任务名称..." />
						</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-1 no-padding-right" for="note">备注</label>	
						<div class="col-sm-10">
							<div class="clearfix">
								<textarea  id="note" name="note" class="form-control" rows="2">${taskEntity.note }</textarea>
							</div>
						</div>
					</div>
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
					<div class="form-group" style="display:none">
						<label class="control-label col-sm-1 no-padding-right" for="ifEnforce">是否强检</label>
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
						<label class="control-label col-sm-1 no-padding-right" for="strategy">筛选策略</label>
						<div class="col-sm-10">
							<div class="clearfix">
								<select class="form-control" id="strategy" name="strategy"
									style="width: 100%">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
					<div style="display:none" id="baojiadan" class="form-group">
						<label class="control-label col-sm-1 no-padding-right" for="inquiryId">报价单</label>
						<div class="col-sm-10">
							<div class="clearfix">
								<select class="form-control" id="inquiryId" name="inquiryId" 
									style="width: 100%">
									<option value=""></option>
								</select>
							</div>
						</div>
					</div>
				</form>
		</div>

		<div id="step2" class="tab-pane fade">
			<div class="left">
				<div class="controls controls-row">
					<select class="span2" onchange="entrust.fieldChange()" id="fieldSelect"><option value="all">全部</option></select>
					<select class="span1" style="width:80px" id="exprSelect"></select>
					<input id="searchText" class="span3" style="height:30px; type="text" placeholder="请输入">

					<input class="date-picker span2"  placeholder="请输入起始日期"  style="height:30px; display:none" id="idDateStart" name="idDateStart" data-date-format="dd-mm-yyyy" />

					<input class="date-picker span2"  placeholder="请输入截止日期"  style="height:30px; display:none" id="idDateEnd" name="idDateEnd" data-date-format="dd-mm-yyyy" />
			
							 
					<button id="btnSearch" type="button" 
						onclick="entrust.search();"
						class="btn btn-primary btn-sm span2.5">
						<i class="fa fa-search"></i>&nbsp; 搜索
					</button>

					<button id="btnAdd" type="button"
						onclick="entrust.addTaskDetail('/entrust/addTaskDetail.html',freshFacilityList);"
						class="btn btn-success btn-sm span2.5">
						<i class="fa fa-plus-square-o"></i>&nbsp; 添加清单
					</button>
			<!-- 		<button id="btnAdd" type="button"
						onclick="entrust.releaseTask('/entrust/setTask.html',closeTask);"
						class="btn btn-warning btn-sm span2.5">
						<i class="glyphicon glyphicon-log-out"></i>&nbsp; 完成
					</button> -->
			<!-- 		<button id="shouqi" type="button"
						onclick="entrust.searchDivHide();"
						class="btn btn-danger btn-sm span2.5">
						<i class="fa fa-angle-double-up"></i>&nbsp;筛选收起
					</button>
					<button id="zhankai" type="button"
						onclick="entrust.searchDivShow();" style="display:none"
						class="btn btn-success btn-sm span2.5">
						<i class="fa fa-angle-double-down"></i>&nbsp;筛选展开
					</button> -->
				</div>
			</div>
			<div class="row" id="searchDiv" style="margin-top: 0px;">
				<div class="col-xs-12 widget-container-col ui-sortable"
					style="min-height: 200px;">
					<!-- #section:custom/widget-box.options.transparent -->
					<div class="widget-box transparent ui-sortable-handle"
						style="opacity: 1; z-index: 0;">
						<div class="widget-body" style="display: block;">
							<div class="widget-main padding-6 no-padding-left no-padding-right">
								<input name="pageNum" type="hidden" value="1">
								<div id="facilityContainer" class="dlshouwen-grid-container"></div>
								<div id="facilityToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="step3" class="tab-pane fade">
			<div class="left">
				<div class="controls controls-row">
					<button id="btnAdd" type="button"
						onclick="entrust.releaseTask('/entrust/setTask.html',closeTask);"
						class="btn btn-primary btn-sm span2.5">
						<i class="glyphicon glyphicon-log-out"></i>&nbsp; 完成
					</button>
				</div>
			</div>
			<div class="row" id="selectedDiv" style="margin-top: 0px;">
				<div class="col-xs-12 widget-container-col ui-sortable"
					style="min-height: 200px;">
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

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/facilityGrid.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrust/taskDetailGrid.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		entrust.initCid();
		entrust.initIfEnfore();
		entrust.initStrategy();
		entrust.initInquiryId(); 
		var taskId = $("#id").val();
		if(taskId != ""){
			//facilitySearch();
		}
		
	});
</script>
