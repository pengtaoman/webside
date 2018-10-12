var dtGridColumns = [{
    id : 'facilityId',
    title : '仪器编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: true
}, /*{
    id : 'taskId',
    title : '任务编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	hide: true    	
},*//*{
    id : 'entrustId',
    title : '委托单编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	hide: true    	
},{
    id : 'cid',
    title : '企业编号',
    type : 'number',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
},  */{
    id : 'facilityName',
    title : '仪器名称',
    type : 'string',
    search : true,
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
    id : 'testRange',
    title : '测量范围',
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
    id : 'factoryNo',
    title : '出厂编号',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'manageNo',
    title : '设备编号',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'usePlace',
    title : '安装/使用地点',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'function',
    title : '计量器具用途',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'belongOrgan',
    title : '制造单位',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'note',
    title : '备注',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'testResult',
    title : '检测结果',
    type : 'string',
    search : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '0') {
            return '<span class="label label-sm label-info arrowed arrowed-righ">未检测</span>';
        } else if (value == '1') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">合格</span>';
        } else {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">不合格</span>';
        } 
    }
}, {
    id : 'expectPrice',
    title : '报价',
    type:'number', 
    format:'#,###.00',
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
    loadURL : sys.rootPath + '/entrustMgr/entrustFacilityList.html',
    columns : dtGridColumns,
    gridContainer : 'facilityContainer',
    toolbarContainer : 'facilityToolBarContainer',
    tools : 'refresh|export[excel]',
    exportFileName :'资源信息',
    pageSize : 20,
    pageSizeLimit : [20]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    grid.parameters = new Object();
    grid.parameters['taskId'] = $("#taskId").val();
    grid.parameters['entrustId'] = $("#entrustId").val();
    grid.load();
    
});

function entrustFacilityList(entrustId, taskId, entrustName,ifEnforce) {
	$("#entrustId").val(entrustId);
	$("#entrustName").val(entrustName);
	$("#ifEnforce").val(ifEnforce);
    grid.parameters = new Object();
    grid.parameters['taskId'] = $("#taskId").val();
    grid.parameters['entrustId'] = $("#entrustId").val();
    grid.refresh(true);
    $("#info").html("公司名称："+$("#companyName").val()+"&nbsp; &nbsp; &nbsp;当前展示委托单名称："+$("#entrustName").val()+"&nbsp; &nbsp; &nbsp;编号："+ $("#entrustId").val());
}

function refreshGrid() {
    grid.parameters = new Object();
    grid.parameters['taskId'] = $("#taskId").val();
    grid.parameters['entrustId'] = $("#entrustId").val();
    grid.refresh(true);
}

function test(){
	alert($("#aaa").val());
}



/////////////////////////////////////委托单表格///////////////////////////////////////////////


var dtGridColumnsTD = [{
    id : 'id',
    title : '委托单编号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md|lg',
	headerClass : 'dlshouwen-grid-header',
	hide: false
}, {
    id : 'name',
    title : '委托单名称',
    type : 'string',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a href="javascript:void(0);" onclick="entrustFacilityList(\''+record.id+'\',\''+record.taskId+'\',\''+record.name+'\',\''+record.ifEnforce+'\');">'+ value +'</a>';
    }
}, {
    id : 'taskId',
    title : '任务编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	hide: true    	
}, {
    id : 'taskName',
    title : '任务名称',
    type : 'number',
    columnClass : 'text-center',
	headerClass : 'dlshouwen-grid-header'
}, {
    id : 'cid',
    title : '公司编号',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	hide: true    	
}, {
    id : 'companyName',
    title : '公司名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg',
	hide: false
}, {
    id : 'organ_id',
    title : '检测机构编号',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
	hide: true    	
}, {
    id : 'organName',
    title : '检测机构',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
}, {
    id : 'contacter',
    title : '联系人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'contactPhone',
    title : '联系电话',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'contactAddress',
    title : '联系地址',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'postcode',
    title : '邮政编码',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'proposer',
    title : '申请人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'receiver',
    title : '领取人',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'attachment',
    title : '附件',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a href="'+sys.rootPath+'/file/downloadEntrustFile.html?name='+value+'" >'+ value +'</a>';
    }
}, {
    id : 'ifEnforce',
    title : '是否强检',
    type : 'string',
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
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '0') {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">进行中</span>';
        } else {
            return '<span class="label label-sm label-success arrowed arrowed-righ">完检</span>';
        }
    }
}, {
    id : 'importDate',
    title : '填报日期',
    type : 'date',
    columnClass : 'text-center',
    search : true,
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'proposeDate',
    title : '委托日期(申)',
    type : 'date',
    columnClass : 'text-center',
    search : true,
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'receiveDate',
    title : '领取日期(领)',
    type : 'date',
    columnClass : 'text-center',
    search : true,
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'updateTime',
    title : '最近更新时间',
    type : 'date',
    columnClass : 'text-center',
    search : true,
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
	id:'operation', 
	title:'操作', 
	type:'string', 
	columnClass:'text-center', 
	resolution:function(value, record, column, grid, dataNo, columnNo){
		var content = '';
	    content += '<button class="btn btn-xs btn-primary" onclick="showEntrust(\''+record.id+'\',\''+record.taskId+'\',\''+record.cid+'\');">修改</button>';
		//content += '<button style="margin-left: 3px;" class="btn btn-xs btn-success" onclick="entrustPrint(\''+record.id+'\',\''+record.ifEnforce+'\');">打印</button>';
		content += '<button style="margin-left: 3px;"class="btn btn-xs btn-primary" onclick="over(\''+record.id+'\');">完检更新</button>';/*
		content += '<select class="form-control" style="height:30px;width:90px">';
			content += '<option value="0">未检测</option><option value="1">合格</option><option value="2">不合格</option></select>';*/
		return content;
	}
}];

