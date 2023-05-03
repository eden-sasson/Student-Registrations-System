import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginGUI extends JFrame implements ActionListener {
    private JLabel lblidStudents, lblPasswordStudents, lblMessage;
    private JTextField txtidStudents, txtPasswordStudents;
    private JButton btnLogin, btnRegister;

    public LoginGUI() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        lblidStudents = new JLabel("Student ID:");
        txtidStudents = new JTextField(10);
        add(lblidStudents);
        add(txtidStudents);

        lblPasswordStudents = new JLabel("Password:");
        txtPasswordStudents = new JPasswordField(10);
        add(lblPasswordStudents);
        add(txtPasswordStudents);

        lblMessage = new JLabel("");
        add(lblMessage);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this);
        add(btnRegister);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String idStudents = txtidStudents.getText();
        String PasswordStudents = txtPasswordStudents.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universityxyz", "root", "EScop4338");
            Statement stmt = conn.createStatement();

            if (action.equals("Login")) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Students WHERE idStudents='" + idStudents + "' AND PasswordStudents='" + PasswordStudents + "'");
                if (rs.next()) {
                    // login successful, open the database GUI
                    new DatabaseGUI();
                    dispose(); // close the login GUI
                } else {
                    lblMessage.setText("Invalid idStudents or password");
                }
            } else if (action.equals("Register")) {
                stmt.executeUpdate("INSERT INTO students (idStudents, PasswordStudents) VALUES('" + idStudents + "', '" + PasswordStudents + "')");
                JOptionPane.showMessageDialog(this, "Record inserted successfully");
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
