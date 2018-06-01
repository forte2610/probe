package com.titan.probe.parsers;

import com.titan.probe.helpers.DotPriceParser;
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

public class TikiParser implements VendorParser {
    private String keyword;
    private List<Product> resultList;
    private DotPriceParser priceParser;

    public TikiParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
        priceParser = new DotPriceParser();
    }

    @Override
    public void process() {
        String url = "https://tiki.vn/dien-thoai-smartphone/c1795?q=";

        try {
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("product-item").select("a");
            if (productList.size() == 0) {
                return;
            }
            int index=0;
            for (Element product : productList) {
                index++;
                if (index == 6) return;
                int price = priceParser.parsePrice(product.getElementsByClass("final-price").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.getElementsByClass("title").text());
                    // url
                    currentProduct.setVendorURL(product.getElementsByClass("search-a-product-item").attr("href"));
                    // image

                    currentProduct.setVendorName("Tiki");
                    String imageURL = product.getElementsByClass("product-image").attr("src");
                    if (!imageURL.contains("https")) {
                        imageURL = "https://" + imageURL;
                    }

                    currentProduct.setImages(imageURL);
                    // price
                    currentProduct.setPrice(price);
                    // type
                    if (currentProduct.getName().substring(0, 10).equals("Điện thoại") || currentProduct.getName().substring(0, 10).equals("Điện Thoại"))
                        currentProduct.setType(new PhoneType());
                    else if (currentProduct.getName().substring(0, 9).equals("Laptop") || currentProduct.getName().contains("Macbook"))
                        currentProduct.setType(new LaptopType());
                    else currentProduct.setType(new UnknownType());
                    // info
                    String productInfo = product.select("figure > span").text();

                    currentProduct.setDescription(productInfo);

                    if (!(currentProduct.getType().getName().equals("Unknown")) && !isDuplicate(currentProduct.getVendorURL()))
                        resultList.add(currentProduct);
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
