package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Box.Filler;

import utils.Constants;

public class SetFactory {

	public static ISet getSet(int setType, int dataType, int length) {
		ISet set = null;

		if (setType == Constants.LIST_TYPE) {

			switch (dataType) {
			case Constants.INT_TYPE:
				return new LinkedListSet<Integer>();

			case Constants.DOUBLE_TYPE:
				return new LinkedListSet<Double>();

			case Constants.STRING_TYPE:
				return new LinkedListSet<String>();

			case Constants.CHAR_TYPE:
				return new LinkedListSet<Character>();

			default:
				return null;
			}

		}

		return set;
	}

	public static ISet fillSetFromFile(ISet set, String fileName, int dataType)
			throws IOException, NumberFormatException {

		//BufferedReader br = null;

		String sCurrentLine;
		//br = new BufferedReader(new FileReader(fileName));

		FileInputStream fis = new FileInputStream(new File(fileName));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		
		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);

			switch (dataType) {
			case Constants.INT_TYPE:
				set.insert(Integer.valueOf(sCurrentLine));
				break;
			case Constants.DOUBLE_TYPE:
				set.insert(Double.valueOf(sCurrentLine));
				break;
			case Constants.CHAR_TYPE:
				set.insert(sCurrentLine.charAt(0));
				break;
			case Constants.STRING_TYPE:
				set.insert(sCurrentLine);
				break;
			default:
				break;
			}

		}

		br.close();

		return set;
	}

}
