package viniciuslambardozzi.threadsarefunawayfromyou.gui;

import viniciuslambardozzi.threadsarefunawayfromyou.core.Interval;
import viniciuslambardozzi.threadsarefunawayfromyou.core.PrimeFinder;
import viniciuslambardozzi.threadsarefunawayfromyou.core.util.*;
import viniciuslambardozzi.threadsarefunawayfromyou.core.util.Timer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;

public class GuiPrimeFinder extends JFrame {

	private JPanel contentPane;
	private JTextField txtStart;
	private JTextField txtSize;
	private JTextField txtNumberOfThreads;
	private JTextField txtStep;

	private JTextArea txtrPrimeFinderOutput;

	public void logToOutput(String str)
	{
		if(!str.equals("\n"))
		{
			txtrPrimeFinderOutput.append("[" + System.currentTimeMillis() +"]: ");
		}
		txtrPrimeFinderOutput.append(str + "\n");
		txtrPrimeFinderOutput.setCaretPosition(txtrPrimeFinderOutput.getDocument().getLength());
	}

	/**
	 * Launch the application.
	 */
	public static GuiPrimeFinder frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GuiPrimeFinder();
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
		
		txtrPrimeFinderOutput = new JTextArea();
		txtrPrimeFinderOutput.setFont(new Font("Monospaced", Font.PLAIN, 10));
		DefaultCaret caret = (DefaultCaret) txtrPrimeFinderOutput.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		txtrPrimeFinderOutput.setEditable(false);
		txtrPrimeFinderOutput.setToolTipText("Output");
		txtrPrimeFinderOutput.setBounds(10, 235, 324, 196);
		txtrPrimeFinderOutput.setLineWrap(true);
		txtrPrimeFinderOutput.setWrapStyleWord(true);

		JScrollPane pane = new JScrollPane(txtrPrimeFinderOutput);
		pane.setBounds(10, 235, 324, 196);
		pane.setAutoscrolls(true);

		contentPane.add(pane);

		JButton btnStart = new JButton("Start!");		
		btnStart.setBounds(245, 487, 89, 23);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Close!");
		btnStop.setBounds(146, 487, 89, 23);
		contentPane.add(btnStop);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 442, 324, 14);
		contentPane.add(progressBar);
		
		txtStep = new JTextField();
		txtStep.setHorizontalAlignment(SwingConstants.CENTER);
		txtStep.setToolTipText("Step size");
		txtStep.setText("2");
		txtStep.setBounds(184, 61, 150, 20);
		contentPane.add(txtStep);
		txtStep.setColumns(10);
		
		chckbxMultiThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chckbxMultiThread.isSelected())
				{
					txtNumberOfThreads.setText("1");
					txtNumberOfThreads.setEnabled(false);
				}else
				{
					txtNumberOfThreads.setText("4");
					txtNumberOfThreads.setEnabled(true);
				}
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* This is when the actual prime finder runs */
				
				btnStart.setEnabled(false);

				try
				{
					Timer timer = new Timer();


					timer.start();
					Interval interval = new Interval(new BigInteger(txtStart.getText()), new BigInteger(txtSize.getText()), new BigInteger(txtStep.getText()));
					logToOutput("Successfully generated interval.\n");

					PrimeFinder finder = new PrimeFinder();
					int threadNumber = Integer.parseInt(txtNumberOfThreads.getText());

					finder.find(interval, threadNumber);

					LinkedList<BigInteger> primes = finder.getPrimesFound();
					timer.stop();

					logToOutput("Primes found: " + String.valueOf(primes.size()));
					logToOutput("Total time elapsed: " + timer.getElapsedTime());
					logToOutput("\n");

					if(chckbxLogPrimesFound.isSelected())
					{
						try
						{
							PrimeFileOutput.writePrimesToFile(primes, "Last-primes-found");
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}

					if(chckbxLogPerformance.isSelected())
					{
						try
						{
							PerformanceFileOutput.writePerformanceToFile(interval, threadNumber, primes.size(), timer.getElapsedTime(), "Performance-log");
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}

				}catch (NumberFormatException numberFormat)
				{
					txtrPrimeFinderOutput.setText("All the interval settings accept only numbers.");
					btnStart.setEnabled(true);
				}
				btnStart.setEnabled(true);
			}
		});
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
				
			}
		});


	}
}
