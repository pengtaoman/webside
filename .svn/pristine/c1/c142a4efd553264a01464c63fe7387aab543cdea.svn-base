<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
	<meta charset="utf-8" />
	<link href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/multiselect/css/style.css" />
    <script src="${pageContext.request.contextPath }/resources/js/multiselect/js/multiselect.min.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath }/resources/js/customer/entrust/entrust.js" type="text/javascript"></script>
</head>

<body>
  <input id="taskId" type="hidden" value="${taskId }">
  <input id="cid" type="hidden" value="${cid }">
	<div class="page-header">
		<button id="btnAdd" type="button"
			onclick="addEntrust()"
			class="btn btn-primary btn-sm">
			<i class="fa fa-plus-square-o"></i>&nbsp; 添加委托单
		</button>
		<button  id="btnAdd" type="button"
			onclick="showEntrust();"
			class="btn btn-danger btn-sm">
			<i class="fa fa-pencil-square-o"></i>&nbsp; 委托单修改
		</button>
		<button id="btnAdd" type="button"
			onclick="deleteEntrust();"
			class="btn btn-info btn-sm">
			<i class="fa fa-trash-o"></i>&nbsp; 删除委托单
		</button>
		<button id="btnAdd" type="button"
			onclick="releaseTask()"
			class="btn btn-success btn-sm">
			<i class="glyphicon glyphicon-log-out"></i>&nbsp; 任务单发布
		</button>
		<span style="margin-left:450px;!important"  class="label label-sm label-success arrowed arrowed-in" id="fee" >资费标准：无 ，上次费用： 无</span>
	</div>
	<div class="row" style="margin-top: 5px;">
		<div class="col-xs-12 widget-container-col ui-sortable"
			style="min-height: 127px;">
			<!-- #section:custom/widget-box.options.transparent -->
			<div class="widget-box transparent ui-sortable-handle"
				style="opacity: 1; z-index: 0;">
				<div class="widget-body" style="display: block;">
					<div class="widget-main padding-6 no-padding-left no-padding-right">
					<div style="margin-top:7px;">
						<div class="col-xs-5" style="float:left">
							<select name="from[]" id="undo_redo" class="form-control" 
								size="5" style="height: 148px; width:100%;  margin-left:-12px" multiple="multiple">
							</select>
						</div>
						<div class="col-xs-2" style="float:left;!important">
							<button style="height: 23px; width:100%;margin-top:20px;" type="button" id="undo_redo_rightAll"
								class="btn btn-warning btn-block">
								<i style="margin-top:-7px;" class="glyphicon glyphicon-forward"></i>
							</button>
							<button style="height: 23px; width:100%;" type="button" id="undo_redo_rightSelected"
								class="btn btn-info btn-block">
								<i style="margin-top:-7px;" class="glyphicon glyphicon-chevron-right"></i>
							</button>
							<button style="height: 23px; width:100%; " type="button" id="undo_redo_leftSelected"
								class="btn btn-info btn-block">
								<i style="margin-top:-7px;" class="glyphicon glyphicon-chevron-left"></i>
							</button>
							<button style="height: 23px; width:100%;" type="button" id="undo_redo_leftAll"
								class="btn btn-warning btn-block">
								<i style="margin-top:-7px;" class="glyphicon glyphicon-backward"></i>
							</button>
						</div>
						<div class="col-xs-5">
							<select name="to[]" id="undo_redo_to" class="form-control"
								size="5" style="height: 148px;  width:100%; margin-left:10px;!important" multiple="multiple"></select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-top: 5px;">
		<div class="col-xs-12 widget-container-col ui-sortable" style="min-height: 127px;">
			<!-- #section:custom/widget-box.options.transparent -->
			<div class="widget-box transparent ui-sortable-handle" style="opacity: 1; z-index: 0;">
				<div class="widget-body" style="display: block;">
					<div class="widget-main padding-6 no-padding-left no-padding-right">
						<input id="pageNum" type="hidden" value="${page.pageNum }">
						<input id="pageSize" type="hidden" value="${page.pageSize }">
						<input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
						<input id="orderByType" type="hidden" value="${page.orderByType }">
						<div class="col-xs-7"  style="float:left; ">
						    <div class="col-xs-7" style="float:left">
							<select class="form-control" id="entrustId" name="entrustId" onchange="entrustFacilityList();" style=" margin-left:-12px; height:33px;width:100%">
							</select>
							</div>
							<button id="btnAdd" type="button"
								onclick="addFacilitysToEntrust()"
								class="btn btn-danger btn-sm">
								<i class="fa fa-plus-square-o"></i>&nbsp; 添加仪器
							</button>
							
						</div>

						<div style="margin-right:12px" id="facilityToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
						<div style="margin-left:12px;margin-right:11px" id="facilityContainer" class="dlshouwen-grid-container"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function flushLeftEntrust(){
			$.ajax({
				url : sys.rootPath + '/entrust/taskEntrusts.html?taskId='+$("#taskId").val(),
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					var entrusts = data.results;

					$("#entrustId").append('<option value="">未分配 </option>');
					$.each(entrusts, function(index, entrust) {
						$("#undo_redo").append('<option value="' + entrust.id + '">'+ entrust.text + '</option>');
						$("#entrustId").append('<option value="' + entrust.id + '">'+ entrust.text + '</option>');
					});
				}
			});
		}
		function clearRightEntrust(){
			$("#undo_redo_to").empty();
		}
		function clearLeftEntrust(){
			$("#undo_redo").empty();
		}
		function clearSelectEntrust(){
			$("#entrustId").empty();
		}
		function addEntrust() {
		    layer.open({
		    	  type: 2,
		    	  title: '请填写委托单信息',
		    	  shadeClose: true,
		    	  shade: 0.8,
		    	  area: ['1000px', '100%'],
		    	  content : sys.rootPath + '/entrust/newEntrustUI.html?taskId='+$("#taskId").val()+'&cid='+$("#cid").val()
		    	}); 
		}
		function showEntrust() {
			if($("#entrustId").val()==""){
				layer.msg("请选择委托单", {
					icon : 5
				});
				return ;
			}
		    layer.open({
		    	  type: 2,
		    	  title: '委托单信息',
		    	  shadeClose: true,
		    	  shade: 0.8,
		    	  area: ['1000px', '100%'],
		    	  content : sys.rootPath + '/entrust/newEntrustUI.html?taskId='+$("#taskId").val()+'&cid='+$("#cid").val()+'&entrustId='+$("#entrustId").val()
		    	}); 
		}
		function addFacilitysToEntrust(nav, callback) {
            var rows = grid.getCheckedRecords();
            var entrustId = "";
            if ($("#undo_redo_to")[0].length>1){
           	    layer.msg('右侧委托单只能有一个', {
                    icon : 3
                });
           	    return;
            }
            if ($("#undo_redo_to")[0].length<1){
           	    layer.msg('请从左侧委托单中，双击一个到右侧', {
                    icon : 3
                });
           	    return;
            }
            
            $("#undo_redo_to option").each(function() {
            	entrustId = $(this).attr("value");
            });
  
            if (rows.length >= 1) {
	            var facilityIds = [];
	            var cid = rows[0].cid;
	            var expectPrices = [];
				$.each(rows, function(index, value) {
					facilityIds.push(this.facilityId);
					expectPrices.push(this.expectPrice);
				});
	           
	            $.ajax({
                     type : "POST",
                     url : sys.rootPath + '/entrust/addFacilitysToEntrust.html',
                     data : {
                         "facilityIds" : facilityIds.join(','),
                         "taskId" : $("#taskId").val(),
                         "entrustId" : entrustId,
                         "cid" : cid,
                         "expectPrices" : expectPrices.join(',')
                     },
                     dataType : "json",
                     success : function(resultdata) {
                    	 entrustFacilityList();
	                 },
                     error : function(errorMsg) {
                         layer.msg('服务器未响应,请稍后再试', {
                             icon : 3
                         });
                     }
	             });
            } else {
                layer.msg("你没有选择行", {
                    icon : 0
                });
            }
		}
		function deleteEntrust(){
            if ($("#undo_redo_to").size()<=0){
           	    layer.msg('右侧委托单列表为空，请选择待删除委托单', {
                    icon : 3
                });
            }
            var entrustIds = [];
            $("#undo_redo_to option").each(function() {
            	entrustIds.push($(this).attr("value"));
            });
            $.ajax({
                type : "POST",
                url : sys.rootPath + '/entrust/deleteEntrusts.html',
                data : {
                    "entrustIds" : entrustIds.join(',')
                },
                dataType : "json",
                success : function(resultdata) {
               	  entrustFacilityList();
                  clearRightEntrust();
	              clearLeftEntrust();
	              clearSelectEntrust();
	              flushLeftEntrust();
                },
                error : function(errorMsg) {
                    layer.msg('服务器未响应,请稍后再试', {
                        icon : 3
                    });
                }
            });
		}
		
		function releaseTask() {
			$.ajax({
				type : "POST",
				url : sys.rootPath + '/entrust/releaseTask.html',
				data : {
					"taskId" : $("#taskId").val()
				},
				dataType : "json",
				success : function(resultdata) {
					if (resultdata.success) {
						var index = parent.layer.getFrameIndex(window.name);
						webside.common.loadPage('/entrust/taskListUI.html');
					} else {
						layer.msg(resultdata.message, {
							icon : 5
						});
					}
				},
				error : function(errorMsg) {
					layer.msg('服务器未响应,请稍后再试', {
						icon : 3
					});
				}
			});
			
		}
		$(document).ready(function() {
		    $('#undo_redo').multiselect();
		    flushLeftEntrust();

		});
	</script>
</body>
</html>