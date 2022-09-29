package br.com.carv.offers.controller.domain;

import java.io.Serializable;

public class SocialMetaTag implements Serializable {

    private String site;
    private String title;
    private String urlPromotions;
    private String urlImage;

    public SocialMetaTag() {

    }

    public SocialMetaTag(String site, String title, String urlPromotions, String urlImage) {
        this.site = site;
        this.title = title;
        this.urlPromotions = urlPromotions;
        this.urlImage = urlImage;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlPromotions() {
        return urlPromotions;
    }

    public void setUrlPromotions(String urlPromotions) {
        this.urlPromotions = urlPromotions;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
