import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo {
    JLabel titleLabel, nameLabel, yearLeveLabel; // Labels for user interface
    JTextField nameTextField; // Text field for user input (name)
    JButton continueButton; // Button to proceed to next step
    JComboBox<String> yearLevelCombobox; // Dropdown menu for selecting year level
    JPanel panel; // Main panel containing all components
    String username; // Variable to store the entered username

    // Constructor that initializes username only
    public UserInfo(String username) {
        this.username = username;
    }

    // Constructor that sets up the user info form inside a frame
    public UserInfo(JFrame frame) {
        frame.setTitle("Information Page"); // Set frame title
        panel = new JPanel(new GridBagLayout()); // Create panel with grid layout for proper alignment
        panel.setBackground(Color.decode("#2494cd")); // Set background color
        
        GridBagConstraints gbc = new GridBagConstraints(); // Object to define layout constraints
        gbc.insets = new Insets(10, 10, 10, 10); // Set spacing between components

        // Title Label Setup
        titleLabel = new JLabel("Enter your information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and style
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc); // Add title label to panel

        // Name Label Setup
        nameLabel = new JLabel("Enter your name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, gbc);

        // Name Input Field Setup
        nameTextField = new JTextField(15);
        nameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(nameTextField, gbc);

        // Year Level Label Setup
        yearLeveLabel = new JLabel("Year Level: ");
        yearLeveLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(yearLeveLabel, gbc);

        // Dropdown Menu for Year Level Selection
        yearLevelCombobox = new JComboBox<>(new String[] {
            "1ST YEAR", "2ND YEAR", "3RD YEAR", "4TH YEAR"
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(yearLevelCombobox, gbc);

        // Continue Button Setup
        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 14));
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = nameTextField.getText(); // Get the entered username
                String yearLevel = (String) yearLevelCombobox.getSelectedItem(); // Get selected year level

                frame.getContentPane().removeAll(); // Clear current content from frame

                // Load appropriate panel based on selected year level
                if ("1ST YEAR".equals(yearLevel)) {
                    frame.add(new FirstYear(frame, username).getPanel());
                } else if ("2ND YEAR".equals(yearLevel)) {
                    frame.add(new SecondYear(frame, username).getPanel());
                } else if ("3RD YEAR".equals(yearLevel)) {
                    frame.add(new ThirdYear(frame, username).getPanel());
                } else if ("4TH YEAR".equals(yearLevel)) {
                    frame.add(new FourthYear(frame, username).getPanel());
                }
                
                frame.revalidate(); // Refresh frame to display new content
                frame.repaint();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(continueButton, gbc);
    }
    
    // Method to return the panel containing all components
    public JPanel getPanel() {
        return panel;
    }
}