<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				id : "required",
				content : "required",
			},
			messages : {
				id : "请输入",
				content : "请输入",
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
            	if($("#editTag").val() == '1') {
            		webside.common.commit("form1", "/property/update.html", "/property/listUI.html");
            	} else {
            		webside.common.commit("form1", "/property/insert.html", "/property/listUI.html");
            	}
            }
		});
	});
	
	function init() {
		$("#btnCancel").bind("click", onCancelClick);
	}

	function onCancelClick() {
		webside.common.loadPage('/property/listUI.html');
	}

	$(window).load(function() {
		
	});
</script>
<div class="page-header">
	<h1>字典属性</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<input type="hidden" id="editTag" value="${editTag }">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="id">Key字符串</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="id" name="id" type="text" value="${entity.id }"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="note">备注</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input class="form-control" id="note" name="note" type="text" value="${entity.note }" />
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="note">内容</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<textarea class="form-control" id="content" name="content"  rows = 20 cols="65">${entity.content }</textarea>
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