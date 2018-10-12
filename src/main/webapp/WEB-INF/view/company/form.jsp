<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link rel="stylesheet"	href="${ctx }/resources/css/jquery.fileupload.css" />
<link rel="stylesheet"	href="${ctx }/resources/js/select2/select2.min.css" />

<script type="text/javascript"	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.fileupload.js"></script>

<script type="text/javascript">
	var fileForm1='<input id="fileupload" type="file" name="file">'
    +'<div class="bar" style="width: 0%;"></div>'
	+'<img id="uploadimg" width="100px" height="50px"/>';
	  	
	$(function() {
		init();
		$("#form1").validate({
		    rules: {
			    companyName: "required",
			    contactPerson: "required",
			    address: "required",
			    mobile: {
			        required: true,
			        digits:true,
			        minlength: 10,
			        maxlength: 12
			      },
		    },
		    messages: {
		    	companyName: "请输入企业名称",
		    	contactPerson: "请输入企业联系人",
		    	address: "请输入企业详细地址",
		    	mobile: {
		          required: "请输入手机号码",
		          digits: "请输入正确手机号码",
		          minlength: "请输入正确手机号码",
		          maxlength: "请输入正确手机号码",
		        },
		    }
		});
	});
	
	function init() {
		if ("${entity }" != undefined && "${entity.name}" != "") {
			$("#companyId").val("${entity.id }");
			$("#companyName").val("${entity.name }");
			if ("${entity.status }" == "0") {
				$("#companyStatus").val("认证审核中");
			} else if ("${entity.status }" == "1") {
				$("#companyStatus").val("认证通过");
			} else {
				$("#companyStatus").val("冻结中");
			}
			
			$("#contactPerson").val("${entity.contactPerson }");
			$("#mobile").val("${entity.mobile }");
			$("#fax").val("${entity.fax }");
			$("#telephone").val("${entity.telephone }");
			$("#address").val("${entity.address }");
			$("#regionCode").val("${entity.regionCode }");
			//$("#createTime").val("${entity.createTime }"); //TODO 格式化
			
			$("#remark").val("${entity.remark }");
			$("#remarkDiv").html('<img id="remarkImg" src="${entity.remark }" height="100" >');
			$("#remarkImg").bind("click", function(){showBigImage("${entity.remark }")});
			
			$("#btnGroup2").attr("style", "display:none");
			$("#btnGroup1").attr("style", "display:block");
		} else {
			//$("#companyId").val("0");
			$("#companyName").removeAttr("readOnly");
			$("#contactPerson").removeAttr("readOnly");
			$("#mobile").removeAttr("readOnly");
			$("#fax").removeAttr("readOnly");
			$("#telephone").removeAttr("readOnly");
			$("#contactPerson").removeAttr("readOnly");
			$("#address").removeAttr("readOnly");
			$("#regionCode").removeAttr("readOnly");
			
			$("#createTime").remove();
			$("#label_createTime").remove();
			$("#companyStatus").remove();
			$("#lable_companyStatus").remove();
			
			$("#remark").val("");
			
			$("#remarkDiv").attr("style", "display:none");
			$("#uploadDiv").attr("style", "display:block");		
			$("#btnGroup1").attr("style", "display:none");
			$("#btnGroup2").attr("style", "display:block");
		}
		
		$('#fileupload').fileupload({
	        dataType: 'json',
	        url: "/file/upload1",//文件的后台接受地址
	        //设置进度条
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100);
	            $('#progress .bar').css(
	                'width',
	                progress + '%'
	            );
	        },
	        //上传完成之后的操作，显示在img里面
	        done: function (e, data){
	            $("#uploadimg").attr({src: data.result.fileurl});
	            $("#uploadimg").css({width: "200px", height: "100px"});
	        }
	    });
		
		
		//$("#btnModify").unbind("click", onModifyClick).bind("click", onModifyClick);
		$("#btnModify").bind("click", onModifyClick);
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
	
	function initFileUploader() {
		$('#idInputFileRemark').ace_file_input({
			no_file:'No File ...',
			btn_choose:'Choose',
			btn_change:'Change',
			droppable:false,
			onchange:null,
			thumbnail:false //| true | large
			//whitelist:'gif|png|jpg|jpeg'
			//blacklist:'exe|php'
			//onchange:''
			//
		});
	}
	
	function onSaveClick() {
		
		validInput();
		
		webside.common.commit("form1", "/company/info.html", "/company/infoUI.html");
	}
	
	function onCancelClick() {
		
		webside.common.loadPage('/company/infoUI.html');
	}
	
	function onModifyClick() {
		if ("${entity.status }" == "0") {
			$("#companyName").removeAttr("readOnly");
			$("#remarkDiv").attr("style", "display:none");	
			$("#uploadDiv").attr("style", "display:block");
		} else if ("${entity.status }" == "1") {
			$("#companyStatus").val("认证通过");
		} else {
			$("#companyStatus").val("冻结中");
		}
		
		//webside.common.loadPage('/company/editUI.html?id=${entity.id }');		
		
		$("#contactPerson").removeAttr("readOnly");
		$("#mobile").removeAttr("readOnly");
		$("#fax").removeAttr("readOnly");
		$("#telephone").removeAttr("readOnly");
		$("#contactPerson").removeAttr("readOnly");
		$("#address").removeAttr("readOnly");
		$("#regionCode").removeAttr("readOnly");
		
		$("#btnGroup1").attr("style", "display:none");
		$("#btnGroup2").attr("style", "display:block");		
	}
	
	function ChangeDateFormat(jsondate) {
	    jsondate = jsondate.replace("/Date(", "").replace(")/", "");
	    if (jsondate.indexOf("+") > 0) {
	        jsondate = jsondate.substring(0, jsondate.indexOf("+"));
	    }
	    else if (jsondate.indexOf("-") > 0) {
	        jsondate = jsondate.substring(0, jsondate.indexOf("-"));
	    }
	 
	    var date = new Date(parseInt(jsondate, 10));
	    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
	    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	 
	    alert(date);
	    alert(month);
	    alert(currentDate);
	    return date.getFullYear()
	        + "年"
	        + month
	        + "月"
	        + currentDate
	        + "日"
	        + " "
	        + date.getHours()
	        + ":"
	        + date.getMinutes();
	}
