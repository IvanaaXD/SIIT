
// const friend1 = "Pera";
// const friend2 = "Mika";
// const friend3 = "Zika";

// // friend1 = "Izmenjen";

// const friends = [friend1, friend2, friend3];
// console.log(friends)
// const friendsNew = new Array(friend1, friend2);
// console.log(friend1);
// console.log(friendsNew[0]);
// console.log(friends.pop());
// friends.push("NoviPrijatelj");
// console.log(friends)
// friends.unshift("NoviNaPocetak");
// console.log(friends);

// friends = [];

// Obratiti pažnju da i niz i objekat imaju za sebe uvezan prototype!
// const myInfo = ["Luka", 1998, friends];
// console.log(myInfo)
// console.log(myInfo[0]);

// Ne gledati na funkciju kao na metodu u okviru klase, već kao na polje objektnog literala koji nosi
// definiciju funkcije u okviru sebe!
// const me = {
//     firstName: 'Luka',
//     friends: friends,
//     birthYear: 1998,
//     calcAge: function() { 
//         console.log(2023 - this.birthYear)
//     }
// }

// let car = {};

// const features = ['color', 'model', 'year'];

// for (let i = 0; i < features.length; i++) {
//   let featureName = features[i];
  
//   car[featureName] = 'N/A';
// }

// car['color'] = 'red';
// car['model'] = 'Sedan';
// car['year'] = 2022;

// console.log(car);

// console.log(me)
// me.calcAge()

// const me = {
//   firstName: "Luka",
//   friends: friends,
//   birthYear: 1998,
//   calcAge: function () {
//     return 2023 - this.birthYear;
//   },
// };

// console.log(me.calcAge());

// console.log(me.friends);
// console.log(me.calcAge(1998));
// console.log(me.calcAge());

// let age = 32;
// const newAge = age;
// age = 33;
// console.log("age:" + age)
// console.log("newAge:" + newAge)

// let pera = {
//     firstName: 'pera'
// }

// let zika = pera;
// zika.firstName = "nestoDrugo"

// console.log(pera)
// console.log(zika)

// let zika = {
//     firstName: 'zika'
// }

// let mika = pera;
// mika.lastName = 'mikic'
// mika.firstName = 'mika'
// mika = zika;

// console.log(pera)
// console.log(mika)

// console.log("age:" + age);
// console.log("newAge:" + newAge);

// const me = {
//     firstName: 'Luka',
//     friends: friends
// }

// const you = Object.assign({}, me);
// you.firstName = "Perica";
// console.log(you)
// console.log(me)
// you.friends.push("PericinNoviPrijatelj");

// // console.log(me);
// // console.log(you);


// me = {
//     firstName: "Luka",
//     birthYear: 1998,
//     calcAge: function() {
//         console.log(2023 - this.birthYear)
//     }
// }

// you = {
//     firstName: "Perica",
//     birthYear: 1995
// }

// you.calcAge = me.calcAge;
// you.calcAge()


// const otherPerson = {};
// otherPerson.calcAge = me.calcAge;
// otherPerson.name = "OtherPerson";
// otherPerson.calcAge();
// // let a = 50;

// // if (60 > 50) {
// //   let a = 60;
// // }

// // console.log(a);

// console.log(this);
// console.log(friends);

// Array.prototype.vratiDrugi = function () {
//   return this[1];
// };

// console.log(friends.vratiDrugi())

// Array.prototype.asd = "Neki rendom string";

// console.log(friends.asd)
// console.log(friends.hasOwnProperty("asd"))

// console.log(friends.__proto__ == Array.prototype)
// console.log(Array.prototype.isPrototypeOf(friends))
// console.log(Array.prototype)

// console.log(friends.__proto__.__proto__.__proto__)

// console.log(friends)

// console.log(friends.vratiDrugi());

// console.log(friends.__proto__.__proto__)

// const Person = function (firstName, birthYear) {
//   this.firstName = firstName;
//   this.birthYear = birthYear;
// };

// Object.prototype.nekiString = "nekiString"

// me = new Person("a", "b")
// console.log(me.__proto__.__proto__.__proto__)
// console.log
// console.log(me.hasOwnProperty("firstName"))

// Person.prototype.calcAge = function () {
//     return 2023 - this.birthYear;
// };

// if (Array.prototype.isPrototypeOf(friends)) {
//     console.log(true)
// }

// Object.prototype.nekiString = "nekiString"
// const me = new Person("Luka", 1998);
// console.log(me.calcAge())

// if (Object.prototype.isPrototypeOf(me)) {
//     console.log(true)
// }

// console.log(me.nekiString)

// console.log(me.__proto__.__proto__.__proto__);

// class Person {
//     constructor(firstName, birthYear) {
//         this.firstName = firstName;
//         this.birthYear = birthYear;
//     }

//     clacAge() {
//         console.log(2023 - this.birthYear)
//     }
// }

// let newPerson = new Person("test", 1976);
// newPerson.clacAge()
// console.log(Person.prototype)

// const PersonPrototype = {
//     calcAge() {
//         console.log(2023 - this.birthYear);
//     }
// }

// const me = Object.create(PersonPrototype);
// me.firstName = 'Luka';
// me.birthYear = 1998;

// console.log(me.__proto__.__proto__)
// me.calcAge()

// class NewPerson {
//   constructor(firstName, birthYear) {
//     this.firstName = firstName;
//     this.birthYear = birthYear;
//   }
//   calcAge() {
//     console.log(2022 - this.birthYear);
//   }

//   get firstName() {
//     return this.firstName;
//   }

//   set firstName(firstName) {
//     this.firstName = firstName;
//   }
// }

// const newMe = new NewPerson("Luka", 1998);
// console.log(newMe);
// console.log(newMe.firstName);

// class Student extends NewPerson {
//   constructor(firstName, birthYear, course) {
//     super(firstName, birthYear);
//     this.course = course;
//   }

//   calcAge() {
//     console.log(2022 - this.birthYear + " i am student");
//   }
// }

// const newStudent = new Student("Pera", 1998, "psw");
// newStudent.calcAge()
