package salesInvoiceApp.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class linesTable extends AbstractTableModel {

    private ArrayList<Line> lines;
    private String[] lineTableColumns = {"Number","Item name","Item price","Item count","total"};

    public linesTable(ArrayList<Line> lines) {
        this.lines = lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return lineTableColumns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Line line = lines.get(rowIndex);
        switch (columnIndex){
            case 0: return line.getNum();
            case 1: return line.getItem();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.calculateLineTotal();
            default: return null;
        }
    }
}
