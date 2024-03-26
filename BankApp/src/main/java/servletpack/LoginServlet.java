package servletpack;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helperpojo.UserDetails;
import task.Login;
import util.ApplicationException;
import util.BankMessage;
import interfaces.LoginOperations;

public class LoginServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        String userName= request.getParameter("userId");
        int userId =Integer.parseInt(userName);
        String password = request.getParameter("password");
        UserDetails user = new UserDetails();
        user.setId(userId);
        user.setPassword(password);
        
        LoginOperations login=new Login();

        try {
            if (login.login(user)) {
            	int userLevel=login.getUserLevel(userId);
            	if(userLevel==1) {
                response.sendRedirect("customer.html");
            }
            	else if(userLevel==2) {
                    response.sendRedirect("employee.html");
                }
            	
            	else if(userLevel==3) {
                    response.sendRedirect("admin.html");
                }
        }
            else {
                HttpSession session = request.getSession();
            }
        } 
        catch (ServletException e) {
        	
            throw new ApplicationException(BankMessage.INVALID_USER.getMessage());
        }
    }
}

}
