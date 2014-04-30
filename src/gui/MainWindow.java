package gui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FilenameFilter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import utils.FileOperations;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainWindow instance = null;
	
	private JTextArea console;

	public static void main(String[] args) throws HeadlessException,
			AWTException {

		MainWindow.getInstance();
	}

	protected MainWindow() throws HeadlessException, AWTException {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				createGUI();

				SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());

			}
		});

	}

	public static MainWindow getInstance() {
		if (instance == null) {
			try {
				instance = new MainWindow();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	public void createGUI() {

		setLocationRelativeTo(null);
		setTitle("АТД Множина");

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int widthWindow = 800;
		int heightWindow = 600;
		this.setSize(widthWindow, heightWindow);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
		int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.

		setLocation(X, Y);

		getContentPane().setLayout(null);
		setResizable(false);

		addComponents();
		this.update(getGraphics());
		
		this.setVisible(true);

	}

	private void addComponents() {
		//addOptionPanel();
		//addOperationsPanel();
		addConsolePanel();
	
	}

	private void addOptionPanel() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null/* new GridLayout(9, 1) */);
		optionsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Параметри"));
		optionsPanel.setBounds(5, 5, 260, 565);
		

		this.add(optionsPanel);
		

		
	}

	private void addOperationsPanel() {
		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(null/* new GridLayout(9, 1) */);
		operationsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Операції"));
		operationsPanel.setBounds(265, 5, 263, 565);
		
		this.add(operationsPanel);
		
		
	}

	private void addConsolePanel() {
		JPanel consolePanel = new JPanel();
		//consolePanel.setLayout(null/* new GridLayout(9, 1) */);
		consolePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Результат"));
		consolePanel.setBounds(527, 5, 263, 565);
		
		
		console = new JTextArea(31,20);
		console.setEditable(true); // set textArea non-editable
		
		JScrollPane scroll = new JScrollPane(console);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    
	    JButton clearButton = new JButton("Очистити");
	    clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				console.setText("");
				
				
			}
		});
	    JButton saveButton = new JButton("Зберегти");
	    saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = console.getText();
				
				FileDialog fileSave = new FileDialog(MainWindow.getInstance(), "Зберегти", FileDialog.SAVE);
				//FilenameFilter filter = new TxtFileFilter();
				
				fileSave.setFile("*.txt");
				//fileSave.setFilenameFilter(filter);

				int widthWindow = 800;
		    	int heightWindow = 600;

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		    	int X = (screen.width / 2) - (widthWindow / 2); // Center horizontally.
		    	int Y = (screen.height / 2) - (heightWindow / 2); // Center vertically.
				fileSave.setLocation(X, Y);
				fileSave.setDirectory(".");

				fileSave.setVisible(true);
				
				String fileName = fileSave.getDirectory() + fileSave.getFile();
				
				
				System.out.println(fileName);
				
				System.out.println(text);
				FileOperations.writeToFile(fileName, text);
				
				//System.out.println(text);
			}
		});
	    
	    consolePanel.add(scroll);
	   
	    consolePanel.add(saveButton);
	    consolePanel.add(clearButton);
		this.add(consolePanel);
	}

}
