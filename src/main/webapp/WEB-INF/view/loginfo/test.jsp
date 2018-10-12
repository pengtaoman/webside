<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<link rel="stylesheet"	href="${pageContext.request.contextPath }/resources/js/webuploader/css/webuploader.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/webuploader/js/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/common/filehelper.js"></script>

<script type="text/javascript">
function afterUpload(data) {
	$("#resultDiv").html(data);
// 	$("#resultDiv").attr('style','width:756px;border:1px solid #000000;')
	$("#resultDiv").find('div').each(function(){
		$(this).attr('style','width:95%;');
	});
	$("#resultDiv").find('img').each(function(){
		$(this).attr('style','width:95%;');
	});
	$("#resultDiv").find('span').each(function(){
		$(this).attr('style','width:95%;');
	});
}

$(function(){
	var $divupload = $("#uploader-demo")
	  FileHelper.initTemplate($divupload, afterUpload);
	
	$("#btnPrint").bind('click', function(){
		layer.open({
			  type: 1,
			  skin: 'layui-layer-rim', //加上边框
			  area: ['1090px', '794px'], //宽高
			  btn: ['打印']
			  ,yes: function(index, layero){
				  doPrint();
			  }
			  ,content: $('#resultDiv').html()
			});
	});
});

function doPrintLandscape() {
	//画表格
	var content = '<div id="printArea223311">' + $('#resultDiv').html()  + '</div>';
	
	//隐藏body，放置打印对象
	var scrollTop = $('body').scrollTop();
	$('body').hide();
	
	if($('#printArea223311').length == 0) {
		$('html').append(content);
	} else {
		$('#printArea223311').html(content);
	}
	$('#printArea223311').attr('style','width:1086px;border:1px solid #000000;')
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

function doPrintPortrait() {
	//画表格
	var content = '<div id="printArea223311">' + $('#resultDiv').html()  + '</div>';
	
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

function doPrint3(){
	window.open('print.html');
	
	var html = $('#resultDiv').innerHTML;
	layer.open({
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['1090px', '794px'], //宽高
		  btn: ['打印']
		  ,yes: function(index, layero){
			  window.document.body.innerHTML=$('#resultDiv').innerHTML;
			  //window.print();
		  }
		  ,content: 'print.html',
		  success: function(layero, index){
		    var body = layer.getChildFrame('body', index);
		    var iframeWin = window[layero.find('iframe')[0]['name']];
		    body.appendChild(html);
		    alert(body.html());
		  }
		});
}
</script>	

<div id="buttonGroup">   
   <button id="btnPrintPortrait" type="button" class="btn btn-info btn-sm" onclick="doPrintPortrait()">
	 	<i class="fa fa-print"></i>&nbsp;竖版打印
	</button>
   <button id="btnPrintLandscape" type="button" class="btn btn-info btn-sm" onclick="doPrintLandscape()">
	 	<i class="fa fa-print"></i>&nbsp;横板打印
	</button>
</div> 
<span style="font-size:10px;">
<div id="uploader-demo">  
   
</div> 

<!--style="width:756px;height:1086px;-->
<!--startprint-->
<div id="resultDiv">  
   
</div> 
<!--endprint-->
</span> 


