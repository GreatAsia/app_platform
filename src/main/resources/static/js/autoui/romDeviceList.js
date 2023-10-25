//获取设备列表
function getDeviceList() {
    $("#deviceResult").text("");
    $("#deviceResult").text("运行中...");
    var url = "/device/getlist";
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 6000,
        success: function (data) {

            if (data.code == 200) {

                var deviceInfo = data.data;
                console.log("data==" + JSON.stringify(deviceInfo));
                $("#deviceResult").text("完成");
                $("#deviceDesc").text("设备总数:" + deviceInfo.length);
            } else {
                alert("获取失败：" + JSON.stringify(data));
                $("#deviceResult").text("");
            }

        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#deviceResult").text("");
        }
    });


};



<!--push dev账号-->
function pushDevAccount() {

    var envName = "dev";
    $("#isPushDevAccount").text(" ");
    $("#isPushDevAccount").text("运行中...");
    var url = "/uiPad/account/push?envName=" + envName;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                $("#isPushDevAccount").text("完成");
            } else {
                alert("获取失败：" + data.msg);
                $("#isPushDevAccount").text(" ");
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#isPushDevAccount").text(" ");
        }
    });
};


<!--push stress账号-->
function pushStressAccount() {

    var envName = "stress";
    $("#isPushStressAccount").text(" ");
    $("#isPushStressAccount").text("运行中...");
    var url = "/uiPad/account/push?envName=" + envName;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                $("#isPushStressAccount").text("完成");
            } else {
                alert("获取失败：" + data.msg);
                $("#isPushStressAccount").text(" ");
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#isPushStressAccount").text(" ");
        }
    });
};

<!--push online账号-->
function pushOnlineAccount() {

    var envName = "online";
    $("#isPushOnlineAccount").text(" ");
    $("#isPushOnlineAccount").text("运行中...");
    var url = "/uiPad/account/push?envName=" + envName;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                $("#isPushOnlineAccount").text("完成");
            } else {
                alert("获取失败：" + data.msg);
                $("#isPushOnlineAccount").text(" ");
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#isPushOnlineAccount").text(" ");
        }
    });
};


//添加学生账号模态框
function showAddDevModal(obj) {
    $("#addDevModal").modal({
        backdrop: 'static',
        keyboard: false
    });

}

//添加学生账号模态框
function showAddStressModal(obj) {
    $("#addStressModal").modal({
        backdrop: 'static',
        keyboard: false
    });

}

//添加学生账号模态框
function showAddOnlineModal(obj) {
    $("#addOnlineModal").modal({
        backdrop: 'static',
        keyboard: false
    });

}

//添加账号
function addDevAccount() {

    var envName = "dev";
    var studentList = $('#addDevStudent').val().trim().split("\n").join(";");

    if (!studentList) {
        alert("请输入学生们");
        return false;
    }
    addAcount(envName, studentList);

};

//添加账号
function addStressAccount() {

    var envName = "stress";
    var studentList = $('#addStressStudent').val().trim().split("\n").join(";");

    if (!studentList) {
        alert("请输入学生们");
        return false;
    }
    addAcount(envName, studentList);

};


//添加账号
function addOnlineAccount() {

    var envName = "online";
    var studentList = $('#addOnlineStudent').val().trim().split("\n").join(";");

    if (!studentList) {
        alert("请输入学生们");
        return false;
    }
    addAcount(envName, studentList);

};

function addAcount(envName, studentList) {
    var url = "/uiPad/account/inserts?envName=" + envName + "&studentList=" + studentList;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 60000,
        success: function (data) {

            if (data.code == 200) {
                //隐藏模态框，刷新页面
                $("#addModal").modal('hide');
                location.reload();

            } else {
                alert("添加失败：" + JSON.stringify(data));
            }

        },
        error: function (e) {
            alert("添加失败：" + JSON.stringify(e));
        }
    });


}


