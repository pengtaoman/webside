<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link rel="stylesheet"	href="${ctx }/resources/js/select2/select2.min.css" />

<script type="text/javascript"	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/webuploader/css/webuploader.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/webuploader/js/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/filehelper.js"></script>

<script type="text/javascript">
	$(function() {
		init();
		$("#form1").validate({
			errorElement : 'div',
            errorClass : 'help-block',
            focusInvalid : false,
            ignore : "",
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
			   regionCode: {
				    required: false,
				    digits:true,
				    minlength: 6,
				    maxlength: 6
			   },    
		    },
		    messages: {
		    	companyName: "请输入企业名称",
		    	contactPerson: "请输入企业联系人",
		    	address: "请输入企业详细地址",
		    	cert: "请上传企业信息",
		    	mobile: {
		          required: "请输入手机号码",
		          digits: "请输入正确手机号码",
		          minlength: "请输入正确手机号码",
		          maxlength: "请输入正确手机号码",
		        },
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
                webside.common.commit("form1", "/company/info.html", "/company/infoUI.html")
            }
		});
	});
		
		function init() {
		if ("${entity }" != undefined && "${entity.name}" != "") {
			$("#companyId").val("${entity.id }");
			$("#companyName").val("${entity.name }");
			
			var str = $("#companyName").val();
			if(str != undefined && str != "") {
				$("#header_companyname").html(str + "管理平台");
			}
			
			if ("${entity.status }" == "2") {
				$("#companyStatus").val("冻结中");
			} else if ("${entity.status }" == "1") {
				$("#companyStatus").val("认证通过");
			} else {
				$("#companyStatus").val("审核中");
			}
			
			$("#contactPerson").val("${entity.contactPerson }");
			$("#mobile").val("${entity.mobile }");
			$("#fax").val("${entity.fax }");
			$("#telephone").val("${entity.telephone }");
			$("#address").val("${entity.address }");
			$("#regionCode").val("${entity.regionCode }");
			//$("#createTime").val("${entity.createTime }"); //TODO 格式化

			$("#remark").val("${entity.remark }");
			
			$("#cert").val("${entity.cert }");
			
			if("${entity.cert }" != "") {
				var url = "${ctx }/file/download2.html?name=${entity.cert }";
				$("#certDiv").html('<img id="certImg" src="' + url +'" height="100" >');
			}			
			
			$("#certImg").bind("click", function(){showBigImage(url)});
			
			$("#btnGroup2").attr("style", "display:none");
			$("#btnGroup1").attr("style", "display:block");
		} else {
			//$("#companyId").val("0");
			$("#companyName").removeAttr("readOnly");
			$("#companyStatus").val("0");
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
// 			$("#remark").removeAttr("readOnly");
			
			$("#certDiv").attr("style", "display:none");
			$("#uploadDiv").attr("style", "display:block");	
			
			FileHelper.initImg($("#uploadDiv"), null, 100, 80, function(filename){
				$("#cert").val(filename);
			});
			
			$("#btnGroup1").attr("style", "display:none");
			$("#btnGroup2").attr("style", "display:block");
		}
		
		$("#btnModify").bind("click", onModifyClick);
		$("#btnCancel").bind("click", onCancelClick);
	}
	
	function onCancelClick() {
		
		webside.common.loadPage('/company/infoUI.html');
	}
	
	function onModifyClick() {
		if ("${entity.status }" == "0") {
			$("#companyName").removeAttr("readOnly");
			$("#certDiv").attr("style", "display:none");	
			$("#uploadDiv").attr("style", "display:block");
		} else if ("${entity.status }" == "1") {
			$("#companyStatus").val("认证通过");
		} else {
			$("#companyName").removeAttr("readOnly");
			$("#companyStatus").val("冻结中");
		}
		
		// 修改时，初始化为原图	
		var certImg = $("#cert").val();
		FileHelper.initImg($("#uploadDiv"), certImg, 100, 80, function(filename){
				$("#cert").val(filename);
			});
		$("#certDiv").attr("style", "display:none");
		$("#uploadDiv").attr("style", "display:block");	
		
		$("#contactPerson").removeAttr("readOnly");
		$("#mobile").removeAttr("readOnly");
		$("#fax").removeAttr("readOnly");
		$("#telephone").removeAttr("readOnly");
		$("#contactPerson").removeAttr("readOnly");
		$("#address").removeAttr("readOnly");
		$("#regionCode").removeAttr("readOnly");
// 		$("#remark").removeAttr("readOnly");
		
		$("#btnGroup1").attr("style", "display:none");
		$("#btnGroup2").attr("style", "display:block");		
	}
	
	function showBigImage(imgsrc) {	
		layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 0,
			  area: ['800px', '600px'],
			  skin: 'layui-layer-nobg', //没有背景色
			  anim: -1,
			  shadeClose: true,
			  content: '<img src="'+imgsrc+'" height="600"  width="800">'
			});
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
			<input type="hidden" name="cert" id="cert">
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
		      <label class="control-label col-sm-1 no-padding-right" for="remark">审核结果</label>
		      <div class="col-sm-10">
		      <div class="clearfix">
		         <input readonly class="form-control" name="remark" id="remark" type="text"/>
		      </div>
		      </div>
		   </div>
		   
		   <div class="form-group">
		      <label class="control-label col-sm-1 no-padding-right" for="cert">营业执照</label>
		      <div class="col-sm-10">
			      <div id="certDiv" class="clearfix">
			      </div>
			      <div id="uploadDiv" style="display:none">
<!-- 			      	<input id="certupload" type="file" name="file"> -->
<!-- 				    <div id="progress"> -->
<!-- 				        <div class="bar" style="width: 0%;"></div> -->
<!-- 				    </div>      -->
		      	</div>	
		      </div>
		   </div>
		   
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div id="btnGroup1" class="center">
	<shiro:hasPermission name="company:modify">
			<button id="btnModify" type="button" class="btn btn-success btn-sm">
			  	<i class="fa fa-user-plus"></i>&nbsp;修改
			</button>
		</shiro:hasPermission>
</div>

<div id="btnGroup2" style="display:none" class="center">
	<shiro:hasPermission name="company:modify">
			<button id="btnSave" type="button" onclick="javascript:$('#form1').submit();" class="btn btn-success btn-sm">
			  	<i class="fa fa-user-plus"></i>&nbsp;保存
			</button>
			<button id="btnCancel" type="button" class="btn btn-info btn-sm">
			  	<i class="fa fa-undo"></i>&nbsp;返回
			</button>
	</shiro:hasPermission>
</div>