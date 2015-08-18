package DataAnalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SortByQ {

	public static void main(String[] args) {
		String inputFile = "data/output1.txt";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		String outputFile = "data/output_q%d.txt";
		FileWriter[] fw = new FileWriter[50];
				
		try {
			 
			br = new BufferedReader(new FileReader(inputFile));
			
			for (int i = 0; i < 50; i++) {
				String fileName = String.format(outputFile, i+1);
				fw[i] = new FileWriter(fileName);
			}
						
			int lineCounter = 0;
			
			while ((line = br.readLine()) != null) {
	 
				String[] data = line.split(cvsSplitBy);
	 
				if (data.length != 54 || data[0].equals("")) {
					break;
				}
				
				for (int i = 0; i < 50; i++) {
					String resultString = "";
					
					resultString += data[i] + "," + data[50] + "," + data[51] + "," + data[52] + "," + data[53];
				
					resultString += "\n";
					fw[i].write(resultString);
				}
				lineCounter++;
				
//				if (lineCounter > 10) {
//					break;
//				}
			}
			
			for (int i = 3; i <= 52; i++) {
				fw[i-3].close();

			}
			System.out.println(lineCounter);
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
