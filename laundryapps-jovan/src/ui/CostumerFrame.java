package ui;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.CostumerRepo;
import model.Costumer;
import model.CustomerBuilder;
import table.TableCostumer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
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
    private JTextField txtEmail;
    private JTable tableCostumers;

    private String id = null;
    private List<Costumer> ls;
    private CostumerRepo custRepo = new CostumerRepo();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                    frame.loadTable(); // safe: komponen sudah dibuat di konstruktor
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadTable() {
        ls = custRepo.show();
        TableCostumer tc = new TableCostumer(ls);
        tableCostumers.setModel(tc);
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

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(23, 38, 49, 14);
        contentPane.add(lblName);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(22, 63, 63, 14);
        contentPane.add(lblAddress);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(23, 87, 62, 14);
        contentPane.add(lblPhone);

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

        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name wajib diisi");
                    return;
                }
                Costumer customer = new CustomerBuilder()
                .setNama(txtName.getText())
                .setAlamat(txtAddress.getText())
                .setHp(txtPhone.getText())
        //        .setEmail(txtEmail.getText())
                .build();
                
                custRepo.save(customer);
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
                try {
                	Costumer customer = new CustomerBuilder()
                            .setNama(txtName.getText())
                            .setAlamat(txtAddress.getText())
                            .setHp(txtPhone.getText())
                            //.setEmail(txtEmail.getText())
                            .build();
                	
                    custRepo.update(customer);
                    reset();
                    loadTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Gagal update: " + ex.getMessage());
                    ex.printStackTrace();
                }
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
                    int opsi = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (opsi == JOptionPane.YES_OPTION) {
                        try {
                            custRepo.delete(id);
                            reset();
                            loadTable();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
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
