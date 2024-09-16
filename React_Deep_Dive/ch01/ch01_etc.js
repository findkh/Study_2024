/** 구조 분해 할당
 *
 */
const arr = [1, 2, 3, 4, 5];
const [first, second, third, ...arrayRest] = arr;
console.log(first);
console.log(second);
console.log(third);
console.log(arrayRest);

const [one, , , , five] = arr;
console.log(one);
console.log(five);

const obj = {
  a: 1,
  b: 2,
  c: 3,
  d: 4,
  e: 5,
};

const { a, b, c, ...objRest } = obj;
console.log(a);
console.log(b);
console.log(c);
console.log(objRest);

const { a: obj1, b: obj2 } = obj;
console.log(obj1);
console.log(obj2);

/** 전개 구문
 *
 */
const arr1 = ["a", "b"];
const arr2 = [...arr1, "c", "d"];
console.log(arr2);

const arr3 = ["a", "b"];
const arr4 = arr3;
console.log(arr3, arr4);
console.log(arr3 === arr4); //true -> 내용이 아닌 참조를 복사
const arr5 = [...arr3];
console.log(arr5 === arr3); //false -> 값만 복사했으므로

const obj3 = {
  a: 1,
  b: 2,
};

const obj4 = {
  c: 3,
  d: 4,
};

const newObj = { ...obj3, ...obj4 };
console.log(newObj);

/** Array 프로토타입 메서드
 * - map
 * - filter
 * - reduce
 * - forEach
 */
const arr6 = [1, 2, 3, 4, 5];
const doubledArr = arr.map((i) => i * 2);
console.log(doubledArr);

const evenArr = arr.filter((i) => i % 2 == 0);
console.log(evenArr);

const sum = arr6.reduce((result, i) => {
  return result + i;
}, 0);
console.log(sum);

const result2 = arr6.filter((i) => i % 2 === 0).map((i) => i * 100);
console.log(result2);

const result3 = arr6.reduce((result, i) => {
  if (i % 2 === 0) {
    result.push(i * 100);
  }
  return result;
}, []);
console.log(result3);

const arr7 = [1, 2, 3];
arr.forEach((i) => console.log(i));
