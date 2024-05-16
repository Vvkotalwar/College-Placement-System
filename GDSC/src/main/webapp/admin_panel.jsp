<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transparent Login Form Design</title>
    <link rel="stylesheet" type="text/css" href="css/rrstyle.css">
</head>
<body>
    <div class="login-box">
        <img src="images/avatar.png" class="avatar">
        <h1>Register Company</h1>
        <form action="<%= request.getContextPath() %>/RegisterCompanyServlet" method="POST">
            <p>Company Name</p>
            <input type="text" name="name" placeholder="" required>
            <p>Job Role</p>
            <input type="text" name="role" placeholder="" required>
            <p>Course</p>
            <input type="text" name="course" placeholder="" required>
            <p>Internship/Full-Time</p>
            <input type="text" name="type" placeholder="" required>
            <p>Stipend/CTC</p>
            <input type="text" name="ctc" placeholder="" required> <!-- Note: typo in placeholder attribute -->
            <p>Job Description</p>
            <input type="text" name="description" placeholder="" required>
            <input type="submit" name="submit" value="Submit">
        </form>
    </div>
</body>
</html>
