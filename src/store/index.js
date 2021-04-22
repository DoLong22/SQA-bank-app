import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const plugins = [];

// set root state, action & mutation
const rootState = {
    showLoadingIndicator: false,
    barColor: 'rgba(0, 0, 0, .8), rgba(0, 0, 0, .8)',
    barImage: 'https://demos.creative-tim.com/material-dashboard-pro/assets/img/sidebar-1.jpg',
    drawer: null,
};
const rootActions = {
    setLoadingIndicator(context, showLoadingIndicator) {
        context.commit('SET_SHOW_LOADING_INDICATOR', showLoadingIndicator);
    },
    setCurrentLang(context, currentLang) {
        context.commit('SET_CURRENT_LANG', currentLang);
    },
};
const rootMutations = {
    SET_SHOW_LOADING_INDICATOR(state, showLoadingIndicator) {
        state.showLoadingIndicator = showLoadingIndicator;
    },
    SET_BAR_IMAGE(state, payload) {
        state.barImage = payload;
    },
    SET_DRAWER(state, payload) {
        state.drawer = payload;
    },
    SET_SCRIM(state, payload) {
        state.barColor = payload;
    },
    SET_CURRENT_LANG(state, currentLang) {
        state.currentLang = currentLang;
    },
};

export default new Vuex.Store({
    modules: {
    },
    state: rootState,
    mutations: rootMutations,
    actions: rootActions,
    plugins,
});
