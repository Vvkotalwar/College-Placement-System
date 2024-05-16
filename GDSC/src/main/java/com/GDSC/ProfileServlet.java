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
import javax.servlet.http.HttpSession;

@WebServlet("/GDSC/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve prn from session
        HttpSession session = request.getSession();
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
                // Set attributes for all fields
                request.setAttribute("studentName", rs.getString("name"));
                request.setAttribute("studentEmail", rs.getString("email"));
                request.setAttribute("branch", rs.getString("branch"));
                request.setAttribute("tenth", rs.getDouble("tenth"));
                request.setAttribute("twelfth", rs.getDouble("twelfth"));
                request.setAttribute("cgpa1", rs.getDouble("cgpa1"));
                request.setAttribute("cgpa2", rs.getDouble("cgpa2"));
                request.setAttribute("cgpa3", rs.getDouble("cgpa3"));
                request.setAttribute("cgpa4", rs.getDouble("cgpa4"));
                request.setAttribute("cgpa5", rs.getDouble("cgpa5"));
                request.setAttribute("cgpa6", rs.getDouble("cgpa6"));
                request.setAttribute("cgpa7", rs.getDouble("cgpa7"));
                request.setAttribute("cgpa8", rs.getDouble("cgpa8"));
                request.setAttribute("aggregate", rs.getDouble("aggregate"));
                request.setAttribute("nationality", rs.getString("nationality"));
                request.setAttribute("disabled", rs.getString("disabled"));
                request.setAttribute("gender", rs.getString("gender"));
                request.setAttribute("phoneNo", rs.getString("phone_no"));
                request.setAttribute("dob", rs.getDate("dob"));
                request.setAttribute("address", rs.getString("address"));
                request.setAttribute("skills", rs.getString("skills"));
                request.setAttribute("placed", rs.getString("placed"));
                
                // Forward the request and response to profile.jsp
                request.getRequestDispatcher("profile.jsp").forward(request, response);
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
    }
}
