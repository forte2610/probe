/*
 *  CrawlerRunnable
 *
 *  Author: 1412093
 *
 *  A Runnable for multi-threaded scraping
 */

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
        synchronized (products) {
            products.addAll(parser.getResults());
        }
        return 1;
    }
}
