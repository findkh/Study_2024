import { useState } from "react";
import classNames from "classnames";
import axios from "axios";

// 기사 즐겨찾기 버튼 컴포넌트
export default function FavoriteArticleButton({ slug, favorited, favoritesCount, className = "", onFavoriteChange }) {
  const [isFavorited, setIsFavorited] = useState(favorited); // 즐겨찾기 상태
  const [isLoading, setIsLoading] = useState(false); // 버튼 로딩 상태
  const [currentFavoritesCount, setCurrentFavoritesCount] = useState(Number(favoritesCount) || 0); // 현재 즐겨찾기 개수

  const handleToggleFavorite = async () => {
    setIsLoading(true); // 로딩 상태 시작
    try {
      // 현재 즐겨찾기 상태에 따라 API 요청
      if (isFavorited) {
        await axios.delete(`/articles/${slug}/favorite`);
      } else {
        await axios.post(`/articles/${slug}/favorite`);
      }

      // 즐겨찾기 상태와 즐겨찾기 개수 업데이트
      const newFavorited = !isFavorited;
      const newFavoritesCount = newFavorited ? currentFavoritesCount + 1 : currentFavoritesCount - 1;

      setIsFavorited(newFavorited);
      setCurrentFavoritesCount(newFavoritesCount);

      // 상태 변경 핸들러 호출
      onFavoriteChange(newFavorited, newFavoritesCount);
    } catch (error) {
      console.error("Error toggling favorite:", error); // 에러 로그
    } finally {
      setIsLoading(false); // 로딩 상태 종료
    }
  };

  return (
    <button
      className={classNames(
        "btn btn-sm pull-xs-right",
        { "btn-primary": isFavorited, "btn-outline-primary": !isFavorited },
        className
      )}
      onClick={handleToggleFavorite}
      disabled={isLoading} // 로딩 중 버튼 비활성화
    >
      <i className="ion-heart" />
      {isLoading ? "Loading..." : currentFavoritesCount} {/* 로딩 중 표시 */}
    </button>
  );
}
