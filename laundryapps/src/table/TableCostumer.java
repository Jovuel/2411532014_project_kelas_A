package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Costumer;

public class TableCostumer extends AbstractTableModel {
    private List<Costumer> list;
    private String[] columns = {"ID", "Nama", "Alamat", "Nomor HP"};

    public TableCostumer(List<Costumer> list) {
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
        Costumer c = list.get(row);
        switch (col) {
            case 0: return c.getId();
            case 1: return c.getNama();
            case 2: return c.getAlamat();
            case 3: return c.getNomor_hp();
            default: return null;
        }
    }
}
