package br.com.carv.offers.service;

import br.com.carv.offers.domain.SocialMetaTag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SocialMetaTagServiceTest {

    @Autowired
    SocialMetaTagService socialMetaTagService;

    @Test
    public void should_return_social() {
        SocialMetaTag social = socialMetaTagService.getSocialMetaTagByUrl("https://www.udemy.com/course/java-data-structures-and-algorithms-masterclass/");
        System.out.println(social.getTitle() + "\n" + social.getSite() + "\n" + social.getUrlPromotions() + "\n" + social.getUrlImage());
        Assertions.assertNotNull(social);
    }

}
