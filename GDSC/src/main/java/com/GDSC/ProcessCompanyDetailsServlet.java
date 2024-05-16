package com.GDSC;
import java.io.IOException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GDSC/ProcessCompanyDetailsServlet")
public class ProcessCompanyDetailsServlet extends HttpServlet {
    
	
    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";
    
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String companyId = request.getParameter("companyId");
        String companyName = request.getParameter("companyName");
        String email = request.getParameter("email");
        String contactNo = request.getParameter("contactNo");
        String address = request.getParameter("address");
        String hrName = request.getParameter("hrName");
        BigDecimal cutoff = new BigDecimal(request.getParameter("cutoff"));
        String branch = request.getParameter("branch");
        String requiredSkills = request.getParameter("requiredSkills");
       

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish database connection
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Prepare SQL statement
            String sql = "INSERT INTO company_details (company_id, company_name, email, contact_no, address, hr_name, cutoff, branch_type, required_skills ) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set the prepared statement parameters
            statement.setString(1, companyId);
            statement.setString(2, companyName);
            statement.setString(3, email);
            statement.setString(4, contactNo);
            statement.setString(5, address);
            statement.setString(6, hrName);
            statement.setBigDecimal(7, cutoff);
            statement.setString(8, branch);
            statement.setString(9, requiredSkills);
            

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Data inserted successfully, redirect back to the form page
                response.sendRedirect("company_details_form.jsp");
            } else {
                // Handle the case where no rows were inserted
                // You can redirect to an error page or display an error message
                response.sendRedirect("error.jsp");
            }

            // Close the database connection
            statement.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
