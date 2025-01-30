
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
 * @author NoLifer
 */
public class ViewIdServlet2 extends HttpServlet {


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
        Person account = PersonDao.getAccount_ID(id);
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Εμφάνιση λογαριασμού</title>");            
            out.println("</head>");
            out.println("<style>" +
                        "table, th, td {" +
                        "  border:1px solid black;" +
                        "}" +
                        "</style>");
            
            out.println("<body>");
            out.println("<h1>Servlet ViewIdServlet2 at " + request.getContextPath() + "</h1>");
            
            
            if (account.getName() != null){
                out.println("<h3>Ο λογαριασμός με αυτό το ID είναι </h3>");
                out.println("<br>");
                out.println("<table style='width:100%'> " +
                            "  <tr> " +
                            "    <th>ID</th> " +
                            "    <th>Name</th> " +
                            "    <th>Address</th> " +
                            "    <th>Number</th> " +
                            "    <th>mail</th> " +
                            "    <th>Balance</th> " +
                            "    <th>Active</th> " +
                            "  </tr> " +
                        
                            "  <tr> " +
                            "    <td>"+account.getId()+"</td> " +
                            "    <td>"+account.getName()+"</td> " +
                            "    <td>"+account.getAddress()+"</td> " +
                            "    <td>"+account.getNumber()+"</td> " +
                            "    <td>"+account.getMail()+"</td> " +
                            "    <td>"+account.getBalance()+"</td> " +        
                            "    <td>"+account.isActive()+"</td> " +        
                            "  </tr> " +
                            
                            "</table>");
                    out.println("<br>");
                    
            } else{
                out.println("<h3>Ο λογαριασμός με αυτό το ID δεν υπάρχει </h3>");
                
                out.println("<br>");
            }
                
            out.println("<br>");
            out.println(" <a href='index.html'>Μενού</a>");
            out.println("</body>");
            out.println("</html>");
        }
        
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
