/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import calendar.CalenderCalculator;
import controller.authentication.authorization.BaseRBACController;
import dal.LessonDBContext;
import dal.AccountDBContext;
import dal.TimeSlotDBContext;
import entity.Account;
import entity.Lesson;
import entity.Role;
import entity.TimeSlot;
import entity.Week;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Fatvv
 */
public class TimeTableLecturerController extends BaseRBACController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        String raw_year = req.getParameter("year");
        String raw_startDay = req.getParameter("startDay");

        Integer year = null;
        ArrayList<Week> weeks = new ArrayList<>();
        LocalDate startDay = null;
        ArrayList<Date> daysOfWeek = new ArrayList<>();
        LessonDBContext lessonDB = new LessonDBContext();
        ArrayList<Lesson> LessonList = new ArrayList<>();
        
         if (raw_year == null && raw_startDay == null) {
            year = LocalDate.now().getYear();
            weeks = CalenderCalculator.getListWeek(year);
            startDay = CalenderCalculator.getMondayDate(LocalDate.now());
            daysOfWeek = CalenderCalculator.getListDayOfWeek(startDay);
            Date from = Date.valueOf(startDay);
            Date to = Date.valueOf(startDay.plusDays(6));
            LessonList = lessonDB.getTimeTableOfInstructor(getIdStudentByAcc(account.getUsername()), from, to);
        }


    } 

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
