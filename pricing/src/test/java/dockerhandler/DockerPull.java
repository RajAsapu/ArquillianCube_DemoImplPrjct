package dockerhandler;

import java.io.File;

public class DockerPull extends DockerConstants{

    String[] gitrepos={priceUi_gitRepo,priceEngine_gitRepo,priceDataMock_gitRepo,priceConfigServices_gitRepo};

    String rootRepoDir="./Repos/";

    /*
      To do : Handle exceptions
     */
    public void cloneRepositories()throws Exception
    {
            File rootDir=new File(rootRepoDir);

            if(rootDir.exists())
            {
              ExecuteCommands.exec("rm -r " + rootRepoDir);
            }

            rootDir.mkdir();
            /*
             *  pull repositories
             */
            for(String tempUrl:gitrepos)
            {
                String[] folder= tempUrl.split("/");
                String path=rootRepoDir+folder[folder.length-1];

                ExecuteCommands.exec("mkdir "+path);
                ExecuteCommands.exec("git clone "+tempUrl+" "+path);
            }

    }

    /*
     * Method to copy index lookup table from dev to the price db container
     */

    public void loadIndexLookupTables()throws Exception
    {
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
