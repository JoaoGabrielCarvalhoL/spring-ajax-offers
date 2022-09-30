package br.com.carv.offers.controller;

import br.com.carv.offers.domain.Category;
import br.com.carv.offers.domain.Promotion;
import br.com.carv.offers.service.CategoryService;
import br.com.carv.offers.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    private static Logger log = LoggerFactory.getLogger(PromotionController.class);
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

    @PostMapping("/save")
    public ResponseEntity<Promotion> savePromotion(Promotion promotion) {
        log.info("Promotion {}", promotion.toString());
        promotionService.save(promotion);
        return ResponseEntity.ok().build();
    }

}
