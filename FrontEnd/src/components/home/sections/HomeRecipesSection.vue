<template>
    <div id="recipes" class="section flex flex-col">

         <RouterLink to="/recipe" class="flex flex-row self-start ml-[420px] mt-16 font-Notosans font-bold text-4xl">
           <span class="flex items-center">다양한 레시피를 찾아보세요</span>
           <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"  stroke-width="1 " stroke="currentColor" class="w-12 ml-2 h-12">
             <path strokeLinecap="round" strokeLinejoin="round" d="M13.5 4.5 21 12m0 0-7.5 7.5M21 12H3" />
           </svg>
         </RouterLink>

        <div class="flex flex-row mt-12">

          <div class="flex flex-col font-Notosans text-left ml-56">
            <img src="@/assets/food1.png" alt="" class="w-56 h-48">
            <span class="font-bold text-xl mt-5">버터 감자구이</span>
            <span class="font-light text-lg mt-3">향긋한 버터의 향에 달달한<br> 설탕이 더해진 맛있는 감자구이 </span>
          </div>

          <div class="flex flex-col font-Notosans text-left ml-24">
            <img src="@/assets/food2.jpg" alt="" class="w-56 h-48">
            <span class="font-bold text-xl mt-5">쪽파 계란말이</span>
            <span class="font-light text-lg mt-3">만들기도 쉽고 재료도 <br>간단하고 어른 아이 할 거 없이<br> 모두 좋아하는 반찬종류 중 하나 </span>
          </div>

          <div class="flex flex-col font-Notosans text-lef  ml-24">
            <img src="@/assets/food3.jpg" alt="" class="w-56 h-48">
            <span class="font-bold text-xl text-left mt-5">베이컨 마늘<br>청경채볶음</span>
            <span class="font-light text-lg  text-left mt-3">밥 반찬은 물론 술안주로도<br> 잘 어울리는 요리 </span>
          </div>


        </div>
    </div>
</template>

<script>
import { RouterLink } from 'vue-router'

export default {
  components: {
    RouterLink
  }
}
</script>

<style scoped>

</style>