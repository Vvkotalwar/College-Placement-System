<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transparent Login Form Design</title>
    <link rel="stylesheet" type="text/css" href="css/rrstyle.css">
</head>
<body>
    <div class="login-box">
        <img src="img/avatar.png" >
        <h1>Register Here</h1>
        <form action="/GDSC/AdminRegistration" method="POST">
            <p>Full Name</p>
            <input type="text" name="name" placeholder="Enter your Full Name" required>
            <p>Email</p>
            <input type="text" name="email" placeholder="Enter your email address" required>
            <p>Mobile Number</p>
            <input type="text" name="mobile_number" placeholder="Enter your Mobile Number" required>
            <p>Faculty</p>
            <input type="text" name="faculty" placeholder="Enter course" required>
            <p>PRN</p>
            <input type="text" name="prn" placeholder="Enter PRN" required>
            <p>Password</p>
            <input type="password" name="password" placeholder="Enter Password" required>
            <p>Renter Password</p>
            <input type="password" name="rpassword" placeholder="Renter Password" required>
            <input type="hidden" name="action" value="signup">
            <input type="submit" name="submit" value="Create Account">
            Already have an account?<a href="login.jsp"> Log In</a>
        </form>
    </div>
</body>
</html>
