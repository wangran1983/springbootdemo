<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <title>cms</title>
    <link href="../assets/fontAwesome/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="../assets/css/lib/themify-icons.css" rel="stylesheet">
    <link href="../assets/css/lib/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/lib/nixon.css" rel="stylesheet">
    <link href="../assets/lib/lobipanel/css/lobipanel.min.css" rel="stylesh1eet">
    <link href="../assets/css/lib/sidebar.css" rel="stylesheet">
    <link href="../assets/css/style.css" rel="stylesheet">
    <link href="../assets/bstable/bootstrap-table.css" rel="stylesheet"/>
    <link href="../assets/datetime/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="../assets/treeview/bootstrap-treeview.min.css" rel="stylesheet">
    <link href="../assets/css/lib/sweetalert/sweetalert.css" rel="stylesheet"/>
</head>

<body>
    <div class="sidebar sidebar-hide-to-small sidebar-shrink sidebar-gestures">
        <div class="nano">
            <div class="nano-content">
                <ul>
                    <%--动态菜单end--%>
                    <c:forEach items="${menus}" var="menu" varStatus="loop">
                        <c:if test="${(menu.parentmenuid == '0000')&&(menu.menulink != null)}">
                            <li><a href="<c:url value="${menu.menulink}" />"><i class="ti-home"></i>${menu.menuname}</a></li>
                        </c:if>

                        <c:if test="${(menu.parentmenuid == '0000')&&(menu.menulink == null)&&(menu.menuid != param.pmenuid)}">
                            <li>
                                <a class="sidebar-sub-toggle"><i class="ti-user"></i>${menu.menuname}<span class="sidebar-collapse-icon ti-angle-down"></span></a>
                                <ul>
                                    <c:forEach items="${menus}" var="menuchild" varStatus="loop">
                                        <c:if test="${menuchild.parentmenuid == menu.menuid}">
                                            <li><a href="<c:url value="${menuchild.menulink}?menuid=${menuchild.menuid}&pmenuid=${menuchild.parentmenuid}"/>">${menuchild.menuname}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>

                        <c:if test="${(menu.parentmenuid == '0000')&&(menu.menulink == null)&&(menu.menuid == param.pmenuid)}">
                            <li class="active open">
                                <a class="sidebar-sub-toggle"><i class="ti-user"></i>${menu.menuname}<span class="sidebar-collapse-icon ti-angle-down"></span></a>
                                <ul>
                                    <c:forEach items="${menus}" var="menuchild" varStatus="loop">
                                        <c:if test="${(menuchild.parentmenuid == menu.menuid)&&(menuchild.menuid == param.menuid)}">
                                            <li><a class="beselect" href="<c:url value="${menuchild.menulink}?menuid=${menuchild.menuid}&pmenuid=${menuchild.parentmenuid}"/>">${menuchild.menuname}</a></li>
                                        </c:if>
                                        <c:if test="${(menuchild.parentmenuid == menu.menuid)&&(menuchild.menuid != param.menuid)}">
                                            <li><a href="<c:url value="${menuchild.menulink}?menuid=${menuchild.menuid}&pmenuid=${menuchild.parentmenuid}"/>">${menuchild.menuname}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>

                    <%--动态菜单end--%>

                    <li><a href="<c:url value="/userlogout" />"><i class="ti-close"></i>退出系统</a></li>
            </div>
        </div>
    </div>
    <!-- /# sidebar -->
    <div class="header">·
        <div class="pull-left">
            <div class="logo">
                <a href="index.jsp">
                    <img id="logoImg" src="logo/logo.png" data-logo_big="logo/logo.png" data-logo_small="logo/logoSmall.png" alt="Nixon" />
                </a>
            </div>
            <div class="hamburger sidebar-toggle">
                <span class="ti-menu"></span>
            </div>
        </div>
        <div class="pull-right p-r-15">
          <%--头部右侧--%>
        </div>
    </div>
    <!-- END chat Sidebar-->
    <div class="content-wrap">
        <div class="main">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-8 p-0">
                        <div class="page-header">
                            <div class="page-title">
                                <h1>角色管理</h1>
                            </div>
                        </div>
                    </div>
                    <!-- /# column -->
                    <div class="col-lg-4 p-0">
                        <div class="page-header">
                            <div class="page-title">
                                <ol class="breadcrumb text-right">
                                    <li class="active">系统管理</li>
                                    <li><a href="#">角色管理</a></li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <!-- /# column -->
                </div>
                <!-- /# row -->
                <div class="main-content">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card alert">
                                <div class="card-header">
                                    <h4>查询条件</h4>
                                </div>
                                <div class="card-body">
                                    <div class="horizontal-form-elements">
                                        <form class="form-horizontal">
                                            <div class="row">
                                                <div class="col-md-4 ">
                                                    <div class="form-group ">
                                                        <label class="col-md-5 control-label">角色编码</label>
                                                        <div class="col-md-7">
                                                            <input id="roleid" type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label class="col-md-5 control-label">角色名称</label>
                                                        <div class="col-md-7">
                                                            <input id="rolename" type="text" class="form-control ">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label class="col-md-5 control-label">创建时间</label>
                                                        <div class="col-md-7">
                                                            <input id="makedate" type="text" class="form-control ">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                            </div>
                                            <div class="row">
                                                <input id="queryrole" type="button" value="查 询" class="btn btn-primary m-b-10 m-l-5"></input>
                                                <input id="addrole" type="button" value="增 加" class="btn btn-primary m-b-10 m-l-5" data-toggle="modal" data-target="#roleform"></input>
                                            </div>
                                        </form><!--/#form-->
                                    </div>
                                </div>
                            </div><!-- /# card -->
                        </div><!-- /# column -->

                    </div>
                    <!-- /# form end row -->
                    <!-- /# table row begin -->
                    <div class="row">


                        <div class="col-lg-12">
                            <div class="card alert">
                                <div class="card-header">
                                    <h4>查询结果</h4>
                                </div>
                                <div class="card-body">
                                            <div class="row">
                                                <table id="roletable"
                                                       class="table  table-bordered table-striped"
                                                       data-toggle="table"
                                                       data-click-to-select="true"
                                                       data-pagination="true"
                                                       data-show-footer="true"
                                                       data-side-pagination="client"
                                                       <%--data-url="../json/data1.json"--%>
                                                          >
                                                </table>
                                            </div>
                                    </div>
                                </div>
                            </div><!-- /# card -->

                    </div>
                    <!-- /# row -->
                    <div class="row">
                        <!-- 增加角色表单（Modal） -->
                        <div class="modal fade" id="roleform" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog" style="width:500px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times  关闭
                                        </button>
                                        <h4 class="modal-title" id="myModalLabel">
                                            增加角色
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="horizontal-form-elements">
                                            <form class="form-horizontal" id = "roleaddform" role="form">
                                                <div class="row">
                                                    <div class="col-md-10 ">
                                                        <div class="form-group ">
                                                            <label class="col-sm-4 control-label">角色编码</label>
                                                            <div class="col-sm-7">
                                                                <input id="newroleid" name="newroleid" type="text" class="form-control" >
                                                            </div>
                                                        </div>
                                                    </div><!-- /# column -->
                                                    <div class="col-md-10">
                                                        <div class="form-group">
                                                            <label class="col-sm-4 control-label">角色名称</label>
                                                            <div class="col-sm-7">
                                                                <input id="newrolename" name="newrolename" type="text" class="form-control" >
                                                            </div>
                                                        </div>
                                                    </div><!-- /# column -->
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-10 ">
                                                        <label class="col-sm-4 control-label">角色菜单</label>
                                                        <div class="col-sm-7">
                                                            <div id="menutree" class="treeview">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 ">
                                                    </div>
                                                </div>
                                                          <%--  <label class="col-sm-5 control-label">角色菜单</label>--%>
                                            </form>
                                            <!--/#form-->
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="button" value="保存" id="saverole"  class="btn btn-primary">
                                        </input>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                    </div>
                    <!-- /# row -->
                    <div class="row">
                        <!-- 角色详情（Modal） -->
                        <div class="modal fade" id="roleformdetail" tabindex="1" role="dialog" aria-labelledby="myModalLabeldetail" aria-hidden="true">
                            <div class="modal-dialog" style="width:500px">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times  关闭
                                        </button>
                                        <h4 class="modal-title" id="myModalLabeldetail">
                                            角色详情
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="horizontal-form-elements">
                                            <form class="form-horizontal" id = "roledetail" role="form">
                                                <div class="row">
                                                    <div class="col-md-10 ">
                                                        <div class="form-group ">
                                                            <label class="col-sm-4 control-label">角色编码</label>
                                                            <div class="col-sm-7">
                                                                <input id="roleiddetail" name="rolenamedetail" readonly="true" type="text" class="form-control" >
                                                            </div>
                                                        </div>
                                                    </div><!-- /# column -->
                                                    <div class="col-md-10">
                                                        <div class="form-group">
                                                            <label class="col-sm-4 control-label">角色名称</label>
                                                            <div class="col-sm-7">
                                                                <input id="rolenamedetail" name="rolenamedetail" readonly="true" type="text" class="form-control" >
                                                            </div>
                                                        </div>
                                                    </div><!-- /# column -->
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-10 ">
                                                        <label class="col-sm-4 control-label">角色菜单</label>
                                                        <div class="col-sm-7">
                                                            <div id="menutreedetail" class="treeview">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 ">
                                                    </div>
                                                </div>
                                                <%--  <label class="col-sm-5 control-label">角色菜单</label>--%>
                                            </form>
                                            <!--/#form-->
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                        </button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>

                    </div>
                <!-- /# row -->
                </div>
     <!-- /# main content -->
 </div>
 <!-- /# container-fluid -->
