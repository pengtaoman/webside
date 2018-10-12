var SearchPanel = {
	exprNumber : [{id : ">", text:">"},  {id : "<", text:"<"}, {id : "=", text:"="}, {id : "<>", text:"!="},{id : ">=", text:">="}, {id : "<=", text:"<="}],
	exprString : [{id : "=", text:"等于"}, {id : "<>", text:"不等于"}, {id : "like", text:"包含"}, {id : "not like", text:"不包含"}],
		//内部用
    GetPanelHtml : function(showAdvanceButton) {
    	var html = '<div class="col-sm-1" style="margin-top: 5px;padding-right: 0px;width:70px;!important"><label>搜索字段</label></div>'
       	 html = html + '<div class="col-sm-3"><select style="width:100%; class="form-control" id="fieldSelect111" ></select></div>';
       	 html = html + '<div id="divExprSelect111" class="col-sm-1" ><select style="width:100%; class="form-control" id="exprSelect111" ></select></div>';
       	 html = html + '<div id="divSearchValueHidden" class="col-sm-1" style="display:none;" ><label>到</label></div>';
       	 html = html + '<div id="divSearchValue" class="col-sm-4"><input id="searchText111" style="width:100%; type="text" class="input form-control" placeholder="请输入"></div>';
       	 html = html + '<div class="col-sm-2"><span class="input-group-btn">';
       	 html = html + '<button id="btnSearch" style="margin-left: 5px" class="btn btn-info btn-sm" type="button"> <i class="fa fa-search"></i>&nbsp;搜索</button>';
       	
       	 if(showAdvanceButton == true) {
	     	html = html + '<button id="btnAdvanceSearch" style="margin-left: 5px" class="btn btn-warn btn-sm" type="button"> <i class="fa fa-angle-double-right"></i>&nbsp;高级搜索</button>';
       	 } 
       	
       	 html = html + '</span></div>';       	 
		
       	 return html;
    },
    
    InitNeedPanel : function($searchDiv, gridColumns, gridObj, showAdvanceButton) {
    	$searchDiv.html(SearchPanel.GetPanelHtml(showAdvanceButton));
    	var searchFieldColumn = [];
    	gridColumns.forEach(function(fieldObj, index){
    		if('1' == fieldObj.search) {
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
     			$("#divExprSelect111").attr("class", "col-sm-1");
     			$("#divExprSelect111").html('<select style="width:100%; class="form-control" id="exprSelect111" ></select>');
     			
     			$("#divSearchValue").attr("class", "col-sm-4");
     			$("#divSearchValue").html('<input id="searchText111" style="width:100%; type="text" class="input form-control" placeholder="请输入">');
     			
     			$("#divSearchValueHidden").attr("style", "display:none");
     			
     			$("#exprSelect111").select2();
     			$("#exprSelect111").select2('destroy').empty(); 
     			
     			$("#exprSelect111").select2({
     	     		  data: SearchPanel.exprString,
     	     	});
     			$("#exprSelect111").val("=").trigger("change");
     			
     		} else if(objType == "number") {
     			$("#divExprSelect111").attr("class", "col-sm-1");
     			$("#divExprSelect111").html('<select style="width:100%; class="form-control" id="exprSelect111" ></select>');
     			
     			$("#divSearchValue").attr("class", "col-sm-4");
     			$("#divSearchValue").html('<input id="searchText111" style="width:100%; type="number" class="input form-control" placeholder="请输入">');
     			
     			$("#divSearchValueHidden").attr("style", "display:none");
     			
     			$("#exprSelect111").select2();
     			$("#exprSelect111").select2('destroy').empty(); 
     			
     			$("#exprSelect111").select2({
   	     		  data: SearchPanel.exprNumber,
     			});
     			$("#exprSelect111").val("=").trigger("change");
     			
     		} else if(objType == "date"){
     			$("#divExprSelect111").attr("class", "col-sm-2");
     			$("#divExprSelect111").html('<input class="form-control  date-picker" id="idDateStart" data-date-format="dd-mm-yyyy" />');
     			
     			$("#divSearchValue").attr("class", "col-sm-2");
     			$("#divSearchValue").html('<input class="form-control  date-picker" id="idDateEnd" data-date-format="dd-mm-yyyy" />');
     			
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
    	
    	 $("#btnSearch").click(function(){
    		 
    		 if(gridObj != undefined) {
    			 SearchPanel.ClickSearch(gridObj, false);
    		 	} else {
    		 		console.log("btnSearch not callback");
    		 	}
    	 });
    	 
    	//注册回车键事件
 	    document.onkeypress = function(e){
 	    var ev = document.all ? window.event : e;
 	        if(ev.keyCode==13) {
 	        	$("#btnSearch").click();
 	        }
 	    };
    },
    
  //内部用
    refreshGrid : function(grid) {
    	SearchPanel.ClickSearch(grid, true);
    },
    
    //内部用
    ClickSearch : function(grid, always) {
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
		 grid.parameters['taskId'] = $("#taskId").val();
		 grid.parameters['entrustId'] = $("#entrustId").val();
		 grid.refresh(true);
    },
    
    
    /**
     * 搜索panel包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    SearchPanelWithLike : function($searchDiv, gridColumns, searchFlag, gridObj){
    	 SearchPanel.InitNeedPanel($searchDiv, gridColumns, gridObj, false);
    },
    
    /**
     * 搜索panel包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    SearchPanelWithLikeAndAdvance : function($searchDiv, gridColumns, searchFlag, gridObj, advCallback){
    	SearchPanel.InitNeedPanel($searchDiv, gridColumns, gridObj, true);
     	
    	 $("#btnAdvanceSearch").click(function(){
    		 if(advCallback) {
    			 advCallback();
    		 }
    	 });
    	 
    },
    
    /**
     * 搜索panel不包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    SearchPanelWithNoLike : function($searchDiv, gridColumns, searchFlag, gridObj){
    	SearchPanel.InitNeedPanel($searchDiv, gridColumns, gridObj, false);
   }
}