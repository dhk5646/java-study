package com.hyeyeoung.study.domain.techblog.service;

import com.hyeyeoung.study.domain.techblog.dto.query.TechBlogPostQuery;
import com.hyeyeoung.study.domain.techblog.dto.result.TechBlogPostResult;
import com.hyeyeoung.study.domain.techblog.repository.TechBlogPostQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechBlogPostQueryService {

    private final TechBlogPostQuerydslRepository techBlogPostQuerydslRepository;

    public List<TechBlogPostResult> findTechBlogPosts(TechBlogPostQuery query) {
        return techBlogPostQuerydslRepository.findTechBlogPosts(query).stream()
                .map(TechBlogPostResult::create)
                .collect(Collectors.toList());

    }
}
