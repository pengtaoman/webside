var addDataIds = [];
var gridDatas = [];
var CONS_price='price';
var CONS_expectPrice='expectPrice';
var CONS_amount='amount';
var gTotalFee = 0.0; //parseDouble
var gEditInt = false;
var gLastRow = 0;

var dtGridColumns = [{
    id : 'idSeq',
    title : '序号',
    width : 60,
    //hide : true,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {    	
    	return parseInt(dataNo) + 1;
    }
},{
    id : 'id',
    title : '编号',
    width : 60,
    //hide : true,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'inquiryId',
    title : '询价编号',
    width : 60,
    hide : true,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'name',
    title : '计量器具',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'field',
    title : '所属专业',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'model',
    title : '规格型号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'accuracy',
    title : '准确度等级',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'testRange',
    title : '测量范围',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},   {
    id : 'standPrice',
    title : '上次报价',
    width : 80,
    type : 'number',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'expectPrice',
    title : '期望费用',
    type : 'number',
    width : 80,
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
},  {
    id : CONS_amount,
    title : '数量',
    type : 'number',
    width : 80,
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
},  {
    id : CONS_price,
    title : '报价(元)',
    //hide  : true,
    type : 'number',
    width : 80,
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
},  {
    id : 'unit',
    title : '收费单位',
    width : 80,
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totalPrice',
    title : '总价(元)',
    //hide  : true,
    type : 'number',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(dataNo == 0) {
    		gTotalFee = 0.0;
    		gLastRow = -1;
    	}
    	
    	if(record.price && gLastRow != dataNo) {
    		gLastRow = dataNo;
    		var fee = record.price * record.amount;
    		gTotalFee += fee;
    		console.log(dataNo + " -> total: " + gTotalFee);
    		$("#totalFee").html('<h4>总价：'+gTotalFee.toFixed(2)+'</h4>');
    		return fee.toFixed(2);
    	}
    	return '';
    }
},  {
    id : 'note',
    title : '报价备注',
    //hide  : true,
    type : 'string',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
}
,  {
    id : 'operate',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a class="label label-sm label-danger" href="javascript:void(0);" onclick="removeRecord(\''+ record.id +'\')">删除</a>';
    	//return '<a class="label label-sm label-success">'+ 删除 + '</span>';
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var $EditInput = null;
var $NextEditCell = null;
var gDataNo = 0;
var gColumnNo = 0;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : false,
    exportURL: sys.rootPath + '/inquiry/listDetails.html',
    datas : gridDatas,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    //loadURL : sys.rootPath + '/fee/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print',
    exportFileName : '报价单',
    pageSize : 1000,
    pageSizeLimit : [10, 20, 50, 1000],
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if($EditInput != null || column.id != CONS_expectPrice &&  column.id != CONS_amount) {
    		return;
    	}
    	
    	if(column.id == CONS_amount) {
    		gEditInt = true;
    	} else {
    		gEditInt = false;
    	}
    	
    	gDataNo = parseInt(dataNo);
    	gColumnNo = parseInt(columnNo);
    	
    	createInput(cell[0]);
   }
};


function createInput($cell) {
	$row = $cell.parentElement;
	 
	//$cell[0].parentElement.cells[12]
	var $allCells = $row.cells;
	var $totalCell = $allCells[$allCells.length - 1];
	
	//[2]
	var $allRows = $cell.parentElement.parentElement.children;
	var nextRowNo = parseInt(gDataNo) * 2 + 2;
	
	if (nextRowNo < $allRows.length ) {
		$NextEditCell = $allRows[nextRowNo].cells[gColumnNo + 1];
	} else {
		$NextEditCell = null;
	}
	
	var val = 0;
	if(gEditInt) {
		val = parseInt($cell.innerHTML);
	} else {
		val = parseFloat($cell.innerHTML).toFixed(2);
	}
	
	var inputHtml = '<input class="form-control" onfocus="this.select();"  onmouseup="this.select();" style="width: 100%;height: 27px;" id="EditInput333" '
		inputHtml += 'type="number" value = "'+ val + '"/>'; 
	
	$cell.innerHTML = inputHtml;
	$EditInput = $("#EditInput333");
	$EditInput.focus();
	
	$("#EditInput333").unbind('blur').bind('blur', function() {
		//cell.html($("#EditInput333").val());
	    if($EditInput != null) {
	    	var inputVal = $EditInput.val();
	    	$cell.innerHTML = inputVal;
	    	gridDatas[gDataNo][dtGridColumns[gColumnNo].id] = inputVal;
	    	grid.originalDatas[gDataNo][dtGridColumns[gColumnNo].id] = inputVal;
	    }
	    
		$EditInput = null;
    });
}

var grid = new Object();

function removeAddDataIds() {
	var idx = -1;
	$.each(addDataIds, function(index, value) {
		if(rowId == value) {
			idx = index;
			return;
		}		
	});
}

function removeRecord(rowId) {

	var i = addDataIds.length - 1;
	for(; i >= 0; i--) {
		if(addDataIds[i] == rowId) {
			break;
		}
	}
	if(i >= 0) {
		addDataIds.splice(i, 1);	
	}
	
	i = gridDatas.length - 1;
	for(; i >= 0; i--) {
		if(gridDatas[i].id == rowId) {
			break;
		}
	}
	
	if(i >= 0) {
		gridDatas.splice(i, 1);	
	}
	
	grid.originalDatas = gridDatas;
	grid.refresh(true);
	$("#facilityCountSpan").html(gridDatas.length);
}

