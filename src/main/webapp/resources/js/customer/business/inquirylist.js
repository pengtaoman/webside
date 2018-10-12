var dtGridColumns = [{
    id : 'id',
    title : '编号',
    width : 60,
    hide : false,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a href="javascript:void(0);" onclick="showInquiryDetails(\''+ record.id +'\')">'+ value +'</a>';
    }
},{
    id : 'note',
    title : '询价单名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'userName',
    title : '提交人',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'companyName',
    title : '公司名',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'status',
    title : '状态',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 0) {
            return '<span class="label label-sm label-warning  arrowed arrowed-in">已提交</span>';
        } else {
            return '<span class="label label-sm label-success">已报价</span>';
        }
    }
},{
    id : 'createTime',
    title : '操作时间',
    search : true,
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
} 
, {
    id : 'operate',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a href="javascript:void(0);" onclick="downloadDetails(\''+ record.id +'\',\''+ record.status +'\')">下载</a>';
    }
}
];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    loadURL : sys.rootPath + '/inquiry/list.html',
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    //loadURL : sys.rootPath + '/fee/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '询价列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 50],
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
    
    SearchPanel.SearchPanelWithLike($("#searchPanel1"), dtGridColumns, false, grid);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});

function downloadDetails(rowId, status) {
	console.log(rowId);
	var gridPager = 
	{"pageSize":"10000",
		"startRecord":0,
		"nowPage":1,		
		"isExport":true,
		"exportFileName":"询价单",
		"exportType":"excel",
		"exportAllData":true,
		"exportColumns": [{
			"id": "id",
			"title": "编号",
			"width": 60,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "name",
			"title": "计量器具名",
			"width": null,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "field",
			"title": "所属专业",
			"width": null,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "model",
			"title": "规格型号",
			"width": null,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "accuracy",
			"title": "准确度等级",
			"width": null,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "testRange",
			"title": "测量范围",
			"width": null,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "unit",
			"title": "收费单位",
			"width": 80,
			"type": "string",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "sm|xs|md",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "standPrice",
			"title": "上次报价",
			"width": 80,
			"type": "number",
			"format": "#,###.00",
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "xs",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "expectPrice",
			"title": "期望费用",
			"width": 80,
			"type": "number",
			"format": "#,###.00",
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "xs",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		},
		{
			"id": "amount",
			"title": "数量",
			"width": 80,
			"type": "number",
			"format": null,
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": false,
			"hideType": "xs",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		}],
		"parameters":{"id":rowId}};
	
	if(status != '0') {
		var replyPrice = 
		{
			"id": "price",
			"title": "报价",
			"width": 80,
			"type": "number",
			"format": "#,###.00",
			"otype": null,
			"oformat": null,
			"columnStyle": "",
			"columnClass": "text-center",
			"headerStyle": "",
			"headerClass": "dlshouwen-grid-header",
			"hide": true,
			"hideType": "xs",
			"extra": true,
			"codeTable": null,
			"fastQuery": false,
			"fastQueryType": "",
			"advanceQuery": true,
			"export": true,
			"print": true,
			"resolution": null
		}
		gridPager.exportColumns.push(replyPrice);
		
	} else {
		
	}
	gridPager.exportColumns.push({
		"id": "note",
		"title": "报价备注",
		"type": "string",
		"otype": null,
		"oformat": null,
		"columnStyle": "",
		"columnClass": "text-center",
		"headerStyle": "",
		"headerClass": "dlshouwen-grid-header",
		"hide": true,
		"hideType": "xs",
		"extra": true,
		"codeTable": null,
		"fastQuery": false,
		"fastQueryType": "",
		"advanceQuery": true,
		"export": true,
		"print": true,
		"resolution": null
	});
	webside.download("/inquiry/listDetails.html", gridPager);
}

function showInquiryDetails(rowId) {
	if ("subplatform" == $("#UR_TYPE_0").val()) {
		webside.common.loadPage('/inquiry/replyUI.html?id=' + rowId);
	} else {
		webside.common.loadPage('/inquiry/editUI.html?id=' + rowId);
	}
}

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
	SearchPanel.refreshGrid(grid);
}