package com.GDSC;
/*import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GDSC/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
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
            String sql = "SELECT * FROM student WHERE email=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            
            // Execute query
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Authentication successful
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("student.jsp");
            } else {
                // Authentication failed
                response.sendRedirect("login.jsp");
            }
            
            // Close connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}*/
//package com.GDSC;
/*import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GDSC/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/GDSC";
        String user = "root";
        String pass = "Vishnu@0912";
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            // Check if it's admin login
            String action = request.getParameter("action");
            if (action != null && action.equals("admin_login")) {
                String adminEmail = request.getParameter("email");
                String adminPassword = request.getParameter("password");
                
                // Create SQL statement for admin login
                String adminSql = "SELECT * FROM admin WHERE email=? AND password=?";
                PreparedStatement adminStatement = conn.prepareStatement(adminSql);
                adminStatement.setString(1, adminEmail);
                adminStatement.setString(2, adminPassword);
                
                // Execute admin query
                ResultSet adminResultSet = adminStatement.executeQuery();
                
                if (adminResultSet.next()) {
                    // Admin authentication successful
                    HttpSession session = request.getSession();
                    session.setAttribute("email", adminEmail);
                    response.sendRedirect("admin_panel.jsp");
                } else {
                    // Admin authentication failed
                    response.sendRedirect("login.jsp");
                }
            } else {
                // It's regular user login
                
                // Create SQL statement for user login
                String userSql = "SELECT * FROM student WHERE email=? AND password=?";
                PreparedStatement userStatement = conn.prepareStatement(userSql);
                userStatement.setString(1, email);
                userStatement.setString(2, password);
                
                // Execute user query
                ResultSet userResultSet = userStatement.executeQuery();
                
                if (userResultSet.next()) {
                    // User authentication successful
                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    response.sendRedirect("student.jsp");
                } else {
                    // User authentication failed
                    response.sendRedirect("login.jsp");
                }
            }
            
            // Close connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}*/
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GDSC/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prn = request.getParameter("prn");
        String password = request.getParameter("password");
        
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/GDSC";
        String user = "root";
        String pass = "Vishnu@0912";
        
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            // Check if it's admin login
            String action = request.getParameter("action");
            if (action != null && action.equals("admin_login")) {
                String adminEmail = request.getParameter("email");
                String adminPassword = request.getParameter("password");
                
                // Create SQL statement for admin login
                String adminSql = "SELECT * FROM admin WHERE email=? AND password=?";
                PreparedStatement adminStatement = conn.prepareStatement(adminSql);
                adminStatement.setString(1, adminEmail);
                adminStatement.setString(2, adminPassword);
                
                // Execute admin query
                ResultSet adminResultSet = adminStatement.executeQuery();
                
                if (adminResultSet.next()) {
                    // Admin authentication successful
                    HttpSession session = request.getSession();
                    session.setAttribute("email", adminEmail);
                    response.sendRedirect("AdminHome.jsp");
                    return; // Exit method after successful login
                }
            } else {
                // It's regular user login
                
                // Create SQL statement for user login
                String userSql = "SELECT * FROM student_details WHERE prn=? AND password=?";
                PreparedStatement userStatement = conn.prepareStatement(userSql);
                userStatement.setString(1, prn);
                userStatement.setString(2, password);
                
                // Execute user query
                ResultSet userResultSet = userStatement.executeQuery();
                
                if (userResultSet.next()) {
                    // User authentication successful
                    HttpSession session = request.getSession();
                    session.setAttribute("prn", prn);
                    response.sendRedirect("student.jsp");
                    return; // Exit method after successful login
                }
            }
            
            // If neither admin nor student login successful, redirect to login page
            response.sendRedirect("login.jsp");
            
            // Close connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

