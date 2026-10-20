// TOPIC-WISE QUICK REFERENCE: Questions 13-20
// Each question is an independent class. Compile this file, then run the
// required class, e.g. java Calculator. JDBC requires MySQL Connector/J.

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// =====================================================================
// QUESTION 13: DisplayStudents
// Original file: 13_DisplayStudents.java
// =====================================================================
class DisplayStudents {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   try(Statement st=con.createStatement();ResultSet rs=st.executeQuery("SELECT * FROM students_info")){
    while(rs.next()) System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("course"));
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 14: InsertStudent
// Original file: 14_InsertStudent.java
// =====================================================================
class InsertStudent {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   try(PreparedStatement ps=con.prepareStatement("INSERT INTO students_info(name,course) VALUES (?,?)")){
    ps.setString(1,"Asha");ps.setString(2,"BCA");System.out.println("Inserted: "+ps.executeUpdate());
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 15: UpdateStudent
// Original file: 15_UpdateStudent.java
// =====================================================================
class UpdateStudent {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   try(PreparedStatement ps=con.prepareStatement("UPDATE students_info SET course=? WHERE id=?")){
    ps.setString(1,"MCA");ps.setInt(2,1);System.out.println("Updated: "+ps.executeUpdate());
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 16: DeleteStudent
// Original file: 16_DeleteStudent.java
// =====================================================================
class DeleteStudent {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   try(PreparedStatement ps=con.prepareStatement("DELETE FROM students_info WHERE id=?")){
    ps.setInt(1,1);System.out.println("Deleted: "+ps.executeUpdate());
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 17: SearchStudent
// Original file: 17_SearchStudent.java
// =====================================================================
class SearchStudent {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   try(PreparedStatement ps=con.prepareStatement("SELECT * FROM students_info WHERE id=? OR name LIKE ?")){
    ps.setInt(1,2);ps.setString(2,"%Ash%");try(ResultSet rs=ps.executeQuery()){
     while(rs.next())System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("course"));
    }
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 18: JoinStudents
// Original file: 18_JoinStudents.java
// =====================================================================
class JoinStudents {
 static final String URL="jdbc:mysql://localhost:3306/college";
 static final String USER="root", PASS="YOUR_PASSWORD";
 public static void main(String[] args) {
  try(Connection con=DriverManager.getConnection(URL,USER,PASS)) {
   String sql="SELECT s.name, d.department_name FROM students_info s JOIN departments d ON s.department_id=d.id";
   try(Statement st=con.createStatement();ResultSet rs=st.executeQuery(sql)){
    while(rs.next())System.out.println(rs.getString("name")+" - "+rs.getString("department_name"));
   }
  } catch(SQLException ex){ex.printStackTrace();}
 }
}

// =====================================================================
// QUESTION 19: StudentsJTable
// Original file: 19_StudentsJTable.java
// =====================================================================
class StudentsJTable {
 public static void main(String[] args){
  DefaultTableModel model=new DefaultTableModel(new String[]{"ID","Name","Course"},0);
  try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD");
      Statement st=con.createStatement();ResultSet rs=st.executeQuery("SELECT id,name,course FROM students_info")){
   while(rs.next())model.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3)});
  }catch(SQLException ex){JOptionPane.showMessageDialog(null,ex.getMessage());}
  JFrame f=new JFrame("Student Table");f.add(new JScrollPane(new JTable(model)));f.setSize(450,250);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 20: TransactionDemo
// Original file: 20_TransactionDemo.java
// =====================================================================
class TransactionDemo {
 public static void main(String[] args){
  try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD")){
   con.setAutoCommit(false);
   try(PreparedStatement ps=con.prepareStatement("INSERT INTO students_info(name,course) VALUES (?,?)")){
    ps.setString(1,"Ravi");ps.setString(2,"BCA");ps.executeUpdate();
    ps.setString(1,"Neha");ps.setString(2,"MCA");ps.executeUpdate();
    con.commit();System.out.println("Transaction committed");
   }catch(SQLException e){con.rollback();System.out.println("Transaction rolled back: "+e.getMessage());}
  }catch(SQLException e){e.printStackTrace();}
 }
}
