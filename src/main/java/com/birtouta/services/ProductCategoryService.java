package com.birtouta.services;

import com.birtouta.dao.ProductCategoryRepository;
import com.birtouta.entities.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory saveOrUpdateProductCategory(ProductCategory productCategory){
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory findById(Long id){
        return productCategoryRepository.getById(id);
    }

    public void delete(Long id){
        ProductCategory productCategory = findById(id);
        productCategoryRepository.delete(productCategory);
    }

    public List<ProductCategory> getAllProductCategories(){
        return productCategoryRepository.findAll();
    }
}
