// SignUp.java Author: Vural Bilgin ID: 22095034
// Sıgn Up window , if user has not any account, can create account here
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignUp extends JFrame {
    //Components
    private JLabel nameL, surnameL, emailL, passwordL,jobL;
    private JTextField nameTF, surnameTF, emailTF, passwordTF,jobTF;
    private JButton cancelButton, okButton;
    private ArrayList<Users> userAList ;
    public SignUp() {
        //Settings up
        Container c=getContentPane();
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Creating Components
        nameL = new JLabel("Name:");
        surnameL = new JLabel("Surname:");
        emailL = new JLabel("Email:");
        passwordL = new JLabel("Password:");
        jobL=new JLabel("Job");

        nameTF = new JTextField();
        surnameTF = new JTextField();
        emailTF = new JTextField();
        passwordTF = new JTextField();
        jobTF=new JTextField();

        cancelButton=new JButton("Cancel");
        okButton=new JButton("Ok");
        c.setLayout(new GridLayout(6, 2));
        okButton.setBackground(Color.GREEN);
        cancelButton.setBackground(Color.RED);

        //Adding components
        c.add(nameL);
        c.add(nameTF);
        c.add(surnameL);
        c.add(surnameTF);
        c.add(emailL);
        
        c.add(emailTF);
        c.add(passwordL);
        c.add(passwordTF);
        c.add(jobL);
        c.add(jobTF);
        c.add(okButton);
        c.add(cancelButton);
        setVisible(true);

        userAList= new ArrayList<Users>(); // List of users
        ButtonListener buttonListener=new ButtonListener();
        okButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
    }
    public class ButtonListener implements ActionListener{

       
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==okButton){
                //Get datas from textField
                String name =nameTF.getText();
                String surname=surnameTF.getText();
                String email=emailTF.getText();
                String password=passwordTF.getText();
                String job=jobTF.getText();
                try {
                Users user=new Users(name, surname, password, email,job);//create user in DB
                user.insertIntoDatabase(user);
                
                //userAList=UserA.getAllUsersFromDatabase();
                //printUserADetails();
                MainFrame mf=new MainFrame(user);// Goes to main page
                mf.setLocation(300,200);
                dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());//warning message
                }
            }
            else if(e.getSource()==cancelButton){
                dispose();//close only this window
            }
        }
        
    }
    
}
