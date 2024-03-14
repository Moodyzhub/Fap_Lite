<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TimeTable for Lecturer</title>
    </head>
    <body>
        <table border = "1px">
            <tr>
                <td rowspan="2">
                    <form id="yearForm" action="timetable" method="GET">
                        <select name="year" onchange="document.getElementById('yearForm').submit()">
                            <c:forEach begin="2022" end="2024" var="i">
                                //when user selected a option that will be default after load
                                <option <c:if test="${requestScope.year == i}"> selected</c:if> value="${i}">${i}</option>    
                            </c:forEach>
                        </select> <br>
                    </form>
                    <form id="dayForm" action="timetable" method="GET">
                        <select name="startDay" onchange="document.getElementById('dayForm').submit()">
                            <c:forEach items="${requestScope.weeks}" var="week">
                                <option <c:if test="${requestScope.startDay == week.startDay}">selected </c:if>value="${week.startDay}"> ${week.from} To ${week.to}</option>      
                            </c:forEach>
                        </select>
                        
                    </form>
                    
                </td>
                // show name 7 day in week
                <c:forEach items="${requestScope.daysOfWeek}" var="day">
                    <td>
                        <fmt:formatDate pattern="E" value="${day}"/>
                    </td>
                </c:forEach>
            </tr>
            
        </table>
    </body>
</html>