package com.titan.probe.services;

import com.titan.probe.models.Vendor;

import java.util.List;

public interface VendorService {
    public Iterable<Vendor> findAll();
}
