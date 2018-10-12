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
			},
			messages : {
				name : "请输入",				
			}
		});
	});

	function domInitValue() {
		$("#title").val("${entity.title }");
		$("#content").val("${entity.content }");
		
		var str1 = '${selectList }';
		var selectList1 = eval('(' + str1 + ')');
		$("#selectList").select2({
			  data: selectList1,
			  placeholder:'请选择',
			  allowClear:false
			});	
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
		$("#btnSave").bind("click", onSaveClick);
	}

	function onSaveClick() {

		validInput();
		//得到value
		var data = {
				owner: $("#selectList").val(),
				title: $("#title").val(),
				content: $("#content").val()
		};
		
  	  webside.getData("/sms/insert.html", data, 
  			  function(result) {
  				  if(result.success) {
  					 webside.common.loadPage("/sms/listUI.html");
  				  }else {
  					layer.msg(result.message, {
                        icon : 3
                    });
  				  } 
  			  }, 
  			  function(result) {    				  
  		 		 });
		
	}

	function onCancelClick() {

		webside.common.loadPage('/sms/listUI.html');
	}

</script>
<div class="page-header">
	<h1>发布消息</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="id">
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="title">发送给</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<select class="form-control" id="selectList" name="onwer"></select>
					</div>
				</div>
			</div>
			
			<div class="form-group">     
			<label class="control-label col-sm-1 no-padding-right" for="title">标题</label>
				<div class="col-sm-10">
					<div class="clearfix">
			 			<input class="form-control" id="title" name="title"  type="text"/>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="content">内容</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<textarea class="form-control" id="content" rows="4"
						 	name="content" maxlength="100" placeholder="请输入">
						</textarea>
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>

<div id="btnGroup2" class="center">
<!-- 	<shiro:hasPermission name="company:modify"> -->
		<button id="btnSave" type="button" class="btn btn-success btn-sm">
			<i class="fa fa-user-plus"></i>&nbsp;发布
		</button>
		<button id="btnCancel" type="button" class="btn btn-info btn-sm">
			<i class="fa fa-undo"></i>&nbsp;返回
		</button>
<!--	</shiro:hasPermission> -->
</div>