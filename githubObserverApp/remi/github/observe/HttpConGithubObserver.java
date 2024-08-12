package remi.github.observe;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConGithubObserver extends GithubObserver
{
    @Override
    protected Map<String, String> loadUserInfo(String username)
        throws  URISyntaxException,
                IOException
    {
        URL url = (new URI("https://api.github.com/users/" + username)).toURL();
        HttpURLConnection hpCon = (HttpURLConnection) url.openConnection();
        
        if (hpCon.getResponseCode() != 200) {
            throw new IOException("Cannot load resources. Code: " + hpCon.getResponseCode());
        }
        
        String contentType = hpCon.getHeaderField("Content-Type");
        String encoding = "UTF-8";
        
        if (contentType != null) {
            int st = contentType.indexOf("charset=") + 8;
            int en = contentType.indexOf(";", st);
            
            if (en == -1) {
                en = contentType.length();
            }
            
            encoding = contentType.substring(st, en);
        }
        
        Map<String, String> map = new HashMap<>();
        
        try (var in = hpCon.getInputStream()) {
            String str = new String(in.readAllBytes(), encoding);
            str = str.substring(1, str.length() - 1);
            
            String[] pairs = str.split(",\"");
            pairs[0] = pairs[0].substring(1);
            
            for (var elem : pairs) {
                String[] keyVal = elem.split( "\":");
                map.put(keyVal[0], keyVal[1]);
            }
        }
        
        return map;
    }
}
