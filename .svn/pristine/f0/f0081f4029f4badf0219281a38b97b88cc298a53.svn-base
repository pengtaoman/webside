var gridDatas = [];

var CONS_price='price';
var CONS_NOTE='note';

var CONS_expectPrice='expectPrice';
var CONS_amount='amount';
var gTotalFee = 0.0; //parseDouble
var gEditInt = false;
var gEditText = false;


var dtGridColumns = [{
    id : 'id',
    title : '编号',
    width : 60,
    hide : true,
    print: false,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'inquiryId',
    title : '询价编号',
    width : 60,
    hide : true,
    print: false,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
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
    id : 'name',
    title : '计量器具',
    type : 'string',
    columnClass : 'text-center',
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
},  {
    id : CONS_amount,
    title : '数量',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
},  {
    id : CONS_price,
    title : '单价(元)',
    width : 80,
    type : 'number',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'unit',
    title : '收费单位',
    width : 80,
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'totalPrice',
    title : '总价(元)',
    //hide  : true,
    type : 'number',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	if(record.price) {
    		var fee = record.price * record.amount;
    		gTotalFee += fee;
    		//console.log("Init grid:" + gTotalFee);
    		//$("#totalFee").html('<h4>总价：'+gTotalFee.toFixed(2)+'</h4>');
    		return fee.toFixed(2);
    	}
    	return '';
    }
},  {
    id : CONS_NOTE,
    title : '报价备注',
    //hide  : true,
    type : 'string',
    format:'#,###.00',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
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
    datas : gridDatas,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    //loadURL : sys.rootPath + '/fee/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '报价单',
    pageSize : 1000,
    pageSizeLimit : [10, 20, 50, 1000],
    onRowDblClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	InquirySearchPanel.doSearch(record.name, record.field, record.model,record.accuracy);
    },
    onCellClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	if($EditInput != null) {
    		return;
    	}
    	
    	gDataNo = parseInt(dataNo);
    	gColumnNo = parseInt(columnNo);
    	
    	if (column.id == CONS_price) {
        	createInput(cell[0]);
    	} else if (column.id == CONS_NOTE) {
        	createTextInput(cell[0]);
    	}
    	
    	
   }
};

function createTextInput($cell) {
	$row = $cell.parentElement;
	gEditText = true;
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
	
	var val = $cell.innerHTML;
		
	var inputHtml = '<input class="form-control" onfocus="this.select();"  onmouseup="this.select();" style="width: 100%;height: 27px;" id="EditInput333" '
		inputHtml += 'type="text" value = "'+ val + '"/>'; 
	
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

function createInput($cell) {
	$row = $cell.parentElement;
	gEditText = false;
	//$cell[0].parentElement.cells[12]
	var $allCells = $row.cells;
	
	// 取得总价cell
	var $totalCell = $allCells[$allCells.length - 2];
	
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
	    	
	    	var amount = gridDatas[gDataNo][CONS_amount];
	    	$totalCell.innerText = inputVal * amount;
	    }
	    
		$EditInput = null;
    });
}

var grid = new Object();

function inquiryPrint(inquiryId) {
	var data = {id : inquiryId};
	webside.getData("/inquiry/getPrintData.html", data, 
			  function(result) {
				  if(result != null && result.success) {
					  doInquiryPrint(result.data, inquiryId);
					  
				  } else {
					  layer.msg(result.message, {icon : 0}); 
				  }
			  }, 
			  function(result) {
				  layer.msg("无法与服务器通信，请重试或者联系管理员", {icon : 0}); 
			  });
}

