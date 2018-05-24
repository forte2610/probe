package com.titan.probe.parsers;

import com.titan.probe.helpers.DotPriceParser;
import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ViettelParser implements VendorParser {
    private String keyword;
    private List<Product> resultList;
    private DotPriceParser priceParser;

    public ViettelParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
        priceParser = new DotPriceParser();
    }

    @Override
    public void process() {
        String url = "https://viettelstore.vn/ket-qua-tim-kiem.html?keyword=";

        try {
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("ProductList3Col_item");

            System.out.println("Number of results: " + productList.size());

            if (productList.size() == 0) {
                return;
            }
            for (Element product : productList) {
                System.out.println(product.text());
                int price = priceParser.parsePrice(product.getElementsByClass("price").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.getElementsByClass("title").text());
                    // url
                    currentProduct.setVendorURL("https://viettelstore.vn/" + product.select("a").attr("href"));
                    // image

                    String imageURL = product.getElementsByClass("img").select("img").attr("src");
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
                    System.out.println("URL: " + currentProduct.getVendorURL());
                    System.out.println("Description: " + currentProduct.getDescription());
                    System.out.println();

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

    public List<Product> getResults() {
        return new ArrayList<>();
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
