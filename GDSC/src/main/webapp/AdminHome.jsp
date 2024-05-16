<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h2 {
            color: #333;
        }
        form {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"],
        input[type="submit"],
        button[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box; /* Ensure input width includes padding and border */
        }
        input[type="text"] {
            width: calc(100% - 22px); /* Adjust width to account for padding and border */
        }
        input[type="submit"],
        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover,
        button[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <center><h2>Registration</h2></center>
    
    <!-- Button to redirect to registration page -->
    <form action="company_details_form.jsp">
        <button type="submit">Add New Company</button>
    </form>
    
    <center><h2>Fetch Student Details</h2></center>
    
    <!-- Form to search by PRN -->
    <form action="/GDSC/SearchStudentServlet" method="post">
        <label for="prn">PRN:</label>
        <input type="text" id="prn" name="prn"><br><br>
        
        <input type="submit" value="Search by PRN">
    </form>
    
    <!-- Form to search by company name -->
    <form action="/GDSC/SearchStudentServlet" method="post">
        <label for="companyName">Company Name:</label>
        <input type="text" id="companyName" name="companyName"><br><br>
        
        <input type="submit" value="Search by Company Name">
    </form>
    
    <!-- Form to fetch all records -->
    <form action="/GDSC/SearchStudentServlet" method="post">
        <input type="hidden" name="allRecords" value="true">
        <input type="submit" value="Fetch All Records">
    </form>
</body>
</html>