function showPrintDailog(data,inquiryId){
	//映射参数
	var gridReflectionObj = grid;
	//如果已经初始化，则调用显示
	if(gridReflectionObj.init.printWindowIsInit){
		$('#dlshouwen_grid_print_'+gridReflectionObj.option.id+'_modal').modal('show');
		return;
	}
	//放置新的打印选项
	var content = '';
	content += $.fn.dlshouwen.grid.tools.getWindowStart('dlshouwen_grid_print_'+gridReflectionObj.option.id, $.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.title);
	content += '				<table class="table table-bordered table-print">';
	content += '					<thead>';
	content += '						<tr>';
	content += '							<th><input type="checkbox" id="dlshouwen_grid_print_check_'+gridReflectionObj.option.id+'" checked="checked" /></th>';
	content += '							<th>'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.column+'</th>';
	content += '							<th>'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.operation+'</th>';
	content += '						</tr>';
	content += '					</thead>';
	content += '					<tbody>';
	//遍历列信息
	for(var i=0; i<gridReflectionObj.option.columns.length; i++){
		var column = gridReflectionObj.option.columns[i];
		if(column.print==false){
			continue;
		}
		//获取记录号
		content += '					<tr id="dlshouwen_grid_print_tr_'+gridReflectionObj.option.id+'_'+i+'">';
		content += '						<td><input type="checkbox" id="dlshouwen_grid_print_check_'+gridReflectionObj.option.id+'_'+i+'" checked="checked" value="'+i+'" /></td>';
		content += '						<td>'+column.title+'</td>';
		content += '						<td>';
		content += '							<button type="button" class="btn btn-primary btn-xs" dataId="'+i+'" id="dlshouwen_grid_print_up_'+gridReflectionObj.option.id+'_'+i+'" title="'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.buttons.upTitle+'">'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.buttons.up+'</button>';
		content += '							<button type="button" class="btn btn-primary btn-xs" dataId="'+i+'" id="dlshouwen_grid_print_down_'+gridReflectionObj.option.id+'_'+i+'" title="'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.buttons.downTitle+'">'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.table.buttons.down+'</button>';
		content += '						</td>';
		content += '					</tr>';
	}
	content += '					</tbody>';
	content += '				</table>';
	var buttons = '';
	buttons += '<button type="button" class="btn btn-primary" id="dlshouwen_grid_print_execute_'+gridReflectionObj.option.id+'">';
	buttons += '	'+$.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].print.buttons.print;
	buttons += '</button>';
	content += $.fn.dlshouwen.grid.tools.getWindowEnd($.fn.dlshouwen.grid.lang[gridReflectionObj.option.lang].buttons.close, buttons);
	$('body').append(content);
	//绑定复选全选反选方法
	$('#dlshouwen_grid_print_check_'+gridReflectionObj.option.id).click(function(){
		$('input[id*=dlshouwen_grid_print_check_'+gridReflectionObj.option.id+'_]').prop('checked', this.checked);
	});
	//绑定上移方法
	$('button[id*=dlshouwen_grid_print_up_'+gridReflectionObj.option.id+'_]').click(function(){
		var dataId = $(this).attr('dataId');
		var gridId = gridReflectionObj.option.id;
		$('#dlshouwen_grid_print_tr_'+gridId+'_'+dataId).prev('#dlshouwen_grid_print_'+gridId+'_modal tr[id*=dlshouwen_grid_print_tr_'+gridId+']').before($('#dlshouwen_grid_print_tr_'+gridId+'_'+dataId));
	});
	//绑定下移方法
	$('button[id*=dlshouwen_grid_print_down_'+gridReflectionObj.option.id+'_]').click(function(){
		var dataId = $(this).attr('dataId');
		var gridId = gridReflectionObj.option.id;
		$('#dlshouwen_grid_print_tr_'+gridId+'_'+dataId).next('#dlshouwen_grid_print_'+gridId+'_modal tr[id*=dlshouwen_grid_print_tr_'+gridId+']').after($('#dlshouwen_grid_print_tr_'+gridId+'_'+dataId));
	});
	//绑定打印方法
	$('#dlshouwen_grid_print_execute_'+gridReflectionObj.option.id).click(function(){
		//画表格
		var content = '';
		content += '<table class="dlshouwen-grid '+gridReflectionObj.option.tableClass+'" id="dlshouwen_grid_print_'+gridReflectionObj.option.id+'" style="'+gridReflectionObj.option.tableStyle+'">';
		if(gridReflectionObj.option.showHeader!=false){
			content += '<thead>';
			content += '	<tr>';
			$('input[id*=dlshouwen_grid_print_check_'+gridReflectionObj.option.id+'_]:checked').each(function(){
				var columnNo = this.value;
				content += '<th class="'+gridReflectionObj.option.columns[columnNo].headerClass+'" style="'+gridReflectionObj.option.columns[columnNo].headerStyle+'">'+gridReflectionObj.option.columns[columnNo].title+'</th>';
			});
			content += '	</tr>';
			content += '</thead>';
		}
		//构建表格
		content += '	<tbody>';
		if(gridReflectionObj.exhibitDatas!=null){
			for(var i=0; i<gridReflectionObj.exhibitDatas.length; i++){
				content += '	<tr>';
				$('input[id*=dlshouwen_grid_print_check_'+gridReflectionObj.option.id+'_]:checked').each(function(){
					var columnNo = this.value;
					content += '	<td class="'+gridReflectionObj.option.columns[columnNo].columnClass+'" style="'+gridReflectionObj.option.columns[columnNo].columnStyle+'">';
					if(gridReflectionObj.option.columns[columnNo].resolution){
						content += gridReflectionObj.option.columns[columnNo].resolution(gridReflectionObj.exhibitDatas[i][gridReflectionObj.option.columns[columnNo].id], gridReflectionObj.exhibitDatas[i], gridReflectionObj.option.columns[columnNo], gridReflectionObj, i, columnNo);
					}else{
						var val22 = gridReflectionObj.exhibitDatas[i][gridReflectionObj.option.columns[columnNo].id];
						if (val22 == 'null' || val22 == undefined) {
							val22 = '';
						}
						content += gridReflectionObj.formatContent(gridReflectionObj.option.columns[columnNo], val22);
					}
					content += '	</td>';
				});
				content += '	</tr>';
			}
		}
		content += '	</tbody>';
		content += '</table>';
		
		data = data.replace('{replaceTable}', content);
		data = data.replace('{{inquiryId}}', inquiryId);
		data = data.replace('{{inquiryCompany}}', $('#inquiryCompanyName').val());      // 询价公司
		data = data.replace('{{inquiryUser}}', $('#inquiryUserName').val());            // 询价员
		data = data.replace('{{inquiryReplyUser}}', $('#inquiryReplyUserName').val());  // 报价员
		data = data.replace('{{inquiryUserTel}}', '');
		data = data.replace('{{inquiryTime}}', $('#inquiryCreateTime').val());
		data = data.replace('{{inquiryReplyPerson}}', '');  // 联系人
		data = data.replace('{{inquiryReplyTel}}', '');     // 联系电话
		
		content = '<div id="printArea223311">' + data  + '</div>';
		
		//隐藏body，放置打印对象
		var scrollTop = $('body').scrollTop();
		$('body').hide();
		$('html').append(content);
		window.print();
		$('#printArea223311').remove();
		$('body').show();
		$('#dlshouwen_grid_print_'+gridReflectionObj.option.id+'_modal').modal('hide');
		$('body').scrollTop(scrollTop);
	});
	//显示打印选项
	$('#dlshouwen_grid_print_'+gridReflectionObj.option.id+'_modal').modal('show');
	//标识初始化完成
	gridReflectionObj.init.printWindowIsInit = true;
}

