<%--
  Created by IntelliJ IDEA.
  User: dalongm
  Date: 2018/9/28
  Time: 18:53
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
        <legend class="uk-legend">欢迎使用短链接服务</legend>
        <div class="uk-margin">
            <label class="uk-form-label" for="url">原始链接:</label>
            <div class="uk-form-controls">
                <input id="url" name="url" class="uk-input uk-form-width-large"
                       uk-tooltip="title: 长度不大于255个字符; pos: top"
                       type="text" placeholder="原始链接" onchange="checkInputEmpty(this)">
            </div>
        </div>
        <div class="uk-margin">
            <label class="uk-form-label" for="sUrl">短链接:</label>
            <div class="uk-form-controls">
                <span class="uk-input uk-form-width-medium">${basePath}/</span>
                <input id="sUrl" name="sUrl" class="uk-input uk-form-width-medium" type="text"
                       placeholder="后缀(可选)" uk-tooltip="title: 不少于5个且不多于10个字母与数字的组合; pos: top"
                       onchange="checkShort(this,5)">
            </div>
        </div>
        <div class="uk-margin uk-grid-large uk-child-width-auto uk-grid">
            <div>
                <label class="uk-form-label" for="charCheck">后缀字符集:</label>
                <div id="charCheck" class="uk-grid-small uk-child-width-auto uk-grid">
                    <label><input class="uk-checkbox" id="number" name="number" type="checkbox" checked> 0 - 9 </label>
                    <label><input class="uk-checkbox" id="lowAlphabet" name="lowAlphabet" type="checkbox" checked> a - z </label>
                    <label><input class="uk-checkbox" id="upAlphabet" name="upAlphabet" type="checkbox" checked> A - Z </label>
                </div>
            </div>
            &nbsp;&nbsp;&nbsp;
            <div>
                <label class="uk-form-label" for="sUrlLen">后缀长度:</label>
                <div class="uk-form-controls">
                    <input id="sUrlLen" name="sUrlLen" class="uk-input uk-form-width-medium" type="number"
                           onchange="change2green(this)"
                           placeholder="长度" uk-tooltip="title: 后缀长度，5-10; pos: top" min="5" value="5" max="10">
                </div>
            </div>

        </div>

        <div class="uk-margin uk-grid-large uk-child-width-auto uk-grid">
            <div>
                <label class="uk-form-label" for="visitPass">访问密码:</label>
                <div class="uk-form-controls">
                    <input id="visitPass" name="visitPass" class="uk-input uk-form-width-medium" type="text"
                           placeholder="可选，默认无" uk-tooltip="title: 默认无，不少于4位的数字与字母组合; pos: top"
                           onchange="checkShort(this,4)">
                </div>
            </div>
            <div>
                <label class="uk-form-label" for="validTimes">有效次数:</label>
                <div class="uk-form-controls">
                    <input id="validTimes" name="validTimes" class="uk-input uk-form-width-medium" type="number" min="0"
                           onchange="change2green(this)"
                           placeholder="可选，默认10w次" uk-tooltip="title: 默认10w次; pos: top">
                </div>
            </div>
        </div>

        <div class="uk-margin">
            <label class="uk-form-label" for="validTime">有效时长:</label>
            <div class="uk-form-controls">
                <input id="validTime" name="validTime" hidden>
                <input id="validDay" name="validDay" class="uk-input uk-form-width-small" type="number"
                       onchange="change2green(this)"
                       placeholder="365" uk-tooltip="title: 天，可选，默认1年; pos: top" min="0" value="365">
                <span>天</span>
                <input id="validHour" name="validHour" class="uk-input uk-form-width-small" type="number"
                       onchange="change2green(this)"
                       placeholder="0" uk-tooltip="title: 分，可选，默认0; pos: top" min="0" value="0" max="59">
                <span>时</span>
                <input id="validMin" name="validMin" class="uk-input uk-form-width-small" type="number"
                       onchange="change2green(this)"
                       placeholder="0" uk-tooltip="title: 分，可选，默认0; pos: top" min="0" value="0" max="59">
                <span>分</span>
            </div>
        </div>

        <div class="uk-margin">
            <input class="uk-button uk-button-primary" type="button" value="创建" onclick="postUrl()"/>
            <input class="uk-button uk-button-default" type="reset" value="重置" onclick="reset2default()"/>
        </div>
    </fieldset>
</form>
</body>
<script type="text/javascript">
    function change2green(obj) {
        $(obj).removeClass("uk-form-danger");
        $(obj).addClass("uk-form-success");
    }

    function change2default(obj) {
        $(obj).removeClass("uk-form-danger");
        $(obj).removeClass("uk-form-success");
    }

    function reset2default() {
        change2default("#url");
        change2default("#sUrl");
        change2default("#visitPass");
        change2default("#validTimes");
        change2default("#validTime");
        change2default("#sUrlLen");
    }

    function checkInputEmpty(obj) {
        if ($(obj).val().trim() === "" || $(obj).val().trim().length > 255 || !isURL($(obj).val())) {
            $(obj).removeClass("uk-form-success");
            $(obj).addClass("uk-form-danger");
            return false;
        } else {
            $(obj).removeClass("uk-form-danger");
            $(obj).addClass("uk-form-success");
            return true;
        }
    }

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

    function postUrl() {
        if (checkInputEmpty("#url") && checkShort("#sUrl", 5)) {
            var day = Number($("#validDay").val()) + Number($("#validHour").val()) / 24 + Number($("#validMin").val()) / 24 / 60;
            $("#validTime").attr("value", day);
            $("#urlForm").submit();
            return;
        }

        if (!checkInputEmpty("#url")) {
            alert("请输入正确的网址！");
        }
        if (!checkShort("#sUrl")) {
            alert("请输入正确的短地址后缀！");
        }


    }

    function isURL(str_url) {
        var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
            + "?(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?" //ftp的user@
            + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
            + "|" // 允许IP和DOMAIN（域名）
            + "([0-9a-zA-Z_!~*'()-]+\.)*" // 域名- www.
            + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\." // 二级域名
            + "[a-zA-Z]{2,6})" // first level domain- .com or .museum
            + "(:[0-9]{1,4})?" // 端口- :80
            + "((/?)|" // a slash isn't required if there is no file name
            + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
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
