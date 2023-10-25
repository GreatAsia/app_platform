function isJSON(str) {
    try {
        /*result=JSON.stringify(str)*/
        JSON.parse(str);
        return true;
    } catch (e) {
        return false;
    }
}

function loginPlatform() {


    var username = $("#username").val().trim();
    var password = $("#password").val().trim();
    var msg = document.getElementById("msg");

    if (!username) {
        $(msg.style.display = "block");
        $("#msg").text("Please input username");
        return false;
    }
    if (!password) {
        $(msg.style.display = "block");
        $("#msg").text("Please input password.");
        return false;
    }
    var requestData = {
        "username": username,
        "password": password
    };


    var url = "/login";
    var isRedirect=true;
    $.ajax({
        async: false,
        type: "POST",
        url: url,
        dataType: "text",
        /*contentType: "application/json",*/
        data: requestData,
        timeout: 6000,
        success: function (result) {
            console.log(isJSON(result))
            if (isJSON(result)) {
                //console.log(isRedirect)
                isRedirect = false;
                console.log(isRedirect)
                $(msg.style.display = "block");
                $("#msg").text("Please check the username and password");
            }
        },
        error: function (result) {
            console.log(result);
            $(msg.style.display = "block");
            $("#msg").text("login error");
            isRedirect = false;

        }
    });
    //isRedirect=false;
    console.log(isRedirect)
    return isRedirect;

}


document.onkeydown = function (e) {
    var ev = document.all ? window.event : e;
    if (ev.keyCode == 13) {
        loginPlatform();
    }
}

