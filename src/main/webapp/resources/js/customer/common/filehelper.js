var FileHelper = {
		initImg: function($upload_div, fileName, thumbnailWidth, thumbnailHeight, callback) {
			var html = "";
			 if(fileName != null && fileName != "") {
				 var btnHtml = '<input type="button" class="btn btn-default" value="删除" '
						+'onClick="FileHelper.delUploadFile(\''+fileName+'\')"/>';
				 
				 html = '<div id="thelist">'
				 		+  '<img src="' + sys.rootPath + '/file/download2.html?name='+ fileName +'" height="'+ thumbnailHeight +'">'
				 		//+  '<div class="info">'+ fileName + '</div>'
				 		//+  btnHtml
				 		+ '</div>'
				 		+ '<div><div id="picker" style="display">选择文件</div>';
             } else {
            	 html = '<div id="thelist" class="uploader-list"></div>'
		             + '<div><div id="picker">选择文件</div>'  
		            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
		             + '</div>';
             }
			 
		     $upload_div.html(html);
			
			 var uploader = WebUploader.create({
				   // 选完文件后，是否自动上传。  
			       auto: true, 
			       
					// swf文件路径  
					swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//		 			formData : {
//		 				"name" : $("#requestDn").val()
//		 			},//参数列表  
					// 文件接收服务端。  
					server : sys.rootPath + '/file/upload2',
					// 选择文件的按钮。可选。  
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
					pick : '#picker',
					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
					resize : false,
					
					// 只允许选择图片文件。  
					accept : {
						title: '选择图片文件',
					    extensions: 'gif,jpg,jpeg,bmp,png',
					    mimeTypes: 'image/*'  
					},
					thumb:{
					    width: 110,
					    height: 110,

					    // 图片质量，只有type为`image/jpeg`的时候才有效。
					    quality: 70,

					    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					    allowMagnify: true,

					    // 是否允许裁剪。
					    crop: false,

					    // 为空的话则保留原有图片格式。
					    // 否则强制转换成指定的类型。
					    type: 'image/jpeg'
					}
				});
				uploader.on('fileQueued', function(file) {
					//$list.append('<div id="' + file.id + '" class="item">'
					$("#thelist").html('<div id="' + file.id + '" class="file-item thumbnail">'
							//+ '<h4 class="info">' + file.name + '</h4>'
							+ '<img><div class="info">'+ file.name + '</div>'
							+ '<p class="progress progress-bar">上传进度...</p>'
							+ '</div>'
							+ '<div id="' + file.id + '_delete" class="item">'
							+ '</div>');
					$img = $('#' + file.id).find('img');
					 uploader.makeThumb(file, function (error, src) {
			                if (error) {
			                    $img.replaceWith('<span>不能预览</span>');
			                    return;
			                }
			                $img.attr('src', src);
			            }, thumbnailWidth, thumbnailHeight);
				});

				uploader.on('uploadSuccess', function(file, response) {
					if(response.success) {
						$( '#'+file.id ).addClass('upload-state-done');
						//$('#' + file.id).find('p.state').text(response.data);
						var btnHtml = '<input type="button" class="btn btn-default" value="删除" '
									+'onClick="FileHelper.delUploadFile(\''+response.data+'\')"/>';
						$('#' + file.id+'_delete').html(btnHtml);
						$("#picker").attr("style", "display:none");
						
						 if(callback) {
							 callback(response.data);
						 }
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
					//$('#' + file.id).find('p.state').text('上传出错');
					 var $li = $( '#'+file.id ),
				        $error = $li.find('div.error');

				    // 避免重复创建
				    if ( !$error.length ) {
				        $error = $('<div class="error"></div>').appendTo( $li );
				    }

				    $error.text('上传失败');
				});

				uploader.on('uploadComplete', function(file) {
					$('#' + file.id).find('.progress').fadeOut();
				});

				$("#ctlBtn").on('click', function() {
					uploader.upload();
					console.log("上传成功");  
				}); 
		},
		init : function($upload_div) {
			var html = '<div id="thelist" class="uploader-list"></div>'
		             + '<div><div id="picker">选择文件</div>'  
		            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
		             + '</div>'  
		     $upload_div.html(html);
			
			 var uploader = WebUploader.create({
				   // 选完文件后，是否自动上传。  
			       auto: true, 
			       
					// swf文件路径  
					swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//		 			formData : {
//		 				"name" : $("#requestDn").val()
//		 			},//参数列表  
					// 文件接收服务端。  
					server : sys.rootPath + '/file/upload2',
					// 选择文件的按钮。可选。  
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
					pick : '#picker',
					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
					resize : false,
					
					// 只允许选择图片文件。  
					accept : {
						title : 'SelectFile',
						extensions : 'jpg'
					//                mimeTypes: '.cer,'  
					}
				});
				uploader.on('fileQueued', function(file) {
					//$list.append('<div id="' + file.id + '" class="item">'
					$("#thelist").html('<div id="' + file.id + '" class="item">'
							//+ '<h4 class="info">' + file.name + '</h4>'
							+ '<p class="state">等待上传...</p>'
							+ '<p class="progress progress-bar">上传进度...</p>'
							+ '</div>'
							+ '<div id="' + file.id + '_delete" class="item">'
							+ '</div>');
				});

				uploader.on('uploadSuccess', function(file, response) {
					if(response.success) {
						$('#' + file.id).find('p.state').text(response.data);
						var btnHtml = '<input type="button" class="btn btn-default" value="删除" '
									+'onClick="FileHelper.delUploadFile(\''+response.data+'\')"/>';
						$('#' + file.id+'_delete').html(btnHtml);
						$("#picker").attr("style", "display:none");
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
				});

				$("#ctlBtn").on('click', function() {
					uploader.upload();
					console.log("上传成功");  
				}); 
		},
		initFile : function($upload_div,callback) {
			var html = '<div id="thelist" class="uploader-list"></div>'
		             + '<div><div id="picker">选择文件</div>'  
		            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
		             + '</div>'  
		     $upload_div.html(html);
			
			 var uploader = WebUploader.create({
				   // 选完文件后，是否自动上传。  
			       auto: true, 
			       
					// swf文件路径  
					swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//		 			formData : {
//		 				"name" : $("#requestDn").val()
//		 			},//参数列表  
					// 文件接收服务端。  
					server : sys.rootPath + '/file/upload2',
					// 选择文件的按钮。可选。  
					// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
					pick : '#picker',
					// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
					resize : false,
					
					// 只允许选择图片文件。  
					accept : {
						title : 'SelectFile',
						extensions : 'rar,zip,doc,docx'
					//                mimeTypes: '.cer,'  
					}
				});
				uploader.on('fileQueued', function(file) {
					//$list.append('<div id="' + file.id + '" class="item">'
					$("#thelist").html('<div id="' + file.id + '" class="item">'
							//+ '<h4 class="info">' + file.name + '</h4>'
							+ '<p class="state">等待上传...</p>'
							+ '<p class="progress progress-bar">上传进度...</p>'
							+ '</div>'
							+ '<div id="' + file.id + '_delete" class="item">'
							+ '</div>');
				});

				uploader.on('uploadSuccess', function(file, response) {
					if(response.success) {
						$('#' + file.id).find('p.state').text(response.data);
						var btnHtml = '<input type="button" class="btn btn-default" value="删除" '
									+'onClick="FileHelper.delUploadFile(\''+response.data+'\')"/>';
						$('#' + file.id+'_delete').html(btnHtml);
						$("#picker").attr("style", "display:none");
						
						if(callback) {
							callback(response.data);
						}
					} else {
						layer.alert(response.message, {icon : 5,shift : 6,time : 0});
					}
					
				});
				// 文件上传过程中创建进度条实时显示。  
				uploader.on('uploadProgress',
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
				});

				$("#ctlBtn").on('click', function() {
					uploader.upload();
					console.log("上传成功");  
				}); 
		},
		initTemplate : function($upload_div, callback) {
			var html = '<div id="thelist" class="uploader-list"></div>'
	             + '<div><div id="picker">选择文件</div>'  
	            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
	             + '</div>'  
	     $upload_div.html(html);
		
		 var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadTemplate',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
				pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'SelectFile',
					extensions : 'doc,docx',
					mimeTypes: 'application/msword',  
				    // mimeTypes: '.cer,'  
				}
			});
			uploader.on('fileQueued', function(file) {
				//$list.append('<div id="' + file.id + '" class="item">'
				$("#thelist").html('<div id="' + file.id + '" class="item">'
						//+ '<h4 class="info">' + file.name + '</h4>'
						+ '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>'
						+ '<div id="' + file.id + '_delete" class="item">'
						+ '</div>');
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('word转换后内容：');
					if(callback) {
						callback(response.data);
					}
					$("#picker").attr("style", "display:none");
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
			});

			$("#ctlBtn").on('click', function() {
				uploader.upload();
				console.log("上传成功");  
			}); 
		},
		initInquiryTemplate : function($upload_div, callback) {
			var html = '<div id="thelist" class="uploader-list"></div>'
	             + '<div><div id="picker">选择文件</div>'  
	            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
	             + '</div>'  
	     $upload_div.html(html);
		
		 var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadInquiryTemplate',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
				pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'SelectFile',
					extensions : 'doc,docx',
					mimeTypes: 'application/msword',  
				    // mimeTypes: '.cer,'  
				}
			});
			uploader.on('fileQueued', function(file) {
				//$list.append('<div id="' + file.id + '" class="item">'
				$("#thelist").html('<div id="' + file.id + '" class="item">'
						//+ '<h4 class="info">' + file.name + '</h4>'
						+ '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>'
						+ '<div id="' + file.id + '_delete" class="item">'
						+ '</div>');
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('word转换后内容：');
					if(callback) {
						callback(response.data);
					}
					$("#picker").attr("style", "display:none");
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
			});

			$("#ctlBtn").on('click', function() {
				uploader.upload();
				console.log("上传成功");  
			}); 
		},
		initEntrustTemplate : function($upload_div, callback) {
			var html = '<div id="thelist" class="uploader-list"></div>'
	             + '<div><div id="picker">选择文件</div>'  
	            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
	             + '</div>'  
	     $upload_div.html(html);
		
		 var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadEntrustTemplate',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
				pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'SelectFile',
					extensions : 'doc,docx',
					mimeTypes: 'application/msword',  
				    // mimeTypes: '.cer,'  
				}
			});
			uploader.on('fileQueued', function(file) {
				//$list.append('<div id="' + file.id + '" class="item">'
				$("#thelist").html('<div id="' + file.id + '" class="item">'
						//+ '<h4 class="info">' + file.name + '</h4>'
						+ '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>'
						+ '<div id="' + file.id + '_delete" class="item">'
						+ '</div>');
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('word转换后内容：');
					if(callback) {
						callback(response.data);
					}
					$("#picker").attr("style", "display:none");
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
			});

			$("#ctlBtn").on('click', function() {
				uploader.upload();
				console.log("上传成功");  
			}); 
		},
		initEntrustforceTemplate : function($upload_div, callback) {
			var html = '<div id="thelist" class="uploader-list"></div>'
	             + '<div><div id="picker">选择文件</div>'  
	            // + '<button id="ctlBtn" class="btn btn-default">开始上传</button>'
	             + '</div>'  
	     $upload_div.html(html);
		
		 var uploader = WebUploader.create({
			   // 选完文件后，是否自动上传。  
		       auto: true, 
		       
				// swf文件路径  
				swf : sys.rootPath + '/resources/js/webuploader/Uploader.swf',
//	 			formData : {
//	 				"name" : $("#requestDn").val()
//	 			},//参数列表  
				// 文件接收服务端。  
				server : sys.rootPath + '/file/uploadEntrustforceTemplate',
				// 选择文件的按钮。可选。  
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.  
				pick : '#picker',
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！  
				resize : false,
				
				// 只允许选择图片文件。  
				accept : {
					title : 'SelectFile',
					extensions : 'doc,docx',
					mimeTypes: 'application/msword',  
				    // mimeTypes: '.cer,'  
				}
			});
			uploader.on('fileQueued', function(file) {
				//$list.append('<div id="' + file.id + '" class="item">'
				$("#thelist").html('<div id="' + file.id + '" class="item">'
						//+ '<h4 class="info">' + file.name + '</h4>'
						+ '<p class="state">等待上传...</p>'
						+ '<p class="progress progress-bar">上传进度...</p>'
						+ '</div>'
						+ '<div id="' + file.id + '_delete" class="item">'
						+ '</div>');
			});

			uploader.on('uploadSuccess', function(file, response) {
				if(response.success) {
					$('#' + file.id).find('p.state').text('word转换后内容：');
					if(callback) {
						callback(response.data);
					}
					$("#picker").attr("style", "display:none");
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
			});

			$("#ctlBtn").on('click', function() {
				uploader.upload();
				console.log("上传成功");  
			}); 
		},
		delUploadFile: function(file) {
			webside.getData("/file/delete2.html", {delFile: file}, 
					  function(result) {
						  if(result.success) {
							  $("#thelist").html("已删除，可重新添加");
							  $("#picker").attr("style", "display:block");
						  } 
					  }, 
					  function(result) {    				  
				 		 });
		}
}