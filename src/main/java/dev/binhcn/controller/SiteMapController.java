package dev.binhcn.controller;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import dev.binhcn.statics.Constant;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class SiteMapController {

  @GetMapping(value ="sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
  public String getSiteMap() throws MalformedURLException {
    WebSitemapGenerator sitemapGenerator = WebSitemapGenerator
        .builder(Constant.DOMAIN, new File("."))
        .gzip(false).build();

    WebSitemapUrl sitemapUrl = new WebSitemapUrl
        .Options(Constant.DOMAIN)
        .lastMod(new Date())
        .changeFreq(ChangeFreq.HOURLY)
        .priority(1.0)
        .build();

    sitemapGenerator.addUrl(sitemapUrl);
    List<String> sitemap = sitemapGenerator.writeAsStrings();
    return sitemap.get(0);
  }
}
