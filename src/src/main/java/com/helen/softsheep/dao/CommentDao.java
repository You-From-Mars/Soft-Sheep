package com.helen.softsheep.dao;
import java.util.List;

import com.helen.softsheep.entity.CommentEntity;

public interface CommentDao {
	void save(CommentEntity comment);
	List findCommentsById(String articleId);
//	void updateComment(CommentEntity comment);
	void deleteCommentById(String id);
}
