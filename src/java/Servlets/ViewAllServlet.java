
package Servlets;

import Database.Person;
import Database.PersonDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stelios Georgaras
 */
public class ViewAllServlet extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Εμφάνιση λογαριασμού</title>");            
            out.println("</head>");
            out.println("<style>" +
                        "table, th, td {" +
                        "  border:1px solid black;" +
                        "  text-align: center;" +
                        "  vertical-align: middle;" +
                        "}" +
                        "</style>");
            
            out.println("<body>");
            out.println("<h1>Servlet ViewIdServlet2 at " + request.getContextPath() + "</h1>");
            out.println("<h3>Λογαριασμοί </h3>");

            List<Person> list = PersonDao.showAcounts();
            
            
            for (Person p:list){
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
                            "    <td>"+p.getId()+"</td> " +
                            "    <td>"+p.getName()+"</td> " +
                            "    <td>"+p.getAddress()+"</td> " +
                            "    <td>"+p.getNumber()+"</td> " +
                            "    <td>"+p.getMail()+"</td> " +
                            "    <td>"+p.getBalance()+"</td> " +        
                            "    <td>"+p.isActive()+"</td> " +        
                            "  </tr> " +
                            
                            "</table>");
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
