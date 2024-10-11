import classNames from "classnames";
import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import ArticleList from "../components/ArticleList";
import FollowButton from "../components/button/FollowButton";

export default function Profile() {
  const { authUser } = useAuth();
  const { username: rawUsername } = useParams();
  const username = rawUsername.startsWith("@") ? rawUsername.slice(1) : rawUsername; // '@' 제거
  const [profile, setProfile] = useState({}); // 프로필 상태
  const [loading, setLoading] = useState(true); // 로딩 상태
  const [error, setError] = useState(null); // 오류 상태
  const [filters, setFilters] = useState({ author: "", favorited: "", offset: 0 }); // 필터 상태
  const [articles, setArticles] = useState([]); // 기사를 저장할 상태
  const [articlesCount, setArticlesCount] = useState(0); // 기사 개수 상태
  const [isLoadingArticles, setIsLoadingArticles] = useState(false); // 기사 로딩 상태
  const limit = 10; // 한 페이지에 표시할 기사 수
  const canUpdateProfile = authUser.username === username;

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await axios.get(`/profiles/${username}`);
        setProfile(response.data.profile); // 프로필 데이터 설정
      } catch (err) {
        setError("프로필을 가져오는 데 실패했습니다."); // 오류 처리
      } finally {
        setLoading(false); // 로딩 상태 업데이트
      }
    };

    fetchProfile(); // 프로필 데이터 가져오기
  }, [username]);

  useEffect(() => {
    // 프로필 변경 시 필터 초기화
    setFilters({ author: username, favorited: "", offset: 0 });
  }, [username]);

  useEffect(() => {
    const fetchArticles = async () => {
      setIsLoadingArticles(true);
      setError(null);
      try {
        const response = await axios.get("/articles", {
          params: {
            limit,
            ...(filters.author && { author: filters.author }),
            ...(filters.favorited && { favorited: filters.favorited }),
            offset: filters.offset, // offset 파라미터 추가
          },
        });
        setArticles(response.data.articles);
        setArticlesCount(response.data.articlesCount);
      } catch (err) {
        console.error("API 호출 중 오류 발생:", err);
        setError("기사를 가져오는 데 실패했습니다."); // 오류 메시지 추가
      } finally {
        setIsLoadingArticles(false);
      }
    };

    fetchArticles(); // 기사 데이터 가져오기
  }, [filters]); // 필터가 변경될 때마다 호출

  const setAuthorFilter = () => {
    setFilters({ author: username, favorited: "", offset: 0 }); // offset 초기화
  };

  const setFavoritedFilter = () => {
    setFilters({ author: "", favorited: username, offset: 0 }); // offset 초기화
  };

  if (loading) {
    return <div>로딩 중...</div>; // 로딩 중일 때 표시
  }

  if (error) {
    return <div>{error}</div>; // 오류 발생 시 표시
  }

  return (
    <div className="profile-page">
      <div className="user-info">
        <div className="container">
          <div className="row">
            <div className="col-xs-12 col-md-10 offset-md-1">
              <img src={profile.image} className="user-img" alt="Profile" />
              <h4>{username}</h4>
              <p>{profile.bio}</p>
              {canUpdateProfile ? (
                <Link className="btn btn-sm btn-outline-secondary action-btn" to="/settings">
                  <i className="ion-gear-a" /> Edit Profile Settings
                </Link>
              ) : (
                // <FollowButton profile={profile} />
                <FollowButton author={profile.username} />
              )}
            </div>
          </div>
        </div>
      </div>

      <div className="container">
        <div className="row">
          <div className="col-xs-12 col-md-10 offset-md-1">
            <div className="articles-toggle">
              <ul className="nav nav-pills outline-active">
                <li className="nav-item">
                  <button
                    onClick={setAuthorFilter}
                    type="button"
                    className={classNames("nav-link", {
                      active: filters.author,
                    })}
                  >
                    My Articles
                  </button>
                </li>
                <li className="nav-item">
                  <button
                    onClick={setFavoritedFilter}
                    type="button"
                    className={classNames("nav-link", {
                      active: filters.favorited,
                    })}
                  >
                    Favorited Articles
                  </button>
                </li>
              </ul>
            </div>

            {isLoadingArticles && <p className="article-preview">Loading articles...</p>}
            {error && <p className="article-preview">Loading articles failed :(</p>}
            {articles.length === 0 && !isLoadingArticles && (
              <p className="article-preview">No articles are here... yet.</p>
            )}

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
              onPageChange={(page) => setFilters((prevFilters) => ({ ...prevFilters, offset: page }))} // 페이지 변경 시 offset 업데이트
              currentPage={filters.offset}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