</script>
<div class="page-header">
	<h1>	
		企业信息
	</h1>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<input type="hidden" name="id" id="companyId">
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="companyName">企业名称</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" id="companyName" name="name" type="text"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label id="lable_companyStatus" class="control-label col-sm-1 no-padding-right" for="companyStatus">状态</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="status" id="companyStatus" type="text" />
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="contactPerson">联系人</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="contactPerson" id="contactPerson" type="text"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="mobile">手机</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="mobile" id="mobile" type="text" />
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="telephone">座机</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="telephone" id="telephone" type="text" />
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="fax">传真</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="fax" id="fax" type="text"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="address">地址</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="address" id="address" type="text" />
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="regionCode">邮编</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="regionCode" id="regionCode" type="text"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label id="label_createTime" class="control-label col-sm-1 no-padding-right" for="createTime">创建时间</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" id="createTime" type="text" 
		         value="<fmt:formatDate value='${entity.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
		      </div>
		      </div>
		   </div>
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="remark">营业执照</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
			      <div id="remarkDiv">
			      </div>			      	
		      	<div id="uploadDiv" style="display:none">
		      	<input type="hidden" name="remark" id="remark">
			      	<input id="fileupload" type="file" name="file"">
				    <div id="progress">
				        <div class="bar" style="width: 0%;"></div>
				    </div>     
		      	</div>		      	 
		      </div>
		      </div>
		   </div>
		   
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div id="btnGroup1" class="center">
	<shiro:hasPermission name="company:modify">
		<button id="btnModify" type="button"  class="btn btn-success btn-sm">
		  	<i class="fa fa-user-plus"></i>&nbsp;修改
		</button>
	</shiro:hasPermission>
</div>

<div id="btnGroup2" style="display:none" class="center">
	<shiro:hasPermission name="company:modify">
			<button id="btnSave" type="button"  class="btn btn-success btn-sm">
			  	<i class="fa fa-user-plus"></i>&nbsp;保存
			</button>
			<button id="btnCancel" type="button" class="btn btn-info btn-sm">
			  	<i class="fa fa-undo"></i>&nbsp;返回
			</button>
	</shiro:hasPermission>
</div>