var dtGridOptionTD = {
    lang : 'zh-cn',
    ajaxLoad : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/entrustMgr/taskEntrustList.html',
    columns : dtGridColumnsTD,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName :'委托单',
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
    gridTD.parameters['taskId'] = $("#taskId").val();
    gridTD.load();
});

function taskDetailSearch() {
	gridTD.parameters = new Object();
	gridTD.parameters['taskId'] = $("#taskId").val();
	gridTD.refresh(true);
}

function closeTask() {
	 var index = parent.layer.getFrameIndex(window.name);
	 parent.customSearch();
     //再执行关闭
     parent.layer.close(index);
}

function saveTestResult() {
	var rows = grid.getCheckedRecords();
	if (rows.length >= 1) {
		var entrustId = $("#entrustId").val();
		var testResult = $("#testResultSelect").val();
		var facilityIds = [];
		$.each(rows, function(index, value) {
			facilityIds.push(this.facilityId);
		});
		$.ajax({
			type : "POST",
			url : sys.rootPath + '/entrustMgr/saveTestResult.html',
			data : {
				"facilityIds" : facilityIds.join(','),
				"testResult" : testResult,
				"entrustId" : entrustId
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					layer.msg(resultdata.message, {
						icon : 0
					});
					refreshGrid();
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
function showEntrust(entrustId,taskId,cid) {
    layer.open({
    	  type: 2,
    	  title: '委托单信息',
    	  shadeClose: true,
    	  shade: 0.8,
    	  area: ['1000px', '100%'],
    	  content : sys.rootPath + '/entrustMgr/newEntrustUI.html?taskId='+taskId+'&cid='+cid+'&entrustId='+entrustId
    	}); 
}
function over(entrustId) {
	$.ajax({
		type : "POST",
		url : sys.rootPath + '/entrustMgr/saveEntrustStatus.html',
		data : {
			"entrustId" : entrustId
		},
		dataType : "json",
		success : function(resultdata) {
			if (resultdata.success) {
				layer.msg(resultdata.message, {
					icon : 0
				});
				taskDetailSearch();
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


function entrustPrint() {
	// TODO 根据id判定使用哪个模板  EntrustTemplate  or  EntrustforceTemplate
	var entrustId = $("#entrustId").val();
	var ifEnforce = $("#ifEnforce").val();
	var kindTmpl = '';
	if (ifEnforce == '1') {
		kindTmpl = 'EntrustforceTemplate';
	} else {
		kindTmpl = 'EntrustTemplate';
	}
	
	$.ajax({
		type : "POST",
		url : sys.rootPath + '/entrustMgr/getPrintData.html',
		data : {
			"id" : kindTmpl
		},
		dataType : "json",
		success : function(result) {
			if (result.success) {
				showPrintDailog(result.data);
			} else {
				layer.msg(result.message, {
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
var gTemplData = "";
function showPrintDailog(templData){
	gTemplData = templData;
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
						content += gridReflectionObj.formatContent(gridReflectionObj.option.columns[columnNo], gridReflectionObj.exhibitDatas[i][gridReflectionObj.option.columns[columnNo].id]);
					}
					content += '	</td>';
				});
				content += '	</tr>';
			}
		}
		content += '	</tbody>';
		content += '</table>';
		
		var abcd = gTemplData.replace('{replaceTable}', content);
		content = '<div id="printArea223311">' + abcd  + '</div>';
		
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