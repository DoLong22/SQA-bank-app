import Vue from 'vue';
import VueRouter from 'vue-router';

import {
  ReportInformation,
  InterestCalculation,
  Registration,
  InterestLoans,
  BorrowerInformation,
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
      { path: '/interest-loans/register', component: BorrowerInformation },
    ],
  },
];
const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;
