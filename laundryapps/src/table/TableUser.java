package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.User;

public abstract class TableUser extends AbstractTableModel {
	  List<User> ls;
	  private String[] columnNames = {"ID", "Name", "Username", "Password"};
	  public TableUser(List<User> ls) {
	    this.ls = ls;
	  }
	  
	  @Override
	  public int getRowCount() {
	    return ls.size();
	  }
	  
	  @Override
	  public int getColumnCount() {
	    return 4;
	  }
	  
	  @Override
	  public String getColumnName(int column) {
	    return columnNames[column];
	  }
	  
	  @Override
	  public Object getValueAt(int rowIndex, int columnIndex) {
	    User user = ls.get(rowIndex);
	    switch (columnIndex) {
	      case 0:
	        return user.getId();
	      case 1:
	        return user.getNama();
	      case 2:
	        return user.getUsername();
	      case 3:
	        return user.getPassword();
	      default:
	        return null;
	    }
	  }
}
