// import { Link } from "react-router-dom";
// import { useAuth } from "../context/AuthContext";
// import DeleteArticleButton from "./button/DeleteArticleButton";
// import FavoriteArticleButton from "./button/FavoriteArticleButton";
// import FollowButton from "./button/FollowButton";

// export default function ArticleMeta({ article, onFavoriteChange }) {
//   const { authUser } = useAuth();
//   const { author, createdAt, favorited, favoritesCount, slug } = article;
//   const canUpdate = author.username === authUser.username;

//   return (
//     <div className="article-meta">
//       <Link to={`/profile/${author?.username}`}>
//         <img src={author?.image} alt={`${author?.username}'s avatar`} />
//       </Link>
//       <div className="info">
//         <Link to={`/profile/${author?.username}`} className="author">
//           {author?.username}
//         </Link>
//         <span className="date">{new Date(createdAt).toDateString()}</span>
//       </div>
//       {canUpdate ? (
//         <span>
//           <Link className="btn btn-outline-secondary btn-sm" to={`/editor/${slug}`}>
//             <i className="ion-edit" /> Edit Article
//           </Link>
//           &nbsp;&nbsp;
//           <DeleteArticleButton slug={slug} />
//         </span>
//       ) : (
//         <>
//           <FollowButton author={author.username} />
//           &nbsp;&nbsp;
//           <FavoriteArticleButton
//             slug={slug}
//             favorited={favorited}
//             favoritesCount={favoritesCount}
//             onFavoriteChange={onFavoriteChange} // 상태 변경 핸들러
//           >
//             &nbsp; {favorited ? "Unfavorite" : "Favorite"} Article <span className="counter">({favoritesCount})</span>
//           </FavoriteArticleButton>
//         </>
//       )}
//     </div>
//   );
// }

import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import DeleteArticleButton from "./button/DeleteArticleButton";
import FavoriteArticleButton from "./button/FavoriteArticleButton";
import FollowButton from "./button/FollowButton";

export default function ArticleMeta({ article, onFavoriteChange }) {
  const { authUser } = useAuth();
  const { author, createdAt, favorited, favoritesCount, slug } = article;
  const canUpdate = author.username === authUser?.username; // authUser가 없을 수 있으므로 optional chaining 사용

  return (
    <div className="article-meta">
      <Link to={`/profile/${author?.username}`}>
        <img src={author?.image} alt={`${author?.username}'s avatar`} />
      </Link>
      <div className="info">
        <Link to={`/profile/${author?.username}`} className="author">
          {author?.username}
        </Link>
        <span className="date">{new Date(createdAt).toDateString()}</span>
      </div>
      {canUpdate ? (
        <span>
          <Link className="btn btn-outline-secondary btn-sm" to={`/editor/${slug}`}>
            <i className="ion-edit" /> Edit Article
          </Link>
          &nbsp;&nbsp;
          <DeleteArticleButton slug={slug} />
        </span>
      ) : (
        <>
          {authUser ? (
            <>
              <FollowButton author={author.username} />
              &nbsp;&nbsp;
              <FavoriteArticleButton
                slug={slug}
                favorited={favorited}
                favoritesCount={favoritesCount}
                onFavoriteChange={onFavoriteChange} // 상태 변경 핸들러
              >
                &nbsp; {favorited ? "Unfavorite" : "Favorite"} Article{" "}
                <span className="counter">({favoritesCount})</span>
              </FavoriteArticleButton>
            </>
          ) : (
            <p className="text-muted">로그인 하세요.</p>
          )}
        </>
      )}
    </div>
  );
}
