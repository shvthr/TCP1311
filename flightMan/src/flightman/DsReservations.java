package flightman;

import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class DsReservations extends AbstractTableModel {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    private String[] columnNames = {"Id","CustomerId", "FlightId", "BookingDate", "TravelingDate", "MealOption"};
    private Vector<rReservations> data = new Vector<rReservations>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DsReservations()">
    DsReservations(){
        ReadFile();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="protected void finalize()">
    protected void finalize() throws Throwable{
        WriteFile();
        super.finalize();
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="private void ReadFile()">
    private void ReadFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("reservation.csv")));
            String line = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            data.clear();

            while ((line=reader.readLine()) != null) {
                String[] chunks = line.split(",");
                if (chunks.length==6){
                    data.add(new rReservations(
                            Integer.parseInt(chunks[0]),
                            Integer.parseInt(chunks[1]),
                            Integer.parseInt(chunks[2]),
                            format.parse(chunks[3]),
                            format.parse(chunks[4]),
                            Boolean.parseBoolean(chunks[5])
                     ));
                }
            }
            reader.close();
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private void WriteFile()">
    private void WriteFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("reservation.csv")));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            for(int i=0;i<data.size();i++){
                String dataline = data.get(i).id + "," +
                data.get(i).customer.id + ","+
                data.get(i).flightId.id + "," +
                format.format(data.get(i).bookingDate) + "," +
                format.format(data.get(i).travelingDate) + "," +
                data.get(i).mealOption;
                writer.write(dataline + '\n');
            }
            writer.close();
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Refresh()">
    public void Refresh(){
        WriteFile();
        ReadFile();
        fireTableDataChanged();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void Insert(...)">
    public void Insert(int CustomerId, int FlightId, Date BookingDate, Date TravelingDate, boolean MealOption){
        // find last used Primary key
        int id=0;
        for(int i=0;i<data.size();i++){
            if (data.get(i).id> id)
                id = data.get(i).id;
        }
        // add 1 for new primary key
        id++;
        //insert the record
        data.add(new rReservations(id, CustomerId, FlightId, BookingDate, TravelingDate, MealOption));
        //write the data file and refresh table
        WriteFile();
        fireTableDataChanged();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void Edit(...)">
    public void Edit(int Id, int CustomerId, int FlightId, Date BookingDate, Date TravelingDate, boolean MealOption){
        // find the requested row
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id == Id) {
                Id = data.get(i).id;
                data.set(i, new rReservations(Id, CustomerId, FlightId, BookingDate, TravelingDate, MealOption));
                //write the data file and refresh table
                WriteFile();
                fireTableDataChanged();
                return;
            }
        }
        // data not found, thats why we are here
        JOptionPane.showMessageDialog(null, "Requested Data not found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void Delete(...)">
    public void Delete(int Id){
        // find last used Primary key
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id == Id) {
                Id = data.get(i).id;
                data.remove(i);
                //write the data file and refresh table
                WriteFile();
                fireTableDataChanged();
                return;
            }
        }
        // data not found, thats why we are here
        JOptionPane.showMessageDialog(null, "Requested Data not found", "Error", JOptionPane.ERROR_MESSAGE);
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="public int getId(...)">
    public int getId(int rowId){
        return data.get(rowId).id;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public rReservations getRowByIndex(...)">
    public rReservations getRowByIndex(int index){
        return data.get(index);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public rReservations getRowById(...)">
    public rReservations getRowById(int rowId){
        for(int i=0;i<data.size();i++){
            if (data.get(i).id==rowId)
                return data.get(i);
        }
        return new rReservations();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public ComboItem getComboItem(...)">
    public ComboItem getComboItem(int Row) {
        return null;
    }
    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="public int getColumnCount()">
    public int getColumnCount() {
        return columnNames.length;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public int getRowCount()">
    public int getRowCount() {
        return data.size();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public String getColumnName(...)">
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public Object getValueAt(...)">
    public Object getValueAt(int row, int col) {
        switch (col){
            case 0: return data.get(row).id;
            case 1: return data.get(row).customer;
            case 2: return data.get(row).flightId;
            case 3: return data.get(row).bookingDate;
            case 4: return data.get(row).travelingDate;
            case 5: return data.get(row).mealOption;
            default: return 0;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public Class getColumnClass()">
    // JTable uses this method to determine how to show column
    // e.g. boolean columns would contain text ("true"/"false"),
    // rather than a check box if we don't implement this method
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public boolean isCellEditable(...)">
    // Don't need to edit in table. we have another editor
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void setValueAt(...)">
    // Don't need to implement this method unless your table's data can change.
    @Override
    public void setValueAt(Object value, int row, int col) {
        //data[row][col] = value;
        //fireTableCellUpdated(row, col);
    }
    // </editor-fold>
}
