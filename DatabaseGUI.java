import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseGUI extends JFrame implements ActionListener {
    private JLabel lblidStudents, lblPasswordStudents, lblStudentsFirst, lblStudentsLast, lblStudentsDOB, lblStudentsAddress, lblStudentsPhone, lblStudentsEmail;
    private JTextField txtidStudents, txtPasswordStudents, txtStudentsFirst, txtStudentsLast, txtStudentsDOB, txtStudentsAddress, txtStudentsPhone, txtStudentsEmail;
    private JButton btnInsert, btnUpdate, btnDelete, btnSelect;

    public DatabaseGUI() {
        super("DatabaseGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(10, 3));
        setVisible(false); // make the window initially invisible

        lblidStudents = new JLabel("ID:");
        txtidStudents = new JTextField(10);
        add(lblidStudents);
        add(txtidStudents);

        lblPasswordStudents = new JLabel("Password:");
        txtPasswordStudents = new JTextField(10);
        add(lblPasswordStudents);
        add(txtPasswordStudents);

        lblStudentsFirst = new JLabel("First Name:");
        txtStudentsFirst = new JTextField(10);
        add(lblStudentsFirst);
        add(txtStudentsFirst);

        lblStudentsLast = new JLabel("Last Name:");
        txtStudentsLast = new JTextField(10);
        add(lblStudentsLast);
        add(txtStudentsLast);

        lblStudentsDOB = new JLabel("DOB:");
        txtStudentsDOB = new JTextField(10);
        add(lblStudentsDOB);
        add(txtStudentsDOB);

        lblStudentsAddress = new JLabel("Address:");
        txtStudentsAddress = new JTextField(10);
        add(lblStudentsAddress);
        add(txtStudentsAddress);

        lblStudentsPhone = new JLabel("Phone:");
        txtStudentsPhone = new JTextField(10);
        add(lblStudentsPhone);
        add(txtStudentsPhone);

        lblStudentsEmail = new JLabel("Email:");
        txtStudentsEmail = new JTextField(10);
        add(lblStudentsEmail);
        add(txtStudentsEmail);

        btnInsert = new JButton("Insert");
        btnInsert.addActionListener(this);
        add(btnInsert);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnSelect = new JButton("Select");
        btnSelect.addActionListener(this);
        add(btnSelect);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String idStudents = txtidStudents.getText();
        String PasswordStudents = txtPasswordStudents.getText();
        String StudentsFirst = txtStudentsFirst.getText();
        String StudentsLast = txtStudentsLast.getText();
        String StudentsDOB = txtStudentsDOB.getText();
        String StudentsAddress = txtStudentsAddress.getText();
        String StudentsPhone = txtStudentsPhone.getText();
        String StudentsEmail = txtStudentsEmail.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/universityxyz", "root", "EScop4338");
            Statement stmt = conn.createStatement();

            if (action.equals("Insert")) {
                stmt.executeUpdate("INSERT INTO students VALUES('" + idStudents + "', '" + PasswordStudents + "', '" + StudentsFirst + "', '" + StudentsLast + "', '" + StudentsDOB + "', '" + StudentsAddress + "', '" + StudentsPhone + "', '" + StudentsEmail + "')");
                JOptionPane.showMessageDialog(this, "Record inserted successfully");
            } else if (action.equals("Update")) {
                stmt.executeUpdate("UPDATE students SET PasswordStudents='" + PasswordStudents + "',StudentsFirst='" + StudentsFirst + "', StudentsLast='" + StudentsLast + "', StudentsDOB='" + StudentsDOB + "', StudentsAddress='" + StudentsAddress + "', StudentsPhone='" + StudentsPhone + "', StudentsEmail='" + StudentsEmail + "' WHERE idStudents='" + idStudents + "'");
                JOptionPane.showMessageDialog(this, "Record updated successfully");
            } else if (action.equals("Delete")) {
                stmt.executeUpdate("DELETE FROM students WHERE idStudents='" + idStudents + "'");
                JOptionPane.showMessageDialog(this, "Record deleted successfully");
            } else if (action.equals("Select")) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE idStudents='" + idStudents + "'");
                if (rs.next()) {
                    txtPasswordStudents.setText(rs.getString("PasswordStudents"));
                    txtStudentsFirst.setText(rs.getString("StudentsFirst"));
                    txtStudentsLast.setText(rs.getString("StudentsLast"));
                    txtStudentsDOB.setText(rs.getString("StudentsDOB"));
                    txtStudentsAddress.setText(rs.getString("StudentsAddress"));
                    txtStudentsPhone.setText(rs.getString("StudentsPhone"));
                    txtStudentsEmail.setText(rs.getString("StudentsEmail"));
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with ID " + idStudents);
                }
            }

            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new DatabaseGUI();
    }
}