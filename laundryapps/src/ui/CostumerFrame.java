package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import model.Costumer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTable tableCostumers;

    CostumerRepo custRepo = new CostumerRepo();
    List<Costumer> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadTable() {
        ls = custRepo.show();
        String[] columns = {"ID", "Name", "Address", "Phone"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (ls != null) {
            for (Costumer c : ls) {
                Object[] row = new Object[] {
                    c.getId(),
                    c.getNama(),
                    c.getAlamat(),
                    c.getNomor_hp()
                };
                model.addRow(row);
            }
        }
        tableCostumers.setModel(model);
        tableCostumers.getTableHeader().setVisible(true);
    }

    public void reset() {
        txtName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        id = null;
    }

    public CostumerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 378);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(23, 38, 49, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Address");
        lblNewLabel_1.setBounds(22, 63, 63, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Phone");
        lblNewLabel_2.setBounds(23, 87, 62, 14);
        contentPane.add(lblNewLabel_2);

        txtName = new JTextField();
        txtName.setBounds(97, 35, 297, 20);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setBounds(97, 60, 297, 20);
        contentPane.add(txtAddress);

        txtPhone = new JTextField();
        txtPhone.setColumns(10);
        txtPhone.setBounds(97, 84, 297, 20);
        contentPane.add(txtPhone);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(128, 255, 128));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name wajib diisi");
                    return;
                }
                String phone = txtPhone.getText().trim();
                if (!phone.isEmpty() && !phone.matches("[0-9+\\- ]+")) {
                    JOptionPane.showMessageDialog(null, "Phone hanya boleh angka/spasi/+-");
                    return;
                }

                Costumer c = new Costumer();
                c.setNama(txtName.getText().trim());
                c.setAlamat(txtAddress.getText().trim());
                c.setNomor_hp(txtPhone.getText().trim());
                custRepo.save(c);
                reset();
                loadTable();
            }
        });
        btnSave.setForeground(new Color(0, 0, 0));
        btnSave.setBounds(23, 127, 89, 23);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu");
                    return;
                }
                if (txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name wajib diisi");
                    return;
                }
                Costumer c = new Costumer();
                c.setId(id);
                c.setNama(txtName.getText().trim());
                c.setAlamat(txtAddress.getText().trim());
                c.setNomor_hp(txtPhone.getText().trim());
                custRepo.update(c);
                reset();
                loadTable();
            }
        });
        btnUpdate.setBackground(new Color(128, 128, 255));
        btnUpdate.setForeground(new Color(0, 0, 0));
        btnUpdate.setBounds(123, 127, 89, 23);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    custRepo.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
                }
            }
        });
        btnDelete.setBackground(new Color(255, 128, 192));
        btnDelete.setForeground(new Color(0, 0, 0));
        btnDelete.setBounds(222, 127, 89, 23);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBackground(new Color(255, 255, 0));
        btnCancel.setForeground(new Color(0, 0, 0));
        btnCancel.setBounds(324, 127, 89, 23);
        contentPane.add(btnCancel);

        tableCostumers = new JTable();
        tableCostumers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableCostumers.getSelectedRow();
                if (r >= 0) {
                    id = tableCostumers.getValueAt(r, 0).toString();
                    txtName.setText(tableCostumers.getValueAt(r, 1).toString());
                    txtAddress.setText(tableCostumers.getValueAt(r, 2).toString());
                    txtPhone.setText(tableCostumers.getValueAt(r, 3).toString());
                }
            }
        });
        tableCostumers.setBounds(23, 161, 391, 169);
        contentPane.add(tableCostumers);
    }
}
