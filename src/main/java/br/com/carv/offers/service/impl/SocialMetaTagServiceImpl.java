package br.com.carv.offers.service.impl;

import br.com.carv.offers.domain.SocialMetaTag;
import br.com.carv.offers.service.SocialMetaTagService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SocialMetaTagServiceImpl implements SocialMetaTagService {

    private static Logger log = LoggerFactory.getLogger(SocialMetaTagServiceImpl.class);

    public SocialMetaTag getSocialMetaTagByUrl(String url) {
        SocialMetaTag openGraph = getOpenGraphByUrl(url);
        if (!isEmpty(openGraph)) {
            return openGraph;
        }

        SocialMetaTag twitter = getTwitterCardByUrl(url);
        if (!isEmpty(twitter)) {
            return twitter;
        }
        return null;
    }

    private SocialMetaTag getOpenGraphByUrl(String url) {
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
            log.error("Error getting data by OpenGraph!\nMessage: " + ioException.getMessage());
            log.error("Cause: " + ioException.getCause());
            log.info("Empty values being populated in SocialMedia");
            return new SocialMetaTag("", "", "", "");
        }
        return social;
    }

    private SocialMetaTag getTwitterCardByUrl(String url) {
        SocialMetaTag social = new SocialMetaTag();

        try {

            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0");
            Document document = connection.get();
            social.setTitle(document.head().select("meta[name=twitter:title]").attr("content"));
            social.setSite(document.head().select("meta[name=twitter:site]").attr("content"));
            social.setUrlImage(document.head().select("meta[name=twitter:image]").attr("content"));
            social.setUrlPromotions(document.head().select("meta[name=twitter:url]").attr("content"));

        } catch (IOException ioException) {
            log.error("Error getting data by Twitter Card!\nMessage: " + ioException.getMessage());
            log.error("Cause: " + ioException.getCause());
            log.info("Empty values being populated in SocialMedia");
            return new SocialMetaTag("", "", "", "");
        }
        return social;
    }

    private boolean isEmpty(SocialMetaTag social) {

        if (social.getUrlPromotions().isEmpty() || social.getUrlImage().isEmpty() ||
                social.getTitle().isEmpty() || social.getSite().isEmpty()) {
            return true;
        }

        return false;
    }
}
