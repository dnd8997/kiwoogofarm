<template>
    <v-container class="main-container">
        <SearchComponent />
    </v-container>
</template>

<script setup>
import SearchComponent from '@/components/search/SearchComponent.vue';
</script>

<style scoped>
.main-content {
  flex: 1;
  max-width: max-content;
  padding: 1em;
}
</style>