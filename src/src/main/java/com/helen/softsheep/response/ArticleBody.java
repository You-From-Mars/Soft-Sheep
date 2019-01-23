package com.helen.softsheep.response;

import java.util.List;

import com.helen.softsheep.entity.ArticleEntity;

public class ArticleBody {
	public int pageSize;
	public int pageNo;
	public Long totleRecords;
	public int totlePage;
	public List<ArticleEntity> articles;
}
