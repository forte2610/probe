package com.titan.probe.parsers;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.titan.probe.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class TGDDParser implements VendorParser {
    private String keyword;
    private List<Product> resultList;

    public TGDDParser(String sKeyword) {
        this.keyword = sKeyword;
        resultList = new ArrayList<Product>();
    }

    @Override
    public void process() {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setTimeout(5000);
        webClient.setAjaxController(new AjaxController(){
            @Override
            public boolean processSynchron(HtmlPage page, WebRequest request, boolean async)
            {
                return true;
            }
        });

        int count = 0;
        String url = "https://www.thegioididong.com/tim-kiem?key=";

        try {
            HtmlPage vendorPage = webClient.getPage(url + keyword);
            Document doc;

            // handle AJAX lazy loading
            while (true) {
                try {
                    HtmlAnchor anchor = vendorPage.getAnchorByHref("javascript:ShowMoreProductResult();");
                    anchor.click();
                }
                catch (ElementNotFoundException anchorNotFound) {
                    break;
                }
            }

            doc = Jsoup.parse(vendorPage.asXml());
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
                    if (currentProduct.getVendorURL().substring(29, 33).equals("dtdd")) currentProduct.setType("Phone");
                    else if (currentProduct.getVendorURL().substring(29, 35).equals("laptop")) currentProduct.setType("Laptop");
                    else currentProduct.setType("Null");
                    // info
                    String productInfo = product.select("figure > span").text();

                    currentProduct.setDescription(productInfo);

                    if (!(currentProduct.getType().equals("Null")) && !isDuplicate(currentProduct.getVendorURL())) resultList.add(currentProduct);
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
