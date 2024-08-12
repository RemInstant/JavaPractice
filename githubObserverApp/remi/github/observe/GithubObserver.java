package remi.github.observe;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

import remi.github.GithubUser;

public abstract class GithubObserver
{
    public GithubUser getUserInfo(String username)
        throws  URISyntaxException,
                IOException,
                ParseException
    {
        var map = loadUserInfo(username);
        
        int id = Integer.parseInt(map.get("id"));
        int publicRepos = Integer.parseInt(map.get("public_repos"));
        
        String login = map.get("login");
        String name = map.get("name");
        String createdAt = map.get("created_at");
        String updatedAt = map.get("updated_at");
        
        login = login.substring(1, login.length() - 1);
        name = name.substring(1, name.length() - 1);
        createdAt = createdAt.substring(1, createdAt.length() - 1);
        updatedAt = updatedAt.substring(1, updatedAt.length() - 1);
        
        return new GithubUser(id, login, name, publicRepos, createdAt, updatedAt);
    }
    
    protected abstract Map<String, String> loadUserInfo(String username)
        throws  URISyntaxException,
                IOException;
    
}
