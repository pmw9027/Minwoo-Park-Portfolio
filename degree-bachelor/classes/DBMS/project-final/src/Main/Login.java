package Main;

import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class Login extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button2;
    private JButton button1;

    private String ID;
    private String PW;


    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();

    private JPanel rootPanel;
    private JButton Button_Join;

    public Login(){
        super("Hello world");
        setContentPane(rootPanel);
        pack();
        setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID = textField1.getText();
                PW = passwordField1.getText();
                String temp = Main.db.DB_Login(ID, PW);
                if (temp.equals(null)) {
                    JOptionPane.showMessageDialog(null, "Login Fail");
                } else {
                    Main.setSession(ID, temp);
                    Main.home.set_Login(temp);
                    dispose();
                }
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        Button_Join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Join();
            }
        });
    }


}