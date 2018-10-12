var dtGridColumnsTD = [{
    id : 'id',
    title : '任务编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, {
    id : 'facilityId',
    title : '仪器编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, {
    id : 'cid',
    title : '企业编号',
    type : 'number',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'factoryNo',
    title : '出厂编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'facilityName',
    title : '仪器名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'facilityType',
    title : '仪器类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'model',
    title : '规格型号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'accuracy',
    title : '准确度等级',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'testRange',
    title : '测量范围',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'lastTestDate',
    title : '上次检测日期',
    type : 'date',
    columnClass : 'text-center',
    search : true,
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
},{
	id:'operation', 
	title:'操作', 
	type:'string', 
	columnClass:'text-center', 
	resolution:function(value, record, column, grid, dataNo, columnNo){
		var content = '';
		content += '<button class="btn btn-xs btn-danger" onclick="deleteFacility(\''+record.id+'\');">删除</button>';
		return content;
	}
}];

var dtGridOptionTD = {
    lang : 'zh-cn',
    ajaxLoad : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/entrust/taskDetailList.html',
    columns : dtGridColumnsTD,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName :'任务单明细',
    pageSize : 5,
    pageSizeLimit : [5]
};

var gridTD = $.fn.dlshouwen.grid.init(dtGridOptionTD);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
    	gridTD.sortParameter.columnId = $("#orderByColumn").val();
    	gridTD.sortParameter.sortType = $("#orderByType").val();
    }
    gridTD.parameters = new Object();
    gridTD.parameters['taskId'] = $("#id").val();
    gridTD.load();/*
    debugger;
    $("#facilityCountSpan").html(gridTD.pager.recordCount);*/
});

function taskDetailSearch() {
	gridTD.parameters = new Object();
	gridTD.parameters['taskId'] = $("#id").val();
	gridTD.refresh(true);
	
}

function closeTask() {
	 var index = parent.layer.getFrameIndex(window.name);
	 parent.customSearch();
     //再执行关闭
     parent.layer.close(index);
}

function deleteFacility( id) {
	$.ajax({
		type : "POST",
		url : sys.rootPath + '/entrust/deleteTaskFacility.html',
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(resultdata) {
			if (resultdata.success) {
				layer.msg(resultdata.message, {
					icon : 1
				});
				
				freshFacilityList();
			    
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