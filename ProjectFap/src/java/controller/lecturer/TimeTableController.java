package controller.assignment.lecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import controller.authentication.authorization.BaseRBACController;
import dal.assignment.LessionDBContext;
import dal.assignment.TimeSlotDBContext;
import entity.Account;
import entity.assignment.Lession;
import entity.assignment.Role;
import entity.assignment.TimeSlot;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DateTimeHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TimeTableController extends BaseRBACController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        String raw_from = req.getParameter("from");
        String raw_to = req.getParameter("to");
        
        String pageParam = req.getParameter("page");
        int page;
        if (pageParam == null || !pageParam.matches("\\d+")) {
            page = 1;
        } else {
            page = Integer.parseInt(pageParam);
        }
        
        String raw_lid = req.getParameter("lid");
        int lid;
        if (raw_lid == null || !raw_lid.matches("\\d+")) {
            lid = 1; // or any default value
        } else {
            lid = Integer.parseInt(raw_lid);
        }
        
        java.sql.Date from;
        java.sql.Date to;
        
        Date today = new Date();
        if(raw_from == null)
        {
            from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
        }
        
        if(raw_to == null)
        {
            to = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.
                    addDaysToDate(DateTimeHelper.getWeekStart(today),6));
        }
        else
        {
            to = java.sql.Date.valueOf(raw_to);
        }
        
        ArrayList<java.sql.Date> dates = DateTimeHelper.getDatesBetween(from, to, page);
        
        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();
        
        LessionDBContext lessDB = new LessionDBContext();
        ArrayList<Lession> lessions = lessDB.getBy(lid, from, to);
        
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("lessions", lessions);
        req.setAttribute("page", page);
        req.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}