import { useEffect } from 'react';

export const useInterval = (callback: () => void, duraiton: number = 1000) => {
  useEffect(() => {
    const id = setInterval(callback, duraiton);
    return () => clearInterval(id);
  }, [callback, duraiton]);
};
