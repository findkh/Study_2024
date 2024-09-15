/** 일급 객체
 * 다른 객체들에 일반적으로 적용 가능한 연산을 모두 지원하는 객체를 의미
 * 다른 함수의 매개 변수가 될 수도 있고 반환값이 될 수도 있으며 할당도 가능하다.
 */

function add(a, b) {
  return a + b;
}

const sum = function (a, b) {
  // 함수 표현식에서 함수의 이름을 생략하는것이 일반적
  return a + b;
};

console.log(add(1, 2));
console.log(sum(1, 2));

/** 함수 표현식과 선언식의 차이
 * 호이스팅: 함수 선언문이 코드 맨 앞단에 작성된 것처럼 작동
 * 함수 선언식인 경우 호이스팅 때문에 선언문이 미리 메모리에 등록 되었고 코드 순서에 상관없이 정상 호출된다.
 * 함수 표현식은 호이스팅 되는 시점에서 var의 경우 undefined로 초기화된다.
 * 변수는 런타임 이전에 undefined로 초기화 되고 할당문이 실행되는 시점(런타임 시점)에 함수가 할당되어 작동한다.
 */

hello();

function hello() {
  console.log("hello");
}

// console.log(typeof hello2 === "undefined");
// hello2(); //Uncaught ReferenceError: hello2 is not defined
// var hello2 = function () {
//   console.log("hello2");
// };

/** Function 생성자
 * 권장되지 않는 방법
 */
const add2 = new Function("a", "b", "return a + b");
console.log(add2(1, 1));

/** 화살표 함수
 * function이라는 키워드 대신 => 화살표를 활용해서 함수를 만든다.
 * constructor를 사용할 수 없다.
 * arguments가 존재하지 않는다.
 * this: 자신이 속한 객체나 자신이 생상할 인스턴스를 가리키는 값
 * 일반 함수로 호출 된다면 내부의 this는 전역 객체를 가리킨다.
 * 화살표 함수는 함수 자체의 바인딩을 갖지 않아서 함수 내부에서 this를 참조하면 상위 스코프의 this를 그대로 따르게 된다.
 */
const add3 = (a, b) => {
  return a + b;
};
console.log(add3(1, 2));

const add4 = (a, b) => a + b;
console.log(add4(2, 2));

/** 즉시 실행 함수
 * 함수를 정의하고 그 순간 즉시 실행되는 함수.
 * 한번만 호출되고 다시 호출할 수 없는 함수다.
 * 글로벌 스코프를 오염시지 않는 독립적인 함수 스코프를 운용할 수 있다는 장점이 있다.
 */
(function (a, b) {
  return console.log(a + b);
})(10, 24);

((a, b) => {
  return console.log(a - b);
})(24, 10);

/** 고차 함수
 * 함수를 인수로 받거나 결과로 새로운 함수를 반환시킬 수 있다.
 */
const doubleArray = [1, 2, 3].map((item) => item * 2);
console.log(doubleArray);

/** 함수 만들 때 주의 사항
 * - 함수의 부수 효과(Side Effect)를 최대한 억제하라
 * - 가능한 한 함수를 작게 만들어라
 * - 누구나 이해할 수 있는 이름을 붙여라
 */
