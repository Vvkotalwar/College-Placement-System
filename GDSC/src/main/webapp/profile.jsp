<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <title>My Profile</title>
</head>
<body>
    <h1>My Profile</h1>
    <% 
        // Fetch and display student details from the database
        String prn = (String) session.getAttribute("prn");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GDSC", "root", "Vishnu@0912");
            String query = "SELECT * FROM student_details WHERE prn = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, prn);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String studentName = rs.getString("name");
                //String studentEmail = rs.getString("email");
                String branch = rs.getString("branch");
                double tenth = rs.getDouble("tenth");
                double twelfth = rs.getDouble("twelfth");
                double cgpa1 = rs.getDouble("cgpa1");
                double cgpa2 = rs.getDouble("cgpa2");
                double cgpa3 = rs.getDouble("cgpa3");
                double cgpa4 = rs.getDouble("cgpa4");
                double cgpa5 = rs.getDouble("cgpa5");
                double cgpa6 = rs.getDouble("cgpa6");
                double cgpa7 = rs.getDouble("cgpa7");
                double cgpa8 = rs.getDouble("cgpa8");
                double aggregate = rs.getDouble("aggregate");
                String nationality = rs.getString("nationality");
                String disabled = rs.getString("disabled");
                String gender = rs.getString("gender");
                String phoneNo = rs.getString("phone_no");
                Date dob = rs.getDate("dob");
                String address = rs.getString("address");
                String skills = rs.getString("skills");
                String placed = rs.getString("placed");
    %>
    <p>Student Name: <%= studentName %></p>
    <p>Branch: <%= branch %></p>
    <p>10th Percentage: <%= tenth %></p>
    <p>12th Percentage: <%= twelfth %></p>
    <p>CGPA Semester 1: <%= cgpa1 %></p>
    <p>CGPA Semester 2: <%= cgpa2 %></p>
    <p>CGPA Semester 3: <%= cgpa3 %></p>
    <p>CGPA Semester 4: <%= cgpa4 %></p>
    <p>CGPA Semester 5: <%= cgpa5 %></p>
    <p>CGPA Semester 6: <%= cgpa6 %></p>
    <p>CGPA Semester 7: <%= cgpa7 %></p>
    <p>CGPA Semester 8: <%= cgpa8 %></p>
    <p>Aggregate: <%= aggregate %></p>
    <p>Nationality: <%= nationality %></p>
    <p>Disabled: <%= disabled %></p>
    <p>Gender: <%= gender %></p>
    <p>Phone Number: <%= phoneNo %></p>
    <p>Date of Birth: <%= dob %></p>
    <p>Address: <%= address %></p>
    <p>Skills: <%= skills %></p>
    <p>Placed: <%= placed %></p>
    <!-- Add form elements for updating student details -->
    <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
</body>
</html>
