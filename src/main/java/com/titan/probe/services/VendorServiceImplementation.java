package com.titan.probe.services;

import com.titan.probe.Repositories.VendorRepository;
import com.titan.probe.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImplementation implements VendorService {
    @Autowired
    @Qualifier("vendorRepository")
    VendorRepository vendorRepository;

    @Override
    public Iterable<Vendor> findAll() {
        return vendorRepository.findAll();
    }
}
