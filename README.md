# Advanced Java — 30 Practical Programs (Topic-wise)

Designed for **fast navigation**: open one topic file, use `Ctrl+F` to find `QUESTION 1` through `QUESTION 30`, then copy the relevant code.

| Main file | Questions | How to use |
|---|---|---|
| `Swing_GUI.java` | 1–6 | Compile once; run the class for the desired question |
| `Event_Handling.java` | 7–12 | Compile once; run the selected class |
| `JDBC.java` | 13–20 | Compile with MySQL Connector/J; run selected class |
| `Servlets.java` | 21–25 | Copy a selected class into its own named Java file and make it public |
| `JSP_Programs.md` | 26–30 | Copy the respective JSP code block into its own `.jsp` file; Q28 also includes servlet code |

## Swing and Events

In a terminal with Java installed:

```bash
javac Swing_GUI.java
java Calculator                 # Question 1
java TemperatureConverter      # Question 4

javac Event_Handling.java
java LoginWindow               # Question 7
```

Each group has **one class per question**, not multiple `main()` methods in one class. This lets you run just the needed exercise without deleting the others.

## JDBC

Run `setup.sql` in MySQL to create the sample database. Change `YOUR_PASSWORD` in `JDBC.java`, and put MySQL Connector/J on the classpath:

```bash
# Linux/macOS
javac -cp '.:mysql-connector-j.jar' JDBC.java
java -cp '.:mysql-connector-j.jar' DisplayStudents

# Windows cmd
javac -cp ".;mysql-connector-j.jar" JDBC.java
java -cp ".;mysql-connector-j.jar" DisplayStudents
```

## Servlets

`Servlets.java` is a **copy-ready reference** containing all five classes (Q21–25). For actual servlet deployment, copy the required servlet into its own `.java` file named after the class, change `class HelloServlet` to `public class HelloServlet`, and deploy with **Tomcat 10.1+ / Jakarta Servlet API**. An IDE Java web project is easiest. Don't deploy the grouped source directly.

## JSP

`JSP_Programs.md` contains code blocks rather than a single `.jsp`, because five complete JSP pages cannot function inside one JSP file. Copy one code block to the named JSP page. Question 28 requires its separate `LoginCheckServlet.java`. For JDBC JSP examples, configure MySQL and Connector/J on the webapp classpath and replace `YOUR_PASSWORD`.

**Note:** Code is intended for local coursework practice. Availability and behavior depend on your setup; Swing/Event grouped sources can be compiled with a JDK. JSP database examples are simplified for learning, not production-ready web apps.
