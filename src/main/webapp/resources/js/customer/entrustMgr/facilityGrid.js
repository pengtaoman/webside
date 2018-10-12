var dtGridColumns = [{
    id : 'id',
    title : '编号',
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
    id : 'facilityState',
    title : '仪器状态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}];

//动态设置jqGrid的rowNum
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/facility/list.html',
    columns : dtGridColumns,
    gridContainer : 'facilityContainer',
    toolbarContainer : 'facilityToolBarContainer',
    tools : '',
    exportFileName :'资源信息',
    pageSize : 20,
    pageSizeLimit : [20],
	onCheck : function(isChecked, record, grid, dataNo, row, extraCell, e) {
		addFacilitySet(isChecked,record.id);
	}

};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['source'] = "entrust";
    grid.parameters['cid'] = $("#cid").val();
    grid.load();
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function facilitySearch() {
    grid.parameters = new Object();
    grid.parameters['cid'] = $("#cid").val();
    grid.refresh(true);
}

var facilitySet = new Set();

function addFacilitySet(addflag, id) {
	if(addflag){
		facilitySet.add(id);
	} else {
		facilitySet.delete(id);
	}
}

function getfacilitySet(){
	var idStr="";
	facilitySet.forEach(function (element, sameElement, set) {
	    idStr = idStr+element+",";
	});
	return idStr;
}

function test(){
	alert($("#aaa").val());
}
