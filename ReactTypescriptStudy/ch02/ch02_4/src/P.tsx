import type { FC, ReactNode } from "react";

export type PProps = {
  children?: ReactNode;
};

export const P: FC<PProps> = (props) => {
  const { children } = props;
  return <p children={children} />;
};
