<template>
    <div class="section">
      <div id="map"></div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

let map = null;

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    /* global kakao */
    script.onload = () => kakao.maps.load(initMap);
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${appkey}`;
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(36.3555, 127.2984),
    level: 3,
  };

  // 지도 객체를 등록합니다.
  // 지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
  map = new kakao.maps.Map(container, options);
};

</script>

<style scoped>
.section {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  height: 100vh;
}

#map {
  width: 80%;
  height: 80%;
}

</style>