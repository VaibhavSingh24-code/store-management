package amacon2;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class delete_testing {

	private static Scanner X;
	private static File file;
	private static FileWriter fw;
	private static BufferedWriter br;
	private static PrintWriter pw;

	
	@Test
	public void test() throws IOException {

		amacon2 ins_t= new amacon2();
		
		
		ArrayList arr= new ArrayList();
		try{
			file= new File("database.txt");
			
		}
		catch(Exception e) {
			System.out.println("Couldn't find the file");
		}
		X= new Scanner(file);
		fw= new FileWriter(file, true);
		br= new BufferedWriter(fw);
		
		pw= new PrintWriter(br);
		
		
		while(X.hasNext()) {
			
			amacon_store ams= new amacon_store();
			
			String category= X.next();
			String subcategory= X.next();
			String product= X.next();
			int price= X.nextInt();
			int quantity= X.nextInt();
			
			ams.category=category;
			ams.subcategory=subcategory;
			ams.product=product;
			ams.price=price;
			ams.quantity=quantity;
			arr.add(ams);
		}
		String str_path="electronics>smartphones>oneplus2";
		

		System.out.println("Inside Delete.");
		int output = amacon2.delete_object(arr,str_path);
		//System.out.printf("Output: %d", output);
		assertEquals(0,output);
		
		
	}

}
