package flightman;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import javax.swing.JOptionPane;
import org.netbeans.lib.awtextra.*;

public class DlgReservation extends javax.swing.JDialog {

    // <editor-fold defaultstate="collapsed" desc="variables declaration">
    public JComboBox cbCustomer;
    public JComboBox cbFlight;
    public JFormattedTextField edtBookingDate;
    public JFormattedTextField edtTravelDate;
    public JCheckBox cbMeal;

    private JLabel lblCustomer;
    private JLabel lblflight;
    private JLabel lblBookingDate;
    private JLabel lbltravelDate;

    private JButton btnOk;
    private JButton btnCancel;

    public int modalResult;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public DlgReservation(...)">
    public DlgReservation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        //initComponents();
        
        initializeComponents();

        modalResult =0;
        //getRootPane().setDefaultButton(closeButton);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init Components">
    private void initializeComponents() {
        getContentPane().setLayout(new AbsoluteLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add/Edit Reservation");
        setSize(399,177);
        setMaximumSize(new Dimension(318,228));
        setMinimumSize(new Dimension(318,228));
        setResizable(false);


        cbCustomer = new JComboBox();
        cbCustomer.setModel(new DefaultComboBoxModel());
        cbFlight = new JComboBox();
        cbFlight.setModel(new DefaultComboBoxModel());
        edtBookingDate = new JFormattedTextField();
        edtBookingDate.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(DateFormat.getDateInstance(DateFormat.SHORT))));
        edtTravelDate = new JFormattedTextField();
        edtTravelDate.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(DateFormat.getDateInstance(DateFormat.SHORT))));
        cbMeal = new JCheckBox();
        cbMeal.setText("With Meal");

        lblCustomer = new JLabel();
        lblCustomer.setText("Customer");
        lblflight = new JLabel();
        lblflight.setText("Flight");
        lblBookingDate = new JLabel();
        lblBookingDate.setText("Booking Date");
        lbltravelDate = new JLabel();
        lbltravelDate.setText("Traveling Date");

        btnOk = new JButton();
        btnOk.setText("Ok");
        btnOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ((cbCustomer.getSelectedIndex()>-1) || (cbFlight.getSelectedIndex()>-1)){
                    modalResult=1;
                    dispose();
                } else if (cbCustomer.getSelectedIndex()<0){
                    JOptionPane.showMessageDialog(null,"Invalid Customer Selected!");
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Flight Selected!");
                }
            }
        });


        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modalResult=0;
                dispose();
            }
        });


        getContentPane().add(cbCustomer, new AbsoluteConstraints(80, 8, 225, 21));
        getContentPane().add(cbFlight, new AbsoluteConstraints(80, 40, 225, 21));
        getContentPane().add(edtBookingDate, new AbsoluteConstraints(80, 72, 225, 21));
        getContentPane().add(edtTravelDate, new AbsoluteConstraints(80, 104, 225, 21));
        getContentPane().add(cbMeal, new AbsoluteConstraints(80, 136, 225, 21));

        getContentPane().add(lblCustomer, new AbsoluteConstraints(8, 12, 72, 13));
        getContentPane().add(lblflight, new AbsoluteConstraints(8, 44, 72, 13));
        getContentPane().add(lblBookingDate, new AbsoluteConstraints(8, 76, 72, 13));
        getContentPane().add(lbltravelDate, new AbsoluteConstraints(8, 108, 72, 13));

        getContentPane().add(btnOk, new AbsoluteConstraints(232, 160, 75, 25));
        getContentPane().add(btnCancel, new AbsoluteConstraints(152, 160, 75, 25));

        //pack();
    }
    // </editor-fold>
}
