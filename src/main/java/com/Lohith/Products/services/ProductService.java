package com.Lohith.Products.services;

import com.Lohith.Products.ProductRepo;
import com.Lohith.Products.product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productrepo;

    public ProductService(ProductRepo productrepo) {
        this.productrepo = productrepo;
    }

    public List<product> getAllProducts(){
        return productrepo.findAll();
    }

    public void deleteProduct(int id){
        productrepo.deleteById(id);
    }

    public product getProductById(int id){
        return productrepo.findById(id).get();
    }


}
