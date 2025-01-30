
package Servlets;

import Database.PersonDao;
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
public class ActiveDectServlet2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String id_s = request.getParameter("id");
        int id = Integer.parseInt(id_s);

        int status = PersonDao.Act_Deact(id);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DepositServlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DepositServlet2 at " + request.getContextPath() + "</h1>");

            if (status == 0 ){
                out.println("<h2>Activation Error</h2>");
                out.println("<h2>Account not found</h2>");
            } else if( status == 2){
                out.println("<h2>Account Deactivated</h2>");
            }else {
                out.println("<h2>Account Activated</h2>");
                
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
    }// </editor-fold>

}
