package org.bitholic.bean;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by bitholic on 2016/10/17.
 */
public class ExtractsBean {
    private String batchcomplete;
    private Query query;

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
        private ArrayList<String> pageids;
        private Map<String, Page> pages;

        public ArrayList<String> getPageids() {
            return pageids;
        }

        public void setPageids(ArrayList<String> pageids) {
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

        @Override
        public String toString(){
            return "pageid: " + pageid + ", ns: " + ns + ", title: " + title + ", extract: " + extract;
        }
    }
}
