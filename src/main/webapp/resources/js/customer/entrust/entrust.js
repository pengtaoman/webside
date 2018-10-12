var CONS_price='expectPrice';

var dtGridColumns = [{
    id : 'facilityId',
    title : '仪器编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, {
    id : 'taskId',
    title : '任务编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, {
    id : 'entrustName',
    title : '委托单',
    type : 'string',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
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
}, {
    id : CONS_price,
    title : '报价',
    type:'number', 
    format:'#,###.00',
    width:100,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
	id:'operation', 
	title:'操作', 
	type:'string', 
	columnClass:'text-center', 
	resolution:function(value, record, column, grid, dataNo, columnNo){
		var content = '';
		if( record.entrustName == "") {
			content += '<button class="btn btn-xs btn-info" disabled="disabled" onclick="saveExpectPrice();">报价</button>';
		} else {
			//alert(grid.exhibitDatas[dataNo].expectPrice);
			content += '<button class="btn btn-xs btn-info" onclick="saveExpectPrice('+dataNo+');">报价</button>';
		}
		content += '<button style="margin-left: 3px;" class="btn btn-xs btn-success" onclick="showFee(\''+record.facilityId+'\');">资费</button>';
		return content;
	}
}];

var $EditInput = null;
var $NextEditCell = null;
var gDataNo = 0;
var gColumnNo = 0;
var gEditInt = false;

//动态设置jqGrid的rowNum
var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/entrust/entrustFacilityList.html',
    columns : dtGridColumns,
    gridContainer : 'facilityContainer',
    toolbarContainer : 'facilityToolBarContainer',
    tools : '',
    exportFileName :'资源信息',
    pageSize : 20,
    pageSizeLimit : [20, 50],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if($EditInput != null || column.id != CONS_price ) {
    		return;
    	}
    	
    	gDataNo = parseInt(dataNo);
    	gColumnNo = parseInt(columnNo);
    	createInput(cell[0]);
   },
   onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
	   	if(column.id == CONS_price || column.id == "operation") {
    		return;
    	}
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
    grid.parameters = new Object();
    grid.parameters['taskId'] = $("#taskId").val();
    grid.load();
    
  //注册回车键事件
    document.onkeypress = function(e){
        var ev = document.all ? window.event : e;
           if(ev.keyCode==13) {
            	if ($EditInput != null) {
            		$EditInput.blur();
            		console.log("回车键 cell: " + $NextEditCell);
            		
            		if(document.all) {
            			if ($NextEditCell) {
            				gDataNo = gDataNo + 1;
            				createInput($NextEditCell);
            			}        			
            		}
            		// 其它浏览器
            		else {
            			var e = document.createEvent("MouseEvents");
            			e.initEvent("click", true, true);
            			if ($NextEditCell) {
            				gDataNo = gDataNo + 1;
            				createInput($NextEditCell);
            			}   
            		}
            	}
                //customSearch();
            }
        };
});

function entrustFacilityList() {
    grid.parameters = new Object();
    grid.parameters['taskId'] = $("#taskId").val();
    grid.parameters['entrustId'] = $("#entrustId").val();
    grid.refresh(true);
}


function createInput($cell) {
	$row = $cell.parentElement;
	 
	//$cell[0].parentElement.cells[12]
	var $allCells = $row.cells;
	var $totalCell = $allCells[$allCells.length - 1];
	
	//[2]
	var $allRows = $cell.parentElement.parentElement.children;
	var nextRowNo = parseInt(gDataNo) * 2 + 2;
	
	if (nextRowNo < $allRows.length ) {
		$NextEditCell = $allRows[nextRowNo].cells[parseInt(gColumnNo) + 2];
	} else {
		$NextEditCell = null;
	}
	
	var val = 0;
	if(gEditInt) {
		val = parseInt($cell.innerHTML);
	} else {
		val = parseFloat($cell.innerHTML).toFixed(2);
	}
	
	var inputHtml = '<input class="form-control" onfocus="this.select();"  onmouseup="this.select();" style="width: 80px;height: 27px;" id="EditInput333" '
		inputHtml += 'type="number" value = "'+ val + '"/>'; 
	
	$cell.innerHTML = inputHtml;
	$EditInput = $("#EditInput333");
	$EditInput.focus();
	
	$("#EditInput333").unbind('blur').bind('blur', function() {
		//cell.html($("#EditInput333").val());
	    if($EditInput != null) {
	    	var inputVal = $EditInput.val();
	    	$cell.innerHTML = inputVal;
	    	grid.exhibitDatas[gDataNo][dtGridColumns[parseInt(gColumnNo)].id] = inputVal;
	    }
	    
		$EditInput = null;
    });
}

function saveExpectPrice(dataNo){
	$.ajax({
		type : "POST",
		url : sys.rootPath + "/entrust/saveExpectPrice.html",
		data : {
			"expectPrice" : grid.exhibitDatas[dataNo].expectPrice,
			"taskId" : grid.exhibitDatas[dataNo].taskId,
			"facilityId" : grid.exhibitDatas[dataNo].facilityId
		},
		dataType : "json",
		success : function(resultdata) {
			if (resultdata.success) {
				layer.msg(resultdata.message, {
					icon : 1
				});
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
function showFee(facilityId){
	$.ajax({
		type : "POST",
		url : sys.rootPath + "/entrust/showFee.html",
		data : {
			"facilityId" : facilityId
		},
		dataType : "json",
		success : function(resultdata) {
			debugger;
			if (resultdata.success) {
				$("#fee").html('资费标准：'+resultdata.fee+'，上次费用：'+resultdata.lastFee);
			} else {
				layer.msg("资费查询失败", {
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