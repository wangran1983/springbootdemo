/* -----------------------变量区 begin------------------------------------------*/

/* -----------------------jquery主函数 begin------------------------------------------*/
$(function () {

    //初始化表格
    initTable();

    //查询按钮动作
    $("#queryuser").click(function(){
        queryuserlist();
    });

    $("#roleselect").on("shown.bs.select",function(){
        codeSelect('#roleselect','role');
    });

    //新增保存按钮动作
    $("#saveuser").click(function () {
        $("#useraddform").submit();
    });

    //新增用户表单校验
    $("#useraddform").validate({
        ignore: "hidden",

        rules: {
            newusercode:{
                required: true
            },
            newpassword:{
                required: true,
                minlength: 5
            },
            newusername:{
                required: true
            },
            newemail:{
                email: true
            }
        },

        messages: {
            newusercode: "用户名不能为空",
            newpassword:
                {
                    required: "密码不能为空",
                    minlength: "密码最少为6位"
                },
            newusername: "姓名不能为空"
        },

        submitHandler: function (form) {
            saveuser();
        }
    });

    //修改保存按钮动作
    $("#saveuseredit").click(function () {
        $("#useredit").submit();
    });

   //修改用户表单校验
    $("#useredit").validate({
        ignore: "hidden",

        rules: {
            usernameedit:{
                required: true
            },
            emailedit:{
                email: true
            }
        },

        submitHandler: function (form) {
            saveuseredit();
        }
    });

    //修改密码
    $("#savepassword").click(function () {
        $("#pwdedit").submit();
    });

    //修改用户表单校验
    $("#pwdedit").validate({
        ignore: "hidden",

        rules: {
            passwordedit:{
                required: true,
                minlength: 6
            },
            passwordeditagain:{
                required: true,
                equalTo: "#passwordedit",
                minlength: 6
            }
        },

        messages: {
            passwordedit:
                {
                    minlength: "密码最少为6位"
                },
            passwordeditagain:
                {
                    equalTo: "两次输入必须相同",
                    minlength: "密码最少为6位"
                }
        },

        submitHandler: function (form) {
            setpassword();
        }
    });

})


/* -----------------------其他函数 begin------------------------------------------*/
function initTable() {
    $("#usertable").bootstrapTable('destroy').bootstrapTable({
        columns: [
            [{
                title: '用户ID',
                field: 'usercode',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                title: '姓名',
                field: 'name',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                title: '手机',
                field: 'mobile',
                align: 'center',
                valign: 'middle',
                width: 150
            }, {
                title: '邮箱',
                field: 'email',
                align: 'center',
                valign: 'middle',
                width: 100
            },{
                title: '操作',
                field: 'operate',
                align: 'center',
                valign: 'middle',
                width: 100,
                events: window.operateEvents,
                formatter: operateFormatter
            }]
        ]
    })
}

/*操作列的格式*/
function operateFormatter(value, row, index) {
    return [
        '<a id="detail" href="javascript:void(0)" title="详情" data-toggle="modal" data-target="#userformdetail" style="color: green">',
        '详情',
        '</a>  ',
        '<a id="edit" href="javascript:void(0)" title="编辑" data-toggle="modal" data-target="#userformedit" style="color: blue">',
        '编辑',
        '</a>  ',
        '<a id="delete" href="javascript:void(0)" title="删除" style="color: red">',
        '删除',
        '</a> ',
        '<a id="setpassword" href="javascript:void(0)" title="设置密码" data-toggle="modal" data-target="#pwdformedit"  style="color: green">',
        '设置密码',
        '</a>'
    ].join('');
}

/*操作列的按钮方法*/
window.operateEvents = {
    'click #detail': function (e, value, row, index) {
        userdetail(row.usercode);
    },
    'click #edit': function (e, value, row, index) {
        useredit(row.usercode);
    },
    'click #delete': function (e, value, row, index) {
        userdelete(row.usercode);
    },
    'click #setpassword': function (e, value, row, index) {
        setpassword(row.usercode);
    }
}

function userdetail(usercode) {
    codeSelect('#roleselectdetail','role');//先初始化好下拉框

    $.ajax({
        url: systemPath + "/controller/getuserdetail",
        type: "GET",
        dateType : "json",
        data: {
            usercode : usercode
        },

        success: function (data) {
            $("#usercodedetail").val(data.usercode);
            $("#usernamedetail").val(data.name);
            $("#mobiledetail").val(data.mobile);
            $("#emaildetail").val(data.email);

            let roles = data.cmsUserroles;
            let len = roles.length;
            let roleids = [];
            for(let i = 0; i < len; i++){
                roleids.push(roles[i].roleid);
            }
            $("#roleselectdetail").selectpicker('val', roleids);
            $("#roleselectdetail").selectpicker("refresh");
        },

        fail: function () {
            swal({
                title: "o(╯□╰)o，可能网络有问题！",
                type: "warning",
            });
        }

    })
}


