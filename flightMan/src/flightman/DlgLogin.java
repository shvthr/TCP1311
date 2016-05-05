package flightman;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.*;

public class DlgLogin extends javax.swing.JDialog {

    // <editor-fold defaultstate="collapsed" desc="variables declaration">
    public JTextField edtUserName;
    public JTextField edtPassword;

    private JLabel lblUserName;
    private JLabel lblPassword;
    private JLabel lblNewUser;

    private JButton btnOk;
    private JButton btnCancel;

    public int modalResult;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public DlgCustomer(...)">
    public DlgLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //initComponents();

        initializeComponents();
        modalResult =0;
        getRootPane().setDefaultButton(btnOk);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init Components">
    private void initializeComponents() {
        getContentPane().setLayout(new AbsoluteLayout());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Login");
        setSize(399,166);
        setMaximumSize(new Dimension(318,166));
        setMinimumSize(new Dimension(318,166));
        setResizable(false);

        ClickHandler clickHandler = new ClickHandler();

        edtUserName = new JTextField();
        edtPassword = new JTextField();

        lblUserName = new JLabel();
        lblUserName.setText("UserName");
        lblPassword = new JLabel();
        lblPassword.setText("Password");

        lblNewUser = new JLabel();
        lblNewUser.setText("<html><a href=''>New Customer</a>");
        lblNewUser.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                modalResult=2;
                dispose();
            }
        });

        btnOk = new JButton();
        btnOk.setText("Ok");
        btnOk.addActionListener(clickHandler);

        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(clickHandler);

        getContentPane().add(edtUserName,   new AbsoluteConstraints( 80,   8, 225, 21));
        getContentPane().add(edtPassword,   new AbsoluteConstraints( 80,  40, 225, 21));

        getContentPane().add(lblUserName,   new AbsoluteConstraints(  8,   8,  72, 13));
        getContentPane().add(lblPassword,   new AbsoluteConstraints(  8,  40,  72, 13));
        getContentPane().add(lblNewUser,    new AbsoluteConstraints(  8,  72,  72, 13));

        getContentPane().add(btnCancel,     new AbsoluteConstraints(152, 104,  75, 25));
        getContentPane().add(btnOk,         new AbsoluteConstraints(232, 104,  75, 25));

        //pack();
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="private class ClickHandler">
    private class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == btnOk){
                // <editor-fold defaultstate="collapsed" desc="btnOk Handler">
                if ((edtUserName.getText().length()==0) || (edtPassword.getText().length()==0)){
                    String Error ="";
                    if (edtUserName.getText().length()==0){
                        Error = Error + "Username is Empty!\n";
                    }
                    if (edtPassword.getText().length()==0){
                        Error = Error + "Password is Empty!\n";
                    }
                    JOptionPane.showMessageDialog(null,Error);
                } else {
                    modalResult=1;
                    dispose();
                }
                // </editor-fold>
            } else if (e.getSource() == btnCancel){
                // <editor-fold defaultstate="collapsed" desc="btnCancel Handler">
                modalResult=0;
                dispose();
                // </editor-fold>
            } else if (e.getSource() == lblNewUser){
                // <editor-fold defaultstate="collapsed" desc="lblNewUser Handler">
                modalResult=2;
                dispose();
                // </editor-fold>
            }
        }
    }
    // </editor-fold>

}

