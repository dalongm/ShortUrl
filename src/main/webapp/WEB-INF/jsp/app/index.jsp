<%--
  Created by IntelliJ IDEA.
  User: dalongm
  Date: 2018/9/28
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<form class="uk-form uk-margin" id="urlForm" action="${basePath}/" method="post">
    <fieldset class="uk-fieldset">
        <legend class="uk-legend">欢迎使用短链接服务</legend>

        <div class="uk-margin">
            <label class="uk-form-label" for="url">原始链接:</label>
            <div class="uk-form-controls">
                <input id="url" name="url" class="uk-input uk-form-width-large" type="text" placeholder="原始链接"
                       onchange="checkInputEmpty(this)">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="sUrl">短链接:</label>
            <div class="uk-form-controls">
                <span class="uk-input uk-form-width-medium">${basePath}/</span>
                <input id="sUrl" name="sUrl" class="uk-input uk-form-width-medium" type="text"
                       placeholder="短链接(可选)" title="不少于5个且不多于10个字母与数字的组合">
            </div>
        </div>
        <div class="uk-margin">
            <input class="uk-button uk-button-primary" type="button" value="创建" onclick="postUrl()"/>
        </div>
    </fieldset>
</form>
</body>
<script type="text/javascript">
    function checkInputEmpty(obj) {
        if ($(obj).val().trim() === "" || !isURL($(obj).val())) {
            $(obj).removeClass("uk-form-success");
            $(obj).addClass("uk-form-danger");
            return false;
        } else {
            $(obj).removeClass("uk-form-danger");
            $(obj).addClass("uk-form-success");
            return true;
        }
    }

    function checkShort(obj) {
        var strRegex = "^[a-zA-Z0-9]{5,10}$"
    }

    function postUrl() {
        if (!checkInputEmpty("#url")) {
            alert("请输入正确的网址！");
            return;
        }
        $("#urlForm").submit();
    }

    function isURL(str_url) {
        var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
            + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
            + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
            + "|" // 允许IP和DOMAIN（域名）
            + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
            + "[a-z]{2,6})" // first level domain- .com or .museum
            + "(:[0-9]{1,4})?" // 端口- :80
            + "((/?)|" // a slash isn't required if there is no file name
            + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        var re = new RegExp(strRegex);
        //re.test()
        if (re.test(str_url)) {
            return (true);
        } else {
            return (false);
        }
    }
</script>
</html>
