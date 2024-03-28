<template>
    <div id="recommendation" class="section">
        <div class="recommendation-box">
            <div id="box-title">
                <p id="nickname">연우</p>
                <p>님을 위한 농작물 추천</p>
            </div>
            <v-row
                cols="4"
                class="recommendation-vegetable-list delay-animate"
                style="justify-content: space-evenly; width: 60%; margin-bottom: 3%;"
            >
                <v-col v-for="(crop, index) in cropRecommends" :key="index" style="margin-left: 5%;">
                    <div style="display: flex; justify-content: center;" class="cursor-pointer" @click="router.push(`/search/${crop.id}`)">
                      <v-avatar size="160">
                        <v-img :src="crop.image_url" alt="" class="object-cover"></v-img>
                      </v-avatar>
                    </div>
                    <p class="font-Notosans font-medium text-xl mt-3">{{ crop.name }}</p>
                </v-col>
            </v-row>

          <router-link to="/recommendation" class="recommendation-button delay-animate">
            더 많은 추천 받으러 가기 →
          </router-link>
        </div>
    </div>
</template>

<script setup>
import {onMounted, ref} from 'vue';
import axios from "axios";
import {useRouter} from "vue-router";

const router = useRouter()

const vegetableList = ref([
    { src: '/vegetable/vegetable_1.png', name: '청치마상추', introduction: "우리나라에서만 주로 재배되는'청치마상추'입니다. 맛이 순하고 잎이 부드러워서 어린이도 먹기 좋죠.우리 가족을 위한 건강한 쌈채소입니다."},
    { src: '/vegetable/vegetable_2.png', name: '방울토마토', introduction: "방울토마토는 일반 토마토보다 당도가 높고 영양성분은 더 풍부하게 들어 있는 과채류 숙성채소예요. 토마토보다 먹기에 더 간편하고, 주식보다는 간식이나 후식으로 많이 이용합니다."},
    { src: '/vegetable/vegetable_3.png', name: '겨자채', introduction: "톡 쏘는 매운맛이 그리울 때, 겨자채의 알싸한 향을 즐겨보세요.조금 부드럽게 맛보고 싶다면, 여린 잎일 때 먹는 것도 추천합니다."},
])
const cropRecommends = ref([]);

async function fetchCropRecommends() {
  try {
    const response = await axios.get('https://j10b303.p.ssafy.io/recommendapi/crop/1');
    cropRecommends.value = response.data.recommended_crop;
    console.log(cropRecommends.value);
  } catch (error) {
    console.error('Error fetching recipe favorites:', error);
  }
}

onMounted(() => {
  fetchCropRecommends();
});


</script>

<style scoped>
.section #recommendation {
    justify-content: center;
    align-items: center;
    height: 100vh;
    /* width: 100vw; */
    position: absolute;
}

.recommendation-box {
    display: flex;
    margin-left: 10%;
    margin-top: 7%;
    width: 100vw;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-family: Noto Sans KR, sans-serif;
    
    
}

.recommendation-box #box-title {
    display: flex;
    margin-bottom: 3%;
    font-size: 36px;
    font-weight: 600;
}

.recommendation-box #box-title > p {
    margin-right: 10px;
}

.recommendation-box #box-title #nickname {
    color: #00B564;
    border-bottom: 3px solid #00B564;
}

.recommendation-button {
    color: #00B564;
    font-size: 24px;
    font-weight: 500;
    margin-left: auto;
    margin-right: 10%;
    width: fit-content;
}



.delay-animate {
    transition: opacity 0.5s ease-out;
    opacity: 0; /* 초기 상태, 보이지 않음 */
}

.aos-animate .recommendation-vegetable-list.delay-animate {
    opacity: 1;
    transition-delay: 1.1s; /* 첫 번째 딜레이 */
}

.aos-animate .recommendation-button.delay-animate {
    opacity: 1;
    transition-delay: 1.8s; /* 두 번째 딜레이 */
}

</style>