package com.Lohith.Products.Controllers;


import com.Lohith.Products.ProductRepo;
import com.Lohith.Products.product;
import com.Lohith.Products.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest Controller class
// default Endpoint for this controller
// to be executed and to execute the CRUD
// operations on the product class is "/api/products/".
@RestController()
@RequestMapping("/api/products/")
public class productController {

    // Objects for the Service class and the Product Repository
    private final ProductService productService;
    private final ProductRepo productRepo;
    private product p;

    // constructor to instantiate the service class,
    // product repository and product class objects
    public productController(ProductService productService, ProductRepo productRepo, product p) {
        this.productService = productService;
        this.productRepo = productRepo;
        this.p = p;
    }

    // Just a default endpoint for checking the api working.
    @GetMapping("/home")
    public String home(){
        return "home";
    }


    // Create a product
    // http://localhost:8080/api/products/AddProduct -> URL for PostMan to Test the endPoint.
    @PostMapping("/AddProduct")
    public String AddProduct(@RequestParam String name,@RequestParam Double price,@RequestParam int quantity){
        p.setName(name);
        p.setPrice(price);
        p.setQuantity(quantity);
        productRepo.save(p);
        return "success! Created Product named "+ p.getName() +" Successfully!";
    }

    // List of products
    //http://localhost:8080/api/products/ProductsList -> URL For PostMan to Test the EndPoint
    @GetMapping("/ProductsList")
    public List<product> ProductsList(){
        return productService.getAllProducts();
    }

    // Delete a product
    // http://localhost:8080/api/products/DeleteProduct/{id} -> URL for PostMan to Test the EndPoint
    // In the place of id just keep the id of the product you need to delete

    @DeleteMapping("/DeleteProduct/{id}")
    public String DeleteProduct(@PathVariable int id){
        try{
            productService.deleteProduct(id);
            return "Successfully ! Deleted Product with id "+id +" Successfully!";
        } catch (Exception e) {
           return "Error while deleting product named "+ p.getName() +" !";
        }

    }
    // Update a product
    //http://localhost:8080/api/products/UpdateProduct/{id} -> URL for Postman to test the Endpoint
    //In the place of id just keep the id of the product you need to update
    //The variables you need to change can be passed using params but if not it will take the default values
    // which are present in the original product -> it will not change anything.

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
