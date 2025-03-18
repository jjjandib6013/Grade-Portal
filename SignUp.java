import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SignUp {
    private JPanel panel; // Main panel for the sign-up form
    private JTextField usernameField; // Field for entering username
    private JPasswordField passwordField; // Field for entering password
    private JButton registerButton, backButton; // Buttons for register and back actions
    private JLabel messageLabel; // Label to display messages
    private static final String FILE_NAME = "users.txt"; // File where user data is stored

    // Constructor for setting up the sign-up form in a given frame
    public SignUp(JFrame frame) {
        frame.setTitle("Create Account"); // Set the title of the window
        panel = new JPanel(new GridBagLayout()); // Create panel with grid layout for alignment
        panel.setBackground(Color.decode("#2494cd")); // Set background color

        GridBagConstraints gbc = new GridBagConstraints(); // Layout constraints for alignment
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between components

        // Title Label Setup
        JLabel titleLabel = new JLabel("Sign Up", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and style
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Username Label and Input Field Setup
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        panel.add(usernameField, gbc);

        // Password Label and Input Field Setup
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Register Button Setup
        registerButton = createButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        // Back to Login Button Setup
        backButton = createButton("Back to Login");
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        // Message Label for feedback
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setForeground(Color.WHITE);
        gbc.gridy = 5;
        panel.add(messageLabel, gbc);

        // Button Actions
        registerButton.addActionListener(e -> registerUser(frame));
        backButton.addActionListener(e -> switchToLogin(frame));
    }

    // Method to create labels with white text
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    // Method to create buttons with consistent style
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusable(false);
        return button;
    }

    // Method to handle user registration
    private void registerUser(JFrame frame) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Fields cannot be empty.");
            return;
        }

        // Attempt to save user to file and show appropriate message
        if (saveUserToFile(username, password)) {
            messageLabel.setText("Account created successfully!");
        } else {
            messageLabel.setText("Username already exists.");
        }
    }

    // Method to save new user credentials to file
    private boolean saveUserToFile(String username, String password) {
        File file = new File(FILE_NAME);

        // Check if username already exists
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(",")[0].equals(username)) {
                    return false; // Username already exists
                }
            }
        } catch (IOException ignored) {}

        // Save new username and password to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            return true; // Successfully saved
        } catch (IOException ignored) {}

        return false; // Failed to save user
    }

    // Method to switch back to the login page
    private void switchToLogin(JFrame frame) {
        frame.getContentPane().removeAll(); // Remove current components
        frame.add(new Login(frame).getPanel()); // Add login panel
        frame.revalidate(); // Refresh the frame
        frame.repaint();
    }

    // Method to return the panel containing the sign-up form
    public JPanel getPanel() {
        return panel;
    }
}