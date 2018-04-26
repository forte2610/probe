package com.titan.probe.helpers;

import com.titan.probe.models.Product;
import com.titan.probe.parsers.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Crawler {
    private String keyword;
    public List<Product> productList = new ArrayList<>();

    public Crawler(String keyword) {
        this.keyword = keyword;

    }

    public JSONObject process() {
        long start = System.currentTimeMillis();
        String strDienMay = keyword;
        String strTheGioiDiDong = keyword.replaceAll(" ", "+");
        String strVienThongA = keyword;
        String strFPTSHOP = keyword.replaceAll(" ", "-");

        VendorParser fptParser = new FPTParser(strFPTSHOP);
        VendorParser xanhParser = new XANHParser(strDienMay);
        VendorParser tgddParser = new TGDDParser(strTheGioiDiDong);
        VendorParser vienthongAParser = new VienthongAParser(strVienThongA);
        try {
            ExecutorService service = Executors.newFixedThreadPool(4);
            service.submit(new CrawlerRunnable(fptParser, productList));
            service.submit(new CrawlerRunnable(xanhParser, productList));
            service.submit(new CrawlerRunnable(tgddParser, productList));
            service.submit(new CrawlerRunnable(vienthongAParser, productList));
            service.shutdown();
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();

        Collections.sort(productList, new ProductComparator());

        for (Product _product : productList) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", _product.getName());
            jsonObject.put("image", _product.getImages());
            jsonObject.put("price", _product.getPrice());
            jsonObject.put("description", _product.getDescription());
            jsonObject.put("url", _product.getVendorURL());
            jsonArray.put(jsonObject);

        }
        long end = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        result.put("keyword", this.keyword);

        DecimalFormat formatter = new DecimalFormat("###,###");

        result.put("elapsed", formatter.format(end - start));
        result.put("size", productList.size());
        result.put("results", jsonArray);
        return result;
    }
}
