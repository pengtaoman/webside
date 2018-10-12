var dtGridColumnsInquirySearchPanel = [ {
    id : 'id',
    title : '编号',
    width : 60,
    hideType : 'sm|xs|md',
    type : 'string',
    hide : true,
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
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
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'facilityType',
    title : '仪器类型',
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
    	return '<span class="label label-sm label-info arrowed arrowed-righ">'+value+'</span>';
    }/*,
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	//仪器状态（新购入 0、正常 1、不合格 2、维修 3、封存 4、报废 5、借出 6、调修 7）
        if (value == 0) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">新购入</span>';
        } else if (value == 1) {
            return '<span class="label label-sm label-info arrowed arrowed-righ">正常</span>';
        }  else if (value == 2) {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">不合格</span>';
        }  else if (value == 3) {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">维修</span>';
        }  else if (value == 4) {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">封存</span>';
        }  else if (value == 5) {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">报废</span>';
        }  else if (value == 6) {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">借出</span>';
        }  else if (value == 7) {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">调修</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">未知</span>';
        }
    }*/
}, {
    id : 'ifTest',
    title : '是否检测',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 1) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">已检测</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">未检测</span>';
        }
    }
}, {
    id : 'lastExpectPrice',
    title : '报价',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg',
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
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg',
}, {
    id : 'price',
    title : '价格',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg',
},  {
    id : 'localSource',
    title : '采购地',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
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
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}, {
    id : 'note',
    title : '备注',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}];

