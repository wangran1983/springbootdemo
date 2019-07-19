<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <title>cms</title>
    <link href="../assets/css/lib/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/lib/nixon.css" rel="stylesheet">
    <link href="../assets/lib/lobipanel/css/lobipanel.min.css" rel="stylesh1eet">
    <link href="../assets/css/lib/sidebar.css" rel="stylesheet">
    <link href="../assets/css/style.css" rel="stylesheet">
    <link href="../assets/bstable/bootstrap-table.css" rel="stylesheet"/>
    <link href="../assets/datetime/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="../assets/treeview/bootstrap-treeview.min.css" rel="stylesheet">
    <link href="../assets/css/lib/sweetalert/sweetalert.css" rel="stylesheet"/>
    <link href="../assets/bsselect/css/bootstrap-select.min.css" rel="stylesheet"/>
    <link href="../assets/css/style.css" rel="stylesheet">
</head>

<body class="bg-primary">

	<div class="Nixon-login">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="login-content">
						<div class="login-form">
                            <h4>CMS 系统登录</h4>
                            <form id="loginform" class="form-horizontal" >
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">用 户 名</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" value = "001" placeholder="用户名" id="usercode" name="usercode">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">密  码</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" value="123456" placeholder="密码" id="password" name="password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <input id="loginbtn" type="button" value="登 录"  class="btn btn-default"/>
                                    </div>
                                </div>
                            </form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

    <script src="../assets/js/lib/jquery.min.js"></script>
    <script src="../assets/js/lib/bootstrap.min.js"></script>
    <script src="../assets/jqvalidate/jquery.validate.min.js"></script>
    <script src="../assets/jqvalidate/localization/messages_zh.js"></script>
    <script src="../assets/js/lib/sweetalert/sweetalert.min.js"></script>
    <script src="../common/common.js"></script>
    <script src="../system/userlogin.js"></script>
    <!-- bootstrap -->
<%--    <script src="userlogin.js"></script>--%>
    <script>
/*
        $(function () {
            alert(1);
        })*/

    </script>
</body>
</html>