package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Service;

public class TableService extends AbstractTableModel {
    private List<Service> list;
    private String[] columns = {"ID", "Jenis", "Harga", "Status"};

    public TableService(List<Service> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Service s = list.get(row);
        switch (col) {
            case 0: return s.getId();
            case 1: return s.getJenis();
            case 2: return s.getHarga();
            case 3: return s.getStatus();
            default: return null;
        }
    }
}
