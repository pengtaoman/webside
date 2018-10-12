var entrust = {
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
			ajax : {
				url : sys.rootPath + "/facility/company/list",
				dataType : 'json'
			}
		});

		$('#cid').val(citInit).trigger('change');
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
				"taskName" : $('#taskName').val()
			},
			dataType : "json",
			success : function(resultdata) {
				if (resultdata.success) {
					$("#id").val(resultdata.id);
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
	addTaskDetail : function(nav, callback) {
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
        			layer.confirm(resultdata.message, {
	                    icon : 3,
	                    title : '提示',
	                    btn: ['确定'] 
	                }, function(index, layero) {
	                	if (callback) {
							callback();
						}
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
}