function addInquiryClick(rows) {
	if(rows.length == 0) {
		return;
	}
	var inquiryId = $("#inquiryId").val();
	localStorage.xxpageSize = grid.pager.pageSize;
	console.log(localStorage.xxpageSize);
	
	var cnt = 0;
	var cntSame = 0;
	$.each(rows, function(index, value) {
		var row = {};
		var str = "" +this.id;
		if(str.indexOf('保存') == -1 && addDataIds.indexOf(str) == -1) {
			addDataIds.push(str);
			row.id = str;
			row.inquiryId = inquiryId;
			row.name = this.facilityName;
			row.field = this.facilityType;
			row.model = this.model;
			row.accuracy = this.accuracy;
			row.unit = this.feeUnit;
			row.standPrice = this.lastRealPrice;
			if (this.lastExpectPrice == null || parseInt(this.lastExpectPrice) == 0) {
				row.expectPrice = this.lastRealPrice;
			} else {
				row.expectPrice = this.lastExpectPrice;
			}
			
			if (this.lastRealPrice != null && parseInt(this.lastRealPrice) > 0) {
				row[CONS_price] = this.lastRealPrice;
			} 
			
			row.amount = 1;
			gridDatas.push(row);
			//grid.originalDatas.push(row);
			cnt = cnt + 1;
		} else {
			cntSame = cntSame +1;
		}
		
    });
	
	if (cnt == 0) {
		layer.msg("未能添加到询价单，原因：选择的未保存、保存出错或者已经添加。", {icon : 1}); 
		return;
	} else {
		if (cntSame > 0) {
			layer.msg("已经添加"+cnt+"条到询价单列表中，已去除错误或者重复器具，数量："+cntSame, {icon : 1}); 
		} else {
			layer.msg("已经添加"+cnt+"条到询价单列表中", {icon : 1}); 
		}
	}
	
	grid.refresh(true);
	$("#facilityCountSpan").html(gridDatas.length);
	
	var tabChange = "yes";
//	var tabChange = localStorage.inquiryTabChange;
//
//	if(tabChange == null) {
//		layer.confirm('是否自动切换到询价单？', {
//			  btn: ['是','不是'] //按钮
//			}, function(){
//				localStorage.inquiryTabChange = 'yes';
//			}, function(){
//				localStorage.inquiryTabChange = 'no';
//			});
//	}
	
	if(tabChange == 'yes') {
		$('#myTab a[href="#inquiryTab"]').tab('show');
	}
}

function inquirySubmit() {
	var entity = { id: $("#inquiryId").val(), note: $("#inquiryNote").val()};
	var data={entity : entity, detailsList : gridDatas};
	
	if (gridDatas.length <= 0) {
		layer.msg("未添加询价器具", {icon : 0}); 
		return;
	}
	
	webside.postJsonData("/inquiry/update.html", JSON.stringify(data), 
			  function(result) {
				  if(result != null && result.success) {
					  layer.msg("提交成功,跳转至列表页。", {icon : 1});
					  webside.common.loadPage("/inquiry/listUI.html");
				  } else {
					  layer.msg(result.message, {icon : 0}); 
				  }
			  }, 
			  function(result) {
				  layer.msg("无法与服务器通信，请重试或者联系管理员", {icon : 0}); 
			  });
}

$(function() {
	console.log("init.pagesize:" + localStorage.xxpageSize);
	if (localStorage.xxpageSize && localStorage.xxpageSize > 0)
	{
		dtGridOption.pageSize = localStorage.xxpageSize;
	}
	
	// 如果未报价状态，隐藏报价和总价列
	var status = $("#status").val();
	
	if(status == "" || status == "0") {
		var dtLen = dtGridColumns.length;
		dtGridColumns[dtLen - 2].hide  = true;
		dtGridColumns[dtLen - 3].hide  = true;
	} 
	
	if (!Array.removeAt) {
		Array.prototype.removeAt = function(pos) {
		if(pos<0)	return this;
		else
			return this.slice(0,pos).concat(this.slice(pos+1,this.length));
		}
	}
	if (!Array.removeId) {
		Array.prototype.removeId = function(id) {
			var newDatas = [];
			$.each(this, function(index, value) {
				if(!this.id){ 
					if(this != id) {
						newDatas.push(this);
					}
				}
				else if(this.id != id) {
					newDatas.push(this);
				}
			});
			return newDatas;
		}
	}
//	if (!Array.indexOf) {
//		  Array.prototype.indexOf = function (obj) {
//		    for (var i = 0; i < this.length; i++) {
//		      if (this[i] == obj) {
//		        return i;
//		      }
//		    }
//		    return -1;
//		  }
//		}
//	
    initData();
	grid = $.fn.dlshouwen.grid.init(dtGridOption);
	grid.originalDatas = gridDatas;
	
	var inquiryId = $("#inquiryId").val();
	if(inquiryId != null && inquiryId != '') {
		grid.parameters = new Object();
		grid.parameters['id'] = inquiryId;
	}
	
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

function initData() {
	var inquiryId = $("#inquiryId").val();
	
	if(inquiryId == null) {
		return;
	}
	
	var params={id : inquiryId};			
	webside.getData("/inquiry/getDetails.html", params, 
			  function(result) {
				  if(result != null && result.success) {
					  $.each(result.data, function(index, value) {
						  gridDatas.push(this);
						  addDataIds.push(this.id);
						});
					  
					  grid.refresh(true);
					  $("#facilityCountSpan").html(gridDatas.length);
				  } else {
					  layer.msg(result.message, {icon : 0}); 
				  }
			  }, 
			  function(result) {
				  layer.msg("无法与服务器通信，请重试或者联系管理员", {icon : 0}); 
			  });
}

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
	//SearchPanel.refreshGrid(grid);
}


function onReturnClick() {
	webside.common.loadPage("/inquiry/listUI.html");
}