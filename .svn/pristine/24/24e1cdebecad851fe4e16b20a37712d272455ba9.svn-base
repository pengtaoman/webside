var dtGridColumns = [{
    id : 'title',
    title : '标题',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
    	return '<a href="javascript:void(0);" onclick="showDetails(\''+ dataNo +'\')">'+ value +'</a>';
    }
},{
    id : 'content',
    title : '内容',
    search : true,
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'status',
    title : '状态',
    type : 'string',
    columnClass : 'text-center',
    hideType : 'sm|xs',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == 1) {
            return '<span class="label label-sm label-success arrowed arrowed-righ">已读</span>';
        } else {
            return '<span class="label label-sm label-warning arrowed arrowed-righ">未读</span>';
        }
    }
}, {
    id : 'createTime',
    title : '时间',
    search : true,
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md'
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : true,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/sms/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,pdf,txt]',
    exportFileName : '消息列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30],
    onRowClick : function(value, record, column, grid, dataNo, columnNo, cell, row, extraCell, e){
    	row.children()[1].children[0].checked = !row.children()[1].children[0].checked;
    }
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    
    SearchPanel.SearchPanelWithLike($("#searchPanel1"), dtGridColumns, true, grid);
});

function showDetails(index) {
	var record = grid.exhibitDatas[index];
	
	if(record.status == 0) {
		var data ={
    			"ids" : record.id,
    		}			  
    	  //得到value
    	  webside.getData("/sms/updateStatusBatch.html", data, 
    			  function(result) {
    				  if(result.success) {
    					  var cnt = parseInt($("#smsUnreadCnt").html());
    					  cnt = cnt - parseInt(result.data);
    					  if(cnt < 0) {
    						  cnt = 0;
    					  }
    					  $("#smsUnreadCnt").html(cnt);
    					  grid.refresh(true);
    				  } 
    			  }, 
    			  function(result) {    				  
    		 		 });
	}
	
	var html = '<div  style="margin-top: 10px;margin-left: 17px;"><textarea rows = 10 cols="65" readonly="readonly">';
		html += record.content;
		html += '</textarea></div>';
		
	layer.open({
		  type: 1,
		  title: record.title,
		  shadeClose: true, //开启遮罩关闭
		  skin: 'layui-layer-demo', //加上边框
		  area: ['520px', '320px'], //宽高
		  content: html  //record.content.replace(/(\r\n)|(\n)/g,'<br>')
		});
}

function customSearch(){
	//得到value
	  webside.getData("/sms/unreadcnt.html", {}, 
			  function(result) {
				  if(result.success) {
					  $("#smsUnreadCnt").html(result.data);
				  }
			  });
	  grid.refresh(true);
}

function filterUpdateStatus(record) {
	if(record.status == 1) {
		return false;
	} 
	return true;
}
