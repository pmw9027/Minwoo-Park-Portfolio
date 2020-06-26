package Main.Login;

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
    private JButton button1;
    private JButton button2;

    private String ID;
    private String PW;


    private JPanel rootPanel;

    public Login(){
        super("Hello world");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ID = textField1.getText();
                PW = passwordField1.getText();
                //Main.setSession(ID,PW);
            }
        });
    }


}