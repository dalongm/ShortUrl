<%--
  Created by IntelliJ IDEA.
  User: dalongm
  Date: 2018/9/28
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    request.setAttribute("basePath", basePath);
%>
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
<form class="uk-margin" id="urlForm" action="${basePath}/" method="get">
    <fieldset class="uk-fieldset">
        <legend class="uk-legend">短链接生成成功</legend>
        <div class="uk-margin">
            <input id="url" readonly="readonly" name="url" class="uk-input uk-form-width-large" type="text"
                   placeholder="原始链接" value="${url.url}">
        </div>
        <div class="uk-margin">
            <input id="sUrl" readonly="readonly" name="sUrl" class="uk-input uk-form-width-large" type="text"
                   placeholder="短链接" value="${basePath}/${url.sUrl}">
        </div>
        <div class="uk-margin">
            <a class="uk-button uk-button-default" href="${basePath}/">返回</a>
            <a class="uk-button uk-button-primary" onclick="copysUrl()">复制短链接</a>
        </div>
    </fieldset>
</form>
</body>
<script type="text/javascript">
    function checkInputEmpty(obj) {
        if ($(obj).val().trim() === "") {
            $(obj).removeClass("uk-form-success");
            $(obj).addClass("uk-form-danger");
            return false;
        } else {
            $(obj).removeClass("uk-form-danger");
            $(obj).addClass("uk-form-success");
            return true;
        }
    }

    function copysUrl() {
        var Url = document.getElementById("sUrl");
        Url.select(); // 选择对象
        document.execCommand("Copy");
        alert("复制成功！");
    }

</script>
</html>
