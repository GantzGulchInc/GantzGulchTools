package com.gantzgulch.tools.hibernate;

import java.util.List;
import java.util.Map;

public interface SearchRequest {

    int getPageSize();

    int getPageNumber();

    Map<String,String> getSearchFields();

    List<String> getOrderFields();

}
