<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <title>cms</title>
    <link href="../assets/fontAwesome/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="../assets/css/lib/themify-icons.css" rel="stylesheet">
    <link href="../assets/css/lib/mmc-chat.css" rel="stylesheet" />
    <link href="../assets/css/lib/sidebar.css" rel="stylesheet">
    <link href="../assets/css/lib/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/lib/nixon.css" rel="stylesheet">
    <link href="../assets/lib/lobipanel/css/lobipanel.min.css" rel="stylesh1eet">
    <link href="../assets/css/style.css" rel="stylesheet">
    <link href="../assets/bstable/bootstrap-table.css" rel="stylesheet"/>
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

                </ul>
            </div>
        </div>
    </div>
    <!-- /# sidebar -->
    <div class="header">
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
            <ul>
                <li class="header-icon dib">
                    <i class="ti-bell"></i>
                    <div class="note-count">2</div>
                    <div class="drop-down">
                        <div class="dropdown-content-heading">
                            <span class="text-left">Recent Notifications</span>
                        </div>
                        <div class="dropdown-content-body">
                            <ul>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/3.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Mr. Jane</div>
                                            <div class="notification-text">5 members joined today </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/3.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Mariam</div>
                                            <div class="notification-text">likes a photo of you</div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/3.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Tasnim</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/3.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Jane Roe</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="text-center">
                                    <a href="#" class="more-link">See All</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="header-icon dib">
                    <i class="ti-email"></i>
                    <div class="note-count">21</div>
                    <div class="drop-down">
                        <div class="dropdown-content-heading">
                            <span class="text-left">2 New Messages</span>
                            <a href="app-email.html"><i class="ti-pencil-alt pull-right"></i></a>
                        </div>
                        <div class="dropdown-content-body">
                            <ul>
                                <li class="notification-unread">
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/1.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Jane Doe</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="notification-unread">
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/2.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Jane Roe</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/3.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Jane Doe</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <img class="pull-left m-r-10 avatar-img" src="assets/images/avatar/2.jpg" alt="" />
                                        <div class="notification-content">
                                            <small class="notification-timestamp pull-right">02:34 PM</small>
                                            <div class="notification-heading">Jane Roe</div>
                                            <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                        </div>
                                    </a>
                                </li>
                                <li class="text-center">
                                    <a href="#" class="more-link">See All</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>
                <li class="header-icon dib chat-sidebar-icon"><i class="ti-comments"></i></li>
                <li class="header-icon dib">
                    <img class="avatar-img" src="assets/images/avatar/1.jpg" alt="" /> <span class="user-avatar">Jane <i class="ti-angle-down f-s-10"></i></span>
                    <div class="drop-down dropdown-profile">
                        <div class="dropdown-content-heading">
                            <span class="text-left">Upgrade Now</span>
                            <p class="trial-day">30 Days Trail</p>
                        </div>
                        <div class="dropdown-content-body">
                            <ul>
                                <li><a href="#"><i class="ti-user"></i> <span>Profile</span></a></li>
                                <li><a href="#"><i class="ti-wallet"></i> <span>My Balance</span></a></li>
                                <li><a href="#"><i class="ti-write"></i> <span>My Task</span></a></li>
                                <li><a href="#"><i class="ti-calendar"></i> <span>My Calender</span></a></li>
                                <li><a href="#"><i class="ti-email"></i> <span>Inbox</span></a></li>
                                <li><a href="#"><i class="ti-settings"></i> <span>Setting</span></a></li>
                                <li><a href="#"><i class="ti-help-alt"></i> <span>Help</span></a></li>
                                <li><a href="#"><i class="ti-lock"></i> <span>Lock Screen</span></a></li>
                                <li><a href="#"><i class="ti-power-off"></i> <span>Logout</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
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
                                    <li><a href="#">角色管理</a></li>
                                    <li class="active">系统管理</li>
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
                                                        <label class="col-sm-5 control-label">Text Input</label>
                                                        <div class="col-sm-7">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label class="col-sm-5 control-label">Text Input</label>
                                                        <div class="col-sm-7">
                                                            <input type="text" class="form-control"
                                                                   value="Some text value...">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label class="col-sm-5 control-label">Text Input</label>
                                                        <div class="col-sm-7">
                                                            <input type="text" class="form-control"
                                                                   value="Some text value...">
                                                        </div>
                                                    </div>
                                                </div><!-- /# column -->
                                            </div>
                                            <div class="row">
                                                <input id="queryrole" type="button" value="查 询" class="btn btn-primary m-b-10 m-l-5"></input>
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
                                                <table  id="rolelist" class="table  table-bordered " ></table>
                                            </div>

                                    </div>
                                </div>
                            </div><!-- /# card -->

                    </div>
                    <!-- /# row -->
                    <div class="row">

                    </div>
                    <!-- /# row -->
                    <div class="row">

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
<!-- bootstrap -->
<!--  Datamap -->
<script src="../assets/js/lib/datamap/d3.min.js"></script>
<script src="../assets/js/lib/datamap/topojson.js"></script>
<script src="../assets/js/lib/datamap/datamaps.world.min.js"></script>

<script src="../assets/js/lib/datamap/datamap-init.js"></script>
<script src="../assets/lib/lobipanel/js/lobipanel.js"></script>
<!-- // Datamap -->
<script src="../assets/js/scripts.js"></script>
<script src="./role.js" type="text/javascript"></script>
    <!-- role.js -->
</body>

</html>