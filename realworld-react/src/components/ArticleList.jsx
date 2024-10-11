import ArticlePreview from "./ArticlePreview";

// 기사 목록을 보여주는 컴포넌트
export default function ArticleList({ articlesCount, articles, onFavoriteChange, onPageChange, currentPage }) {
  const limit = 10; // 한 페이지에 표시할 기사 수
  const pages = Math.ceil(articlesCount / limit); // 전체 페이지 수 계산

  // 기사가 없을 때 표시
  if (articles.length === 0) {
    return <p className="article-preview">No articles are here... yet.</p>;
  }

  return (
    <>
      {/* 각 기사를 ArticlePreview 컴포넌트로 렌더링 */}
      {articles.map((article, idx) => (
        <ArticlePreview key={idx} article={article} onFavoriteChange={onFavoriteChange} />
      ))}

      {/* 페이지네이션 */}
      {pages > 1 && (
        <nav>
          <ul className="pagination">
            {Array.from({ length: pages }, (_, i) => (
              <li className={currentPage === i ? "page-item active" : "page-item"} key={i}>
                <button type="button" className="page-link" onClick={() => onPageChange(i)}>
                  {i + 1}
                </button>
              </li>
            ))}
          </ul>
        </nav>
      )}
    </>
  );
}
