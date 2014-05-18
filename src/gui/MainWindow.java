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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
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
import logic.Pair;
import logic.SetFactory;
import logic.list.LinkedListImp;

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

	public static int CURRENT_ACTIVE_SET = Constants.SET_A;
	public static int CURRENT_VAL_OPERATION;

	private ISet setA = null;
	private ISet setB = null;
	private int vectorSetLen = 0;
	public static LinkedListImp UNIVERSUM_SET;

	private String fileNameASet = "";
	private String fileNameBSet = "";

	JLabel removeALabel;
	JLabel removeBLabel;
	JLabel univSetLenLabel;
	JButton univSetOkButton;

	JButton okButton;
	JTextField valueField;
	NumberField univSetLenField;
	JPanel valuePanel;
	
	
	JRadioButton intType;
	JRadioButton doubleType; 
	JRadioButton stringType; 
	JRadioButton charType;
	JButton setAFileButton;
	JButton setBFileButton;

//	public static void main(String[] args) throws HeadlessException,
//			AWTException {
//
//		MainWindow.getInstance();
//	}

	protected MainWindow() throws HeadlessException, AWTException {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {

				createGUI();

				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);

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

				CURRENT_SET_TYPE = Constants.LIST_TYPE;

				UNIVERSUM_SET = null;

				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);

				univSetLenLabel.setEnabled(false);
				univSetLenField.setEnabled(false);
				univSetOkButton.setEnabled(false);

			}
		});

		vectorType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Vector");
				UNIVERSUM_SET = null;

				univSetLenLabel.setEnabled(true);
				univSetLenField.setEnabled(true);
				univSetOkButton.setEnabled(true);
				
				optionsEnable(false);

			}
		});

		JPanel univSetPanel = new JPanel();
		univSetPanel.setLayout(null);

		univSetLenLabel = new JLabel("Потужність:");
		univSetLenLabel.setBounds(5, 0, 100, 25);

		univSetLenField = new NumberField(6);
		univSetLenField.setText("100");
		univSetLenField.setBounds(95, 3, 85, 20);

		Icon applyIcon = null;
		
		String path = "res/apply_20.png";
		
		try{
			applyIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
		}catch(Exception e){
			applyIcon = new ImageIcon("res/apply_20.png");
		}
		univSetOkButton = new JButton(applyIcon);
		univSetOkButton.setBounds(185, 2, 50, 22);
		univSetOkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("OK");

				int len = 0;
				try {
					len = Integer.valueOf(univSetLenField.getText());
				} catch (Exception e) {
					len = 0;
				}

				vectorSetLen = len;
				UNIVERSUM_SET = null;

				CURRENT_SET_TYPE = Constants.CHARACTER_VACTOR_TYPE;

				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				
				optionsEnable(true);
				
				System.out.println(len);

			}
		});

		univSetLenLabel.setEnabled(false);
		univSetLenField.setEnabled(false);
		univSetOkButton.setEnabled(false);

		univSetPanel.add(univSetLenLabel);
		univSetPanel.add(univSetLenField);
		univSetPanel.add(univSetOkButton);

		listType.setSelected(true);
		setTypeGroup.add(listType);
		setTypeGroup.add(vectorType);

		typePanel.add(listType);
		typePanel.add(vectorType);

		typePanel.add(univSetPanel);

		// ///////////////////////////////

		GridLayout dataTypePanelLayout = new GridLayout(0, 2);

		JPanel dataTypePanel = new JPanel();
		dataTypePanel.setLayout(dataTypePanelLayout);
		dataTypePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Тип даних"));
		dataTypePanel.setBounds(5, 120, 250, 100);

		intType = new JRadioButton("Цілі");
		doubleType = new JRadioButton("Дробові");
		stringType = new JRadioButton("Рядки");
		charType = new JRadioButton("Символи");

		ButtonGroup dataTypeGroup = new ButtonGroup();
		intType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.INT_TYPE;
				//fillSetA();
				//fillSetB();
				
				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				UNIVERSUM_SET = null;
				fliesCompotentEnable(false);
			}
		});

		doubleType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.DOUBLE_TYPE;
				//fillSetA();
				//fillSetB();
				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				UNIVERSUM_SET = null;
				fliesCompotentEnable(false);
			}
		});
		stringType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.STRING_TYPE;
