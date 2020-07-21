function undermaintainceNotification() {
    // alert("施工中，暂未开通该功能，敬请期待")
    alert("Success");
}

var flag = -1;

$(function () {
    $("#btn03").click(function () {
        if (flag<0){
            $("#desc3").hide();
        }
        else{$("#desc3").show();}
        flag = -flag

    })

})


