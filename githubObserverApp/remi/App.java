package remi;

import remi.github.GithubUser;
import remi.github.observe.*;

public class App
{    
    public static void main(String[] args)
        throws Exception
    {
        //GithubObserver obs = new HttpConGithubObserver();
        GithubObserver obs = new HttpClientGithubObserver();
        
        GithubUser user = obs.getUserInfo("reminstant");
        
        System.out.println(user.getID());
        System.out.println(user.getLogin());
        System.out.println(user.getName());
        System.out.println(user.getPublicReposCount());
        System.out.println(user.getCreationDate());
        System.out.println(user.getLastUpdateDate());
    }
    
}
