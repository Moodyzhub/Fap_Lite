/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.TimeSlot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatvv
 */
public class TimeSlotDBContext extends DBContext {

    
    public ArrayList<TimeSlot> list() {
        
        ArrayList<TimeSlot> slots = new ArrayList<>();
        try {
            
            String sql = "SELECT tid,tname FROM TimeSlot";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                TimeSlot slot = new TimeSlot();
                slot.setTime_id(rs.getInt("tid"));
                slot.setTime_name(rs.getString("tname"));
                slots.add(slot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slots;
    }

    
    
}
