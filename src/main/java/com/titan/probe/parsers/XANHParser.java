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

public class XANHParser implements  VendorParser {
    private String keyword;
    private List<Product> resultList;
    DotPriceParser priceParser;

    public XANHParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
        priceParser = new DotPriceParser();
    }

    @Override
    public void process() {
        String url = "https://www.dienmayxanh.com/tag/";

        try {
            Document doc = Jsoup.connect(url + keyword)
                    .get();
            Elements productList = doc.getElementsByClass("listsearch").select("li");

            if (productList.size() == 0) {
                return;
            }
            for (Element product : productList) {
                int price = priceParser.parsePrice(product.select("figure > strong, figure > strong[class  =\"normal shock-price\"]").text());
                if (price != -1) {

                    Product currentProduct = new Product();
                    // name
                    currentProduct.setName(product.select("a > h3").text());
                    // url
                    currentProduct.setVendorURL("https://www.dienmayxanh.com" + product.select("a").attr("href"));
                    // image

                    String imageURL = product.select("a > img").attr("data-src");
                    if (!imageURL.contains("https")) {
                        imageURL = "https://" + imageURL;
                    }

                    currentProduct.setImages(imageURL);
                    currentProduct.setVendorName("XANH");
                    // price
                    currentProduct.setPrice(price);
                    // type

                    if (currentProduct.getVendorURL().substring(28, 38).equals("dien-thoai")) currentProduct.setType(new PhoneType());
                    else if (currentProduct.getVendorURL().substring(28, 34).equals("laptop")) currentProduct.setType(new LaptopType());
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
