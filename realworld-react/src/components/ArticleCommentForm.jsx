import { useState } from "react";
import axios from "axios";

export default function ArticleCommentForm({ slug, addComment }) {
  const [body, setBody] = useState(""); // 댓글 입력 상태
  const [isSubmitting, setIsSubmitting] = useState(false); // 제출 상태

  const handleSubmit = async (e) => {
    e.preventDefault(); // 기본 제출 동작 방지
    setIsSubmitting(true); // 제출 시작

    try {
      const response = await axios.post(`/articles/${slug}/comments`, {
        comment: { body },
      });
      addComment(response.data.comment); // 댓글 추가
      setBody(""); // 입력 필드 초기화
    } catch (err) {
      console.error("Failed to add comment:", err); // 오류 로깅
    } finally {
      setIsSubmitting(false); // 제출 완료
    }
  };

  return (
    <form className="card comment-form" onSubmit={handleSubmit}>
      <div className="card-block">
        <textarea
          required
          className="form-control"
          placeholder="Write a comment..."
          rows={3}
          value={body}
          onChange={(e) => setBody(e.target.value)} // 입력 처리
        />
      </div>
      <div className="card-footer">
        <button disabled={isSubmitting} type="submit" className="btn btn-sm btn-primary">
          Post Comment
        </button>
      </div>
    </form>
  );
}
