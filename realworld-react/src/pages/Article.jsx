import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import ArticleMeta from "../components/ArticleMeta";
import ArticleComments from "../components/ArticleComments";

export default function Article() {
  const { slug } = useParams(); // Get the article slug from the URL
  const [article, setArticle] = useState(null); // State to store article data
  const [loading, setLoading] = useState(true); // Loading state
  const [error, setError] = useState(null); // Error state

  useEffect(() => {
    // Function to fetch the article data
    const fetchArticle = async () => {
      try {
        const response = await axios.get(`/articles/${slug}`);
        setArticle(response.data.article);
      } catch (err) {
        setError("Failed to fetch article");
      } finally {
        setLoading(false);
      }
    };

    if (slug) {
      fetchArticle(); // Fetch the article if slug is available
    }
  }, [slug]);

  if (loading) {
    return <div>Loading...</div>; // Display loading indicator
  }

  if (error) {
    return <div>{error}</div>; // Display error message if any
  }

  // Destructure the article properties
  const { title, description, body } = article;

  return (
    <div className="article-page">
      <div className="banner">
        <div className="container">
          <h1>{title}</h1>
          <ArticleMeta article={article} />
        </div>
      </div>
      <div className="container page">
        <div className="row article-content">
          <div className="col-md-12">
            <p>{description}</p>
            <p>{body}</p>
          </div>
        </div>
        <hr />
        <div className="article-actions">
          <ArticleMeta article={article} />
        </div>
        <div className="row">
          <div className="col-xs-12 col-md-8 offset-md-2">
            <ArticleComments slug={slug} />
          </div>
        </div>
      </div>
    </div>
  );
}
