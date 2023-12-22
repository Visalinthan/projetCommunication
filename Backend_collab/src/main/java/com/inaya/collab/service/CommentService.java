package com.inaya.collab.service;

import com.inaya.collab.exceptions.EntityNotFoundException;
import com.inaya.collab.model.Comment;
import com.inaya.collab.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // Create
    public Comment createComment(Comment comment) {
        if (comment.getComment_id() != null) {
            throw new IllegalArgumentException("Comment ID must be null for creation.");
        }
        return commentRepository.save(comment);
    }

    // Read
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with ID: " + commentId));
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Update
    public Comment updateComment(Comment updatedComment) {
        if (commentRepository.existsById(updatedComment.getComment_id())) {
            return commentRepository.save(updatedComment);
        } else {
            throw new EntityNotFoundException("Comment not found with ID: " + updatedComment.getComment_id());
        }
    }

    // Delete
    public void deleteComment(Long commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
        } else {
            throw new EntityNotFoundException("Comment not found with ID: " + commentId);
        }
    }
}


