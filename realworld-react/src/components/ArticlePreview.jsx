import { Link } from "react-router-dom";
import FavoriteArticleButton from "./button/FavoriteArticleButton";

// 개별 기사를 보여주는 컴포넌트
export default function ArticlePreview({ article, onFavoriteChange }) {
  const { slug, author, createdAt, title, body, tagList, favorited, favoritesCount } = article;

  // 즐겨찾기 상태가 변경될 때 부모 컴포넌트로 알림
  const handleFavoriteChange = (newFavorited, newFavoritesCount) => {
    onFavoriteChange(slug, newFavorited, newFavoritesCount);
  };

  return (
    <div className="article-preview" key={slug}>
      <div className="article-meta">
        {/* 작성자 프로필 이미지 및 링크 */}
        <Link to={`/profile/${author.username}`}>
          <img src={author.image} alt={`${author.username}'s avatar`} />
        </Link>
        <div className="info">
          <Link to={`/profile/${author.username}`} className="author">
            {author.username}
          </Link>
          <span className="date">{new Date(createdAt).toDateString()}</span> {/* 작성일 표시 */}
        </div>
        {/* 즐겨찾기 버튼 */}
        <FavoriteArticleButton
          className="pull-xs-right"
          favorited={favorited}
          slug={slug}
          favoritesCount={favoritesCount}
          onFavoriteChange={handleFavoriteChange} // 상태 변경 핸들러
        />
      </div>
      {/* 기사 내용 미리보기 */}
      <Link to={`/article/${slug}`} className="preview-link">
        <h1>{title}</h1>
        <p>{body}</p>
        <span>Read more...</span>
        {/* 태그 목록 표시 */}
        <ul className="tag-list">
          {tagList.map((tag) => (
            <li key={tag} className="tag-default tag-pill tag-outline">
              {tag}
            </li>
          ))}
        </ul>
      </Link>
    </div>
  );
}
