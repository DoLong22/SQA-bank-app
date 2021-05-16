import axiosInstance from '../plugins/axios';
const registration = ({ name, birthday, job, salary, identityCode }) => {
  return axiosInstance.post('/customers/registration', {
    name,
    birthday,
    job,
    salary,
    identityCode,
  });
};
const reportInformation = (customer) => {
  console.log(customer)
  return axiosInstance.put('/customers/report-information', customer);
};

const getCustomers = () =>{
  return axiosInstance.get('/customers');
}
const userService = {
  registration,
  reportInformation,
  getCustomers,
};

export default userService;
