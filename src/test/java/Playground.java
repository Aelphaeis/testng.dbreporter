import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TemporaryFolder;
import org.sqlite.JDBC;
import org.testng.TestNG;

/**
 * This class is here for prototyping and should be removed.
 * 
 * @author morain
 *
 */
@Deprecated
public class Playground {
	
	private static final String CONN_STR_TEMPLATE = "jdbc:sqlite:%s/test.db";
	private static final String PROTO = "src/test/resources/prototype-test.xml";
	
	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();
	
	@Rule
	public final ErrorCollector collector = new ErrorCollector();
	
	@Before
	public void setup() {
		String location = folder.getRoot().toString();
		String connString = String.format(CONN_STR_TEMPLATE, location);
		assumeTrue(JDBC.isValidURL(connString));
		
		System.setProperty("reporting.url", connString);
	}
	
	@Test
	public void test() {
		TestNG.privateMain(new String[] { PROTO }, null);
	}
}
