<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	<link rel="stylesheet"  href="${pageContext.request.contextPath }/resources/js/bootstrap-fileinput-master/css/fileinput.css"/>   
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-fileinput-master/js/fileinput.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-fileinput-master/js/locales/zh.js"></script>
 	
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/search.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/facility/list.js"></script>
 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" type="text/javascript"></script>
  <div class="page-header">
	<shiro:hasPermission name="facility:manager:addUI">
	<button id="btnAdd" type="button" onclick="webside.common.addModel('/facility/addUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-plus-square-o"></i>&nbsp;添加
	</button>
	<button id="btnAdd" type="button" onclick="webside.common.editModel('/facility/addCopyUI.html')" class="btn btn-success btn-sm">
	  	<i class="fa fa-copy"></i>&nbsp;复制
	</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="facility:editUI">
	<button id="btnEdit" type="button" onclick="webside.common.editModel('/facility/editUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-edit"></i>&nbsp;编辑
	</button>
	</shiro:hasPermission>
	<shiro:hasPermission name="facility:deleteBatch">
	<button id="btnDel" type="button" onclick="webside.form.facility.delModel('/facility/deleteBatch.html', customSearch)" class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除
	</button>
	</shiro:hasPermission>
<!-- 	<button type="button" data-toggle="modal" data-target="#exampleModal" class="btn btn-success btn-sm"> -->
<!-- 	 <i class="fa fa-trash-o"></i>&nbsp; 导入 -->
<!-- 	</button> -->
<!-- 	<button id="repair" type="button" onclick="webside.common.loadPage('/facility/uploadUI.html');" class="btn btn-success btn-sm">
	 <i class="fa fa-upload"></i>&nbsp; 导入
	</button> -->
	<button id="repair" type="button" onclick="webside.common.editModel('/facility/repairUI.html', customSearch)" class="btn btn-warning btn-sm">
	 <i class="fa fa-wrench"></i>&nbsp; 调修
	</button>

</div>

<div id="searchPanelArea" class="widget-header">
	<div id="searchPanel1" class="col-xs-12">	
	</div>
</div>
<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header"  style="display: none;">
				<h4 class="widget-title lighter">资源列表</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> 
						<i class="ace-icon fa fa-arrows-alt"></i>
					</a> 
					<a href="#" data-action="collapse" class="green"> 
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
					<input id="orderByType" type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="file-loading">
          <input id="input-b9" name="input-b9[]" type="file">
        </div>
        <div id="kartik-file-errors"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" title="Your custom upload logic">Save</button>
      </div>
    </div>
  </div>
</div>
<script>
$(document).on('ready', function() {
    $("#input-b9").fileinput({
        showPreview: false,
        showUpload: false,
        elErrorContainer: '#kartik-file-errors',
        allowedFileExtensions: ["jpg", "png", "gif"],
        uploadUrl: '/site/file-upload-single'
    });
});
</script>

