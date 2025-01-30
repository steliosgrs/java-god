package Servlets;
import Database.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stelios Georgaras
 */
public class CreateServlet2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        
        // έρχεται η είσοδος από την φόρμα με request και αναθέτονται σε μεταβλητές
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mail = request.getParameter("mail");
        
        String phone_s = request.getParameter("phone");
        int phone = Integer.parseInt(phone_s);
        
        String money_s = request.getParameter("money");
        float money = Float.parseFloat(money_s);
        
        // Δημιουργούμε ένα αντικείμενο Person
        Person account = new Person();
        
        // Αναθέτουμε τις τιμές των μεταβλητών 
        // στο αντικείμενο Person με το όνομα account
        account.setName(name);
        account.setAddress(address);
        account.setMail(mail);
        account.setNumber(phone);
        account.setBalance(money);
        account.isActive();
        
        int status = PersonDao.createAccount(account);
        
        // Δημιουργούμε ένα αντικείμενο PrintWriter και για να δημιουργηθεί
        // δυναμικά η html ιστοσελίδα 
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateServlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateServlet2 at " + request.getContextPath() + "</h1>");

            if (status == 0 ){
                out.println("<h2>Account not Created</h2>");
            }else {
                out.println("<h2>Account Created Successfully</h2>");
            }
            
            out.println("<br>");out.println("<br>");
            out.println("<a href=\"index.html\">Μενού</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
