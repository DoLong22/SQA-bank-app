const moneys = [-1, 0, 10, 1000, 10000, 1000000];

const formatMoney = (number) =>
  number.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');

console.log(moneys.map((num) => (num<0)? '0':formatMoney(num)));