function useredit(usercode) {
    codeSelect('#roleselectedit','role');//先初始化好下拉框

    $.ajax({
        url: systemPath + "/controller/getuserdetail",
        type: "GET",
        dateType : "json",
        data: {
            usercode : usercode
        },

        success: function (data) {
            $("#usercodeedit").val(data.usercode);
            $("#usernameedit").val(data.name);
            $("#mobileedit").val(data.mobile);
            $("#emailedit").val(data.email);

            let roles = data.cmsUserroles;
            let len = roles.length;
            let roleids = [];
            for(let i = 0; i < len; i++){
                roleids.push(roles[i].roleid);
            }
            $("#roleselectedit").selectpicker('val', roleids);
            $("#roleselectedit").selectpicker("refresh");
        },

        fail: function () {
            swal({
                title: "o(╯□╰)o，可能网络有问题！",
                type: "warning",
            });
        }

    })

}


function userdelete(usercode) {
    swal({
            title: "确定删除吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "确 定",
            cancelButtonText:"再想想",
            closeOnConfirm: false
        },
        function(){
            userdeleteCallback(usercode);
        });
}

function userdeleteCallback(usercode) {
    let params = {
        usercode: usercode
    }

    $.ajax({
        url: systemPath + "/controller/deleteuser",
        type: "POST",
        dataType: "json",
        data: params,
        success: function (data) {
            if(data.flag=='1')
            {
                swal({
                        title: "删除成功",
                        type: "success",
                    });

                $("#usertable").bootstrapTable('remove', {
                    field: 'usercode',
                    values: usercode
                })
            }

        },
        error: function () {
            swal({
                title: "删除失败 o(╯□╰)o",
                type: "error"
            });
        }
    });
}

function setpassword(usercode) {

    let password = $("#newpassword").val();

    let user = {
        usercode: usercode,
        password: password
    };

    $.ajax({
        url: systemPath + "/controller/setpassword",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(user),
        contentType: "application/json",
        success: function (data) {
            if(data.flag == '1'){
                swal({
                        title: "密码修改成功！",
                        type: "success",
                    },
                    function () {
                        $("#pwdformedit").modal('hide');//保存成功则关闭模态框，并且重置模态框
                        window.location.reload();//重新刷新页面
                    });
            }
            else {
                swal({
                    title: data.message,
                    type : "error"
                });
            }
        },
        error: function () {
            swal({
                title: "保存失败，请联系管理员",
                type : "error"
            });
        }
    });
}


function queryuserlist() {

    var usercode = $("#usercode").val();
    var params = {
        usercode: usercode
    }


    $.ajax({
        url: systemPath + "/controller/queryusers",
        type: "GET",
        dataType: "json",
        data: params,
        success: function (data) {
            $("#usertable").bootstrapTable('load', data);
        },
        fail: function () {
            swal({
                title: "啥都没查到 o(╯□╰)o",
                type: "error"
            });
        }
    });
}

/**保存用户*/
function saveuser() {
    let roles = $("#roleselect").val();
    let usercode = $("#newusercode").val();
    let userrolelist = [];

    if(roles == null)
    {
        swal({
            title: "角色不能为空",
            type : "warning"
        });
        return;
    }

    let len = roles.length;
    for(let i = 0;i < len; i++){
        let userrole = {};
        userrole.usercode = usercode;
        userrole.roleid = roles[i];
        userrolelist.push(userrole);
    }

    let user = {
        usercode: usercode,
        password: $("#newpassword").val(),
        name: $("#newusername").val(),
        mobile: $("#newmobile").val(),
        email: $("#newemail").val(),
        cmsUserroles: userrolelist
    };

    $.ajax({
        url: systemPath + "/controller/saveuser",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(user),
        contentType: "application/json",
        success: function (data) {
            if(data.flag == '1'){
                swal({
                        title: "保存成功！",
                        type: "success",
                    },
                    function () {
                        $("#useraddform").modal('hide');//保存成功则关闭模态框，并且重置模态框
                        window.location.reload();//重新刷新页面
                    });
            }
            else {
                swal({
                    title: data.message,
                    type : "error"
                });
            }
        },
        error: function () {
            swal({
                title: "保存失败，请联系管理员",
                type : "error"
            });
        }
    });
}

/**保存修改*/
function saveuseredit() {
    let roles = $("#roleselectedit").val();
    let usercode = $("#usercodeedit").val();
    let userrolelist = [];

    if(roles == null)
    {
        swal({
            title: "角色不能为空",
            type : "warning"
        });
        return;
    }

    let len = roles.length;
    for(let i = 0;i < len; i++){
        let userrole = {};
        userrole.usercode = usercode;
        userrole.roleid = roles[i];
        userrolelist.push(userrole);
    }

    let user = {
        usercode: usercode,
        name: $("#usernameedit").val(),
        mobile: $("#mobileedit").val(),
        email: $("#emailedit").val(),
        cmsUserroles: userrolelist
    };

    $.ajax({
        url: systemPath + "/controller/edituser",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(user),
        contentType: "application/json",
        success: function (data) {
            if(data.flag == '1'){
                swal({
                        title: "修改成功！",
                        type: "success",
                    },
                    function () {
                        $("#userformedit").modal('hide');//保存成功则关闭模态框，并且重置模态框
                        window.location.reload();//重新刷新页面
                    });
            }
            else {
                swal({
                    title: data.message,
                    type : "error"
                });
            }
        },
        error: function () {
            swal({
                title: "保存失败，请联系管理员",
                type : "error"
            });
        }
    });
}
