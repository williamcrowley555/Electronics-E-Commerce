package com.william.electronics_ecommerce.controller;

import com.william.electronics_ecommerce.helper.Message;
import com.william.electronics_ecommerce.model.Product;
import com.william.electronics_ecommerce.model.Role;
import com.william.electronics_ecommerce.model.User;
import com.william.electronics_ecommerce.security.CustomUserDetails;
import com.william.electronics_ecommerce.service.ProductService;
import com.william.electronics_ecommerce.service.RoleService;
import com.william.electronics_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String viewHomePage(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = userService.getUserByEmail(auth.getName());
//        Role adminRole = roleService.getRoleByNormalizedName("ROLE_ADMIN");
//
//        model.addAttribute("currentUser", currentUser);

        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);

        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String viewRegisterForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String saveUserRegistration(Model model, @Valid @ModelAttribute("user") User user,
                                       BindingResult bindingResult) {
        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            model.addAttribute("uniqueEmailError", "Email đã được sử dụng");
            return "register";
        }

        existingUser = userService.getUserByPhone(user.getPhone());

        if (existingUser != null) {
            model.addAttribute("uniquePhoneError", "Số điện thoại đã được sử dụng");
            return "register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.saveUserRegistration(user);
        return "redirect:/register?success";
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
