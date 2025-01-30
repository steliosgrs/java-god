package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Stelios Georgaras
 */
public class CreateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Δημιουργούμε το αντικείμενο PrintWriter
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Δημιουργία λογαριασμού</title>");            
            out.println("</head>");
            
            out.println("<body>");
            out.println("<h1>Servlet CreateServlet at " + request.getContextPath() + "</h1>");
            out.println("<form class='' action='CreateServlet2'  method='post'>");
            
            // Φόρμα για την δημιουργία χρήστη
            out.println("<label for='name'>Ονοματεπώνυμο: </label>");
            out.println("<input type='text' name='name' id='name' >");
            out.println("<br>");

            out.println("<label for='address'>Διεύθυνση: </label>");
            out.println("<input type='text' name='address' id='address'> ");
            out.println("<br>");

            out.println("<label for='phone'>Τηλέφωνο: </label>");
            out.println("<input type='number' name='phone' id='phone' >");
            out.println("<br>");

            out.println("<label for='mail'>Mail: </label>");
            out.println("<input type='text' name='mail' id='mail' >");
            out.println("<br>");

            out.println("<label for='money'>Ποσό χρημάτων: </label>");
            out.println("<input type='number' step='0.01' name='money' id='money' >");
            out.println("<br>");
            
            out.println("<input type='submit' value='Submit'>");
            out.println("<a href='index.html'>Menu</a>");
            
            out.println("</body>");
            out.println("</html>");
        }
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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
