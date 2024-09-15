/** Class
 * - constructor
 *    생성자로 객체를 생성하는데 사용하는 메서드다.
 *    단 하나만 존재할 수 있다.
 * - 프로퍼티
 *    클래스로 인스턴스를 생성할 때 내부에 정의할 수 있는 속성값을 의미.
 * - getter와 setter
 *    getter를 사용하기 위해서는 get, setter를 사용하기 위해서는 set 키워드를 사용한다.
 * - 인스턴스 메서드(=프로토타입 메서드)
 *    클래스 내부에서 선언한 메서드.
 *  - 정적 메서드
 *    클래스의 인스턴스가 아닌 이름으로 호출할 수 있는 메서드
 *    this에 접근할 수 없지만 인스턴스를 생성하지 않아도 사용할 수 있는 장점 -> 재활용 가능
 *    애플리케이션 전역에서 사용하는 유틸 함수를 정적 메서드로 활용한다.
 */

class Car {
  constructor(name) {
    this.name = name;
  }

  hello() {
    console.log(`안녕하세요. ${this.name}입니다.`);
  }

  static staticHello() {
    console.log("정적 메서드 호출");
  }

  get firstCharacter() {
    return this.name[0];
  }

  set firstCharacter(char) {
    this.name = [char, ...this.name.slice(1)].join("");
  }
}

const myCar = new Car("박수붕");
console.log(myCar);
console.log(myCar.firstCharacter);
myCar.firstCharacter = "차";
console.log(myCar.firstCharacter, myCar.name);
myCar.hello();
console.log(Object.getPrototypeOf(myCar));
console.log(Object.getPrototypeOf(myCar) === Car.prototype); //true
console.log(myCar.__proto__ === Car.prototype); //true
Car.staticHello();
