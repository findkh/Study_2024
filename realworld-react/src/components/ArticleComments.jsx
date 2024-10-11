import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useEffect, useState } from "react";
import axios from "axios";
import ArticleComment from "./ArticleComment";
import ArticleCommentForm from "./ArticleCommentForm";

export default function ArticleComments({ slug }) {
  const { isAuth } = useAuth();
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const fetchComments = async () => {
      try {
        const response = await axios.get(`/articles/${slug}/comments`);
        setComments(response.data.comments);
      } catch (error) {
        console.error("Failed to fetch comments:", error);
      }
    };

    fetchComments();
  }, [slug]);

  const handleDeleteComment = (id) => {
    setComments((prevComments) => prevComments.filter((comment) => comment.id !== id));
  };

  const addComment = (comment) => {
    setComments((prevComments) => [...prevComments, comment]);
  };

  if (!isAuth) {
    return (
      <p>
        <Link to="/login">Sign in</Link> or <Link to="/register">sign up</Link> to add comments on this article.
      </p>
    );
  }

  return (
    <div>
      <ArticleCommentForm slug={slug} addComment={addComment} />
      {comments.map((comment) => (
        <ArticleComment key={comment.id} comment={comment} onDelete={handleDeleteComment} />
      ))}
    </div>
  );
}
