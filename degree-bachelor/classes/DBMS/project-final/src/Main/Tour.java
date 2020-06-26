package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by pmw90 on 2015-12-05.
 */
public class Tour extends JFrame{
    private JPanel rootPanel;
    private JPanel Jpanel_image;
    private JLabel Jlabel_name;
    private JLabel Jlabel_addres;
    private JPanel Jlabel_text;
    private JLabel Jlabel_image;
    private JButton Button_my_tour_insert;
    private JLabel Jlabel_tel;
    private JLabel Jlabel_text2;
    private JTextPane textPane1;
    private JButton button_hompage;
    private JTextPane textPane2;
    private BufferedImage image;
    Tour(TourVo tour) {
        Main.jsn = new Json(tour);
        ImageIcon img=null;
        tour = Main.jsn.getTour();
        //Jlabel_text
        try {
            if(tour.getImage()!=null) {
                image = ImageIO.read(new URL(tour.getImage()));
                img = new ImageIcon(((new ImageIcon(image)).getImage()).getScaledInstance(200,200*image.getWidth()/image.getHeight(), java.awt.Image.SCALE_SMOOTH)) ;
            }
        } catch (Exception e) {
            image = null;
        }
        Jlabel_name.setText(tour.getTitle());
        Jlabel_addres.setText(tour.getAdrres());
        Jlabel_image.setIcon(img);
        Jlabel_tel.setText(tour.getHomepage());
        button_hompage.setText("이동");
        textPane1.setText(tour.getOverview());
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        Button_my_tour_insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.session !=null){
                    Main.db.DB_Mytour_insert(Main.jsn.getTour().getContentID());
                }
                else
                    new Login();
            }
        });
    }
}
