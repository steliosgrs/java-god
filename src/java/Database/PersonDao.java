package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class PersonDao {
    
    public static Connection getConnection(){
           
        Connection conn = null;
        try {
            // Load and Register the appropriate database driver for your database
            Class.forName("org.mariadb.jdbc.Driver");

            // Create the connection to the DB using your dbname                    dbusername      password
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bank", "georgaras", "1234");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("DB Connectrion exception " + ex);
        }
        return conn;
    }
    // Create an account - 1
    
    public static int createAccount(Person person){
        int status = 0;
        // Φτιάχνουμε το connection με την βάση
        Connection conn = PersonDao.getConnection();
        try{
            // Ετοιμάζουμε το Prepared Statement για την δημιουργία λογαριασμού
            // και εκτελούμε το sql queries 
            PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts "
                    + "(NAME,ADDRESS,NUMBER,mail,balance,active) VALUES (?,?,?,?,?,?)");
            ps.setString(1, person.getName());
            ps.setString(2, person.getAddress());
            ps.setInt(3, person.getNumber());
            ps.setString(4, person.getMail());
            ps.setFloat(5, person.getBalance());
            ps.setInt(6,1);         // ACTIVE ACCOUNT 
            status = ps.executeUpdate();
            conn.close();
        }catch(SQLException ex){
            System.out.println("SQL Connectrion exception " + ex);
        }
        return status;
    }
    
    // Delete an account - 8
    public static int deleteAcount(int id){
        int status = 0;
        Connection conn = PersonDao.getConnection();
        try{
            // Ετοιμάζουμε το Prepared Statement για την διαγραφή λογαριασμού
            // και εκτελούμε το sql queries 
            
            PreparedStatement ps = conn.prepareStatement("DELETE FROM accounts WHERE AccountID = ?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            conn.close();
      
        } catch (SQLException ex) {
            System.out.println("SQL exception " + ex);
        }
        return status;
    }
    
    // Find an account by id - 6
    public static Person getAccount_ID(int id){
        Person person = new Person();
        Connection conn = PersonDao.getConnection();
        try{
            // Μέσω του AccountID η συνάρτηση θα επιστρέψει ένα αντικείμενο Person
            // μέσω ενός ResultSet αντικειμένου αποθηκεύουμε τις τιμές από την εγγραφή
            // στην βάση με το συγκεκριμένο AccountID.
            // Γυρνάει το Person για εμφάνιση του στην html σελίδα
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts WHERE AccountID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setAddress(rs.getString(3));
                person.setNumber(rs.getInt(4));
                person.setMail(rs.getString(5));
                person.setBalance(rs.getFloat(6));
                person.setActive(rs.getBoolean(7));
            }
            conn.close();
        }catch(SQLException ex){
            System.out.println("SQL Connectrion exception " + ex);
        }
        return person;
    }
    
    // Show all accounts - 7
    public static List<Person> showAcounts(){
        
        // Δημιουργούμε ένα αντικείμενο ArrayList 
        // Και εκεί θα αποθηκεύσουμε όλες τις εγγραφές του πίνακα
        List<Person> Plist = new ArrayList();
        Connection conn = PersonDao.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts");
            ResultSet rs = ps.executeQuery();
            
            // Χρησιμοποιούμε το ίδιο μοτίβο με την προηγούμενη συνάττηση getAccount_ID
            // Και στο τέλος προσθέτουμε στην λίση Plist το Person.
            // Στο τέλος γυρνάει το List για να το τυπώσει.
            while (rs.next()){
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setAddress(rs.getString(3));
                person.setNumber(rs.getInt(4));
                person.setMail(rs.getString(5));
                person.setBalance(rs.getFloat(6));
                person.setActive(rs.getBoolean(7));
                Plist.add(person);
            }
            conn.close();
        }catch ( SQLException ex) {
            System.out.println("SQL exception " + ex);
        }
        return Plist;
    }
    
    // Money deposit - 2
    public static int deposit(int id, float dep){
        int status = 0;
        
        // Δημιουργία σύνδεσης - παίρνουμε την ένα αντικείμενο Person μέσω 
        // μέσω της συνάρτησης getAccount_ID.
        Connection conn = PersonDao.getConnection();
        Person p = PersonDao.getAccount_ID(id);
        if (p.getName() == null){
            status = 2;
            return status;
        } else {
            try{
                // Εκτελείται το sql query βρίσκοντας το balance και προσθέτουμε το
                // το χρηματικό ποσό dep 
                PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance="
                        + "((SELECT balance FROM accounts WHERE AccountID=?)+?) "
                        + "WHERE accounts.AccountID = ?");
    //                    + "AND accounts.active = ? ");
                ps.setInt(1, p.getId());
                ps.setFloat(2, dep);
                ps.setInt(3, p.getId());

                // Check if account is active
                if (p.isActive() == true){
                    System.out.println("act");
                    // Και αν είναι εκτελούμε το query
                    status = ps.executeUpdate();
                } else{ System.out.println(" NOT act"); }
                
                conn.close();
            }catch(SQLException ex){
                System.out.println("SQL Connectrion exception " + ex);
            }
        }
        return status;
    }
    
    // Money withdrawal - 3
    public static int withdrawal(int id, float wwal){
        int status = 0;
        
        // Με τον ίδιο τρόπο παίρνουμε το αντικείμενο Person
        Connection conn = PersonDao.getConnection();
        Person p = PersonDao.getAccount_ID(id);
        
        if (p.getName() == null){ // Check if account exists
            status = 2;
            return status;
        } else {
            
            try{
                // Εκτελείται το sql query βρίσκοντας το balance και αφαιρούμε το
                // το χρηματικό ποσό wwal 
                PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance="
                        + "((SELECT balance FROM accounts WHERE AccountID=?)-?) "
                        + "WHERE accounts.AccountID = ? AND accounts.balance >= ? ");
                ps.setInt(1, p.getId());
                ps.setFloat(2, wwal);
                ps.setInt(3, p.getId());
                ps.setFloat(4, wwal);
                // Check if account is active and balance exists
                if (p.getBalance() >= wwal){
                    System.out.println("Sufficient Balance "); 
                    if (p.isActive() == true){
                        System.out.println("act"); // Debug Message on GlassFish
                        // Και αν είναι εκτελούμε το query
                        status = ps.executeUpdate();
                    }else{ 
                        System.out.println(" NOT act"); // Debug Message on GlassFish
                    }
                    
                } else{ 
                    System.out.println(" Insufficient Balance"); 
                    return 3;
                }

                conn.close();
            }catch(SQLException ex){
                System.out.println("SQL Connectrion exception " + ex);
            }
        return status;
        }
    }

    
    // Transfer money in other account - 4
    public static int tranfer(int id, float tran,int id2){
//        int status=0;
        // 1ος τρόπος
        
        int status = 0;
        Connection conn = PersonDao.getConnection();
        Person p = PersonDao.getAccount_ID(id);
        
        if (p.getName() == null){ // Check if account exists
            status =2;
            //return status;
        } else {
            
            try{
                PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance="
                        + "((SELECT balance FROM accounts WHERE AccountID=?)-?) "
                        + "WHERE accounts.AccountID = ? AND accounts.balance >= ? ");
                ps.setInt(1, p.getId());
                ps.setFloat(2, tran);
                ps.setInt(3, p.getId());
                ps.setFloat(4, tran);
                // Check if account is active and balance exists
                if (p.getBalance() >= tran){
                    System.out.println("Sufficient Balance "); 
                    if (p.isActive() == true){
                        System.out.println("act"); // Debug Message on GlassFish
                        status = ps.executeUpdate();
                    }else{ 
                        System.out.println(" NOT act"); // Debug Message on GlassFish
                    }
                    
                } else{ 
                    System.out.println(" Insufficient Balance"); 
                    status = 3;
                    //return status;
                }

                conn.close();
            }catch(SQLException ex){
                System.out.println("SQL Connectrion exception " + ex);
            }
        }    
        int status2 = 0;
        if (status == 1 ){
            Connection conn2 = PersonDao.getConnection();
            Person p2 = PersonDao.getAccount_ID(id2);
            
            if (p.getName() == null){
                status2 = 2;
                return status2;
            } else {
                try{
                    PreparedStatement ps = conn2.prepareStatement("UPDATE accounts SET balance="
                            + "((SELECT balance FROM accounts WHERE AccountID=?)+?) WHERE accounts.AccountID = ?");
                    ps.setInt(1, p2.getId());
                    ps.setFloat(2, tran);
                    ps.setInt(3, p2.getId());

                    // Check if account is active
                    if (p.isActive() == true){
                        System.out.println("act");
                        status2 = ps.executeUpdate();
                    } else{ System.out.println(" NOT act"); }

                    conn.close();
                }catch(SQLException ex){
                    System.out.println("SQL Connectrion exception " + ex);
                }
            }
        }
        if (status == status2 && status == 1){
           status = 1;
           } else {
               status = 0;
           }
          
       return status ; 
    }
        
        // 2ος τρόπος
