/** 클로저
 * 함수와 함수가 선언된 어휘적 환경의 조합
 * 선언적 어휘 환경 -> 변수가 코드 내부에서 어디에서 선언됐는지를 말하는 것.
 */

function add() {
  const a = 10;
  function innerAdd() {
    const b = 20;
    console.log(a + b);
  }
  innerAdd();
}

add();

/** 전역 스코프
 *    전역 레벨에 선언하는 것을 전역 스코프라 한다.
//  */
var global = "global scope";

function hello() {
  console.log(global);
}

console.log(global);
hello();
console.log(global === window.global); //true

/** 함수 스코프
 *
 */
if (true) {
  var global = "global scope";
}

console.log(global);
console.log(global == window.global); //true

function hello() {
  var local = "local variable";
  console.log(local);
}

hello();
// console.log(local); //Uncaught ReferenceError: local is not defined

var x = 10;
function foo() {
  var x = 100;
  console.log(x); // 100

  function bar() {
    var x = 1000;
    console.log(x); //1000
  }
  bar();
}
foo();

function Counter() {
  var counter = 0;

  return {
    increase: function () {
      return ++counter;
    },
    decrease: function () {
      return --counter;
    },
    counter: function () {
      console.log("counter에 접근");
      return counter;
    },
  };
}

var c = Counter();
console.log(c.increase());
console.log(c.increase());
console.log(c.increase());
console.log(c.increase());
console.log(c.decrease());
console.log(c.counter());

// 5만 출력됨...
for (var i = 0; i < 5; i++) {
  setTimeout(function () {
    console.log(i);
  }, i * 1000);
}

for (let i = 0; i < 5; i++) {
  setTimeout(function () {
    console.log(i);
  }, i * 1000);
}
