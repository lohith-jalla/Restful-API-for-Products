package com.Lohith.Products.Controllers;


import com.Lohith.Products.ProductRepo;
import com.Lohith.Products.product;
import com.Lohith.Products.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/products/")
public class productController {

    private final ProductService productService;
    private final ProductRepo productRepo;
    private product p;

    public productController(ProductService productService, ProductRepo productRepo, product p) {
        this.productService = productService;
        this.productRepo = productRepo;
        this.p = p;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }


    // Create a product
    @PostMapping("/AddProduct")
    public String AddProduct(@RequestParam String name,@RequestParam Double price,@RequestParam int quantity){
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        productRepo.save(p);
        return "success! Created Product named "+ p.getName() +" Successfully!";
    }

    // List of products
    @GetMapping("/ProductsList")
    public List<product> ProductsList(){
        return productService.getAllProducts();
    }

    // Delete a product
    @DeleteMapping("/DeleteProduct/{id}")
    public String DeleteProduct(@PathVariable int id){
        try{
            productService.deleteProduct(id);
            return "Successfully ! Deleted Product with id "+id +" Successfully!";
        } catch (Exception e) {
           return "Error while deleting product named "+ p.getName() +" !";
        }

    }

    @PatchMapping("/UpdateProduct/{id}")
    public String UpdateProduct(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false , defaultValue = "0") Double price,
            @RequestParam(required = false , defaultValue = "0") int Quantity
            ){
        try{
            product p=productService.getProductById(id);
            if(name!=null) p.setName(name);
            if(price!=0) p.setPrice(price);
            if(Quantity!=0) p.setQuantity(Quantity);

            productRepo.save(p);
            return "Successfully ! Updated Product with id "+id +" Successfully!";
        }catch (Exception e) {
            return "Error while updating product with id "+ id +" !";
        }
    }

}
