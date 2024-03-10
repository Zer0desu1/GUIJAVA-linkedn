// JobGUI.java Author: Vural Bilgin ID: 22095034
// Create panel that contains job 

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JobGUI extends JFrame {
    private JTextField companyTF,jobNameTF,salaryTF,dateTF;
    private JTextArea jobDescTA,locationTA;
    private JButton viewJobBtn,exitButton;
    
    private JPanel infoFrame,buttonFrame,mainFrame;
    private Users user;
    public JobGUI(List<Jobs> jobs,Users user){
        

        this.user=user;
        mainFrame=new JPanel(); // main Panel
        // Set layout manager and spacing
        mainFrame.setLayout(new GridLayout(jobs.size(), 3, 10, 10)); // Add spacing between components

        for (Jobs job:jobs) {// Add all jobs to the main panel
            mainFrame.add(createJobPanel(job,user));
        }
        
    }
    
    private JPanel createJobPanel(Jobs job,Users user) {// Create individual panel for each job
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        JLabel nameLabel = new JLabel("Job :");
        JTextArea nameTA = new JTextArea(job.getName()+" "+job.getCompany()+"  "+job.getSalary());//Display info
        nameTA.setEditable(false);

        

        viewJobBtn =new JButton("View Job ");

        panel.add(nameLabel);
        panel.add(nameTA);
        panel.add(viewJobBtn);
        //  Set consistent font and color
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameTA.setFont(new Font("Arial", Font.PLAIN, 14));
        viewJobBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        viewJobBtn.setBackground(new Color(50, 100,150));
        viewJobBtn.setForeground(Color.WHITE);

        viewJobBtn.addActionListener(new ActionListener() { // goes to job details
            public void actionPerformed(ActionEvent e){
                JobDetail detail=new JobDetail(job,user); detail.setLocation(getLocation());
            }
        });
        
        return panel;
    }
    public JPanel getMainPanel() {// sends back main panel to Main page
        return mainFrame;
    }
    public void updateJobPanel(List<Jobs> jobs) {// yupdate the window
        mainFrame.removeAll(); // Remove existing components from the panel
        mainFrame.setLayout(new GridLayout(jobs.size(), 3));

        for (Jobs job : jobs) {// update all the jobs 
            // Create a JLabel or any other suitable component to display job information
            //JLabel jobLabel = new JLabel(job.getName() + " - " + job.getCompany() + " - " + job.getLocation());
            mainFrame.add(createJobPanel(job,user));
        }

        mainFrame.revalidate(); // Revalidate the panel to reflect the changes
        mainFrame.repaint(); // Repaint the panel to update the UI
    }

}
