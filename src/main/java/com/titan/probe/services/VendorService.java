package com.titan.probe.services;

import com.titan.probe.models.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    public Iterable<Vendor> findAll();
    public Optional<Vendor> findVendorById(int id);
}
