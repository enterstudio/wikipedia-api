package org.bitholic.wikipedia;

import com.google.gson.Gson;
import org.bitholic.bean.WikiData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by bitholic on 2016/10/16.
 */
public class Wikipedia implements Serializable {
    //necessary parameter
    private final String titles;
    //optional parameters
    private final String prop;
    private final String lang;
    private final String action;
    private final String wikiURL;

    public static class Query {
        private final String titles;

        private String lang = "en";
        private String action = "query";
        private String type = "plaintext";
        private String prop = "&prop=";

        public Query(String titles){
            this.titles = titles;
        }

        public Query setLang(String lang){
            this.lang = lang;
            return this;
        }

        public Query addProp(String prop){
            //categories: 获取页面的非隐藏的所有类别(最多500个), 默认参数: &clshow=!hidden&cllimit=max
            //extlinks: 获取页面的所有外部链接, 默认参数: &elexpandurl=true&ellimit=max
            //extracts: 获取页面的纯文本或有限的HTML内容提取物, 如果exintro=true, 那么只返回首个章节前的内容(即介绍) 默认参数: explaintext=true
            //langlinks: 获取页面所支持的语言列表 默认参数: &llprop=url|langname&lllimit=max
            //links: 获取页面上的所有链接
            //redirects: 获取至此页面的所有重定向 默认参数: &rdlimit=max
            //references:
            if(this.prop.equals("&prop=")){
                this.prop += prop;
            }else{
                this.prop += "|" + prop;
            }
            return this;
        }

        public Wikipedia build(){
            return new Wikipedia(this);
        }
    }

    private Wikipedia(Query query){
        titles = query.titles;
        prop = query.prop;
        action = query.action;
        lang = query.lang;
        //private String wikiURL = "https://" + lang + ".wikipedia.org/w/api.php?format=json&indexpageids&action=" + action + parameters;
        wikiURL = "https://" + lang + ".wikipedia.org/w/api.php?format=json&indexpageids&action=" + action + "&titles=" + titles + prop;
    }

    public WikiData execute(){
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) Ahttp://10.3.8.211/ppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
        SocketAddress addr = new InetSocketAddress("127.0.0.1", 1080);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        Connection connection = Jsoup.connect(this.wikiURL)
                .proxy(proxy)
                .ignoreContentType(true)
                .userAgent(userAgent)
                .method(Connection.Method.GET);
        try {
            Connection.Response response = connection.execute();
            WikiData wikiData = new Gson().fromJson(response.body(), WikiData.class);
            return wikiData;
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Wikipedia wikipedia = new Wikipedia.Query("MacBook_Pro").setLang("en").addProp("extracts|langlinks").build();
        System.out.println(wikipedia.wikiURL);
        System.setProperty("java.net.useSystemProxies", "true");
        System.out.println("detecting proxies");
        List l = null;
        try {
            l = ProxySelector.getDefault().select(new URI("http://foo/bar"));
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (l != null) {
            for (Iterator iter = l.iterator(); iter.hasNext();) {
                java.net.Proxy proxy = (java.net.Proxy) iter.next();
                System.out.println("proxy type: " + proxy.type());

                InetSocketAddress addr = (InetSocketAddress) proxy.address();

                if (addr == null) {
                    System.out.println("No Proxy");
                } else {
                    System.out.println("proxy hostname: " + addr.getHostName());
                    System.setProperty("http.proxyHost", addr.getHostName());
                    System.out.println("proxy port: " + addr.getPort());
                    System.setProperty("http.proxyPort", Integer.toString(addr.getPort()));
                }
            }
        }
        WikiData wikiData = wikipedia.execute();
        Integer pageid = wikiData.getQuery().getPageids().get(0);
        System.out.println(pageid);
        for(int i = 0; i < 10; i++){
            System.out.println(wikiData.getQuery().getPages().get(pageid.toString()).getLanglinks().get(i).getLocalName());
        }

//        Wikipedia wikipedia = new Wikipedia();
//
//        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
//        Connection connection = Jsoup.connect(wikipedia.wikiURL).ignoreContentType(true)
//                .userAgent(userAgent)
//                .method(Connection.Method.GET);
//        try {
//            Connection.Response response = connection.execute();
//            Gson gson = new Gson();
//            System.out.println(wikipedia.wikiURL);
//            WikiData wikiData = gson.fromJson(response.body(), WikiData.class);
//            //ExtractsBean extracts = gson.fromJson(response.body(), ExtractsBean.class );
//            Integer pageid = wikiData.getQuery().getPageids().get(0);
//            System.out.println(wikiData.getQuery().getPages().get(pageid.toString()).getExtract());
//        } catch (IOException ex){
//            ex.printStackTrace();
//        }
    }
}
