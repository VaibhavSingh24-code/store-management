package amacon2;

import java.io.*;
import java.util.*;


class customer_cart_array implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<customer_cart> customer_arr= new ArrayList<>();
	
}

/*class MyException extends Exception 
{ 
	
    public MyException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
} */

class amacon_store_array implements Serializable{
	
	private static final long serialVersionUID = 1L;
	ArrayList<amacon_store> arr= new ArrayList<>();
}

class amacon_store implements Serializable{
	
	
	
	
	String category;
	String subcategory;
	String product;
	int price;
	int quantity;
	
	public void print_category() {
		
		System.out.printf(category+" ");
	}
	public void print_subcategory() {
		
		System.out.printf(subcategory+ " ");
	}
	
	public void print_product() {
		
		System.out.printf(product+ " ");
	}

	public void print_price() {
		
		System.out.print(price + " ");
	}

	public void print_quantity() {
		
		System.out.print(quantity+ " ");
	}

	public void updateValues(int p, int q) {
		
		price= p;
		quantity= q;
	}
	
}
///////////////////////////////////////////

class customer_cart implements Serializable{
	
	
	ArrayList<amacon_store> ams= new ArrayList<>();
	String user;
	int total_cost;
	int n_items;
	int rem_funds;
	
	customer_cart(){
	
		user=null;
		total_cost=0;
		n_items=0;
		rem_funds=10000;
	}
	public void set_user(String s) {
		user=s;
	}
	public void upd_total_cost(int c) {
		
		total_cost+=c;
	}
	public void upd_n_items(int c) {
		
		n_items+=c;
	}
	public void upd_rem_funds(int c) {
		
		rem_funds-=c;
	}
	public void add_funds(int c) {
		
		rem_funds+=c;
	}
	public void edit_funds(int c) {
		
		rem_funds=c;
	}
}


public class amacon2 {

	private static Scanner X;
	private static File file;
	
	//private static File file_user;
	
	/////////////////////////////////////
	
	public static int sale(ArrayList<amacon_store> arr,customer_cart_array object,int index,String str, int quan, int c) {
		
		amacon_store ams= new amacon_store();
		ams=search(arr, str);
		
		amacon_store temp_ams= new amacon_store();
		
		customer_cart obj= object.customer_arr.get(index);
		
		int i, flag=0;
		if(ams==null) {
			System.out.println(ams);
			throw new Product_Unavailable();
			//return 1;	
		}
		for (i=0;i<arr.size(); i++) {
			
			Object o= (amacon_store) arr.get(i);
			
			if(((amacon_store) o).product.contentEquals(ams.product)) {
				
				int cost=(((amacon_store) o).price)*quan;
				
				if(quan>((amacon_store) o).quantity) {
					System.out.println("Not enough amount available in store.\n");
					throw new Product_Unavailable();
					//return 2;
				}

				if(c<cost) {
					System.out.println("Not enough amount available in customer's wallet.\n");
					throw new Product_Unavailable();
					//return 3;
				}

				flag=1;
				
				
				
				temp_ams.category=((amacon_store) o).category;
				temp_ams.subcategory=((amacon_store) o).subcategory;
				temp_ams.product=((amacon_store) o).product;
				temp_ams.price=((amacon_store) o).price;
				temp_ams.quantity=quan;
				
				obj.ams.add(temp_ams);

				((amacon_store) o).updateValues(((amacon_store) o).price, ((amacon_store) o).quantity-quan);
				
				obj.upd_n_items(quan);
				obj.upd_rem_funds(cost);
				obj.upd_total_cost(cost);
				
				return 0;
			}
		}
		if(flag==0) {
			
			System.out.println("No Such item in store.");
			throw new Product_Unavailable();
			//return 4;
		}
		return 0;
	}
	
	/////////////////////////////////////
	
