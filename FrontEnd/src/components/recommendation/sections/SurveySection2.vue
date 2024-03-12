<template>
  <div class="flex flex-col justify-center items-center">
    <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/7)</span>
    <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br>경험이 있나요?</span>
    <img class="w-80 h-80 mt-3" src="../../../assets/cropsSurvey2.jpg">

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 1 }"
         @click="handleClick(1)" >아니요, 처음입니다.</div>

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 2 }"
         @click="handleClick(2)" >많이는 아니지만, 소소하게 키워봤습니다.</div>

    <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 bg-[#F6F6F3] text-[#444444] font-Notosans font-medium text-base"
         :class="{ 'bg-[#00B563] text-white': isSelected.value === 3 }"
         @click="handleClick(3)">꽤 많이 키워봤습니다.</div>

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
