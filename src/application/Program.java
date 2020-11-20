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
	
	/* 
	 * Reads an input file and creates a CSV file with the sub total calculus inside
	 * an created sub directory 
	 * 
	 * e.g.: input file: C:\User\file.csv
	 * 		 sub directory: C:\User\out
	 */
	
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String inputFile, dirPath;
		
		System.out.print("Enter the input file full path: ");
		inputFile = sc.nextLine();
		System.out.print("Enter output file subdirectory full path for its creation: ");
		dirPath = sc.nextLine();
		
		String line;
		String joinedFields;
		
		Double price;
		Integer quantity;
		
		boolean success = new File(dirPath).mkdir();
		
		if(success == true)
		{	
			System.out.println("Directory created successfully!");
		}
		else {
			System.out.println("Couldn't created directory because it already exists "
								+ "or you don't have the permission to create one");
		}
			
		String completeOutputFilePath = dirPath + "\\Summary.csv";
		
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
		
		finally {
			
			if(sc != null)
			{
				sc.close();
			}
		}
	}	
}
