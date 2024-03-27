<template>
  <div class="section">
    <div class="mb-8 flex flex-row font-Notosans">
      <div class="pt-[132px]  text-sm">운영주체</div>
      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">지자체</div>
      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">민간단체</div>
      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">개인</div>
      <div class="pt-32 text-xl ml-3">모두 표시</div>
      <div class="pt-32 text-xl ml-8">
        <input type="text"  class="w-56 h-8 bg-[#F0F0F0] rounded-md text-sm pl-3" placeholder="지역명 검색"/>
      </div>
    </div>
    <div id="map" class="absolute left-0.5 w-full h-[75%]"></div>
  </div>
</template>

<script>

export default {
  name: "test",
  data() {
    return {
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0
    }
  },
  created() {
    if (!("geolocation" in navigator)) {
      return;
    }

    // get position
    navigator.geolocation.getCurrentPosition(pos => {
      this.latitude = pos.coords.latitude;
      this.longitude = pos.coords.longitude;

      if (window.kakao && window.kakao.maps) {

        this.initMap();

      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7d1ba57eb57e1922b3275d2bcb6791e8";
        document.head.appendChild(script);
      }

    }, err => {
      alert(err.message);
    })
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.latitude, this.longitude),
        level: 6,
      };
      this.map = new kakao.maps.Map(container, options);
      this.displayMarker([[this.latitude, this.longitude]]);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
            (position) =>
                new kakao.maps.Marker({
                  map: this.map,
                  position,
                })
        );

        const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
        );

      }
      this.map.setBounds(bounds);
    }
  }
}
</script>


<style scoped>
.test {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

</style>
