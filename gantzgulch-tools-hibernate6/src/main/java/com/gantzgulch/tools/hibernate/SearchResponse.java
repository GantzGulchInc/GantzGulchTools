package com.gantzgulch.tools.hibernate;

import java.util.List;

public class SearchResponse<T> {

    private int pageSize;
    
    private int pageNumber;
    
    private int pageCount;
    
    private int itemCount;
    
    private List<T> items;
    
    public SearchResponse() {
    }

    public SearchResponse(final int pageSize, final int pageNumber, final int pageCount, final int itemCount, final List<T> items) {
        super();
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.pageCount = pageCount;
        this.itemCount = itemCount;
        this.items = items;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
    
    

}
