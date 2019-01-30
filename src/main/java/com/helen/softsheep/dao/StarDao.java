package com.helen.softsheep.dao;
import com.helen.softsheep.entity.StarEntity;

public interface StarDao {
	void saveStar(StarEntity starEntity);
	void removeStar(String articleUuid);
	Boolean isStar(String articleUuid, String userUuid);
}
