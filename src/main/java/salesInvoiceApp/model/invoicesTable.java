package salesInvoiceApp.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class invoicesTable extends AbstractTableModel {
    private ArrayList<Invoice> invoices;
    private String[] invoiceTableColumns = {"Number","Date","Customer","Total"};

    public invoicesTable(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int columnNumber){
        return invoiceTableColumns[columnNumber];
    }

    @Override
    public Object getValueAt(int i, int j) {
        Invoice invoice = invoices.get(i);
        switch (j){
            case 0: return invoice.getNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomer();
            case 3: return invoice.calculateTotal();
            default: return "";
        }
    }

}
