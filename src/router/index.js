import Vue from 'vue';
import VueRouter from 'vue-router';

import ReportInformation from '../modules/report-information/ReportInformation.vue';
import InterestCalculation from '../modules/interest-calculation/InterestCalculation.vue';
import LoanRegistration from '../modules/loan-registration/LoanRegistration.vue'
import MainLayout from '../Layout/MainLayout.vue'
// routers
Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        redirect: '/loan-registration',
        component: MainLayout,
        children: [
            { path: '/loan-registration', component: LoanRegistration },
            { path: '/report-information', component: ReportInformation },
            { path: '/interest-calculation', component: InterestCalculation },
        ],
    },
];
const router = new VueRouter({
    mode: 'history',
    routes,
});

export default router;
