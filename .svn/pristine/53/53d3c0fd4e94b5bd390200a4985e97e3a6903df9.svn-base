var gridFeeSearchObj = null;
var dtGridColumnsInquirySearchPanel = [ {
    id : 'id',
    title : '编号',
    width : 60,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'code',
    title : '序号',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs|md',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'name',
    title : '器具名',
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
}];

var dtGridOptionSearchPanel = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/fee/list.html',
    columns : dtGridColumnsInquirySearchPanel,
    gridContainer : 'dtGridContainer33',
    toolbarContainer : 'dtGridToolBarContainer33',
    tools : 'refresh',
    pageSize : 3,
    pageSizeLimit : [1,3,5],
    onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	row.children()[1].children[0].checked = !row.children()[1].children[0].checked;
    }
};

var InquirySearchPanel = {
	
		SelectFields : [{ id : "selectField33" , name : "专业", col : "col-sm-1", wd : "padding-right: 0px;width:40px;!important"},
		                { id : "selectType33" , name : "类型", col : "col-sm-2", wd : "padding-right: 0px;width:40px;!important"}, 
		                { id : "selectModel33" , name : "型号", col : "col-sm-2", wd : "padding-right: 0px;width:40px;!important"},
	                { id : "selectAccuracy33" , name : "准确度", col : "col-sm-1", wd : "padding-right: 0px;width:55px;!important"},
	                { id : "selectTestRange33" , name : "测量范围", col : "col-sm-1", wd : "padding-right: 0px;width:70px;!important"}],
			//内部用
    GetPanelHtml : function(addCallback) {
    	var html = '<div class="col-xs-12 widget-container-col ui-sortable"><div class="widget-header">'
       	for (var k = 0, length = InquirySearchPanel.SelectFields.length; k < length; k++) {
       		html += '<div class="col-sm-1" style="margin-top: 5px;'+ InquirySearchPanel.SelectFields[k].wd+ '"><label >';
       		html += InquirySearchPanel.SelectFields[k].name;
       		html += '</label></div><div class="'+ InquirySearchPanel.SelectFields[k].col+ '"><select class="form-control" id="';
       		html += InquirySearchPanel.SelectFields[k].id;
       		html += '"></select></div>';
       	}
    	
       	html = html + '<div class="col-sm-2"><span class="input-group-btn">';
       	html = html + '<button id="btnSearch33" style="margin-left: 5px" class="btn btn-info btn-sm" type="button"> <i class="fa fa-search"></i>&nbsp;搜索</button>';
        html = html + '<button id="btnSearchHide33" style="margin-left: 5px" class="btn btn-success btn-sm" type="button"><i class="fa fa-angle-double-up"></i>&nbsp;收起</button>';
     
        if(addCallback != null) {
        	html = html + '<button id="btnAdd33" style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"><i class="fa fa-plus-square-o"></i>&nbsp;添加</button>';	
        }
      	
        html = html + '</span></div></div>'; 
      	
      	html = html + '<div style="margin-top:0px;margin-bottom:0px;" class="widget-body" style="display: block;">'; 
      	html = html + '<div style="margin-top:0px;margin-bottom:0px;" class="widget-main padding-6 no-padding-left no-padding-right">'; 
      	html = html + '	<input name="pageNum" type="hidden" value="1">'; 
      	html = html + '	<input name="pageSize" type="hidden" value="5">'; 
      	//html = html + '	<input id="orderByColumn33" type="hidden" value="${page.orderByColumn }">'; 
      	//html = html + '	<input id="orderByType33" type="hidden" value="${page.orderByType }">'; 
      	html = html + '	<div id="dtGridContainer33" style="margin-top:0px;margin-bottom:0px;" class="dlshouwen-grid-container"></div>'; 
      	html = html + '	<div id="dtGridToolBarContainer33" style="margin-top:1px;margin-bottom:0px;" class="dlshouwen-grid-toolbar-container"></div>'; 
      	html = html + '</div></div></div>'; 
	
       	return html;
    },
    
    /**
     * 搜索panel包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    InitSearchPanel : function($searchDiv, pageSize, addCallback) {
    	if(pageSize != undefined) {
    		dtGridOptionSearchPanel.pageSize = pageSize;
    	}
    	var gridObj = $.fn.dlshouwen.grid.init(dtGridOptionSearchPanel);
    	gridFeeSearchObj = gridObj;
    	$searchDiv.html(InquirySearchPanel.GetPanelHtml(addCallback));

    	gridObj.load();
    	
    	$("#selectField33").select2();// 初始化控件，只有初始化绑定其它事件才生效
    	$("#selectType33").select2();// 初始化控件，只有初始化绑定其它事件才生效
    	$("#selectModel33").select2();// 初始化控件，只有初始化绑定其它事件才生效
    	$("#selectAccuracy33").select2();// 初始化控件，只有初始化绑定其它事件才生效
    	$("#selectTestRange33").select2();// 初始化控件，只有初始化绑定其它事件才生效
    	var data={};			
		webside.getData("/fee/listFields", data, 
				  function(result) {
					  if(result != null && result.success) {
						selectList1 = result;
						$("#selectField33").select2({
						  data: result.data,
						  placeholder:'请选择',
						  allowClear:true
						});	
						$("#selectField33").val(null).trigger("change");
						$("#selectType33").val(null).trigger("change");
						$("#selectModel33").val(null).trigger("change");
						$("#selectAccuracy33").val(null).trigger("change");
						$("#selectTestRange33").val(null).trigger("change");
					  } else {
						  layer.msg(result.message); 
					  }
				  }, 
				  function(result) {
					  layer.msg("无法与服务器通信，请重试或者联系管理员"); 
				  });		
  	
		$("#selectField33").on("select2:select", function (e) { 
			var data={ 
					"field":$("#selectField33").val()
					};
			//得到value
	  	  	webside.getData("/fee/listFacilityNames", data, 
	  			  function(result) {
	  	  			if(result.success) {
	  					$("#selectType33").select2('destroy').empty();  
	  					$("#selectType33").select2({
	  					  data: result.data,
	  					  placeholder:'请选择',
	  					  allowClear:true
	  					});
						$("#selectType33").val(null).trigger("change");
						$("#selectModel33").val(null).trigger("change");
						$("#selectAccuracy33").val(null).trigger("change");
						$("#selectTestRange33").val(null).trigger("change");
	  				  } else {
	  					  layer.msg(result.message); 
	  				  }
	  			  }, 
	  			  function(result) {
	  				  layer.msg("/fee/test出错，请重试或者联系管理员"); 
	  		 });
		});
		
		$("#selectType33").on("select2:select", function (e) { 
			var data={ 
						"field":$("#selectField33").val(),
						"fname":$("#selectType33").val(),
					};
			//得到value
	  	  	webside.getData("/fee/listModels", data, 
	  			  function(result) {
	  	  			if(result.success) {
	  					$("#selectModel33").select2('destroy').empty();  
	  					$("#selectModel33").select2({
	  					  data: result.data,
	  					  placeholder:'请选择',
	  					  allowClear:true
	  					});
						$("#selectModel33").val(null).trigger("change");
						$("#selectAccuracy33").val(null).trigger("change");
						$("#selectTestRange33").val(null).trigger("change");
	  				  } else {
	  					  layer.msg(result.message); 
	  				  }
	  			  }, 
	  			  function(result) {
	  				  layer.msg("/fee/test出错，请重试或者联系管理员"); 
	  		 });
		});
		
		$("#selectModel33").on("select2:select", function (e) { 
			var data={ 
						"field":$("#selectField33").val(),
						"fname":$("#selectType33").val(),
						"model":$("#selectModel33").val(),
					};
			//得到value
	  	  	webside.getData("/fee/listAccuracys", data, 
	  			  function(result) {
	  	  			if(result.success) {
	  					$("#selectAccuracy33").select2('destroy').empty();  
	  					$("#selectAccuracy33").select2({
	  					  data: result.data,
	  					  placeholder:'请选择',
	  					  allowClear:true
	  					});
						$("#selectAccuracy33").val(null).trigger("change");
						$("#selectTestRange33").val(null).trigger("change");
	  				  } else {
	  					  layer.msg(result.message); 
	  				  }
	  			  }, 
	  			  function(result) {
	  				  layer.msg("/fee/test出错，请重试或者联系管理员"); 
	  		 });
		});
		
		$("#selectAccuracy33").on("select2:select", function (e) { 
			var data={ 
						"field":$("#selectField33").val(),
						"fname":$("#selectType33").val(),
						"model":$("#selectModel33").val(),
						"accuracy":$("#selectAccuracy33").val(),
					};
			//得到value
	  	  	webside.getData("/fee/listTestRanges", data, 
	  			  function(result) {
	  	  			if(result.success) {
	  					$("#selectTestRange33").select2('destroy').empty();  
	  					$("#selectTestRange33").select2({
	  					  data: result.data,
	  					  placeholder:'请选择',
	  					  allowClear:true
	  					});
	  					//$("#selectTestRange").trigger('change.select2');
						$("#selectTestRange33").val(null).trigger("change");
	  				  } else {
	  					  layer.msg(result.message); 
	  				  }
	  			  }, 
	  			  function(result) {
	  				  layer.msg("/fee/test出错，请重试或者联系管理员"); 
	  		 });
		});
    	
    	 $("#btnSearch33").click(function(){
    		 if(gridObj != undefined) {
    			 InquirySearchPanel.ClickSearch(gridObj);
    			 if($("#btnSearchHide33").html().indexOf('展开') > 0) {
    				 $("#btnSearchHide33").html('<i class="fa fa-angle-double-up"></i>&nbsp;收起');
        			 $("#dtGridContainer33").attr('style', 'display:block');
        			 $("#dtGridToolBarContainer33").attr('style', 'display:block');
        		 } 
    		 } 
    	 });
    	 $("#btnSearchHide33").click(function(){
    		 
    		 if($("#btnSearchHide33").html().indexOf('收起') > 0) {
    			 $("#btnSearchHide33").html('<i class="fa fa-angle-double-down"></i>&nbsp;展开');
    			 
    			 $("#dtGridContainer33").attr('style', 'display:none');
    			 $("#dtGridToolBarContainer33").attr('style', 'display:none');
    		 } else {
    			 $("#btnSearchHide33").html('<i class="fa fa-angle-double-up"></i>&nbsp;收起');
    			 $("#dtGridContainer33").attr('style', 'display:block');
    			 $("#dtGridToolBarContainer33").attr('style', 'display:block');
    		 }
    	 });
    	 $("#btnAdd33").click(function(){
             if (addCallback) {
            	 addCallback(gridObj.getCheckedRecords());
             }
    	 });
    	 
    },
    
    doSearch : function(name, field, model, accuracy) {
    	if(gridFeeSearchObj == null) {
    		return;
    	}
    	
    	gridFeeSearchObj.parameters = new Object();
    	if(field != null) {
    		gridFeeSearchObj.parameters["field"] = field;
    	}
    	if(name != null) {
    		gridFeeSearchObj.parameters["name"] = name;
    	}
    	if(model != null) {
    		gridFeeSearchObj.parameters["model"] = model;
    	}
    	if(accuracy != null) {
    		gridFeeSearchObj.parameters["accuracy"] = accuracy;
    	}
		
    	gridFeeSearchObj.parameters["query_expr"] = '=';
			
    	gridFeeSearchObj.refresh(true);
    },
  //内部用
    ClickSearch : function(grid) {
    	grid.parameters = new Object();
    	
    	var field = $("#selectField33").val();
    	var fname = $("#selectType33").val();
    	var model = $("#selectModel33").val();
    	var accuracy = $("#selectAccuracy33").val();
    	var testRange = $("#selectTestRange33").val();
		
    	if(field != null) {
    		grid.parameters["field"] = field;
    	}
    	if(fname != null) {
    		grid.parameters["name"] = fname;
    	}
    	if(model != null) {
    		grid.parameters["model"] = model;
    	}
    	if(accuracy != null) {
    		grid.parameters["accuracy"] = accuracy;
    	}
    	if(testRange != null) {
    		grid.parameters["testRange"] = testRange;
    	}
		
		
		grid.parameters["query_expr"] = '=';
			
		grid.refresh(true);
    }
}