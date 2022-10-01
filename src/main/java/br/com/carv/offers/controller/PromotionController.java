package br.com.carv.offers.controller;

import br.com.carv.offers.domain.Category;
import br.com.carv.offers.domain.Promotion;
import br.com.carv.offers.service.CategoryService;
import br.com.carv.offers.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> savePromotion(@Valid Promotion promotion, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        log.info("Promotion {}", promotion.toString());
        promotionService.save(promotion);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public String listPromotions(ModelMap modelMap) {
        modelMap.addAttribute("promocoes", promotionService.findAll());
        return "promo-list";
    }

    @GetMapping("/list/ajax")
    public String listPromotionsCards(@RequestParam(name = "page", defaultValue = "1") Integer page, ModelMap modelMap) {
        modelMap.addAttribute("promocoes", promotionService.findAllPage(page));
        return "promo-card";
    }

}
