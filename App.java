
// App.java Author: Vural Bilgin ID: 22095034
// First Page of Linkedn (Directs user to login screen or sign up screen)

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;   
public class App extends JFrame {
    private JButton yesBtn, noBtn;
    private JLabel logo, questionLabel;
    private JPanel textPanel, buttonPanel;
    

    public App(){ // Constructor of first window
        setLayout(new GridLayout(2,1));
        setSize(600,400);
        
        //Button that directs user to login screen or sign up screen
        yesBtn=new JButton("YES");
        noBtn=new JButton("NO");

        // Style buttons
        yesBtn.setBackground(Color.GREEN);
        noBtn.setBackground(Color.RED);

        //create components
        logo = new JLabel("LINKEDIN", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 36));

        questionLabel = new JLabel("Welcome, Do you have an account?", SwingConstants.CENTER); 
        textPanel=new JPanel(new GridLayout(2,1));
        buttonPanel=new JPanel(new GridLayout(1,2));

        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(yesBtn);buttonPanel.add(noBtn);
        textPanel.add(logo); textPanel.add(questionLabel);
        setVisible(true);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// close only this window when clicked x 

        yesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Login login =new Login(); // pop up login window
                login.setLocation(800  , 300);
                dispose();// close window
            }
        });
        noBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                SignUp sign=new SignUp(); //pop up sign up window
                sign.setLocation(800  , 300);
                dispose();// clsoe window
            }
        });
    }

    public static void main(String[] args)   {
        App app= new App(); // instintiate initial page
        app.setLocation(800  , 300);

        //SignUp signUp=new SignUp();
       // UserA user =new UserA("vural", "bilgin", "1561asd", "vuraldesu");
        
        //new ProfileGUI();

        //List all the job and user to the console to check out
        //noy necesarry
        List<Users> users = Users.getAllUsersFromDatabase();
        for (Users user : users) {
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Job: " + user.getJob());
            System.out.println("-------------");
        }
        List<Jobs> jobList = Jobs.getAllJobsFromDatabase();
        for(Jobs job:jobList){
            System.out.println("Name: " + job.getName());
            System.out.println("Company: " + job.getCompany());
            System.out.println("salary: " + job.getSalary());
            System.out.println("desc: " + job.getDescription());
             System.out.println("-------------"); 
        }
        
    }

    
}
