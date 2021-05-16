<template>
  <!-- <v-alert class="success" type="success" transition="fade-transition">
    {{ message }}
  </v-alert> -->
  <div :class="type" role="alert">
    {{ message }}
  </div>
</template>
<script>
export default {
  name: "Notification",
  computed: {
    type() {
      return this.$store.state.notification.type ?? "alert alert-success";
    },
    message() {
      return this.$store.state.notification.message;
    },
    timeOut() {
      return this.$store.state.notification.timeOut ?? 1500;
    },
  },
  mounted() {
    console.log(this.type + this.message + this.timeOut);
    setTimeout(() => {
      this.$store.dispatch("hiddenNotification");
    }, this.timeOut);
  },
};
</script>

<style scoped>
.v-alert.v-sheet.theme--dark {
  position: absolute;
  z-index: 1000;
  max-width: max-content;
  min-width: 12.5rem;
  top: 40%;
  font-size: 1rem;
  left: 40%;
}
.alert {
  position: absolute;
  z-index: 1000;
  max-width: max-content;
  min-width: 10.5rem;
  top: 8%;
  font-size: 1rem;
  left: 89%;
}
</style>
