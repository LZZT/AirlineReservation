
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>AirLine Reservation System</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link href="tooplate_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<br><br>

<div id="tooplate_wrapper">

    <div id="tooplate_menu">
        <ul>
            <li><a href="index.jsp" class="current" style="font-size: large">Home</a></li>
            <li><a href="reloadDB.action" style="font-size: large">Manager</a></li>
            <li><a href="about.html" style="font-size: large">About Us</a></li>
            <li><a href="contact.html" style="font-size: large">Contact</a></li>
        </ul>
    </div> <!-- end of tooplate_menu -->

    <div id="tooplate_header">

        <div><h1>Reset Password</h1></div>

    </div> <!-- end of header -->

    <div id="tooplate_middle">

        <s:actionerror cssStyle="color:red"/>

        <form action="ResetPassword.action" method="post">

            username: <input type="text" name="username"/><br>

            email: <input type="text" name="email"/><br>

            re-enter email: <input type="text" name="reemail"/><br>

            <input type="submit" value="Re-send password"/>

            <%--onclick="location.href='ResetPasswordConfirmation.jsp';"--%>

            <input type="reset" value="reset">

        </form>


    </div>


</div>

<div id="tooplate_footer_wrapper">
    <div id="tooplate_footer">
        Copyright © 2017 <a href="#">CS 542 Team 2</a>
    </div>
</div>

</body>
</html>



