// Dashboard.java Author: Vural Bilgin ID: 22095034
// Dashboard screen of the app, list all jobs posting and users 
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;


public class DashboardPanel extends JFrame {
    private JPanel mainPanel,searchPanel;
    private JTextArea searchBarTA;
    private JButton searchBtn;
    private JRadioButton jobRadio,userRadio;
    public DashboardPanel(Users user) {
        //creating panels 
        mainPanel=new JPanel();
        searchPanel=new JPanel();
        searchPanel.setLayout(new GridLayout(2,2));
        // Example: Add padding and margins
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        jobRadio=new JRadioButton("Job");
        userRadio=new JRadioButton("User");
        userRadio.setSelected(true);
        jobRadio.setSelected(true);
        //Creating components
        searchBarTA=new JTextArea();
        //Set colors and fonts
        searchBarTA.setFont(new Font("Arial", Font.PLAIN, 12));
        searchBarTA.setForeground(Color.BLACK);

        searchBtn=new JButton("Search");
        // Customize button appearance
        searchBtn.setBackground(new Color(50,120,200)); // Set a custom background color
        searchBtn.setForeground(Color.WHITE); // Set text color
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14)); // Set font

        List<Jobs> jobList = Jobs.getAllJobsFromDatabase();// Get all jobs
        List<Users> userList=Users.getAllUsersFromDatabase();//Get all users

        JobGUI job=new JobGUI(jobList,user);//Creating JobGUI window
        JPanel jobPanel=job.getMainPanel();// add window to a panel
        UserGUI userGUI=new UserGUI(userList);//Creating UserGUI window
        JPanel userPanel=userGUI.getMainPanel();// add window to a panel

        //Adding components 
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(jobPanel);
        mainPanel.add(userPanel);
        searchPanel.add(searchBarTA);
        searchPanel.add(searchBtn);
        searchPanel.add(jobRadio);
        searchPanel.add(userRadio);
        mainPanel.add(searchPanel);
        //Set layout manager and spacing
        mainPanel.setLayout(new BorderLayout()); 
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(jobPanel,BorderLayout.SOUTH);
        mainPanel.add(userPanel,BorderLayout.CENTER);
        

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchBarTA.getText().toLowerCase(); // Convert to lowercase for case-insensitive search

                List<Jobs> filteredJobs = new ArrayList<>();
                List<Users> filteredUsers = new ArrayList<>();
                // Filter jobs based on the search term
                
                if(jobRadio.isSelected() && userRadio.isSelected()){ // if selected then filter 
                    filteredJobs = filterJobs(jobList, searchTerm);
                    filteredUsers=filterUsers(userList,searchTerm);
                    mainPanel.add(jobPanel,BorderLayout.SOUTH);
                    mainPanel.add(userPanel,BorderLayout.CENTER);
                }
                
                else if(userRadio.isSelected()){
                    filteredUsers=filterUsers(userList,searchTerm);
                    mainPanel.add(jobPanel,BorderLayout.SOUTH);
                    mainPanel.add(userPanel,BorderLayout.CENTER);
                   
                }
                else if(jobRadio.isSelected()){
                    filteredJobs = filterJobs(jobList, searchTerm);
                    mainPanel.add(jobPanel,BorderLayout.CENTER);
                    mainPanel.add(userPanel,BorderLayout.SOUTH);
                }
                
                job.updateJobPanel(filteredJobs);// update the displayer
                userGUI.updateUserPanel(filteredUsers);
            }
        });
        
        
    }
     private List<Jobs> filterJobs(List<Jobs> jobs, String searchTerm) {
        List<Jobs> filteredJobs = new ArrayList<>();

        for (Jobs job : jobs) {
            // Check if the search term matches job name, company, or location
            if (job.getName().toLowerCase().contains(searchTerm) ||
                job.getCompany().toLowerCase().contains(searchTerm) ||
                job.getLocation().toLowerCase().contains(searchTerm)) {
                filteredJobs.add(job);
            }
        }

        return filteredJobs;
    }
    private List<Users> filterUsers(List<Users> users, String searchTerm) {
        List<Users> filteredUsers = new ArrayList<>();

        for ( Users user : users) {
            // Check if the search term matches job name, company, or location
            if (user.getName().toLowerCase().contains(searchTerm) ||
                user.getSurname().toLowerCase().contains(searchTerm) ||
                user.getJob().toLowerCase().contains(searchTerm)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public JPanel getMainPanel() {
        return mainPanel; // send main panel 
    }
}
