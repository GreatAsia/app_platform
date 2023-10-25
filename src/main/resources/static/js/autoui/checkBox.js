function runAppMoreCase() {
    var boxs = document.getElementsByTagName("input");
    var arr = [];
    for (i = 0; i < boxs.length; i++) {
        if (boxs[i].checked == true) {
            arr.push(boxs[i].value);
        }
    }


    var url = "/device/run/local/more?caseIds=" + arr + "&runFrom=app";
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                alert("运行中,请稍后查看结果");
            } else {
                alert("获取失败：" + data.msg);
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
        }
    });
}


function runMoreCase() {

    var boxs = document.getElementsByTagName("input");
    var arr = [];
    for (i = 0; i < boxs.length; i++) {
        if (boxs[i].checked == true) {
            arr.push(boxs[i].value);
        }
    }

    var url = "/device/run/local/more?caseIds=" + arr + "&runFrom=rom";
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        contentType: "application/json",
        timeout: 600000,
        success: function (data) {

            if (data.code == 200) {

                alert("运行中,请稍后查看结果");
            } else {
                alert("获取失败：" + data.msg);
            }
        },
        error: function (e) {
            alert("获取失败：" + JSON.stringify(e));
        }
    });
}


function checkAll() {

    $("input[name='imgVo']:checkbox").prop("checked", true);
}

function checkNo() {
    $("input[name='imgVo']").prop('checked', false);

}