function doInquiryPrint(data,inquiryId) {
	showPrintDailog(data,inquiryId);return;
		//画表格
		data = data.replace('{replaceTable}', getPrintTable());
		var content = '<div id="printArea223311">' + data  + '</div>';
		
		//隐藏body，放置打印对象
		var scrollTop = $('body').scrollTop();
		$('body').hide();
		
		if($('#printArea223311').length == 0) {
			$('html').append(content);
		} else {
			$('#printArea223311').html(content);
		}
		$('#printArea223311').attr('style','width:756px;border:1px solid #000000;')
		$("#printArea223311").find('div').each(function(){
			$(this).attr('style','width:95%;');
		});
		$("#printArea223311").find('img').each(function(){
			$(this).attr('style','width:95%;');
		});
		$("#printArea223311").find('span').each(function(){
			$(this).attr('style','width:95%;');
		});
		
		window.print();
		$('body').show();
		$('#printArea223311').html('');
		$('#printArea223311').attr('style','display:none');
		$('body').scrollTop(scrollTop);
	}

function inquirySubmit() {
	var entity = { id: $("#inquiryId").val(), userId: $("#inquiryUserId").val(), note: $("#inquiryNote").val()};
	var data={entity : entity, detailsList : gridDatas};			
	webside.postJsonData("/inquiry/updateReply.html", JSON.stringify(data), 
			  function(result) {
				  if(result != null && result.success) {
					  layer.msg("提交成功,跳转至列表页。", {icon : 0});
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
	grid = $.fn.dlshouwen.grid.init(dtGridOption);
    initData();
    console.log("datalen:" + gridDatas.length);
	
    if (gridDatas.length > 0) {
    	grid.load();
    }
    
    
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
            				if(gEditText == false) {
            					createInput($NextEditCell);
            				} else {
            					createTextInput($NextEditCell);
            				}
            				
            			}        			
            		}
            		// 其它浏览器
            		else {
            			var e = document.createEvent("MouseEvents");
            			e.initEvent("click", true, true);
            			if ($NextEditCell) {
            				gDataNo = gDataNo + 1;
            				if(gEditText == false) {
            					createInput($NextEditCell);
            				} else {
            					createTextInput($NextEditCell);
            				}
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
						});
					  grid.load();
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