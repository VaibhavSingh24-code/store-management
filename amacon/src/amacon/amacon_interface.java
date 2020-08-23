package amacon;

import java.io.*;
import java.util.*;



class amacon_store implements Serializable {
	
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
	int total_cost;
	int n_items;
	int rem_funds;
	
	customer_cart(){
	
		total_cost=0;
		n_items=0;
		rem_funds=10000;
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
}



////////////////////////////////////////////
public class amacon_interface {

	private static Scanner X;
	//private static BufferedWriter br;
	/////////////////////////////////////
	
	private static int sale(ArrayList arr,customer_cart obj,String str, int quan, int c) {
		
		amacon_store ams= new amacon_store();
		ams=search(arr, str);
		
		amacon_store temp_ams= new amacon_store();
		
		int i;
		if(ams==null) {
			return 0;	
		}
		for (i=0;i<arr.size(); i++) {
			
			Object o= (amacon_store) arr.get(i);
			
			if(((amacon_store) o).product.contentEquals(ams.product)) {
				
				int cost=(((amacon_store) o).price)*quan;
				
				if(quan>((amacon_store) o).quantity) {
					System.out.println("Not enough amount available in store.\n");
					return 0;
				}

				if(c<cost) {
					System.out.println("Not enough amount available in customer's wallet.\n");
					return 0;
				}

				
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
			
			}
		}

		return 0;
	}
	
	
	
	
	
