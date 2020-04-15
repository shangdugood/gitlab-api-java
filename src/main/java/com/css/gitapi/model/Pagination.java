package com.css.gitapi.model;

/**
 * @author shangdu
 * @version 1.0
 * @date 2020/4/9 16:23
 */
public class Pagination {
    /**
     * 当前获取页数，默认第一页
     */
    private Integer page = 1;
    /**
     * 每页显示数量，默认20，最大100
     */
    private Integer per_page = 20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }
}
