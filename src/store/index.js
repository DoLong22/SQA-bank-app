import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const plugins = [];

const rootState = {
  notification: {
    isDisplay: false,
    message: 'Notification',
    type: 'success',
    timeOut: 2000,
  },
  informationPersonal: {
    informPersonal: {
      name: '',
      birthday: '',
      gender: '',
      job: '',
      nameCompany: '',
      department: '',
      salary: '',
      identityCode: '',
      identityCreatedFrom: '',
      identityCreatedAt: '',
    },
    informContact: {
      email: '',
      phone: '',
      address: '',
      hometown: '',
    },
    informProtector: {
      nameProtector: '',
      phoneProtector: '',
      addressProtector: '',
    },
  },
  informationPersonalDefault: {
    informPersonal: {
      name: '',
      birthday: '',
      gender: '',
      job: '',
      nameCompany: '',
      department: '',
      salary: '',
      identityCode: '',
      identityCreatedFrom: '',
      identityCreatedAt: '',
    },
    informContact: {
      email: '',
      phone: '',
      address: '',
      hometown: '',
    },
    informProtector: {
      nameProtector: '',
      phoneProtector: '',
      addressProtector: '',
    },
  },
};
const rootActions = {
  displayNotification({ commit }, payload) {
    commit('DISPLAY_NOTIFICATION', payload);
  },
  hiddenNotification({ commit }) {
    commit('HIDDEN_NOTIFICATION');
  },
  setInformation({ commit }, informationPersonal) {
    commit('SET_INFORMATION', informationPersonal);
  },
  resetInformation({commit,state}){
    commit('SET_INFORMATION',state.informationPersonalDefault)
  }
};
const rootMutations = {
  DISPLAY_NOTIFICATION(state, notification) {
    state.notification = notification;
  },
  HIDDEN_NOTIFICATION(state) {
    state.notification = { ...state.notification, isDisplay: false };
  },
  SET_INFORMATION(state, information) {
    state.informationPersonal = {
      ...state.informationPersonal,
      ...information,
    };
  },
  SET_INFORMATION_PERSONAL(state, informPersonal) {
    state.informationPersonal.informPersonal = {
      ...state.informationPersonal.informPersonal,
      ...informPersonal,
    };
  },
};
const getters = {
  getInformationPersonal(state) {
    return state.informationPersonal;
  },
};
export default new Vuex.Store({
  state: rootState,
  mutations: rootMutations,
  actions: rootActions,
  getters,
  plugins,
});
