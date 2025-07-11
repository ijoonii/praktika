package com.example.prak.controller;

import com.example.prak.repository.model.Product;
import com.example.prak.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView products(ModelAndView model) {
        model.setViewName("index");
        model.addObject("products", productService.getAll());
        return model;
    }

    @GetMapping("/product/create")
    public ModelAndView createProduct(ModelAndView model) {
        model.setViewName("add-product");
        return model;
    }

    @PostMapping("/product/create")
    public ModelAndView saveProduct(ModelAndView model, Product product) {
        productService.save(product);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productInfo(ModelAndView model, @PathVariable Long id) {
        model.setViewName("product-info");
        model.addObject("product", productService.getById(id));
        return model;
    }
}
