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

public class search_testing {

	private static Scanner X;
	private static File file;
	private static FileWriter fw;
	private static BufferedWriter br;
	private static PrintWriter pw;

	
	@Test
	public void test() throws IOException{
		
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
		amacon_store ams= new amacon_store();
		
		ams= amacon2.search(arr, "oneplus2");
		int output=3;
		if(ams==null) {
			output= 2;
		}
		else if(ams.product.contentEquals("oneplus2")) {
			output=0;
		}
		assertEquals(0, output);
		
	}

}
