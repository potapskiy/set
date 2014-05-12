package main;

import java.awt.FileDialog;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import utils.Constants;
import logic.CharVectorSet;
import logic.ISet;
import logic.LinkedListSet;
import logic.SetFactory;
import logic.list.LinkedListImp;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("OK");
		
		
		
		
//		LinkedListImp<Integer> a = new LinkedListImp<>();
//		a.add(5);
//		a.add(7);
//		a.add(-2);
//		a.add(1);
//		
//
//		System.out.println("POs:" + a.remove(5));
//		System.out.println(a.toString());
		
		
		
		ISet set = new CharVectorSet<Integer>(5);
		
		
		set.insert(5);
		set.insert(7);
		set.insert(-2);
		set.insert(1);
		set.insert(8);
		//set.delete(-2);
		System.out.println(set.getList().toString());
		System.out.println(set.vectorToString());
		System.out.println(set.max());
		
		
//		System.out.println(Integer.compare(1, 4));
//		
//		
//		ISet a = SetFactory.getSet(Constants.LIST_TYPE, Constants.INT_TYPE, 0);
//		
//		a.insert(5);
//		a.insert(7);
//		a.insert(1);
//		a.insert("sddsd");
//		
//		
//		
//		ISet<Integer> b = SetFactory.getSet(Constants.LIST_TYPE, Constants.INT_TYPE, 0);
//		
//		b.insert(7);
//		b.insert(5);
//		b.insert(2);
//		
//		
//		
//		
//		System.out.println(a.toString());
//		System.out.println(b.toString());
//		
//		
//		
//		JPanel middlePanel = new JPanel ();
//	    middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
//
//	    // create the middle panel components
//
//	    JTextArea display = new JTextArea ( 16, 58 );
//	    display.setEditable ( false ); // set textArea non-editable
//	    display.append("dfdsf");
//	    JScrollPane scroll = new JScrollPane ( display );
//	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//
//	    //Add Textarea in to middle panel
//	    middlePanel.add ( scroll );
//
//	    // My code
//	    JFrame frame = new JFrame ();
//	    frame.add ( middlePanel );
//	    
//	    
//	    FileDialog fd = new FileDialog(frame, "Save File", FileDialog.SAVE);
//	    fd.setFile("*.txt");
//	    fd.setVisible(true);
//
//	    String file = fd.getFile();
//	    System.out.println(file);
//	    System.exit(0);
//	    
//	    frame.pack ();
//	    frame.setLocationRelativeTo ( null );
//	    frame.setVisible ( true );
		
		
		
	}

}
