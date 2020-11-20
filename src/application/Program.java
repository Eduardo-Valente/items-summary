package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;

public class Program {
	
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String inputFile = "D:\\Eduardo\\Cursos\\Java Completo 2020\\Intermediário\\Sold Items.csv";
		String outputFile = "D:\\Eduardo\\Cursos\\Java Completo 2020\\Intermediário\\Summary.csv";
		String dirPath = "D:\\Eduardo\\Cursos\\Java Completo 2020\\Intermediário";
		
		
		String line;
		String joinedFields;
		
		Double price;
		Integer quantity;
		
		boolean success = new File(dirPath + "\\out").mkdir();
		System.out.println("Directory created successfully: " + success);
		
		String completeOutputFilePath = "D:\\Eduardo\\Cursos\\Java Completo 2020\\Intermediário\\out\\Summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

				line = br.readLine();
				
				while(line != null)
				{
					String[] splittedFields = line.split(",");
					
					price = Double.parseDouble(splittedFields[1]);
					quantity = Integer.parseInt(splittedFields[2]);
					
					Product product = new Product(splittedFields[0], price, quantity);
					
					joinedFields = product.getName() + ',' + String.format("%.2f", product.subTotal() ) + '\n';
					
					try(BufferedWriter bw = new BufferedWriter(new FileWriter(completeOutputFilePath, true)) ) {
						
						bw.write(joinedFields);
					}
					
					line = br.readLine();
				}
			
		}
		
		catch(IOException ioe)
		{
			System.out.println("Error message: " + ioe.getMessage());
		}
		
		catch(NullPointerException npe)
		{
			System.out.println("Error message: " + npe.getMessage());
		}
	}	
}
