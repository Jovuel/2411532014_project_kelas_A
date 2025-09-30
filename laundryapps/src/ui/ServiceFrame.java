package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import model.Service;

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

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtJenis;
    private JTextField txtHarga;
    private JTextField txtStatus;
    private JTable tableServices;

    ServiceRepo srvRepo = new ServiceRepo();
    List<Service> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServiceFrame frame = new ServiceFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void loadTable() {
        ls = srvRepo.show();
        String[] columns = {"ID", "Jenis", "Harga", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        if (ls != null) {
            for (Service s : ls) {
                Object[] row = new Object[] {
                    s.getId(),
                    s.getJenis(),
                    s.getHarga(),
                    s.getStatus()
                };
                model.addRow(row);
            }
        }
        tableServices.setModel(model);
        tableServices.getTableHeader().setVisible(true);
    }

    public void reset() {
        txtJenis.setText("");
        txtHarga.setText("");
        txtStatus.setText("");
        id = null;
    }

    public ServiceFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 378);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Jenis");
        lblNewLabel.setBounds(23, 38, 49, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Harga");
        lblNewLabel_1.setBounds(22, 63, 63, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Status");
        lblNewLabel_2.setBounds(23, 87, 62, 14);
        contentPane.add(lblNewLabel_2);

        txtJenis = new JTextField();
        txtJenis.setBounds(97, 35, 297, 20);
        contentPane.add(txtJenis);
        txtJenis.setColumns(10);

        txtHarga = new JTextField();
        txtHarga.setColumns(10);
        txtHarga.setBounds(97, 60, 297, 20);
        contentPane.add(txtHarga);

        txtStatus = new JTextField();
        txtStatus.setColumns(10);
        txtStatus.setBounds(97, 84, 297, 20);
        contentPane.add(txtStatus);

        JButton btnSave = new JButton("Save");
        btnSave.setBackground(new Color(128, 255, 128));
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtJenis.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Jenis wajib diisi");
                    return;
                }
                int harga = 0;
                try {
                    harga = Integer.parseInt(txtHarga.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harga harus angka");
                    return;
                }
                
                String sTxt = txtStatus.getText().trim().toLowerCase();
                boolean status = (sTxt.equals("true") || sTxt.equals("1") || sTxt.equals("yes") || sTxt.equals("on") || sTxt.equals("aktif"));

                Service s = new Service();
                s.setJenis(txtJenis.getText().trim());
                s.setHarga(harga);
                s.setStatus(status);
                srvRepo.save(s);
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
                if (txtJenis.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Jenis wajib diisi");
                    return;
                }
                int harga = 0;
                try {
                    harga = Integer.parseInt(txtHarga.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harga harus angka");
                    return;
                }
                String sTxt = txtStatus.getText().trim().toLowerCase();
                boolean status = (sTxt.equals("true") || sTxt.equals("1") || sTxt.equals("yes") || sTxt.equals("on") || sTxt.equals("aktif"));

                Service s = new Service();
                s.setId(id);
                s.setJenis(txtJenis.getText().trim());
                s.setHarga(harga);
                s.setStatus(status);
                srvRepo.update(s);
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
                    srvRepo.delete(id);
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

        tableServices = new JTable();
        tableServices.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableServices.getSelectedRow();
                if (r >= 0) {
                    id = tableServices.getValueAt(r, 0).toString();
                    txtJenis.setText(tableServices.getValueAt(r, 1).toString());
                    txtHarga.setText(tableServices.getValueAt(r, 2).toString());
                    txtStatus.setText(tableServices.getValueAt(r, 3).toString());
                }
            }
        });
        tableServices.setBounds(23, 161, 391, 169);
        contentPane.add(tableServices);
    }
}
