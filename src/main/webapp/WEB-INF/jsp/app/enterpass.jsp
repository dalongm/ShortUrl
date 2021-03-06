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
<form class="uk-form uk-margin" id="urlForm" action="${basePath}/veri" method="post">
    <fieldset class="uk-fieldset">
        <legend class="uk-legend">请输入短链接密码</legend>
        <div class="uk-margin">
            <label class="uk-form-label" for="sUrl">短链接:</label>
            <div class="uk-form-controls">
                <span class="uk-input uk-form-width-medium">${basePath}/</span>
                <input id="sUrl" name="sUrl" class="uk-input uk-form-width-medium" type="text"
                       placeholder="后缀(可选)" readonly="readonly" value="${sUrl}">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="visitPass">访问密码:</label>
            <div class="uk-form-controls">
                <input id="visitPass" name="visitPass" class="uk-input uk-form-width-medium" type="text"
                       uk-tooltip="title: 不少于4位的数字与字母组合; pos: top"
                       onchange="checkShort(this,4)">
            </div>
        </div>
        <div class="uk-margin">
            <input class="uk-button uk-button-primary" type="button" value="访问" onclick="goto()"/>
            <input class="uk-button uk-button-default" type="button" value="关闭" onclick="closeCurrentPage()"/>
        </div>
    </fieldset>
</form>
</body>
<script type="text/javascript">
    function checkShort(obj, n) {
        var strRegex = "^[a-zA-Z0-9]{" + n + ",10}$";
        var re = new RegExp(strRegex);
        if ($(obj).val().trim() !== "" && !re.test($(obj).val().trim())) {
            $(obj).removeClass("uk-form-success");
            $(obj).addClass("uk-form-danger");
            return (false);
        } else {
            $(obj).removeClass("uk-form-danger");
            $(obj).addClass("uk-form-success");
            return (true);
        }
    }

    function goto(){
        if(checkShort("#visitPass",4)){
            $("#urlForm").submit();
        }
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
