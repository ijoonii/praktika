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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView saveProduct(ModelAndView model, Product product) {
        productService.save(product);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping("/product/search")
    public ModelAndView searchByName(ModelAndView model, @RequestParam(name = "productName", required = false) String productName) {
        model.setViewName("index");
        model.addObject("products", productService.getAllByName(productName));
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        return model;
    }

    @GetMapping("/product/search/city")
    public ModelAndView searchByCity(ModelAndView model, @RequestParam(name = "cityId", required = false) Long cityId) {
        model.setViewName("index");
        model.addObject("products", productService.getAllByCity(cityId));
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        model.addObject("selectedCityId", cityId);
        return model;
    }

    @GetMapping("/product/search/category")
    public ModelAndView searchByCategory(ModelAndView model, @RequestParam(name = "categoryId", required = false) Long categoryId) {
        model.setViewName("index");
        model.addObject("products", productService.getAllByCategory(categoryId));
        model.addObject("cities", cityService.getCities());
        model.addObject("categories", categoryService.getCategories());
        model.addObject("selectedCategoryId", categoryId);
        return model;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productInfo(ModelAndView model, @PathVariable Long id) {
        model.setViewName("product-info");
        model.addObject("product", productService.getById(id));
        return model;
    }
}
