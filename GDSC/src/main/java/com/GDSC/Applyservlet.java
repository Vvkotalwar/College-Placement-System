package com.GDSC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Applyservlet")
public class Applyservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String companyId = request.getParameter("companyId");
        String prn = (String) request.getSession().getAttribute("prn");
        String companyQuery = "SELECT company_name, cutoff, branch_type FROM company_details WHERE company_id = ?";
        String studentQuery = "SELECT name, branch FROM student_details WHERE prn = ?";
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/GDSC";
        String user = "root";
        String pass = "Vishnu@0912";
        
        System.out.println("companyId from request: " + companyId); // Print companyId
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("apply_error.jsp");
            return;
        }
        
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement companyStmt = conn.prepareStatement(companyQuery);
             PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
             PreparedStatement applyStmt = conn.prepareStatement("INSERT INTO applications (student_prn, company_id, company_name, cutoff, branch_type, student_name, student_branch) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            
            companyStmt.setString(1, companyId);
            studentStmt.setString(1, prn);
            
            try (ResultSet companyResult = companyStmt.executeQuery();
                 ResultSet studentResult = studentStmt.executeQuery()) {
                
                if (companyResult.next() && studentResult.next()) {
                    String companyName = companyResult.getString("company_name");
                    double companyCutoff = companyResult.getDouble("cutoff");
                    String companyBranch = companyResult.getString("branch_type");
                    
                    String studentName = studentResult.getString("name");
                    String studentBranch = studentResult.getString("branch");
                    
                    applyStmt.setString(1, prn);
                    applyStmt.setString(2, companyId);
                    applyStmt.setString(3, companyName);
                    applyStmt.setDouble(4, companyCutoff);
                    applyStmt.setString(5, companyBranch);
                    applyStmt.setString(6, studentName);
                    applyStmt.setString(7, studentBranch);
                    
                    int rowsInserted = applyStmt.executeUpdate();
                    if (rowsInserted > 0) {
                        response.sendRedirect("apply_sus.jsp");
                    } else {
                        response.sendRedirect("apply.jsp");
                    }
                } else {
                    response.sendRedirect("apply1.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("apply_error.jsp");
        }
    }
}
