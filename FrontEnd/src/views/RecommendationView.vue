<template>
    <v-container class="main-container">
        <RecommendationComponent />
    </v-container>
</template>

<script setup>
import RecommendationComponent from '@/components/recommendation/RecommendationComponent.vue';
</script>

<style scoped>
.main-content {
  flex: 1;
  max-width: max-content;
  padding: 1em;
}
</style>