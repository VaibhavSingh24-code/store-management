package amacon2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class modify_testing {

	@Test
	public void test() throws IOException{

		amacon2 ins_t= new amacon2();
		
		amacon_store ams= new amacon_store();
		
		ArrayList arr= new ArrayList();
		
		System.out.println("Inside Modify");
		
		ams.category="electronics";
		ams.subcategory="smartphones";
		ams.product="oneplus2";
		ams.price=23;
		ams.quantity=23;
		arr.add(ams);
		amacon2.modify(arr, "oneplus3");
		//System.out.printf("Output: %d", output);
		//assertEquals(0,output);

	}

}
