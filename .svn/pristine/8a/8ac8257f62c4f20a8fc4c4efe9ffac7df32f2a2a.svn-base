<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link rel="stylesheet"	href="${ctx }/resources/css/jquery.fileupload.css" />
<link rel="stylesheet"	href="${ctx }/resources/js/select2/select2.min.css" />

<script type="text/javascript"	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript"	src="${ctx }/resources/js/plugin/jquery.ui.widget.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/plugin/jquery.iframe-transport.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/plugin/jquery.fileupload.js"></script>

<script type="text/javascript">
	$(function() {
		init();
		$("#form1").validate({
			errorElement : 'div',
            errorClass : 'help-block',
            focusInvalid : false,
            ignore : "",
			rules : {
				name : "required",
				field : "required",
				model : "required",
				accuracy : "required",
				testRange : "required",
				feeUnit : "required",
				feePrice : "required"
			},
			messages : {
				name : "请输入",
				field : "请输入",
				model : "请输入",
				accuracy : "请输入",
				testRange : "请输入",
				feeUnit : "请输入",
				feePrice : "请输入"
			},
		    highlight : function(e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success : function(e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement : function(error, element) {
                if (element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
                    var controls = element.closest('div[class*="col-"]');
                    if (controls.find(':checkbox,:radio').length > 1)
                        controls.append(error);
                    else
                        error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                } else if (element.is('.select2')) {
                    error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                } else if (element.is('.chosen-select')) {
                    error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                } else
                    error.insertAfter(element.parent());
            },
            submitHandler : function(form) {
            	webside.common.commit("form1", "/fee/update.html", "/fee/listUI.html");
            }
		});
	});

	function domInitValue() {
		$("#id").val("${entity.id }");
		$("#code").val("${entity.code }");
		$("#idSub").val("${entity.idSub }");
		$("#idProvince").val("${entity.idProvince }");
		$("#idCountry").val("${entity.idCountry }");
		$("#name").val("${entity.name }");
		$("#field").val("${entity.field }");
		$("#model").val("${entity.model }");
		$("#accuracy").val("${entity.accuracy }");
		$("#testRange").val("${entity.testRange }");
		$("#feeUnit").val("${entity.feeUnit }");
		$("#feePrice").val("${entity.feePrice }");
		$("#alias").val("${entity.alias }");
		$("#status").val("${entity.status }");
		$("#note").val("${entity.note }");
		
		var validData = [{id : "1", text: "有效"},{id : "0", text: "无效"}];
		$("#statusValid").select2({
			  data: validData,
			  placeholder:'请选择',
			  //allowClear:true
			});
		$("#statusValid").val("${entity.statusValid }").trigger("change");
	}
	
	function init() {
		domInitValue();
		$("#btnCancel").bind("click", onCancelClick);
	}

	function onCancelClick() {
		webside.common.loadPage('/fee/listUI.html');
	}

	$(window).load(function() {
		
	});
</script>
<div class="page-header">
	<h1>资费标准</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="id">
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="code">序号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="code" name="code"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="idSub">院标准ID</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="idSub" name="idSub"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="idProvince">省标准ID</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="idProvince"
							name="idProvince" type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="idCountry">国家标准ID</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="idCountry"
							name="idCountry" type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="name">计量器具</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="name" name="name"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="field">所属专业</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="field" name="field"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="model">规格型号</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="model" name="model"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="accuracy">准确度等级</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="accuracy" name="accuracy"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="testRange">测量范围</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="testRange"
							name="testRange" type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="feeUnit">收费单位</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="feeUnit" name="feeUnit"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="feePrice">收费标准</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="feePrice" name="feePrice"
							type="number" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="alias">别名</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="alias" name="alias"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="status">状态</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="status" name="status"
							type="text" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="statusValid">是否有效</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="statusValid" name="statusValid"></select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="note">备注</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="note" name="note"
							type="text" />
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>

<div id="btnGroup2" class="center">
<!-- 	<shiro:hasPermission name="company:modify"> -->
		<button id="btnSave" type="button" onclick="javascript:$('#form1').submit();" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>&nbsp;保存
		</button>
		<button id="btnCancel" type="button" class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
		</button>
<!--	</shiro:hasPermission> -->
</div>