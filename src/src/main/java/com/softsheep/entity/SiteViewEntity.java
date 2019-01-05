package com.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "siteviews")
public class SiteViewEntity implements Serializable{
	private static int siteView;

	public static int getSiteView() {
		return siteView;
	}

	public static void setSiteView(int siteView) {
		SiteViewEntity.siteView = siteView;
	}
	
}
