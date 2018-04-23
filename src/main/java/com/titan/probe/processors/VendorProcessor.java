package com.titan.probe.processors;

import com.titan.probe.models.Product;

import java.util.List;

public interface VendorProcessor {
    public void process();
    public List<Product> getResults();
}
