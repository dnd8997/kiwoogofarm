<template>
  <div class="about">
    <p @click="clickCrops" style="cursor: pointer;">
      HELLO!!!!!!!!!!!!
    </p>


    <p @click="clickEmail">
      이메일 전송 테스트
    </p>
    {{ token.email }}

    <p>

      {{ nickName }}
    </p>
  </div>
</template>

<script setup>
import * as Crop from '@/js/Crop';
import * as MyPage from '@/js/MyPage';
import { jwtDecode } from 'jwt-decode';
import { ref } from 'vue';
screenX
const token = jwtDecode(localStorage.getItem('accessToken'))
const nickName = ref(null)

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

const clickEmail = () => {
  MyPage.getUserinfo(
    token.email,
    (success) => {
      console.log(success.data);
      nickName.value = success.data
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
