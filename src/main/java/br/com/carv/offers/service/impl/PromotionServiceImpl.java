package br.com.carv.offers.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import br.com.carv.offers.domain.Promotion;
import br.com.carv.offers.exception.NotFoundException;
import br.com.carv.offers.repository.PromotionRepository;
import br.com.carv.offers.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {

    Logger log = LoggerFactory.getLogger(PromotionServiceImpl.class);

    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion findById(Long id) {
        return promotionRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found! Id: " + id));
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void update(Promotion promotion) {
        save(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.delete(findById(id));
    }

    @Override
    public List<Promotion> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(0, 8, sort);
        return promotionRepository.findAll(pageRequest).stream().collect(Collectors.toList());
    }

    @Override
    public List<Promotion> findAllPage(Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        PageRequest pageRequest = PageRequest.of(page, 8, sort);
        return promotionRepository.findAll(pageRequest).stream().collect(Collectors.toList());
    }
}
