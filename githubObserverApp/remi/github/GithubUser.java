package remi.github;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GithubUser {
    
    private int id;
    private String login;
    private String name;
    private int publicRepos;  
    // String[] followers?
    private Date createdAt;
    private Date updatedAt;
    
    public GithubUser(int id, String login, String name, int publicRepos, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.login = login;
        this.name = name;
        this.publicRepos = publicRepos;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public GithubUser(int id, String login, String name, int publicRepos, String createdAt, String updatedAt) 
        throws ParseException
    {
        this.id = id;
        this.login = login;
        this.name = name;
        this.publicRepos = publicRepos;
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdAt = parser.parse(createdAt.substring(0, 10) + " " + createdAt.substring(11));
        this.updatedAt = parser.parse(updatedAt.substring(0, 10) + " " + updatedAt.substring(11));
    }
    
    public int getID() { return id; }
    public String getLogin() { return login; }
    public String getName() { return name; }
    public int getPublicReposCount() { return publicRepos; }
    public Date getCreationDate() { return createdAt; }
    public Date getLastUpdateDate() { return updatedAt; }
}
