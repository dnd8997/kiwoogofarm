<script setup>
import { RouterLink, RouterView } from "vue-router";
import HeaderView from "@/views/HeaderView.vue";
</script>

<template>
  <v-app>
    <HeaderView />
    <RouterView />
  </v-app>
  <link
    href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
    rel="stylesheet"
  />
  <link
    href="https://cdn.jsdelivr.net/npm/@mdi/font@7.x/css/materialdesignicons.min.css"
    rel="stylesheet"
  />
</template>

<style scoped>
@font-face {
  font-family: "Noto Sans KR";
  src: url("/path/to/noto-sans-kr-regular.woff2") format("woff2");

  font-display: swap;
}
</style>
