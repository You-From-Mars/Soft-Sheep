package com.helen.softsheep.dao;

import com.helen.softsheep.entity.FollowEntity;

public interface FollowDao {
	void saveFollow(FollowEntity follow);
	void removeFollow(FollowEntity follow);
}
