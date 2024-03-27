<template>
  <div class="section">

<!--    <div class="mb-8 flex flex-row font-Notosans">-->
<!--      <div class="pt-[132px]  text-sm">운영주체</div>-->
<!--      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">지자체</div>-->
<!--      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">민간단체</div>-->
<!--      <div class="pt-32 text-xl text-[#8F8F8D] ml-3">개인</div>-->
<!--      <div class="pt-32 text-xl ml-3">모두 표시</div>-->
<!--      <div class="pt-32 text-xl ml-8">-->
<!--        <input type="text" class="w-56 h-8 bg-[#F0F0F0] rounded-md text-sm pl-3" placeholder="지역명 검색" />-->
<!--      </div>-->
<!--    </div>-->

    <!--지도-->
    <div id="map" class="absolute left-0 top-32 w-full h-[80%] z-10"></div>

    <!--정보창-->
    <div v-if="isDivVisible" class="absolute top-32 right-0 w-[23%] h-[80%] bg-white z-20 font-Notosans">
      <img src="@/assets/close.svg" class="h-8 w-8 absolute right-3 top-3 cursor-pointer" @click="handleCloseClick">
      <div class="flex  w-full h-[32%] bg-blue-200 ">
        <img src="@/assets/garden.png" class="object-cover w-full h-full">
      </div>

      <div class="flex flex-col items-center">
        <span class="font-bold text-xl mt-6">동구행복나눔텃밭</span>
        <span class="font-light text-base text-[#696969] mt-3">대구광역시 동구 불로동 823-12</span>
      </div>

      <div class="flex flex-col ml-[15%]">
        <div class="flex flex-row mt-10">
          <span class="font-medium text-base">운영주체</span>
          <span class="font-light text-[#696969] text-base ml-6">지자체</span>
        </div>
        <div class="flex flex-row mt-4">
          <span class="font-medium text-base">부대시설</span>
          <span class="font-light text-[#696969] text-base ml-6">주차장, 화장실</span>
        </div>
        <div class="flex flex-row mt-4">
          <span class="font-medium text-base">신청방법</span>
          <span class="font-light text-[#696969] text-base ml-6">방문</span>
        </div>
        <div class="flex flex-row mt-4">
          <span class="font-medium text-base">분양가격</span>
          <span class="font-light text-[#696969] text-base ml-6">미정</span>
        </div>
      </div>

      <div class="flex flex-row mt-[18%] justify-center" >
        <div class="cursor-pointer border-[1px] border-[#EC6B6B] text-[#EC6B6B]  rounded-lg w-28 h-12 flex flex-row items-center justify-center " @click="handleSaveClick">
          <img v-if="!isSave" alt="" src="@/assets/heart.svg" class="h-6 w-6 mr-1.5">
          <img v-else alt="" src="@/assets/fullheart.svg" class="h-6 w-6 mr-1.5" >
          찜하기
        </div>
        <div class="cursor-pointer ml-6 w-36 h-12 bg-[#86BF60] text-white  rounded-lg flex items-center justify-center" @click="openNewWindow">상세정보</div>
      </div>
    </div>
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
      longitude: 0,
      isSave: false,
      isDivVisible: false
    };
  },
  created() {
    if (!("geolocation" in navigator)) {
      return;
    }

    // get position
    navigator.geolocation.getCurrentPosition(
        (pos) => {
          this.latitude = pos.coords.latitude;
          this.longitude = pos.coords.longitude;

          if (window.kakao && window.kakao.maps) {
            this.initMap();
          } else {
            const script = document.createElement("script");
            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
                "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7d1ba57eb57e1922b3275d2bcb6791e8";
            document.head.appendChild(script);
          }
        },
        (err) => {
          alert(err.message);
        }
    );
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
        this.markers = [];
      }

      const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map((position) =>
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
      const newMarkers = [
        {
          title: "카카오",
          latlng: new kakao.maps.LatLng(36.3744745, 127.2965807),
        },
        {
          title: "생태연못",
          latlng: new kakao.maps.LatLng(36.3331874, 127.2967792),
        },
        {
          title: "텃밭",
          latlng: new kakao.maps.LatLng(36.3387924, 127.2849865),
        },
        {
          title: "근린공원",
          latlng: new kakao.maps.LatLng(33.451393, 126.570738),
        },
      ];

      const imageSrc =
          "https://i.ibb.co/ch1GjBH/marker.png";

      for (let i = 0; i < newMarkers.length; i++) {
        let imageSize = new kakao.maps.Size(40, 54);
        let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        let marker = new kakao.maps.Marker({
          map: this.map, // 수정: this.map으로 변경
          position: newMarkers[i].latlng,
          title: newMarkers[i].title,
          image: markerImage,
        });

        kakao.maps.event.addListener(marker, "click", () => {
          // 클릭된 마커에 대한 처리를 수행하는 함수 호출
          this.handleMarkerClick(newMarkers[i]);
        });
      }
      this.map.setBounds(bounds);
    },

    handleMarkerClick() {
      this.isDivVisible = true;
    },
    handleCloseClick() {
      this.isDivVisible = false;
    },
    handleSaveClick() {
      this.isSave = !this.isSave;
    },
    openNewWindow() {
      // 새 창 열기
      window.open(
          'http://soil.rda.go.kr/geoweb/soilInfoPopup.do?soilsign=SqC',
          'dialog',
          'modal=yes,dialog=yes,width=1000,height=600'
      );
    }
  },

};
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
