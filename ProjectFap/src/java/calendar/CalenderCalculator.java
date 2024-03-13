/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendar;

/**
 *
 * @author Fatvv
 */
import entity.Week;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalenderCalculator {
    
    // create a list of weeks in a particular year
    public static ArrayList<Week> getWeeks(int year) {
        ArrayList<Week> weekList = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");
        
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        LocalDate firstMondayOfYear1 = null;
        // first day of year will be monday of first week
        if (firstDayOfYear.getDayOfWeek().getValue() != 1) {
            firstMondayOfYear1 = firstDayOfYear.plusDays(8 - firstDayOfYear.getDayOfWeek().getValue());
        } else {
            firstMondayOfYear1 = firstDayOfYear;
        }

        LocalDate lastDayOfYear = LocalDate.of(year, 12, 31);
        LocalDate lastSundayOfYear = null;
        // last day of year will be sunday of last week
        if (lastDayOfYear.getDayOfWeek().getValue() != 7) {
            lastSundayOfYear = lastDayOfYear.plusDays(7 - lastDayOfYear.getDayOfWeek().getValue());
        } else {
            lastSundayOfYear = lastDayOfYear;
        }
        //compare until first monay this year become first monay next year.
        LocalDate monday = firstMondayOfYear1;
        while (monday.isBefore(lastSundayOfYear)) {
            Week week = new Week();
            week.setStartDay(monday);
            week.setFrom(fmt.format(monday));
            week.setTo(fmt.format(monday.plusDays(6)));
            weekList.add(week);
            
            monday = monday.plusDays(7);
        }
        
        return weekList;
    }
    public static LocalDate getMondayDate(LocalDate pointDate){
        LocalDate mondayDate = pointDate.minusDays(pointDate.getDayOfWeek().getValue() - 1);
        return mondayDate;
    }
    
}
