import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import FormErrors from "../components/FormErrors";
import TagsInput from "../components/TagsInput";

function Editor() {
  const navigate = useNavigate();
  const { slug } = useParams();
  const [article, setArticle] = useState({
    title: "",
    description: "",
    body: "",
    tagList: [],
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (slug) {
      axios
        .get(`/articles/${slug}`)
        .then((response) => setArticle(response.data.article))
        .catch((err) => console.error(err));
    }
  }, [slug]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setArticle({
      ...article,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      const { data } = await axios[slug ? "put" : "post"](`/articles${slug ? `/${slug}` : ""}`, { article });
      navigate(`/article/${data?.article?.slug}`);
    } catch (error) {
      console.log(error.response.data); // 에러 메시지 확인
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="editor-page">
      <div className="container page">
        <div className="row">
          <div className="col-md-10 offset-md-1 col-xs-12">
            <FormErrors errors={errors} />
            <form onSubmit={handleSubmit}>
              <fieldset disabled={isSubmitting}>
                <fieldset className="form-group">
                  <input
                    name="title"
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Article Title"
                    value={article.title}
                    onChange={handleChange}
                  />
                </fieldset>
                <fieldset className="form-group">
                  <input
                    name="description"
                    type="text"
                    className="form-control"
                    placeholder="What's this article about?"
                    value={article.description}
                    onChange={handleChange}
                  />
                </fieldset>
                <fieldset className="form-group">
                  <textarea
                    name="body"
                    className="form-control"
                    rows={8}
                    placeholder="Write your article (in markdown)"
                    value={article.body}
                    onChange={handleChange}
                  />
                </fieldset>
                <fieldset className="form-group">
                  <TagsInput
                    value={article.tagList}
                    onChange={(newTags) => setArticle({ ...article, tagList: newTags })}
                  />
                </fieldset>
                <button className="btn btn-lg pull-xs-right btn-primary" type="submit" disabled={isSubmitting}>
                  Publish Article
                </button>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Editor;
