var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    hide : true,
	headerClass : 'dlshouwen-grid-header',
	hideType : 'sm|xs|md'
}, {
    id : 'cid',
    title : '企业编号',
    hide : true,
    type : 'string',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
},  {
    id : 'companyName',
    title : '企业',
    type : 'string',
    search : true,
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hideType : 'sm|xs|md'
},{
    id : 'factoryNo',
    title : '出厂编号',
    type : 'string',
    search : true,
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
    search : true,
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
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<span class="label label-sm label-warning arrowed arrowed-righ">'+value+'</span>';
    }
}, {
    id : 'ifTest',
    title : '是否可检测',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
       return '<span class="label label-sm label-success arrowed arrowed-righ">'+value+'</span>';
    }
}, {
    id : 'lastExpectPrice',
    title : '报价',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'belongOrgan',
    title : '责任部门',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'manageNo',
    title : '管理编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'price',
    title : '价格',
    search : true,
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'localSource',
    title : '采购地',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'purchaseDate',
    title : '购买日期',
    type : 'date',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'expirationDate',
    title : '有效日期',
    type : 'date',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'facilityAttach',
    title : '仪器附件',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'keeper',
    title : '保管人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'note',
    title : '备注',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/facility/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,txt]',
    exportFileName :'器具列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	row.children()[1].children[0].checked = !row.children()[1].children[0].checked;
    }
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    
  //$("#btnSearch").click(customSearch);
    SearchPanel.SearchPanelWithLike($("#searchPanel1"), dtGridColumns, true, grid);
});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() { 
    //grid.parameters = new Object();
   // grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}