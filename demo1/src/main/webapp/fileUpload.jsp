<%--
  Created by IntelliJ IDEA.
  User: XiongfeiMo
  Date: 2019/6/25
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>fileUpload</title>
</head>
<body>
<p>
    文件上传页面
</p>
<form action="${pageContext.request.contextPath}/user/fileUpload" method="post" enctype="multipart/form-data">
    头像：<input type="file" name="headico"><br>
    <input type="submit">
</form>
</body>
</html>
