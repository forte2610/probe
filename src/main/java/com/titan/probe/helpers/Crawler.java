/*
 *  Crawler
 *
 *  Author: 1412093
 *
 *  Scrapes through vendor pages to find products matching the user's keyword
 */

package com.titan.probe.helpers;

import com.titan.probe.models.Product;
import com.titan.probe.models.ResultObject;
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
    public List<Product> productList;

    public Crawler(String keyword) {
        this.keyword = keyword;
        productList = new ArrayList<>();
    }

    public ResultObject process() {
        long start = System.currentTimeMillis();
        String strDienMay = keyword;
        String strTheGioiDiDong = keyword.replaceAll(" ", "+");
        String strVienThongA = keyword;
        String strFPTSHOP = keyword.replaceAll(" ", "-");
        // String strViettel = keyword;
        String strTiki = keyword.replaceAll(" ", "+");

        VendorParser fptParser = new FPTParser(strFPTSHOP);
        VendorParser xanhParser = new XANHParser(strDienMay);
        VendorParser tgddParser = new TGDDParser(strTheGioiDiDong);
        VendorParser vienthongAParser = new VienthongAParser(strVienThongA);
        // VendorParser viettelParser = new ViettelParser(strViettel);
        VendorParser tikiParser = new TikiParser(strTiki);
        try {
            ExecutorService service = Executors.newFixedThreadPool(5);
            service.submit(new CrawlerRunnable(fptParser, productList));
            service.submit(new CrawlerRunnable(xanhParser, productList));
            service.submit(new CrawlerRunnable(tgddParser, productList));
            service.submit(new CrawlerRunnable(vienthongAParser, productList));
            service.submit(new CrawlerRunnable(tikiParser, productList));
            // service.submit(new CrawlerRunnable(viettelParser, productList));
            service.shutdown();
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        ResultObject result = new ResultObject();
        Collections.sort(productList, new ProductComparator());


        for (Product _product : productList) {

            result.getResultList().add(_product);

        }

        long end = System.currentTimeMillis();

        result.setKeyword(this.keyword);
        result.setCount(result.getResultList().size());
        result.setPages(1);

        DecimalFormat formatter = new DecimalFormat("###,###");

        result.setTimeElapsed(formatter.format(end - start));

        return result;
    }
}
