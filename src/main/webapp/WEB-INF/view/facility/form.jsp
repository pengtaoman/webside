<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"	src="${ctx }/resources/js/select2/zh-CN.js"></script>

<link rel="stylesheet" href="${ctx }/resources/js/datepicker/css/bootstrap-datepicker3.standalone.min.css"/>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/datepicker/locales/bootstrap-datepicker.zh-CN.min.js" charset="UTF-8"></script>

<script type="text/javascript">
function initDateContrl(){
	$("#purchaseDate").datepicker({
        format : 'yyyy-mm-dd',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
        clearDate : function() {
            $("#purchaseDate").val('').datepicker('update');
        }
    });
	
	$("#expirationDate").datepicker({
        format : 'yyyy-mm-dd',
        autoclose : true,
        language : 'zh-CN',
        todayHighlight : true,
        clearBtn : true,
        immediateUpdates : true,
        clearDate : function() {
            $("#expirationDate").val('').datepicker('update');
        }
    });
}
 $(document).ready(function() {
	webside.form.facility.initCid();
	webside.form.facility.initFacilityType();
	//webside.form.facility.initFacilityName();
	//webside.form.facility.initModel(); 
	//webside.form.facility.initAccuracy(); 
	//webside.form.facility.initTestRange();
	webside.form.facility.initFacilityState(); 
	webside.form.facility.validateFacilityForm(); 
	
	//added by pengtao begin
	<%if(!("t".equals((String)request.getAttribute("isAdd")))) {%>
		webside.form.facility.initIfTest();
	<%}%>
	webside.form.facility.initFacilityState();
	//end
	initDateContrl();
});
 
 
</script>
<div class="page-header">
	<h1>
	<c:choose>
		<c:when test="${empty facilityEntity}">
		新增器具
		</c:when>
		<c:when test="${!empty facilityEntity && !empty facilityEntity.id}">
		编辑器具
		</c:when>
		<c:otherwise>
		新增器具
		</c:otherwise>
	</c:choose>
	</h1>