function runAdb() {

    $("#isRunAdb").text(" ");
    $("#isRunAdb").text("运行中...");
    var cmd = $("#runAdbDesc").val();

    var SAVEDATA = [];
    var requestData = {
        "cmd": cmd,
    };

    SAVEDATA.push(requestData);

    $.ajax({
        type: "POST",
        url: "/device/run/shell",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(requestData),
        timeout: 60000,
        success: function (data) {

            if (data.code == 200) {

                $("#isRunAdb").text("完成");
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#isRunAdb").text(" ");
        }
    });
};

//添加包名
function showAddPackageModal(obj) {
    $("#addPackage").modal({
        backdrop: 'static',
        keyboard: false
    });

}

<!--run monkey-->
function runMonkey() {

    var runTime = $("#runMonkeyTime").val();
    var packageS = $('#addMonkeyPackage').val().trim().split("\n").join(",");
    $("#isMonkey").text(" ");
    $("#isMonkey").text("运行中...");

    var url = "/device/monkey/run?packageS=" + packageS + "&runTime=" + runTime;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                $("#isMonkey").text("完成");
            } else {
                alert("获取失败：" + data.msg);
                $("#isMonkey").text(" ");
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
            $("#isMonkey").text(" ");
        }
    });
};


function installTestApp() {

    var url = "/device/apk/install?apkName=local";
    $("#installApkResult").text("安装中...");
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                $("#installApkResult").text("安装完成");

            } else {
                alert("安装失败：" + data.msg);
                $("#installApkResult").text("");
            }
        },
        error: function (e) {
            alert("安装失败：" + JSON.stringify(e));
            $("#installApkResult").text("");
        }
    });
};

function openUploadPage() {
    var img = ['jpg', 'jpeg', 'png', 'gif', 'bmp']; //图片
    var txt = ['txt', 'sql', 'log'];  //文字
    var out = ['cfg', 'dat', 'hlp', 'tmp'];  //文字
    var ott = ['xlsx', 'xls', 'pdf', 'docx', 'doc', 'pptx',];    //表格，幻灯片，WORD，PDF
    var sin = ['mpg', 'mpeg', 'avi', 'rm', 'rmvb', 'mov', 'wmv', 'asf', 'dat', 'mp4']; //视频
    var ein = ['cd', 'ogg', 'mp3', 'asf', 'wma', 'wav', 'mp3pro', 'rm', 'real', 'ape', 'module', 'midi', 'vqf']; //音频
    var spe = ['jar', 'war', 'zip', 'rar', 'tag.gz'];//压缩包
    var exe = ['exe', 'bat', 'com', 'msi']; //可执行文件
    var zat = ['chm', 'ink'];    //特殊文件
    var viw = ['ftl', 'htm', 'html', 'jsp']; //页面
    var rol = ['js', 'css'];
    var apk = ['apk'];

    $("#updateAPK").modal({
        backdrop: 'static',
        keyboard: false
    });
    $("#installApkResult").text("");
    initFileInput('#files', '/device/filesupload', apk, {});
};

function initFileInput(formGropId, url, fileCan, extraData) {
    $(formGropId).fileinput({
        theme: "explorer", //主题
        language: 'zh', //设置语言
        uploadUrl: url,
        // uploadExtraData: extraData,
        allowedFileExtensions: fileCan,//接收的文件后缀
        maxFileSize: 1024 * 100,     //1024*20Kb = 20Mb
        minFileCount: 1,
        enctype: 'multipart/form-data',
        showCaption: true,//是否显示标题
        showUpload: true, //是否显示上传按钮
        textEncoding: 'utf-8',
        browseClass: "btn btn-primary", //按钮样式
        overwriteInitial: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        //previewFileIcon: '<i class="fa fa-file"></i>',
        //initialPreviewAsData: true, // defaults markup
        //preferIconicPreview: false, // 是否优先显示图标  false 即优先显示图片
        //showPreview: true,
        /*不同文件图标配置*/

        allowedPreviewTypes: false, //不预览
        previewSettings: {
            image: {width: "100px", height: "120px"},
        },
        layoutTemplates: {
            //actionUpload: '',   //取消上传按钮
            // actionZoom: ''      //取消放大镜按钮
        }

    }).on('filepreupload', function (event, data, previewId, index) {
        console.info(data);
    }).on("fileuploaded", function (event, data, previewId, index) {
        console.log('文件上传成功！' + data);
    }).on('fileerror', function (event, data, msg) {
        console.log('文件上传失败！' + msg);
    })
}