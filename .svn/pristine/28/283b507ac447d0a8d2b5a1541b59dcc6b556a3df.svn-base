var keyTypeMap = new Map();
var entrust = {

	exprNumber : [{id : ">", text:">"},  {id : "<", text:"<"}, {id : "=", text:"="}, {id : "<>", text:"!="},{id : ">=", text:">="}, {id : "<=", text:"<="}],
	exprString : [{id : "=", text:"等于"}, {id : "<>", text:"不等于"}, {id : "like", text:"包含"}, {id : "not like", text:"不包含"}],
	
	initCid : function() {
		var citInit = $('#cidInit').val();
		var cidName = $('#cidNameInit').val();
		var data = [ {
			id : citInit,
			text : cidName
		} ];
		$('#cid').select2({
			data : data,
			placeholder : '请选择',
			allowClear : true,
			minimumResultsForSearch: -1,
			ajax : {
				url : sys.rootPath + "/facility/company/list",
				dataType : 'json'
			}
		}).on('change', function() {
			$("#inquiryId").val('').trigger('change');
		});

		$('#cid').val(citInit).trigger('change');
	},
	initInquiryId : function() {
		var inquiryIdInit = $('#inquiryIdInit').val();
		var inquiryNoteInit = $('#inquiryNoteInit').val();
		var data = [ {
			id : inquiryIdInit,
			text : inquiryNoteInit
		} ];
		$("#inquiryId").select2({
			placeholder : '请选择',
			data : data,
			allowClear : true,
			minimumResultsForSearch: -1,
			ajax : {
				url : sys.rootPath + "/entrust/inquiry/list",
				dataType : 'json',
				data : function() {
					var query = {
						cid : $('#cid').val()
					}
					return query;
				}	
			}
		});
		$('#inquiryId').val(inquiryIdInit).trigger('change');
	},
	initStrategy : function() {
		var strategyInit = $('#strategyInit').val();
		var strategyNameInit = $('#strategyNameInit').val();
		var data = [ {
			id : strategyInit,
			text : strategyNameInit
		} ];
		$('#strategy').select2({
			placeholder : '请选择',
			data : data,
			allowClear : true,
			minimumResultsForSearch: -1,
			ajax : {
				url : sys.rootPath + "/entrust/strategy/list",
				dataType : 'json',
				data : {
					"type" : "strategy"
				}
			}
		}).on('change',  function() {
			var strategy = $('#strategy').val();
			if(strategy==2){
				$('#baojiadan').show();
				
			} else {
				$('#baojiadan').hide();
			}
			
		});
		$('#strategy').val(strategyInit).trigger('change');
	},

	initIfEnfore : function() {
		var ifEnforceInit = $('#ifEnforceInit').val();
		$('#ifEnforce').val(ifEnforceInit).trigger('change');
	},
	
	addTask : function(nav, callback) {
		$.ajax({
			type : "POST",
			url : sys.rootPath + nav,
			data : {
				"cid" : $('#cid').val(),
				"ifEnforce" : $('#ifEnforce').val(),
				"note" : $('#note').val(),
				"taskName" : $('#taskName').val(),
				"strategy" : $('#strategy').val(),
				"inquiryId" : $('#inquiryId').val()
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					$("#id").val(resultdata.id);
					if (callback) {
						callback();
					}
					$('#myTab a[href="#step2"]').tab('show');
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
	},

	addTaskDetail : function(nav, callback) {
		if(getFacilitySetSize()<1){
			layer.msg('请从上半区选择待检测仪器', {
				icon : 3
			});
			return;
		}
		$.ajax({
			type : "POST",
			url : sys.rootPath + nav,
			data : {
				"facilityIds" : getfacilitySet(),
				"taskId" : $("#id").val()
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					$(".next").click();
					if (callback) {
						callback();
					}
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
	},
	releaseTask : function(nav, callback) {
		$.ajax({
			type : "POST",
			url : sys.rootPath + nav,
			data : {
				"taskId" : $("#id").val()
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					layer.msg(resultdata.message, {
						icon : 0
					});
					webside.common.loadPage('/entrust/taskListUI.html');
					
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
	},
    searchDivHide : function(){
		$("#searchDiv").hide();
		$("#shouqi").hide();
		$("#zhankai").show();
	},
	searchDivShow : function(){
		$("#searchDiv").show();
		$("#zhankai").hide();
		$("#shouqi").show();
	},
	initFieldSelect : function(gridColumns, gridObj) {
    	gridColumns.forEach(function(fieldObj, index){
    		if('1' == fieldObj.search) {
    			if (fieldObj != undefined) {
    				if(fieldObj.text  != undefined) {
    					$("#fieldSelect").append('<option value="' + fieldObj.id + '">'+ fieldObj.text + '</option>');
    					keyTypeMap.set(fieldObj.id, "string");
    				} else if (fieldObj.title  != undefined ) {
    					$("#fieldSelect").append('<option value="' + fieldObj.id + '">'+ fieldObj.title + '</option>');
    					keyTypeMap.set(fieldObj.id, fieldObj.type);
    				} else {
    					console.log("!!no text!!");
    				}
    			} else {
    				console.log("!!fieldObj undefined!!");
    			}
    		}		
    	});
    	this.fieldChange();
    },
	
	fieldChange : function() {
		var selectVal = $("#fieldSelect").val();
		var objType = keyTypeMap.get(selectVal);
		$("#exprSelect").empty();
		$("input[id='searchText']").val("").focus(); 
 		if(objType == "string") {
 			$("#exprSelect").show();
 			$("#searchText").show();
 			$("#idDateStart").hide();
 			$("#idDateEnd").hide();
 			 $.each(this.exprString, function(index, value) {
 				$("#exprSelect").append('<option value="' + value.id + '">'+ value.text + '</option>');
 	         });
 			
 		} else if(objType == "number") {
 			$("#exprSelect").show();
 			$("#searchText").show();
 			$("#idDateStart").hide();
 			$("#idDateEnd").hide();
			 $.each(this.exprNumber, function(index, value) {
 				$("#exprSelect").append('<option value="' + value.id + '">'+ value.text + '</option>');
 	         });
 			
 		} else if(objType == "date"){
 			$("#exprSelect").hide();
 			$("#searchText").hide();
 			$("#idDateStart").show();
 			$("#idDateEnd").show();
 			
 			$("#idDateStart").datepicker({
                format : 'yyyy-mm-dd',
                autoclose : true,
                language : 'zh-CN',
                todayHighlight : true,
                clearBtn : true,
                immediateUpdates : true,
                clearDate : function() {
                    $("#idDateStart").val('').datepicker('update');
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
                    $("#idDateEnd").val('').datepicker('update');
                }
            });
 			
 		}    
	},
	search : function() {
		grid.parameters = new Object();
	    grid.parameters['cid'] = $("#cid").val();
	    grid.parameters['strategy'] = $("#strategy").val();
	    grid.parameters['inquiryId'] = $("#inquiryId").val();
	    grid.parameters['taskId'] = $("#id").val();
	    
		var field = $("#fieldSelect").val();
		var expr = $("#exprSelect").val();
		
		if (expr == null && field != "all") {
			var startDate = $("#idDateStart").val();
			var endDate = $("#idDateEnd").val();

			if (startDate != undefined && startDate != '') {
				grid.parameters["startDate"] = startDate;
			}

			if (endDate != undefined && endDate != '') {
				grid.parameters["endDate"] = endDate;
			}
			grid.parameters[$("#fieldSelect111").val()] = '1';
		} else if (expr != null && field != "all"){
			var searchVal = $("#searchText").val();
			grid.parameters[field] = searchVal;
			grid.parameters["query_expr"] = expr;
		}

		grid.refresh(true);
	}
}
