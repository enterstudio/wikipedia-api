package org.bitholic.wikipedia;

import com.google.gson.Gson;
import org.bitholic.bean.WikiData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by bitholic on 2016/10/16.
 */
public class Wikipedia {
    //private final String queryWords;
    private String lang = "en";
    private String action = "query";
    private String parameters = "&titles=Hearthstone: Heroes of Warcraftdfd&prop=extracts&explaintext=true&exsectionformat=wiki";
    private String wikiURL = "https://" + lang + ".wikipedia.org/w/api.php?format=json&indexpageids&action=" + action + parameters;

    public static class Builder {
        private final String queryWords;

        private String lang = "en";
        private String action = "query";

        public Builder(String queryWords){
            this.queryWords = queryWords;
        }

        public Builder setLang(String lang){
            this.lang = lang;
            return this;
        }
    }

    public static void main(String[] args) {
        Wikipedia wikipedia = new Wikipedia();

        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
        Connection connection = Jsoup.connect(wikipedia.wikiURL).ignoreContentType(true)
                .userAgent(userAgent)
                .method(Connection.Method.GET);
        try {
            Connection.Response response = connection.execute();
            Gson gson = new Gson();
            System.out.println(wikipedia.wikiURL);
            WikiData wikiData = gson.fromJson(response.body(), WikiData.class);
            //ExtractsBean extracts = gson.fromJson(response.body(), ExtractsBean.class );
            Integer pageid = wikiData.getQuery().getPageids().get(0);
            System.out.println(wikiData.getQuery().getPages().get(pageid.toString()).getExtract());
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
