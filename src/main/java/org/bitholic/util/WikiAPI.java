package org.bitholic.util;

/**
 * Created by bitholic on 2016/10/17.
 */

/**
 * a singleton class for wikipedia-api config.
 */
public class WikiAPI {
    private final static WikiAPI WIKI_API = new WikiAPI();

    private String lang = "en";
    private String action = "query";
    private String parameters = "&indexpageids&titles=Hearthstone: Heroes of Warcraft";
    private String URL = "https://" + lang + ".wikipedia.org/w/api.php?format=json&action=query" + parameters;

    private WikiAPI(){}

    public static WikiAPI getInstance(){
        return WIKI_API;
    }

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

/**
 * Query参数
 * prop(设置要查询页面的属性)
 *      categories - List all categories the pages belong to.
 *      categoryinfo - Returns information about the given categories.
 *      extracts - 返回指定界面的纯文本或有限的HTML页面内容
 *          exintro(Boolean)- 只返回首个章节前的内容,即介绍
 *          explaintext(Boolean) - 返回纯文本而非HTML页面,默认为true
 *          exsectionformat - 纯文本模式下返回文本章节的格式(默认为wiki格式)
 *              wiki - wiki格式的样本为: ===章节===
 *              plain - 无格式
 *              raw - 此模块的内部表示模块
 *
 *
 *      images - Returns all files contained on the given pages.
 *      links - Return all links from the given pages.
 *      pageimages - Returns information about images on the page, such as thumbnail and presence of photos.
 *
 *
 */
