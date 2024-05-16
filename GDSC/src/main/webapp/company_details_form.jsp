<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Company Details Form</title>
    <link rel="stylesheet" type="text/css" href="css/company_detail.css">
</head>
<body>
    <h1>Enter Company Details</h1>
    <form method="post" action="/GDSC/ProcessCompanyDetailsServlet">
        <label for="companyId">CompanyId:</label>
        <input type="text" id="companyId" name="companyId" value="${param.companyId}"><br>

        <label for="companyName">Company Name:</label>
        <input type="text" id="companyName" name="companyName" value="${param.companyName}"><br>

        <label for="email">EMAIL:</label>
        <input type="email" id="email" name="email" value="${param.email}"><br>

        <label for="contactNo">contactNo:</label>
        <input type="text" id="contactNo" name="contactNo" value="${param.contactNo}"><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${param.address}"><br>

        <label for="hrName">HR Name:</label>
        <input type="text" id="hrName" name="hrName" value="${param.hrName}"><br>

        <label for="cutoff">Cutoff:</label>
        <input type="text" id="cutoff" name="cutoff" value="${param.cutoff}"><br>

        <label for="branch">Branch:</label>
        <select id="branch_type" name="branch">
            <option value="Btech.CSE" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.CSE") ? "selected" : "" %>>Btech.CSE</option>
            <option value="Btech.Electrical" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.Electrical") ? "selected" : "" %>>Btech.Electrical</option>
            <option value="Btech.Mechanical" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.Mechanical") ? "selected" : "" %>>Btech.Mechanical</option>
            <option value="Btech.ENTC" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("Btech.ENTC") ? "selected" : "" %>>Btech.ENTC</option>
            <option value="MCA" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MCA") ? "selected" : "" %>>MCA</option>
            <option value="MSc" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MSc") ? "selected" : "" %>>MSc</option>
            <option value="BSc" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("BSc") ? "selected" : "" %>>BSc</option>
            <option value="MBA" <%= request.getParameter("branch") != null && request.getParameter("branch").equals("MBA") ? "selected" : "" %>>MBA</option>
        </select><br>
        <label for="requiredSkills">Required Skills:</label>
        <input type="text" id="requiredSkills" name="requiredSkills" value="${param.requiredSkills}"><br>

        <input type="submit" value="Submit">
       
    </form>
</body>
</html>