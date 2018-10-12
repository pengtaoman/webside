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
			    contactPerson: "required",
			    address: "required",
				telephone: {
			        required: true,
			        minlength: 9,
			        maxlength: 13
			      },
				   regionCode: {
					    required: false,
					    digits:true,
					    minlength: 6,
					    maxlength: 6
				   }, 
			      mobile: {
				        required: true,
				        digits:true,
				        minlength: 10,
				        maxlength: 12
				 },
				 bank: {
					    required: true
				   },
				   accountName: {
						    required: true
					   }, 
				 accountCode: {
					    required: true,
					    digits:true,
					    minlength: 12,
					    maxlength: 32
				   }
			},
			messages : {
				name : "请输入",				
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
            	webside.common.commit("form1", "/organ/update.html", "/organ/listUI.html");
            }
		});
	});

	function domInitValue() {
		$("#name").val("${entity.name }");
		$("#telephone").val("${entity.telephone }");
		$("#contactPerson").val("${entity.contactPerson }");
		$("#mobile").val("${entity.mobile }");
		$("#fax").val("${entity.fax }");
		$("#address").val("${entity.address }");
		$("#regionCode").val("${entity.regionCode }");
		$("#bank").val("${entity.bank }");
		$("#accountName").val("${entity.accountName }");
		$("#accountCode").val("${entity.accountCode }");
		$("#note").val("${entity.note }");
		
		var validData = [{id : "1", text: "有效"},{id : "0", text: "无效"}];
		$("#statusValid").select2({
			  data: validData,
			  placeholder:'请选择',
			  //allowClear:true
			});
		$("#statusValid").val("${entity.status }").trigger("change");
	}
	
	
	function validInput() {
		$("#form1").find("input").each(function(index, inputDom){
    		if($(inputDom).val() === ""){
    			$(inputDom).addClass("error");
                $(inputDom).focus();
                return false;
    		}
    	});
		
		return true;
	}
	
	function init() {
		domInitValue();

		$("#btnCancel").bind("click", onCancelClick);
	}

	function onCancelClick() {
		webside.common.loadPage('/organ/listUI.html');
	}

</script>
<div class="page-header">
	<h1>检测机构</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="id" value="${entity.id }">
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="name">名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="name" name="name"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="telephone">座机</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="telephone" name="telephone"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="contactPerson">联系人</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="contactPerson" name="contactPerson"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="mobile">手机</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="mobile" name="mobile"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="fax">传真</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="fax" name="fax"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="address">住址</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="address" name="address"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="regionCode">邮编</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="regionCode" name="regionCode"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="bank">开户银行</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="bank" name="bank"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="accountName">账户名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="accountName" name="accountName"  type="text"/>
					</div>
				</div>
			</div>
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="accountCode">银行账号</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="accountCode" name="accountCode"  type="text"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="statusValid">是否有效</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="statusValid" name="status"></select>
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
	<shiro:hasPermission name="organ:Modify">
		<button id="btnSave" type="button" onclick="javascript:$('#form1').submit();" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>&nbsp;保存
		</button>
		<button id="btnCancel" type="button" class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
		</button>
	</shiro:hasPermission>
</div>