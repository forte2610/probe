package com.titan.probe.parsers;

import com.titan.probe.models.Product;

import java.util.List;

public interface VendorParser {
    void process();
    List<Product> getResults();
}