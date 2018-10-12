<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath }" />

<link rel="stylesheet"	href="${ctx }/resources/js/webuploader/css/webuploader.css" />
<script type="text/javascript" src="${ctx }/resources/js/webuploader/js/webuploader.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/customer/common/filehelper.js"></script>

<script type="text/javascript">
	$(function() {
		FileHelper.initEntrustforceTemplate($("#uploadDiv"), afterUploadInquiry);
			var data = {key : 'EntrustforceTemplate'};
	  	  	webside.ajaxGet("/property/getProperty.html", data, 
	  			  function(result) {
	  	  			if(result.success) {
		  	  			$("#entrustPrintResult").html(result.data.content);
	  	  				$("#entrustPrintResult").find('div').each(function(){
		  	  			$(this).attr('style','width:95%;');
			  	  		});
			  	  		$("#entrustPrintResult").find('img').each(function(){
			  	  			$(this).attr('style','width:95%;');
			  	  		});
			  	  		$("#entrustPrintResult").find('span').each(function(){
			  	  			$(this).attr('style','width:95%;');
			  	  		});
	  	  			}
	  			  }, 
	  			  function(result) {
	  				  layer.msg("出错，请重试或者联系管理员"); 
	  		 });
	});

	function afterUploadInquiry(data) {
		$("#entrustPrintResult").html(data);
	}
	
	function onCancelClick() {
		webside.common.loadPage('/property/listUI.html');
	}
</script>
<div class="page-header">
	<h1>委托单(强检)模板上传</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<input type="hidden" id="editTag" value="${editTag }">
	<div class="col-xs-12">
		<form id="form1" class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="id">委托单(强检)模板</label>
				<div class="col-sm-2">
					<div id="uploadDiv" class="clearfix">
					</div>
				</div>
			</div>
		</form>
		<div class="hr hr-dotted"></div>
		<div id="entrustPrintResult" class="clearfix">
		</div>			
	</div>
</div>
