<%@ page import="java.io.*,java.sql.*,java.util.*" %>
<%@ page import="javax.servlet.*,javax.servlet.http.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apply Servlet</title>
</head>
<body>
    <%-- Retrieve parameters from the request --%>
    <% String companyId = request.getParameter("companyId"); %>
    <% String prn = (String) request.getSession().getAttribute("prn"); %>
    
    <%-- Define SQL queries --%>
    <% String companyQuery = "SELECT company_name, cutoff, branch_type FROM company_details WHERE company_id = ?"; %>
    <% String studentQuery = "SELECT name, branch FROM student_details WHERE prn = ?"; %>
    
    <%-- Database connection parameters --%>
    <% String url = "jdbc:mysql://localhost:3306/GDSC"; %>
    <% String user = "root"; %>
    <% String pass = "Vishnu@0912"; %>
    
    <%-- Establish database connection --%>
    <% Connection conn = null; %>
    <% PreparedStatement companyStmt = null; %>
    <% PreparedStatement studentStmt = null; %>
    <% PreparedStatement applyStmt = null; %>
    <% ResultSet companyResult = null; %>
    <% ResultSet studentResult = null; %>
    
    <% try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, pass);
        
        // Prepare statements
        companyStmt = conn.prepareStatement(companyQuery);
        studentStmt = conn.prepareStatement(studentQuery);
        applyStmt = conn.prepareStatement("INSERT INTO applications (student_prn, company_id, company_name, cutoff, branch_type, student_name, student_branch) VALUES (?, ?, ?, ?, ?, ?, ?)");
        
        // Set parameters and execute queries
        companyStmt.setString(1, companyId);
        studentStmt.setString(1, prn);
        companyResult = companyStmt.executeQuery();
        studentResult = studentStmt.executeQuery();
        
        if (companyResult.next() && studentResult.next()) {
            String companyName = companyResult.getString("company_name");
            double companyCutoff = companyResult.getDouble("cutoff");
            String companyBranch = companyResult.getString("branch_type");
            
            String studentName = studentResult.getString("name");
            String studentBranch = studentResult.getString("branch");
            
            // Insert data into the applications table
            applyStmt.setString(1, prn);
            applyStmt.setString(2, companyId);
            applyStmt.setString(3, companyName);
            applyStmt.setDouble(4, companyCutoff);
            applyStmt.setString(5, companyBranch);
            applyStmt.setString(6, studentName);
            applyStmt.setString(7, studentBranch);
            applyStmt.executeUpdate();
            
            // Redirect to a success page
            response.sendRedirect("apply_su.jsp");
        } else {
            // Handle no results found
            response.sendRedirect("apply.jsp");
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        // Redirect to an error page
        response.sendRedirect("apply_error.jsp");
    } finally {
        // Close resources
        try { if (companyResult != null) companyResult.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (studentResult != null) studentResult.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (companyStmt != null) companyStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (studentStmt != null) studentStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (applyStmt != null) applyStmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    } %>
</body>
</html>
