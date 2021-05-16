<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="400">
      <v-card>
        <v-card-title class="headline">
          {{message}}
        </v-card-title>
        <!-- <v-card-text
          >Let Google help apps determine location. This means sending anonymous
          location data to Google, even when no apps are running.</v-card-text
        > -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="2" color="green darken-1" text @click="cancel"> Thoát </v-btn>
          <v-btn elevation="2" color="green darken-1" text @click="agree"> Đồng ý </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
export default {
  name: "Dialog",
  props: {
    message: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      dialog: true,
    };
  },
  methods: {
    cancel() {
      this.$emit("dialog-result", false);
      this.dialog = false;
    },
    agree() {
      this.$emit("dialog-result", true);
      this.dialog = false;
    },
  },
};
</script>
<style scoped>
</style>