package amacon2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ cart_serial.class, database_serial.class, delete_testing.class, insert_testing.class,
		 sale_testing.class, search_testing.class })
public class AllTests {

}
