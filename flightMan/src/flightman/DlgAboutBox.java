package flightman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class DlgAboutBox extends JDialog {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    private JButton btnClose;
    private JLabel lblTitleVal = new JLabel();
    private JLabel lblVersion = new JLabel();
    private JLabel lblVersionVal = new JLabel();
    private JLabel lblContributors = new JLabel();
    private JLabel lblContributorsVal = new JLabel();
    private JLabel lblHomePage = new JLabel();
    private JLabel lblHomepageVal = new JLabel();
    private JLabel lblDescVal = new JLabel();
    private JLabel lblImage = new JLabel();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DlgAboutBox(...)">
    public DlgAboutBox(java.awt.Frame parent) {
        super(parent);
        initializeComponents();
        getRootPane().setDefaultButton(btnClose);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init Components">
    private void initializeComponents() {
        getContentPane().setLayout(new AbsoluteLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("About");
        setSize(399,208);
        setMaximumSize(new Dimension(318,225));
        setMinimumSize(new Dimension(318,225));
        setResizable(false);
        setModal(true);
        setResizable(false);

        btnClose = new JButton();
        btnClose.setText("Close");
        btnClose.addActionListener(new ClickHandler());
        
        lblTitleVal = new JLabel();
        lblTitleVal.setFont(new java.awt.Font("Tahoma", 1, 15));
        lblTitleVal.setText("Object Oriented Assignment T-43");

        lblDescVal = new JLabel();
        lblDescVal.setText("<html>An Application for Object Oriented\nassignment of Trimester 43 class");

        lblVersion = new JLabel();
        lblVersion.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblVersion.setText("Product Version:");

        lblVersionVal = new JLabel();
        lblVersionVal.setText("Trimester 43");

        lblContributors = new JLabel();
        lblContributors.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblContributors.setText("Contribution:");

        lblContributorsVal = new JLabel();

        lblContributorsVal.setText("<html>1071112174 Babak Sarmady<br>"+
                "1071112223 Baglan Beisenov<br>"+
                "1091104498 Shiva Tehrani<br>"+
                "1101106599 Shideh Adibi<br>");

        lblHomePage = new JLabel();
        lblHomePage.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11));
        lblHomePage.setText("Homepage:");

        lblHomepageVal = new JLabel();
        lblHomepageVal.setText("www.mmu.edu.my");


        lblImage = new JLabel();
        lblImage.setIcon(new ImageIcon(getClass().getResource("/flightman/resources/about.png")));

        getContentPane().add(lblImage, new AbsoluteConstraints(8, 8, -1, -1));

        getContentPane().add(lblTitleVal, new AbsoluteConstraints(148, 11, -1, -1));
        getContentPane().add(lblDescVal, new AbsoluteConstraints(148, 36, 266, 30));

        getContentPane().add(lblVersion, new AbsoluteConstraints(148, 70, -1, -1));
        getContentPane().add(lblVersionVal, new AbsoluteConstraints(246, 70, -1, -1));

        getContentPane().add(lblHomePage, new AbsoluteConstraints(148, 90, -1, -1));
        getContentPane().add(lblHomepageVal, new AbsoluteConstraints(246, 90, -1, -1));

        getContentPane().add(lblContributors, new AbsoluteConstraints(148, 110, -1, -1));
        getContentPane().add(lblContributorsVal, new AbsoluteConstraints(246, 110, 160, 50));


        getContentPane().add(btnClose, new AbsoluteConstraints(347, 165, -1, -1));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private class ClickHandler">
    private class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }
    // </editor-fold>
}
