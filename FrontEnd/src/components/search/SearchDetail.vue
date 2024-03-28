<template>
    <div class="section">
        <div>
          <v-icon icon="mdi-chevron-left" style="font-size: 50px; cursor: pointer;" @click="goBack">
          </v-icon>
        </div>
        <div class="section-1" style="margin-left: 10%; margin-top: 2%;">
            <div>
                <div style="width: 360px; height: 300px;">

                    <img 
                        referrerpolicy="no-referrer" 
                        :src="cropData.imageUrl" 
                        style="width: 100%; height: 100%; object-fit: cover;"
                    >
                    <div class="flex flex-row mt-3">
                      <v-icon
                          @click="checkcheck"
                          style="cursor: pointer; color: #FF4081;"
                          icon="mdi-heart"
                      >
                      </v-icon>
                      <span class="ml-1 font-normal"> {{ cropData.likes }}</span>
                    </div>

                </div>
<!--                <div class="mt-3">-->
<!--                    <v-icon -->
<!--                        v-if="heartCheck" -->
<!--                        @click="checkcheck"-->
<!--                        style="cursor: pointer; color: #FF4081;"-->
<!--                        icon="mdi-heart"-->
<!--                    >-->
<!--                    </v-icon>-->

<!--                    <v-icon -->
<!--                        v-else -->
<!--                        @click="checkcheck" -->
<!--                        style="cursor: pointer; color: #FF4081;" -->
<!--                        icon="mdi-heart-outline"-->
<!--                    >-->
<!--                    </v-icon>-->
<!--                </div>-->
            </div>
            <div class="crop">
              <div class="flex flex-row">
                <div class="crop-title">
                  {{ cropData.name }}
                </div>
                <div class="ml-2 mt-2">
                  <v-icon
                      v-if="heartCheck"
                      @click="checkcheck"
                      style="cursor: pointer; color: #FF4081;"
                      icon="mdi-heart"
                  >
                  </v-icon>

                  <v-icon
                      v-else
                      @click="checkcheck"
                      style="cursor: pointer; color: #FF4081;"
                      icon="mdi-heart-outline"
                  >
                  </v-icon>
                </div>
              </div>
                <div class="whitespace-pre-wrap mt-6 text-[#4F4F4F] ">
                    <div>

                        {{ cropData.cropInfo }}
                    </div>
                </div>

                <div class="window-frame">
                    <div class="quadrant top-left">
                        <div>
                            <v-icon icon="mdi-watering-can-outline" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            물주기
                        </div>
                        <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">
                            {{ cropData.waterPeriodInfo }}
                        </div>
                    </div>
                    <div class="quadrant top-right">
                        <div>
                            <v-icon icon="mdi-white-balance-sunny" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            일조량
                        </div>
                        <div class="whitespace-pre-wrap  text-[#4F4F4F] text-sm">
                            {{ cropData.sunshineInfo }}
                        </div>
                    </div>
                    <div class="quadrant bottom-left">
                        <div>
                            <v-icon icon="mdi-water" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            습도
                        </div>
                        <div class="whitespace-pre-wrap  text-[#4F4F4F] text-sm">
                            {{ cropData.humidityInfo }}
                        </div>
                    </div>
                    <div class="quadrant bottom-right">
                        <div>
                            <v-icon icon="mdi-thermometer-lines" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            온도
                        </div>
                        <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm">
                            {{ cropData.temperatureInfo }}
                        </div>
                    </div>
                </div>
            </div>
        </div>

        
        <div class="healthy-section">
            <div class="healthy-section-subtitle">
                기능성
            </div>
            <div class="healthy-section-title">
                건강상의 유익성
            </div>
            <div style="margin-top: 2%;">
                <img src="@/assets/healthy.png" alt="">
            </div>
            <div style="font-size: 17px; margin-top: 5%; width: 50%; white-space: wrap;">
                {{ cropData.effectInfo }}
            </div>
            <div style="margin-top: 5%;">

            </div>
            <div class="healthy-section-subtitle">
                키우는 방법
            </div>
            <div class="healthy-section-title">
                자세히 알아보기
            </div>
            <div style="font-size: 17px; margin-top: 5%; margin-bottom: 5%; white-space: wrap; text-align: center;">
                <a :href="cropData.link" style="font-weight: bold; border-bottom: solid 1px black;">{{ cropData.link }}</a>
            </div>
        </div>
    </div>
</template>

<script setup>
import * as Crop from '@/js/Crop';
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter()
const cropData = ref([])

const heartCheck = ref(false)

const goBack = () => {
  router.back();
}

function checkcheck () {
    heartCheck.value = !heartCheck.value
}

onMounted(() => {
    Crop.getCropById(
        route.params.cropId,
        (success) => {
            cropData.value = success.data
        },
        (error) => {
            console.error(error)
        }
    )
})

</script>

<style scoped>
.section {
  display: flex;
  justify-content: left;
  height: 100vh;
  margin-top: 15%;
  white-space: nowrap;
  flex-direction: column;
  font-family: 'Noto Sans KR', sans-serif;
}

.section-1 {
    display: inline-flex;
}

.healthy-section {
    margin-top: 10%; 
    display: flex; 
    justify-content: center; 
    align-items: center;
    flex-direction: column;
}

.healthy-section-subtitle {
    font-size: 12px; color: #00B564;
}

.healthy-section-title {
    margin-top: 1%; font-size: 20px; font-weight: 500;
}

.crop {
    margin-left: 8%;
}

.crop-title {
    font-weight: 700;
    font-size: 28px;
}

.window-frame {
    position: relative;
    margin-top: 5%;
    width: 100%;
    height: 300px;
    border-block: 1px solid #E0E0E0;
}

.window-frame::before, .window-frame::after {
    content: '';
    position: absolute;
    background: #E0E0E0;
}

.window-frame::before {
    /* 세로선 */
    left: 50%;
    width: 1px;
    height: 100%;
}

.window-frame::after {
    /* 가로선 */
    top: 50%;
    height: 1px;
    width: 100%;
}

.quadrant {
    position: absolute;
    width: 50%;
    height: 50%;
    display: flex;
    flex-direction: column;
}

.top-left {
    top: 1%;
    left: 3%;
    padding-right: 5%;
}

.top-right {
    top: 1%;
    right: -3%;
    padding-right: 5%;
}

.bottom-left {
    bottom: -1%;
    left: 3%;
    padding-right: 5%;
}

.bottom-right {
    bottom: -1%;
    right: -3%;
    padding-right: 5%;
}

.frame-title {
    font-weight: bold;
    margin-bottom: 5px;
}

</style>
