<%@ page import="java.util.List" %>
<%@ page import="com.contactmanager.contactmanager.Contact" %>

<!DOCTYPE html>
<html>
<head>

    <style>
        body{
            font-family: Arial;
        }

        table{
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }

        th,td{
            border:1px solid black;
            padding:10px;
            text-align:center;
        }

        th{
            background-color: lightblue;
        }
    </style>
    <title>Contact List</title>
</head>
<body>

<h2>Contact List</h2>

<%
    List<Contact> contacts =
            (List<Contact>) session.getAttribute("contacts");

    if(contacts != null){
%>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>

    <%
        for(Contact c : contacts){
    %>

    <tr>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getPhone() %></td>
    </tr>

    <%
        }
    %>

</table>

<%
    }
%>

<br>
<a href="contact-form.jsp">Add New Contact</a>

</body>
</html>