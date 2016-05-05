package flightman;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.*;

public class DlgCustomer extends javax.swing.JDialog {

    // <editor-fold defaultstate="collapsed" desc="variables declaration">
    public JTextField edtSSID;
    public JTextField edtFirstName;
    public JTextField edtLastName;
    public JTextField edtCreditCard;
    public JTextField edtCVC2;
    public JTextField edtUserName;
    public JTextField edtPassword;

    private JLabel lblSSID;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblCreditCard;
    private JLabel lblCVC2;
    private JLabel lblUserName;
    private JLabel lblPassword;

    private JButton btnOk;
    private JButton btnCancel;

    public int modalResult;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public DlgCustomer(...)">
    public DlgCustomer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //initComponents();

        initializeComponents();
        modalResult =0;
        getRootPane().setDefaultButton(btnCancel);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init Components">
    private void initializeComponents() {
        getContentPane().setLayout(new AbsoluteLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add/Edit Customer");
        setSize(399,278);
        setMaximumSize(new Dimension(318,278));
        setMinimumSize(new Dimension(318,278));
        setResizable(false);

        edtSSID = new JTextField();
        edtFirstName = new JTextField();
        edtLastName = new JTextField();
        edtCreditCard = new JTextField();
        edtCVC2 = new JTextField();

        edtUserName = new JTextField();
        edtPassword = new JTextField();

        lblSSID = new JLabel();
        lblSSID.setText("Social ID");

        lblFirstName = new JLabel();
        lblFirstName.setText("First Name");
        lblLastName = new JLabel();
        lblLastName.setText("Last Name");

        lblCreditCard = new JLabel();
        lblCreditCard.setText("Credit Card");
        lblCVC2 = new JLabel();
        lblCVC2.setText("CVC2");

        lblUserName = new JLabel();
        lblUserName.setText("UserName");
        lblPassword = new JLabel();
        lblPassword.setText("Password");

        ClickHandler clickHandler = new ClickHandler();

        btnOk = new JButton();
        btnOk.setText("Ok");
        btnOk.addActionListener(clickHandler);

        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(clickHandler);

        getContentPane().add(edtSSID,       new AbsoluteConstraints(80,   8, 225, 21));
        getContentPane().add(edtFirstName,  new AbsoluteConstraints(80,  40, 225, 21));
        getContentPane().add(edtLastName,   new AbsoluteConstraints(80,  72, 225, 21));
        getContentPane().add(edtCreditCard, new AbsoluteConstraints(80, 104, 225, 21));
        getContentPane().add(edtCVC2,       new AbsoluteConstraints(80, 136, 225, 21));
        getContentPane().add(edtUserName,   new AbsoluteConstraints(80, 168, 225, 21));
        getContentPane().add(edtPassword,   new AbsoluteConstraints(80, 200, 225, 21));

        getContentPane().add(lblSSID,       new AbsoluteConstraints(8,   8, 72, 13));
        getContentPane().add(lblFirstName,  new AbsoluteConstraints(8,  40, 72, 13));
        getContentPane().add(lblLastName,   new AbsoluteConstraints(8,  72, 72, 13));
        getContentPane().add(lblCreditCard, new AbsoluteConstraints(8, 104, 72, 13));
        getContentPane().add(lblCVC2,       new AbsoluteConstraints(8, 136, 72, 13));
        getContentPane().add(lblUserName,   new AbsoluteConstraints(8, 168, 72, 13));
        getContentPane().add(lblPassword,   new AbsoluteConstraints(8, 200, 72, 13));

        getContentPane().add(btnCancel, new AbsoluteConstraints(152, 232, 75, 25));
        getContentPane().add(btnOk, new AbsoluteConstraints(232, 232, 75, 25));

        //pack();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private class ClickHandler">
    private class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == btnOk){
                // <editor-fold defaultstate="collapsed" desc="btnOk Handler">
                if ((edtSSID.getText().length()==0) || (edtFirstName.getText().length()==0) ||
                        (edtLastName.getText().length()==0) || (edtCreditCard.getText().length()==0) ||
                        (edtCVC2.getText().length()==0) || (edtUserName.getText().length()==0)  ||
                        (edtPassword.getText().length()==0)){
                    String Error ="";
                    if (edtSSID.getText().length()==0){
                        Error = Error + "Social Id is Empty!\n";
                    }
                    if (edtFirstName.getText().length()==0){
                        Error = Error + "First Name is Empty!\n";
                    }
                    if (edtLastName.getText().length()==0){
                        Error = Error + "Last Name is Empty!\n";
                    }
                    if (edtCreditCard.getText().length()==0){
                        Error = Error + "Credit Card is Empty!\n";
                    }
                    if (edtCVC2.getText().length()==0){
                        Error = Error + "CVC2 is Empty!\n";
                    }

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
            }
        }
    }
    // </editor-fold>

}
