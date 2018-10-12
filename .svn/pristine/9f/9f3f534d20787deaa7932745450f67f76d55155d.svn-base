var uploadGrid = null;
var dtGridColumnsFacilityUploadPanel = [ {
    id : 'id',
    title : '编号',
    width : 60,
    hideType : 'sm|xs|md',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == '未保存') {
            return '<span class="label label-sm label-success arrowed arrowed-righ">未保存</span>';
        } else  if (value.indexOf('错误') != -1) {
            return '<span class="label label-sm label-danger arrowed arrowed-righ">'+value+'</span>';
        } else {
            return value;
        }
    }
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
    }
}, {
    id : 'ifTest',
    title : '是否可检测',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == "1") {
            return '<span class="label label-sm label-success arrowed arrowed-righ">可检测</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">不可检测</span>';
        }
    }
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
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'price',
    title : '价格',
    search : true,
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'localSource',
    title : '采购地',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
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
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'note',
    title : '备注',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md|lg'
}];

var uploadGridDatas = [];
var dtGridOptionFacilityUploadPanel = {
    lang : 'zh-cn',
    ajaxLoad : false,
    datas : uploadGridDatas,
    check : false,
    extraWidth : '37px',
    loadURL : sys.rootPath + '/facility/list.html',
    columns : dtGridColumnsFacilityUploadPanel,
    gridContainer : 'dtGridContainerFupload33',
    toolbarContainer : 'dtGridToolBarContainerFupload33',
    tools : 'refresh',
    pageSize : 10,
    pageSizeLimit : [10,30,50,1000],
//    onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
//    	row.children()[1].children[0].checked = !row.children()[1].children[0].checked;
//    }
};

