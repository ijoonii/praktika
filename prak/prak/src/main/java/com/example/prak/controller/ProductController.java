package com.example.prak.controller;

import com.example.prak.repository.model.Product;
import com.example.prak.service.CategoryService;
import com.example.prak.service.CityService;
import com.example.prak.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CityService cityService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CityService cityService, CategoryService categoryService) {
        this.productService = productService;
        this.cityService = cityService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public ModelAndView products(ModelAndView model) {
        model.setViewName("index");
        model.addObject("products", productService.getAll());
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        return model;
    }

    @GetMapping("/product/create")
    public ModelAndView createProduct(ModelAndView model) {
        model.setViewName("add-product");
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        return model;
    }

    @PostMapping("/product/create")
    public ModelAndView saveProduct(ModelAndView model, Product product, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3) throws IOException {
        productService.save(product, file1, file2, file3);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping("product/search")
    public ModelAndView search(ModelAndView model,
                               @RequestParam(name = "productName", required = false) String productName,
                               @RequestParam(name = "cityId", required = false) Long cityId,
                               @RequestParam(name = "categoryId", required = false) Long categoryId
                               ) {
        model.setViewName("index");
        model.addObject("products", productService.searchProducts(productName, cityId, categoryId));
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        model.addObject("selectedCategoryId", categoryId);
        model.addObject("selectedCityId", cityId);
        return model;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productInfo(ModelAndView model, @PathVariable Long id) {
        Product product = productService.getById(id);
        model.setViewName("product-info");
        model.addObject("product", product);
        model.addObject("images", product.getImages());
        return model;
    }
}
