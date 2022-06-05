import java.sql.*;

public class Main {
    public static void main(String[] args) {

        /**COURSES*/

        /**READ ALL COURSES */

        String sql = "SELECT * FROM COURSES";


        DBConnection db = new DBConnection();
        Connection con = db.getConnection();
        try {
//            Statement stmt = con.createStatement();
            Statement stmt=con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Course s = new Course(rs.getInt("ID"), rs.getString("title"), rs.getString("stream"), rs.getString("type"), rs.getDate("start_date"), rs.getDate("end_date"));


//                System.out.println("ID:" + s.getId() + " Title: " + s.getTitle() + " Stream: " + s.getStream() + "Type: " + s.getType() + " Start_Date: " + s.getStart_date() + "End_Date: " + s.getEnd_date());
                    System.out.println(s.toString());
//                System.out.println("Connection closed");
            }
            con.close();
//            rs.close();
//            stmt.close();
            System.out.println("Connection closed\n");
        } catch (Exception e) {
            System.out.println(e.toString());
        }


        /**
         * READ SPECIFIC COURSE
         */


        String sql1 = "SELECT * FROM Courses Where id=?";
        DBConnection db1 = new DBConnection();
        Connection con1 = db1.getConnection();
        ResultSet rs1=null;
        Course course1;

        try {
            PreparedStatement stmt1 = con1.prepareStatement(sql1);
            //SELECT COURSE PUT AT X THE ID THAT YOU WANT;
            stmt1.setInt(1,3);
            rs1 = stmt1.executeQuery();
            if(rs1.next()){
            int findid=rs1.getInt("id");

                System.out.println("The course you are looking for is:");
                course1 = new Course(findid, rs1.getString("title"), rs1.getString("stream"), rs1.getString("type"), rs1.getDate("start_date"), rs1.getDate("end_date"));
                System.out.println("ID:" + course1.getId() + " Title: " + course1.getTitle() + " Stream: " + course1.getStream() + "Type: " + course1.getType() + " Start_Date: " + course1.getStart_date() + "End_Date: " + course1.getEnd_date());
            }
            con1.close();
            System.out.println("Connection closed");
        } catch (Exception e1) {
            System.out.println(e1.toString());
        }




        /**ADD COURSE*/
        String sql2="INSERT INTO Courses (title,stream,type,start_date,end_date) VALUES (?,?,?,?,?) ";
        DBConnection db2 = new DBConnection();
        Connection con2 = db2.getConnection();
        ResultSet rs2=null;
        Course course2;


        try{

            PreparedStatement stmt2=con2.prepareStatement(sql2);
            stmt2.setString(1,"CB123");
            stmt2.setString(2,"JAVA");
            stmt2.setString (3,"Part-time");
            stmt2.setDate(4, Date.valueOf("2021-09-03"));
            stmt2.setDate(5, Date.valueOf("2021-12-03"));
//            course2=new Course(stmt2.setString(1,"CB124"),stmt2.setString(2,"JAVAr"),stmt2.setString (3,"Part-time"),stmt2.setDate(4,java.sql.Date.valueOf("2021-09-03")), stmt2.setDate(5,java.sql.Date.valueOf("2021-12-03")));

            int result=stmt2.executeUpdate();
            if(result==1){
                System.out.println("Course succesfully inserted!!!");
            }

            con2.close();


        }catch (Exception e2) {
            System.out.println(e2.toString());
        }


        /**DELETE COURSE*/
        String sql3="DELETE FROM COURSES WHERE ID=?";
        DBConnection db3=new DBConnection();
        Connection con3=db3.getConnection();

        try{
            PreparedStatement stmt3=con3.prepareStatement(sql3);
            //INSERT THE ID THAT YOU WANT TO DELETE!!
            stmt3.setInt(1,10);
            int result=stmt3.executeUpdate();
            if(result==1){
                System.out.println("Course has succesfully Deleted!!!");

            }

            con3.close();


        }catch (Exception e3) {
            System.out.println(e3.toString());
        }

        /**UPDATE COURSE*/

        String sql4="UPDATE COURSES SET title=?,stream=?,type=?,start_date=?,end_date=? WHERE id=?";
        DBConnection db4=new DBConnection();
        Connection con4=db4.getConnection();

        try{
            PreparedStatement stmt4=con4.prepareStatement(sql4);

            //UPDATE THE ROW
            stmt4.setString(1,"CB2310");
            stmt4.setString(2,"Ruby");
            stmt4.setString(3,"Full-Time");
            stmt4.setDate(4, Date.valueOf("2021-10-01"));
            stmt4.setDate(5, Date.valueOf("2021-04-15"));
            //PUT THE ID OF THE ROW THAT YOU WANT TO UPDATE
            stmt4.setInt(6,11);
            int result=stmt4.executeUpdate();
            if(result==1){
                System.out.println("Course has successfully updated");
            }

        }catch(Exception e4){
            System.out.println(e4.toString());
        }

    }

    }






//        String name,pass,url;
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            url="jdbc:mysql://localhost:3306/cube_coding_bootcamp1";
//            name="root";
//            pass="24038304937@kalerantes";
//            Connection con = DriverManager.getConnection(url,name,pass);
//            System.out.println("Connection created");
//            Statement stmt=con.createStatement();
//            ResultSet rs= stmt.executeQuery("SELECT * FROM courses");
//
//            while(rs.next()){
//
//                Course s=new Course(rs.getInt("ID"),rs.getString("title"),rs.getString("stream"),rs.getString("type"),rs.getDate("start_date"),rs.getDate("end_date"));
//
////                int Id=rs.getInt("ID");
////                String title=rs.getString("title");
////                String stream=rs.getString("stream");
////                String type=rs.getString("type");
////                Date startDate=rs.getDate("start_date");
////                Date endDate=rs.getDate("end_date");
//
//
//                System.out.println("ID:"+s.getId()+"Title: "+s.getTitle()+"Stream: "+s.getStream()+"Type: "+s.getType()+" Start_Date: "+s.getStart_date()+"End_Date: "+s.getEnd_date()  );
//            }
//            con.close();
//            System.out.println("Connection closed");
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//        }
//    }
//   }




