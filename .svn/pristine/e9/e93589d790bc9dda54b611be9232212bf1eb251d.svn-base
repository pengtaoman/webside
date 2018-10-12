<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<head>
	<meta charset="utf-8" />
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/fonts/fontawesome/font-awesome.min.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/fonts/opensans/ace-fonts.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/ace/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/customer/webside.min.css"/>
	<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/ace/ace-part2.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/ace/ace-ie.min.css" />
	<![endif]-->
	<!-- JQuery script -->
	<!-- 非IE浏览器不会识别IE的条件注释，所以这里判断非IE需要如下写法：参照下面jquery-2.1.4.min.js引入的方式 -->
	<!--[if !IE]><!-->
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script> 
	<%-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>--%>
	<!--<![endif]-->
	<!--[if IE]>
		<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery/jquery-1.11.3.min.js"></script>
	<![endif]-->
	<!-- basic scripts -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/layer-v2.3/layer.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jqueryui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/js/jqueryui/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-validation/localization/messages_zh.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/nicescroll/jquery.nicescroll.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/js/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/underscore/underscore-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/purl/purl.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/scrollreveal/scrollreveal.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/customer/index/index.js"></script>
	<script type="text/javascript">
		var sys = sys || {};
		sys.rootPath = "${ctx}";
		sys.pageNum = 10;
		sys.gridStyle = "Bootstrap";
	</script>
	<!-- css3动画 -->
	<link href="${pageContext.request.contextPath }/resources/css/animate/animate.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
    <script type="text/javascript" src="${ctx }/resources/js/bootstrap-datetimepicker-master/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${ctx }/resources/js/bootstrap-datetimepicker-master/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>	
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/entrustMgr/newEntrust.js"></script>
	<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/webuploader/css/webuploader.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/webuploader/js/webuploader.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/filehelper.js"></script>
	<script type="text/javascript" src="${ctx }/resources/js/plugin/jquery.ui.widget.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#entrustForm").validate({
			    rules: {
			    	name: "required",
			    	ifEnforce: "required",
			    	organId: "required",
			    	proposer: "required",
			    	proposeDate: "required",
			    	contactPhone: {
				        digits:true
				      }
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
	            	entrust.commit('entrustForm','/entrust/addEntrust.html');
	            	//webside.common.commit("form1", "/organ/update.html", "/organ/listUI.html");
	            }
			});
		});
	</script>
</head>

