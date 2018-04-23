package com.titan.probe.parsers;

import com.titan.probe.models.Product;

import java.util.List;

public interface VendorParser {
    public void process();
    public List<Product> getResults();
}
