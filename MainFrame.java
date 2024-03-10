// MainFrame.java Author: Vural Bilgin ID: 22095034
// First Page of Linkedn (Directs user to login screen or sign up screen)
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    public MainFrame(Users user) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("MAIN PAGE");
        
        JTabbedPane tabbedPane = new JTabbedPane();// Tab component

        

        // Create instances of your panels
        ProfileGUI profilePanel = new ProfileGUI(user, true);
        DashboardPanel dashboardPanel = new DashboardPanel(user);

        dashboardPanel.getMainPanel().setPreferredSize(new Dimension(800, 500));
        profilePanel.getMainPanel().setPreferredSize(new Dimension(800, 500));
        // Wrap panels with JScrollPane
        JScrollPane dashboardScrollPane = new JScrollPane(dashboardPanel.getMainPanel());
        JScrollPane profileScrollPane = new JScrollPane(profilePanel.getMainPanel());

        // Add panels with scroll bars to the tabbed pane
        tabbedPane.addTab("Dashboard", dashboardScrollPane);
        tabbedPane.addTab("Profile", profileScrollPane);

        // Add the tabbed pane to the frame
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
