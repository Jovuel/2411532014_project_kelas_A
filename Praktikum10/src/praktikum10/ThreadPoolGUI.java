package praktikum10;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolGUI extends JFrame {
    private JTextField threadCountField, taskCountField;
    private JButton startButton, clearButton;
    private JTextArea logArea;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JLabel statusLabel;
    private ExecutorService threadPool;

    public ThreadPoolGUI() {
        setTitle("Aplikasi ThreadPool dengan GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel pengaturan
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Jumlah Thread:"));
        threadCountField = new JTextField("3", 5);
        controlPanel.add(threadCountField);
        controlPanel.add(new JLabel("Jumlah Tugas:"));
        taskCountField = new JTextField("20", 5);
        controlPanel.add(taskCountField);

        startButton = new JButton("Mulai Proses");
        startButton.setBackground(new Color(46, 204, 113));
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startProcessing());
        controlPanel.add(startButton);

        clearButton = new JButton("Bersihkan Log");
        clearButton.addActionListener(e -> clearLog());
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.NORTH);

        // Panel tengah: status tugas dan log
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        centerPanel.add(new JScrollPane(taskList));

        logArea = new JTextArea();
        logArea.setEditable(false);
        centerPanel.add(new JScrollPane(logArea));

        add(centerPanel, BorderLayout.CENTER);

        // Status bar
        statusLabel = new JLabel("Log dibersihkan. Siap untuk proses baru.");
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void startProcessing() {
        try {
            int threadCount = Integer.parseInt(threadCountField.getText());
            int taskCount = Integer.parseInt(taskCountField.getText());

            if (threadCount < 1 || taskCount < 1) {
                JOptionPane.showMessageDialog(this,
                    "Jumlah thread dan tugas harus lebih dari 0!",
                    "Input Tidak Valid",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            startButton.setEnabled(false);
            taskListModel.clear();
            logArea.append("=== Memulai Proses Baru === \n");
            logArea.append("ThreadPool dibuat dengan " + threadCount + " worker threads\n\n");

            statusLabel.setText("Memproses " + taskCount + " tugas dengan " + threadCount + " threads...");

            threadPool = Executors.newFixedThreadPool(threadCount);

            for (int i = 1; i <= taskCount; i++) {
                taskListModel.addElement("Task #" + i + " - Waiting");
            }

            for (int i = 1; i <= taskCount; i++) {
                Task task = new Task(i, logArea, taskListModel);
                threadPool.execute(task);
            }

            new Thread(() -> {
                threadPool.shutdown();
                try {
                    if (threadPool.awaitTermination(5, TimeUnit.MINUTES)) {
                        SwingUtilities.invokeLater(() -> {
                            logArea.append("\n=== Semua tugas selesai ===\n");
                            statusLabel.setText("Semua tugas selesai!");
                            startButton.setEnabled(true);
                        });
                    }
                } catch (InterruptedException e) {
                    threadPool.shutdownNow();
                }
            }).start();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Masukkan angka yang valid!",
                "Input Tidak Valid",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearLog() {
        logArea.setText("");
        taskListModel.clear();
        statusLabel.setText("Log dibersihkan. Siap untuk proses baru.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadPoolGUI().setVisible(true);
        });
    }
}