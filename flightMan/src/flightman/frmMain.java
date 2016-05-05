package flightman;

import java.text.*;
import javax.swing.*;
import java.util.Date;
import java.util.Date.*;
import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.table.*;
import org.netbeans.lib.awtextra.*;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;

public class frmMain extends FrameView{
    
    // <editor-fold defaultstate="collapsed" desc="variables declaration">
    private JPanel mainPanel;

    //  menu bar
    private JMenu mnuHelp;
    private JMenuItem mnuAbout;
    private JMenu mnuFile;
    private JMenuItem mnuExit;
    private JSeparator mnuSeparator1;
    
    //pnlButtons
    private JPanel pnlButtons;
    private JToggleButton btnReservation;
    private JToggleButton btnFlights;
    private JToggleButton btnCustomers;
    private JToggleButton btnExit;

    // Reservation Panel
    private JPanel pnlReservation;
    private JButton btnReservationRefresh;
    private JButton btnReservationAdd;
    private JButton btnReservationDelete;
    private JButton btnReservationEdit;
    private JScrollPane scrlReservation;
    private JTable tblReservation;
    private DsReservations dsReservation;

    // Flights Panel
    private JPanel pnlFlights;
    private JButton btnFlightsAdd;
    private JButton btnFlightsDelete;
    private JButton btnFlightsEdit;
    private JButton btnFlightsRefresh;
    private JScrollPane scrlFlights;
    private JTable tblFlights;
    private DsFlights dsFlights;

    // Customers Panel
    private JPanel pnlCustomers;
    private JButton btnCustomersAdd;
    private JButton btnCustomersDelete;
    private JButton btnCustomersEdit;
    private JButton btnCustomersRefresh;
    private JScrollPane scrlCustomers;
    private JTable tblCustomers;
    private DsCustomers dsCustomers;

    private Integer CustomerId;
    private Integer AccessType;

    // Other Forms
    private JDialog aboutBox;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public frmMain(...)">
    public frmMain(SingleFrameApplication app) {
        super(app);
        CustomerId=0;
        initializeComponents();
        if (!showLogin())
            getApplication().exit();
        FilterTables();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Init Components">
    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(625, 314));
        mainPanel.setLayout(new AbsoluteLayout());
        mainPanel.setMaximumSize(new Dimension(625, 314));
        mainPanel.setMinimumSize(new Dimension(625, 314));
        mainPanel.setSize(new Dimension(625, 314));
        this.getFrame().addWindowListener(new windowHandler());
        this.getFrame().setSize(new Dimension(625, 314));
        this.getFrame().setMinimumSize(new Dimension(615, 354));
        this.getFrame().setMaximumSize(new Dimension(615, 354));

        ClickHandler clickHandler = new ClickHandler();

        // <editor-fold defaultstate="collapsed" desc="Menubar">
        mnuHelp = new JMenu("Help");
        mnuAbout = new JMenuItem("About...");
        mnuAbout.addActionListener(clickHandler);

        mnuFile = new JMenu("File");
        mnuExit = new JMenuItem("Exit");
        mnuExit.addActionListener(clickHandler);
        
        mnuSeparator1 = new JSeparator();
        mnuExit.setMnemonic('X');

        //mnuExit.setAction(actionMap.get("quit"));
        //mnuAbout.setAction(actionMap.get("showAboutBox"));

        mnuHelp.add(mnuAbout);
        mnuFile.add(mnuSeparator1);
        mnuFile.add(mnuExit);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setName("menuBar");
        menuBar.add(mnuFile);
        menuBar.add(mnuHelp);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Button Panel">
        pnlButtons = new JPanel();
        pnlButtons.setBorder(BorderFactory.createEtchedBorder());
        pnlButtons.setMaximumSize(new java.awt.Dimension(81, 32767));
        pnlButtons.setMinimumSize(new java.awt.Dimension(81, 297));
        pnlButtons.setPreferredSize(new java.awt.Dimension(81, 297));
        //pnlButtons.setPreferredSize(new java.awt.Dimension(81, 297));
        pnlButtons.setLayout(new AbsoluteLayout());


