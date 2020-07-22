$(function(){
    $(".wrapper .input-data input").on({
        focus:function () {
        $(this).siblings().addClass("entering");
        $(this).addClass("entering");
        },
        blur:function () {
        if(""==$(this).val()){
            $(this).siblings().removeClass("entering");
        }
        $(this).removeClass("entering");
    }})
    $("button#tosign").click(function () {
        $(".sig").toggle(500);
        $("h3").text("Register");
        $("input").each(function () {
            $(this).val('');
            $(this).siblings().removeClass("entering");
        });
    })
    $("button#tolgin").click(function () {
        $(".sig").toggle(500);
        $("h3").text("Login");
        $("input").each(function () {
            $(this).val('');
            $(this).siblings().removeClass("entering");
        });
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
    $("input[name=username]").change(function () {
        if(""!==$("input[name=username]").val() && ""!==$("input[name=password]").val() && $("#sign").is(":hidden")){
            $("#lgin").removeAttr("disabled");
        }
    })
    $("input[name=password]").change(function () {
        if(""!==$("input[name=username]").val() && ""!==$("input[name=password]").val() && $("#sign").is(":hidden")){
            $("#lgin").removeAttr("disabled");
        }
    })

    $("#sign").hover(function () {
        if(""!==$("input[name=username]").val() && ""!==$("input#passwordcheck").val() && ""!==$("input[name=email]").val()){
            //do nothing disabled无法hover
        }else{$("#sign").attr("disabled", "disabled");}
    })
    $("#lgin").hover(function () {
        if(""!==$("input[name=username]").val() && ""!==$("input[name=password]").val()){
            //do nothing disabled无法hover
        }else{$("#lgin").attr("disabled", "disabled");}
    })

    $("input[name=username]").change(function () {
        if($("#lgin").is(":hidden")){
        $.post(
            "/pmicu/RegisCheckServlet",
            {"checkName":"username",
                "username":$(this).val()},
            function(result){
                $("#notification").text(result);
            }
        );}
    })
    ///^[1-9][0-9]{4,10}@qq.com/g
    $("input[name=email]").change(function () {
        // var qqmail = /^[1-9][0-9]{4,9}@qq\.com/i
        var whumail = /^[2][0-9]{12}@whu\.edu\.cn/i
        // alert($(this).val());
        // alert("QQ邮箱的验证结果:"+qqmail.test($(this).val()));
        // alert("WHU邮箱的验证结果:"+whumail.test($(this).val()));
        if(whumail.test($(this).val())){
            $("#notification").text("");
        }else{$("#notification").text("您输入的邮箱有误，当前仅支持whu邮箱注册");}
    })
    $("input[name=token]").change(function () {
        $.post(
            "/pmicu/RegisCheckServlet",
            {"checkName":"token",
                "username":$(this).val()},
            function(result){
                $("#notification").text(result);
            }
        );
    })
})