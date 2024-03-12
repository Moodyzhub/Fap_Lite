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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <style>
        /* Add your custom CSS here */
    </style>
</head>
<body>
    <h1 class="text-center mb-4">Teacher Timetable</h1>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <label for="year">Year:</label>
                <select id="year" class="form-control">
                    <!-- Options will be populated by server-side code -->
                </select>
            </div>

            <div class="col-md-6">
                <label for="week">Week:</label>
                <select id="week" class="form-control">
                    <!-- Options will be populated by server-side code -->
                </select>
            </div>
        </div>

        <table class="table table-bordered mt-4">
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
                            <button class="btn btn-primary"><i class="fas fa-edit"></i> Edit</button>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>