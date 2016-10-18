package org.bitholic.util;

import java.net.URL;

/**
 * Created by bitholic on 2016/10/17.
 */
public class WikiAPI {
    private String lang = "en";
    private String parameters = "indexpageids&format=json&action=query&titles=Hearthstone: Heroes of Warcraft";
    private String URL = "https://" + lang + ".wikipedia.org/w/api.php?" + parameters;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString(){
        return URL;
    }
}
