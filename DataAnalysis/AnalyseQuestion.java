package DataAnalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalyseQuestion {

	public static void main(String[] args) {
		AnalyseQuestion aq = new AnalyseQuestion();
		
		String outputFile = "data_analysis/output_q%d.txt";
		FileWriter[] fw = new FileWriter[50];
		
		try {
			for (int i = 0; i < 50; i++) {
				String fileName = String.format(outputFile, i+1);
				fw[i] = new FileWriter(fileName);
				
				aq.analyseByQnumber(i+1, fw[i]);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}

	public void analyseByQnumber(int qNum, FileWriter fw) {
		try {
			Question question = new Question(qNum);
			
			String inputFile = "data/output_q%d.txt";
			String fileName = String.format(inputFile, qNum);
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			
			String[] genderArray = {"M", "F"};
			int thisYear = 2014;
			String[] salaryArray = {"0-25k", "25k-50k", "50k-75k", "75k-100k", "100k-150k", "150k-300k", "300k+"};
			String[] degreeArray = {"初中及以下", "高中", "大学", "研究生及以上"};
			
			int[] genderCounter = new int[genderArray.length];
			int[] ageCounter = new int[115];
			int[] salaryCounter = new int[salaryArray.length];
			int[] degreeCounter = new int[degreeArray.length];
			
			int counter = 0;
			
			try {
				br = new BufferedReader(new FileReader(fileName));
				
				while ((line = br.readLine()) != null) {
					String[] data = line.split(cvsSplitBy);
					
					int info = Integer.parseInt(data[0]);
					int gender = Integer.parseInt(data[1]);
					int bornYear = Integer.parseInt(data[2]);
					int salary = Integer.parseInt(data[3]);
					int degree = Integer.parseInt(data[4]);
					
					genderCounter[gender] += info;
					ageCounter[thisYear - bornYear] += info;
					salaryCounter[salary] += info;
					degreeCounter[degree] += info;
					
					counter++;
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
			
			System.out.printf("%d\t%s\n", question.getNum(), question.getContent());
			String title = String.format("%d\t%s\n\n", question.getNum(), question.getContent());
			fw.write(title);
			
	//		System.out.println("------------GENDER-------------");
			System.out.println();
			
			fw.write("gender\n");
			for (int i = 0; i < genderCounter.length; i++) {
				int j = genderCounter[i];
				double result = (double)j / counter;
				System.out.printf("%s\t%f\n", genderArray[i], result);
				String content = String.format("%s\t%f\n", genderArray[i], result);
				fw.write(content);
			}
			fw.write("\n");
		
	//		System.out.println("-------------------------------");
	//		System.out.println("--------------AGE--------------");
			System.out.println();
			
			fw.write("age\n");
			for (int i = 14; i <= 54; i++) {
				int j = ageCounter[i];
				double result = (double)j / counter;
				System.out.printf("%s\t %f\n", i, result);
				String content = String.format("%s\t %f\n", i, result);
				fw.write(content);
			}
			fw.write("\n");
			
	//		System.out.println("-------------------------------");
	//		System.out.println("------------SALARY-------------");
			System.out.println();
			
			fw.write("salary\n");
			for (int i = 0; i < salaryCounter.length; i++) {
				int j = salaryCounter[i];
				double result = (double)j / counter;
				System.out.printf("%s\t%f\n", salaryArray[i], result);
				String content = String.format("%s\t%f\n", salaryArray[i], result);
				fw.write(content);
			}
			fw.write("\n");
			
	//		System.out.println("-------------------------------");
	//		System.out.println("-------------DEGREE------------");
			System.out.println();
			
			fw.write("degree\n");
			for (int i = 0; i < degreeCounter.length; i++) {
				int j = degreeCounter[i];
				double result = (double)j / counter;
				System.out.printf("%s\t%f\n", degreeArray[i], result);
				String content = String.format("%s\t%f\n", degreeArray[i], result);
				fw.write(content);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
