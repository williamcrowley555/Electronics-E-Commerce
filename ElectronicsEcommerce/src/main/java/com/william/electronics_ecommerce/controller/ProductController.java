package com.william.electronics_ecommerce.controller;

import com.william.electronics_ecommerce.model.Brand;
import com.william.electronics_ecommerce.model.Catalog;
import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.service.BrandService;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public String listProducts(Model model) {
        List<Product> productList = productService.getAllProducts();

        model.addAttribute("productList", productList);
        return "product";
    }

    @GetMapping("/list")
    public String viewProducts(Model model) {
        List<Product> productList = productService.getAllProducts();

        model.addAttribute("productList", productList);
        return "product_list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();
        List<Brand> brandList = brandService.getAllBrands();

        model.addAttribute("product", product);
        model.addAttribute("brandList", brandList);
        return "product_form";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                              @RequestParam(value = "imageFile", required = false) MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }

        if (multipartFile.getSize() > 0) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                product.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.saveProduct(product);

        return "redirect:/product/add";
    }
}
