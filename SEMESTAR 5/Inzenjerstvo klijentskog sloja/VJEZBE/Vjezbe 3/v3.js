// // "use strict";

// //PRIMER FUNKCIJE

// // DECLARATION
// function juicer(apple, banana) {
//   console.log(`Sok je napravljen od ${apple} jabuka i ${banana} banana`);
// }

// //EXPRESSION'
// const juicerExpression = function (apple, banana) {
//   console.log(`Sok je napravljen od ${apple} jabuka i ${banana} banana`);
// };

// //POZIV SA PARAMETRIMA KOLIKO SE I OCEKUJE
// juicer(5, 10);
// juicerExpression(5, 10);

// //POZIV SA MANJE PARAMETARA I VISE PARAMETARA
// juicer();
// juicer(5, 10, 20);


// const arrowFunkcija = () => console.log("Poz iz arrow fje")
// arrowFunkcija()

// const arrowFunkcija = (nekiString) => {
//   let nekiDrugiString = '123'
//   nekiDrugiString += nekiString
//   return nekiDrugiString
// }
// arrowFunkcija('456')


// //ARROW FUNKCIJE
// const juicerArrow = (apple) => {
//     return `Sok je napravljen od ${apple} jabuka`
// }

// const printNumbers = {
//   phrase: 'The current value is:',
//   numbers: [1, 2, 3, 4],

//   loop() {

//     this.numbers.forEach(function(number) {
//       console.log(this.phrase, number)
//     })

//   },
// }

// printNumbers.loop()

// const printNumbers = {
//   phrase: 'The current value is:',
//   numbers: [1, 2, 3, 4],

//   loop() {
//     this.numbers.forEach(function(number) {
//       console.log(this.phrase, number);
//     }.bind(this));
//   },
// }

// printNumbers.loop();

// const printNumbers = {
//   phrase: 'The current value is:',
//   numbers: [1, 2, 3, 4],

//   loop() {
//     this.numbers.forEach(number => {
//       console.log(this.phrase, number)
//     })
//   },
// }

// const arrowPrimer = (poruka) => {
//     const novaPoruka = poruka + " iz arrow funkcije";
//     return novaPoruka;
// }
// console.log(arrowPrimer("Pozz"))

// //PRIMER ARROW NAD OBJEKTOM

// const me = {
//   firstName: "Luka",
//   year: 1998,
//   calcAge: () => console.log(2022 - this.year),
//   calcAgeStandard: function () {
//     console.log(2022 - this.year);
//   },
// };

// me.calcAge();
// me.calcAgeStandard();

// //SCOPE

// let a = 5;

// function functionA() {
//   console.log(a);

//   if (true) {
//     var b = 10;
//     console.log(b);
//   }

//   function functionB() {
//     console.log(b);
//     console.log(a);
//   }

//   functionB();
// }

// functionA();

// EXECUTION CONTEXT + JS RUNTIME

// function multiply(a, b) {
//   return a * b
// }

// function square(n) {
//   return multiply(n, n)
// }

// function printSquare(n) {
//   var squared = square(n)
//   console.log(squared)
// }

// printSquare(4)


// console.log('prvi')

// setTimeout(function test() {
//   console.log('drugi')
// }, 5000)

// console.log('treci')

// //DEFAULT PARAMETRI

// let bookings = [];
// const createBooking = function (flightNum, numPassengers = 1, price = 100) {
//   const booking = {
//     fligthtNum: flightNum,
//     numPassengers: numPassengers,
//     price: price,
//   };

//   console.log(booking);
//   bookings.push(booking);
// };

// createBooking("LH123");

// //HIGH-ORDER FUNKCIJE

// function firstWordUpper(str) {
//   const strSplit = str.split(" ");
//   return strSplit[0].toUpperCase();
// }

// function allUpper(str) {
//   return str.toUpperCase();
// }

// console.log(firstWordUpper("Poz iz javascripta!"))
// console.log(allUpper("Poz iz javascripta!"))

