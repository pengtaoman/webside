var companyStore = function(){
    var instance = this;
    
    instance.upLoad = function(exid, formData, callback){
        var url = "/file/upload1";
        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            /**
            * 必须false才会避开jQuery对 formdata 的默认处理
            * XMLHttpRequest会对 formdata 进行正确的处理
            */
            processData: false,
            /**
            *必须false才会自动加上正确的Content-Type
            */
            contentType: false,
            success: function (res) {
                if(res.success){
                    if(res.dataObject){
                        callback(res.dataObject);
                    }else{
                        showAlertWindow(REPORT_LENGTH_0);
                    }
                }else{
                    if(typeof res == "string"){
                        var resObj = JSON.parse(res);
                        if(resObj){
                            showAlertWindow(resObj.message);
                        }
                    }else{
                    	if(typeof obj == "string"){
                            if (obj.indexof("ERROR_UNLOGIN") != -1) {
                            	window.top.location.href = '/index.html';
                            } else {
                            	showAlertWindow(obj);
                            }
                        }else {
                        	showAlertWindow(obj.message);
                        }
                    }
                    callback(res.dataObject);
                }
                //progressbar.stopProgress();
            },
            error: function (res) {
                //progressbar.stopProgress();
            }
        });

    }
}