<body>
	<div class="container">
			<div class="row" style="margin-top: 0px;">
				<div class="col-xs-12">
					<form id="entrustForm" name="entrustForm" class="form-horizontal"	role="form"  method="post">
						<input type="hidden" name="taskId" id="taskId" value="${taskId }">
						<input type="hidden" name="id" id="id" value="${enforceEntrustEntity.id }">
						<input type="hidden" name="cid" id="cid" value="${cid }">
						<input type="hidden" name="ifEnforceInit" id="ifEnforceInit" value="${enforceEntrustEntity.ifEnforce }">
						<input type="hidden" name=attachment id="attachment">
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="name">委托单名称</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="name" id="name" type="text"
									value="${enforceEntrustEntity.name }" placeholder="委托单名称..." />
							</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="ifEnforce">是否强检</label>
							<div class="col-sm-10">
								<div class="clearfix">
									<select class="form-control" id="ifEnforce" name="ifEnforce" style="width: 100%">
										<option value="1">强检</option>
										<option value="0">非强检</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="organId">检测机构</label>
							<div class="col-sm-10">
								<div class="clearfix">
									<select class="form-control" id="organId" name="organId" style="width: 100%">
										<option value="${enforceEntrustEntity.organId }">${organName }</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="contacter">联系人</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="contacter" id="contacter" type="text"
									value="${enforceEntrustEntity.contacter }" placeholder="联系人..." />
							</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="contactPhone">联系电话</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="contactPhone" id="contactPhone" type="text"
									value="${enforceEntrustEntity.contactPhone }" placeholder="联系电话..." />
							</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="contactAddress">联系地址</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="contactAddress" id="contactAddress" type="text"
									value="${enforceEntrustEntity.contactAddress }" placeholder="联系地址..." />
							</div>
							</div>
						</div>			
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="postcode">邮政编码</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="postcode" id="postcode" type="text"
									value="${enforceEntrustEntity.postcode }" placeholder="邮政编码..." />
							</div>
							</div>
						</div>	
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="proposer">申请人</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="proposer" id="proposer" type="text"
									value="${enforceEntrustEntity.proposer }" placeholder="申请人..." />
							</div>
							</div>
						</div>	
						<div class="form-group">
					    	<label class="control-label col-sm-2 no-padding-right" for="proposeDate">委托日期(申)</label>
					    	<div class="col-sm-10">
					    	<div class="clearfix">
					        	<div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="proposeDate" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" id="proposeDate" name="proposeDate"
				                     value="<fmt:formatDate value='${enforceEntrustEntity.proposeDate }' pattern='yyyy-MM-dd'/>" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
					    	</div>
					    	</div>
					    </div>	
							
						<div class="form-group">
					    	<label class="control-label col-sm-2 no-padding-right" for="importDate">填报日期</label>
					    	<div class="col-sm-10">
					    	<div class="clearfix">
					        	<div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="importDate" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="6" type="text" id="importDate" name="importDate" 
				                     value="<fmt:formatDate value='${enforceEntrustEntity.importDate }' pattern='yyyy-MM-dd'/>" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
					    	</div>
					    	</div>
					    </div>	
						
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="receiver">领取人</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" style="height: 34px;" name="receiver" id="receiver" type="text"
									value="${enforceEntrustEntity.receiver }" placeholder="领取人..." />
							</div>
							</div>
						</div>	
						<div class="form-group">
					    	<label class="control-label col-sm-2 no-padding-right" for="receiveDate">领取日期(领)</label>
					    	<div class="col-sm-10">
					    	<div class="clearfix">
					        	<div class="input-group date form_date col-md-6" data-date="" data-date-format="yyyy-mm-dd" data-link-field="receiveDate" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" id="receiveDate" name="receiveDate" 
				                    value="<fmt:formatDate value='${enforceEntrustEntity.receiveDate }' pattern='yyyy-MM-dd'/>" readonly>
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
					    	</div>
					    	</div>
					    </div>		
						<div class="form-group">
							<label class="control-label col-sm-2 no-padding-right" for="attachment">上传附件</label>
							<div class="col-sm-10">
							<div class="clearfix">
								<div id="certDiv" class="clearfix">
			      				</div>
			     				<div id="uploadDiv" style="display:none">
		      					</div>	
							</div>
							
							</div>
						</div>		
						<div class="center">
							<button id="btnAdd" type="button" onclick="javascript:$('#entrustForm').submit();" class="btn btn-success btn-sm">
								<i class="fa fa-user-plus"></i>&nbsp;
								保存
							</button>	
							<button id="btnChange" type="button" onclick="onModifyClick()" class="btn btn-success btn-sm">
								<i class="fa fa-user-plus"></i>&nbsp;
								附件变更
							</button>		
						</div>		    					
					</form>
				</div>
			</div>
	</div>

	<script type="text/javascript">
		function onModifyClick() {
			// 修改时，初始化为原图	
			FileHelper.initFile($("#uploadDiv"),function(filename) {
				$("#attachment").val(filename);
			});
			$("#certDiv").attr("style", "display:none");
			$("#uploadDiv").attr("style", "display:block");	

		}
		
	
		function initFile() {
			if ("${enforceEntrustEntity }" != undefined && "${enforceEntrustEntity.name}" != "") {
				$("#attachment").val("${enforceEntrustEntity.attachment }");
				if ("${enforceEntrustEntity.attachment }" != "") {
					var url = "${ctx }/file/downloadEntrustFile.html?name=${enforceEntrustEntity.attachment }";
					$("#certDiv").html('<a id="certImg" style="margin-top:-20px;" href="' + url +'" height="100" >${enforceEntrustEntity.attachment}</a>');
				}
			} else {
				$("#certDiv").attr("style", "display:none");
				$("#uploadDiv").attr("style", "display:block");	
				
				/* FileHelper.init($("#uploadDiv"), null, 100, 80, function(filename) {
					$("#attachment").val(filename);
				}); */
				FileHelper.initFile($("#uploadDiv"),function(filename) {
					$("#attachment").val(filename);
				});
			}
		}

		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		
		$(document).ready(function() {
			entrust.initCid();
			entrust.initIfEnfore();
			initFile();
		});
	</script>
</body>
</html>