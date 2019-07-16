var systemPath = "http://localhost:8081";

/**
*@description: codeselect 公用方法 ，传入select组件的id值，要带#号，以及要查询代码的类型
*/
function codeSelect(elementid, codetype) {

    var params = {
        codetype: codetype
    }

    $.ajax({
        url: systemPath + "/controller/codeselect",
        type: "GET",
        dataType: "json",
        data: params,
        success: function (data) {
            var len = data.length;
            $(elementid).empty();//每次加载前要先清空
            for(var i = 0 ; i < len; i++)
            {
                $(elementid).append("<option value=" + data[i].code + ">" + data[i].codename + "</option>");
            }
            $(elementid).selectpicker('refresh');
            $(elementid).selectpicker('render');
        },
        fail: function () {
            swal({
                text: "啥都没查到 o(╯□╰)o",
                type: "error"
            });
        }
    });
}