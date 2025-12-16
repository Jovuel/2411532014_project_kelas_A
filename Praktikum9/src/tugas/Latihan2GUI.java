package tugas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Latihan2GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar1;
	private JProgressBar progressBar2;
	private JProgressBar progressBar3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Latihan2GUI frame = new Latihan2GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Latihan2GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Download Manager App");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(112, 10, 228, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("File 1");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(33, 62, 47, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("File 2");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(33, 94, 47, 24);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("File 3");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(33, 128, 47, 24);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Download");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(275, 170, 121, 20);
		contentPane.add(btnNewButton);

		progressBar1 = new JProgressBar(0, 100);
		progressBar1.setBounds(90, 62, 300, 24);
		progressBar1.setValue(0);
		contentPane.add(progressBar1);

		progressBar2 = new JProgressBar(0, 100);
		progressBar2.setBounds(90, 94, 300, 24);
		progressBar2.setValue(0);
		contentPane.add(progressBar2);

		progressBar3 = new JProgressBar(0, 100);
		progressBar3.setBounds(90, 128, 300, 24);
		progressBar3.setValue(0);
		contentPane.add(progressBar3);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				progressBar1.setValue(0);
				progressBar2.setValue(0);
				progressBar3.setValue(0);

				AtomicInteger finished = new AtomicInteger(0);

				Thread t1 = new Thread(() -> {
					for (int i = 10; i <= 100; i += 10) {
						final int val = i;
						System.out.println("File-1 progress: " + val + "%");
						SwingUtilities.invokeLater(() -> progressBar1.setValue(val));
						try {
							Thread.sleep(500);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					System.out.println("File-1 selesai diunduh!");
					if (finished.incrementAndGet() == 3) {
						SwingUtilities.invokeLater(() -> btnNewButton.setEnabled(true));
					}
				}, "File-1");

				Thread t2 = new Thread(() -> {
					for (int i = 10; i <= 100; i += 10) {
						final int val = i;
						System.out.println("File-2 progress: " + val + "%");
						SwingUtilities.invokeLater(() -> progressBar2.setValue(val));
						try {
							Thread.sleep(500);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					System.out.println("File-2 selesai diunduh!");
					if (finished.incrementAndGet() == 3) {
						SwingUtilities.invokeLater(() -> btnNewButton.setEnabled(true));
					}
				}, "File-2");

				Thread t3 = new Thread(() -> {
					for (int i = 10; i <= 100; i += 10) {
						final int val = i;
						System.out.println("File-3 progress: " + val + "%");
						SwingUtilities.invokeLater(() -> progressBar3.setValue(val));
						try {
							Thread.sleep(500);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					System.out.println("File-3 selesai diunduh!");
					if (finished.incrementAndGet() == 3) {
						SwingUtilities.invokeLater(() -> btnNewButton.setEnabled(true));
					}
				}, "File-3");

				t1.start();
				t2.start();
				t3.start();

				System.out.println("\nDownloading....");
			}
		});
	}
}
