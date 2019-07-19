
$(function(){

   $("#loginbtn").click(function() {
        $("#loginform").submit();
    });

    $("#loginform").validate({
        ignore: "hidden",

        rules: {
            usercode:{
                required: true
            },
            password:{
                required: true
            }
        },

        messages: {
            newroleid: "用户名不能为空",
            newrolename: "密码不能为空"
        },

        submitHandler: function (form) {
            login();
        }
    })
});


function login() {
    let user =
        {
           usercode: $("#usercode").val(),
           password: $("#password").val()
        };

        $.ajax({
            url: systemPath + "/userlogin",
            type: "POST",
            dataType: "json",
            data: user,
            success: function (data) {
                if(data.flag=='1')
                {
                    window.location.href = "../main/welcome.jsp";
                }
                else {
                    swal({
                        title: data.message,
                        type: "warning"
                    });
                }
            },
            error: function (data) {
                swal({
                    title: "登录失败 o(╯□╰)o",
                    type: "error"
                });
            }
        });
}
