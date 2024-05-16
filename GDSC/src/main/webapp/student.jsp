<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company Details</title>
    <script>
        function apply(companyName, companyId, studentId) {
            document.getElementById("companyId").value = companyId;
            document.getElementById("studentId").value = studentId;
            document.getElementById("applyForm").submit();
            alert("You have successfully applied for " + companyName);
        }
    </script>
    <link rel="stylesheet" type="text/css" href="css/student.css">
</head>
<body>
    <h1>Campus Placement System</h1>
    <form id="applyForm" action="Applyservlet" method="post">
        <input type="hidden" id="companyId" name="companyId" value="">
        <input type="hidden" id="studentId" name="studentId" value="">
        <table border="1">
            <!-- Table headers -->
            <tr>
                <th>Company Name</th>
                <th>Address</th>
                <th>Cutoff</th>
                <th>Branch Type</th>
                <th>Required Skills</th>
                <th>Action</th>
            </tr>
            <!-- Loop through company details -->
            <% 
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                PreparedStatement eligibilityStmt = null;
                ResultSet eligibilityResultSet = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GDSC", "root", "Vishnu@0912");
                    stmt = conn.createStatement();
                    String sql = "SELECT company_name, address, cutoff, branch_type, required_skills, company_id FROM company_details";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        String companyName = rs.getString("company_name");
                        String companyBranch = rs.getString("branch_type");
                        double companyCutoff = rs.getDouble("cutoff");
                        int companyId = rs.getInt("company_id");

                        String eligibilityQuery = "SELECT * FROM applications WHERE student_prn = ? AND company_id = ?";
                        eligibilityStmt = conn.prepareStatement(eligibilityQuery);
                        eligibilityStmt.setString(1, (String) request.getSession().getAttribute("prn"));
                        eligibilityStmt.setInt(2, companyId);
                        eligibilityResultSet = eligibilityStmt.executeQuery();
                        boolean isApplied = eligibilityResultSet.next();

                        boolean isEligible = false;
                        if (!isApplied) {
                            // Check eligibility
                            String countQuery = "SELECT COUNT(*) AS count FROM student_details WHERE branch = ? AND aggregate >= ?";
                            PreparedStatement countStmt = conn.prepareStatement(countQuery);
                            countStmt.setString(1, companyBranch);
                            countStmt.setDouble(2, companyCutoff);
                            ResultSet countResultSet = countStmt.executeQuery();
                            if (countResultSet.next() && countResultSet.getInt("count") > 0) {
                                isEligible = true;
                            }
                            countResultSet.close();
                            countStmt.close();
                        }
            %>
            <tr>
                <!-- Display company details -->
                <td><%= companyName %></td>
                <td><%= rs.getString("address") %></td>
                <td><%= companyCutoff %></td>
                <td><%= companyBranch %></td>
                <td><%= rs.getString("required_skills") %></td>
                <!-- Apply button or message -->
                <td>
                    <% if (isApplied) { %>
                        Applied
                    <% } else if (isEligible) { %>
                        <a href="#" onclick="apply('<%= companyName %>', '<%= companyId %>', '<%= (String) request.getSession().getAttribute("prn") %>')">Apply</a>
                    <% } else { %>
                        Not Eligible
                    <% } %>
                </td>
            </tr>
            <% 
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                        if (eligibilityResultSet != null) eligibilityResultSet.close();
                        if (eligibilityStmt != null) eligibilityStmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </table>
    </form>
    <!-- My Profile button -->
    <form action="profile.jsp" method="get">
        <button type="submit">My Profile</button>
    </form>
     <form action="login.jsp" method="get">
        <button type="submit">Logout</button>
    </form>
</body>
</html>