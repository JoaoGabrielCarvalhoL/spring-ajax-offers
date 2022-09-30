package br.com.carv.offers.service.impl;

import br.com.carv.offers.domain.Category;
import br.com.carv.offers.exception.NotFoundException;
import br.com.carv.offers.repository.CategoryRepository;
import br.com.carv.offers.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception! Id: " + id));
    }

    @Override
    public void save(Category category) {
        log.info("Saving Category.");
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(findById(id));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
