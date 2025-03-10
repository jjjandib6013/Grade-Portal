import javax.swing.*;
import java.awt.*;

public class FirstYear {
    JLabel usernameLabel;
    JPanel panel;

    public FirstYear(JFrame frame, String username) {
        panel = new JPanel(new BorderLayout());

        usernameLabel = new JLabel("Welcome, " + username + "!");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
        panel.add(usernameLabel, BorderLayout.NORTH);
        

    }

    public JPanel getPanel() {
        return panel;
    }

}