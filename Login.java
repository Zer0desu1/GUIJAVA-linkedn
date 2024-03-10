// Login.java Author: Vural Bilgin ID: 22095034
// Login Page. If user has an account, can sign in the system
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JFrame {
    //components
    private JTextField emailTF,passwordTF;
    private JButton loginButton,signupButton,exitButton;
    private JLabel emailLabel,passwordLabel;
    private JPanel infoFrame,buttonFrame,mainFrame;
    List<Users> users = Users.getAllUsersFromDatabase(); // retrive all the user from database
    public Login(){
        setTitle("LOGIN");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//close this windown only
        
        //create panel and frame
        Container container=getContentPane();
        infoFrame=new JPanel();
        buttonFrame=new JPanel();
        infoFrame.setLayout(new GridLayout(2,2));
        buttonFrame.setLayout(new GridLayout(1,3) );


        //create components
        emailTF=new JTextField("E-mail");
        passwordTF=new JTextField("Password");
        loginButton=new JButton("Log In");
        signupButton=new JButton("Sign Up");
        emailLabel=new JLabel("E-mail");
        passwordLabel=new JLabel("Password");
        exitButton=new JButton("Exit");

        //Adding some visual effect
        loginButton.setBackground(Color.CYAN);
        signupButton.setBackground(Color.YELLOW);
        exitButton.setBackground(Color.RED);


        //Adding the components to panels
        infoFrame.add(emailLabel);infoFrame.add(emailTF);
        infoFrame.add(passwordLabel); infoFrame.add(passwordTF);
        buttonFrame.add(loginButton); buttonFrame.add(signupButton);
        buttonFrame.add(exitButton);
        mainFrame=new JPanel();
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.add(infoFrame);
        mainFrame.add(buttonFrame);
        
        container.setLayout(new BorderLayout());
        container.add(mainFrame);
        EventHandler handler=new EventHandler(); // Create listener  object
        exitButton.addActionListener(handler);
        loginButton.addActionListener(handler);
        signupButton.addActionListener(handler);
        setVisible(true);
    }

    public class EventHandler implements ActionListener{// Inner Listener class

        
        public void actionPerformed(ActionEvent e) {
            if(exitButton==e.getSource()){
                dispose();              //close only this window
            }
            else if(e.getSource()==loginButton){
                //retrive text from text field
                String password=passwordTF.getText();
                String email=emailTF.getText();
                
                System.out.println(password+email);
                boolean found = false;

                for (Users user : users) {// loop for all users
                    String emailDB = user.getEmail();
                    String passwordDB = user.getPassword();
            
                    if (email.equals(emailDB) && password.equals(passwordDB)) {// check if email and password correct
                        // Successful login
                        JOptionPane.showMessageDialog(null, "LOGGED IN SUCCESSFULLY!!");
                        MainFrame mf = new MainFrame(user);
                        mf.setLocation(300, 200);
                        dispose(); // Close only this window
                        found = true;
                        break; // No need to check further
                    }
                }
            
                if (!found) {// Unsuccesfull login
                    JOptionPane.showMessageDialog(mainFrame, "INVALID DATA");
                }
                
                
            }
            else if(e.getSource()==signupButton){// Clicked sign up button
                SignUp signUp=new SignUp();// directs to Login Window
                signUp.setLocation(getLocation());
            }
        }
        
    }

}