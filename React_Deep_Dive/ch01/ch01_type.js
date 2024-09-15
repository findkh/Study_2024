/** undefined
 * 선언한 후 값을 할당하지 않은 변수 또는 값이 주어지지 않은 인수에 자동으로 할당하는 값.
 */
let foo;
console.log(typeof foo === "undefined"); //true
// 변수 선언 후 초기화하지 않았으므로 undefined

function bar(hello) {
  return hello;
}
console.log(typeof bar() === "undefined"); //true
// 함수 호출 시 파라미터 값을 넘겨주지 않았으므로 undefined
console.log(typeof bar("aa") == "undefined"); //false

/** null
 * 아직 값이 없거나 비어 있는 값을 표현
 * typeof로 null을 확인했을 때 해당 타입이 아닌 object라는 결과가 반환된다.
 * 명시적으로 비어 있는 값을 의미.
 */
console.log(typeof null == "object"); //true

/** boolean
 * true와 false만을 가질 수 있는 데이터 타입
 * truthy, falsy값도 존재
 * falsy: 조건문 내부에서 false로 취급되는 값.
 *        ex) false, 0, NaN, null, undefined, '', "", ``
 * truthy: 조건문 내부에서 true로 취급되는 값 falsy를 제외한 값
 *         배열의 경우 값의 존재 여부는 상관없이 truthy
 */
if (1) {
  //true
}

if (0) {
  //false
}

if (NaN) {
  //false
}

console.log(Boolean(1)); //true
console.log(Boolean(0)); //false
console.log(Boolean(true)); //true

/** Number
 * MAX_SAFE_INTEGER: 2^53 - 1로, 안전하게 표현할 수 있는 최대 정수.
 * MIN_SAFE_INTEGER: -(2^53 - 1)로, 안전하게 표현할 수 있는 최소 정수.
 * 2진수, 8진수, 16진수 등 별도 데이터 타입을 제공하지 않음.
 * 각 진수 별로 값을 다르게 표현해도 동일한 값으로 표시된다.
 */
const a = 1;
const maxInteger = Math.pow(2, 53);
console.log(maxInteger - 1 === Number.MAX_SAFE_INTEGER); //true
const minInteger = -(Math.pow(2, 53) - 1);
console.log(minInteger === Number.MIN_SAFE_INTEGER); //true

// 10진수(decimal)
const decimal = 255;

// 2진수(binary)
const binary = 0b11111111;

// 8진수(octal)
const octal = 0o377;

// 16진수(hexadecimal)
const hexadecimal = 0xff;

// 출력
console.log(decimal); // 255 (decimal)
console.log(binary); // 255 (binary)
console.log(octal); // 255 (octal)
console.log(hexadecimal); // 255 (hexadecimal)

// 상호 비교
console.log(decimal === binary); // true
console.log(decimal === octal); // true
console.log(decimal === hexadecimal); // true

/** BigInt
 * number가 다룰 수 있는 숫자 크기의 제한 극복을 위해 ES2020에서 나옴
 */
console.log(9007199254740992 === 9007199254740993); //뒤에 숫자가 달라도 true가 나옴
const maxInteger2 = Number.MAX_SAFE_INTEGER;
console.log(maxInteger2 + 5 === maxInteger2 + 6); //true

//bitInt 정의 끝에 n을 붙이거나 BigInt함수 사용
const number = 9007199254740992;
const bigInt1 = 9007199254740992n;
const bigInt2 = BigInt(9007199254740992);

console.log(typeof number);
console.log(typeof bigInt1);
console.log(number == bigInt1); //true
console.log(number === bigInt1); //false -> 타입이 달라서...

/** String
 * 문자열이 원시 타입이며 변경 불가능함.
 */
const foo2 = "bar";
console.log(foo2[0]);
foo2[0] = "a";
console.log(foo2); //bar

/** Symbol
 * 중복되지 않는 고유값을 나타내기 위해 만들어짐.
 * 동일한 값을 사용하기 위해서는 Symbol.for을 사용한다.
 */
const key = Symbol("key");
const key2 = Symbol("key");

console.log(key === key2); //false
console.log(Symbol.for("hello") === Symbol.for("hello")); //true

/** 객체 타입
 */
console.log(typeof [] === "object"); //true
console.log(typeof {} === "object"); //true
function hello() {}
console.log(typeof hello === "function"); //true

const hello1 = function () {};
const hello2 = function () {};

console.log(hello1 === hello2); //false

/** 값을 저장하는 방식의 차이
 * 원시 타입은 불변 형태의 값으로 저장. -> 변수 할당 시점에 메모리 영역을 차지하고 저장된다.
 * 객체는 프로퍼티를 삭제, 추가, 수정할 수 있으므로 원시 값과 다르게 변경 가능한 형태로 저장되며, 값을 복사 할 때도 값이 아닌 참조를 전달한다.
 */

let hello3 = "hello world";
let hi = hello3;
console.log(hello3 === hi); //true

var hello4 = {
  greet: "hello, world",
};

var hi2 = {
  greet: "hello, world",
};

console.log(hello === hi); //false
console.log(hello4.greet === hi2.greet); //true

/**  Object.is
 * 두개의 파라미터를 받으며, 두개가 동일한지 확인하고 반환하는 메서드.
 * == : 같음을 비교하기 전에 양쪽이 같은 타입이 아니라면 강제 형변환을 한 후에 비교
 * Object.is : 형변환 없이 타입이 다르다면 false를 반환한다.
 */
console.log(-0 === +0); //true
console.log(Number.NaN === NaN); //false
console.log(Object.is(Number.NaN, NaN)); //true
console.log(NaN === 0 / 0); //false
console.log(Object.is(NaN, 0 / 0)); //true
