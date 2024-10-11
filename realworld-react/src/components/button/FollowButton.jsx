import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useAuth } from "../../context/AuthContext";

export default function FollowButton({ author }) {
  const { isAuth } = useAuth();
  const navigate = useNavigate();
  const [isFollowing, setIsFollowing] = useState(author?.following || false);
  const [isLoading, setIsLoading] = useState(false);

  const handleFollowClick = async () => {
    if (!isAuth) {
      navigate("/login");
      return;
    }

    setIsLoading(true);

    try {
      if (isFollowing) {
        await axios.delete(`/profiles/${author}/follow`);
      } else {
        await axios.post(`/profiles/${author}/follow`);
      }
      setIsFollowing(!isFollowing);
    } catch (error) {
      console.error("Failed to follow/unfollow author:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <button
      className={`btn btn-sm ${isFollowing ? "btn-secondary" : "btn-outline-secondary"}`}
      disabled={isLoading}
      onClick={handleFollowClick}
    >
      {isFollowing ? "Unfollow" : "Follow"} {author}
    </button>
  );
}
