<%--
  Created by IntelliJ IDEA.
  User: dalongm
  Date: 2018/9/29
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName();
    if(request.getServerPort()==80){
        basePath += request.getContextPath();
    }else{
        basePath += ":" + request.getServerPort() + request.getContextPath();
    }
    request.setAttribute("basePath", basePath);
%>
