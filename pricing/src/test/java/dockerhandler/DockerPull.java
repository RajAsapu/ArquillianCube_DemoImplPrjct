package dockerhandler;

import org.junit.Test;

import java.io.File;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

public class DockerPull extends DockerConstants{

    String[] gitrepos={priceUi_gitRepo,priceEngine_gitRepo,priceDataMock_gitRepo,priceConfigServices_gitRepo};

    String rootRepoDir="./Repos/";

    ExecuteCommands ec=new ExecuteCommands();

    /*
      To do : Handle exceptions
     */
    public void cloneRepositories()throws Exception
    {
            File rootDir=new File(rootRepoDir);

            if(rootDir.exists())
            {
              ec.exec("rm -r " + rootRepoDir);
            }

            rootDir.mkdir();

            /*
              pull repositories
            */
            for(String tempUrl:gitrepos)
            {
                String[] folder= tempUrl.split("/");

                String path=rootRepoDir+folder[folder.length-1];

                ec.exec("mkdir "+path);
                ec.exec("git clone "+tempUrl+" "+path);
            }

    }

    public void buildPriceUi()throws Exception
    {
        /*
         To do : Build the price config ui
         */
//        String[] folder= priceUi_gitRepo.split("/");
//        String path=rootRepoDir+folder[folder.length-1];
//
//        Runtime.getRuntime().exec("sh -c \"cd ./Repos/price-configuration-ui.git\"");
//        ec.exec("ls -l");
////        ec.exec("npm install -g angular-cli");
////        ec.exec("ng build "+path);
    }

    public void loadIndexLookupTables()throws Exception
    {
        ec.exec("docker exec -it runPriceDB4.0 bash");
        ec.exec("cqlsh 10.172.5.62;");
        ec.exec("COPY wfs_pricing.index_lookup TO './indexLookUp.csv';");
        Thread.sleep(4000);
        ec.exec("exit");
        ec.exec("cqlsh");
        ec.exec("COPY wfs_pricing.index_lookup FROM './indexLookUp.csv';");
        Thread.sleep(4000);
        ec.exec("exit");

    }
}
