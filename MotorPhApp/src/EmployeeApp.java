import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTextField;

import CreateAccount.CreateAccount;
import EmployeeDetails.EmployeeInfo;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EmployeeApp {

    private JFrame frmMotorph;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private static Map<String, String> employeeMap;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeApp window = new EmployeeApp();
                    window.frmMotorph.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    //Create the application.
    public EmployeeApp() {
        initialize();
        try {
            EmployeeDetails();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading employee details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Initialize the contents of the frame.
    private void initialize() {
        frmMotorph = new JFrame();
        frmMotorph.getContentPane().setBackground(new Color(255, 182, 193));
        frmMotorph.setTitle("MotorPh");
        frmMotorph.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 11));
        frmMotorph.setBounds(100, 100, 477, 444);
        frmMotorph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMotorph.getContentPane().setLayout(null);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(213, 112, 169, 20);
        frmMotorph.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblUsername = new JLabel("Username (Last Name)");
        lblUsername.setBounds(62, 115, 150, 14);
        frmMotorph.getContentPane().add(lblUsername);
        
        JLabel lblPassword = new JLabel("Password (Birthday)");
        lblPassword.setBounds(62, 164, 150, 14);
        frmMotorph.getContentPane().add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(213, 161, 169, 20);
        frmMotorph.getContentPane().add(txtPassword);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(txtPassword.getPassword()).trim();
                String username = txtUsername.getText().trim();
                
                if (employeeMap != null && employeeMap.containsKey(username) && employeeMap.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    
                    //open employee details
                    EmployeeInfo info = new EmployeeInfo();
                    EmployeeInfo.main(null);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText("");
                    txtUsername.setText("");
                }
            }
        });
        btnLogin.setBounds(103, 241, 89, 23);
        frmMotorph.getContentPane().add(btnLogin);
        
        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // create functionality
            	CreateAccount info = new CreateAccount();
            	CreateAccount.main(null);
            }
        });
        btnCreate.setBounds(253, 241, 89, 23);
        frmMotorph.getContentPane().add(btnCreate);
    }
    
    public static void EmployeeDetails() throws IOException {
        String file = "src\\EmployeeDetails.csv";
        BufferedReader reader = null;
        String line = "";
        employeeMap = new HashMap<>();
        
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 18) { // Assuming relevant columns are at least the first 4
                    String lastName = row[1].trim();
                    String birthday = row[3].trim();
                    String position = row[11].trim();
                    employeeMap.put(lastName, birthday);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
   
}



