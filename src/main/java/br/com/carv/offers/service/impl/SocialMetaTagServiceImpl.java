package br.com.carv.offers.service.impl;

import br.com.carv.offers.controller.domain.SocialMetaTag;
import br.com.carv.offers.service.SocialMetaTagService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SocialMetaTagServiceImpl implements SocialMetaTagService {

    public SocialMetaTag getOpenGraphByUrl(String url) {
        SocialMetaTag social = new SocialMetaTag();

        try {

            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0");
            Document document = connection.get();
            social.setTitle(document.head().select("meta[property=og:title]").attr("content"));
            social.setSite(document.head().select("meta[property=og:site_name]").attr("content"));
            social.setUrlImage(document.head().select("meta[property=og:image]").attr("content"));
            social.setUrlPromotions(document.head().select("meta[property=og:url]").attr("content"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return social;

    }
}
