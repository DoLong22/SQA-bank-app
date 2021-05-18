import axiosInstance from '../plugins/axios';

const calFollowOriginal = ({
  loan,
  interestRate,
  interestType,
  numOfMonths,
  dateOfLoan,
}) => {
  return axiosInstance.post('/calculation/original', {
    loan,
    interestRate,
    interestType,
    numOfMonths,
    dateOfLoan,
  });
};

const calFollowDecreasing = ({
  loan,
  interestRate,
  interestType,
  numOfMonths,
  dateOfLoan,
}) => {
  return axiosInstance.post('/calculation/decreasing', {
    loan,
    interestRate,
    interestType,
    numOfMonths,
    dateOfLoan,
  });
};

const requestLoan = ({
  loan,
  interestRate,
  interestType,
  numOfMonths,
  dateOfLoan,
  customerId,
}) => {
  return axiosInstance.post(`/customers/${customerId}/loans`, {
    loan,
    interestRate,
    interestType,
    numOfMonths,
    dateOfLoan,
    customerId,
  });
};

const getLoans = (idCustomer) => {
  return axiosInstance.get(`/customers/${idCustomer}/loans`);
};

const getPayments = (idLoan) => {
  return axiosInstance.get(`/loans/${idLoan}/payments`);
};

const updatePayment = (payment, idLoan) => {
  return axiosInstance.put(`/loans/${idLoan}/payments`, payment);
};

export default {
  calFollowOriginal,
  calFollowDecreasing,
  requestLoan,
  getLoans,
  getPayments,
  updatePayment,
};
