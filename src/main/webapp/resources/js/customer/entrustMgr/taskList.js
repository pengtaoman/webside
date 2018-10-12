var dtGridColumns = [{
    id : 'id',
    title : '任务编号',
    type : 'string',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'taskName',
    title : '任务名称',
    type : 'string',
    search : true,
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'cid',
    title : '企业编号',
    type : 'number',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, {
    id : 'companyName',
    title : '企业名称',
    type : 'String',
    search : true,
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'ifEnforce',
    title : '是否强检',
    type : 'string',
    hide: true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '1') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">强检</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">非强检</span>';
        }
    }
}, {
    id : 'ifOver',
    title : '是否完检',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '0') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">完检</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">进行中</span>';
        }
    }
}, {
    id : 'note',
    title : '检测要求',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'ifValid',
    title : '是否生效',
    type : 'string',
    hide: true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '1') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">生效</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">未生效</span>';
        }
    }
}, {
    id : 'operator',
    title : '操作人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'updateTime',
    title : '更新时间',
    search : true,
    type : 'date',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
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
    loadURL : sys.rootPath + '/entrustMgr/taskList.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName :'资源信息',
    pageSize : 10,
    pageSizeLimit : [10,20],
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
    grid.refresh(true);
}


function addTask() {
	//webside.common.loadPage('/entrust/taskUI.html');
    var iconLayer = layer.open({
        type : 2,
        scrollbar : false,
        content : sys.rootPath + '/entrust/taskUI.html',
        area : 'auto',
        maxmin : true,
        shift : 4,
        title : '<i class="fa fa-cogs"></i>&nbsp;选择图标'
    });
    //弹出即全屏
    layer.full(iconLayer);
}

function editTask() {
	var rows = grid.getCheckedRecords();
	var taskId = "";
	if (rows.length == 1) {
		taskId = rows[0].id;
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
		return;
	}
    var iconLayer = layer.open({
        type : 2,
        scrollbar : false,
        content : sys.rootPath + '/entrust/taskUI.html?'+'taskId='+taskId,
        area : 'auto',
        maxmin : true,
        shift : 4,
        title : '<i class="fa fa-cogs"></i>&nbsp;选择图标'
    });
    //弹出即全屏
    layer.full(iconLayer);
}

function editEntrust() {
	var rows = grid.getCheckedRecords();
	var taskId = "";
	var cid = "";
	if (rows.length == 1) {
		taskId = rows[0].id;
		cid = rows[0].cid;
		debugger;
		companyName = rows[0].companyName;
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
		return;
	}
	webside.common.loadPage('/entrustMgr/entrustUI.html?taskId='+taskId+'&cid='+cid+'&companyName='+companyName);
   /* var iconLayer = layer.open({
        type : 2,
        scrollbar : false,
        content : sys.rootPath + '/entrustMgr/entrustUI.html?taskId='+taskId+'&cid='+cid,
        area : 'auto',
        maxmin : true,
        shift : 4,
        title : '<i class="fa fa-cogs"></i>&nbsp;选择图标'
    });
    //弹出即全屏
    layer.full(iconLayer);*/
}
function deleteTask() {
	var rows = grid.getCheckedRecords();
	if (rows.length >= 1) {
		var delete_ids = [];
		$.each(rows, function(index, value) {
				delete_ids.push(this.id);
		});
		layer.confirm('确认删除吗？', {
			icon : 3,
			title : '删除提示'
		}, function(index, layero) {
			$.ajax({
				type : "POST",
				url : sys.rootPath + '/entrust/deleteTasks.html',
				data : {
					"ids" : delete_ids.join(',')
				},
				dataType : "json",
				success : function(resultdata) {
					if (resultdata.success) {
						layer.msg(resultdata.message, {
							icon : 1
						});
						
						customSearch();
					    
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
			layer.close(index);
		});
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
	}
}

function backTask() {
	var rows = grid.getCheckedRecords();
	if (rows.length >= 1) {
		var ids = [];
		$.each(rows, function(index, value) {
			ids.push(this.id);
		});
		$.ajax({
			type : "POST",
			url : sys.rootPath + '/entrustMgr/backTask.html',
			data : {
				"taskIds" : ids.join(',')
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					layer.msg(resultdata.message, {
						icon : 5
					});
					customSearch();
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
	} else {
		layer.msg("你没有选择行.", {
			icon : 0
		});
	}
}