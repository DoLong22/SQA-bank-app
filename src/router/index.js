import Vue from 'vue';
import VueRouter from 'vue-router';

import {
  ReportInformation,
  InterestCalculation,
  Registration,
  InterestLoans,
  ListLoan,
  ListPayment
} from '../modules';
import MainLayout from '../Layout/MainLayout.vue';
// routers
Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    redirect: '/registration',
    component: MainLayout,
    children: [
      { path: '/registration', component: Registration },
      { path: '/report-information', component: ReportInformation },
      { path: '/interest-calculation', component: InterestCalculation },
      { path: '/interest-loans', component: InterestLoans },
      {
        path: '/interest-loans/report-information/:id',
        component: InterestCalculation,
      },
      { path: '/pay-the-loan', component: InterestLoans },
      { path: '/customers/:id/loans', component: ListLoan },
      { path: '/loans/:id/payments', component: ListPayment },
    ],
  },
];
const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
