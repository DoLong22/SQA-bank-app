export function principalPerMonthByOriginal(month, loan) {
    return Math.round(loan / month);
}
export function interestPerMonthByOriginal(month , loan, interestRate) {
    return Math.round((loan * interestRate / 100) / month);
}
export function totalPerMonthByOriginal(loan, interestRate) {
    return loan * interestRate / 100;
}