package viniciuslambardozzi.threadsarefunawayfromyou.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPrimeFinder extends JFrame {

	private JPanel contentPane;
	private JTextField txtStart;
	private JTextField txtSize;
	private JTextField txtNumberOfThreads;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrimeFinder frame = new GuiPrimeFinder();
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
	public GuiPrimeFinder() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setTitle("PrimeFinder");
		setResizable(false);
		setAutoRequestFocus(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prime Finder Interval");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 134, 14);
		contentPane.add(lblNewLabel);
		
		txtStart = new JTextField();
		txtStart.setText("1");
		txtStart.setHorizontalAlignment(SwingConstants.CENTER);
		txtStart.setToolTipText("Start");
		txtStart.setBounds(10, 30, 150, 20);
		contentPane.add(txtStart);
		txtStart.setColumns(10);
		
		txtSize = new JTextField();
		txtSize.setToolTipText("Size");
		txtSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtSize.setText("1000");
		txtSize.setBounds(184, 30, 150, 20);
		contentPane.add(txtSize);
		txtSize.setColumns(10);
		
		JLabel lblThreadSettings = new JLabel("Thread Settings");
		lblThreadSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblThreadSettings.setBounds(10, 105, 97, 14);
		contentPane.add(lblThreadSettings);
		
		JCheckBox chckbxMultiThread = new JCheckBox("Multi Thread");
		chckbxMultiThread.setSelected(true);
		chckbxMultiThread.setBounds(10, 126, 150, 23);
		contentPane.add(chckbxMultiThread);
		
		txtNumberOfThreads = new JTextField();
		txtNumberOfThreads.setToolTipText("Number of threads");
		txtNumberOfThreads.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumberOfThreads.setText("4");
		txtNumberOfThreads.setBounds(184, 127, 150, 20);
		contentPane.add(txtNumberOfThreads);
		txtNumberOfThreads.setColumns(10);
		
		JLabel lblOutputSettings = new JLabel("File Output Settings");
		lblOutputSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOutputSettings.setBounds(10, 160, 134, 14);
		contentPane.add(lblOutputSettings);
		
		JCheckBox chckbxLogPerformance = new JCheckBox("Log Performance");
		chckbxLogPerformance.setBounds(10, 181, 150, 23);
		contentPane.add(chckbxLogPerformance);
		
		JCheckBox chckbxLogPrimesFound = new JCheckBox("Log Primes Found");
		chckbxLogPrimesFound.setSelected(true);
		chckbxLogPrimesFound.setBounds(184, 181, 150, 23);
		contentPane.add(chckbxLogPrimesFound);
		
		JTextArea txtrPrimeFinderOutput = new JTextArea();
		txtrPrimeFinderOutput.setToolTipText("Output");
		txtrPrimeFinderOutput.setEditable(false);
		txtrPrimeFinderOutput.setBounds(10, 235, 324, 196);
		contentPane.add(txtrPrimeFinderOutput);
		
		JButton btnStart = new JButton("Start!");		
		btnStart.setBounds(245, 487, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop!");		
		btnStop.setBounds(146, 487, 89, 23);
		btnStop.setEnabled(false);
		contentPane.add(btnStop);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 442, 324, 14);
		contentPane.add(progressBar);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setToolTipText("Step size");
		textField.setText("2");
		textField.setBounds(184, 61, 150, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		chckbxMultiThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chckbxMultiThread.isSelected())
				{
					txtNumberOfThreads.setEnabled(false);
				}else
				{
					txtNumberOfThreads.setEnabled(true);
				}
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO on start button clicked
				
				btnStart.setEnabled(false);
				btnStop.setEnabled(true);				
				
			}
		});
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO on stop button clicked
				
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				
			}
		});
		
	}
}
