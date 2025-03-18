import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private JPanel panel; // Main panel to hold all login components
    private JTextField usernameField; // Input field for username
    private JPasswordField passwordField; // Input field for password
    private JButton loginButton, signUpButton; // Buttons for login and sign up actions
    private JLabel messageLabel; // Label to display messages to the user
    private static final String FILE_NAME = "users.txt"; // File to store user credentials

    public Login(JFrame frame) {
        frame.setTitle("Login Form"); // Set the title of the frame
        panel = new JPanel(new GridBagLayout()); // Create a panel with a grid layout for alignment
        panel.setBackground(Color.decode("#2494cd")); // Set background color of panel
        GridBagConstraints gbc = new GridBagConstraints(); // Object to control layout constraints
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding around components

        JLabel titleLabel = new JLabel("Login", JLabel.CENTER); // Create a title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font style and size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc); // Add title label to panel

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc); // Label for username input
        gbc.gridx = 1;
        usernameField = new JTextField(15); // Create text field for username input
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc); // Label for password input
        gbc.gridx = 1;
        passwordField = new JPasswordField(15); // Create password field for secure input
        panel.add(passwordField, gbc);

        loginButton = new JButton("Login"); // Create login button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        signUpButton = new JButton("Create Account"); // Create sign-up button
        gbc.gridy = 4;
        panel.add(signUpButton, gbc);

        messageLabel = new JLabel("", JLabel.CENTER); // Create label to display messages
        gbc.gridy = 5;
        panel.add(messageLabel, gbc);

        loginButton.addActionListener(e -> login(frame)); // Add action listener to login button
        signUpButton.addActionListener(e -> switchToSignUp(frame)); // Add action listener to sign-up button
    }

    private void login(JFrame frame) {
        String username = usernameField.getText(); // Get username input
        String password = new String(passwordField.getPassword()); // Get password input
        Map<String, String> users = readUsersFromFile(); // Read users from file

        if (users.containsKey(username) && users.get(username).equals(password)) { // Check if credentials are valid
            messageLabel.setText("Login successful!");
            frame.getContentPane().removeAll(); // Clear current content

            if (hasSavedSuggestions(username)) { // Check if user has saved suggestions
                loadSuggestions(frame, username); // Load suggestions
            } else {
                UserInfo user = new UserInfo(frame); // Create user info panel
                frame.add(user.getPanel()); // Add user info panel to frame
            }
            
            frame.revalidate(); // Refresh frame
            frame.repaint();
        } else {
            messageLabel.setText("Invalid credentials. Try again."); // Display error message
        }
    }

    private void switchToSignUp(JFrame frame) {
        frame.getContentPane().removeAll(); // Clear current content
        frame.add(new SignUp(frame).getPanel()); // Add sign-up panel to frame
        frame.revalidate(); // Refresh frame
        frame.repaint();
    }

    private Map<String, String> readUsersFromFile() {
        Map<String, String> users = new HashMap<>(); // Create a map to store user credentials
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) { // Read each line from file
                String[] parts = line.split(","); // Split username and password
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]); // Store in map
                }
            }
        } catch (IOException ignored) {} // Handle file reading exception
        return users;
    }

    private boolean hasSavedSuggestions(String username) {
        File file = new File(username + "Suggestions.txt"); // Check if suggestions file exists
        return file.exists();
    }

    private void loadSuggestions(JFrame frame, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(username + "Suggestions.txt"))) {
            StringBuilder suggestions = new StringBuilder(); // Create a StringBuilder to store suggestions
            String line;
            while ((line = reader.readLine()) != null) { // Read each line from file
                suggestions.append(line).append("\n"); // Append to suggestions
            }
            
            frame.getContentPane().removeAll(); // Clear current content
            Suggest suggest = new Suggest(frame, username, suggestions.toString()); // Create suggestion panel
            frame.add(suggest.getPanel()); // Add suggestion panel to frame
            frame.revalidate(); // Refresh frame
            frame.repaint();
        } catch (IOException e) {
            e.printStackTrace(); // Print error if file reading fails
        }
    }

    public JPanel getPanel() {
        return panel; // Return the main panel
    }
}
