export default function FormErrors({ errors }) {
  if (!errors || Object.keys(errors).length === 0) return null;

  return (
    <ul className="error-messages">
      {Object.entries(errors).map(([key, messages]) =>
        messages.map((message) => (
          <li key={`${key} ${message}`}>
            {key} {message}
          </li>
        ))
      )}
    </ul>
  );
}
