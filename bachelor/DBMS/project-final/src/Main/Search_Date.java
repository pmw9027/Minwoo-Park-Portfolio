package Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by pmw90 on 2015-12-04.
 */
public class Search_Date extends JFrame{
    private JSpinner spinner_year;
    private JButton Button_ok;
    private JButton Button_cancel;
    private JPanel rootPanel;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    DefaultListModel listModel = new DefaultListModel();
    Search_Date(){
        super("Hello world");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(screenSize.width / 2 - this.getWidth() / 2, screenSize.height / 2 - this.getHeight() / 2);
        Date now = new Date();
        Calendar start = Calendar.getInstance ( );
        Calendar end = Calendar.getInstance ( );
        start.add(Calendar.DATE, -1);
        end.add ( Calendar.DATE, 10 );
        final SpinnerDateModel model = new SpinnerDateModel(now,start.getTime(),end.getTime(), Calendar.DAY_OF_MONTH);
        spinner_year.setModel(model);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner_year,"yyyy-MM-dd");
        JFormattedTextField ftf = editor.getTextField();
        ftf.setEditable(false);
        ftf.setHorizontalAlignment(JTextField.CENTER);
        ftf.setBackground(new Color(255, 255, 255));
        spinner_year.setEditor(editor);
        spinner_year.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Date value = (Date) model.getValue();
                Date next = (Date) model.getNextValue();
                if (value != null && next != null)
                    System.out.println("value = " + df.format(value) + "\t"
                            + "next = " + df.format(next));
            }
        });
        Button_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.home.set_date(df.format((Date) model.getValue()));
                dispose();
            }
        });
        Button_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setVisible(true);
    }

}
