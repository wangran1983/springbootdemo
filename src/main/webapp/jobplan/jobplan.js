/* -----------------------变量区 begin------------------------------------------*/

/* -----------------------jquery主函数 begin------------------------------------------*/
$(function(){
    //初始化表格
    initTable();

    //初始化任务计划表格数据
    queryJobplanList();


    //初始化日期控件
    initDatePicker();

/*

    //查询按钮动作，填充table
    $("#queryrole").click(function () {
        queryrolelist();
    });

    $("#addrole").click(function () {
        $operatetype = "insert";
    })


    //提交表单保存角色信息，submit后跳转到$("#roleaddform").validate，juqery validate 控制
    $("#saverole").click(function () {
        $("#roleaddform").submit();
    })

    //提交表单前校验
    $("#roleaddform").validate({
        ignore: "hidden",

        rules: {
            newroleid:{
                required: true
            },
            newrolename:{
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
    });
*/

});


/* -----------------------成员函数 begin------------------------------------------*/
function initTable() {
    $("#jobplantable").bootstrapTable('destroy').bootstrapTable({
        columns: [
            [{
                title: '计划ID',
                field: 'jobCode',
                align: 'center',
                valign: 'middle',
                width: 50
            }, {
                title: '任务名称',
                field: 'jobName',
                align: 'center',
                valign: 'middle',
                width: 100
            }, {
                title: '计划描述',
                field: 'jobPlanDesc',
                align: 'center',
                valign: 'middle',
                width: 150
            }, {
                title: '执行频率',
                field: 'repeatInterval',
                align: 'center',
                valign: 'middle',
                width: 100
            },{
                title: '开始时间',
                field: 'startDate',
                align: 'center',
                valign: 'middle',
                width: 150
            },{
                title: '执行ip',
                field: 'paramValue',
                align: 'center',
                valign: 'middle',
                width: 100
            },{
                title: '启用/停止',
                field: 'useFlag',
                align: 'center',
                valign: 'middle',
                width: 50
            },{
                title: '运行状态',
                field: 'runState',
                align: 'center',
                valign: 'middle',
                width: 100
            },{
                title: '操作',
                field: 'operate',
                align: 'center',
                valign: 'middle',
                width: 150,
                events: window.operateEvents,
                formatter: operateFormatter
            }]
        ]
    });
}

/*操作列的格式*/
function operateFormatter(value, row, index) {
    return [
        '<a id="detail" href="javascript:void(0)" title="详情" data-toggle="modal" data-target="" style="color: green">',
        '详情',
        '</a>  ',
        '<a id="edit" href="javascript:void(0)" title="编辑" data-toggle="modal" data-target="" style="color: blue">',
        '编辑',
        '</a>  ',
        '<a id="delete" href="javascript:void(0)" title="停止" style="color: red">',
        '停止',
        '</a> ',
        '<a id="setpassword" href="javascript:void(0)" title="删除" data-toggle="modal" data-target=""  style="color: green">',
        '删除',
        '</a>'
    ].join('');
}

/*操作列的按钮方法*/
window.operateEvents = {
    'click #detail': function (e, value, row, index) {
        //userdetail(row.usercode);
    },
    'click #edit': function (e, value, row, index) {
        //useredit(row.usercode);
    },
    'click #delete': function (e, value, row, index) {
        //userdelete(row.usercode);
    },
    'click #setpassword': function (e, value, row, index) {
        //$("#userpasswordedit").val(row.usercode);
    }
}


function queryJobplanList() {
    $.ajax({
        url: systemPath + "/controller/queryjobplanlist",
        type: "GET",
        dateType: JSON,
        data: [],
        success: function (data) {
            $("#jobplantable").bootstrapTable('load',data);
        },
        error: function () {
            swal({
                title: "查询出错了 o(╯□╰)o",
                type: "error"
            });
        }
    });
}