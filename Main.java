import javax.swing.JFrame; // Import the JFrame class from the Swing library to create a window

public class Main { // Define a class named 'Main'
    public static void main(String[] args) { // The main method, which is the entry point of the program
        
        JFrame frame = new JFrame("Login Window"); // Create a new JFrame (window) with the title "Login Window"
        frame.setSize(500, 500); // Set the width and height of the window to 500x500 pixels
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        frame.setResizable(false); // Prevent the user from resizing the window
        frame.setLocationRelativeTo(null); // Center the window on the screen
        
        Login login = new Login(frame); // Create an instance of the Login class, passing the frame as a parameter
        frame.add(login.getPanel()); // Add the login panel (user interface) to the frame
        
        frame.setVisible(true); // Make the window visible on the screen
    }
}