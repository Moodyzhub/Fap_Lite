/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication.authorization;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.RoleDBContext;
import entity.Account;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fatvv
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController{
    
    private ArrayList<Role> getRoles(HttpServletRequest req, Account account){
        //get url form current request
        String url = req.getServletPath();
        //create new RoleDBContext to access DB
        RoleDBContext db= new RoleDBContext();
        return db.getByUsernameAndUrl(account.getUsername(), url);
    
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }
    
}
