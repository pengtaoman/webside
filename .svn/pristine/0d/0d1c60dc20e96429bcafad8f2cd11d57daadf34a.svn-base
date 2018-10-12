<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link rel="stylesheet"	href="${ctx }/resources/js/select2/select2.min.css" />

<script type="text/javascript"	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.iframe-transport.js"></script>

<script type="text/javascript">
	$(function() {
		init();
		$("#form1").validate({
		    rules: {
			    userName: "required",
			    companyName: "required"			   
		    },
		    messages: {
		    	companyName: "请输入企业名称",
		    	userName: "请输入企业联系人"
		    }
		});
	});
	
	function init() {
		var str1 = '${selectList1 }';
		var selectList1 = eval('(' + str1 + ')');
		$("#userId").select2({
			  data: selectList1,
			  placeholder:'请选择',
			  allowClear:false
			});	
		var str2 = '${selectList2 }';
		var selectList2 = eval('(' + str2 + ')');
		$("#companyId").select2({
			  data: selectList2,
			  placeholder:'请选择',
			  allowClear:false
			});	
		var validData = [{id : "2", text: "全部权限"}];
		$("#authCode").select2({
			  data: validData,
			  placeholder:'请选择',
			  //allowClear:true
			});
		$("#authCode").val("2").trigger("change");
		
		if ("${entity }" != undefined && "${entity.id }" != "") {
			$("#authId").val("${entity.id }");
			$("#note").val("${entity.note }");
			
			$("#companyId").val("${entity.companyId }").trigger("change");
			$("#userId").val("${entity.userId }").trigger("change");
			$("#authCode").val("${entity.authCode }").trigger("change");
		} 
				
		$("#btnCancel").bind("click", onCancelClick);
		$("#btnSave").bind("click", onSaveClick);
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
	
	function onSaveClick() {
		
		validInput();
		
		webside.common.commit("form1", "/companyauth/update.html", "/companyauth/listUI.html");
	}
	
	function onCancelClick() {
		
		webside.common.loadPage('/companyauth/listUI.html');
	}
</script>
<div class="page-header">
	<h1>	
		授权信息
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="authId">
		  
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="userId">平台用户</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="userId" name="userId"></select>							
					</div>
				</div>
			</div>
			 <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="companyId">公司名</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="companyId" name="companyId"></select>							
					</div>
				</div>
			</div>
		   <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="authCode">授权选项</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<select class="form-control" id="authCode" name="authCode"></select>							
					</div>
				</div>
			</div>
			 <div class="form-group">
			      <label class="control-label col-sm-1 no-padding-right" for="note">备注</label>
			      <div class="col-sm-10">
				      <div class="clearfix">
				         <input class="form-control" name="note" id="note" type="text"/>
				      </div>
			      </div>
		   </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>

<div class="center">
	<button id="btnSave" type="button" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;保存
	</button>
	<button id="btnCancel" type="button" class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>