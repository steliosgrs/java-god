
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
public class TransferServlet2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        
        // 1o Account
        String id_s = request.getParameter("id");
        int id = Integer.parseInt(id_s);
        
        String money_s = request.getParameter("money");
        float money = Float.parseFloat(money_s);
        
        // 2o Account
        String id_s2 = request.getParameter("id2");
        int id2 = Integer.parseInt(id_s2);


        int status = PersonDao.tranfer(id,money,id2);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TransferServlet2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransferServlet2 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            if (status == 0 ){
                out.println("<h2>Withdrawal not Successfully</h2>");
                out.println("<h2>Deposit not Successfully</h2>");

            }else if (status == 2){
                out.println("<h2>Deposit not Successfully</h2>");
                out.println("<h2>Account does not exist</h2>");
            }else if (status == 1){
                out.println("<h2>Withdrawal money Successfully</h2>");
            }else {
                out.println("<h2>error</h2>");
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
