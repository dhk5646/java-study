package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class TechBlogScraperFactory {

    private final EnumMap<TechBlogScrapEnum, TechBlogScraper> scraperMap;

    public TechBlogScraperFactory(List<TechBlogScraper> scrapers) {
        scraperMap = createScraperMap(scrapers);
    }

    private EnumMap<TechBlogScrapEnum, TechBlogScraper> createScraperMap(List<TechBlogScraper> scrapers) {
        EnumMap<TechBlogScrapEnum, TechBlogScraper> scraperMap = new EnumMap<>(TechBlogScrapEnum.class);

        // 각 스크래퍼를 순회하며 EnumMap에 추가
        for (TechBlogScraper scraper : scrapers) {
            TechBlogScrapEnum scrapEnum = TechBlogScrapEnum.findByTechBlogScraperClass(scraper.getClass());
            scraperMap.put(scrapEnum, scraper);
        }
        return scraperMap;
    }

    public TechBlogScraper getTechBlogScraper(TechBlogScrapEnum techBlogScrapEnum) {
        TechBlogScraper scraper = scraperMap.get(techBlogScrapEnum);
        if (scraper == null) {
            throw new IllegalArgumentException("No scraper found for: " + techBlogScrapEnum.getTechBlogEnum().getName());
        }
        return scraper;
    }
}
