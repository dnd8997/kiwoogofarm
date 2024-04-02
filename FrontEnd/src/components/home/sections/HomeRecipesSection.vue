<template>
  <div id="recipes" class="flex flex-col section">
    <RouterLink to="/recipe" class="flex flex-row self-start ml-[420px] mt-16 font-Notosans font-bold text-4xl">
      <span class="flex items-center">다양한 레시피를 찾아보세요</span>
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1 " stroke="currentColor"
        class="w-12 h-12 ml-2">
        <path strokeLinecap="round" strokeLinejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
      </svg>
    </RouterLink>

    <div class="flex flex-row w-1/3 gap-10 mt-12">
      <div v-for="recipe in topThreeRecipe" :key="recipe.id" class="flex flex-col text-left font-Notosans">
        <div class="w-56 h-48 cursor-pointer" @click="router.push(`/recipedetail/${recipe.id}`)">
          <img :src="recipe.imageUrl" alt="recipeImage" class="w-56 h-48">
          <div class="mt-5 text-xl font-bold">{{ recipe.name }}</div>
          <div class="mt-3 text-lg font-light">{{ recipe.info }} </div>
        </div>
      </div>
      <!-- <div class="flex flex-col ml-56 text-left font-Notosans">
        <img src="@/assets/food1.png" alt="" class="w-56 h-48">
        <span class="mt-5 text-xl font-bold">버터 감자구이</span>
        <span class="mt-3 text-lg font-light">향긋한 버터의 향에 달달한<br> 설탕이 더해진 맛있는 감자구이 </span>
      </div>

      <div class="flex flex-col ml-24 text-left font-Notosans">
        <img src="@/assets/food2.jpg" alt="" class="w-56 h-48">
        <span class="mt-5 text-xl font-bold">쪽파 계란말이</span>
        <span class="mt-3 text-lg font-light">만들기도 쉽고 재료도 <br>간단하고 어른 아이 할 거 없이<br> 모두 좋아하는 반찬종류 중 하나 </span>
      </div>

      <div class="flex flex-col ml-24 font-Notosans text-lef">
        <img src="@/assets/food3.jpg" alt="" class="w-56 h-48">
        <span class="mt-5 text-xl font-bold text-left">베이컨 마늘<br>청경채볶음</span>
        <span class="mt-3 text-lg font-light text-left">밥 반찬은 물론 술안주로도<br> 잘 어울리는 요리 </span>
      </div> -->
    </div>
  </div>
</template>

<script setup>
import * as Recipe from '@/js/Recipe';
import { onMounted, ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';

const router = useRouter()
const topThreeRecipe = ref([])

onMounted(() => {
  Recipe.searchRecipes(
    (success) => {
      topThreeRecipe.value = success.data.slice(0, 3)
      console.log("여기", topThreeRecipe.value)
    },
    (error) => {
      console.error(error)
    }
  )
})


</script>

<style scoped></style>