<template>
  <v-main>
    <notification v-if="isDisplay" />
    <router-view />
  </v-main>
</template>

<script>
import Notification from "./components/Notification.vue";
export default {
  name: "App",
  components: { Notification },
  computed: {
    isDisplay() {
      return this.$store.state.notification.isDisplay;
    },
  },
};
</script>
