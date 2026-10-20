# Advanced Java — JDBC + Servlet Viva Code Explanations (Q13–Q25)

**How to use:** Open on GitHub, press **Ctrl+F**, search `Q13`–`Q25`. Read **How the code works**, then practise saying **Tell the teacher** aloud.

## JDBC + MYSQL (Q13–Q20)

**Basic idea:** JDBC means **Java Database Connectivity**. Java sends SQL statements to MySQL through the **MySQL Connector/J driver** and receives the results.

**Same starting steps in almost every JDBC program:**
1. `import java.sql.*;` brings JDBC classes such as `Connection`, `Statement`, `PreparedStatement`, and `ResultSet`.
2. `DriverManager.getConnection(URL, USER, PASS)` opens a connection to the `college` database.
3. Java runs a SQL query. A `SELECT` usually uses `executeQuery()`; `INSERT`, `UPDATE`, or `DELETE` uses `executeUpdate()`.
4. `try (...)` closes resources automatically; `catch(SQLException e)` handles database errors.

**Important:** Run `setup.sql` first, start MySQL, set your own password **locally**, and include the MySQL Connector/J JAR when running Java. **Do not publish your real MySQL password on GitHub.**

### Q13 — Display Student Records (`DisplayStudents`)

**How the code works**
1. `getConnection()` connects Java to the `college` database.
2. `con.createStatement()` makes a `Statement` for SQL commands.
3. `executeQuery("SELECT * FROM students_info")` fetches student records into a `ResultSet`.
4. `while(rs.next())` moves through the records **one row at a time**. `getInt("id")` and `getString("name")` / `getString("course")` read each column and print it.

**Tell the teacher:** “I establish a JDBC connection, execute a SELECT query using Statement, store the rows in a ResultSet, and use a while loop to display each student's ID, name, and course.”

**Remember:** `ResultSet` = table of returned rows; `rs.next()` = go to the next row; `executeQuery()` = retrieve data.

### Q14 — Insert Student (`InsertStudent`)

**How the code works**
1. Java connects to MySQL and creates a `PreparedStatement` with `INSERT INTO students_info(name,course) VALUES (?,?)`.
2. The `?` symbols are placeholders for values. `ps.setString(1,"Asha")` fills the first placeholder; `ps.setString(2,"BCA")` fills the second.
3. `ps.executeUpdate()` runs the INSERT. The returned number shows how many rows were inserted.
4. The database generates the `id` automatically because the column uses `AUTO_INCREMENT` in `setup.sql`.

**Tell the teacher:** “I use PreparedStatement to insert a new student. The question-mark placeholders are filled with setString, and executeUpdate saves the record. PreparedStatement also helps avoid SQL injection when handling user input.”

**Remember:** `executeUpdate()` returns the **affected row count**. Running this program again inserts another record.

### Q15 — Update Student (`UpdateStudent`)

**How the code works**
1. The SQL is `UPDATE students_info SET course=? WHERE id=?`.
2. `ps.setString(1,"MCA")` provides the new course. `ps.setInt(2,1)` selects student ID 1.
3. `executeUpdate()` updates the matching row and prints how many rows were affected.

**Tell the teacher:** “This program changes the course of an existing student. I use a PreparedStatement with UPDATE and WHERE id, set the new course and student ID, and run executeUpdate to modify only that student.”

**Remember:** `WHERE id=?` matters! Without the WHERE condition, the query could update **every student**.

### Q16 — Delete Student (`DeleteStudent`)

**How the code works**
1. The SQL is `DELETE FROM students_info WHERE id=?`.
2. `ps.setInt(1,1)` chooses the row with student ID 1.
3. `ps.executeUpdate()` executes the deletion and prints the affected row count.

**Tell the teacher:** “I connect to the database and use PreparedStatement to delete a specific student by ID. The WHERE condition identifies the record, and executeUpdate performs the deletion.”

**Remember:** Without `WHERE`, a `DELETE` could remove **all rows**. If no matching ID exists, the affected row count is **0**.

### Q17 — Search Student (`SearchStudent`)

**How the code works**
1. The SELECT query includes `WHERE id=? OR name LIKE ?`, so a student can match by **ID or partial name**.
2. `ps.setInt(1,2)` supplies ID 2, and `ps.setString(2,"%Ash%")` finds names containing `Ash`.
3. `executeQuery()` returns matching records. The `while(rs.next())` loop displays them.

