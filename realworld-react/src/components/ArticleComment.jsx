import React from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function ArticleComment({ comment, onDelete }) {
  const { author, body, createdAt, id } = comment;

  const handleDelete = async () => {
    try {
      await axios.delete(`/articles/${comment.articleSlug}/comments/${id}`);
      onDelete(id); // 댓글 삭제 후 부모 컴포넌트에 알림
    } catch (error) {
      console.error("Failed to delete comment:", error);
    }
  };

  return (
    <div className="card">
      <div className="card-block">
        <p className="card-text">{body}</p>
      </div>
      {id && (
        <div className="card-footer">
          <Link to={`/profile/${author.username}`} className="comment-author">
            <img src={author.image} className="comment-author-img" alt="author" />
          </Link>
          &nbsp;
          <Link to={`/profile/${author.username}`} className="comment-author">
            {author.username}
          </Link>
          <span className="date-posted">{new Date(createdAt).toDateString()}</span>
          <span className="mod-options">
            <i className="ion-trash-a" onClick={handleDelete} />
          </span>
        </div>
      )}
    </div>
  );
}

export default ArticleComment;
