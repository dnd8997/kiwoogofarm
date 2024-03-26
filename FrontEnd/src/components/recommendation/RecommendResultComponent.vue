<template>
  <div>

    <!--로딩화면-->
    <div v-if="isLoading" class="flex justify-center items-center flex-col">
      <div class="mt-72">
        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="margin: auto; background: transparent; display: block; shape-rendering: auto;" width="100px" height="100px" viewBox="0 0 100 100" preserveAspectRatio="xMidYMid">
          <circle cx="50" cy="50" fill="none" stroke="#00b564" stroke-width="10" r="35" stroke-dasharray="164.93361431346415 56.97787143782138">
            <animateTransform attributeName="transform" type="rotate" repeatCount="indefinite" dur="1s" values="0 50 50;360 50 50" keyTimes="0;1"></animateTransform>
          </circle>
        </svg>
      </div>
      <div class="font-Notosans font-medium text-lg mt-3">분석중입니다..</div>
    </div>

    <!--결과 화면-->
    <div v-else class="flex justify-center items-center flex-col">
      <h2 class="text-3xl mt-40 font-Notosans font-medium">당신을 위한 농작물은</h2>

      <!--추천하는 농작물 리스트-->
      <swiper
          :effect="'coverflow'"
          :grabCursor="true"
          :centeredSlides="true"
          :slidesPerView="'auto'"
          :coverflowEffect="{
      rotate: 50,
      stretch: 0,
      depth: 100,
      modifier: 1,
      slideShadows: true,
    }"
          :pagination="true"
          :spaceBetween="20"
      >
        <swiper-slide v-for="(image, index) in result" :key="index">
          <div class="slide-content flex flex-col items-center">
            <img :src="image.url" :alt="`Image ${index + 1}`" class="" />
            <div class="mt-5 font-Notosans font-normal text-xl ">{{image.name}}</div>
          </div>
        </swiper-slide>
      </swiper>

      <div class="ml-16 text-3xl mt-12 text-[#00B564] font-Notosans font-medium cursor-pointer">
        요리추천 받으러 가기
        <v-icon
            class="mb-2 "
            icon="mdi-arrow-right"
            start
        ></v-icon>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted ,watch} from 'vue';
import { Swiper, SwiperSlide } from "swiper/vue";
import { useRoute, useRouter } from 'vue-router';
import "swiper/swiper.min.css";
import { useRecommendationStore } from '@/stores/recommend';

const recommendationStore = useRecommendationStore();
const router = useRouter();

  const route = useRoute();
const isLoading = ref(true);
const result = ref([]);

onMounted(() => {
  console.log(recommendationStore.recommendationData);
  result.value = recommendationStore.recommendationData;
  setTimeout(() => {
    isLoading.value = false;
  }, 3000);
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