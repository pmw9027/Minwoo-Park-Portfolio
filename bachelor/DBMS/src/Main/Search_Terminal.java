package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pmw90 on 2015-12-03.
 */



public class Search_Terminal extends JFrame {
    private JPanel JPanel_root;
    private JButton button1;
    private JButton button2;
    private JList Jlist;

    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    DefaultListModel listModel = new DefaultListModel();
    Search_Terminal(int i){
        super("Hello world");
        setTitle("Hi");
        setContentPane(JPanel_root);
        setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        ArrayList<TerminalVo> al = Main.db.DB_Terminal();
        int j=0;
        while(!al.isEmpty()){
            listModel.add(j++,al.remove(0).getName());
        }
        Jlist.setModel(listModel);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i==1)
                    Main.home.set_start_terminal(Jlist.getSelectedValue().toString());
                else
                    Main.home.set_arrive_terminal(Jlist.getSelectedValue().toString());
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setVisible(true);
    }
}
