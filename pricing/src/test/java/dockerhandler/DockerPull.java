package dockerhandler;

import org.junit.Test;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class DockerPull{

	private String[] gitrepos=new String[4] ;
	private String rootRepoDir = "./Repos/";
	private Enumeration<?> nameList;
	File resourceFile;
	String propName;
	InputStream in;
	Properties props;
	int temp=0;
	/*
	 * To do : Handle exceptions
	 */
	@Test
	public void cloneRepositories() throws Exception {

		/*
		 * Load properties file
		 */
		props = new Properties();
		in = getClass().getClassLoader().getResourceAsStream("repos.properties");
		props.load(in);
		nameList = props.propertyNames();

		while(nameList.hasMoreElements())
		{
			if((propName = nameList.nextElement().toString()).contains("price"))
			{
				gitrepos[temp++]= props.getProperty(propName);
			}
		}

		File rootDir = new File(rootRepoDir);
		if (rootDir.exists()) {
			ExecuteCommands.exec("rm -r " + rootRepoDir);
		}
		rootDir.mkdir();
		/*
		 * pull repositories
		 */
		for (String tempUrl : gitrepos) {
			String[] folder = tempUrl.split("/");
			String path = rootRepoDir + folder[folder.length - 1];

			ExecuteCommands.exec("mkdir " + path);
			ExecuteCommands.exec("git clone " + tempUrl + " " + path);
		}

	}

	/*
	 * Method to copy index lookup table from dev to the price db container
	 */

	public void loadIndexLookupTables() throws Exception {
		ExecuteCommands.exec("docker exec -it runPriceDB4.0 bash");
		ExecuteCommands.exec("cqlsh 10.172.5.62;");
		ExecuteCommands.exec("COPY wfs_pricing.index_lookup TO './indexLookUp.csv';");
		Thread.sleep(4000);
		ExecuteCommands.exec("exit");
		ExecuteCommands.exec("cqlsh");
		ExecuteCommands.exec("COPY wfs_pricing.index_lookup FROM './indexLookUp.csv';");
		Thread.sleep(4000);
		ExecuteCommands.exec("exit");

	}
}
