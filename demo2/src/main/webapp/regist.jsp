<%--
  Created by IntelliJ IDEA.
  User: XiongfeiMo
  Date: 2019/6/24
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>regist</title>
</head>
<body>
<p>
    注册页面
</p>
<form action="${pageContext.request.contextPath}/user/regist" method="post">
    用户名：<input type="text" name="username" required="required"><br>
    密码：<input type="password" name="password" required="required"><br>
    <input type="submit">
</form>
</body>
</html>
