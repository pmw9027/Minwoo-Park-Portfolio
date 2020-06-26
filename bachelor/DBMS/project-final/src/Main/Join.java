package Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class Join extends JFrame {
    private JPanel rootPanel;
    private JTextField text_id;
    private JPasswordField pw_field;
    private JTextField text_name;
    private JButton button1;
    private JButton Button_cancel;
    private JTextField address_field;
    private JPanel rootPanel_Join;
    private String ID;
    private String PW;

    public Join(){
        super("Hello world");
        setContentPane(rootPanel_Join);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.db.DB_JOIN(new UserVo(text_id.getText(),pw_field.getText(),text_name.getText(),address_field.getText(),0));
                dispose();
            }
        });
        Button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