        // Reservation Button
        btnReservation = new JToggleButton();
        btnReservation.setName("btnReservation");
        btnReservation.setIcon(new ImageIcon(getClass().getResource("/flightman/resources/book.png")));
        btnReservation.addActionListener(clickHandler);

        // Flight Button
        btnFlights = new JToggleButton();
        btnFlights.setName("btnFlights");
        btnFlights.setIcon(new ImageIcon(getClass().getResource("/flightman/resources/airplane.png")));
        btnFlights.addActionListener(clickHandler);

        // Customer Button
        btnCustomers = new JToggleButton();
        btnCustomers.setName("btnCustomers");
        btnCustomers.setIcon(new ImageIcon(getClass().getResource("/flightman/resources/user.png")));
        btnCustomers.addActionListener(clickHandler);

        // Exit Button
        btnExit = new JToggleButton();
        btnExit.setName("btnExit");
        btnExit.setIcon(new ImageIcon(getClass().getResource("/flightman/resources/exit.png")));
        btnExit.addActionListener(clickHandler);

        // Group the buttons so pressing each button will toggle other buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(btnReservation);
        buttonGroup.add(btnFlights);
        buttonGroup.add(btnCustomers);
        buttonGroup.add(btnExit);
        buttonGroup.setSelected(btnReservation.getModel(), true);

        // Add Buttons to button panel
        pnlButtons.add(btnReservation, new AbsoluteConstraints(8, 8, 64, 64));
        pnlButtons.add(btnCustomers, new AbsoluteConstraints(8, 152, 64, 64));
        pnlButtons.add(btnFlights, new AbsoluteConstraints(8, 80, 64, 64));
        pnlButtons.add(btnExit, new AbsoluteConstraints(8, 224, 64, 64));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Reservation Panel">
        pnlReservation = new JPanel();
        pnlReservation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlReservation.setLayout(new AbsoluteLayout());

        // Data Table
        
        dsReservation = new DsReservations();
        tblReservation = new JTable(dsReservation);
        tblReservation.setFillsViewportHeight(true);
        tblReservation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*
        tblReservation.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblReservation.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblReservation.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblReservation.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblReservation.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblReservation.getColumnModel().getColumn(5).setPreferredWidth(10);
        */
        
        scrlReservation = new JScrollPane(tblReservation);

        //Refresh Button
        btnReservationRefresh = new JButton();
        btnReservationRefresh.setText("Refresh");
        btnReservationRefresh.setToolTipText("Refresh Reservation Data");
        btnReservationRefresh.addActionListener(clickHandler);

        //Add Button
        btnReservationAdd = new JButton();
        btnReservationAdd.setText("New");
        btnReservationAdd.setToolTipText("Add A New Reservation");
        btnReservationAdd.addActionListener(clickHandler);

        //Edit Button
        btnReservationEdit = new JButton();
        btnReservationEdit.setText("Edit");
        btnReservationEdit.setToolTipText("Edit Current Reservation Record");
        btnReservationEdit.addActionListener(clickHandler);

        //Delete Button
        btnReservationDelete = new JButton();
        btnReservationDelete.setText("Delete");
        btnReservationDelete.setToolTipText("Delete Current Reservation Record");
        btnReservationDelete.addActionListener(clickHandler);

        //Add Buttons to panel
        pnlReservation.add(scrlReservation, new AbsoluteConstraints(8, 8, 505, 249));
        pnlReservation.add(btnReservationRefresh, new AbsoluteConstraints(200, 264, 73, 25));
        pnlReservation.add(btnReservationAdd, new AbsoluteConstraints(280, 264, 73, 25));
        pnlReservation.add(btnReservationEdit, new AbsoluteConstraints(360, 264, 73, 25));
        pnlReservation.add(btnReservationDelete, new AbsoluteConstraints(440, 264, 73, 25));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Flight Panel">
        pnlFlights = new JPanel();
        pnlFlights.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlFlights.setLayout(new AbsoluteLayout());

        // Data Table
        dsFlights = new DsFlights();
        tblFlights = new JTable(dsFlights);
        tblFlights.setFillsViewportHeight(true);
        tblFlights.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrlFlights = new JScrollPane(tblFlights);

