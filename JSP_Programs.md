# JSP + Database Integration — Questions 26–30

Use **Ctrl+F** to locate `QUESTION 26`, etc. Copy only the code inside the corresponding block to a `.jsp` file (or `.java` for the supporting servlet).

JSP files cannot be combined into one executable JSP because each has its own page structure. These are separate copy-ready blocks.

## QUESTION 26 — `26_Welcome.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%! String greeting="Welcome to JSP"; %>
<html><body>
<h2><%= greeting %></h2>
<% String name="Student"; %>
<p>Hello, <%= name %>!</p>
<p>Current time: <%= new java.util.Date() %></p>
</body></html>
```

## QUESTION 27 — `27_StudentsList.jsp`

```jsp
<%@ page import="java.sql.*" contentType="text/html;charset=UTF-8" %>
<html><body><h2>Students</h2><table border="1"><tr><th>ID</th><th>Name</th><th>Course</th></tr>
<%
try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD");
 Statement st=con.createStatement();ResultSet rs=st.executeQuery("SELECT id,name,course FROM students_info")){
 while(rs.next()){
%><tr><td><%=rs.getInt("id")%></td><td><%=rs.getString("name")%></td><td><%=rs.getString("course")%></td></tr><%
 }
}catch(Exception e){out.println("Database error: "+e.getClass().getSimpleName());}
%>
</table></body></html>
```

## QUESTION 28 — `28_Login.jsp`

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html><body><h2>Login (JSP)</h2>
<form method="post" action="login-check">
Username: <input name="user" required><br>
Password: <input type="password" name="password" required><br>
<button type="submit">Login</button>
</form></body></html>
```

## QUESTION 28 — `28_LoginCheckServlet.java`

```java
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/login-check")
public class LoginCheckServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException {
  response.setContentType("text/html;charset=UTF-8");
  String user=request.getParameter("user"),password=request.getParameter("password");
  response.getWriter().println("admin".equals(user)&&"1234".equals(password)?"<h2>Welcome!</h2>":"<h2>Invalid login</h2>");
 }
}
```

## QUESTION 29 — `29_InsertStudentJSP.jsp`

```jsp
<%@ page import="java.sql.*" contentType="text/html;charset=UTF-8" %>
<html><body><h2>Insert Student</h2>
<form method="post">Name: <input name="name" required> Course: <input name="course" required><button>Add</button></form>
<%
String name=request.getParameter("name"),course=request.getParameter("course");
if("POST".equalsIgnoreCase(request.getMethod()) && name!=null && course!=null){
 try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD");
     PreparedStatement ps=con.prepareStatement("INSERT INTO students_info(name,course) VALUES (?,?)")){
  ps.setString(1,name);ps.setString(2,course);ps.executeUpdate();out.println("<p>Student inserted.</p>");
 }catch(Exception e){out.println("<p>Insert failed.</p>");}
}
try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD");
 Statement st=con.createStatement();ResultSet rs=st.executeQuery("SELECT id,name,course FROM students_info")){
 out.println("<h3>Current records</h3><table border='1'><tr><th>ID</th><th>Name</th><th>Course</th></tr>");
 while(rs.next())out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2).replace("&","&amp;").replace("<","&lt;")+"</td><td>"+rs.getString(3).replace("&","&amp;").replace("<","&lt;")+"</td></tr>");
 out.println("</table>");
}catch(Exception e){out.println("<p>Cannot show records.</p>");}
%></body></html>
```

## QUESTION 30 — `30_ManageStudentsJSP.jsp`

```jsp
<%@ page import="java.sql.*" contentType="text/html;charset=UTF-8" %>
<html><body><h2>Student Management</h2>
<form method="post">ID: <input type="number" name="id" required>
Name: <input name="name"> Course: <input name="course">
<button name="action" value="search">Search</button>
<button name="action" value="update">Update</button>
<button name="action" value="delete">Delete</button></form>
<%
String action=request.getParameter("action"),idText=request.getParameter("id");
if(action!=null && idText!=null){
 try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","YOUR_PASSWORD")){
  int id=Integer.parseInt(idText);
  if("search".equals(action)){
   try(PreparedStatement ps=con.prepareStatement("SELECT id,name,course FROM students_info WHERE id=?")){
    ps.setInt(1,id);try(ResultSet rs=ps.executeQuery()){
     if(rs.next())out.println("<p>ID "+rs.getInt(1)+", Name: "+rs.getString(2).replace("&","&amp;").replace("<","&lt;")+", Course: "+rs.getString(3).replace("&","&amp;").replace("<","&lt;")+"</p>");
     else out.println("<p>No record</p>");
    }
   }
  }else if("update".equals(action)){
   try(PreparedStatement ps=con.prepareStatement("UPDATE students_info SET name=?,course=? WHERE id=?")){
    ps.setString(1,request.getParameter("name"));ps.setString(2,request.getParameter("course"));ps.setInt(3,id);
    out.println("<p>Updated "+ps.executeUpdate()+" row(s)</p>");
   }
  }else if("delete".equals(action)){
   try(PreparedStatement ps=con.prepareStatement("DELETE FROM students_info WHERE id=?")){
    ps.setInt(1,id);out.println("<p>Deleted "+ps.executeUpdate()+" row(s)</p>");
   }
  }
 }catch(Exception e){out.println("<p>Operation failed. Check ID and database.</p>");}
}
%></body></html>
```
