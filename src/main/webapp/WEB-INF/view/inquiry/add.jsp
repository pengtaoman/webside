<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>


<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/webuploader/css/webuploader.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/webuploader/js/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/filehelper.js"></script>


<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/facilityUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/inquirySearch.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/business/inquiryadd.js"></script>
<script type="text/javascript">
$(function() {
	InquirySearchPanel.InitSearchPanel($("#inquirySearchPanelEmbed"), 0, addInquiryClick);
	FacilityUploadPanel.Init($("#inquiryUpload"), 10, addInquiryClick, function(data){},"${selectcid}");
	
	$("#btnSave").click(function(){ inquirySubmit()} );
	$("#btnReturn").click(function(){ onReturnClick()} );
	
	var note = "${entity.note }";
	if(note == null || note == "") {
		note = "新建询价单" + getNowFormatDate();
	} else {
		$('#myTab a[href="#inquiryTab"]').tab('show');
	}
	$("#inquiryNote").val(note);
	
	var status = $("#status").val();
	if(status == "") {
		$("#statusLabel").html('<span><h4>状态：&nbsp;&nbsp;新建</h4></span>');
	} else if(status == "0"){
		$("#statusLabel").html('<span><h4>状态：&nbsp;&nbsp;已提交</h4></span>');
	} else {
		$("#statusLabel").html('<span><h4>状态：&nbsp;&nbsp;已报价</h4></span>');
	}
});

$(window).load(function () {
	var inquiryId = $("#inquiryId").val();
	if(inquiryId != "") {
		$('#myTab a[href="#inquiryTab"]').tab('show');
	}});
	
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
</script>

<div class="tabbable">
	<ul class="nav nav-tabs" id="myTab">
		<li class="active"><a data-toggle="tab" href="#home"> <i
				class="green ace-icon fa fa-upload bigger-120"></i> 器具导入添加
		</a></li>
		
		<li ><a data-toggle="tab" href="#upload"> <i
				class="green ace-icon fa fa-search bigger-120"></i> 器具查询添加
		</a></li>

		<li><a data-toggle="tab" href="#inquiryTab"><i
				class="blue ace-icon fa fa-question bigger-120"></i> 询价单 <span id="facilityCountSpan"
				class="badge badge-info">0</span>
		</a></li>
	</ul>

	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<div id="inquiryUpload" class="clearfix" style="margin-top:1px;margin-bottom:1px;">
			</div>
		</div>
		<div id="upload" class="tab-pane fade">
			<div id="inquirySearchPanelEmbed" class="row" style="margin-top:1px;margin-bottom:1px;">
			</div>
		</div>

		<div id="inquiryTab" class="tab-pane fade">
			<div class="row" style="margin-top: 0px;">
				<div class="col-xs-12 widget-container-col ui-sortable">
					<div class="widget-header" style="height: 36px;">
						<label class="col-sm-1"
							style="height: 28px; width: 60px;important!"><h4>名称</h4></label>
						<input class="col-sm-3" onfocus="this.select();"
							onmouseup="this.select();" style="margin-top: 1px; height: 33px;"
							id="inquiryNote" value="${entity.note }">
						<div class="col-sm-1"></div>
						<label class="col-sm-2" id="statusLabel" style="height: 28px;"><h4>状态</h4></label>
						<label class="col-sm-2" id="totalFee" style="height: 28px;"></label>
						<input id="inquiryId" type="hidden" value="${entity.id }">
						<input id="status" type="hidden" value="${entity.status }">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 0px;">
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
								<input id="orderByColumn" type="hidden"
									value="${page.orderByColumn }"> <input id="orderByType"
									type="hidden" value="${page.orderByType }">
								<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
								<div id="dtGridToolBarContainer"
									class="dlshouwen-grid-toolbar-container"></div>
							</div>
						</div>
					</div>
				</div>

				<div id="btnGroup2" class="center">
					<shiro:hasPermission name="inquiry:ModifyButton">
						<button id="btnSave" type="button" class="btn btn-success btn-sm">
							<i class="fa fa-save"></i>&nbsp;保存
						</button>
					</shiro:hasPermission>
					<button id="btnReturn" type="button" class="btn btn-info btn-sm">
						<i class="fa fa-reply"></i>&nbsp;返回
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<HR style="margin-top: 0px;margin-bottom: 2px;border:1 dashed #rgba(0, 162, 202, 0.38)" width="100%" color=#e5ebef SIZE=1>
