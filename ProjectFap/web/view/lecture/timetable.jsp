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
                        
                    </form>
                    <form id="dayForm" action="timetable" method="GET">
                        
                    </form>
                    
                </td>
                <c:forEach items="${requestScope.daysOfWeek}" var="day">
                    <td>
                        <fmt:formatDate pattern="E" value="${day}"/>
                    </td>
                </c:forEach>
            </tr>
            
        </table>
    </body>
</html>