package br.com.carv.offers.controller;

import br.com.carv.offers.domain.SocialMetaTag;
import br.com.carv.offers.service.SocialMetaTagService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/meta")
public class SocialMetaTagController {

    private final SocialMetaTagService socialMetaTagService;

    @Autowired
    public SocialMetaTagController(SocialMetaTagService socialMetaTagService) {
        this.socialMetaTagService = socialMetaTagService;
    }

    @PostMapping("/info")
    public ResponseEntity<SocialMetaTag> getDataByUrl(@RequestParam("url") String url) {
        SocialMetaTag socialMetaTag = socialMetaTagService.getSocialMetaTagByUrl(url);
        if (socialMetaTag != null) {
            return ResponseEntity.ok().body(socialMetaTag);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
