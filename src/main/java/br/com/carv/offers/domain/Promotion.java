package br.com.carv.offers.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Promotion implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String urlPromotion;

    @Column(nullable = false)
    private String site;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String urlImage;

    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false)
    private BigDecimal promotionPrice;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private Category category;

    public Promotion() {

    }

    public Promotion(String title, String urlPromotion, String site,
                     String description, String urlImage, BigDecimal promotionPrice,
                     Integer likes, LocalDateTime createdAt) {
        this.title = title;
        this.urlPromotion = urlPromotion;
        this.site = site;
        this.description = description;
        this.urlImage = urlImage;
        this.promotionPrice = promotionPrice;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlPromotion() {
        return urlPromotion;
    }

    public void setUrlPromotion(String urlPromotion) {
        this.urlPromotion = urlPromotion;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


