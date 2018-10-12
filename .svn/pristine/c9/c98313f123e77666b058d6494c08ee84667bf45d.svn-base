<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript" src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/select2/zh-CN.js"></script>
<link rel="stylesheet" href="${ctx }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<script type="text/javascript">

function initDateContrl(){
	$("#repairTime").datepicker({
        format : 'yyyy-mm-dd',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
        clearDate : function() {
            $("#repairTime").val('').datepicker('update');
        }
    });
	$("#finishTime").datepicker({
        format : 'yyyy-mm-dd',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
        clearDate : function() {
            $("#finishTime").val('').datepicker('update');
        }
    });
}

$(document).ready(function() {
	initDateContrl();
	
	$('#repairForm').validate(
	{
		errorElement : 'div',
		errorClass : 'help-block',
		focusInvalid : false,
		ignore : "",
		rules : {
			/* factoryNo : {
				required : true
			},
			facilityType : {
				required : true
			},
			facilityName : {
				required : true
			},
			facilityState : {
				required : true
			} */
		},
		highlight : function(e) {
			$(e).closest('.form-group').removeClass('has-info')
					.addClass('has-error');
		},
		success : function(e) {
			$(e).closest('.form-group')
					.removeClass('has-error').addClass(
							'has-success');
			$(e).remove();
		},
		errorPlacement : function(error, element) {
			if (element.is('input[type=checkbox]')
					|| element.is('input[type=radio]')) {
				var controls = element
						.closest('div[class*="col-"]');
				if (controls.find(':checkbox,:radio').length > 1)
					controls.append(error);
				else
					error.insertAfter(element.nextAll(
							'.lbl:eq(0)').eq(0));
			} else if (element.is('.select2')) {
				error
						.insertAfter(element
								.siblings('[class*="select2-container"]:eq(0)'));
			} else if (element.is('.chosen-select')) {
				error
						.insertAfter(element
								.siblings('[class*="chosen-container"]:eq(0)'));
			} else
				error.insertAfter(element.parent());
		},
		submitHandler : function(form) {
			url = '/facility/repair.html';
			webside.common.commit('repairForm', url,
					'/facility/listUI.html');
		}
	});
});
</script>
<div class="page-header">
	<h1>
		<c:if test="${empty repairEntity}">
		新增调修单
		</c:if>
		<c:if test="${!empty repairEntity}">
		更新调修单
		</c:if>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="repairForm" name="repairForm" class="form-horizontal" role="form" method="post">
			<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
			<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
			<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
			<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
			<input type="hidden" name="cid" id="cid" value="${repairEntity.cid }">
			<div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="facilityId">器具编号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="facilityId" id="facilityId" type="text"
		         	value="${repairEntity.facilityId }" readonly="true"  placeholder="器具编号..."/>
		    	</div>
		    	</div>
		    </div> 
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="facilityName">器具名称</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="facilityName" id="facilityName" type="text"
		         	value="${repairEntity.facilityName }" readonly="true" placeholder="器具名称..."/>
		    	</div>
		    	</div>
		    </div> 
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="factoryNo">出厂编号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="factoryNo" id="factoryNo" type="text"
		         	value="${repairEntity.factoryNo }" readonly="true" placeholder="器具编号..."/>
		    	</div>
		    	</div>
		    </div> 
			<div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="organ">调修机构名称</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="organ" id="organ" type="text"
		         	value="${repairEntity.organ }"  placeholder="调修机构名称..."/>
		    	</div>
		    	</div>
		    </div> 
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="telephone">调修机构电话</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="telephone" id="telephone" type="text"
		         	value="${repairEntity.telephone }" placeholder="调修机构电话..."/>
		    	</div>
		    	</div>
		    </div> 
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="contactPerson">调修机构联系人</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="contactPerson" id="contactPerson" type="text"
		         	value="${repairEntity.contactPerson }" placeholder="调修机构联系人..."/>
		    	</div>
		    	</div>
		    </div> 
		    <c:if test="${repairEntity.ifNew == '1' }">
			<div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="repairTime">调修时间</label>
		    	<div class="col-sm-10">
				  <div class="clearfix">
		    		<input class="form-control" name="repairTime" id="repairTime" type="text" 
		    		value="<fmt:formatDate value='${repairEntity.repairTime }' pattern='yyyy-MM-dd'/>"/>
				  </div>
			    </div>
		    </div>	
		    </c:if>
		    <c:if test="${repairEntity.ifNew == '0' }">
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="finishTime">完成时间</label>
		    	<div class="col-sm-10">
				  <div class="clearfix">
		    		<input  class="form-control" name="finishTime" id="finishTime" type="text" 
		    		value="<fmt:formatDate value='${repairEntity.finishTime }' pattern='yyyy-MM-dd'/>"/>
				  </div>
			    </div>
		    </div>
		    </c:if>		    
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#repairForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		保存
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/facility/listUI.html<c:if test="${!empty repairEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript">

</script>