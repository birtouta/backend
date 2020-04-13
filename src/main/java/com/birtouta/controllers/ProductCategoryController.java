package com.birtouta.controllers;

import com.birtouta.dao.SessionRepository;
import com.birtouta.entities.ProductCategory;
import com.birtouta.entities.Session;
import com.birtouta.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewProductCategory(@RequestBody ProductCategory productCategory, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            ProductCategory category=productCategoryService.saveOrUpdateProductCategory(productCategory);
            return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateProductCategory(@RequestBody ProductCategory productCategory, @RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        } else {
            ProductCategory category=productCategoryService.saveOrUpdateProductCategory(productCategory);
            return new ResponseEntity<ProductCategory>(category, HttpStatus.OK);
        }
    }

    @DeleteMapping(path="/delete")
    public ResponseEntity<?> deleteProductCategory (@RequestParam Long id_product_category,@RequestHeader("Token") String token) {

        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        }else {
            productCategoryService.delete(id_product_category);
            return new ResponseEntity<String>("Product category was deleted deleted", HttpStatus.OK);
        }
    }

    @GetMapping(path="/all")
    public ResponseEntity<?> getAllProductCategories(@RequestHeader("Token") String token) {
        Session session = sessionRepository.findByToken(token);
        if(session == null) {
            return new ResponseEntity<String>("Unauthorized Token", HttpStatus.UNAUTHORIZED);
        }else {
            return new ResponseEntity<List<ProductCategory>> (productCategoryService.getAllProductCategories(), HttpStatus.OK);
        }
    }
}
