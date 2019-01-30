package com.helen.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "stars")
public class StarArticleEntity implements Serializable {
	@Id
	private String articleUuid;
}
