<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company Details</title>
    <script>
        function apply(companyName) {
            // Display a popup message
            alert("You have successfully applied for " + companyName);
        }
    </script>
</head>
<body>
    <h1>Company Details</h1>
    <table border="1">
        <tr>
            <th>Company Name</th>
            <th>Address</th>
            <th>Cutoff</th>
            <th>Branch Type</th>
            <th>Required Skills</th>
            <th>Action</th>
        </tr>
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
                String sql = "SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String companyName = rs.getString("company_name");
                    String companyBranch = rs.getString("branch_type");
                    double companyCutoff = rs.getDouble("cutoff");
                    
                    // Check eligibility of the student
                    String eligibilityQuery = "SELECT COUNT(*) AS count FROM student_details WHERE branch = ? AND aggregate >= ?";
                    eligibilityStmt = conn.prepareStatement(eligibilityQuery);
                    eligibilityStmt.setString(1, companyBranch);
                    eligibilityStmt.setDouble(2, companyCutoff);
                    eligibilityResultSet = eligibilityStmt.executeQuery();
                    
                    boolean isEligible = false;
                    if (eligibilityResultSet.next()) {
                        int count = eligibilityResultSet.getInt("count");
                        if (count > 0) {
                            isEligible = true;
                        }
                    }
        %>
        <tr>
            <td><%= companyName %></td>
            <td><%= rs.getString("address") %></td>
            <td><%= companyCutoff %></td>
            <td><%= companyBranch %></td>
            <td><%= rs.getString("required_skills") %></td>
            <td>
                <% if (isEligible) { %>
                    <a href="#" onclick="apply('<%= companyName %>')">Apply</a>
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
</body>
</html>
