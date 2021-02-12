package webshop.demo.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop.demo.model.PictureLink;
import webshop.demo.model.Product;
import webshop.demo.model.ProductDescription;
import webshop.demo.service.ProductService;

//Vi mangler update og slet funktionen, vi kan ikke finde ud af at skaffe det autoincrementerede id på produkterne, så man kan se descriptionen og ændre i den.

@Controller
public class WebshopController {

    private final ProductService productService;


    public WebshopController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getProductList());
        return "webShop";}

    @GetMapping("/createNewProduct")
    public String createNewProduct(Model model, Product product, PictureLink pictureLink, ProductDescription productDescription) {

        model.addAttribute("product", product);
        model.addAttribute("pictureLink", pictureLink);
        model.addAttribute("productDescription", productDescription);
        return "createNewProduct";
    }

    @PostMapping("/createNewProduct/submit")
    public String createProductSubmit(Product product, PictureLink pictureLink, ProductDescription productDescription) {
        productService.createProduct(product, pictureLink, productDescription);
        return "redirect:/createNewProduct";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam long productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return "updateProduct";
    }

    @PostMapping("/updateProduct/submit")
    public String updateProductSubmit(Model model){
        String s = "";
        model.addAttribute("navn", s);
        return "redirect:/";
    }












}