        //Refresh Button
        btnFlightsRefresh = new JButton();
        btnFlightsRefresh.setText("Refresh");
        btnFlightsRefresh.setToolTipText("Refresh Flight Data");
        btnFlightsRefresh.addActionListener(clickHandler);

        //Add Button
        btnFlightsAdd = new JButton();
        btnFlightsAdd.setText("New");
        btnFlightsAdd.setToolTipText("Add A New Flight");
        btnFlightsAdd.addActionListener(clickHandler);
        
        //Edit Button
        btnFlightsEdit = new JButton();
        btnFlightsEdit.setText("Edit");
        btnFlightsEdit.setToolTipText("Edit Current Flight Record");
        btnFlightsEdit.addActionListener(clickHandler);

        //Delete Button
        btnFlightsDelete = new JButton();
        btnFlightsDelete.setText("Delete");
        btnFlightsDelete.setToolTipText("Delete Current Flight Record");
        btnFlightsDelete.addActionListener(clickHandler);

        //Add Buttons to panel
        pnlFlights.add(scrlFlights, new AbsoluteConstraints(8, 8, 505, 249));
        pnlFlights.add(btnFlightsRefresh, new AbsoluteConstraints(200, 264, 73, 25));
        pnlFlights.add(btnFlightsAdd, new AbsoluteConstraints(280, 264, 73, 25));
        pnlFlights.add(btnFlightsEdit, new AbsoluteConstraints(360, 264, 73, 25));
        pnlFlights.add(btnFlightsDelete, new AbsoluteConstraints(440, 264, 73, 25));
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Customer Panel">
        pnlCustomers = new JPanel();
        pnlCustomers.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlCustomers.setLayout(new AbsoluteLayout());

        // Data Table
        dsCustomers = new DsCustomers();
        tblCustomers = new JTable(dsCustomers);
        tblCustomers.setFillsViewportHeight(true);
        tblCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrlCustomers = new JScrollPane(tblCustomers);

        //Refresh Button
        btnCustomersRefresh = new JButton();
        btnCustomersRefresh.setText("Refresh");
        btnCustomersRefresh.setToolTipText("Refresh Customer Data");
        btnCustomersRefresh.addActionListener(clickHandler);


        //Add Button
        btnCustomersAdd = new JButton();
        btnCustomersAdd.setText("New");
        btnCustomersAdd.setToolTipText("Add A New Customer");
        btnCustomersAdd.addActionListener(clickHandler);

        //Edit Button
        btnCustomersEdit = new JButton();
        btnCustomersEdit.setText("Edit");
        btnCustomersEdit.setToolTipText("Edit Current Customer Record");
        btnCustomersEdit.addActionListener(clickHandler);

        //Delete Button
        btnCustomersDelete = new JButton();
        btnCustomersDelete.setText("Delete");
        btnCustomersDelete.setToolTipText("Delete Current Customer Record");
        btnCustomersDelete.addActionListener(clickHandler);

        //Add Buttons to panel
        pnlCustomers.add(scrlCustomers, new AbsoluteConstraints(8, 8, 505, 249));
        pnlCustomers.add(btnCustomersRefresh, new AbsoluteConstraints(200, 264, 73, 25));
        pnlCustomers.add(btnCustomersAdd, new AbsoluteConstraints(280, 264, 73, 25));
        pnlCustomers.add(btnCustomersEdit, new AbsoluteConstraints(360, 264, 73, 25));
        pnlCustomers.add(btnCustomersDelete, new AbsoluteConstraints(440, 264, 73, 25));
        // </editor-fold>

        // Add Panels to main form
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        // Hide Data panels in startup except reservation
        pnlFlights.setVisible(false);
        pnlCustomers.setVisible(false);

