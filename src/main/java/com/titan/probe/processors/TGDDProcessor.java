package com.titan.probe.processors;

import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TGDDProcessor implements VendorProcessor {
    private String keyword;
    private List<Product> resultList;

    public TGDDProcessor(String sKeyword) {
        this.keyword = sKeyword;
    }

    @Override
    public void process() {
        String url = "https://www.thegioididong.com/tim-kiem?key=";

        try {
            int intPageIndex = 0;
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("listsearch").select("li");

            if (productList.size() == 0) {
                return;
            }
            for (Element product : productList) {
                int price = parsePrice(product.select("figure > strong").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.select("a > h3").text());
                    // url
                    currentProduct.setVendorURL("http://www.thegioididong.com" + product.select("a").attr("href"));
                    // image

                    String imageURL = product.select("a > img").attr("src");
                    if (!imageURL.contains("https")) {
                        imageURL = "https://" + imageURL;
                    }

                    currentProduct.setImages(imageURL);
                    // price
                    currentProduct.setPrice(price);
                    // type
                    currentProduct.setType(product.select("a > h3").text());
                    // info
                    String productInfo = product.select("figure > span").text();

                    DecimalFormat formatter = new DecimalFormat("###,###,###");

                    currentProduct.setDescription(productInfo.substring(9, productInfo.length()));

                    System.out.println("Name: " + currentProduct.getName());
                    System.out.println("Price: " + currentProduct.getPrice());
                    System.out.println("Image: " + currentProduct.getImages());
                    System.out.println("Description: " + currentProduct.getDescription());

                }

            }

        } catch (Exception ex) {
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            System.out.println(ex + "\t" + ex.getMessage());
        }
    }

    private int parsePrice(String value) {
        int result = -1;
        try {
            if (!value.trim().equals("")) {
                String temp = value.substring(0, value.length() - 1).trim();
                char c = 46;
                temp = temp.replaceAll("\\" + Character.toString(c), "");
                result = Integer.parseInt(temp);

            }

        } catch (Exception ex) {
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            System.out.println(ex + "\t" + ex.getMessage());
        } finally {

        }
        return result;
    }

    public List<Product> getResults() {
        return new ArrayList<>();
    }

    private boolean isDuplicate(String url) {
        for (Product item : resultList)
            if (item.getVendorURL().equals(url)) return true;
        return false;
    }
}