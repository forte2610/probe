package com.titan.probe.processors;

import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FPTProcessor implements VendorProcessor{
    private String keyword;
    private List<Product> resultList;

    public FPTProcessor(String sKeyword) {
        this.keyword = sKeyword;
    }

    @Override
    public void process() {
        String url = "https://fptshop.com.vn/tim-kiem/";

        try {
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("fs-lpitem").select("a");

            if (productList.size() == 0) {
                return;
            }
            for (Element product : productList) {
                int price = parsePrice(product.getElementsByClass("fs-icpri").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.attr("title"));
                    // url
                    currentProduct.setVendorURL("https://fptshop.com.vn" + product.attr("href"));
                    // image

                    String imageURL = product.getElementsByClass("fs-icimg").select("img").attr("src");
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

                    currentProduct.setDescription(productInfo);

                    System.out.println("Name: " + currentProduct.getName());
                    System.out.println("Price: " + currentProduct.getPrice());
                    System.out.println("Image: " + currentProduct.getImages());
                    System.out.println("Description: " + currentProduct.getDescription());

                    if (!isDuplicate(currentProduct.getVendorURL())) resultList.add(currentProduct);
                }

            }

        } catch (Exception ex) {
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            System.out.println(ex + "\t" + ex.getMessage());
        }
    }

    // This is a bunch of spaghetti code. I should look into rewriting it.
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
