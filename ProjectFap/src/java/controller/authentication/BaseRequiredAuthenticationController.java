/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Fatvv
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {
    
    
    private Account getAuthentication(HttpServletRequest req){
        // get account in the current session 
        Account account =(Account) req.getSession().getAttribute("account");
        
        if (account == null)
        {
            //check cookie
            String username = null;
            String password = null;
            Cookie[] cookies = req.getCookies();
            if(cookies!= null)
            {
                for(Cookie cook : cookies){
                    //check valid cookie that created in remember me by name and get value
                    if(cook.getName().equals("username"))
                        username = cook.getValue();
                    if(cook.getName().equals("password"))
                        password = cook.getValue();
                    if(username != null && password!=null)
                        break;
                }
                // if cookie is valid
                if(username != null && password!=null)
                {
                    AccountDBContext db = new AccountDBContext();
                    return db.getByUsernamePassword(username, password);
                }
                else
                    return null;

            }
            else
            {
                return null;
            }
       
        }
        return account;
    
    }
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get account from cookie/session 
        Account account =getAuthentication(req);
        if(account !=null){
            doPost(req, resp, account);
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    }
    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get account from cookie/session 
        Account account =getAuthentication(req);
        if(account !=null){
            doGet(req, resp, account);
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    }
    
    
}
