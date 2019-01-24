package com.helen.softsheep.response;

import java.util.List;

import com.helen.softsheep.entity.ArticleEntity;

public class ArticleBody {
	public double pageSize;
	public int pageNo;
	public double totalRecords;
	public double totalPage;
	public List<ArticleEntity> articles;
}
