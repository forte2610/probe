package com.titan.probe.helpers;

import com.titan.probe.models.Product;
import com.titan.probe.parsers.VendorParser;

import java.util.List;
import java.util.concurrent.Callable;

public class CrawlerRunnable implements Callable<Object> {
    private VendorParser parser;
    private List<Product> products;

    public CrawlerRunnable(VendorParser _parser, List<Product> _products) {
        this.parser = _parser;
        this.products = _products;
    }

    @Override
    public Object call() {

        parser.process();
        System.out.println("Thread done. Number of results: " + parser.getResults().size());
        synchronized (products) {
            products.addAll(parser.getResults());
        }
        return 1;
    }
}
