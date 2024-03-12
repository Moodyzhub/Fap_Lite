<%-- 
    Document   : timetable
    Created on : Mar 11, 2024, 11:03:32 PM
    Author     : Fatvv
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TimeTable</title>
</head>
<body>
    <h1>TimeTable</h1>
    
    <%-- Dropdown menu cho năm --%>
    <label for="year">Chọn năm:</label>
    <select id="year">
        <% 
            // Lặp qua 5 năm từ năm hiện tại trở lại 5 năm
            int currentYear = java.time.Year.now().getValue();
            for(int i = currentYear - 5; i <= currentYear + 5; i++) {
        %>
        <option value="<%= i %>"><%= i %></option>
        <% } %>
    </select>
    
    <%-- Dropdown menu cho tuần --%>
    <label for="week">Chọn tuần:</label>
    <select id="week">
        <% 
            java.time.LocalDate currentDate = java.time.LocalDate.now();
            java.time.temporal.WeekFields weekFields = java.time.temporal.WeekFields.ISO;
            // Lặp qua 52 tuần trong năm
            for(int i = 1; i <= 52; i++) {
                java.time.LocalDate firstDayOfWeek = currentDate.with(weekFields.weekOfYear(), i).with(weekFields.dayOfWeek(), 1);
                java.time.LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);
        %>
        <option value="<%= firstDayOfWeek %> - <%= lastDayOfWeek %>">Tuần <%= i %>: <%= firstDayOfWeek %> - <%= lastDayOfWeek %></option>
        <% } %>
    </select>
    
    <%-- Bảng hiển thị Thời gian --%>
    <table border="1">
        <tr>
            <th>Thứ</th>
            <th>Ngày</th>
        </tr>
        <% 
            java.time.LocalDate startDate = java.time.LocalDate.now().with(weekFields.dayOfWeek(), 1);
            for (int i = 0; i < 7; i++) {
        %>
        <tr>
            <td><%= startDate.plusDays(i).getDayOfWeek().toString() %></td>
            <td><%= startDate.plusDays(i) %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