        mainPanel.add(pnlButtons, new AbsoluteConstraints(8, 8, 81, 297));
        mainPanel.add(pnlReservation, new AbsoluteConstraints(96, 8, 521, 297));
        mainPanel.add(pnlFlights, new AbsoluteConstraints(96, 8, 521, 297));
        mainPanel.add(pnlCustomers, new AbsoluteConstraints(96, 8, 521, 297));

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private void FilterTables()">
    private void FilterTables() {
        //Filter Content of tblReservation according to CustomerId
        RowFilter<Object, Object> reservationFilter = new RowFilter<Object, Object>() {
          public boolean include(Entry entry) {
            return (CustomerId == ((rCustomers) entry.getValue(1)).id) || (CustomerId==0);
          }
        };
        TableRowSorter<TableModel> reservationSorter = new TableRowSorter<TableModel>(dsReservation);
        reservationSorter.setRowFilter(reservationFilter);
        tblReservation.setRowSorter(reservationSorter);

        //Filter Content of tblCustomer according to CustomerId
        RowFilter<Object, Object> customerFilter = new RowFilter<Object, Object>() {
          public boolean include(Entry entry) {
            return (CustomerId == entry.getValue(0)) || (CustomerId==0);
          }
        };
        TableRowSorter<TableModel> customerSorter = new TableRowSorter<TableModel>(dsCustomers);
        customerSorter.setRowFilter(customerFilter);
        tblCustomers.setRowSorter(customerSorter);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private void showAboutBox()">
    private void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = FlightManApp.getApplication().getMainFrame();
            aboutBox = new DlgAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        FlightManApp.getApplication().show(aboutBox);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public boolean showLogin()">
    private boolean showLogin() {
        boolean result= false;
        DlgLogin dlgLogin = new DlgLogin(this.getFrame(),true);
        dlgLogin.setLocationRelativeTo(this.getFrame());

        // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
        dlgLogin.edtUserName.setText(null);
        dlgLogin.edtPassword.setText(null);
        // </editor-fold>

        FlightManApp.getApplication().show(dlgLogin);
        if (dlgLogin.modalResult==1){
            // Login
            if (dlgLogin.edtUserName.getText().equals("admin") && dlgLogin.edtPassword.getText().equals("123")){
                AccessType = 3;
                result = true;
            } else if (dlgLogin.edtUserName.getText().equals("operator") && dlgLogin.edtPassword.getText().equals("123")){
                AccessType = 2;
                result = true;
            } else {
                result = false;
                DsCustomers customers = new DsCustomers();
                for(int i= 0;i<customers.getRowCount();i++){
                    if (customers.getRowById(i).userName.equals(dlgLogin.edtUserName.getText()) &&
                        customers.getRowById(i).password.equals(dlgLogin.edtPassword.getText())){
                        CustomerId = customers.getRowById(i).id;
                        AccessType = 1;
                        result = true;
                    }
                }
            }
        } else if (dlgLogin.modalResult == 2) {
            // New Customer
            if (btnCustomersAddClick()){
                AccessType = 1;
                result = true;
            } else
                result = false;
        } else {
            //Cancel Pressed
            AccessType = 0;
            result = false;
        }
        dlgLogin.dispose();

        switch (AccessType){
            case 1:
                // Customer Access
                btnFlights.setEnabled(false);
                break;
            case 2:
                //Operator
                btnFlightsAdd.setEnabled(false);
                btnFlightsDelete.setEnabled(false);
                btnFlightsEdit.setEnabled(false);
                break;
            case 3:
                //Manager
                break;
            default:
                break;
        }

        return result;
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="public void btnReservationRefreshClick()">
    public void btnReservationRefreshClick() {
        dsReservation.Refresh();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnReservationAddClick()">
    public void btnReservationAddClick() {
        DlgReservation dlgReservation = new DlgReservation(this.getFrame(),true);
        dlgReservation.setLocationRelativeTo(this.getFrame());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
        //fill Flight combobox data
        for(int i=0;i < dsFlights.getRowCount();i++){
            dlgReservation.cbFlight.addItem(dsFlights.getComboItem(i));
        }
        dlgReservation.cbFlight.setEditable(true);
        dlgReservation.cbFlight.setSelectedItem(null);

        //fill Customer combobox data
        for(int i=0;i < dsCustomers.getRowCount();i++){
            dlgReservation.cbCustomer.addItem(dsCustomers.getComboItem(i));
        }
        dlgReservation.cbCustomer.setEditable(true);
        dlgReservation.cbCustomer.setSelectedItem(null);

        dlgReservation.edtBookingDate.setText(format.format(new Date()));
        dlgReservation.edtTravelDate.setText(format.format(new Date()));
        // </editor-fold>

        FlightManApp.getApplication().show(dlgReservation);
        if (dlgReservation.modalResult==1){
            //Add new Reservation if Ok pressed
            try{
                dsReservation.Insert(
                    dsCustomers.getRowByIndex(dlgReservation.cbCustomer.getSelectedIndex()).id,
                    dsFlights.getRowByIndex(dlgReservation.cbFlight.getSelectedIndex()).id,
                    new Date(dlgReservation.edtBookingDate.getText()),
                    new Date(dlgReservation.edtTravelDate.getText()),
                    dlgReservation.cbMeal.isSelected()
                );
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        dlgReservation.dispose();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnReservationEditClick()">
    public void btnReservationEditClick() {
        //Make sure a row is selected
        if (tblReservation.getSelectedRowCount()>0){
            rReservations row = dsReservation.getRowByIndex(tblReservation.convertRowIndexToModel(tblReservation.getSelectedRow()));
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            DlgReservation dlgReservation = new DlgReservation(this.getFrame(),true);
            dlgReservation.setLocationRelativeTo(this.getFrame());

            // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
            //fill Flight combobox data
            for(int i=0;i < dsFlights.getRowCount();i++){
                dlgReservation.cbFlight.addItem(dsFlights.getComboItem(i));
            }

            //fill Customer combobox data
            for(int i=0;i < dsCustomers.getRowCount();i++){
                dlgReservation.cbCustomer.addItem(dsCustomers.getComboItem(i));
            }

            dlgReservation.edtBookingDate.setText(format.format(row.bookingDate));
            dlgReservation.edtTravelDate.setText(format.format(row.travelingDate));
            
            for(int i=0;i < dlgReservation.cbCustomer.getItemCount();i++){
                if (((ComboItem)dlgReservation.cbCustomer.getItemAt(i)).id== row.customer.id ){
                    dlgReservation.cbCustomer.setSelectedIndex(i);
                }
            }
            dlgReservation.cbMeal.setSelected(row.mealOption);
            // </editor-fold>

            FlightManApp.getApplication().show(dlgReservation);
            if (dlgReservation.modalResult==1){
                //Update Record if Ok Pressed
                dsReservation.Edit(
                    row.id,
                    dsCustomers.getRowByIndex(dlgReservation.cbCustomer.getSelectedIndex()).id,
                    dsFlights.getRowByIndex(dlgReservation.cbFlight.getSelectedIndex()).id,
                    new Date(dlgReservation.edtBookingDate.getText()),
                    new Date(dlgReservation.edtTravelDate.getText()),
                    dlgReservation.cbMeal.isSelected()
                );
            }
            dlgReservation.dispose();
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to edit");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnReservationDeleteClick()">
    public void btnReservationDeleteClick() {
        //Make sure a row is selected
        if (tblReservation.getSelectedRowCount()>0){
            rReservations row = dsReservation.getRowByIndex(tblReservation.convertRowIndexToModel(tblReservation.getSelectedRow()));
            if (JOptionPane.showConfirmDialog(getFrame(),
                "This will delete selected record,\nAre you sure?",
                "Confirm",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
                )==0){
                //JOptionPane.showMessageDialog(this.getFrame(), row.id);
                dsReservation.Delete(row.id);
            }
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to Delete");
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="public void btnFlightsRefreshClick()">
    public void btnFlightsRefreshClick() {
        dsFlights.Refresh();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnFlightsAddClick()">
    public void btnFlightsAddClick() {
        DlgFlight dlgFlight = new DlgFlight(this.getFrame(),true);
        dlgFlight.setLocationRelativeTo(this.getFrame());

        // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
        dlgFlight.edtDestination.setText(null);
        dlgFlight.edtOrigin.setText(null);

        dlgFlight.cbAvailablity.setSelected(true);
        dlgFlight.cbStopOrTransit.setSelected(false);
        // </editor-fold>

        FlightManApp.getApplication().show(dlgFlight);
        if (dlgFlight.modalResult==1){
            //Add new Reservation if Ok pressed
            dsFlights.Insert(
                    0,
                    dlgFlight.edtOrigin.getText(),
                    dlgFlight.edtDestination.getText(),
                    dlgFlight.cbStopOrTransit.isSelected(),
                    dlgFlight.cbAvailablity.isSelected()
            );
        }
        dlgFlight.dispose();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnFlightsEditClick()">
    public void btnFlightsEditClick() {
        //Make sure a row is selected
        if (tblFlights.getSelectedRowCount()>0){
            rFlights row = dsFlights.getRowByIndex(tblFlights.convertRowIndexToModel(tblFlights.getSelectedRow()));
            DlgFlight dlgFlight = new DlgFlight(this.getFrame(),true);
            dlgFlight.setLocationRelativeTo(this.getFrame());

            // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
            dlgFlight.edtOrigin.setText(row.origin);
            dlgFlight.edtDestination.setText(row.destination);
            dlgFlight.cbStopOrTransit.setSelected(row.stopOrTransit);
            dlgFlight.cbAvailablity.setSelected(row.availablity);
            // </editor-fold>

            FlightManApp.getApplication().show(dlgFlight);
            if (dlgFlight.modalResult==1){
                //Update Record if Ok Pressed
                dsFlights.Edit(
                    row.id,
                    dlgFlight.edtOrigin.getText(),
                    dlgFlight.edtDestination.getText(),
                    dlgFlight.cbStopOrTransit.isSelected(),
                    dlgFlight.cbAvailablity.isSelected()
                );
            }
            dlgFlight.dispose();
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to edit");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnFlightsDeleteClick()">
    public void btnFlightsDeleteClick() {
        //Make sure a row is selected
        if (tblFlights.getSelectedRowCount()>0){
            rFlights row = dsFlights.getRowByIndex(tblFlights.convertRowIndexToModel(tblFlights.getSelectedRow()));
            if (JOptionPane.showConfirmDialog(getFrame(),
                "This will delete selected record,\nAre you sure?",
                "Confirm",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
                )==0){
                //JOptionPane.showMessageDialog(this.getFrame(), row.id);
                dsFlights.Delete(row.id);
            }
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to Delete");
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="public void btnCustomersRefreshClick()">
    public void btnCustomersRefreshClick() {
        dsCustomers.Refresh();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public boolean btnCustomersAddClick()">
    public boolean btnCustomersAddClick() {
        DlgCustomer dlgCustomer = new DlgCustomer(this.getFrame(),true);
        dlgCustomer.setLocationRelativeTo(this.getFrame());

        // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
        dlgCustomer.edtSSID.setText(null);
        dlgCustomer.edtFirstName.setText(null);
        dlgCustomer.edtLastName.setText(null);
        dlgCustomer.edtCreditCard.setText(null);
        dlgCustomer.edtCVC2.setText(null);
        dlgCustomer.edtUserName.setText(null);
        dlgCustomer.edtPassword.setText(null);
        // </editor-fold>

        FlightManApp.getApplication().show(dlgCustomer);
        if (dlgCustomer.modalResult==1){
            //Add new Reservation if Ok pressed
            CustomerId = dsCustomers.Insert(
                0,
                dlgCustomer.edtSSID.getText(),
                dlgCustomer.edtFirstName.getText(),
                dlgCustomer.edtLastName.getText(),
                dlgCustomer.edtCreditCard.getText(),
                dlgCustomer.edtCVC2.getText(),
                dlgCustomer.edtUserName.getText(),
                dlgCustomer.edtPassword.getText()
            );
            return true;
        } else {
            return false;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnCustomersEditClick()">
    public void btnCustomersEditClick() {
        //Make sure a row is selected
        if (tblCustomers.getSelectedRowCount()>0){
            rCustomers row = dsCustomers.getRowByIndex(tblCustomers.convertRowIndexToModel(tblCustomers.getSelectedRow()));
            DlgCustomer dlgCustomer = new DlgCustomer(this.getFrame(),true);
            dlgCustomer.setLocationRelativeTo(this.getFrame());

            // <editor-fold defaultstate="collapsed" desc="Initialize Data in dialog">
            dlgCustomer.edtSSID.setText(row.ssid);
            dlgCustomer.edtFirstName.setText(row.firstName);
            dlgCustomer.edtLastName.setText(row.lastName);
            dlgCustomer.edtCreditCard.setText(row.creditCard);
            dlgCustomer.edtCVC2.setText(row.cvc2);
            dlgCustomer.edtUserName.setText(row.userName);
            dlgCustomer.edtPassword.setText(row.password);
            // </editor-fold>

            FlightManApp.getApplication().show(dlgCustomer);
            if (dlgCustomer.modalResult==1){
                //Update Record if Ok Pressed
                dsCustomers.Edit(
                    row.id,
                    dlgCustomer.edtSSID.getText(),
                    dlgCustomer.edtFirstName.getText(),
                    dlgCustomer.edtLastName.getText(),
                    dlgCustomer.edtCreditCard.getText(),
                    dlgCustomer.edtCVC2.getText(),
                    dlgCustomer.edtUserName.getText(),
                    dlgCustomer.edtPassword.getText()
                );
            }
            dlgCustomer.dispose();
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to edit");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void btnCustomersDeleteClick()">
    public void btnCustomersDeleteClick() {
        //Make sure a row is selected
        if (tblCustomers.getSelectedRowCount()>0){
            rCustomers row = dsCustomers.getRowByIndex(tblCustomers.convertRowIndexToModel(tblCustomers.getSelectedRow()));
            if (JOptionPane.showConfirmDialog(getFrame(),
                "This will delete "+ row.firstName + " " + row.lastName + ",\nAre you sure?",
                "Confirm",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
                )==0){
                //JOptionPane.showMessageDialog(this.getFrame(), row.id);
                dsCustomers.Delete(row.id);
            }
        } else {
            JOptionPane.showMessageDialog(this.getFrame(),"Please select a record to Delete");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private void ApplicationExit()">
    private void ApplicationExit(){
         if (JOptionPane.showConfirmDialog(getFrame(),
            "This will exit from program,\nAre you sure?",
            "Confirm",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
            )==0)
        getApplication().exit();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private class ClickHandler implements ActionListener">
    private class ClickHandler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == mnuAbout){
                showAboutBox();

            } else if (e.getSource() == mnuExit){
                ApplicationExit();
            } else if (e.getSource() == btnCustomers){
                pnlReservation.setVisible(false);
                pnlFlights.setVisible(false);
                pnlCustomers.setVisible(true);
            } else if (e.getSource() == btnCustomersAdd){
                btnCustomersAddClick();
            } else if (e.getSource() == btnCustomersDelete){
                btnCustomersDeleteClick();
            } else if (e.getSource() == btnCustomersEdit){
                btnCustomersEditClick();
            } else if (e.getSource() == btnCustomersRefresh){
                btnCustomersRefreshClick();
            } else if (e.getSource() == btnFlights){
                pnlReservation.setVisible(false);
                pnlFlights.setVisible(true);
                pnlCustomers.setVisible(false);
            } else if (e.getSource() == btnFlightsAdd){
                btnFlightsAddClick();
            } else if (e.getSource() == btnFlightsDelete){
                btnFlightsDeleteClick();
            } else if (e.getSource() == btnFlightsEdit){
                btnFlightsEditClick();
            } else if (e.getSource() == btnFlightsRefresh){
                btnFlightsRefreshClick();
            } else if (e.getSource() == btnReservation){
                pnlReservation.setVisible(true);
                pnlFlights.setVisible(false);
                pnlCustomers.setVisible(false);
            } else if (e.getSource() == btnReservationAdd){
                btnReservationAddClick();
            } else if (e.getSource() == btnReservationDelete){
                btnReservationDeleteClick();
            } else if (e.getSource() == btnReservationEdit){
                btnReservationEditClick();
            } else if (e.getSource() == btnReservationRefresh){
                btnReservationRefreshClick();
            } else if (e.getSource() == btnExit){
                ApplicationExit();
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private class windowHandler implements WindowListener">
    private class windowHandler implements WindowListener {
        public void windowClosing(WindowEvent e){
            ApplicationExit();
        }

        public void windowd(WindowEvent e){
            //
        }

        public void windowOpened(WindowEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void windowClosed(WindowEvent e) {
           //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void windowIconified(WindowEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void windowDeiconified(WindowEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void windowActivated(WindowEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void windowDeactivated(WindowEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    // </editor-fold>

}
