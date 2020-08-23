package amacon2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class database_serial {
	
	private static File file;
	
	@Test
	public void test() throws IOException, ClassNotFoundException {
		
		//customer_cart_array cust_arr_obj =null;
		System.out.println("Testing Database Serealization.");
		
		amacon_store_array store_data= new amacon_store_array();
		
		amacon_store e= new amacon_store();
		e.category="a";
		e.subcategory="b";
		e.product="c";
		e.price=20;
		e.quantity=10;
		
		store_data.arr.add(e);
		
		FileOutputStream fo_database = new FileOutputStream(new File("test_database.txt"));
		ObjectOutputStream oo_database = new ObjectOutputStream(fo_database);
	
		oo_database.writeObject(store_data);
			
		oo_database.close();
		fo_database.close();

		FileInputStream fi_database = new FileInputStream(new File("test_database.txt"));
		ObjectInputStream oi_database = new ObjectInputStream(fi_database);
		
		store_data=(amacon_store_array) oi_database.readObject();
		
		oi_database.close();
		fi_database.close();

		amacon_store ams= amacon2.search(store_data.arr, "c");
		
		int output=0;
		if(ams==null) {
			output=1;
			System.out.println("Test Unsuccessful.");
		}
		assertEquals(0, output);
		
	}

}
