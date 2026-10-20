// TOPIC-WISE QUICK REFERENCE: Questions 1-6
// Each question is an independent class. Compile this file, then run the
// required class, e.g. java Calculator. JDBC requires MySQL Connector/J.

import javax.swing.*;
import java.awt.*;
import java.io.*;

// =====================================================================
// QUESTION 1: Calculator
// Original file: 01_Calculator.java
// =====================================================================
class Calculator {
 public static void main(String[] args) {
  JFrame f=new JFrame("Calculator"); JTextField a=new JTextField(),b=new JTextField(),out=new JTextField();
  JButton add=new JButton("+"), sub=new JButton("-"), mul=new JButton("*"), div=new JButton("/");
  f.setLayout(new GridLayout(4,2)); f.add(new JLabel("First"));f.add(a);f.add(new JLabel("Second"));f.add(b);
  for(JButton btn:new JButton[]{add,sub,mul,div}) { f.add(btn); btn.addActionListener(e->{try {
   double x=Double.parseDouble(a.getText()),y=Double.parseDouble(b.getText());
   double z=btn==add?x+y:btn==sub?x-y:btn==mul?x*y:x/y;
   out.setText(String.valueOf(z)); JOptionPane.showMessageDialog(f,out.getText());
  } catch(Exception ex){JOptionPane.showMessageDialog(f,"Invalid input");}}); }
  f.setSize(350,220);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 2: RegistrationForm
// Original file: 02_RegistrationForm.java
// =====================================================================
class RegistrationForm {
 public static void main(String[] args){
  JFrame f=new JFrame("Registration");f.setLayout(new GridLayout(6,2));
  JTextField name=new JTextField();JRadioButton male=new JRadioButton("Male"),female=new JRadioButton("Female");
  ButtonGroup bg=new ButtonGroup();bg.add(male);bg.add(female);
  JComboBox<String> course=new JComboBox<>(new String[]{"BCA","BSc","MCA"});
  JCheckBox agree=new JCheckBox("I agree");JButton submit=new JButton("Submit");
  f.add(new JLabel("Name"));f.add(name);f.add(new JLabel("Gender"));
  JPanel genders=new JPanel();genders.add(male);genders.add(female);f.add(genders);
  f.add(new JLabel("Course"));f.add(course);f.add(new JLabel("Consent"));f.add(agree);
  f.add(submit);submit.addActionListener(e->JOptionPane.showMessageDialog(f,
   name.getText()+" / "+(male.isSelected()?"Male":female.isSelected()?"Female":"Not selected")+" / "+course.getSelectedItem()+" / Consent: "+agree.isSelected()));
  f.setSize(400,260);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 3: LayoutsDemo
// Original file: 03_LayoutsDemo.java
// =====================================================================
class LayoutsDemo {
 public static void main(String[] args){
  JFrame f=new JFrame("Layouts");f.setLayout(new GridLayout(1,3));
  JPanel flow=new JPanel(new FlowLayout());flow.add(new JButton("Flow 1"));flow.add(new JButton("Flow 2"));
  JPanel border=new JPanel(new BorderLayout());border.add(new JButton("North"),BorderLayout.NORTH);border.add(new JButton("Center"),BorderLayout.CENTER);
  JPanel grid=new JPanel(new GridLayout(2,2));for(int i=1;i<=4;i++)grid.add(new JButton("Grid "+i));
  f.add(flow);f.add(border);f.add(grid);f.setSize(650,250);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 4: TemperatureConverter
// Original file: 04_TemperatureConverter.java
// =====================================================================
class TemperatureConverter {
 public static void main(String[] args){
  JFrame f=new JFrame("Temperature");f.setLayout(new FlowLayout());
  JTextField input=new JTextField(10);JButton ctof=new JButton("C to F"),ftoc=new JButton("F to C");JLabel result=new JLabel("Result");
  f.add(input);f.add(ctof);f.add(ftoc);f.add(result);
  ctof.addActionListener(e->{try{result.setText("F = "+(Double.parseDouble(input.getText())*9/5+32));}catch(Exception ex){result.setText("Invalid number");}});
  ftoc.addActionListener(e->{try{result.setText("C = "+((Double.parseDouble(input.getText())-32)*5/9));}catch(Exception ex){result.setText("Invalid number");}});
  f.setSize(380,140);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 5: StudentInfo
// Original file: 05_StudentInfo.java
// =====================================================================
class StudentInfo {
 public static void main(String[] args){
  JFrame f=new JFrame("Student Info");f.setLayout(new GridLayout(4,2));
  JTextField name=new JTextField(),roll=new JTextField(),course=new JTextField();JButton show=new JButton("Show");
  f.add(new JLabel("Name"));f.add(name);f.add(new JLabel("Roll"));f.add(roll);f.add(new JLabel("Course"));f.add(course);f.add(show);
  show.addActionListener(e->JOptionPane.showMessageDialog(f,"Name: "+name.getText()+"\nRoll: "+roll.getText()+"\nCourse: "+course.getText()));
  f.setSize(350,200);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 6: TextEditor
// Original file: 06_TextEditor.java
// =====================================================================
class TextEditor {
 public static void main(String[] args){
  JFrame f=new JFrame("Text Editor");JTextArea area=new JTextArea();JMenuBar bar=new JMenuBar();JMenu menu=new JMenu("File");
  JMenuItem open=new JMenuItem("Open"),save=new JMenuItem("Save"),clear=new JMenuItem("Clear");
  menu.add(open);menu.add(save);menu.add(clear);bar.add(menu);f.setJMenuBar(bar);f.add(new JScrollPane(area));
  open.addActionListener(e->{JFileChooser fc=new JFileChooser();if(fc.showOpenDialog(f)==JFileChooser.APPROVE_OPTION)try{area.read(new FileReader(fc.getSelectedFile()),null);}catch(IOException ex){JOptionPane.showMessageDialog(f,ex.getMessage());}});
  save.addActionListener(e->{JFileChooser fc=new JFileChooser();if(fc.showSaveDialog(f)==JFileChooser.APPROVE_OPTION)try{area.write(new FileWriter(fc.getSelectedFile()));}catch(IOException ex){JOptionPane.showMessageDialog(f,ex.getMessage());}});
  clear.addActionListener(e->area.setText(""));f.setSize(500,350);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}
