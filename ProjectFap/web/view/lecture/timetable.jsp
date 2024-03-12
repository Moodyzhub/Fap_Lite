<%-- 
    Document   : timetable
    Created on : Mar 11, 2024, 11:03:32 PM
    Author     : Fatvv
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Timetable</title>
</head>
<body>
    <h1>Teacher Timetable</h1>

    <label for="year">Year:</label>
    <select id="year">
        <!-- Options will be populated by server-side code -->
    </select>

    <label for="week">Week:</label>
    <select id="week">
        <!-- Options will be populated by server-side code -->
    </select>

    <table>
        <tr>
            <th></th>
            <th>Monday</th>
            <th>Tuesday</th>
            <th>Wednesday</th>
            <th>Thursday</th>
            <th>Friday</th>
            <th>Saturday</th>
            <th>Sunday</th>
        </tr>
        <% for (int i = 1; i <= 6; i++) { %>
            <tr>
                <td>Slot <%= i %></td>
                <% for (int j = 1; j <= 7; j++) { %>
                    <td>
                        <!-- Lesson details will be populated by server-side code -->
                        <button>Edit</button>
                    </td>
                <% } %>
            </tr>
        <% } %>
    </table>
</body>
</html>