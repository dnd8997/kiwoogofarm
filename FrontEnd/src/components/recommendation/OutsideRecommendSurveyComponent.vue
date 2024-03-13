<template>
  <div class="mt-40 flex flex-row justify-center items-center">
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

    <!--    첫번째 질문-->
    <div  v-if="currentPage === 1" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">실외공간...</span>
      <img alt="Survey1" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey1.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[1].value === 1 }"
           @click="handleClick(1,1)" >실내공간</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[1].value === 2 }"
           @click="handleClick(1,2)" >베란다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[1].value === 3 }"
           @click="handleClick(1,3)">실외공간(마당,테라스)</div>

      <div class="py-10"></div>
    </div>

    <!--    두번째 질문-->
    <div v-if="currentPage === 2" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey2" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[2].value === 1 }"
           @click="handleClick(2,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[2].value === 2 }"
           @click="handleClick(2,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[2].value === 3 }"
           @click="handleClick(2,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!--    세번째 질문-->
    <div v-if="currentPage === 3" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(3/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey3" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[3].value === 1 }"
           @click="handleClick(3,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[3].value === 2 }"
           @click="handleClick(3,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[3].value === 3 }"
           @click="handleClick(3,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!--    네번째 질문-->
    <div v-if="currentPage === 4" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(4/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey4" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[4].value === 1 }"
           @click="handleClick(4,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[4].value === 2 }"
           @click="handleClick(4,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[4].value === 3 }"
           @click="handleClick(4,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!--    다섯번째 질문-->
    <div v-if="currentPage === 5" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(5/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey5" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[5].value === 1 }"
           @click="handleClick(5,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[5].value === 2 }"
           @click="handleClick(5,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[5].value === 3 }"
           @click="handleClick(5,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!--    여섯번째 질문-->
    <div v-if="currentPage === 6" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(6/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey6" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[6].value === 1 }"
           @click="handleClick(6,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[6].value === 2 }"
           @click="handleClick(6,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[6].value === 3 }"
           @click="handleClick(6,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!--    일곱번째 질문-->
    <div v-if="currentPage === 7" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(7/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
      <img alt="Survey7" class="w-80 h-80 mt-3" src="@/assets/cropsSurvey2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[7].value === 1 }"
           @click="handleClick(7,1)" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[7].value === 2 }"
           @click="handleClick(7,2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
           :class="{ 'bg-[#00B562] text-white': isSelected[7].value === 3 }"
           @click="handleClick(7,3)">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <button @click="nextPage" v-if="currentPage < 7" class="absolute w-40 h-40 right-96"> &gt;</button>
    <div v-if="currentPage === 7" class="font-Notosans font-bold text-xl  absolute w-40 h-40 right-96 cursor-pointer flex items-center justify-center" >
    추천 받기
      <v-icon
          icon="mdi-arrow-right"
          start
      ></v-icon>
    </div>

  </div>
</template>

<script>

export default {
  data() {
    return {
      currentPage: 1,
    };
  },

  methods: {
    nextPage() {
      if (this.currentPage < 7) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
  },

};
</script>

<script setup>
import { ref  } from 'vue';

const isSelected = {
  1: ref({ value: 0 }),
  2: ref({ value: 0 }),
  3: ref({ value: 0 }),
  4: ref({ value: 0 }),
  5: ref({ value: 0 }),
  6: ref({ value: 0 }),
  7: ref({ value: 0 }),
};

const handleClick = (index, value) => {
  isSelected[index].value =  value ;
  console.log(isSelected[index].value);
};
</script>

