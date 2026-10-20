// TOPIC-WISE QUICK REFERENCE: Questions 21-25
// IMPORTANT: For deployment, copy ONE servlet class into its own ClassName.java
// file and change "class ClassName" to "public class ClassName".
// Tomcat 10.1+ (jakarta.servlet) is required.

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.*;

// =====================================================================
// QUESTION 21: HelloServlet
// Original file: 21_HelloServlet.java
// =====================================================================
@WebServlet("/hello")
class HelloServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<h1>Hello World</h1>");
 }
}

// =====================================================================
// QUESTION 22: StudentFormServlet
// Original file: 22_StudentFormServlet.java
// =====================================================================
@WebServlet("/student-form")
class StudentFormServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<form method='post'>Name: <input name='name'> Course: <input name='course'><button>Submit</button></form>");
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<h2>Student received</h2><p>Name: "+escape(req.getParameter("name"))+"</p><p>Course: "+escape(req.getParameter("course"))+"</p>");
 }
 private String escape(String s){return s==null?"":s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");}
}

// =====================================================================
// QUESTION 23: GetPostServlet
// Original file: 23_GetPostServlet.java
// =====================================================================
@WebServlet("/get-post")
class GetPostServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<h3>doGet called</h3><form method='post'><button>Send POST</button></form>");
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<h3>doPost called</h3>");
 }
}

// =====================================================================
// QUESTION 24: LoginServlet
// Original file: 24_LoginServlet.java
// =====================================================================
@WebServlet("/login")
class LoginServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<form method='post'>User <input name='user'> Password <input type='password' name='password'><button>Login</button></form>");
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  String u=req.getParameter("user"),p=req.getParameter("password");resp.setContentType("text/html;charset=UTF-8");
  resp.getWriter().println("admin".equals(u)&&"1234".equals(p)?"<h2>Login Successful</h2>":"<h2>Invalid Credentials</h2>");
 }
}

// =====================================================================
// QUESTION 25: SumServlet
// Original file: 25_SumServlet.java
// =====================================================================
@WebServlet("/sum")
class SumServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");resp.getWriter().println("<form method='post'><input type='number' name='a' required><input type='number' name='b' required><button>Add</button></form>");
 }
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException {
  resp.setContentType("text/html;charset=UTF-8");try{
   double a=Double.parseDouble(req.getParameter("a")),b=Double.parseDouble(req.getParameter("b"));resp.getWriter().println("Sum = "+(a+b));
  }catch(Exception ex){resp.getWriter().println("Invalid numbers");}
 }
}
