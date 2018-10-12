var dtGridColumnsTD = [{
    id : 'id',
    title : '任务编号',
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
}
];

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
    gridTD.load();
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