//        int status_with = 0;
//        int status_dep = 0;
//        int status =0;
//        status_with = withdrawal( id, tran);
//        if (status_with == 1){
//            status_dep= deposit( id2, tran);
//        }
        /*
        Connection conn = PersonDao.getConnection();
        try {
            status_with = withdrawal( id, tran);
            conn.close();
            
        } catch(SQLException ex){
                System.out.println("SQL Connectrion exception " + ex);
        }
        
        Connection conn2 = PersonDao.getConnection();
        try {
            status_dep= deposit( id2, tran);
            conn2.close();
            
        } catch(SQLException ex){
                System.out.println("SQL Connectrion exception " + ex);
        }

       if (status_with == status_dep && status_with == 1){
           status = 1;
           } else {
               status = 0;
           }
         
       return status ;
    }*/
    
    
    // Active or Deactive an account - 5
    public static int Act_Deact(int id){
        int status = 0;
        
        // Δημιουργία σύνδεσης - παίρνουμε την ένα αντικείμενο Person μέσω 
        // μέσω της συνάρτησης getAccount_ID.
        Connection conn = PersonDao.getConnection();
        Person p = PersonDao.getAccount_ID(id);
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET active = NOT(active)"
                                                    + " WHERE AccountID = ?");
            ps.setInt(1, id);
            
            // Γίνεται έλεγχος για  το αν ο λογαριασμός είσαι
            if (p.isActive()==true){
                status = ps.executeUpdate();
                conn.close();
                status = 2;
                return status;
            }else if(p.isActive()==false){
                
                status = ps.executeUpdate();
                conn.close();
                return status;
            }else{
                conn.close();
                status = 3;
                return status;
            }
//            status = ps.executeUpdate();
//            conn.close();
      
        } catch (SQLException ex) {
            System.out.println("SQL exception " + ex);
        }
        return status;
    }
    
}
