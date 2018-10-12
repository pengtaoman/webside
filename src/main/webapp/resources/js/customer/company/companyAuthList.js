var dtGridColumns = [{
    id : 'id',
    title : '编号',
    type : 'number',
    columnClass : 'text-center',
    hideType : 'xs',
    headerClass : 'dlshouwen-grid-header'
},  {
    id : 'userName',
    title : '平台用户',
    type : 'string',
    search : '1',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
},{
    id : 'companyName',
    title : '公司名',
    search : '1',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'authCode',
    title : '授权',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == "2") {
            return '全部权限';
        } else if (value == "1") {
            return '查看';
        } else {
            return '无';
        }
    }
},{
    id : 'note',
    title : '说明',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md'
}, {
    id : 'createTime',
    title : '创建时间',
    search : '1',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
}, {
    id : 'updateTime',
    title : '更新时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'sm|xs|md',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        if (value == null) {
            return '';
        } else {
            return value;
        }
    }
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
    loadURL : sys.rootPath + '/companyauth/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh|print|export[excel,csv,pdf,txt]',
    exportFileName : '企业授权信息',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
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

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.refresh(true);
}

function showDetails(recordId) {
	//alert(recordId);
}

function showBigImage(imgsrc) {	
	layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  area: ['800px', '600px'],
		  skin: 'layui-layer-nobg', //没有背景色
		  anim: -1,
		  shadeClose: true,
		  content: '<img src="'+imgsrc+'" height="600"  width="800">'
		});
}
function onExamineClick() {
	var rows = grid.getCheckedRecords();
	
	layer.confirm('确认审核通过', {
        icon : 3,
        title : '提示',
		btn: ['通过','取消'] //按钮
    }, function(index,val) {
    	var ids = [];
        
         $.each(rows, function(index, value) {
        	 ids.push(rows[index].id);
         });
        
    	var data ={
    			"ids" : ids.join(','),
    			status:'1',
        		remark:''
    		}			  
    	  
    	  //得到value
    	  webside.getData("/company/examineBatch.html", data, 
    			  function(result) {
    				  if(result.isSuccess) {
    					  layer.msg("操作成功", {icon : 1});
    					  grid.refresh(true);
    				  } else {
    					  layer.msg(result.msg); 
    				  }
    			  }, 
    			  function(result) {
    				  layer.msg("系统出错，请重试或者联系管理员", {icon : 0}); 
    		 		 });
    	layer.close(index);
    });
	
}

function onExamineNotClick() {
	var rows = grid.getCheckedRecords();
    if (rows.length < 1) {
    	layer.msg('请选择审核的记录', {icon: 6}); 
    	return;
    }
    
    if (rows.length > 1) {
    	layer.msg('请选择单条记录', {icon: 6}); 
    	return;
    }
    
	layer.prompt({
		  formType: 2,
		  value: '',
		  title: '审核不通过理由',
		  area: ['400px', '240px'] //自定义文本域宽高
	}, function(value, index, elem){
		var data ={
			id:rows[0].id,
			status:'0',
			remark:value
		}			  
	  
	  //得到value
	  webside.getData("/company/examine.html", data, 
			  function(result) {
				  if(result.isSuccess) {
					  layer.msg("操作成功", {icon : 1});
					  grid.refresh(true);
				  } else {
					  layer.msg(result.msg, {icon : 1}); 
				  }
			  }, 
			  function(result) {
				  layer.msg("系统出错，请重试或者联系管理员", {icon : 0}); 
	 		 });
	  layer.close(index);
	});
}