	public static void modify(ArrayList<amacon_store> arr, String prod) {
		
		Scanner sc= new Scanner(System.in);
		
		
		amacon_store ams= new amacon_store();
		ams=search(arr, prod);
		int i;
		
		if(ams==null) {
			throw new Product_Unavailable();
			
		}
		
		System.out.println("Enter the new price of "+prod+".");
		int price= sc.nextInt();

		System.out.println("Enter  new quantity of "+prod+".");
		int amount= sc.nextInt();

		for (i=0;i<arr.size(); i++) {
			
			Object o= (amacon_store) arr.get(i);
			
			if(((amacon_store) o).product.contentEquals(ams.product)) {
		
				((amacon_store) o).updateValues(price, amount);
				System.out.println("The product has been modified.");
			}
		}

		ams.print_category();ams.print_subcategory();
		ams.print_product();ams.print_price();
		ams.print_quantity();
		System.out.println();
		
		//return 0;
	}
	
	//////////////////////////////////////
	
	public static amacon_store search(ArrayList<amacon_store> arr,String str){
		
		int i=0;
		for (i=0;i<arr.size(); i++) {
				
			Object o= (amacon_store) arr.get(i);
			
			if(((amacon_store) o).product.contentEquals(str)) {
				
				System.out.println("The path of the product is: ");
				((amacon_store) o).print_category();System.out.printf(">");
				((amacon_store) o).print_subcategory();System.out.printf(">");
				((amacon_store) o).print_product();System.out.println("");
				System.out.printf("The price is: ");
				((amacon_store) o).print_price();System.out.println("");
				System.out.printf("The quantity is: ");
				((amacon_store) o).print_quantity();System.out.println("");System.out.println("");
				return (amacon_store) arr.get(i);
			}
		}
		
		System.out.println("The item is not available in the store.\n\n");
		
		throw new Product_Unavailable();
		//return null;
		
		
	}
	
	///////////////////////////////////////////
	
	public static int delete_object(ArrayList<amacon_store> arr, String path) {
		
		String[] arrOfStr = path.split(">", 0);
		String cat=arrOfStr[0];
		String subcat=arrOfStr[1];
		
		int i, flag=0;
		//System.out.println(arrOfStr.length);
		
		int j=0;
		if(arrOfStr.length==2) {
			for(i=0;i<arr.size(); i++) {
				
				Object o=(amacon_store) arr.get(i);
				if(((amacon_store) o).subcategory.contentEquals(subcat)) {
					flag=1;
					arr.remove(i);
					i--;
					System.out.println("The item has been removed.");
				}
			}
		}		
		
		else if(arrOfStr.length==3) {
			String strprod = arrOfStr[2];
			
			for(i=0;i<arr.size(); i++) {
				
				Object o=(amacon_store) arr.get(i);
				if(((amacon_store) o).product.contentEquals(strprod)) {
					flag=1;
					arr.remove(i);
					System.out.println("The item has been removed.");
				}
			}
		}
		if(flag==0) {
			
			System.out.println("Invalid Path");
			throw new Invalid_Path();
			//return 1;
		}
		
		return 0;
	}
	
	////////////////////////////
	
	public static int insert_object(ArrayList<amacon_store> arr, String path , String strprod, int pri, int qua) throws IOException {
		
		Scanner sc= new Scanner(System.in);
		
		int i, flag=0;
		
		String[] arrOfStr = path.split(">", 0);
		String cat=arrOfStr[0];
		String subcat=arrOfStr[1];
		
		for(i=0;i<arr.size(); i++) {
			
			Object o=(amacon_store) arr.get(i);
			
			if(((amacon_store)o).product.contentEquals(strprod) && ((amacon_store)o).quantity==0) {
				
				//flag=1;
				System.out.println("Product already contained but quantity is 0. Adding Quantity");
				break;
					
			}
			
			else if(((amacon_store)o).product.contentEquals(strprod)) {
				flag=1;
				System.out.println("Product already contained.");
				throw new Product_already_available();
				//return 1;	
			}
		}
		if(flag==0) {

			amacon_store ams= new amacon_store();
			ams.category=cat;
			ams.subcategory=subcat;
			ams.product=strprod;
			
			ams.updateValues(pri, qua);
			arr.add(ams);
			
		}
		return 0;
	}
	///////////////////////////////////????/////////
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
		int TEMP=4;
		Scanner sc= new Scanner(System.in);
		
