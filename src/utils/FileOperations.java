package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class FileOperations {
	
	
	
	public static void writeToFile(String fileName, String text) {
		
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, false)));
			
			
			text = text.replaceAll("\n", "\r\n");
			writer.write(text);
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		
		
	}

}
