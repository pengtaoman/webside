var entrust = {
	initCid : function() {
		$('#organId').select2({
			placeholder : '请选择',
			allowClear : true,
			minimumResultsForSearch: -1,
			ajax : {
				url : sys.rootPath + "/organ/namesList",
				dataType : 'json'
			}
		}).on('change', function() {
			entrust.freshOrganInfo();
		});
	},
	freshOrganInfo : function() {
		$.ajax({
			url : sys.rootPath +"/entrust/organ.html",
			data : {
				"organId" : $('#organId').val()
			},
			dataType : "json",
			success : function(resultdata) {
				 $('#contactPhone').val(resultdata.telephone);
				 $('#contacter').val(resultdata.contactPerson);
				 $('#contactAddress').val(resultdata.address);
				 $('#postcode').val(resultdata.regionCode);
			},
			error : function(errorMsg) {
				layer.msg('服务器未响应,请稍后再试', {
					icon : 3
				});
			}
		});
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
	},
	commit : function(formId, commitUrl) {
	     var data = $("#" + formId).serialize();
	     $.ajax({
	         type : "POST",
	         url : sys.rootPath + commitUrl,
	         data : data,
	         dataType : "json",
	         success : function(resultdata) {
	             if (resultdata.success) {
                	 var index = parent.layer.getFrameIndex(window.name);
	                 parent.taskDetailSearch();
	                 parent.layer.close(index);
	             } else {
	                 layer.msg(resultdata.message, {
	                     icon : 5
	                 });
	             }
	         },
	         error : function(data, errorMsg) {
	             layer.msg(data.responseText, {
	                 icon : 2
	             });
	         }
	     });
	 }
}