</div>
<!-- /# main -->
</div>
<!-- /# content wrap -->


<script src="../assets/js/lib/jquery.min.js"></script>
<!-- jquery vendor -->
<script src="../assets/js/lib/jquery.nanoscroller.min.js"></script>
<!-- nano scroller -->
<script src="../assets/js/lib/sidebar.js"></script>
<!-- sidebar -->
<script src="../assets/js/lib/bootstrap.min.js"></script>
<script src="../assets/bstable/bootstrap-table.js"></script>
<script src="../assets/bstable/locale/bootstrap-table-zh-CN.js"></script>
<script src="../assets/datetime/bootstrap-datetimepicker.js" ></script>
<script src="../assets/datetime/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../assets/treeview/bootstrap-treeview.min.js"></script>
<script src="../assets/jqvalidate/jquery.validate.min.js"></script>
<script src="../assets/jqvalidate/localization/messages_zh.js"></script>
<script src="../assets/js/lib/sweetalert/sweetalert.min.js"></script>
<script src="../common/common.js"></script>
<!-- bootstrap -->
<!--  Datamap -->
<%--<script src="../assets/js/lib/datamap/d3.min.js"></script>
<script src="../assets/js/lib/datamap/topojson.js"></script>
<script src="../assets/js/lib/datamap/datamaps.world.min.js"></script>

<script src="../assets/js/lib/datamap/datamap-init.js"></script>
<script src="../assets/lib/lobipanel/js/lobipanel.js"></script>--%>
<!-- // Datamap -->
<%----%>
<script src="role.js"></script>
    <!-- role.js -->
<%--<script src="../assets/js/scripts.js"></script>--%>
</body>


<%--<script>
    $(function () {
        $("#roleaddform").validate();
    });
</script>--%>

</html>