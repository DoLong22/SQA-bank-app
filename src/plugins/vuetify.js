import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import 'vuetify/dist/vuetify.min.css';
import vi from 'vuetify/lib/locale/vi';
import en from 'vuetify/lib/locale/en';

Vue.use(Vuetify);

export default new Vuetify({
  lang: {
    locales: { vi, en },
    current: 'vi',
  },
});
