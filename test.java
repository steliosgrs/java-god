if (account.getName() != null){
                out.println("<h3>Ο λογαριασμός με αυτό το ID δεν υπάρχει </h3>");
                out.println("<table style=\"width:100%\"> " +
                            "  <tr> " +
                            "    <th>ID</th> " +
                            "    <th>Name</th> " +
                            "    <th>Address</th> " +
                            "    <th>Number</th> " +
                            "    <th>mail</th> " +
                            "    <th>Balance</th> " +
//                            "    <th>Address</th> " +
                            "  </tr> " +

                            "  <tr> " +
                            "    <td>"+account.getId()+"</td> " +
                            "    <td>"+account.getName()+"</td> " +
                            "    <td>"+account.getAddress()+"</td> " +
                            "    <td>"+account.getNumber()+"</td> " +
                            "    <td>"+account.getMail()+"</td> " +
                            "    <td>"+account.getBalance()+"</td> " +

                            "  </tr> " +

                            "</table>");
                    out.println("<br>");
            } else{
                out.println("<h3>Ο λογαριασμός με αυτό το ID είναι </h3>");
                out.println("<br>");
            }

            out.println("<br>");
            out.println("\ <a href=\"index.html\">Μενού</a>");
            out.println("</body>");
            out.println("</html>");
