package org.bitholic.wikipedia;

import com.google.gson.Gson;
import org.bitholic.bean.ExtractsBean;
import org.bitholic.util.WikiAPI;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by bitholic on 2016/10/16.
 */
public class Wikipedia {
    private static String URL = "";
    public static void main(String[] args) {
        String apiBase = "https://en.wikipedia.org/w/api.php?format=json&action=query&indexpageids";

        String api = apiBase + "&titles=Hearthstone&prop=extracts";

        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
        Connection connection = Jsoup.connect(api).ignoreContentType(true)
                .userAgent(userAgent)
                .method(Connection.Method.GET);
        try {
            Connection.Response response = connection.execute();
            Gson gson = new Gson();
            WikiAPI wikiAPI = new WikiAPI();
            System.out.println(wikiAPI);
            //ExtractsBean extracts = gson.fromJson(response.body(), ExtractsBean.class );
            //System.out.println(extracts);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }


}
