package remi.github.observe;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.util.HashMap;
import java.util.Map;

public class HttpClientGithubObserver extends GithubObserver
{
    @Override
    protected Map<String, String> loadUserInfo(String username)
        throws  URISyntaxException,
                IOException
    {
        HttpResponse<String> resp;
        
        try (HttpClient clnt = HttpClient.newHttpClient()) {
            HttpRequest req = HttpRequest
                                .newBuilder(new URI("https://api.github.com/users/" + username))
                                .build();
            resp = clnt.send(req, HttpResponse.BodyHandlers.ofString()); // automatically reads charset from header
        }
        catch (InterruptedException ex) {
            throw new IOException("Request failed due to interruption", ex);
        }
        
        if (resp.statusCode() != 200) {
            throw new IOException("Cannot load resources. Code: " + resp.statusCode());
        }
        
        Map<String, String> map = new HashMap<>();
        
        String str = resp.body();
        str = str.substring(1, str.length() - 1);
        
        String[] pairs = str.split(",\"");
        pairs[0] = pairs[0].substring(1);
        
        for (var elem : pairs) {
            String[] keyVal = elem.split( "\":");
            
            if (keyVal[1].startsWith("\"") && keyVal[1].endsWith("\"")) {
                keyVal[1] = keyVal[1].substring(1, keyVal[1].length() - 1);
            }
            
            map.put(keyVal[0], keyVal[1]);
        }
        
        return map;
    }
}

