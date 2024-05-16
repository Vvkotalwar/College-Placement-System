<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Transparent Login Form Design</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h1>Student Login</h1>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                <p>PRN ID</p>
                <input type="text" name="prn" placeholder="Enter your PRN ID" required>
                <p>Password</p>
                <input type="password" name="password" placeholder="Enter Password" required>
                <input type="hidden" name="action" value="login">
                <input type="submit" name="submit" value="Login as Student">
                <a href="#">Forget Password?</a><br><br>
                <a href="registration.jsp">Don't have a student account? Sign Up Here</a>
            </form>
        </div>
        
        <div class="login-box">
            <!-- Separate form for admin login -->
            <form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
                <h1>Admin Login</h1>
                <p>Email ID</p>
                <input type="text" name="email" placeholder="Enter Admin Email ID" required>
                <p>Password</p>
                <input type="password" name="password" placeholder="Enter Admin Password" required>
                <input type="hidden" name="action" value="admin_login">
                <input type="submit" name="submit" value="Login as Admin">
                <a href="#">Forget Password?</a><br><br>
                <a href="Admin_Regi.jsp">Don't have an account? - Sign Up Here</a>
            </form>
        </div>
    </div>
</body>
</html>
