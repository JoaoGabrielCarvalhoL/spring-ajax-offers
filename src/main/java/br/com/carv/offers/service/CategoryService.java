package br.com.carv.offers.service;

import br.com.carv.offers.domain.Category;
import java.util.List;

public interface CategoryService {

    Category findById(Long id);

    void save(Category category);

    void update(Category category);

    void delete(Long id);

    List<Category> findAll();
}
