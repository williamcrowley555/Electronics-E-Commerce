package com.william.electronics_ecommerce.controller;

import com.william.electronics_ecommerce.model.Brand;
import com.william.electronics_ecommerce.model.Catalog;
import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.service.BrandService;
import com.william.electronics_ecommerce.service.CatalogService;
import com.william.electronics_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private CatalogService catalogService;

    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public String listProducts(Model model) {
        return findPaginated(model, 1, "laptop", null);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(Model model,
                                @PathVariable(value = "pageNo") Integer pageNo,
                                @RequestParam(value = "catalog", required = false) String catalog,
                                @RequestParam(value = "brand", required = false) String brand) {
        Integer pageSize = 9;

        Page<Product> page = productService.getPaginated(pageNo, pageSize, catalog, brand);
        List<Product> productList = page.getContent();

        List<Catalog> catalogList = catalogService.getAllCatalog();
        List<Brand> brandList = brandService.getAllBrands();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("currentCatalog", catalog);
        model.addAttribute("currentBrand", brand);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("productList", productList);
        model.addAttribute("catalogList", catalogList);
        model.addAttribute("brandList", brandList);


        return "product";
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
