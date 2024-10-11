import { useState } from "react";

export default function TagsInput({ value, onChange }) {
  const [inputValue, setInputValue] = useState("");

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      if (inputValue.trim() !== "") {
        onChange([...value, inputValue.trim()]);
        setInputValue("");
      }
    }
  };

  const removeTag = (tagToRemove) => {
    onChange(value.filter((tag) => tag !== tagToRemove));
  };

  return (
    <>
      <input
        type="text"
        className="form-control"
        placeholder="Enter tags"
        value={inputValue}
        onChange={(e) => setInputValue(e.target.value)}
        onKeyDown={handleKeyDown}
      />
      <div className="tag-list">
        {value.map((tag, idx) => (
          <span key={idx} className="tag-default tag-pill">
            <i className="ion-close-round" onClick={() => removeTag(tag)} />
            {tag}
          </span>
        ))}
      </div>
    </>
  );
}
