package amacon2;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class sale_testing {

	private static Scanner X;
	private static File file;
	private static FileWriter fw;
	private static BufferedWriter br;
	private static PrintWriter pw;

	
	@Test
	public void test() throws IOException, ClassNotFoundException {

		amacon2 ins_t= new amacon2();
		
		//amacon_store ams= new amacon_store();
		
		customer_cart_array cust_arr_obj =null;
		
		
		if((file= new File("Users.txt")).exists()) {
						
			FileInputStream fi_customers = new FileInputStream(new File("Users.txt"));
			ObjectInputStream oi_customers = new ObjectInputStream(fi_customers);

			cust_arr_obj = (customer_cart_array) oi_customers.readObject();
			oi_customers.close();
			fi_customers.close();
		}
		else {
			cust_arr_obj= new customer_cart_array();
		}
		
		customer_cart cust= new customer_cart();
		ArrayList arr= new ArrayList();
		
		System.out.println("Inside Sales.");
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
		
		int output = amacon2.sale(arr, cust_arr_obj , 0, "oneplus8", 1000,cust.rem_funds);
		//System.out.printf("Output: %d", output);
		//assertEquals(0,output);

	}

}
