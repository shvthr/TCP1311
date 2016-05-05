package flightman;

import java.util.*;
import java.io.*;
import java.text.*;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class DsFlights extends AbstractTableModel {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    private String[] columnNames = {"Id", "Origin", "Destination", "Stop/Transit", "Availablity"};
    private Vector<rFlights> data = new Vector<rFlights>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DsFlights()">
    DsFlights(){
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
            BufferedReader reader = new BufferedReader(new FileReader(new File("flights.csv")));
            String line = null;
            data.clear();

            while ((line=reader.readLine()) != null) {
                String[] chunks = line.split(",");
                if (chunks.length==5){
                    data.add(new rFlights(
                            Integer.parseInt(chunks[0]),
                            chunks[1],
                            chunks[2],
                            Boolean.parseBoolean(chunks[3]),
                            Boolean.parseBoolean(chunks[4])
                     ));
                }
            }
            reader.close();  // Close to unlock.
        } catch (Exception e)  {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="private void WriteFile()">
    private void WriteFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("flights.csv")));
            for(int i=0;i<data.size();i++){
                String dataline = data.get(i).id + "," +
                data.get(i).origin + ","+
                data.get(i).destination + "," +
                data.get(i).stopOrTransit + "," +
                data.get(i).availablity;
                writer.write(dataline + '\n');
                //JOptionPane.showMessageDialog(null, dataline, "Error", JOptionPane.ERROR_MESSAGE);
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
    public void Insert(int Id, String Origin, String Destination, boolean StopOrTransit, boolean Availablity){
        // find last used Primary key
        int id = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id > id) {
                id = data.get(i).id;
            }
        }
        // add 1 for new primary key
        id++;
        //insert the record
        data.add(new rFlights(id,Origin,Destination,StopOrTransit,Availablity));
        //write the data file and refresh table
        WriteFile();
        fireTableDataChanged();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public void Edit(...)">
    public void Edit(int Id, String Origin, String Destination, boolean StopOrTransit, boolean Availablity){
        // find the requested row
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).id == Id) {
                Id = data.get(i).id;
                data.set(i, new rFlights(Id,Origin,Destination,StopOrTransit,Availablity));
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

    // <editor-fold defaultstate="collapsed" desc="public rFlights getRowByIndex(...)">
    public rFlights getRowByIndex(int index){
        return data.get(index);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public rFlights getRowById(...)">
    public rFlights getRowById(int rowId){
        for(int i=0;i<data.size();i++){
            if (data.get(i).id==rowId)
                return data.get(i);
        }
        return new rFlights();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="public ComboItem getComboItem(...)">
    public ComboItem getComboItem(int Row) {
        return new ComboItem(
            Integer.parseInt(this.getValueAt(Row, 0).toString()),
            this.getValueAt(Row, 1).toString() +
            " - " +
            this.getValueAt(Row, 2).toString()
        );
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
            case 1: return data.get(row).origin;
            case 2: return data.get(row).destination;
            case 3: return data.get(row).stopOrTransit;
            case 4: return data.get(row).availablity;
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
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
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
