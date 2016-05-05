package flightman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class DlgFlight extends javax.swing.JDialog {

    // <editor-fold defaultstate="collapsed" desc="variables declaration">
    public JTextField edtOrigin;
    public JTextField edtDestination;
    public JCheckBox cbStopOrTransit;
    public JCheckBox cbAvailablity;

    private JLabel lblOrigin;
    private JLabel lblDestination;

    private JButton btnOk;
    private JButton btnCancel;

    public int modalResult;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public DlgFlight(...)">
    public DlgFlight(java.awt.Frame parent, boolean modal) {
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
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setTitle("Add/Edit Flight");
        setSize(399,208);
        setMaximumSize(new Dimension(318,154));
        setMinimumSize(new Dimension(318,154));
        setResizable(false);

        ClickHandler clickHandler = new ClickHandler();

        edtOrigin = new JTextField();
        edtDestination = new JTextField();

        cbAvailablity = new  JCheckBox();
        cbAvailablity.setText("Availablity");

        cbStopOrTransit = new  JCheckBox();
        cbStopOrTransit.setText("Stop/Transit");

        lblOrigin = new JLabel();
        lblOrigin.setText("Origin");
        lblDestination = new JLabel();
        lblDestination.setText("Destination");

        btnOk = new JButton();
        btnOk.setText("Ok");
        btnOk.addActionListener(clickHandler);

        btnCancel = new JButton();
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(clickHandler);

        getContentPane().add(edtOrigin, new AbsoluteConstraints(80, 8, 225, 21));
        getContentPane().add(edtDestination, new AbsoluteConstraints(80, 40, 225, 21));
        getContentPane().add(cbStopOrTransit, new AbsoluteConstraints(72, 64, 90, 15));
        getContentPane().add(cbAvailablity, new AbsoluteConstraints(162, 64, 80, 15));

        getContentPane().add(lblOrigin, new AbsoluteConstraints(8, 12, 72, 13));
        getContentPane().add(lblDestination, new AbsoluteConstraints(8, 44, 72, 13));

        getContentPane().add(btnOk, new AbsoluteConstraints(232, 88, 75, 25));
        getContentPane().add(btnCancel, new AbsoluteConstraints(152, 88, 75, 25));

        //pack();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private class ClickHandler">
    private class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == btnOk){
                // <editor-fold defaultstate="collapsed" desc="btnOk Handler">
                if ((edtOrigin.getText().length()==0) || (edtDestination.getText().length()==0)){
                    String Error = "";
                    if (edtOrigin.getText().length()==0)
                        Error = Error + "Origin is Empty!\n";
                    if (edtDestination.getText().length()==0)
                        Error = Error + "Destination is Empty!\n";
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
