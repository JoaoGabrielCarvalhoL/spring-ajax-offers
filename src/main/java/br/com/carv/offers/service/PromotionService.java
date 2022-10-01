package br.com.carv.offers.service;

import br.com.carv.offers.domain.Promotion;
import br.com.carv.offers.exception.NotFoundException;

import java.util.List;

public interface PromotionService {

    Promotion findById(Long id);
    void save(Promotion promotion);
    void update(Promotion promotion);
    void delete(Long id);
    List<Promotion> findAll();
    List<Promotion> findAllPage(Integer page);
}
