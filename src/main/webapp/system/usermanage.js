/* -----------------------变量区 begin------------------------------------------*/

var $operatetype;

/* -----------------------jquery主函数 begin------------------------------------------*/
$(function () {

    //初始化表格
    initTable();

    //查询按钮动作
    $("#queryuser").click(function(){
        queryuserlist();
    });

    $(".selectpicker").on("shown.bs.select",function(){
        codeSelect('#roleselect','role');
    });

    //保存按钮动作
    $("#saveuser").click(function () {
        $("#useraddform").submit();
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
        '<a id="edit" href="javascript:void(0)" title="编辑" data-toggle="modal" data-target="#userform" style="color: blue">',
        '编辑',
        '</a>  ',
        '<a id="delete" href="javascript:void(0)" title="删除" style="color: red">',
        '删除',
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

        $("#usertable").bootstrapTable('remove', {
            field: 'usercode',
            values: [row.usercode]
        })
    }
}


function userdetail() {

}


function useredit() {

}

function userdelete(usercode) {

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
                text: "啥都没查到 o(╯□╰)o",
                type: "error"
            });
        }
    });
}

$("#useraddform").validate({
    ignore: "hidden",

    rules: {
        newusercode:{
            required: true
        },
        newpassword:{
            required: true
        },
        newusername:{
            required: true
        }
    },

    messages: {
        newroleid: "角色编码不能为空",
        newrolename: "角色名称不能为空"
    },

    submitHandler: function (form) {
        saverole();
    }
})