**Tell the teacher:** “I use PreparedStatement to search students by ID or by a part of their name. SQL LIKE performs the partial-name search, and the ResultSet shows matching records.”

**Remember:** `%` in SQL LIKE means **any number of characters**. The example uses fixed search values; for a user-input version, read these values from the user first.

### Q18 — SQL JOIN (`JoinStudents`)

**How the code works**
1. The code connects to MySQL and runs:
   `SELECT s.name, d.department_name FROM students_info s JOIN departments d ON s.department_id=d.id`.
2. `s` is a short alias for the `students_info` table; `d` is an alias for `departments`.
3. `JOIN ... ON s.department_id=d.id` connects each student to their department using the matching IDs.
4. The `ResultSet` loop prints the **student name** and **department name** together.

**Tell the teacher:** “A JOIN combines related data from two tables. Here, students_info stores each student's department ID, while departments stores the department name. I join the tables using matching IDs and print each student's name with their department.”

**Remember:** This is an **INNER JOIN**, so a student with no matching department will not appear. Q18 needs **both** tables and matching IDs.

### Q19 — Display MySQL Data in JTable (`StudentsJTable`)

**How the code works**
1. `DefaultTableModel` creates three column headings: **ID, Name, Course**.
2. JDBC connects to MySQL and fetches student records using SELECT.
3. For each row, `model.addRow(...)` adds database values to the table model.
4. `new JTable(model)` displays the rows in Swing. `JScrollPane` adds scrolling when needed.

**Tell the teacher:** “I retrieve student records using JDBC and put the values into a DefaultTableModel. JTable displays the model as rows and columns inside a Swing window.”

**Remember:** `ResultSet` holds database rows; `DefaultTableModel` holds GUI table rows; `JTable` **displays** them.

### Q20 — Transactions (`TransactionDemo`)

**How the code works**
1. `con.setAutoCommit(false)` stops Java from automatically saving each database operation immediately.
2. One `PreparedStatement` inserts **Ravi** and then **Neha**, using different parameter values.
3. If both inserts succeed, `con.commit()` makes the changes permanent.
4. If an insert throws `SQLException`, `con.rollback()` undoes the uncommitted changes from the transaction.

**Tell the teacher:** “A transaction treats multiple SQL operations as one unit. I turn off auto-commit, insert two students, and commit only after both succeed. If a database error happens, rollback prevents a partial update.”

**Remember:** `commit()` = save changes; `rollback()` = undo uncommitted changes; `setAutoCommit(false)` = control saving manually.

---

## SERVLETS (Q21–Q25)

**Basic idea:** A **Servlet** is a Java class that runs on a **web server**, such as Apache Tomcat. A browser sends an HTTP request; Tomcat calls a servlet method; the servlet sends an HTTP response back to the browser.

**Common lines to understand:**
- `extends HttpServlet` → the class is an HTTP servlet.
- `@WebServlet("/hello")` → connects a URL path to this servlet.
- `doGet(request,response)` → handles GET requests, often when opening a URL.
- `doPost(request,response)` → handles POST requests, often when submitting a form.
- `request.getParameter("name")` → reads a form field named `name`.
- `response.getWriter().println(...)` → writes text/HTML to the browser.
- `response.setContentType("text/html;charset=UTF-8")` → tells the browser the response is HTML using UTF-8.

**To run your repo's servlet code:** Copy the required class and imports from `Servlets.java` into its own file (for example `HelloServlet.java`), change its declaration to `public class HelloServlet`, compile/deploy with **Tomcat 10.1+** (`jakarta.servlet`), and open the mapped URL. The topic-wise combined file is meant for reading, not direct servlet deployment.

### Q21 — Hello World Servlet (`HelloServlet`)

**How the code works**
1. `@WebServlet("/hello")` maps the servlet to `/hello`.
2. `HelloServlet extends HttpServlet` lets Tomcat execute it as a servlet.
3. When you open the URL in a browser, Tomcat calls `doGet()`.
4. `setContentType()` sets the output type and `getWriter().println("<h1>Hello World</h1>")` sends the HTML heading to the browser.

**Tell the teacher:** “This is a basic servlet. The WebServlet annotation maps a URL to my class. When the browser requests that URL, Tomcat calls doGet, and the response writer sends Hello World as HTML.”

