<template>
  <v-container>
    <v-row align="center" justify="center" no-gutters>
      <!-- <v-col cols="10"> -->
      <router-view></router-view>
      <!-- </v-col> -->
    </v-row>
  </v-container>
</template>

<script>
export default {
  methods: {
    helloWord() {
      console.log("Hello word");
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 1200px;
}
</style>