		amacon_store_array store_data= new amacon_store_array();
		
		
		
		try{
			file= new File("database.txt");
			
		}
		catch(Exception e) {
			System.out.println("Couldn't find the file");
		}
		X= new Scanner(file);
		
		
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
			store_data.arr.add(ams);
		}

		
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
		
		if((file= new File("Database.txt")).exists()) {


			FileInputStream fi_database = new FileInputStream(new File("Database.txt"));
			ObjectInputStream oi_database = new ObjectInputStream(fi_database);
			
			store_data=(amacon_store_array) oi_database.readObject();
			
			oi_database.close();
			fi_database.close();
		}

		
		int choice2=10;
		
		System.out.println("Press 1 to be an Administrator.");
		System.out.println("Press 2 to be an Customer.");
		System.out.println("Press 0 to terminate.");
		int choice= sc.nextInt(), i=0;
		
		int index=-1;
		
		while(choice!=0) {
			
			int flag=0;
			
			while(choice==1) {

				System.out.println("Press 1 to Insert a product/Category.");
				System.out.println("Press 2 to Delete a product/Category.");
				System.out.println("Press 3 to Search for a product.");
				System.out.println("Press 4 to Modify a product.");
				System.out.println("Press 5 to Exit as an Administrator.");
				choice2= sc.nextInt();
				
				if(choice2==5) {
					break;
				}
				else if(choice2==1) {
					//Insert Object
				
					String str_path;
					String str_prod;
					System.out.println("Enter Path.");
					str_path= sc.next();
					System.out.println("Enter Product.");
					str_prod= sc.next();
					System.out.println("Enter the Price of the Product.");
					int pri= sc.nextInt();
					System.out.println("Enter the Quantity of the Product.");
					int qua= sc.nextInt();

					TEMP=insert_object(store_data.arr, str_path,str_prod, pri, qua);
					
						
				}
				else if(choice2==2) {
					//Delete Object
					
					String str_path;
					System.out.println("Enter Path.");
					str_path= sc.next();
					TEMP=delete_object(store_data.arr, str_path);
				
				}
				else if(choice2==3) {
					//Search Object
					
					String str_prod;
					System.out.println("Enter Product to Search for.");
					str_prod= sc.next();
					amacon_store ams= new amacon_store();
					ams=search(store_data.arr, str_prod);
				
				}
				else if(choice2==4) {
					//Modify Object
					System.out.println("Enter the Product to modify.");
					String prod= sc.next();

					modify(store_data.arr, prod);
				}
				
			}
			
			
			
			
			
			System.out.println("Enter Your Username.");
			String user= sc.next();

			customer_cart cust_obj2= new customer_cart();
			
			for(i=0;i<cust_arr_obj.customer_arr.size(); i++) {
				
				Object o=(customer_cart) cust_arr_obj.customer_arr.get(i);
				if(((customer_cart) o).user.contentEquals(user)) {
					flag=1;
					System.out.println("\nUser already registered.");
					index=i;
					cust_obj2=(customer_cart) o;
					break;	
				}
			}
			if(flag==0) {
				System.out.println("\nRegistered a new User.");
				
				cust_obj2.set_user(user);
				cust_arr_obj.customer_arr.add(cust_obj2);
				index=i;
			}
			
		
			
			
			
			
			
			while(choice==2) {

				
				System.out.println("Press 1 to Add Funds. Initial Funds 10000");
				System.out.println("Press 2 to Add a product to the Cart.");
				System.out.println("Press 3 to Check-Out Cart.");
				System.out.println("Press 4 to Recall their Cart.");
				System.out.println("Press 5 Exit as a Customer.");
				int choice3= sc.nextInt();
				
				if(choice3==5) {
					
					cust_obj2.ams.clear();
					
					break;
				}
				if(choice3==1) {
					//add funds
				
					System.out.println("Enter The amount to add in wallet.");
					int amount= sc.nextInt();
					cust_obj2.add_funds(amount);
					
					
					//cust_arr_obj.customer_arr.remove(cust_obj2);
					//cust_obj2.add_funds(amount);
					//cust_arr_obj.customer_arr.add(cust_obj2);
					
				
				}
				if(choice3==2) {
					//add product (sale)
					
					System.out.println("Enter the product to add in the cart.");
					String str_prod= sc.next();
					System.out.println("Enter the amount to be added in the cart.");
					int amount= sc.nextInt();

					TEMP=sale(store_data.arr, cust_arr_obj, index,str_prod, amount, cust_obj2.rem_funds);
					
					
				}
				if(choice3==3 || cust_obj2.rem_funds==0) {
					//Check-Out
					
					System.out.println("**************************");
					System.out.printf("The Number of Items Bought: "+cust_obj2.n_items+"\n");
					System.out.printf("Total cost of the Bought Items: "+cust_obj2.total_cost +"\n\n");
					System.out.println("Purchase Summary:");
					
					//System.out.println("Here!!");
					for(i=0; i <cust_obj2.ams.size(); i++){

						Object o=(amacon_store) cust_obj2.ams.get(i);
						((amacon_store) o).print_category();((amacon_store) o).print_subcategory();
						((amacon_store) o).print_product();((amacon_store) o).print_price();
						((amacon_store) o).print_quantity();
						System.out.println();
					
					}
					for(i=0; i <cust_arr_obj.customer_arr.size(); i++){

						Object o=(customer_cart) cust_arr_obj.customer_arr.get(i);

						System.out.println();
					
					}
					
					System.out.printf("Remaining Funds: %d\n",cust_obj2.rem_funds);
					System.out.println("**************************");
					
					cust_obj2.ams.clear();
					
					
					break;
				}
				if(choice3==4) {
					//recall purchases
					
					int j=0;
					Object oj=(customer_cart) cust_arr_obj.customer_arr.get(index);
					
					System.out.println(((customer_cart)oj).ams.size());
					for(j=0;j<((customer_cart)oj).ams.size();j++) {
						
						Object o= (amacon_store) cust_obj2.ams.get(j);
						
						String tcategory=((amacon_store) o).category;
						String tsubcategory= ((amacon_store) o).subcategory;
						String tproduct= ((amacon_store) o).product;
						int tprice= ((amacon_store) o).price;
						int tquantity= ((amacon_store) o).quantity;

						System.out.printf("%s %s %s %d %d\n",tcategory,tsubcategory, tproduct, tprice, tquantity);
						
					}	
						
					
					System.out.println("\n");
					
				}

			
			}
			
			System.out.println("Press 1 to be an Administrator.");
			System.out.println("Press 2 to be an Customer.");
			System.out.println("Press 0 to terminate.");
			choice= sc.nextInt();
			
			
			if(choice==0) {
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
				System.out.println("The total Revenue generated by Amacon is: ");
				System.out.println(cust_obj2.total_cost+"\n");
				
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
				
				FileOutputStream fo_database = new FileOutputStream(new File("Database.txt"));
				ObjectOutputStream oo_database = new ObjectOutputStream(fo_database);
			
				oo_database.writeObject(store_data);
					
				oo_database.close();
				fo_database.close();
								
				FileOutputStream fo_customers = new FileOutputStream(new File("Users.txt"));
				ObjectOutputStream oo_customers = new ObjectOutputStream(fo_customers);
					
				oo_customers.writeObject(cust_arr_obj);
				oo_customers.close();
				fo_customers.close();
				
				break;
			}
		}
		

	
	}
}
