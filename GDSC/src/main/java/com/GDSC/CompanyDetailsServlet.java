package com.GDSC;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
@WebServlet("/GDSC/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC?useSSL=false&serverTimezone=UTC";

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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Company> companies = new ArrayList<>();
        Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GDSC?useTimezone=true&serverTimezone=UTC", "root", "Vishnu@0912");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            //conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to fetch company details
            String sql = "SELECT * FROM company_details";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Retrieve company details from result set
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyId(rs.getString("company_id"));
                company.setCompanyName(rs.getString("company_name"));
                company.setEmail(rs.getString("email"));
                company.setContactNo(rs.getString("contact_no"));
                company.setAddress(rs.getString("address"));
                company.setHrName(rs.getString("hr_name"));
                company.setCutoff(rs.getBigDecimal("cutoff"));
                company.setBranch(rs.getString("branch_type"));
                company.setRequiredSkills(rs.getString("required_skills"));
                //company.setBranchType(rs.getString("branch_type"));
                companies.add(company);
            }

            // Set companies list as request attribute
            request.setAttribute("companies", companies);

            // Forward request to JSP
            request.getRequestDispatcher("company_details.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
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
*/

/*
@WebServlet("/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Company> companies = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to fetch company details
            String sql = "SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Retrieve company details from result set
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyName(rs.getString("company_name"));
                company.setAddress(rs.getString("address"));
                company.setCutoff(rs.getBigDecimal("cutoff"));
                company.setBranch(rs.getString("branch_type"));
                company.setRequiredSkills(rs.getString("required_skills"));
                companies.add(company);
            }

            // Set companies list as request attribute
            request.setAttribute("companies", companies);

            // Forward request to JSP
            request.getRequestDispatcher("student.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
*/

/*
@WebServlet("/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
        final String DB_USERNAME = "root";
        final String DB_PASSWORD = "Vishnu@0912";
        
         {
            try {
                // Load MySQL JDBC driver class
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery("SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details");

            if (rs.next()) {
                String companyName = rs.getString("company_name");
                String address = rs.getString("address");
                String cutoff = rs.getString("cutoff");
                String branchType = rs.getString("branch_type");
                String requiredSkills = rs.getString("required_skills");

                response.setContentType("text/html");  // Set response content type

                StringBuilder output = new StringBuilder();
                output.append("<h1>Company Details</h1>");
                output.append("<table border=\"1\">");
                output.append("<thead>");
                output.append("<tr>");
                output.append("<th>Company Name</th>");
                output.append("<th>Address</th>");
                output.append("<th>Cutoff</th>");
                output.append("<th>Branch Type</th>");
                output.append("<th>Required Skills</th>");
                output.append("</tr>");
                output.append("</thead>");
                output.append("<tbody>");
                output.append("<tr>");
                output.append("<td>" + companyName + "</td>");
                output.append("<td>" + address + "</td>");
                output.append("<td>" + cutoff + "</td>");
                output.append("<td>" + branchType + "</td>");
                output.append("<td>" + requiredSkills + "</td>");
                output.append("</tr>");
                output.append("</tbody>");
                output.append("</table>");

                response.getWriter().println(output.toString());
            } else {
                response.getWriter().println("No company details found.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            // Handle database errors appropriately
        }
    }
}
*/

/*@WebServlet("/GDSC/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to fetch company details
            String sql = "SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Retrieve company details from result set
            while (rs.next()) {
                Company company = new Company();
                company.setCompanyName(rs.getString("company_name"));
                company.setAddress(rs.getString("address"));
                company.setCutoff(rs.getBigDecimal("cutoff"));
                company.setBranch(rs.getString("branch_type"));
                company.setRequiredSkills(rs.getString("required_skills"));
                // Do something with the company object, such as storing it in a database or passing it to another method
            }

            // Forward request to JSP
            request.getRequestDispatcher("student.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}*/

/*@WebServlet("/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to fetch company details
            String sql = "SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Set companies list as request attribute
            request.setAttribute("companies", rs);

            // Forward request to JSP
            request.getRequestDispatcher("student.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
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

@WebServlet("/CompanyDetailsServlet")
public class CompanyDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/GDSC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Vishnu@0912";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet companiesResultSet = null; // Declare companiesResultSet
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Query to fetch company details
            String sql = "SELECT company_name, address, cutoff, branch_type, required_skills FROM company_details";
            stmt = conn.prepareStatement(sql);
            companiesResultSet = stmt.executeQuery(); // Assign the result to companiesResultSet

            // Set ResultSet as a request attribute
            request.setAttribute("companiesResultSet", companiesResultSet);

            // Forward request to JSP
            request.getRequestDispatcher("student.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (companiesResultSet != null)
                    companiesResultSet.close(); // Close companiesResultSet
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

