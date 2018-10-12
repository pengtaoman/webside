var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'code',
    search : '1',
    title : '序号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'idSub',
    title : '院标准ID',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'idProvince',
    title : '省标准ID',
    type : 'string',
    hideType : 'sm|xs|md',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'idCountry',
    title : '国家标准ID',
    type : 'string',
    hideType : 'sm|xs|md',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'name',
    search : '1',
    title : '计量器具名',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'field',
    title : '所属专业',
    search : '1',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'model',
    title : '规格型号',
    search : '1',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'accuracy',
    title : '准确度等级',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'testRange',
    search : '1',
    title : '测量范围',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'feeUnit',
    title : '收费单位',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'feePrice',
    search : '1',
    title : '收费标准',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'alias',
    title : '别名',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'status',
    title : '状态',
    type : 'string',
    search : '1',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '审核通过') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">'+ value + '</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">'+ value + '</span>';
        }
    }
},{
    id : 'note',
    title : '备注',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'operater',
    title : '操作人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs'
}, {
    id : 'operateTime',
    title : '操作时间',
    search : '1',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs',
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
    search : '1',
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
    loadURL : sys.rootPath + '/fee/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,pdf,txt]',
    exportFileName : '资费标准',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	row.children()[1].children[0].checked = !row.children()[1].children[0].checked;
    }
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);

function advanceShowClick() {
	$("#searchPanel1").attr("style", "display:none");
	$("#searchPanelAdv").attr("style", "display:block");
	
	initAdvanceSearch();
}
function advanceHideClick() {
	$("#searchPanelAdv").attr("style", "display:none");
	$("#searchPanel1").attr("style", "display:block");	
}

function advanceSearchGoClick(gridObj) {
	grid.parameters = new Object();
	
	var val = $("#selectField").val();
	if(val != "" ) {
		gridObj.parameters["field"] = val;
	}
	var val = $("#selectModel").val();
	if(val != "" ) {
		gridObj.parameters["model"] = val;
	}
	var val = $("#selectAccuracy").val();
	if(val != "" ) {
		gridObj.parameters["accuracy"] = val;
	}
	var val = $("#selectTestRange").val();
	if(val != "" ) {
		gridObj.parameters["testRange"] = val;
	}
	grid.parameters["query_expr"] = '=';
    grid.refresh(true);
}

function initAdvanceSearch() {
	$("#selectField").select2();// 初始化控件，只有初始化绑定其它事件才生效
	$("#selectType").select2();// 初始化控件，只有初始化绑定其它事件才生效
	$("#selectModel").select2();// 初始化控件，只有初始化绑定其它事件才生效
	$("#selectAccuracy").select2();// 初始化控件，只有初始化绑定其它事件才生效
	$("#selectTestRange").select2();// 初始化控件，只有初始化绑定其它事件才生效
	
	var existData = $("#selectField").select2('data');
	if(existData.length == 0) {
		var data={};			
		webside.getData("/fee/listFields", data, 
				  function(result) {
					  if(result != null && result.success) {
						selectList1 = result;
						$("#selectField").select2({
						  data: result.data,
						  placeholder:'请选择',
						  allowClear:true
						});	
						$("#selectField").val(null).trigger("change");
						$("#selectType").val(null).trigger("change");
						$("#selectModel").val(null).trigger("change");
						$("#selectAccuracy").val(null).trigger("change");
						$("#selectTestRange").val(null).trigger("change");
					  } else {
						  layer.msg(result.message, {icon : 0}); 
					  }
				  }, 
				  function(result) {
					  layer.msg("无法与服务器通信，请重试或者联系管理员", {icon : 0}); 
				  });
	} 
	
	$("#selectField").on("select2:select", function (e) { 
		var data={ 
				"field":$("#selectField").val()
				};
		//得到value
  	  	webside.getData("/fee/listFacilityNames", data, 
  			  function(result) {
  	  			if(result.success) {
  					$("#selectType").select2('destroy').empty();  
  					$("#selectType").select2({
  					  data: result.data,
  					  placeholder:'请选择',
  					  allowClear:true
  					});
					$("#selectType").val(null).trigger("change");
					$("#selectModel").val(null).trigger("change");
					$("#selectAccuracy").val(null).trigger("change");
					$("#selectTestRange").val(null).trigger("change");
  				  } else {
  					  layer.msg(result.message, {icon : 0}); 
  				  }
  			  }, 
  			  function(result) {
  				  layer.msg("/fee/test出错，请重试或者联系管理员", {icon : 0}); 
  		 });
	});
	
	$("#selectType").on("select2:select", function (e) { 
		var data={ 
					"field":$("#selectField").val(),
					"fname":$("#selectType").val(),
				};
		//得到value
  	  	webside.getData("/fee/listModels", data, 
  			  function(result) {
  	  			if(result.success) {
  					$("#selectModel").select2('destroy').empty();  
  					$("#selectModel").select2({
  					  data: result.data,
  					  placeholder:'请选择',
  					  allowClear:true
  					});
					$("#selectModel").val(null).trigger("change");
					$("#selectAccuracy").val(null).trigger("change");
					$("#selectTestRange").val(null).trigger("change");
  				  } else {
  					  layer.msg(result.message, {icon : 0}); 
  				  }
  			  }, 
  			  function(result) {
  				  layer.msg("/fee/test出错，请重试或者联系管理员", {icon : 0}); 
  		 });
	});
	
	$("#selectModel").on("select2:select", function (e) { 
		var data={ 
					"field":$("#selectField").val(),
					"fname":$("#selectType").val(),
					"model":$("#selectModel").val(),
				};
		//得到value
  	  	webside.getData("/fee/listAccuracys", data, 
  			  function(result) {
  	  			if(result.success) {
  					$("#selectAccuracy").select2('destroy').empty();  
  					$("#selectAccuracy").select2({
  					  data: result.data,
  					  placeholder:'请选择',
  					  allowClear:true
  					});
					$("#selectAccuracy").val(null).trigger("change");
					$("#selectTestRange").val(null).trigger("change");
  				  } else {
  					  layer.msg(result.message, {icon : 0}); 
  				  }
  			  }, 
  			  function(result) {
  				  layer.msg("/fee/test出错，请重试或者联系管理员", {icon : 0}); 
  		 });
	});
	
	$("#selectAccuracy").on("select2:select", function (e) { 
		var data={ 
					"field":$("#selectField").val(),
					"fname":$("#selectType").val(),
					"model":$("#selectModel").val(),
					"accuracy":$("#selectAccuracy").val(),
				};
		//得到value
  	  	webside.getData("/fee/listTestRanges", data, 
  			  function(result) {
  	  			if(result.success) {
  					$("#selectTestRange").select2('destroy').empty();  
  					$("#selectTestRange").select2({
  					  data: result.data,
  					  placeholder:'请选择',
  					  allowClear:true
  					});
  					//$("#selectTestRange").trigger('change.select2');
					$("#selectTestRange").val(null).trigger("change");
  				  } else {
  					  layer.msg(result.message, {icon : 0}); 
  				  }
  			  }, 
  			  function(result) {
  				  layer.msg("/fee/test出错，请重试或者联系管理员", {icon : 0}); 
  		 });
	});
	
}

$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    
    SearchPanel.SearchPanelWithLikeAndAdvance($("#searchPanel1"), dtGridColumns, true, grid, advanceShowClick);
    initAdvanceSearch();
    
    $("#btnSearchReturn").bind("click", advanceHideClick);
    $("#btnSearchGo").click(function(){advanceSearchGoClick(grid)});
    
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
function customSearch() {
	SearchPanel.refreshGrid(grid);
}