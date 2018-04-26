package com.titan.probe.parsers;

import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class VienthongAParser implements VendorParser {
    private String keyword;
    private List<Product> resultList;

    public VienthongAParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
    }

    @Override
    public void process() {
        String url = "https://vienthonga.vn/tim-kiem?q=";

        try {
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("product");

            if (productList.size() == 0) {
                return;
            }
            for (Element product : productList) {
                int price = parsePrice(product.getElementsByClass("product-overlay").select("div[class = overlay-price]").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.getElementsByClass("product-name").select("div[class = name]").text());
                    // url
                    currentProduct.setVendorURL("https://vienthonga.vn" + product.getElementsByClass("product-overlay").select("a").attr("href"));
                    // image

                    String imageURL = product.getElementsByClass("product-image").select("a > figure > img").attr("data-original");
                    if (!imageURL.contains("https")) {
                        imageURL = "https://" + imageURL;
                    }

                    currentProduct.setImages(imageURL);
                    // price
                    currentProduct.setPrice(price);
                    // type
                    currentProduct.setType(product.select("a > h3").text());
                    // info
                    String productInfo = product.getElementsByClass("product-overlay").select("div[itemprop = description]").select("p").text();

                    currentProduct.setDescription(productInfo);

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
                temp = temp.replaceAll(",", "");
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
        return resultList;
    }

    private boolean isDuplicate(String url) {
        if (resultList.size() == 0) return false;
        for (Product item : resultList) {
            String currentURL = item.getVendorURL();
            if (currentURL == null || currentURL.equals(url)) return true;
        }
        return false;
    }
}
