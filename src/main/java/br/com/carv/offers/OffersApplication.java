package br.com.carv.offers;

import br.com.carv.offers.controller.domain.SocialMetaTag;
import br.com.carv.offers.service.SocialMetaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OffersApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OffersApplication.class, args);
	}

	@Autowired
	SocialMetaTagService socialMetaTagService;

	@Override
	public void run(String... args) throws Exception {
		SocialMetaTag social = socialMetaTagService.getOpenGraphByUrl("https://www.udemy.com/course/java-data-structures-and-algorithms-masterclass/");
		System.out.println(social.getTitle() + "\n" + social.getSite() + "\n" + social.getUrlPromotions());
	}
}
