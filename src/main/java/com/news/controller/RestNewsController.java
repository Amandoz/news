package com.news.controller;

import com.news.model.Comments;
import com.news.model.News;
import com.news.service.CommentsService;
import com.news.service.NewsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class RestNewsController {

    @Value("${upload.path}")
    private String uploadPath;
    final NewsService newsService;
    final CommentsService commentsService;

    public RestNewsController(NewsService newsService, CommentsService commentsService) {
        this.newsService = newsService;
        this.commentsService = commentsService;
    }

    @DeleteMapping("/delete-news/{id}")
    public void deleteNews(@PathVariable("id") Long id) {
        News news = newsService.findNewsById(id);
        new File(uploadPath + news.getSourceImage()).delete();
        newsService.deleteNews(news);
    }

    @PostMapping("/create-news")
    public void createNews(@RequestParam("image")MultipartFile file,
                           @RequestParam("title")String title,
                           @RequestParam("description")String description) throws IOException {
        News news = new News();
        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));
            news.setSourceImage(fileName);
        }
        news.setDate(Date.valueOf(LocalDate.now()));
        news.setDescription(description);
        news.setTitle(title);
        newsService.saveNews(news);
    }

    @PostMapping("/send-comment/{id}")
    public Comments sendComment(@PathVariable("id") Long id, @RequestBody Comments comments) {
        News news = newsService.findNewsById(id);
        news.getComments().add(comments);
        commentsService.save(comments);
        newsService.editNews(news);
        return comments;
    }

    @GetMapping("/news/{id}")
    public News getNewsById(@PathVariable("id") Long id) {
        return newsService.findNewsById(id);
    }

    @GetMapping("/list-news")
    public List<News> allNews() {
        return newsService.findAllNews();
    }

    @GetMapping("/search-news")
    public List<News> findNewsByTitle(@RequestParam(name = "find", required = false, defaultValue = "") String title) {
        return newsService.findNewsByTitle(title);
    }
}
