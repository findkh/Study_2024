import { useEffect, useState } from "react";
import classNames from "classnames";
import PopularTags from "../components/PopularTags";
import { useAuth } from "../context/AuthContext";
import axios from "axios";
import ArticleList from "../components/ArticleList";

const initialFilters = { tag: "", offset: 0, feed: false };

export default function Home() {
  const { isAuth } = useAuth();
  const [filters, setFilters] = useState({ ...initialFilters, feed: isAuth });
  const [articles, setArticles] = useState([]);
  const [articlesCount, setArticlesCount] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const limit = 10;

  useEffect(() => {
    setFilters((prevFilters) => ({ ...initialFilters, feed: isAuth }));
  }, [isAuth]);

  useEffect(() => {
    const fetchArticles = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(filters.feed ? "/articles/feed" : "/articles", {
          params: {
            limit,
            offset: filters.offset,
            ...(filters.feed && { feed: true }),
            ...(filters.tag && { tag: filters.tag }),
          },
        });
        setArticles(response.data.articles);
        setArticlesCount(response.data.articlesCount);
      } catch (err) {
        console.error("API 호출 중 오류 발생:", err);
        setError(err);
      } finally {
        setIsLoading(false);
      }
    };

    fetchArticles();
  }, [filters.feed, filters.offset, filters.tag]); // 의존성 배열 수정

  const onTagClick = (tag) => setFilters((prevFilters) => ({ ...prevFilters, tag }));
  const onGlobalFeedClick = () => setFilters(initialFilters);
  const onFeedClick = () => setFilters((prevFilters) => ({ ...prevFilters, feed: true }));

  const onPageChange = (page) => {
    setFilters((prevFilters) => ({ ...prevFilters, offset: page })); // offset 업데이트 최적화
  };

  return (
    <div className="home-page">
      <div className="banner">
        <div className="container">
          <h1 className="logo-font">conduit</h1>
          <p>A place to share your knowledge.</p>
        </div>
      </div>
      <div className="container page">
        <div className="row">
          <div className="col-md-9">
            <div className="feed-toggle">
              <ul className="nav nav-pills outline-active">
                {isAuth && (
                  <li className="nav-item">
                    <button
                      onClick={onFeedClick}
                      type="button"
                      className={classNames("nav-link", { active: filters.feed })}
                    >
                      Your Feed
                    </button>
                  </li>
                )}
                <li className="nav-item">
                  <button
                    type="button"
                    className={classNames("nav-link", { active: !filters.tag && !filters.feed })}
                    onClick={onGlobalFeedClick}
                  >
                    Global Feed
                  </button>
                </li>
                {filters.tag && (
                  <li className="nav-item">
                    <a className="nav-link active"># {filters.tag}</a>
                  </li>
                )}
              </ul>
            </div>

            {isLoading && <p className="article-preview">Loading articles...</p>}
            {error && <p className="article-preview">Loading articles failed :(</p>}
            {articles.length === 0 && !isLoading && <p className="article-preview">No articles are here... yet.</p>}

            <ArticleList
              articlesCount={articlesCount}
              articles={articles}
              onFavoriteChange={(slug, newFavorited, newFavoritesCount) => {
                setArticles((prevArticles) =>
                  prevArticles.map((article) =>
                    article.slug === slug
                      ? { ...article, favorited: newFavorited, favoritesCount: newFavoritesCount }
                      : article
                  )
                );
              }}
              onPageChange={onPageChange}
              currentPage={filters.offset}
            />
          </div>
          <div className="col-md-3">
            <PopularTags onTagClick={onTagClick} />
          </div>
        </div>
      </div>
    </div>
  );
}
