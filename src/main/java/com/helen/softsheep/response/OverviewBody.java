package com.helen.softsheep.response;

import java.util.List;

import com.helen.softsheep.entity.OverviewEntity;

public class OverviewBody {
	public double pageSize;
	public int pageNo;
	public double totalRecords;
	public double totalPage;
	public List<OverviewEntity> overviews;
}