var FacilityUploadPanel = {
		//内部用
		GetPanelHtml : function(selectCid,addCallback) {
    	var html = '<div class="row"><div class="col-xs-12"><div class="widget-header" style="height: 40px">';
    		
    	html = html + '<div id="picker"  class="col-sm-2">导入器具</div>' ;
    	
    	if(selectCid == "true") {
    		html = html + '<div id="thelist" class="col-sm-3 uploader-list"></div>';
        	html = html + '<div class="col-sm-1" style="margin-top: 5px;padding-right: 0px;width:50px;!important"><label>公司</label></div>'
           	html = html + '<div class="col-sm-2"><select style="margin-top: 1px;width:100%;" class="form-control" id="selectcid11" ></select></div>';
    	} else {
    		html = html + '<div id="thelist" class="col-sm-6 uploader-list"></div>';
    	}
    	
       	html = html + '<div class="col-sm-3"><span class="input-group-btn">';
     
       	html = html + '<button id="btnSaveFUpload33" style="margin-top: 1px" class="btn btn-success btn-sm" type="button"><i class="fa fa-save"></i>&nbsp;确认保存</button>';	
        if(addCallback != null) {
        	html = html + '<button id="btnAddFUpload33" style="margin-top: 1px;margin-left: 5px" class="btn btn-warning btn-sm" type="button"><i class="fa fa-plus-square-o"></i>&nbsp;添加全部</button>';	
        } 
        
        html = html + '<button id="btnTemplateDown33" style="margin-top: 1px;margin-left: 5px" class="btn btn-info btn-sm" type="button"><i class="fa fa-download"></i>&nbsp;模板下载</button>';	
        
        html = html + '</span></div>'; 
        html = html + '</div></div></div>'; 
        
        html = html + '<div class="row" style="margin-top:2px;"><div class="col-xs-12 widget-container-col ui-sortable">'; 
      	
      	html = html + '<div style="margin-top:0px;margin-bottom:0px;" class="widget-body" style="display: block;">'; 
      	html = html + '<div style="margin-top:0px;margin-bottom:0px;" class="widget-main padding-6 no-padding-left no-padding-right">'; 
      	html = html + '	<input name="pageNum" type="hidden" value="1">'; 
      	html = html + '	<input name="pageSize" type="hidden" value="5">'; 
      	//html = html + '	<input id="orderByColumn33" type="hidden" value="${page.orderByColumn }">'; 
      	//html = html + '	<input id="orderByType33" type="hidden" value="${page.orderByType }">'; 
      	html = html + '	<div id="dtGridContainerFupload33" style="margin-top:0px;margin-bottom:0px;" class="dlshouwen-grid-container"></div>'; 
      	html = html + '	<div id="dtGridToolBarContainerFupload33" style="margin-top:1px;margin-bottom:0px;" class="dlshouwen-grid-toolbar-container"></div>'; 
      	html = html + '</div></div></div></div></div>'; 
	
       	return html;
    },
    
    /**
     * 搜索panel包含模糊查询
     * @param $searchDiv 搜索panel所在div
     * @param gridColumns grid的column对象或者自定义数组 [{id:'',text:''}]
     * @param searchFlag  使用grid的column时，是否启用search字段
     * @param gridObj     grid对象，用于刷新数据
     */
    Init : function($searchDiv, pageSize, addCallback, uploadCallback,selectcid, loadUrl) {
    	if(pageSize != undefined) {
    		dtGridOptionFacilityUploadPanel.pageSize = pageSize;
    	}
    	var gridObj = $.fn.dlshouwen.grid.init(dtGridOptionFacilityUploadPanel);
    	uploadGrid = gridObj;
    	
    	$searchDiv.html(FacilityUploadPanel.GetPanelHtml(selectcid, addCallback));

    	if(selectcid == "true") {
    		$("#selectcid11").select2();// 初始化控件，只有初始化绑定其它事件才生效
    		webside.getData("/facility/company/list", {}, 
  				  function(result) {
  					  if(result != null) {
  						$("#selectcid11").select2({
  						  data: result.results,
  						  placeholder:'',
  						  allowClear:false
  						});	
  						//$("#selectcid11").val(null).trigger("change");
  						
  					  } else {
  						  layer.msg(result.message); 
  					  }
  				  }, 
  				  function(result) {
  					  layer.msg("无法取得公司信息，请重试或者联系管理员"); 
  				  });
    	}
    	
    	var uploading = null;
    	
    	// 创建上传组件
    	var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadExcel',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
			    pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'Files',
					extensions : 'xlsx,xls',
					mimeTypes: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel',  
				}
			});
			uploader.on('fileQueued', function(file) {
//				var html = '<div id="thelist" class="uploader-list"></div>'
//		             + '<div><div id="picker">选择文件</div>'  
//		             + '</div>';
				uploading = layer.load();      
		        var html =  '<div id="' + file.id + '" class="item" style="margin-top:5px;">'
		             + '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>';
				$("#thelist").html(html);
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('上传并解析完毕，请确认数据是否正确，如果正确请点击确认保存。');
					 $.each(response.data, function(index, value) {
						 uploadGridDatas.push(this);
						});
					 gridObj.load();
//					if(uploadCallback) {
//						uploadCallback(response.data);
//					}
					//$("#picker").attr("style", "display:none");
				} else {
					layer.alert(response.message, {icon : 5,shift : 6,time : 0});
				}
				
			});
			// 文件上传过程中创建进度条实时显示。  
			uploader.on(
							'uploadProgress',
							function(file, percentage) {
								var $li = $('#' + file.id), $percent = $li
										.find('.progress .progress-bar');

								// 避免重复创建  
								if (!$percent.length) {
									$percent = $(
											'<div class="progress progress-striped active">'
													+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
													+ '</div>' + '</div>')
											.appendTo($li).find('.progress-bar');
								}

								$li.find('p.state').text('上传中');

								$percent.css('width', percentage * 100 + '%');
							});
			uploader.on('uploadError', function(file) {
				$('#' + file.id).find('p.state').text('上传出错');
			});

			uploader.on('uploadComplete', function(file) {
				$('#' + file.id).find('.progress').fadeOut();
				layer.close(uploading);  
			});
			
    	gridObj.load();
    	$("#btnSaveFUpload33").click(function(){
    		
    		var loading = layer.load();
			var data={detailsList : uploadGridDatas};	
			if(selectcid == "true") {
    			data.cid = $("#selectcid11").val();
    		}
			webside.postJsonData("/facility/uploadSave.html", JSON.stringify(data), 
					  function(result) {
				  		layer.close(loading);     
						  if(result != null && result.success) {
							  uploadGridDatas.splice(0, uploadGridDatas.length);
							  var errCnt = 0;
							  $.each(result.data, function(index, value) {
									 uploadGridDatas.push(this);
									 if (this.id == null || this.id.indexOf('保存') >= 0) {
										 errCnt = errCnt + 1;
									 }
									});
							  if (errCnt > 0) {
								  layer.msg('部分成功保存，请检查编号中有错误的记录，错误数量：' + errCnt, {icon : 0});
							  } else {
								  layer.msg('成功保存，数量：' + uploadGridDatas.length , {icon : 1});
							  }
							  gridObj.refresh(true); 
						  } else {
							  layer.msg(result.message, {icon : 0}); 
						  }
						  
					  }, 
					  function(result) {
						  layer.close(loading);     
						  
						  layer.msg("无法与服务器通信，请重试或者联系管理员", {icon : 0}); 
					  });
   	 	});
    	if (addCallback) {
	    	 $("#btnAddFUpload33").click(function(){
	    		 addCallback(uploadGridDatas);
	    	 });
    	} 
    	 $("#btnTemplateDown33").click(function(){
    		  
    		 webside.download("/file/downloadFacilityTemplate.html");
    	 });
    	
    }
    ,InitUploadTemplate : function($targetDiv, uploadCallback) {
    	
    	var html = '<div class="row"><div class="widget-header" style="height: 40px">';
		
    	html = html + '<div id="picker"  class="col-sm-2">导入器具</div>' ;
    	html = html + '<div id="thelist" class="col-sm-6 uploader-list"></div>';
    	html = html + '</div></div>';
       	
    	$targetDiv.html(html);

    	var uploading = null;

    	// 创建上传组件
    	var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadFacilityTemplate',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
			    pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'Files',
					extensions : 'xlsx,xls',
					mimeTypes: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel',  
				}
			});
			uploader.on('fileQueued', function(file) {
//				var html = '<div id="thelist" class="uploader-list"></div>'
//		             + '<div><div id="picker">选择文件</div>'  
//		             + '</div>';
				uploading = layer.load();      
		        var html =  '<div id="' + file.id + '" class="item" style="margin-top:5px;">'
		             + '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>';
				$("#thelist").html(html);
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('上传并解析完毕，请确认数据是否正确，如果正确请点击确认保存。');
				} else {
					layer.alert(response.message, {icon : 5,shift : 6,time : 0});
				}
				
			});
			// 文件上传过程中创建进度条实时显示。  
			uploader.on(
							'uploadProgress',
							function(file, percentage) {
								var $li = $('#' + file.id), $percent = $li
										.find('.progress .progress-bar');

								// 避免重复创建  
								if (!$percent.length) {
									$percent = $(
											'<div class="progress progress-striped active">'
													+ '<div class="progress-bar" role="progressbar" style="width: 0%">'
													+ '</div>' + '</div>')
											.appendTo($li).find('.progress-bar');
								}

								$li.find('p.state').text('上传中');

								$percent.css('width', percentage * 100 + '%');
							});
			uploader.on('uploadError', function(file) {
				$('#' + file.id).find('p.state').text('上传出错');
			});

			uploader.on('uploadComplete', function(file) {
				$('#' + file.id).find('.progress').fadeOut();
				layer.close(uploading);  
			});
    }
}