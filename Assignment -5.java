import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame implements ActionListener {
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    public LoginForm() {
        // Frame setup
        setTitle("Login Form");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        // Text fields
        userField = new JTextField();
        userField.setBounds(120, 30, 120, 25);
        add(userField);

        passField = new JPasswordField();
        passField.setBounds(120, 70, 120, 25);
        add(passField);

        // Button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 25);
        add(loginButton);

        loginButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.equals("admin") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
