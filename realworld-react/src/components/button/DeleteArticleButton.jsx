import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { useState } from "react";

export default function DeleteArticleButton() {
  const { slug } = useParams();
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  const handleDelete = async () => {
    setIsLoading(true);
    try {
      await axios.delete(`/articles/${slug}`);
      navigate("/");
    } catch (error) {
      console.error("Failed to delete article:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <button disabled={isLoading} onClick={handleDelete} type="button" className="btn btn-outline-danger btn-sm">
      <i className="ion-trash-a" /> Delete Article
    </button>
  );
}
