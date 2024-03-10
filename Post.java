// Post.java Author: Vural Bilgin ID: 22095034
// Page of creating job post
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Post extends JFrame {
    // Components 
    private JLabel nameL, companyL, descriptionL, locationL, salaryL;
    private JTextArea nameTA, companyTA, descriptionTA, locationTA, salaryTA;
    private JPanel mainPanel;
    private JButton okBtn, cancelBtn;

    // Constructor to set up the GUI
    public Post() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));

        // Create and add components to the panel
        nameL = new JLabel("Name:");
        nameTA = new JTextArea();
        mainPanel.add(nameL);
        mainPanel.add(nameTA);

        companyL = new JLabel("Company:");
        companyTA = new JTextArea();
        mainPanel.add(companyL);
        mainPanel.add(companyTA);

        descriptionL = new JLabel("Description:");
        descriptionTA = new JTextArea();
        mainPanel.add(descriptionL);
        mainPanel.add(descriptionTA);

        locationL = new JLabel("Location:");
        locationTA = new JTextArea();
        mainPanel.add(locationL);
        mainPanel.add(locationTA);

        salaryL = new JLabel("Salary:");
        salaryTA = new JTextArea();
        mainPanel.add(salaryL);
        mainPanel.add(salaryTA);

        // Create buttons and add ActionListener for OK and Cancel actions
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        mainPanel.add(okBtn);
        mainPanel.add(cancelBtn);

        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from text areas
                String name = nameTA.getText();
                String company = companyTA.getText();
                String description = descriptionTA.getText();
                String location = locationTA.getText();
                int salary = Integer.parseInt(salaryTA.getText());

                // Create a new Job object with the entered data and the current date
                Jobs newJob = new Jobs(name, company, salary, description, location, LocalDate.now());

                // Insert the new job into the database
                Jobs.insertIntoDatabase(newJob);

                // Close the Post window
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the Post window without saving
                dispose();
            }
        });

        // Set up the frame properties
        setTitle("Post a Job");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);
    }
}