**Remember:** `doGet()` runs on a normal browser GET request. Example URL: `http://localhost:8080/AdvancedJava/hello` (if the application name is `AdvancedJava`).

### Q22 — Student Form Servlet (`StudentFormServlet`)

**How the code works**
1. `@WebServlet("/student-form")` defines the servlet's URL.
2. `doGet()` displays an HTML form with **Name** and **Course** inputs. `method='post'` tells the browser to submit it using POST.
3. When Submit is clicked, Tomcat calls `doPost()`.
4. `req.getParameter("name")` and `req.getParameter("course")` read the submitted values. `resp.getWriter()` displays them on a new page.
5. The helper `escape()` converts characters such as `<` into safe HTML text before showing user input.

**Tell the teacher:** “First doGet shows a student form. When the user enters name and course and submits, the browser sends a POST request. doPost receives the form values using getParameter and displays the entered student details.”

**Remember:** HTML `name='course'` must match `getParameter("course")`. This program **displays** submitted details; it does not save them into MySQL.

### Q23 — GET vs POST (`GetPostServlet`)

**How the code works**
1. `@WebServlet("/get-post")` sets the URL.
2. Opening the URL makes a **GET** request, so `doGet()` shows “doGet called” and a form.
3. The form's `method='post'` sends a **POST** request when its button is clicked.
4. The servlet's `doPost()` displays “doPost called.”

**Tell the teacher:** “This servlet demonstrates two HTTP methods. Opening the URL sends GET and executes doGet. Submitting the form sends POST and executes doPost, so the displayed message changes.”

**Remember:** GET is commonly used to **request/read a page**; POST is commonly used to **submit form data**. POST is not automatically secure; use HTTPS for sensitive data.

### Q24 — Login Servlet (`LoginServlet`)

**How the code works**
1. `doGet()` displays a login form with username and password fields.
2. The form uses `method='post'`, so clicking Login invokes `doPost()`.
3. `getParameter("user")` and `getParameter("password")` retrieve the input.
4. The code compares the input with **admin / 1234** using `.equals()` and sends either “Login Successful” or “Invalid Credentials”.

**Tell the teacher:** “doGet shows the login form, and doPost processes the submitted credentials. I use request.getParameter to read the username and password, compare them with hard-coded values, and return a success or failure message.”

**Remember:** `"admin".equals(u)` compares **String values** safely, even if `u` is `null`. This is only a classroom example, not secure real-world authentication.

### Q25 — Sum of Two Numbers (`SumServlet`)

**How the code works**
1. `doGet()` displays a form with two `<input type='number'>` fields named `a` and `b`.
2. When Add is clicked, the form sends those values to `doPost()`.
3. `req.getParameter("a")` and `req.getParameter("b")` return text. `Double.parseDouble()` turns each into a number.
4. The servlet calculates `a+b` and sends `Sum = ...` to the browser. A `catch` block displays “Invalid numbers” if parsing fails.

**Tell the teacher:** “I display an HTML form using doGet. On submission, doPost reads the two form values, converts them into double numbers, calculates the sum, and prints the result in the browser.”

**Remember:** Form parameters are received as **Strings**, so numerical calculations need conversion such as `Double.parseDouble()`.

---

## 30-second viva revision

| If teacher asks… | Say this |
|---|---|
| What is JDBC? | A Java API used to connect to a database and execute SQL. |
| `Statement` vs `PreparedStatement`? | PreparedStatement has parameter placeholders and is safer for user-supplied values. |
| `executeQuery()` vs `executeUpdate()`? | executeQuery returns a ResultSet; executeUpdate returns an affected-row count. |
| What is `ResultSet`? | An object that holds rows returned by a SELECT query. |
| What does JOIN do? | Combines related records from two tables using a matching column. |
| `commit()` vs `rollback()`? | Commit saves the transaction; rollback undoes uncommitted changes. |
| What is a servlet? | A Java class on a server that handles HTTP requests and responses. |
| What does `@WebServlet` do? | Maps a URL path to a servlet. |
| `doGet()` vs `doPost()`? | GET typically retrieves data/pages; POST typically submits form data. |
| What is `getParameter()`? | Reads an input value submitted through an HTTP request. |

**One-line memory trick:** JDBC = connect, SQL, results → Servlet = request, method, response.
