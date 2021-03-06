package com.titan.probe.parsers;

import com.titan.probe.helpers.LaptopType;
import com.titan.probe.helpers.PhoneType;
import com.titan.probe.helpers.UnknownType;
import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class FPTParser implements VendorParser {
    private String keyword;
    private List<Product> resultList;

    public FPTParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
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
                int price = parsePrice(product.getElementsByClass("fs-icpri").get(0).textNodes().get(0).text());
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
                    currentProduct.setVendorName("FPT");
                    // price
                    currentProduct.setPrice(price);
                    // type
                    if (currentProduct.getVendorURL().substring(23, 33).equals("dien-thoai")) currentProduct.setType(new PhoneType());
                    else if (currentProduct.getVendorURL().substring(23, 29).equals("laptop")) currentProduct.setType(new LaptopType());
                    else currentProduct.setType(new UnknownType());
                    // info
                    String productInfo = product.select("figure > span").text();

                    currentProduct.setDescription(productInfo);

                    if (!(currentProduct.getType().getName().equals("Unknown")) && !isDuplicate(currentProduct.getVendorURL())) resultList.add(currentProduct);
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
                String temp = value.trim();
                temp = temp.substring(0, temp.length() - 1).trim();
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
