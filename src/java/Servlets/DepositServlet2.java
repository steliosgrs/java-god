
package Servlets;

import Database.Person;
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
public class DepositServlet2 extends HttpServlet {


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
        String id_s = request.getParameter("id");
        int id = Integer.parseInt(id_s);
        
        String money_s = request.getParameter("money");
        float money = Float.parseFloat(money_s);

        int status = PersonDao.deposit(id,money);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DepositServlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DepositServlet2 at " + request.getContextPath() + "</h1>");

            if (status == 0 ){
                out.println("<h2>Deposit Error</h2>");
                out.println("<h2>Transaction Error – Account is not active</h2>");
            }else if (status == 2){
                out.println("<h2>Deposit Error</h2>");
                out.println("<h2>Transaction Error – Account not found</h2>");
            }else {
                out.println("<h2>Deposit money Successfully</h2>");
                
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
