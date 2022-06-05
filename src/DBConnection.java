import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
    String url = "jdbc:mysql://localhost:3306/cube_coding_bootcamp1";
    String name = "root";
    String pass = "24038304937@kalerantes";

    Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, name, pass);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return connection;
    }


}
//    String name,pass,url;

//          String  url="jdbc:mysql://localhost:3306/cube_coding_bootcamp1";
//          String name="root";
//         String   pass="24038304937@kalerantes";
//            Connection con = DriverManager.getConnection(url,name,pass);
//            System.out.println("Connection created");
//            Statement stmt=con.createStatement();

