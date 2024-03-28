<template>
  <div>
    <!--결과 화면-->
    <div class="flex flex-col items-center justify-center">
      <h2 class="mt-40 text-3xl font-medium font-Notosans">당신을 위한 레시피는</h2>

      <!--추천하는 레시피 리스트-->
      <swiper :effect="'coverflow'" :grabCursor="true" :centeredSlides="true" :slidesPerView="'auto'" :coverflowEffect="{
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows: true,
      }" :pagination="true" :spaceBetween="20">
        <swiper-slide v-for="(recipe, index) in result" :key="index">
          <div class="flex flex-col items-center slide-content" @click="router.push(`/recipedetail/${recipe.id}`)">
            <img :src="recipe.image" :alt="`Image ${index + 1}`" referrerpolicy="no-referrer" />
            <div class="mt-5 text-xl font-normal font-Notosans ">{{ recipe.name }}</div>
          </div>
        </swiper-slide>
      </swiper>
    </div>
  </div>
</template>

<script setup>
import { useRecipeRecommendationStore } from '@/stores/recipeRecommend';
import "swiper/swiper.min.css";
import { Swiper, SwiperSlide } from "swiper/vue";
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const recipeRecommendationStore = useRecipeRecommendationStore();
const router = useRouter();

const route = useRoute();
const isLoading = ref(true);
const result = ref([]);

onMounted(() => {
  result.value = recipeRecommendationStore.recipeRecommendationData;
  console.log(result.value)
});

</script>

<style scoped>
.swiper {
  width: 100%;
  padding-top: 50px;
  padding-bottom: 50px;
}

.swiper-slide {
  background-size: cover;
  width: 300px;
  height: 300px;
  font-size: 24px;
  font-weight: 700;
  display: flex;
  justify-content: center;
  background: white;
  border-radius: 0.375rem;
  box-shadow: rgba(0, 0, 0, 0.25) 0px 4px 15px 0px;
}

.slide-content img {
  width: 300px;
  height: 230px;
  object-fit: cover;
}
</style>