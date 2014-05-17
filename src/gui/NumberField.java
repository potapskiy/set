package gui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


public class NumberField extends JTextField {
	 
	private static int length;
	private static String regex;
	public NumberField(/*String text,*/int length) {
		//setText(text);
		this.length = length;
		regex = "\\d{0," + String.valueOf(length)+ "}";
	}
	
//    public int getLength() {
//		return length;
//	}
//
//    public void setLength(int length) {
//		this.length = length;
//	}

	protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }

    static class UpperCaseDocument extends PlainDocument {

        public void insertString(int offs, String str, AttributeSet a) 
            throws BadLocationException {

            if (str == null) {
                return;
            }
           // System.out.println(str);
            
           //ыдо System.out.println(regex);
            if ( (str.matches(regex)) && ((getLength() + str.length()) <= length)){ 
            	super.insertString(offs, str, a);
            }
            
        }
    }
}