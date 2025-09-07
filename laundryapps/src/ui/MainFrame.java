package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(29, 23, 186, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("Pengguna");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3.setBounds(29, 141, 123, 48);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("Keluar");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_6.setBounds(29, 200, 359, 40);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_3_1 = new JButton("Pesanan");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1.setBounds(29, 82, 123, 48);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("Layanan");
		btnNewButton_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1_1.setBounds(166, 82, 123, 48);
		contentPane.add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_1_1 = new JButton("Pelanggan");
		btnNewButton_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1_1_1.setBounds(299, 82, 123, 48);
		contentPane.add(btnNewButton_3_1_1_1);
		
		JButton btnNewButton_3_1_1_1_1 = new JButton("Laporan");
		btnNewButton_3_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1_1_1_1.setBounds(166, 141, 123, 48);
		contentPane.add(btnNewButton_3_1_1_1_1);
		
		JButton btnNewButton_3_1_1_1_1_1 = new JButton("Profile");
		btnNewButton_3_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_3_1_1_1_1_1.setBounds(299, 141, 123, 48);
		contentPane.add(btnNewButton_3_1_1_1_1_1);

	}

}
