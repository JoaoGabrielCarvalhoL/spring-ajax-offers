package br.com.carv.offers.controller;

import br.com.carv.offers.domain.Category;
import br.com.carv.offers.service.CategoryService;
import br.com.carv.offers.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    private final CategoryService categoryService;

    @Autowired
    public PromotionController(PromotionService promotionService, CategoryService categoryService) {
        this.promotionService = promotionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String openRegister() {
        return "promo-add";
    }

    @ModelAttribute("categorias")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

}