var dtGridOptionSearchPanel = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/facility/list.html',
    columns : dtGridColumnsInquirySearchPanel,
    gridContainer : 'dtGridContainer33',
    toolbarContainer : 'dtGridToolBarContainer33',
    tools : 'refresh',
    pageSize : 10,
    pageSizeLimit : [10,20,50,1000],
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
	     exprNumber : [{id : ">", text:">"},  {id : "<", text:"<"}, {id : "=", text:"="}, {id : "<>", text:"!="},{id : ">=", text:">="}, {id : "<=", text:"<="}],
	     exprString : [{id : "=", text:"等于"}, {id : "<>", text:"不等于"}, {id : "like", text:"包含"}, {id : "not like", text:"不包含"}],
	        			//内部用
	     GetNormalSearchHtml : function() {
	        var html = '<div class="col-sm-1" style="margin-top: 5px;padding-right: 0px;width:70px;!important"><label>搜索字段</label></div>'
	        	       	 html = html + '<div class="col-sm-2"><select style="width:100%;" class="form-control" id="fieldSelect111" ></select></div>';
	        	       	 html = html + '<div id="divExprSelect111" class="col-sm-2" ><select  style="width:100%;" class="form-control" id="exprSelect111" ></select></div>';
	        	       	 html = html + '<div id="divSearchValueHidden" class="col-sm-1" style="display:none;" ><label>到</label></div>';
	        	       	 html = html + '<div id="divSearchValue" class="col-sm-4"><input id="searchText111" style="width:100%; type="text" class="input form-control" placeholder="请输入"></div>';
	        	       	 return html;
	        	    },
	     GetAdvanceSearchHtml : function() {
		       	for (var k = 0, length = InquirySearchPanel.SelectFields.length; k < length; k++) {
		    		html += '<div class="col-sm-1" style="margin-top: 5px;'+ InquirySearchPanel.SelectFields[k].wd+ '"><label >';
		    		html += InquirySearchPanel.SelectFields[k].name;
		    		html += '</label></div><div class="'+ InquirySearchPanel.SelectFields[k].col+ '"><select class="form-control" id="';
		    		html += InquirySearchPanel.SelectFields[k].id;
		    		html += '"></select></div>';
		       	} 	 
		       	return html;
	     },
	     GetGridHtml : function() {
	    	 var html = '<div style="margin-top:0px;margin-bottom:0px;" class="widget-body" style="display: block;">'; 
		       	html = html + '<div style="margin-top:0px;margin-bottom:0px;" class="widget-main padding-6 no-padding-left no-padding-right">'; 
		       	html = html + '	<input name="pageNum" type="hidden" value="1">'; 
		       	html = html + '	<input name="pageSize" type="hidden" value="10">'; 
		       	html = html + '	<div id="dtGridContainer33" style="margin-top:0px;margin-bottom:0px;" class="dlshouwen-grid-container"></div>'; 
		       	html = html + '	<div id="dtGridToolBarContainer33" style="margin-top:1px;margin-bottom:0px;" class="dlshouwen-grid-toolbar-container"></div>'; 
		       	html = html + '</div></div>'; 
	       	return html;
		 },   	    
	    GetPanelHtml : function(addCallback) {
	    	var html = '<div class="col-xs-12 widget-container-col ui-sortable"><div class="widget-header">';
	    	html = html + InquirySearchPanel.GetNormalSearchHtml();
	    	//html = html + InquirySearchPanel.GetAdvanceSearchHtml();
	    	
	    	// 按钮组html
	       	html = html + '<div class="col-sm-2"><span class="input-group-btn">';
	       	html = html + '<button id="btnSearch33" style="margin-left: 5px" class="btn btn-info btn-sm" type="button"> <i class="fa fa-search"></i>&nbsp;搜索</button>';
	        html = html + '<button id="btnSearchHide33" style="margin-left: 5px" class="btn btn-success btn-sm" type="button"><i class="fa fa-angle-double-up"></i>&nbsp;收起</button>';
	     
	        if(addCallback != null) {
	        	html = html + '<button id="btnAdd33" style="margin-left: 5px" class="btn btn-warning btn-sm" type="button"><i class="fa fa-plus-square-o"></i>&nbsp;添加</button>';	
	        }
	        html = html + '</span></div></div>'; 
	      	
	        // grid组html
	        html = html + InquirySearchPanel.GetGridHtml();
		
	       	return html + '</div>';
	    },
    
    /**
     * 搜索panel包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    InitSearchPanel : function($searchDiv, pageSize, addCallback) {
    	if(pageSize != undefined && pageSize > 0) {
    		dtGridOptionSearchPanel.pageSize = pageSize;
    	} else {
    		if (localStorage.InquirySearchPanelpageSize && localStorage.InquirySearchPanelpageSize > 0)
    		{
    			dtGridOptionSearchPanel.pageSize = localStorage.InquirySearchPanelpageSize;
    		}
    	}
    	
    	var gridObj = $.fn.dlshouwen.grid.init(dtGridOptionSearchPanel);
    	
    	$searchDiv.html(InquirySearchPanel.GetPanelHtml(addCallback));

    	gridObj.load();
    	
    	InquirySearchPanel.InitNormalSearchHtml(dtGridColumnsInquirySearchPanel);
    	
    	 $("#btnSearch33").click(function(){
    		 if(gridObj != undefined) {
    			 InquirySearchPanel.ClickSearch(gridObj, true);
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
    
  //内部用
    refreshGrid : function(grid) {
    	InquirySearchPanel.ClickSearch(grid, true);
    },
    
    //内部用
    ClickSearch : function(grid, always) {
    	
    	localStorage.InquirySearchPanelpageSize = grid.pager.pageSize;
    	
    	grid.parameters = new Object();
    	
    	var field = $("#fieldSelect111").val();
		var expr = $("#exprSelect111").val();
		
		 console.log(field + ' ' +expr);
		
		if(field == null && always == false) {
			return;
		}
		 
		 if(expr == null) {
			 var startDate = $("#idDateStart").val();
				var endDate = $("#idDateEnd").val();		
	
				if(startDate != undefined && startDate != '') {
					grid.parameters["startDate"] = startDate;
				} 
				
				if(endDate != undefined && endDate != '') {
					grid.parameters["endDate"] = endDate;
				}
				grid.parameters[$("#fieldSelect111").val()] = '1';
			} else {
				var searchVal = $("#searchText111").val();
				
				grid.parameters[$("#fieldSelect111").val()] = searchVal;
				grid.parameters["query_expr"] = expr;
			}	
			
		 grid.refresh(true);
    },
    
  //内部用
    ClickAdvanceSearch : function(grid) {
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
    },
    
    InitNormalSearchHtml : function(gridColumns) {
    	var searchFieldColumn = [];
    	gridColumns.forEach(function(fieldObj, index){
    		if(fieldObj.search != undefined) {
    			if (fieldObj != undefined) {
    				if(fieldObj.text  != undefined) {
    					searchFieldColumn.push({id: fieldObj.id, text: fieldObj.text, type: "string"});
    				} else if (fieldObj.title  != undefined ) {
    					searchFieldColumn.push({id: fieldObj.id, text: fieldObj.title, type: fieldObj.type});
    				} else {
    					console.log("!!no text!!");
    				}
    			} else {
    				console.log("!!fieldObj undefined!!");
    			}
    		}		
    	});
    	
    	$("#fieldSelect111").select2({
   		  data: searchFieldColumn,
   		  //placeholder:{id: '0', text:'全部字段'},
   		  //allowClear:true
   		});
    	$("#fieldSelect111").val(null).trigger("change");
    	$("#exprSelect111").select2();
    	
    	$("#fieldSelect111").on("select2:select", function (e) { 
    		var selectVal = $("#fieldSelect111").val();
    		var objType = "string";
    		
    		searchFieldColumn.forEach(function(elem){  
    			if(selectVal == elem.id) 
    				objType = elem.type;
    		})
    		console.log(objType);
    		
     		if(objType == "string") {
     			$("#divExprSelect111").attr("class", "col-sm-2");
     			$("#divExprSelect111").html('<select style="width:100%; class="form-control" id="exprSelect111" ></select>');
     			
     			$("#divSearchValue").attr("class", "col-sm-4");
     			$("#divSearchValue").html('<input id="searchText111" style="width:100%; type="text" class="input form-control" placeholder="请输入">');
     			
     			$("#divSearchValueHidden").attr("style", "display:none");
     			
     			$("#exprSelect111").select2();
     			$("#exprSelect111").select2('destroy').empty(); 
     			
     			$("#exprSelect111").select2({
     	     		  data: InquirySearchPanel.exprString,
     	     	});
     			$("#exprSelect111").val("=").trigger("change");
     			
     		} else if(objType == "number") {
     			$("#divExprSelect111").attr("class", "col-sm-2");
     			$("#divExprSelect111").html('<select class="form-control" style="width:100%; id="exprSelect111" ></select>');
     			
     			$("#divSearchValue").attr("class", "col-sm-4");
     			$("#divSearchValue").html('<input id="searchText111" style="width:100%; type="number" class="input form-control" placeholder="请输入">');
     			
     			$("#divSearchValueHidden").attr("style", "display:none");
     			
     			$("#exprSelect111").select2();
     			$("#exprSelect111").select2('destroy').empty(); 
     			
     			$("#exprSelect111").select2({
   	     		  data: InquirySearchPanel.exprNumber,
     			});
     			$("#exprSelect111").val("=").trigger("change");
     			
     		} else if(objType == "date"){
     			$("#divExprSelect111").attr("class", "col-sm-2");
     			$("#divExprSelect111").html('<input style="width:100%; class="form-control  date-picker" id="idDateStart" data-date-format="dd-mm-yyyy" />');
     			
     			$("#divSearchValue").attr("class", "col-sm-2");
     			$("#divSearchValue").html('<input style="width:100%; class="form-control  date-picker" id="idDateEnd" data-date-format="dd-mm-yyyy" />');
     			
     			$("#divSearchValueHidden").attr("style", "display:block;margin-top:5px;width:40px;");
     			
     			$("#idDateStart").datepicker({
                    format : 'yyyy-mm-dd',
                    autoclose : true,
                    language : 'zh-CN',
                    todayHighlight : true,
                    clearBtn : true,
                    immediateUpdates : true,
                    clearDate : function() {
                        $("#idDateSearchValue").val('').datepicker('update');
                    }
                });

     			$("#idDateEnd").datepicker({
                    format : 'yyyy-mm-dd',
                    autoclose : true,
                    language : 'zh-CN',
                    todayHighlight : true,
                    clearBtn : true,
                    immediateUpdates : true,
                    clearDate : function() {
                        $("#idDateSearchValue").val('').datepicker('update');
                    }
                });
     		}    		
    	});
    },
    
    InitAdvanceSearchHtml : function() {
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
    },
}