import { useEffect, useState } from "react";
import axios from "axios";

// eslint-disable-next-line react/prop-types
export default function PopularTags({ onTagClick }) {
  const [tags, setTags] = useState([]);
  const [isFetching, setIsFetching] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTags = async () => {
      setIsFetching(true);
      setError(null);
      try {
        const response = await axios.get("/tags");
        setTags(response.data.tags);
      } catch (err) {
        setError(err);
      } finally {
        setIsFetching(false);
      }
    };

    fetchTags();
  }, []);

  function content() {
    if (isFetching) return <p>Loading tags...</p>;
    if (error) return <p>Loading tags failed :(</p>;

    return tags.map((tag) => (
      <a
        href="#"
        key={tag}
        className="tag-pill tag-default"
        onClick={(e) => {
          e.preventDefault();
          onTagClick(tag);
        }}
      >
        {tag}
      </a>
    ));
  }

  return (
    <div className="sidebar">
      <p>Popular Tags</p>
      <div className="tag-list">{content()}</div>
    </div>
  );
}
