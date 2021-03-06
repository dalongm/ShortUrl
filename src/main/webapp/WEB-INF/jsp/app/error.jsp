<%--
  Created by IntelliJ IDEA.
  User: dalon
  Date: 2018/9/29
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>短链接服务</title>
    <link type="favicon" rel="shortcut icon" href="${basePath}/images/favicon.ico"/>
    <link href="${basePath}/css/uikit.css" rel="stylesheet" type="text/css"/>
    <script src="${basePath}/js/common/jquery-3.3.1.js"
            type="text/javascript"></script>
    <script src="${basePath}/js/common/uikit.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/uikit-icons.js"
            type="text/javascript"></script>
    <style type="text/css">
        #urlForm {
            margin: 0 auto;
            width: 600px;
            min-height: 900px;
        }
    </style>
</head>
<body>
<form class="uk-form uk-margin" id="urlForm" action="${basePath}/" method="post">
    <fieldset class="uk-fieldset">
        <legend class="uk-legend">出现错误请重试</legend>
        <p>${error.message}</p>
        <div class="uk-margin">
            <input class="uk-button uk-button-primary" type="button" value="返回" onclick="goBack()"/>
            <input class="uk-button uk-button-default" type="button" value="关闭" onclick="closeCurrentPage()"/>
        </div>
    </fieldset>
</form>
</body>
<script type="text/javascript">
    function goBack() {
        window.history.back(-1);
    }

    function closeCurrentPage() {
        var userAgent = navigator.userAgent;
        if (userAgent.indexOf("Firefox") !== -1 || userAgent.indexOf("Chrome") !== -1) {
            window.location.href = "about:blank";
            window.close();
        } else {
            window.opener = null;
            window.open("", "_self");
            window.close();
        }
    }
</script>
</html>