// function transformer(str, fn) {
//   console.log(fn(str));
//   console.log("Uradjeno od strane:" + fn.name);
// }

// transformer("javaScript kida!", allUpper);
// transformer("javaScript kida!", firstWordUpper);

// function hey(greet) {
//   return function (name) {
//     console.log(`${greet} ${name}`);
//   };
// }

// const greet = hey("Cao");
// greet("Luka");
// greet("Pera");
// greet("Mika");

// //CALL APPLY BIND

// const lufthansa = {
//   name: "Lufthansa",
//   code: "LH",
//   bookings: [],

//   book: function (name, number) {
//     this.bookings.push(number);
//     console.log(`${name} je bukirao pod brojem ${this.code}${number}`);
//   },
// };

// const swiss = {
//   name: "Swiss",
//   code: "SA",
//   bookings: [],
// };

// const booking = lufthansa.book;

// booking.call(lufthansa, "pera", "123");
// booking.call(swiss, "mika", "444");
// booking.apply(lufthansa, ["mika", "4444"]);
// const bookingLH = booking.bind(lufthansa);
// bookingLH("Perica", 55555);


// (function () {
//     console.log("run once");
// }
// )();

// const secureBooking = function () {
//   let bookingNum = 0;

//   fja1 = function () {
//     bookingNum++;
//     console.log(bookingNum);
//   }

//   return fja1
// }


// const secureBooking = function () {
//   let bookingNum = 0;

//   fja1 = function () {
//     bookingNum++;
//     console.log(bookingNum);
//   }

//   fja2 = function() {
//     bookingNum++;
//     console.log(bookingNum);
//   }

//   return [fja1, fja2]
// };

// [increment1, increment2] = secureBooking()
// increment1()
// increment1()
// increment1()
// increment2()
// increment2()
// increment2()

// //DESTRUKTURIRANJE NIZOVA

// const niz = [0, 1, "pera", 55, -1];
// const [x, ,z] = niz;
// console.log(x, z);

// //DESTRUKTURIRANJE OBJEKATA

// me = {
//     firstName: "Pera",
//     year: 1998
// }
// const { firstName: ime, year } = me;
// console.log(ime, year);

// // //SPREAD

// const newArr = [10, 20, 30]
// const arr = [newArr ,0, 1, 2, 3, 4, 5];
// console.log(arr)
// const newArrCopy = [8, 7, 9, ...arr];
// newArr.push(150)
// console.log(newArrCopy)
// ...[0, 5, 8]
// 0, 5, 8, -> [0, 5, 8]

// const [d, b, ...others] = [5, 10, 20, 30, 40];
// console.log(d, b, others);

// function add(...numbers) {
//   console.log(numbers);
// }

// add(5, 10, 20, 50);
// add(5, 10);

// const g = null || "" || "" || "" || 0
// console.log(g)

// // console.log(g)
// const a = "a" && 5 && 7 && null && ""
// console.log(Boolean(a))


// //NULLISH

// const restaurant = {
//   name: "Pizzeria",
//   numOfGuests: 0,
//   menu: ["Pizza", "Pasta", "Lasagnia", "Pizza", "Pasta"],
// };

// restaurant.numOfGuests ??= 5;
// console.log(restaurant.numOfGuests);

// //FOR-OF

// for (let menuItem of restaurant.menu) {
//   console.log(menuItem);
// }

// for (const item of Object.entries(restaurant)) {
//   console.log(item);
// }

// //SETOVI

// const set = new Set(restaurant.menu);
// console.log(set);
// set.add("Pizza")

// set.add("NovoJelo");
// console.log(set)

// //MAPE

// const map = new Map([
//   [3, "proba"],
//   [4, "test"],
// ]);
// map.set("kljuc1", "jedan");
// map.set(2, "dva");

// console.log(map.get(2));
// console.log(map)
// console.log(map.get("kljuc1"))