// UserGUI.java Author: Vural Bilgin ID: 22095034
// Create panel that contains all users 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserGUI extends JFrame {
    private JLabel nameL, surnameL, emailL, passwordL;
    private JTextArea nameTA, surnameTA, emailTA, passwordTA;
    private List<Users> users;
    private JButton viewProf;
    private JPanel mainPanel;
    public UserGUI(List<Users> users) {
        mainPanel=new JPanel();
        this.users = users;
        mainPanel.setLayout(new GridLayout(users.size(), 1));

        for (Users user : users) {// Each user has their own panel
            mainPanel.add(createUserPanel(user));// add to main panel
        }
        
        
        


        
    }

    private JPanel createUserPanel(Users user) {// Create panel for each users
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        // Add padding and margins
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        //Displaying user data
        JLabel nameLabel = new JLabel("USER:");
        JTextArea nameTA = new JTextArea(user.getName()+" "+user.getSurname()+"  "+user.getJob());
        nameTA.setEditable(false);

        

        viewProf =new JButton("View Profile");

        panel.add(nameLabel);
        panel.add(nameTA);
        panel.add(viewProf);

        viewProf.addActionListener(new ActionListener() {// See the users profile 
            public void actionPerformed(ActionEvent e) {
                ProfileGUI profileGui = new ProfileGUI(user, false);// profile window but changes if you are the user or not
                JFrame profileFrame = new JFrame();

                profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                profileFrame.setSize(800, 600);
                profileFrame.setTitle("Profile");
                profileFrame.add(profileGui.getMainPanel());
                profileFrame.setVisible(true);
                
            }
            
        });
        return panel;
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void updateUserPanel(List<Users> users) {
        mainPanel.removeAll(); // Remove existing components from the panel
        mainPanel.setLayout(new GridLayout(users.size(), 1));

        for (Users user : users) {
            // Create a JLabel or any other suitable component to display job information
            //JLabel userLabel = new JLabel("Name: "+user.getName() + " - " + user.getSurname() + " JOB: " + user.getJob());
            mainPanel.add(createUserPanel(user));
        }

        mainPanel.revalidate(); // Revalidate the panel to reflect the changes
        mainPanel.repaint(); // Repaint the panel to update the UI
    }
    public  void update(){
        mainPanel.revalidate(); // Revalidate the panel to reflect the changes
        mainPanel.repaint(); // Repaint the panel to update the UI
    }

}
