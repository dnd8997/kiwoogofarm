<template>
  <div class="h-full w-full flex flex-col items-center font-Notosans" style="margin-top: 10%;">
    <v-avatar size="80">
      <v-img
          alt="John"
          src="https://cdn.vuetifyjs.com/images/john.jpg"
      ></v-img>
    </v-avatar>
    <div class="font-medium text-2xl mt-3">연우</div>
    <v-card class="h-full w-full mt-5">
      <v-tabs
          v-model="tab"
          align-tabs="center"
          color="#00B564"
          @change="handleChangeTab"
      >
        <v-tab :value="1">찜한 농작물</v-tab>
        <v-tab :value="2">찜한 레시피</v-tab>
        <v-tab :value="3">찜한 텃밭</v-tab>
      </v-tabs>
      <v-window v-model="tab">
        <v-window-item
            v-for="n in 3"
            :key="n"
            :value="n"
        >
          <v-container fluid class="h-full ">
            <v-row class="h-full flex items-center justify-center mt-5" >
              <v-col
                  v-for="(item, index) in items[n]"
                  :key="index"
                  cols="3"
              >
                <div class="flex flex-col items-center my-4">
                  <v-avatar size="180">
                    <v-img :src="item.image" alt="" class="object-cover"></v-img>
                  </v-avatar>
                  <div class="mt-3 text-lg">{{ item.name }}</div>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-window-item>
      </v-window>
    </v-card>
  </div>
</template>

<script setup>
import { ref,onMounted  } from 'vue';
import axios from "axios";

const tab = ref(1);


const recipeFavorites = ref([]);
const cropFavorites = ref([]);


const itemsMap1 = [
  { name: '완두콩', image: 'https://blog.kakaocdn.net/dn/0YuZx/btr0ilo7qUJ/LAVLvegbIKcGsxZkeSttz1/img.jpg' },
  { name: '옥수수', image: 'https://dimg.donga.com/wps/NEWS/IMAGE/2021/07/06/107793926.5.jpg' },
  { name: '토마토', image: 'https://lh3.googleusercontent.com/proxy/F7Q_AMMuhPfFmrdr7eQbeRLMuHB0igJZ4JNen7MdiKNBN20a_kfKoNWm445_hcxpPvNMoJa9YwvH1IuNCxrO8tsbvHie9o4Cmyk5693GZ6hWYCWg4wGqm8KJ3m4' },
];
const itemsMap2 = [
  { name: '디저트', image: 'https://lh5.googleusercontent.com/proxy/gOlu3d7Gw7g56kMaOAFhn9SRdP5VfXvc-tbI81byHtvcTFj-QKNHomEJKC76VEBksrCb0SO1d3L-zHskOlHkLb2-eFhee2SdmuWw' },
  { name: '떡볶이', image: 'https://img.freepik.com/free-photo/cheesy-tokbokki-korean-traditional-food-on-black-board-background-lunch-dish_1150-42986.jpg' },
  { name: '비빔밥', image: 'https://i.namu.wiki/i/b2U9ZGSKF76RyLb-E_jdaH9vlhgWqSohlyJlHD_J7eEllHhoO5C9OtQPwSOnEnyudRBn0XUHpS10SEnyZLUbUg.webp' },
  { name: '장어덮밥', image: 'https://d20aeo683mqd6t.cloudfront.net/ko/articles/title_images/000/039/267/medium/pixta_32269376_M.jpg?2019' }
];
const itemsMap3 = [
  { name: '콩', image: 'https://blog.kakaocdn.net/dn/0YuZx/btr0ilo7qUJ/LAVLvegbIKcGsxZkeSttz1/img.jpg' },
  { name: '수', image: 'https://dimg.donga.com/wps/NEWS/IMAGE/2021/07/06/107793926.5.jpg' },
  { name: '토', image: 'https://lh3.googleusercontent.com/proxy/F7Q_AMMuhPfFmrdr7eQbeRLMuHB0igJZ4JNen7MdiKNBN20a_kfKoNWm445_hcxpPvNMoJa9YwvH1IuNCxrO8tsbvHie9o4Cmyk5693GZ6hWYCWg4wGqm8KJ3m4' },
];

const items = ref({1: itemsMap1, 2: itemsMap2, 3: recipeFavorites});
// 찜한 농작물 조회 API
async function fetchCropFavorites() {
  try {
    const response = await axios.get('https://j10b303.p.ssafy.io/api/crop/myfavorites');
    cropFavorites.value = response.data;
  } catch (error) {
    console.error('Error fetching recipe favorites:', error);
  }
}
// 찜한 레시피 조회 API
async function fetchRecipeFavorites() {
  try {
    const response = await axios.get('https://j10b303.p.ssafy.io/api/api/recipe/myFavorites/jungyoanwoo@naver.com');
    recipeFavorites.value = response.data;
  } catch (error) {
    console.error('Error fetching recipe favorites:', error);
  }
}

onMounted(() => {
  // fetchCropFavorites();
  fetchRecipeFavorites();
});

function handleChangeTab(newTab) {
  tab.value = newTab;
}
</script>

