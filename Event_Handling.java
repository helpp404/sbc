// TOPIC-WISE QUICK REFERENCE: Questions 7-12
// Each question is an independent class. Compile this file, then run the
// required class, e.g. java Calculator. JDBC requires MySQL Connector/J.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// =====================================================================
// QUESTION 7: LoginWindow
// Original file: 07_LoginWindow.java
// =====================================================================
class LoginWindow {
 public static void main(String[] args){
  JFrame f=new JFrame("Login");f.setLayout(new GridLayout(3,2));JTextField user=new JTextField();JPasswordField pass=new JPasswordField();JButton login=new JButton("Login");
  f.add(new JLabel("Username"));f.add(user);f.add(new JLabel("Password"));f.add(pass);f.add(login);
  login.addActionListener(e->JOptionPane.showMessageDialog(f,user.getText().equals("admin")&&new String(pass.getPassword()).equals("1234")?"Login successful":"Invalid credentials"));
  f.setSize(300,160);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 8: MouseListenerDemo
// Original file: 08_MouseListenerDemo.java
// =====================================================================
class MouseListenerDemo implements MouseListener {
 JLabel label=new JLabel("Use mouse inside window",SwingConstants.CENTER);
 MouseListenerDemo(){JFrame f=new JFrame("Mouse Events");f.add(label);f.addMouseListener(this);label.addMouseListener(this);f.setSize(350,200);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);}
 public void mouseClicked(MouseEvent e){label.setText("Clicked");}
 public void mousePressed(MouseEvent e){label.setText("Pressed");}
 public void mouseReleased(MouseEvent e){label.setText("Released");}
 public void mouseEntered(MouseEvent e){label.setText("Entered");}
 public void mouseExited(MouseEvent e){label.setText("Exited");}
 public static void main(String[] args){new MouseListenerDemo();}
}

// =====================================================================
// QUESTION 9: KeyListenerDemo
// Original file: 09_KeyListenerDemo.java
// =====================================================================
class KeyListenerDemo implements KeyListener {
 JLabel result=new JLabel("Type in box");
 KeyListenerDemo(){JFrame f=new JFrame("Keyboard");JTextField input=new JTextField();f.add(input,"North");f.add(result,"Center");input.addKeyListener(this);f.setSize(350,140);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);}
 public void keyTyped(KeyEvent e){result.setText("Typed: "+e.getKeyChar());}
 public void keyPressed(KeyEvent e){result.setText("Pressed: "+KeyEvent.getKeyText(e.getKeyCode()));}
 public void keyReleased(KeyEvent e){result.setText("Released: "+KeyEvent.getKeyText(e.getKeyCode()));}
 public static void main(String[] args){new KeyListenerDemo();}
}

// =====================================================================
// QUESTION 10: MouseAdapterDemo
// Original file: 10_MouseAdapterDemo.java
// =====================================================================
class MouseAdapterDemo {
 public static void main(String[] args){
  JFrame f=new JFrame("Mouse Adapter");JLabel label=new JLabel("Click here",SwingConstants.CENTER);
  label.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){label.setText("Clicked at "+e.getX()+", "+e.getY());}});
  f.add(label);f.setSize(350,180);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 11: InnerClassArithmetic
// Original file: 11_InnerClassArithmetic.java
// =====================================================================
class InnerClassArithmetic {
 static JTextField a=new JTextField(),b=new JTextField();static JLabel result=new JLabel("Result");
 static class AddHandler implements ActionListener {public void actionPerformed(ActionEvent e){try{result.setText("Sum = "+(Double.parseDouble(a.getText())+Double.parseDouble(b.getText())));}catch(Exception ex){result.setText("Invalid input");}}}
 public static void main(String[] args){
  JFrame f=new JFrame("Inner Classes");f.setLayout(new GridLayout(3,2));JButton add=new JButton("Add"),multiply=new JButton("Multiply");
  f.add(a);f.add(b);f.add(add);f.add(multiply);f.add(result);
  add.addActionListener(new AddHandler()); // Named inner class
  multiply.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){try{result.setText("Product = "+(Double.parseDouble(a.getText())*Double.parseDouble(b.getText())));}catch(Exception ex){result.setText("Invalid input");}}}); // Anonymous inner class
  f.setSize(350,170);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}

// =====================================================================
// QUESTION 12: ColorChooserDemo
// Original file: 12_ColorChooserDemo.java
// =====================================================================
class ColorChooserDemo {
 public static void main(String[] args){
  JFrame f=new JFrame("Color Chooser");JPanel panel=new JPanel();JButton choose=new JButton("Choose Color");panel.add(choose);f.add(panel);
  choose.addActionListener(e->{Color c=JColorChooser.showDialog(f,"Select Color",panel.getBackground());if(c!=null)panel.setBackground(c);});
  f.setSize(350,220);f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);f.setVisible(true);
 }
}
