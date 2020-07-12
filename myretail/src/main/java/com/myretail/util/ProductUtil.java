package com.myretail.util;

import java.util.Map;

public class ProductUtil {

    public static String getProductName(Map map) {
        Map<String, Map> product = (Map<String, Map>) map.get("product");
        Map<String, Map> item = product.get("item");
        Map<String, String> productDescription = item.get(("product_description"));
        return productDescription.get("title");
    }
}
