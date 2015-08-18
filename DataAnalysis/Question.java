package DataAnalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Question {
	private int num;
	private String content;
	
	public Question(int number) {
		this.num = number;
		this.content = getContent(number);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String getContent(int number) {
		String csvFile = "data/2014data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		String result = null;
		
		try {
			 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				 
				String[] data = line.split(cvsSplitBy);
				
				if (!data[number + 2].equals("")) {
					result = data[number + 2];
				}
				break;
			}
			
	 
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
		
		return result;
	}

}
