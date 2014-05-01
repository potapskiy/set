package gui;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import logic.ISet;
import logic.SetFactory;

import org.w3c.dom.stylesheets.StyleSheetList;

import utils.Constants;
import utils.FileOperations;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainWindow instance = null;

	private JTextArea console;
	private JTextField setAField;
	private JTextField setBField;

	public static int CURRENT_SET_TYPE = Constants.LIST_TYPE;
	public static int CURRENT_DATA_TYPE = Constants.INT_TYPE;

	private ISet setA = null;
	private ISet setB = null;
	private int vectorSetLen = 0;

	private String fileNameASet;
	private String fileNameBSet;

	JLabel removeALabel;
	JLabel removeBLabel;

	public static void main(String[] args) throws HeadlessException,
			AWTException {

		MainWindow.getInstance();
	}

	protected MainWindow() throws HeadlessException, AWTException {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				createGUI();

				FontUIResource f = new FontUIResource(
						new Font("Verdana", 0, 12));
				Enumeration<Object> keys = UIManager.getDefaults().keys();
				while (keys.hasMoreElements()) {
					Object key = keys.nextElement();
					Object value = UIManager.get(key);
					if (value instanceof FontUIResource) {
						FontUIResource orig = (FontUIResource) value;
						Font font = new Font(f.getFontName(), orig.getStyle(),
								f.getSize());
						UIManager.put(key, new FontUIResource(font));
					}
				}

				try {
					javax.swing.UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(MainWindow.getInstance());

			}
		});

	}

	public static MainWindow getInstance() {
		if (instance == null) {
			try {
				instance = new MainWindow();
			} catch (Exception e) {
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
		addOptionPanel();
		addOperationsPanel();
		addConsolePanel();

	}

	private void addOptionPanel() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null/* new GridLayout(9, 1) */);
		optionsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Параметри"));
		optionsPanel.setBounds(5, 5, 260, 565);

		// ///////////////////////////////////////

		GridLayout typePanelLayout = new GridLayout(0, 1);
		JPanel typePanel = new JPanel();
		typePanel.setLayout(typePanelLayout);
		typePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Тип реалізації множини"));
		typePanel.setBounds(5, 20, 250, 100);

		JRadioButton listType = new JRadioButton("Список");
		JRadioButton vectorType = new JRadioButton("Характеристичний вектор");
		ButtonGroup setTypeGroup = new ButtonGroup();
		listType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("LIST");

			}
		});

		vectorType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Vector");

			}
		});

		listType.setSelected(true);
		setTypeGroup.add(listType);
		setTypeGroup.add(vectorType);

		typePanel.add(listType);
		typePanel.add(vectorType);

		// ///////////////////////////////

		GridLayout dataTypePanelLayout = new GridLayout(0, 2);

		JPanel dataTypePanel = new JPanel();
		dataTypePanel.setLayout(dataTypePanelLayout);
		dataTypePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Тип даних"));
		dataTypePanel.setBounds(5, 120, 250, 100);

		JRadioButton intType = new JRadioButton("Цілі");
		JRadioButton doubleType = new JRadioButton("Дробові");
		JRadioButton stringType = new JRadioButton("Рядки");
		JRadioButton charType = new JRadioButton("Символи");

		ButtonGroup dataTypeGroup = new ButtonGroup();
		intType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.INT_TYPE;
				fillSetA();
				fillSetB();
			}
		});

		doubleType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.DOUBLE_TYPE;
				fillSetA();
				fillSetB();
			}
		});
		stringType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.STRING_TYPE;
				fillSetA();
				fillSetB();
			}
		});

		charType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.CHAR_TYPE;
				fillSetA();
				fillSetB();
			}
		});

		intType.setSelected(true);
		dataTypeGroup.add(intType);
		dataTypeGroup.add(doubleType);
		dataTypeGroup.add(stringType);
		dataTypeGroup.add(charType);

		dataTypePanel.add(intType);
		dataTypePanel.add(doubleType);
		dataTypePanel.add(stringType);
		dataTypePanel.add(charType);

		// ////////////////////////////////////////

		// GridLayout dataTypePanelLayout = new GridLayout(0,2);

		JPanel filePanel = new JPanel();
		filePanel.setLayout(null);
		filePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Вхідні дані"));
		filePanel.setBounds(5, 220, 250, 150);
		// ///////////////////////////////////////
		Label setALabel = new Label("Множина A");
		setALabel.setBounds(5, 20, 100, 20);
		filePanel.add(setALabel);

		setAField = new JTextField();
		setAField.setBounds(5, 45, 150, 21);
		setAField.setEditable(false);
		filePanel.add(setAField);

		JButton setAFileButton = new JButton("Файл");
		setAFileButton.setBounds(155, 44, 70, 23);

		setAFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				FileDialog fileSave = new FileDialog(MainWindow.getInstance(),
						"Відкрити", FileDialog.LOAD);
				// FilenameFilter filter = new TxtFileFilter();

				fileSave.setFile("*.txt");
				// fileSave.setFilenameFilter(filter);

				int widthWindow = 800;
				int heightWindow = 600;

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				int X = (screen.width / 2) - (widthWindow / 2);
				int Y = (screen.height / 2) - (heightWindow / 2);

				fileSave.setLocation(X, Y);
				fileSave.setDirectory(".");

				fileSave.setVisible(true);

				fileNameASet = fileSave.getDirectory() + fileSave.getFile();
				fileNameASet = (fileNameASet.equals(".null")) ? ""
						: fileNameASet;

				fillSetA();

			}
		});

		ImageIcon icon = new ImageIcon("res/delete_16.png");
		removeALabel = new JLabel(icon);
		removeALabel.setToolTipText("Видалити множину");
		removeALabel.setEnabled(false);
		removeALabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setA = null;
				fileNameASet = "";
				setAField.setText("");
				removeALabel.setEnabled(false);
			}
		});
		removeALabel.setBounds(228, 47, icon.getIconWidth(),
				icon.getIconHeight());

		filePanel.add(setAFileButton);
		filePanel.add(removeALabel);
		
		// /////////////////////
		// ////////////
		// ////////////
		// ///////////
		
		Label setBLabel = new Label("Множина B");
		setBLabel.setBounds(5, 70, 100, 20);
		filePanel.add(setBLabel);

		setBField = new JTextField();
		setBField.setBounds(5, 95, 150, 21);
		setBField.setEditable(false);
		filePanel.add(setBField);

		JButton setBFileButton = new JButton("Файл");
		setBFileButton.setBounds(155, 94, 70, 23);

		setBFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				FileDialog fileSave = new FileDialog(MainWindow.getInstance(),
						"Відкрити", FileDialog.LOAD);
				fileSave.setFile("*.txt");
				
				int widthWindow = 800;
				int heightWindow = 600;

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				int X = (screen.width / 2) - (widthWindow / 2);
				int Y = (screen.height / 2) - (heightWindow / 2);

				fileSave.setLocation(X, Y);
				fileSave.setDirectory(".");

				fileSave.setVisible(true);

				fileNameBSet = fileSave.getDirectory() + fileSave.getFile();
				fileNameBSet = (fileNameBSet.equals(".null")) ? ""
						: fileNameBSet;

				fillSetB();

			}
		});

		removeBLabel = new JLabel(icon);
		removeBLabel.setToolTipText("Видалити множину");
		removeBLabel.setEnabled(false);
		removeBLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setB = null;
				fileNameBSet = "";
				setBField.setText("");
				removeBLabel.setEnabled(false);
			}
		});
		removeBLabel.setBounds(228, 97, icon.getIconWidth(),
				icon.getIconHeight());

		filePanel.add(setBFileButton);
		filePanel.add(removeBLabel);
		
		
		
		
		
		
		
		
		///////////////
		/////////////
		////////

		optionsPanel.add(typePanel);
		optionsPanel.add(dataTypePanel);
		optionsPanel.add(filePanel);

		this.add(optionsPanel);

	}

	protected void fillSetA() {
		if (!fileNameASet.isEmpty()) {
			setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
					vectorSetLen);
			try {
				setA = SetFactory.fillSetFromFile(setA, fileNameASet,
						CURRENT_DATA_TYPE);
			} catch (Exception e) {
				// fileNameASet = "";
				JOptionPane.showMessageDialog(null, "Помилка зчитування. "
						+ "Перевірте правильність типу даних", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		System.out.println(setA.toString());
		setAField.setText(fileNameASet);
		removeALabel.setEnabled(true);

	}
	
	
	protected void fillSetB() {
		if (!fileNameBSet.isEmpty()) {
			setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
					vectorSetLen);
			try {
				setB = SetFactory.fillSetFromFile(setB, fileNameBSet,
						CURRENT_DATA_TYPE);
			} catch (Exception e) {
				// fileNameASet = "";
				JOptionPane.showMessageDialog(null, "Помилка зчитування. "
						+ "Перевірте правильність типу даних", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		System.out.println(setB.toString());
		setBField.setText(fileNameBSet);
		removeBLabel.setEnabled(true);

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
		// consolePanel.setLayout(null/* new GridLayout(9, 1) */);
		consolePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Результат"));
		consolePanel.setBounds(527, 5, 263, 565);

		console = new JTextArea(31, 20);

		console.setBounds(5, 5, 260, 200);
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

				FileDialog fileSave = new FileDialog(MainWindow.getInstance(),
						"Зберегти", FileDialog.SAVE);
				// FilenameFilter filter = new TxtFileFilter();

				fileSave.setFile("*.txt");
				// fileSave.setFilenameFilter(filter);

				int widthWindow = 800;
				int heightWindow = 600;

				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				int X = (screen.width / 2) - (widthWindow / 2); // Center
																// horizontally.
				int Y = (screen.height / 2) - (heightWindow / 2); // Center
																	// vertically.

				// System.out.println(screen.width+" "+screen.height+"\n"+X+" "+Y);
				fileSave.setLocation(X, Y);
				fileSave.setDirectory(".");

				fileSave.setVisible(true);

				String fileName = fileSave.getDirectory() + fileSave.getFile();

				// System.out.println(fileName);
				// System.out.println(text);
				FileOperations.writeToFile(fileName, text);

				// System.out.println(text);
			}
		});

		consolePanel.add(scroll);

		consolePanel.add(saveButton);
		consolePanel.add(clearButton);
		this.add(consolePanel);
	}

}
