package com.GDSC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationServlet
 */
//@WebServlet("/GDSC/RegistrationServlet")
/*public class RegistrationServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int mobile_number = Integer.parseInt(request.getParameter("mobile_number"));
        String course = request.getParameter("course");
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
            String sql = "INSERT INTO student (name, email, mobile_number,course,prn, password, rpassword) VALUES (?, ?,?,?,? ,?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, mobile_number);
            statement.setString(4, course);
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
                response.sendRedirect("registration.jsp");
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
    
}*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";
    
    static {
        try {
            // Load MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String prn = request.getParameter("prn");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String branch = request.getParameter("branch");
        double tenth = parseDoubleOrDefault(request.getParameter("10th"), 0.0);
        double twelfth = parseDoubleOrDefault(request.getParameter("12th"), 0.0);
        double cgpa1 = parseDoubleOrDefault(request.getParameter("cgpa1"), 0.0);
        double cgpa2 = parseDoubleOrDefault(request.getParameter("cgpa2"), 0.0);
        double cgpa3 = parseDoubleOrDefault(request.getParameter("cgpa3"), 0.0);
        double cgpa4 = parseDoubleOrDefault(request.getParameter("cgpa4"), 0.0);
        double cgpa5 = parseDoubleOrDefault(request.getParameter("cgpa5"), 0.0);
        double cgpa6 = parseDoubleOrDefault(request.getParameter("cgpa6"), 0.0);
        double cgpa7 = parseDoubleOrDefault(request.getParameter("cgpa7"), 0.0);
        double cgpa8 = parseDoubleOrDefault(request.getParameter("cgpa8"), 0.0);
        double aggregate = parseDoubleOrDefault(request.getParameter("cgpa9"), 0.0);
        String nationality = request.getParameter("nationality");
        String disabled = request.getParameter("disabled");
        String gender = request.getParameter("gender");
        String phoneNo = request.getParameter("phoneNo");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String skills = request.getParameter("skills");
        String placed = request.getParameter("placed");

        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement
            String sql = "INSERT INTO student_details (prn, name, email, password, branch, tenth, twelfth, cgpa1, cgpa2, cgpa3, cgpa4, cgpa5, cgpa6, cgpa7, cgpa8, aggregate, nationality, disabled, gender, phone_no, dob, address, skills, placed) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the prepared statement parameters
            statement.setString(1, prn);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, branch);
            statement.setDouble(6, tenth);
            statement.setDouble(7, twelfth);
            statement.setDouble(8, cgpa1);
            statement.setDouble(9, cgpa2);
            statement.setDouble(10, cgpa3);
            statement.setDouble(11, cgpa4);
            statement.setDouble(12, cgpa5);
            statement.setDouble(13, cgpa6);
            statement.setDouble(14, cgpa7);
            statement.setDouble(15, cgpa8);
            statement.setDouble(16, aggregate);
            statement.setString(17, nationality);
            statement.setString(18, disabled);
            statement.setString(19, gender);
            statement.setString(20, phoneNo);
            statement.setString(21, dob);
            statement.setString(22, address);
            statement.setString(23, skills);
            statement.setString(24, placed);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("registration.jsp");
            }

            // Close the database connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the doPost() method
        doPost(request, response);
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // If parsing fails, return the default value
                return defaultValue;
            }
        } else {
            // If the value is null, return the default value
            return defaultValue;
        }
    }
}