</div>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="facilityForm" name="facilityForm" class="form-horizontal" role="form" method="post">
			<c:if test="${!empty facilityEntity}">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }"> 
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> 
				<input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }"> 
				<input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
				<input type="hidden" name="id" id="id" value="${facilityEntity.id }">
				<input type="hidden" name="cidInit" id="cidInit" value="${facilityEntity.cid }">
				<input type="hidden" name="cidNameInit" id="cidNameInit" value="${cidNameInit }">
				<input type="hidden" name="facilityTypeInit" id="facilityTypeInit" value="${facilityEntity.facilityType }">
				<input type="hidden" name="facilityNameInit" id="facilityNameInit" value="${facilityEntity.facilityName }">
				<input type="hidden" name="modelInit" id="modelInit" value="${facilityEntity.model }">
				<input type="hidden" name="accuracyInit" id="accuracyInit" value="${facilityEntity.accuracy}">
				<input type="hidden" name="testRangeInit" id="testRangeInit" value="${facilityEntity.testRange }">
				<input type="hidden" name="facilityStateInit" id="facilityStateInit" value="${facilityEntity.facilityState }">
				<input type="hidden" name="facilityStateNameInit" id="facilityStateNameInit" value="${facilityStateName }">
				<input type="hidden" name="ifTestInit" id="ifTestInit" value="${facilityEntity.ifTest }">
				<input type="hidden" name="ifTestNameInit" id="ifTestNameInit" value="${ifTestName }">
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="cid">企业</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="cid" name="cid" style="width: 100%">
						<option value=""></option>
					</select>
				</div>
				</div>
			</div>
			<div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="factoryNo">出厂编号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="factoryNo" id="factoryNo" type="text"
		         	value="${facilityEntity.factoryNo }" placeholder="出厂编号..."/>
		    	</div>
		    	</div>
		    </div> 
		    <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="facilityType" >所属专业</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="facilityType" name="facilityType" style="width: 100%">
					<option value=""></option>
					</select>
				</div>
				</div>
			</div>
		 <!--    <div class="form-group">
		        <label class="control-label col-sm-1 no-padding-right" for="facilityName">计量器具</label>
		        <div class="col-sm-10">
		        <div class="clearfix">
	         	    <select class="form-control" id="facilityName" name="facilityName" style="width: 100%">
	         	    <option value=""></option>
					</select>
		    	</div>
		    	</div>
		    </div>  
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="model">规格型号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		         	<select class="form-control" id="model" name="model" style="width: 100%">
		         	<option value=""></option>
					</select>
		    	</div>
		   		</div>
		    </div>  
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="accuracy">准确度等级</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		         	<select class="form-control" id="accuracy" name="accuracy" style="width: 100%">
		         	<option value=""></option>
					</select>
		    	</div>
		   		</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="testRange">测量范围</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		         	<select class="form-control" id="testRange" name="testRange" style="width: 100%">
		         	<option value=""></option>
					</select>
		    	</div>
		   		</div>
		    </div> -->
		    <div class="form-group">
		        <label class="control-label col-sm-1 no-padding-right" for="facilityName">计量器具</label>
		        <div class="col-sm-10">
		        <div class="clearfix">
					<input class="form-control" name="facilityName" id="facilityName" type="text"
		         	value="${facilityEntity.facilityName }" placeholder="计量器具..."/>
		    	</div>
		    	</div>
		    </div>  
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="model">规格型号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
					<input class="form-control" name="model" id="model" type="text"
		         	value="${facilityEntity.model }" placeholder="规格型号..."/>
		    	</div>
		   		</div>
		    </div>  
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="accuracy">准确度等级</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
					<input class="form-control" name="accuracy" id="accuracy" type="text"
		         	value="${facilityEntity.accuracy }" placeholder="准确度等级..."/>
		    	</div>
		   		</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="testRange">测量范围</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
					<input class="form-control" name="testRange" id="testRange" type="text"
		         	value="${facilityEntity.testRange }" placeholder="测量范围..."/>
		    	</div>
		   		</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="facilityState">仪器状态</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		         	<select class="form-control" id="facilityState" name="facilityState" style="width: 100%">
		         	<option value=""></option>
					</select>
		    	</div>
		   		</div>
		    </div>
		    <div class="form-group">
				<label class="control-label col-sm-1 no-padding-right" for="ifTest" >是否可检测</label>
				<div class="col-sm-10">
				<div class="clearfix">
					<select class="form-control" id="ifTest" name="ifTest" style="width: 100%">
						<option value="1" selected="true">可检测</option>
						<option value="0" >不可检测</option>
					</select>
				</div>
				</div>
			</div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="belongOrgan">责任部门</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="belongOrgan" id="belongOrgan" type="text"
		         	value="${facilityEntity.belongOrgan }" placeholder="责任部门..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="manageNo">管理编号</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="manageNo" id="manageNo" type="text"
		         	value="${facilityEntity.manageNo }" placeholder="管理编号..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="manufacturer">生产厂家</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="manufacturer" id="manufacturer" type="text"
		         	value="${facilityEntity.manufacturer }" placeholder="生产厂家..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="price">价格（元）</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="price" id="price" type="number"
		         	value="${facilityEntity.price }" placeholder="价格..."/>
		    	</div>
		    	</div>
		    </div>
		    
		    <div class="form-group">
		     	<label class="control-label col-sm-1 no-padding-right" for="purchaseDate">购买日期</label>
				<div class="col-sm-10">
				  <div class="clearfix">
		    		<input class="form-control" name="purchaseDate" id="purchaseDate" type="text" 
		    		value="<fmt:formatDate value='${facilityEntity.purchaseDate }' pattern='yyyy-MM-dd'/>"/>
				  </div>
			    </div>
		    </div>
  			<div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="expirationDate">有效日期</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		    		<input class="form-control" name="expirationDate" id="expirationDate" type="text"
		    		value="<fmt:formatDate value='${facilityEntity.expirationDate }' pattern='yyyy-MM-dd'/>"/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="facilityAttach">仪器附件</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="facilityAttach" id="facilityAttach" type="text"
		         	value="${facilityEntity.facilityAttach }" placeholder="仪器附件..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="keeper">保管人</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="keeper" id="keeper" type="text"
		         	value="${facilityEntity.keeper }" placeholder="保管人..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="localSource">采购地</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="localSource" id="localSource" type="text"
		         	value="${facilityEntity.localSource }" placeholder="采购地..."/>
		    	</div>
		    	</div>
		    </div>
		    <div class="form-group">
		    	<label class="control-label col-sm-1 no-padding-right" for="note">备注</label>
		    	<div class="col-sm-10">
		    	<div class="clearfix">
		        	<input class="form-control" name="note" id="note" type="text"
		         	value="${facilityEntity.note }" placeholder="备注..."/>
		    	</div>
		    	</div>
		    </div>
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="javascript:$('#facilityForm').submit();" class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp;
		<c:choose>
			<c:when test="${empty facilityEntity}">
			添加
			</c:when>
			<c:when test="${!empty facilityEntity && !empty facilityEntity.id}">
			保存
			</c:when>
			<c:otherwise>
			添加
			</c:otherwise>
		</c:choose>
	</button>
	<button id="btn" type="button"
		onclick="webside.common.loadPage('/facility/listUI.html<c:if test="${!empty resourceEntity}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script type="text/javascript">

</script>