	/////////////////////////////////////
	public static int modify(ArrayList arr) {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the Product to modify.");
		String prod= sc.next();
		
		amacon_store ams= new amacon_store();
		ams=search(arr, prod);
		int i;
		
		if(ams==null) {
			return 0;
			
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
		
		return 0;
	}
	//////////////////////////////////////
	
	
	
	
	//////////////////////////////////////
	
	public static amacon_store search(ArrayList arr,String str){
		
		int i=0, flag=0;
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
		
		return null;
		
		
	}
	
	
	///////////////////////////////////////////
	
	public static int delete_object(ArrayList arr, String path) {
		
		String[] arrOfStr = path.split(">", 0);
		String cat=arrOfStr[0];
		String subcat=arrOfStr[1];
		
		int i, flag=0;
		System.out.println(arrOfStr.length);
		
		int j=0;
		if(arrOfStr.length==2) {
			for(i=0;i<arr.size(); i++) {
				
				Object o=(amacon_store) arr.get(i);
				if(((amacon_store) o).subcategory.contentEquals(subcat)) {
					
					arr.remove(i);
					flag=1;
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
		
		}
		return 0;
	}
	
	
	public static int insert_object(ArrayList arr, String path , String strprod) throws IOException {
		
		Scanner sc= new Scanner(System.in);
		
		int i, flag=0;
		
		String[] arrOfStr = path.split(">", 0);
		String cat=arrOfStr[0];
		String subcat=arrOfStr[1];
		
		for(i=0;i<arr.size(); i++) {
			
			Object o=(amacon_store) arr.get(i);
			if(arr.contains(strprod)) {
				flag=1;
				System.out.println("Product already contained.");
				break;	
			}
		}
		if(flag==0) {

			amacon_store ams= new amacon_store();
			ams.category=cat;
			ams.subcategory=subcat;
			ams.product=strprod;
			
			System.out.println("Enter the Price of the Product.");
			int pri= sc.nextInt();
			//ams.price=pri;
			System.out.println("Enter the Quantity of the Product.");
			int qua= sc.nextInt();
			//ams.quantity=qua;
			ams.updateValues(pri, qua);
			arr.add(ams);
			
			
			arr.add(ams);
		}
		
		return 0;
	}
	///////////////////////////////////????/////////
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int TEMP=1;
		
		Scanner sc= new Scanner(System.in);
		
		ArrayList arr= new ArrayList();
		try{
			X= new Scanner(new File("database.txt"));
		}
		catch(Exception e) {
			System.out.println("Couldn't find the file");
		}
		
		while(X.hasNext()) {
			
			amacon_store ams= new amacon_store();
			
			String category= X.next();
			String subcategory= X.next();
			String product= X.next();
			int price= Integer.parseInt(X.next());
			int quantity= Integer.parseInt(X.next());
			
			ams.category=category;
			ams.subcategory=subcategory;
			ams.product=product;
			ams.price=price;
			ams.quantity=quantity;
			arr.add(ams);
		}

		customer_cart cust_obj= new customer_cart();
		
		
		System.out.println("Press 1 to be an Administrator.");
		System.out.println("Press 2 to be an Customer.");
		System.out.println("Press 0 to terminate.");
		int choice= sc.nextInt();
		
		while(choice!=0) {
			
			while(choice==1) {

				System.out.println("Press 1 to Insert a product/Category.");
				System.out.println("Press 2 to Delete a product/Category.");
				System.out.println("Press 3 to Search for a product.");
				System.out.println("Press 4 to Modify a product.");
				System.out.println("Press 5 to Exit as an Administrator.");
				int choice2= sc.nextInt();
				
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
					TEMP=insert_object(arr, str_path,str_prod);
				
				}
				else if(choice2==2) {
					//Delete Object
					
					String str_path;
					System.out.println("Enter Path.");
					str_path= sc.next();
					TEMP=delete_object(arr, str_path);
				
				}
				else if(choice2==3) {
					//Search Object
					
					String str_prod;
					System.out.println("Enter Product to Search for.");
					str_prod= sc.next();
					amacon_store ams= new amacon_store();
					ams=search(arr, str_prod);
				
				}
				else if(choice2==4) {
					//Modify Object
					TEMP=modify(arr);
				}
				
			}
			
			while(choice==2) {

				System.out.println("Press 1 to Add Funds. Initial Funds 10000");
				System.out.println("Press 2 to Add a product to the Cart.");
				System.out.println("Press 3 to Check-Out Cart.");
				System.out.println("Press 4 Exit as a Customer.");
				int choice3= sc.nextInt();
				
				if(choice3==4) {
					cust_obj.ams.clear();
					break;
				}
				else if(choice3==1) {
					//add funds
				
					System.out.println("Enter The amount to add in wallet.");
					int amount= sc.nextInt();
					cust_obj.add_funds(amount);
				
				}
				else if(choice3==2) {
					//add product (sale)
					
					System.out.println("Enter the product to add in the cart.");
					String str_prod= sc.next();
					System.out.println("Enter the amount to be added in the cart.");
					int amount= sc.nextInt();
					TEMP=sale(arr, cust_obj, str_prod, amount, cust_obj.rem_funds);
					
				}
				else if(choice3==3 || cust_obj.rem_funds==0) {
					//Check-Out
					
					System.out.println("**************************");
					System.out.printf("The Number of Items Bought: "+cust_obj.n_items+"\n");
					System.out.printf("Total cost of the Bought Items: "+cust_obj.total_cost +"\n\n");
					System.out.println("Purchase Summary:");
					int i;
					for(i=0; i <cust_obj.ams.size(); i++){

						Object o=(amacon_store) cust_obj.ams.get(i);
						((amacon_store) o).print_category();((amacon_store) o).print_subcategory();
						((amacon_store) o).print_product();((amacon_store) o).print_price();
						((amacon_store) o).print_quantity();
						System.out.println();
					
					}
					System.out.println("**************************");
					cust_obj.ams.clear();
					break;
				}

			
			}
			
			System.out.println("Press 1 to be an Administrator.");
			System.out.println("Press 2 to be an Customer.");
			System.out.println("Press 0 to terminate.");
			choice= sc.nextInt();
			
			
			if(choice==0) {
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
				System.out.println("The total Revenue generated by Amacon is: ");
				System.out.println(cust_obj.total_cost+"\n");
				
				
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
				
				break;
			}
		}
	}
}