//				fillSetA();
//				fillSetB();
				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				UNIVERSUM_SET = null;
				fliesCompotentEnable(false);
			}
		});

		charType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_DATA_TYPE = Constants.CHAR_TYPE;
//				fillSetA();
//				fillSetB();
				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				UNIVERSUM_SET = null;
				fliesCompotentEnable(false);
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

		setAFileButton = new JButton("Файл");
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

		ImageIcon icon = null;
		
		String deleteIcon = "res/delete_16.png";
		try{
			icon = new ImageIcon(getClass().getClassLoader().getResource(deleteIcon));
		}catch(Exception e){
			icon = new ImageIcon("res/delete_16.png");
		}
		removeALabel = new JLabel(icon);
		removeALabel.setToolTipText("Видалити множину");
		removeALabel.setEnabled(false);
		removeALabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
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

		setBFileButton = new JButton("Файл");
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
				setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
						vectorSetLen);
				fileNameBSet = "";
				setBField.setText("");
				removeBLabel.setEnabled(false);
			}
		});
		removeBLabel.setBounds(228, 97, icon.getIconWidth(),
				icon.getIconHeight());

		filePanel.add(setBFileButton);
		filePanel.add(removeBLabel);

		// /////////////
		// ///////////
		// //////

		optionsPanel.add(typePanel);
		optionsPanel.add(dataTypePanel);
		optionsPanel.add(filePanel);

		this.add(optionsPanel);

	}

	
	
	private void optionsEnable(boolean enabled){
		intType.setEnabled(enabled);
		doubleType.setEnabled(enabled);
		stringType.setEnabled(enabled);
		charType.setEnabled(enabled);
		setAFileButton.setEnabled(enabled);
		setBFileButton.setEnabled(enabled);

	}
	private void fliesCompotentEnable(boolean enabled){
		
		fileNameBSet = "";
		setBField.setText("");
		setBField.setEnabled(enabled);
		removeBLabel.setEnabled(enabled);
		
		fileNameASet = "";
		setAField.setText("");
		setAField.setEnabled(enabled);
		removeALabel.setEnabled(enabled);
		
		
		
	}
	protected void fillSetA() {

		if (UNIVERSUM_SET != null) {
			setA.setList(UNIVERSUM_SET);
		} else {

			UNIVERSUM_SET = null;
			setA = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
					vectorSetLen);
		}
		
		if (!fileNameASet.isEmpty()) {

			try {
				setA = SetFactory.fillSetFromFile(setA, fileNameASet,
						CURRENT_DATA_TYPE);
			} catch (Exception e) {
				// fileNameASet = "";
				JOptionPane.showMessageDialog(null, "Помилка зчитування. "
						+ "Перевірте правильність типу даних", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			}

			setAField.setText(fileNameASet);
			removeALabel.setEnabled(true);

			UNIVERSUM_SET = setA.getList();
			if (CURRENT_SET_TYPE == Constants.CHARACTER_VACTOR_TYPE) {
				setB.setList(UNIVERSUM_SET);
			}
		}

		System.out.println("UNI SET: " + UNIVERSUM_SET.toString());

	}

	protected void fillSetB() {
		
		
		
		if (UNIVERSUM_SET != null) {
			setB.setList(UNIVERSUM_SET);
		} else {

			UNIVERSUM_SET = null;
			setB = SetFactory.getSet(CURRENT_SET_TYPE, CURRENT_DATA_TYPE,
					vectorSetLen);
		}

		if (!fileNameBSet.isEmpty()) {

			try {
				setB = SetFactory.fillSetFromFile(setB, fileNameBSet,
						CURRENT_DATA_TYPE);
			} catch (Exception e) {
				// fileNameASet = "";
				JOptionPane.showMessageDialog(null, "Помилка зчитування. "
						+ "Перевірте правильність типу даних", "Помилка",
						JOptionPane.ERROR_MESSAGE);
			}

			setBField.setText(fileNameBSet);
			removeBLabel.setEnabled(true);

			UNIVERSUM_SET = setB.getList();
			if (CURRENT_SET_TYPE == Constants.CHARACTER_VACTOR_TYPE) {
				setA.setList(UNIVERSUM_SET);
			}
		}

		System.out.println("UNI SET: " + UNIVERSUM_SET.toString());

	}

	private void addOperationsPanel() {
		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(null/* new GridLayout(9, 1) */);
		operationsPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Операції"));
		operationsPanel.setBounds(265, 5, 263, 565);

		JPanel activeSetPanel = new JPanel();
		activeSetPanel.setLayout(new GridLayout(0, 2));
		activeSetPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Активна множина"));
		activeSetPanel.setBounds(5, 20, 252, 50);

		JRadioButton setAButton = new JRadioButton("Множина А");
		JRadioButton setBButton = new JRadioButton("Множина B");
		setAButton.setSelected(true);

		ButtonGroup activeSetGroup = new ButtonGroup();

		setAButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if ((CURRENT_SET_TYPE == Constants.CHARACTER_VACTOR_TYPE)&&(UNIVERSUM_SET != null)) {
					
					
					setA.setList(UNIVERSUM_SET);
				}

				CURRENT_ACTIVE_SET = Constants.SET_A;
				String setName = "Множина А:";
				console.append(setName + "\n" + getActiveSet().toString()
						+ "\n");
			}
		});

		setBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_ACTIVE_SET = Constants.SET_B;

				if ((CURRENT_SET_TYPE == Constants.CHARACTER_VACTOR_TYPE)&&(UNIVERSUM_SET != null)) {
					setB.setList(UNIVERSUM_SET);
				}

				String setName = "Множина B:";
				console.append(setName + "\n" + getActiveSet().toString()
						+ "\n");

			}
		});

		activeSetGroup.add(setAButton);
		activeSetGroup.add(setBButton);

		activeSetPanel.add(setAButton);
		activeSetPanel.add(setBButton);

		operationsPanel.add(activeSetPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder()));
		buttonPanel.setBounds(7, 70, 248, 299);

		JRadioButton insert = new JRadioButton("Додати елемент");
		JRadioButton delete = new JRadioButton("Вилучити елемент");
		JRadioButton member = new JRadioButton("Належність");
		JRadioButton min = new JRadioButton("Мінімальний елемент");
		JRadioButton max = new JRadioButton("Максимальний елемент");
		JRadioButton empty = new JRadioButton("Очистити множину");
		JRadioButton union = new JRadioButton("Об'єднання множин");
		JRadioButton intersection = new JRadioButton("Перетин множин");
		JRadioButton difference = new JRadioButton("Різниця множин");
		JRadioButton merge = new JRadioButton("Злиття множин");
		JRadioButton equal = new JRadioButton("Рівність множин");
		JRadioButton power = new JRadioButton("Потужність множини");
		JRadioButton product = new JRadioButton("Декартовий добуток");
		JRadioButton toString = new JRadioButton("Показати множину");

		ButtonGroup operationsGroup = new ButtonGroup();
		insert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_VAL_OPERATION = Constants.VAL_INS;
				setValuePanelEnabled(true);
			}
		});

		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_VAL_OPERATION = Constants.VAL_DEL;
				setValuePanelEnabled(true);
			}
		});

		member.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CURRENT_VAL_OPERATION = Constants.VAL_MEMB;
				setValuePanelEnabled(true);
			}
		});

		min.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String min = String.valueOf(getActiveSet().min());
				console.append("Мінімальний елемент: " + min + "\n");
				setValuePanelEnabled(false);
			}
		});

		max.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String max = String.valueOf(getActiveSet().max());
				console.append("Максимльний елемент: " + max + "\n");
				setValuePanelEnabled(false);
			}
		});

		empty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getActiveSet().empty();
				console.append("Множину очищено \n");
				setValuePanelEnabled(false);
			}
		});

		union.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ISet unionSet = setA.union(setB);
				console.append("Множина А: " + setA.toString() + "\n");
				console.append("Множина B: " + setB.toString() + "\n");
				console.append("Об'єднання: " + unionSet.toString() + "\n");
				setValuePanelEnabled(false);
			}
		});

		intersection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ISet intersectionSet = setA.intersection(setB);
				console.append("Множина А: " + setA.toString() + "\n");
				console.append("Множина B: " + setB.toString() + "\n");
				console.append("Перетин : " + intersectionSet.toString() + "\n");
				setValuePanelEnabled(false);
			}
		});

		difference.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ISet differenceSet = null;
				String dif;

				if (CURRENT_ACTIVE_SET == Constants.SET_A) {
					differenceSet = setA.difference(setB);
					dif = "A/B";
				} else {
					differenceSet = setB.difference(setA);
					dif = "B/A";
				}
				console.append("Множина А: " + setA.toString() + "\n");
				console.append("Множина B: " + setB.toString() + "\n");
				console.append("Різниця " + dif + ": "
						+ differenceSet.toString() + "\n");
				setValuePanelEnabled(false);
			}
		});

		merge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ISet mergeSet = null;

				if (CURRENT_ACTIVE_SET == Constants.SET_A) {
					mergeSet = setA.merge(setB);
				} else {
					mergeSet = setB.merge(setA);
				}

				if (mergeSet == null) {
					console.append("Результат невизначений" + "\n");
				} else {
					console.append("Множина А: " + setA.toString() + "\n");
					console.append("Множина B: " + setB.toString() + "\n");
					console.append("Злиття : " + mergeSet.toString() + "\n");
				}
				setValuePanelEnabled(false);
			}
		});

		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean res = setA.equal(setB);
				String resS = String.valueOf(res).toUpperCase();
				console.append("Рівність множин: " + resS + "\n");
				setValuePanelEnabled(false);
			}
		});

		toString.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String setName = (CURRENT_ACTIVE_SET == Constants.SET_A) ? "Множина А:"
						: "Множина B:";
				console.append(setName + "\n" + getActiveSet().toString()
						+ "\n");
				setValuePanelEnabled(false);
			}
		});

		power.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				console.append("Потужність: " + getActiveSet().getSize() + "\n");
				setValuePanelEnabled(false);
			}
		});

		product.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<Pair<Object, Object>> prodList = new ArrayList<Pair<Object, Object>>();
				String prod;

				if (CURRENT_ACTIVE_SET == Constants.SET_A) {
					prodList = setA.cartesianProduct(setB);
					prod = "AxB";
				} else {
					prodList = setB.cartesianProduct(setA);
					prod = "BxA";
				}
				console.append("Множина А: " + setA.toString() + "\n");
				console.append("Множина B: " + setB.toString() + "\n");
				console.append("Декартовий добуток " + prod + ": \n");

				for (Pair<Object, Object> val : prodList) {
					console.append("[" + val.getFirst() + "] ["
							+ val.getSecond() + "]\n");
				}

				setValuePanelEnabled(false);
			}
		});

		operationsGroup.add(insert);
		operationsGroup.add(delete);
		operationsGroup.add(member);
		operationsGroup.add(min);
		operationsGroup.add(max);
		operationsGroup.add(empty);
		operationsGroup.add(union);
		operationsGroup.add(intersection);
		operationsGroup.add(difference);
		operationsGroup.add(merge);
		operationsGroup.add(equal);
		operationsGroup.add(power);
		operationsGroup.add(product);
		operationsGroup.add(toString);

		buttonPanel.add(insert);
		buttonPanel.add(insert);
		buttonPanel.add(delete);
		buttonPanel.add(member);
		buttonPanel.add(min);
		buttonPanel.add(max);
		buttonPanel.add(empty);
		buttonPanel.add(union);
		buttonPanel.add(intersection);
		buttonPanel.add(difference);
		buttonPanel.add(merge);
		buttonPanel.add(equal);
		buttonPanel.add(power);
		buttonPanel.add(product);
		buttonPanel.add(toString);

		operationsPanel.add(buttonPanel);

		valuePanel = new JPanel();
		valuePanel.setLayout(null);
		valuePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Значення"));
		valuePanel.setBounds(5, 370, 252, 50);

		valueField = new JTextField();
		valueField.setBounds(5, 20, 185, 20);

		
		Icon applyIcon = null;

		String apply = "res/apply_20.png";
		
		try{
			applyIcon = new ImageIcon(getClass().getClassLoader().getResource(apply));
		}catch(Exception e){
			applyIcon = new ImageIcon("res/apply_20.png");
		}
		okButton = new JButton(applyIcon);
		okButton.setBounds(195, 19, 50, 22);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String setName = (CURRENT_ACTIVE_SET == Constants.SET_A) ? "Множина А:"
						: "Множина B:";
				String val = valueField.getText();
				switch (CURRENT_VAL_OPERATION) {
				case Constants.VAL_INS:

					try {
						SetFactory.insertToSet(getActiveSet(), val,
								CURRENT_DATA_TYPE);
					} catch (Exception e2) {
						e2.printStackTrace();
						System.out.println("data " + CURRENT_DATA_TYPE);
						JOptionPane.showMessageDialog(null,
								"Помилка зчитування. "
										+ "Перевірте правильність типу даних",
								"Помилка", JOptionPane.ERROR_MESSAGE);
					}

					valueField.setText("");
					console.append(setName + "\n" + getActiveSet().toString()
							+ "\n");

					UNIVERSUM_SET = getActiveSet().getList();
					break;
				case Constants.VAL_DEL:
					try {
						SetFactory.deleteFromSet(getActiveSet(), val,
								CURRENT_DATA_TYPE);
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Помилка зчитування. "
										+ "Перевірте правильність типу даних",
								"Помилка", JOptionPane.ERROR_MESSAGE);
					}
					valueField.setText("");
					UNIVERSUM_SET = getActiveSet().getList();
					console.append(setName + "\n" + getActiveSet().toString()
							+ "\n");
					break;
				case Constants.VAL_MEMB:
					try {
						boolean res = SetFactory.isMemb(getActiveSet(), val,
								CURRENT_DATA_TYPE);
						String resS = String.valueOf(res).toUpperCase();
						console.append(resS + "\n");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null,
								"Помилка зчитування. "
										+ "Перевірте правильність типу даних",
								"Помилка", JOptionPane.ERROR_MESSAGE);
					}
					valueField.setText("");
					break;
				default:
					break;
				}

			}
		});

		valuePanel.add(valueField);
		valuePanel.add(okButton);

		setValuePanelEnabled(false);
		operationsPanel.add(valuePanel);

		this.add(operationsPanel);

	}

	private void setValuePanelEnabled(boolean val) {
		this.valuePanel.setEnabled(val);
		this.valueField.setEditable(val);
		this.okButton.setEnabled(val);

	}

	private ISet getActiveSet() {
		if (CURRENT_ACTIVE_SET == Constants.SET_A)
			return setA;
		else
			return setB;
	}

	private void addConsolePanel() {
		JPanel consolePanel = new JPanel();
		// consolePanel.setLayout(null/* new GridLayout(9, 1) */);
		consolePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Результат"));
		consolePanel.setBounds(527, 5, 263, 565);

		console = new JTextArea(31, 20);

		console.setBounds(5, 5, 260, 200);
		console.setEditable(false);

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
