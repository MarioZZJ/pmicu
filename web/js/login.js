$(function(){
    $(".wrapper .input-data input").focus(function () {
        $(this).siblings().addClass("entering");
        $(this).addClass("entering");
    })
    $(".wrapper .input-data input").blur(function () {
        if(""==$(this).val()){
            $(this).siblings().removeClass("entering");
        }
        $(this).removeClass("entering");
    })
    $("button#tosign").click(function () {
        $(".sig").toggle(500);
        $("h3").text("Register");
        $("input").text("");
    })
    $("button#tolgin").click(function () {
        $(".sig").toggle(500);
        $("h3").text("Login");
        $("input").text("");
    })
    $("#passwordcheck").change(function () {
        if(""!==$("input[name=password]").val()) {
            if ("" !== $("input#passwordcheck").val()) {
                // alert($("input#passwordcheck").val()+" | "+$("input[name=password]").val())
                if ($("input#passwordcheck").val() != $("input[name=password]").val()) {
                    $("#notification").text("您两次输入的密码不一致！");
                    $("#sign").attr("disabled", "disabled");
                } else {
                    $("#notification").text("");
                    $("#sign").removeAttr("disabled");
                }
            }

        }    })

    $("#sign").hover(function () {
        if(""!==$("input[name=username]").val() && ""!==$("input#passwordcheck").val() && ""!==$("input[name=email]").val()){
            $("#sign").removeAttr("disabled");
        }else{$("#sign").attr("disabled", "disabled");}
    })
})