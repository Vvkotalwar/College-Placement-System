package com.GDSC;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve PRN, company name, download, and allRecords parameters from the request
        String prn = request.getParameter("prn");
        String companyName = request.getParameter("companyName");
        String download = request.getParameter("download");
        String allRecords = request.getParameter("allRecords");

        // Initialize JDBC objects
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the MySQL database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            if (allRecords != null && "true".equals(allRecords)) {
                // Fetch all records
                String sql = "SELECT * FROM applications";
                stmt = conn.prepareStatement(sql);
            } else if (prn != null && !prn.isEmpty()) {
                // Search by PRN
                String sql = "SELECT * FROM applications WHERE student_prn = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, prn);
            } else if (companyName != null && !companyName.isEmpty()) {
                // Search by company name
                String sql = "SELECT * FROM applications WHERE company_name = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, companyName);
            } else {
                response.getWriter().println("<p>Please enter either PRN, company name, or select all records.</p>");
                return;
            }

            // Execute the query
            rs = stmt.executeQuery();

            if ("true".equals(download)) {
                // Set response content type to CSV
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"student_details.csv\"");
                PrintWriter out = response.getWriter();

                // Write CSV header
                out.println("Company Name,Name,Branch,Cutoff");

                // Process the results and write CSV rows
                boolean found = false;
                while (rs.next()) {
                    String studentName = rs.getString("student_name");
                    String studentBranch = rs.getString("student_branch");
                    double cutoff = rs.getDouble("cutoff");
                    String compName = rs.getString("company_name");
                    // Write student details in CSV row
                    out.printf("%s,%s,%s,%.2f%n", compName, studentName, studentBranch, cutoff);
                    found = true;
                }

                if (!found) {
                    out.println("No student found with provided PRN or company name.");
                }

            } else {
                // Display results in HTML
                PrintWriter out = response.getWriter();
                out.println("<html><head>");
                out.println("<title>Student Details</title>");
                out.println("<style>");
                out.println("table { border-collapse: collapse; width: 100%; }");
                out.println("th, td { text-align: left; padding: 8px; }");
                out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
                out.println("th { background-color: #4CAF50; color: white; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<h2>Student Details</h2>");

                boolean found = false;
                out.println("<table>");
                out.println("<tr><th>Company Name</th><th>Name</th><th>Branch</th><th>Cutoff</th></tr>");
                while (rs.next()) {
                    String studentName = rs.getString("student_name");
                    String studentBranch = rs.getString("student_branch");
                    double cutoff = rs.getDouble("cutoff");
                    String compName = rs.getString("company_name");
                    // Display student details in table row
                    out.println("<tr>");
                    out.println("<td>" + compName + "</td>");
                    out.println("<td>" + studentName + "</td>");
                    out.println("<td>" + studentBranch + "</td>");
                    out.println("<td>" + cutoff + "</td>");
                    out.println("</tr>");
                    found = true;
                }
                out.println("</table>");
                if (!found) {
                    out.println("<p>No student found with provided PRN or company name.</p>");
                }

                // Add download button
                out.println("<form method='post' action='SearchStudentServlet'>");
                if (prn != null && !prn.isEmpty()) {
                    out.println("<input type='hidden' name='prn' value='" + prn + "'>");
                }
                if (companyName != null && !companyName.isEmpty()) {
                    out.println("<input type='hidden' name='companyName' value='" + companyName + "'>");
                }
                out.println("<input type='hidden' name='download' value='true'>");
                out.println("<button type='submit'>Download as CSV</button>");
                out.println("</form>");

                out.println("</body></html>");
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // Handle any errors
            throw new ServletException("Error in database operation", ex);
        } finally {
            // Clean up JDBC resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
