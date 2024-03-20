<template>
  <div class="about">
    <p @click="clickCrops" style="cursor: pointer;">
      HELLO!!!!!!!!!!!!
    </p>


    
  </div>
</template>

<script setup>
import * as Crop from '@/js/Crop';

const clickCrops = () => {
  Crop.getCropById(
    1,                                                                   
    (success) => {
      console.log(success);
    },
    (error) => {
      console.log(error);
    }
  )
}

</script>

<style>

.about {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

</style>
