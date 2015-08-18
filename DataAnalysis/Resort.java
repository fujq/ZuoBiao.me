package DataAnalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Resort {

	public static void main(String[] args) {
		String csvFile = "data/2014data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
//		String outputFile = "data/output.txt";
		String outputFile = "data/output1.txt";
		FileWriter fw = null;
				
		try {
			 
			br = new BufferedReader(new FileReader(csvFile));
			fw = new FileWriter(outputFile);
			
			int lineCounter = 0;
			
			while ((line = br.readLine()) != null) {
	 
				String[] data = line.split(cvsSplitBy);
	 
				if (data.length != 57 || data[0].equals("")) {
					continue;
				}
				
		//		String resultString = "(";
				String resultString = "";
				
				for (int i = 3; i <= 52; i++) {
					if (data[i].equals("同意")) {
						resultString += "1,";
					} else if (data[i].equals("强烈同意")) {
						resultString += "2,";
					} else if (data[i].equals("反对")) {
						resultString += "-1,";
					} else if (data[i].equals("强烈反对")) {
						resultString += "-2,";
					} else {
						continue;
					}
				}
				
				if (data[53].equals("M")) {
					resultString += "1,";
				} else if (data[53].equals("F")) {
					resultString += "0,";
				} else {
					continue;
				}
				
				if (!data[54].equals("1901") && !data[54].equals("")) {
					resultString += data[54] + ",";
				} else {
					continue;
				}
				
				if (data[55].equals("0-25k")) {
					resultString += "0,";
				} else if (data[55].equals("25k-50k")) {
					resultString += "1,";
				} else if (data[55].equals("50k-75k")) {
					resultString += "2,";
				} else if (data[55].equals("75k-100k")) {
					resultString += "3,";
				} else if (data[55].equals("100k-150k")) {
					resultString += "4,";
				} else if (data[55].equals("150k-300k")) {
					resultString += "5,";
				} else if (data[55].equals("300k+")) {
					resultString += "6,";
				} else {
					continue;
				}
				
				if (data[56].equals("初中及以下")) {
					resultString += "0";
				} else if (data[56].equals("高中")) {
					resultString += "1";
					if (2014 - Integer.parseInt(data[54]) < 14) {
						System.out.println(data[0] + " " + resultString);
						continue;
					}
				} else if (data[56].equals("大学")) {
					resultString += "2";
					if (2014 - Integer.parseInt(data[54]) < 17) {
						System.out.println(data[0] + " " + resultString);
						continue;
					}
				} else if (data[56].equals("研究生及以上")) {
					resultString += "3";
					if (2014 - Integer.parseInt(data[54]) < 21) {
						System.out.println(data[0] + " " + resultString);
						continue;
					}
				} else {
					continue;
				}
				
		//		System.out.println(resultString);
			
		//		resultString += "),";
				resultString += "\n";
				fw.write(resultString);
				
				lineCounter++;
				
		//		if (lineCounter > 10) {
		//			break;
		//		}
			}
			
			fw.close();
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
