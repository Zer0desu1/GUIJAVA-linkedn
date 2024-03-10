// JobDetail.java Author: Vural Bilgin ID: 22095034
// Gives more detail info about job
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobDetail extends JFrame {
    private JButton applyBtn;
    private JPanel mainPanel;
    private JLabel nameLabel, companyLabel, descriptionLabel, locationLabel, salaryLabel;
    private JTextField nameField, companyField, descriptionField, locationField, salaryField;

    public JobDetail(Jobs job,Users user) {
        mainPanel = new JPanel(new GridLayout(6, 2));

        setTitle("JOB DETAILS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);


        //Creating Components 
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(job.getName());
        nameField.setEditable(false);

        companyLabel = new JLabel("Company:");
        companyField = new JTextField(job.getCompany());
        companyField.setEditable(false);

        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(job.getDescription());
        descriptionField.setEditable(false);

        locationLabel = new JLabel("Location:");
        locationField = new JTextField(job.getLocation());
        locationField.setEditable(false);

        salaryLabel = new JLabel("Salary:");
        salaryField = new JTextField(String.valueOf(job.getSalary()));
        salaryField.setEditable(false);

        applyBtn = new JButton("Apply");


        //Adding components to panels
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(companyLabel);
        mainPanel.add(companyField);
        mainPanel.add(descriptionLabel);
        mainPanel.add(descriptionField);
        mainPanel.add(locationLabel);
        mainPanel.add(locationField);
        mainPanel.add(salaryLabel);
        mainPanel.add(salaryField);
        mainPanel.add(applyBtn);

        add(mainPanel);// merge them into main panel

        applyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// if button is clicked update the info(change the job that is applied)
                user.setJob(nameField.getText());//Update info
                Users.updateJob(user, nameField.getText());
                dispose();// close only this window
            }
        });
        setVisible(true);
    }


}
