// import { useEffect, useState } from 'react';
// import Clock from './pages/Clock';

import { useClock } from './hooks';
import Clock from './pages/Clock';

// export default function App() {
//   const [today, setToday] = useState(new Date());

//   useEffect(() => {
//     const duration = 1000;
//     const id = setInterval(() => {
//       setToday(new Date());
//     }, duration);
//     return () => clearInterval(id);
//   }, []);
//   return <Clock today={today} />;
// }

export default function App() {
  const today = useClock();
  return <Clock today={today} />;
}
