package org.bitholic.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bitholic on 2016/10/17.
 */
public class WikiData{
    private String batchcomplete;
    private Query query;
    private Map<String, Integer> limits;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    @Override
    public String toString(){
        return "batchcomplete: " + batchcomplete + ", query: " + query;
    }

    public static class Query {
        private ArrayList<Integer> pageids;
        private Map<String, Page> pages;

        public ArrayList<Integer> getPageids() {
            return pageids;
        }

        public void setPageids(ArrayList<Integer> pageids) {
            this.pageids = pageids;
        }


        public Map<String, Page> getPages() {
            return pages;
        }

        public void setPages(Map<String, Page> pages) {
            this.pages = pages;
        }

        @Override
        public String toString(){
            return "pageids: " + pageids.toString() + ", pages: { " + pages.toString() + "}";
        }
    }

    public static class Page {
        private Integer pageid;
        private Integer ns;
        private String title;
        private String extract;
        private ArrayList<Language> langlinks;

        public Integer getPageid() {
            return pageid;
        }

        public void setPageid(Integer pageid) {
            this.pageid = pageid;
        }

        public Integer getNs() {
            return ns;
        }

        public void setNs(Integer ns) {
            this.ns = ns;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExtract() {
            return extract;
        }

        public void setExtract(String extract) {
            this.extract = extract;
        }

        public ArrayList<Language> getLanglinks() { return langlinks; }

        public void setLanglinks(ArrayList<Language> langlinks) { this.langlinks = langlinks; }

        @Override
        public String toString(){
            return "pageid: " + pageid + ", ns: " + ns + ", title: " + title + ", extract: " + extract;
        }
    }

    public static class Language {
        private String lang;
        private String url;

        @SerializedName("*") private String localName;

        public Language() { }

        public Language(String lang, String url, String localName) {
            this.lang = lang;
            this.url = url;
            this.localName = localName;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }
    }
}
