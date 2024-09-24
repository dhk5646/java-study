package com.hyeyeoung.study.appbatch.domain.techblogscrap.enums;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.TechBlogScraper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.impl.KakaoTechBlogScraper;
import com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper.impl.NaverTechBlogScraper;
import com.hyeyeoung.study.domain.techblog.enums.TechBlogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TechBlogScrapEnum {

    KAKAO(TechBlogEnum.KAKAO, "https://tech.kakao.com/api/v1/posts/no-offset?categoryCode=blog&lastSeq=0&firstSeq=0", "https://tech.kakao.com/posts/%s", KakaoTechBlogScraper.class),
    NAVER(TechBlogEnum.NAVER, "https://d2.naver.com/api/v1/contents?categoryId=2&page=0&size=10", "https://d2.naver.com%s", NaverTechBlogScraper.class),
    ;

    private final TechBlogEnum techBlogEnum;
    private final String blogUrl;
    private final String postUrlFormat;
    private final Class<? extends TechBlogScraper> techBlogScraperClass;

    public String getPostUrl(String postNumber) {
        return String.format(this.postUrlFormat, postNumber);
    }

    public static TechBlogScrapEnum findByTechBlogScraperClass(Class<? extends TechBlogScraper> techBlogScraperClass) {
        return Arrays.stream(TechBlogScrapEnum.values())
                .filter(scrapEnum -> scrapEnum.getTechBlogScraperClass().equals(techBlogScraperClass))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching TechBlogScrapEnum found for class: " + techBlogScraperClass.getName()));
    }
}
