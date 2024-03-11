/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.authentication;

import dal.AccountDBContext;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Fatvv
 */
public class LoginController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("view/authentication/login.jsp").forward(request, response);

    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        
        AccountDBContext db = new AccountDBContext();
        Account account =db.getByUsernamePassword(username, password);
        
        if(account != null)
        {
            String remember = request.getParameter("remember");
            if(remember !=null){
                //creat cookie of username and password and set name/value
                Cookie c_user = new Cookie("username", username);
                Cookie c_pass = new Cookie("password", password);
                c_user.setMaxAge(3600*24*7);
                c_pass.setMaxAge(3600*24*7);
                
                response.addCookie(c_pass);
                response.addCookie(c_user);
            
            }
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            response.getWriter().println("login successful!");
            
        
        }
        else
        {
            response.getWriter().println("login failed!");
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
