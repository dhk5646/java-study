package com.hyeyeoung.study.appapi.domain.techblog.controller;

import com.hyeyeoung.study.appapi.domain.techblog.dto.request.TechBlogPostRequest;
import com.hyeyeoung.study.appapi.domain.techblog.dto.response.TechBlogPostResponse;
import com.hyeyeoung.study.common.response.ApiResponse;
import com.hyeyeoung.study.domain.techblog.dto.query.TechBlogPostQuery;
import com.hyeyeoung.study.domain.techblog.service.TechBlogPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tech-blog-post")
public class TechBlogPostController {

    private final TechBlogPostQueryService techBlogPostService;

    @GetMapping
    public ApiResponse<List<TechBlogPostResponse>> findTechBlogPosts(TechBlogPostRequest request) {
        TechBlogPostQuery query = request.convetToTechBlogPostQuery();

        List<TechBlogPostResponse> response = techBlogPostService.findTechBlogPosts(query)
                .stream()
                .map(TechBlogPostResponse::create)
                .collect(Collectors.toList());

        return ApiResponse.success(response);
    }

}
