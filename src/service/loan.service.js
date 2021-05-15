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

export default { calFollowOriginal, calFollowDecreasing };
