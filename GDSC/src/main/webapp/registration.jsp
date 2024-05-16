<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  <!DOCTYPE html>
<html>
<head>
    <title>Transparent Login Form Design</title>
    <link rel="stylesheet" type="text/css" href="css/rrstyle.css">
</head>
<body>
    <div class="login-box">
        <img src="img/avatar.png" class="avatar">
        <h1>Register Here</h1>
        <form action="/GDSC/RegistrationServlet" method="POST">
            <p>Full Name</p>
            <input type="text" name="name" placeholder="Enter your Full Name" required>
            <p>Email</p>
            <input type="text" name="email" placeholder="Enter your email address" required>
            <p>Mobile Number</p>
            <input type="text" name="mobile_number" placeholder="Enter your Mobile Number" required>
            <p>course</p>
            <input type="text" name="course" placeholder="Enter course" required>
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
-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Details Form</title>
    <link rel="stylesheet" type="text/css" href="css/registrationcss.css">
</head>
<body>
    <h1>Student Details Form</h1>
    <form method="post" action="/GDSC/RegistrationServlet">
        <label for="prn">PRN:</label>
        <input type="text" id="prn" name="prn" value="<%= request.getParameter("prn") %>"><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="<%= request.getParameter("name") %>"><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= request.getParameter("email") %>"><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword"><br>

        <label for="branch">Branch:</label>
        <select id="branch" name="branch">
            <option value="Btech.CSE" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.CSE") ? "selected" : "" %>>Btech.CSE</option>
            <option value="Btech.Electrical" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.Electrical") ? "selected" : "" %>>Btech.Electrical</option>
            <option value="Btech.Mechanical" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.Mechanical") ? "selected" : "" %>>Btech.Mechanical</option>
            <option value="Btech.ENTC" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.ENTC") ? "selected" : "" %>>Btech.ENTC</option>
            <option value="MCA" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MCA") ? "selected" : "" %>>MCA</option>
            <option value="MSc" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MSc") ? "selected" : "" %>>MSc</option>
            <option value="BSc" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("BSc") ? "selected" : "" %>>BSc</option>
            <option value="MBA" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MBA") ? "selected" : "" %>>MBA</option>
        </select><br>

        <label for="10th">10th:</label>
        <input type="text" id="10th" name="10th" value="<%= request.getParameter("10th") %>"><br>

        <label for="12th">12th:</label>
        <input type="text" id="12th" name="12th" value="<%= request.getParameter("12th") %>"><br>

        <label for="cgpa1">CGPA1:</label>
        <input type="text" id="cgpa1" name="cgpa1" value="<%= request.getParameter("cgpa1") %>"><br>

        <label for="cgpa2">CGPA2:</label>
        <input type="text" id="cgpa2" name="cgpa2" value="<%= request.getParameter("cgpa2") %>"><br>

        <label for="cgpa3">CGPA3:</label>
        <input type="text" id="cgpa3" name="cgpa3" value="<%= request.getParameter("cgpa3") %>"><br>

        <label for="cgpa4">CGPA4:</label>
        <input type="text" id="cgpa4" name="cgpa4" value="<%= request.getParameter("cgpa4") %>"><br>

        <label for="cgpa5">CGPA5:</label>
        <input type="text" id="cgpa5" name="cgpa5" value="<%= request.getParameter("cgpa5") %>"><br>

        <label for="cgpa6">CGPA6:</label>
        <input type="text" id="cgpa6" name="cgpa6" value="<%= request.getParameter("cgpa6") %>"><br>

        <label for="cgpa7">CGPA7:</label>
        <input type="text" id="cgpa7" name="cgpa7" value="<%= request.getParameter("cgpa7") %>"><br>

        <label for="cgpa8">CGPA8:</label>
        <input type="text" id="cgpa8" name="cgpa8" value="<%= request.getParameter("cgpa8") %>"><br>

        <label for="cgpa9">Aggregrate:</label>
        <input type="text" id="cgpa9" name="cgpa9" value="<%= request.getParameter("cgpa9") %>"><br>

        <label for="nationality">Nationality:</label>
        <input type="text" id="nationality" name="nationality" value="<%= request.getParameter("nationality") %>"><br>

        <label for="disabled">Disabled:</label>
        <select id="disabled" name="disabled">
            <option value="Yes" <%= request.getParameter("disabled") != null && request.getParameter("disabled").equals("Yes") ? "selected" : "" %>>Yes</option>
            <option value="No" <%= request.getParameter("disabled") != null && request.getParameter("disabled").equals("No") ? "selected" : "" %>>No</option>
        </select><br>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender">
            <option value="Male" <%= request.getParameter("gender") != null && request.getParameter("gender").equals("Male") ? "selected" : "" %>>Male</option>
            <option value="Female" <%= request.getParameter("gender") != null && request.getParameter("gender").equals("Female") ? "selected" : "" %>>Female</option>
        </select><br>

        <label for="phoneNo">Phone No:</label>
        <input type="text" id="phoneNo" name="phoneNo" value="<%= request.getParameter("phoneNo") %>"><br>

        <label for="dob">DOB:</label>
        <input type="text" id="dob" name="dob" value="<%= request.getParameter("dob") %>"><br>

        <label for="address">Address:</label>
        <textarea id="address" name="address"><%= request.getParameter("address") %></textarea><br>

        <label for="skills">Skills:</label>
        <textarea id="skills" name="skills"><%= request.getParameter("skills") %></textarea><br>

        <label for="placed">Placed:</label>
        <select id="placed" name="placed">
            <option value="Yes" <%= request.getParameter("placed") != null && request.getParameter("placed").equals("Yes") ? "selected" : "" %>>Yes</option>
            <option value="No" <%= request.getParameter("placed") != null && request.getParameter("placed").equals("No") ? "selected" : "" %>>No</option>
        </select><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
