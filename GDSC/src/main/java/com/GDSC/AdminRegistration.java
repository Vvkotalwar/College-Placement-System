package com.GDSC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminRegistration
 */
@WebServlet("/AdminRegistration")
public class AdminRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        long mobile_number = Long.parseLong(request.getParameter("mobile_number"));
        String faculty = request.getParameter("course");
        int prn = Integer.parseInt(request.getParameter("prn"));
        String password = request.getParameter("password");
        String rpassword = request.getParameter("rpassword");
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/GDSC";
        String user = "root";
        String pass = "Vishnu@0912";
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            // Create SQL statement
            String sql = "INSERT INTO admin (name, email, mobile_number,faculty,prn, password, rpassword) VALUES (?, ?,?,?,? ,?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setLong(3, mobile_number);
            statement.setString(4, faculty);
            statement.setInt(5, prn);
            statement.setString(6, password);
            statement.setString(7, rpassword);
            
            // Execute update
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0) {
                // Registration successful
                response.sendRedirect("login.jsp");
            } else {
                // Registration failed
                response.sendRedirect("Admin_Regi.jsp");
            }
            
            // Close connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the doPost() method
        doPost(request, response);
    }
}
