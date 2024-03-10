// ProfileGUI.java Author: Vural Bilgin ID: 22095034
// User's Profile Window , it contains users infos profile photos  and some activities
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProfileGUI extends JFrame{
    private JButton editProfButton,okButton,cancelButton,refreshBtn,postJobButton, selectPhotoButton;
    private JTextArea nameL,surnameL,emailL,jobL,passwordTA;
    private JPanel infoPanel,mainPanel,ppPanel,btnPanel1,btnPanel2;
    private JLabel photoLabel;
    public ProfileGUI(Users user,boolean isUser){

        //Creating Components
        postJobButton=new JButton("Post Job");
        editProfButton=new JButton("Edit Profile");
        okButton=new JButton("OK");
        cancelButton =new JButton("Cancel");
        refreshBtn=new JButton("Refresh");
        selectPhotoButton = new JButton("Select Photo");
        emailL=new JTextArea(user.getEmail());
        jobL=new JTextArea(user.getJob());
        infoPanel=new JPanel(new GridLayout(5,1));
        mainPanel=new JPanel(new GridLayout(2,2));
        ppPanel=new JPanel(new GridLayout(1,1));
        btnPanel1=new JPanel(new BorderLayout());
        btnPanel2=new JPanel(new BorderLayout());

        mainPanel.add(ppPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(btnPanel1);
        mainPanel.add(btnPanel2);
        

        nameL=new JTextArea(user.getName());
        surnameL=new JTextArea(user.getSurname());
        emailL.setEditable(false);jobL.setEditable(false);
        nameL.setEditable(false);surnameL.setEditable(false);
        infoPanel.add(nameL);
        infoPanel.add(surnameL);
        infoPanel.add(emailL);
        infoPanel.add(jobL);
        photoLabel = new JLabel();
        setPhoto("C:\\Users\\90539\\OneDrive\\Masaüstü\\sql.jpg");  
        
        // Add the photo label to the infoPanel
        ppPanel.add(photoLabel);
        
        if(isUser){// Only user can change their information, not others
            btnPanel1.add(selectPhotoButton,BorderLayout.NORTH);
            btnPanel1.add(postJobButton,BorderLayout.CENTER);
            btnPanel2.add(editProfButton,BorderLayout.NORTH);
            btnPanel2.add(refreshBtn,BorderLayout.CENTER);

        
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {// responsive buttons
                user.setPassword(passwordTA.getText());
                infoPanel.remove(passwordTA);
                infoPanel.remove(okButton);
                infoPanel.remove(cancelButton);
                btnPanel2.add(editProfButton,BorderLayout.NORTH);
                nameL.setEditable(false);
                surnameL.setEditable(false);
                emailL.setEditable(false);jobL.setEditable(false);
                // put to the db
                Users.updateUserData(user,nameL.getText(),surnameL.getText(),passwordTA.getText(),emailL.getText(),jobL.getText() );
                user.setName(nameL.getText());
                user.setSurname(surnameL.getText());
                user.setEmail(emailL.getText());
                user.setJob(jobL.getText());
                //update panels
                infoPanel.revalidate();
                infoPanel.repaint();
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoPanel.remove(okButton);
                infoPanel.remove(cancelButton);
                btnPanel2.add(editProfButton,BorderLayout.NORTH);
                nameL.setText(user.getName());
                surnameL.setText(user.getSurname());
                nameL.setEditable(false);
                surnameL.setEditable(false);   //non editable
                emailL.setEditable(false);jobL.setEditable(false);

                
        
                //update panels
                infoPanel.revalidate();
                infoPanel.repaint();
                btnPanel1.revalidate();
                btnPanel1.repaint();
            }
        });

        postJobButton.addActionListener(new ActionListener() {//Creating job posts
            public void actionPerformed(ActionEvent e) {
                new Post().setLocation(getLocation());;//pop op post window
            }
        });
        
        selectPhotoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectPhoto();// select photo
            }
        });
        
            editProfButton.addActionListener(new ActionListener() {// edit the information of users
            public void actionPerformed(ActionEvent e){
                passwordTA=new JTextArea(user.getPassword());
                infoPanel.add(passwordTA);
                btnPanel2.remove(editProfButton);
                infoPanel.add(okButton);
                infoPanel.add(cancelButton);
                nameL.setEditable(true);surnameL.setEditable(true);//make it editable
                emailL.setEditable(true);jobL.setEditable(true);
                //update
                infoPanel.revalidate();
                infoPanel.repaint();
                btnPanel1.revalidate();
                btnPanel1.repaint();
            }
        });
        
        
    }
    
    refreshBtn.addActionListener(new ActionListener() {// refresh the page
        public void actionPerformed(ActionEvent e) {
            jobL.setText(user.getJob());
                // update panel
                infoPanel.revalidate();
                infoPanel.repaint();
                btnPanel1.revalidate();
                btnPanel1.repaint();
        }
        });
}
private void setPhoto(String imagePath) {
        try {
            // Load the image from the file path
            Image image = ImageIO.read(new File(imagePath));

            // Resize the image to fit within a certain size (you can adjust this as needed)
            int maxSize = 100;
            Image scaledImage = image.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH);

            // Set the scaled image as the icon for the photoLabel
            photoLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public JPanel getMainPanel() {
    return mainPanel;
}

   private void selectPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            setPhoto(selectedFile.getAbsolutePath());
        }
    }
    public  void update(){
        mainPanel.revalidate(); // Revalidate the panel to reflect the changes
        mainPanel.repaint(); // Repaint the panel to update the UI
    }
}
