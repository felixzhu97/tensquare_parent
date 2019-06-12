package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Article article) {
		articleService.save(article);
		return new Result(true, StatusCode.OK,"添加成功");
	}

	@RequestMapping(value = "/{key}/{page}/{rows}",method = RequestMethod.GET)
	public Result findByKey(@PathVariable String key,@PathVariable int page,@PathVariable int rows) {
		Page<Article> pageData = articleService.findBykey(key,page,rows);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(pageData.getTotalElements(),pageData.getContent()));
	}
}
