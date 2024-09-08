package com.hyeyeoung.study.appbatch.domain.techblogscrap.scraper;

import com.hyeyeoung.study.appbatch.domain.techblogscrap.enums.TechBlogScrapEnum;
import com.hyeyeoung.study.domain.techblog.entity.TechBlogPost;

import java.util.List;

public interface TechBlogScraper {

    List<TechBlogPost> scrap(TechBlogScrapEnum techBlogScrapEnum);

}
