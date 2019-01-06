package com.helen.softsheep.dao;
import com.helen.softsheep.entity.CommentEntity;

public interface CommentDao {
	void saveComment(CommentEntity comment);
	CommentEntity findCommentById(String id);
	void updateComment(CommentEntity comment);
	void deleteCommentById(String id);
}
