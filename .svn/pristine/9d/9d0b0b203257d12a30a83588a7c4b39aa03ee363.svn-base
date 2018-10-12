<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<meta charset="utf-8" />
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	
    <link href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
    
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/multiselect/css/style.css" />
    <script src="${pageContext.request.contextPath }/resources/js/multiselect/js/multiselect.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/resources/js/customer/entrustMgr/entrust.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrustMgr/search.js"></script>
</head>

<body>
  	<input id="taskId" type="hidden" value="${taskId }">
  	<input id="cid" type="hidden" value="${cid }">
  	<input id="entrustId" type="hidden" value="${entrustId }">
  	<input id="ifEnforce" type="hidden" value="${ifEnforce }">
  	<input id="entrustName" type="hidden" value="${entrustName }">
  	<input id="companyName" type="hidden" value="${companyName }">

	<span style="margin-top: 10px;!important" class="label label-sm label-success arrowed arrowed-in"  id="info" >公司名称：${companyName }&nbsp; &nbsp; &nbsp;当前展示委托单名称：${entrustName }&nbsp; &nbsp; &nbsp;编号： ${entrustId }</span>
	
	<div class="row" style="margin-top: 5px;">
		<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
			<!-- #section:custom/widget-box.options.transparent -->
			<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
				<div class="widget-body" style="display: block;">
					<div class="widget-main padding-6 no-padding-left no-padding-right">
						<input name="pageNum" type="hidden" value="1">
						<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
						<!-- <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div> -->
					</div>
				</div>
			</div>
		</div>
	</div>  
	
	<div class="row" style="margin-top: 5px;">
		<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
			<!-- #section:custom/widget-box.options.transparent -->
			<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
				<div class="widget-body" style="display: block;">
					<div class="widget-main padding-0 no-padding-left no-padding-right">
						<div id="searchPanelArea" class="widget-header">
							<div id="searchPanel1" class="col-xs-12" >	
							</div>
						</div>
						<div id="facilityContainer" class="dlshouwen-grid-container"></div>
						<div style="float:left">
							<select class="form-control" id="testResultSelect" style="margin-top: -1px; height:30px;width:100px">
								<option value="0">未检测</option>
								<option value="1">合格</option>
								<option value="2">不合格</option>
							</select>
						</div>
						<div style="float:left">
							<button id="btnAdd" type="button" style="margin-left: 3px;margin-top: -1px;!important; height:30px;width:120px"
								onclick="saveTestResult();"
								class="btn btn-primary btn-sm">
								<i class="glyphicon glyphicon-ok-sign"></i>&nbsp; 更新检测结果
						    </button>
						    <button id="btnAdd" type="button" style="margin-left: 1px;margin-top: -1px;margin-right: 3px;!important; height:30px;width:120px"
								onclick="entrustPrint();"
								class="btn btn-primary btn-sm">
								<i class="glyphicon glyphicon-print"></i>&nbsp; 打印委托单
						    </button>
						</div>
						<div id="facilityToolBarContainer" style="margin-left: 3px;!important" class="dlshouwen-grid-toolbar-container"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			SearchPanel.SearchPanelWithLike($("#searchPanel1"),
					dtGridColumns, true, grid);
		});
	</script>

</body>
</html>