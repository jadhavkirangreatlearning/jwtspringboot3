package com.csi.service;

import com.csi.entity.Product;

import java.util.List;

public interface IProductService {

    Product save(Product product);

    List<Product> findAll();

    //
    //

}
