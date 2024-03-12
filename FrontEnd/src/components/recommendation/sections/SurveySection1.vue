<template>
  <div class="flex flex-col justify-center items-center">
    <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/7)</span>
    <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키울 곳은<br>어디인가요?</span>
    <img class="w-80 h-80 mt-3" src="../../../assets/cropsSurvey1.jpg">

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 1 }"
         @click="handleClick(1)" >실내공간</div>

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 2 }"
         @click="handleClick(2)" >실내공간</div>

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 3 }"
         @click="handleClick(3)">실내공간</div>

    <div class="py-10"></div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const isSelected = ref({ value: 0 });

const handleClick = (value) => {
  isSelected.value = { value };
};
</script>
