package amacon2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class cart_serial {

	private static File file;
	
	@Test
	public void test() throws IOException, ClassNotFoundException {
		
		System.out.println("Testing Customer Cart Serealization.");
		
		customer_cart_array cust_arr_obj =new customer_cart_array();
		
		customer_cart user_cart= new customer_cart();
		user_cart.rem_funds=7;
		cust_arr_obj.customer_arr.add(user_cart);
		
		FileOutputStream fo_database = new FileOutputStream(new File("test_cart.txt"));
		ObjectOutputStream oo_database = new ObjectOutputStream(fo_database);
	
		oo_database.writeObject(cust_arr_obj);
			
		oo_database.close();
		fo_database.close();

		FileInputStream fi_database = new FileInputStream(new File("test_cart.txt"));
		ObjectInputStream oi_database = new ObjectInputStream(fi_database);
		
		cust_arr_obj=(customer_cart_array) oi_database.readObject();
		
		oi_database.close();
		fi_database.close();
		
		int output=1;
		
		if(cust_arr_obj.customer_arr.get(0).rem_funds==7) {
			output=0;
			System.out.println("Test Successful.");
		}
		assertEquals(0, output);
		
	}

}
