import { P } from "./P";

export default function App() {
  // const text = [<p key="1">hello</p>, <p key="2">world</p>];
  // const texts = ["hello", "world"].map((text, idx) => <p key={idx}>{text}</p>);
  const texts = ["hello", "world"].map((text, idx) => (
    <P key={idx} children={text} />
  ));

  return <div children={texts}></div>;
}
