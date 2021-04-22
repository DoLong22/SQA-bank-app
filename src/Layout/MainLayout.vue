<template>
  <v-app>
    <app-bar />
    <main-body />
  </v-app>
</template>

<script>
import AppBar from "./components/AppBar";
import MainBody from "./components/MainBody";
export default {
  components: { AppBar, MainBody },
};
</script>

<style>
</style>