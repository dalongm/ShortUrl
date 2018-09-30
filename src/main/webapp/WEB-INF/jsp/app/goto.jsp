<%@ page import="top.dalongm.dto.URLDto" %><%--
  Created by IntelliJ IDEA.
  User: dalongm
  Date: 2018/9/28
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<%
    URLDto urlDto = (URLDto)request.getAttribute("url");
    double dday = urlDto.getValidTime();
    int day = (int)Math.floor(dday);
    double dhour = (dday-day)*24.0;
    int hour = (int)Math.floor(dhour);
    double dmin = (dhour-hour)*60.0;
    int min = (int)Math.floor(dmin);
    int sec = (int)Math.floor((dmin-min)*60.0);
    request.setAttribute("day", day);
    request.setAttribute("hour", hour);
    request.setAttribute("min", min);
    request.setAttribute("sec", sec);
%>
<body>
<form class="uk-margin" id="urlForm" action="${basePath}/" method="get">
    <fieldset class="uk-fieldset">
        <legend class="uk-legend">获取原始链接成功</legend>
        <div class="uk-margin">
            <label class="uk-form-label" for="url">原始链接:</label>
            <div class="uk-form-controls">
                <input id="url" name="url" class="uk-input uk-form-width-large" readonly="readonly"
                       type="text" placeholder="原始链接" value="${url.url}">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="sUrl">短链接:</label>
            <div class="uk-form-controls">
                <input id="sUrl" name="sUrl" class="uk-input uk-form-width-large" type="text" value="${basePath}/${url.sUrl}"
                       placeholder="后缀(可选)" readonly="readonly">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="validTime">有效时长:</label>
            <div class="uk-form-controls">
                <input id="validTime" name="validTime" class="uk-input uk-form-width-medium" type="text" readonly="readonly"
                       placeholder="可选，默认1年" value="${day}天${hour}时${min}分${sec}秒">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="validTimes">有效次数:</label>
            <div class="uk-form-controls">
                <input id="validTimes" name="validTimes" class="uk-input uk-form-width-medium" type="number" min="0" readonly="readonly"
                       placeholder="可选，默认10w次" uk-tooltip="title: 默认10w次; pos: top" value="${url.validTimes}">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="visitPass">访问密码:</label>
            <div class="uk-form-controls">
                <input id="visitPass" name="visitPass" class="uk-input uk-form-width-medium" type="text" readonly="readonly"
                       placeholder="可选，默认无"
                <c:if test="${url.visitPass==null||empty url.visitPass}">
                        value="无"
                </c:if>
                <c:if test="${url.visitPass!=null}">
                       value="${url.visitPass}"
                </c:if>
                >
            </div>
        </div>

        <div class="uk-margin">
            <a class="uk-button uk-button-default" onclick="copysUrl()">复制长链接</a>
            <a class="uk-button uk-button-primary" onclick="goUrl()">直接访问</a>
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
        var Url = document.getElementById("url");
        Url.select(); // 选择对象
        document.execCommand("Copy");
        alert("原始链接复制成功！");
    }
    
    function goUrl() {
        var url = "${url.url}";
        if(url.toLowerCase().startsWith("http://")||url.toLowerCase().startsWith("https://")){
            window.location.href = url;
        }else{
            window.location.href = "http://"+url;
        }
    }
</script>
</html>
