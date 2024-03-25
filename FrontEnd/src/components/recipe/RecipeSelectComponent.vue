<!-- //TODO 감자, 사과 등 이미지 안뜨는 이유, 스크롤, 뱃지  -->
<template>
  <div class="flex flex-row items-center justify-center mt-40">
    <!--왼쪽 이동 버튼-->
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

    <!-- 첫번째 질문 -->
    <div v-if="currentPage === 1" class="flex flex-col items-center justify-center" style="width: 1100px;">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/3)</span>
      <span class="mt-5 text-4xl font-bold text-center font-Notosans">요리에 사용할<br>농작물을 선택해주세요</span>
      <!-- <img alt="Survey1" class="mt-3 mb-3 w-80 h-80" src="../../assets/insideImage1.jpg"> -->

      <!-- 검색창 -->
      <div class="self-end m-5 w-96">
        <v-text-field v-model="searchText" label="농작물 이름을 입력해 주세요." variant="solo-filled"
          prepend-inner-icon="mdi-magnify"></v-text-field>
      </div>

      <!-- 선택된 재료 뱃지 -->
      <div v-for="selectedIngredient in isSelected.ingredients.value" :key="selectedIngredient" class="flex flex-row">
        <v-chip variant="outlined">
          {{ selectedIngredient }}
          <v-icon icon="mdi-close" @click="toggleIngredient(selectedIngredient)"></v-icon>
        </v-chip>
      </div>

      <!-- 재료 목록 -->
      <div class="grid grid-cols-5 gap-20 m-10">
        <div v-for="crop in filteredCropData" :key="crop.id" class="flex flex-col items-center m-1" @click="
      toggleIngredient(crop.name)">
          <div
            :class="isSelectedIngredient(crop.name) ? 'ring-2 ring-green-500 rounded-full p-0.5 relative' : 'ring-1 ring-gray-300 rounded-full p-0.5 relative'">
            <div style="position: relative;">
              <img :src="crop.thumbnailUrl" alt="crop.name" class="object-cover w-32 h-32 rounded-full" :class="
                  isSelectedIngredient(crop.name) ? 'opacity-50' : ''" />
              <v-icon v-if="isSelectedIngredient(crop.name)" icon="mdi-check" class="text-green-500"
                style="font-size: 50px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);"></v-icon>
            </div>
          </div>
          <div class="text-center ">{{ crop.name }}</div>
        </div>
      </div>
      <!-- 선택 시 좌측 상단에 선택된 농작물 목록 표시, X 버튼 클릭 시 선택한 농작물 취소 -->

    </div>

    <!-- 두번째 질문 -->
    <!-- 6개씩 가로 정렬, 사진이 원 내부에만 있도록 설정 -->
    <div v-if="currentPage === 2" class="flex flex-col items-center justify-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/3)</span>
      <span class="mt-5 text-4xl font-bold text-center font-Notosans">조리 방법을 선택해주세요</span>
      <img alt="Survey2" class="w-96 h-80" src="@/assets/insideImage2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '굽기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','굽기')">
        <img src="../../assets/fry.png">
        굽기
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '끓이기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','끓이기')">
        <img src="../../assets/boil.png">
        끓이기
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '데치기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','데치기')">
        <img src="../../assets/dachim.png">
        데치기
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '무침' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','무침')">
        <img src="../../assets/muchim.png">
        무침
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '볶음' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','볶음')">
        <img src="../../assets/boggeum.png">
        볶음
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '부침' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','부침')">
        <img src="../../assets/buchim.png">
        부침
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '비빔' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','비빔')">
        <img src="../../assets/bibim.png">
        비빔
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '삶기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','삶기')">
        <img src="../../assets/samgi.png">
        삶기
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '절임' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','절임')">
        <img src="../../assets/jullim.png">
        절임
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '조림' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','조림')">
        <img src="../../assets/jorim.png">
        조림
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '찜' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','찜')">
        <img src="../../assets/jjim.png">
        찜
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '튀김' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','튀김')">
        <img src="../../assets/fried.png">
        튀김
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '회' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','회')">
        <img src="../../assets/sushi.png">
        회
      </div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
        :class="isSelected['cookingmethod'].value === '기타' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
        @click="handleClick('cookingmethod','기타')">
        <img src="../../assets/cook.png">
        기타
      </div>


      <div class="py-10"></div>
    </div>

    <!-- 세번째 질문 -->
    <!-- 가로 정렬 -->
    <div v-if="currentPage === 3" class="flex flex-col items-center justify-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(3/3)</span>
      <span class="mt-5 text-4xl font-bold text-center font-Notosans">조리 난이도를 선택해주세요</span>

      <div class="flex flex-row mt-24" style="justify-content: space-evenly; margin-bottom: 3%;">
        <div class="mx-5 text-base font-medium text-center cursor-pointer font-Notosans"
          :class="isSelected['difficulty'].value === '아무나' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
          @click="handleClick('difficulty','아무나')">
          <img src="../../assets/everyone2.png">
          아무나
        </div>

        <div class="mx-5 text-base font-medium text-center cursor-pointer font-Notosans"
          :class="isSelected['difficulty'].value === '초급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
          @click="handleClick('difficulty','초급')">
          <img src="../../assets/beginner.png">
          초급
        </div>

        <div class="mx-5 text-base font-medium text-center cursor-pointer font-Notosans"
          :class="isSelected['difficulty'].value === '중급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
          @click="handleClick('difficulty','중급')">
          <img src="../../assets/intermediate.png">
          중급
        </div>

        <div class="mx-5 text-base font-medium text-center cursor-pointer font-Notosans"
          :class="isSelected['difficulty'].value === '고급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
          @click="handleClick('difficulty','고급')">
          <img src="../../assets/expert2.png">
          고급
        </div>

        <div class="mx-5 text-base font-medium text-center cursor-pointer font-Notosans"
          :class="isSelected['difficulty'].value === '신의경지' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
          @click="handleClick('difficulty','신의경지')">
          <img src="../../assets/god.png">
          신의경지
        </div>
      </div>


      <!-- <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['difficulty'].value === '아무나' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','아무나')" >
           <img src="../../assets/everyone2.png">
           아무나</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['difficulty'].value === '초급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','초급')" >
           <img src="../../assets/beginner.png">
           초급</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['difficulty'].value === '중급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','중급')" >
           <img src="../../assets/intermediate.png">
           중급</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['difficulty'].value === '고급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','고급')">
           <img src="../../assets/expert2.png">
           고급</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['difficulty'].value === '신의경지' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','신의경지')" >
           <img src="../../assets/god.png">
           신의경지</div> -->

      <div class="py-10"></div>
    </div>

    <!--    오른쪽 이동버튼-->
    <button @click="nextPage" v-if="currentPage < 3"
      class="absolute w-40 h-40 font-medium font-bold font-Notosans top-96 right-32"> 다음
      <v-icon icon="mdi-arrow-right"></v-icon>
    </button>
  </div>
  <!--    추천 받기 버튼-->
  <div>
    <button v-if="isAllSelected()&&currentPage === 3" @click="handleRecommendation"
      class="absolute flex items-center justify-center w-40 h-20 font-bold cursor-pointer font-Notosans text-l right-60"
      :class="isSelected['difficulty'].value != '0' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'">
      추천 받기
      <v-icon icon="mdi-arrow-right" start></v-icon>
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1,
    };
  },

  methods: {
    nextPage() {
      if (this.currentPage < 3) {
        this.currentPage++;
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
  },

};
</script>

<script setup>
import * as Crop from '@/js/Crop';
import { useSearchStore } from "@/stores/search";
import axios from "axios";
import { computed, onMounted, ref } from 'vue';

const searchStore = useSearchStore()

//재료 선택 관련
const searchText = ref(searchStore.searchBox)
const cropData = ref([])

const filteredCropData = computed(() => {
  if (!searchText.value) {
    return cropData.value
  }
  return cropData.value.filter(crop => crop.name.includes(searchText.value))
})

onMounted(() => {
  Crop.getCrops(
    (success) => {
      cropData.value = success.data
    },
    (error) => {
      console.log(error)
    }
  )
})

const isSelected = {
  'ingredients': ref([]),
  'cookingmethod': ref({ value: '0' }),
  'difficulty': ref({ value: '0' }),
};

// 모든 질문지에 대한 대답이 완료되었는지 체크하는 함수
const isAllSelected = () => {
  for (let key in isSelected) {
    if (key == "ingredients") {
      if (isSelected['ingredients'].value.length === 0) {
        return false
      }
    }
    else {
      if (isSelected[key].value.value === '0'){
        return false;
      } } 
  }
  return true;
};

//재료 선택 토글 함수
const toggleIngredient = (cropName) => {
  const index = isSelected['ingredients'].value.findIndex(ingredient => ingredient === cropName);
  if (index !== -1) {
    isSelected['ingredients'].value.splice(index, 1);
  } else {
    isSelected['ingredients'].value.push(cropName);
  }
  searchText.value = ''
  console.log(isSelected['ingredients'].value)
}

//해당 재료가 선택되었는지 확인하는 함수
const isSelectedIngredient = (cropName) => { 
  return isSelected['ingredients'].value.includes(cropName)
}

const handleClick = (index, value) => {
  isSelected[index].value =  value ;
  console.log(isSelected[index].value);
};

// 추천 요청을 보내는 함수
const handleRecommendation = async () => {
  try {
    // isSelected 객체의 값을 추출하여 API 요청에 필요한 데이터로 변환
    const requestData = {};
    for (let key in isSelected) {
      requestData[key] = isSelected[key].value;
    }
    console.log(requestData);
    // API 요청 보내기
    const response = await axios.post('/api/recommendation', requestData, {
      // 필요한 옵션 설정
    });

    // API 요청 성공 시 페이지 이동
    if (response.status === 200) {
      this.$router.push({
        path: '/recommendresult',
        state: { recommendation: response.data }
      });

    } else {
      console.error('API 요청 실패:', response.status);
      // 실패한 경우에 대한 처리 추가
    }
  } catch (error) {
    console.error('API 요청 중 오류 발생:', error);
    // 오류 발생 시에 대한 처리 추가
  }
}
</script>

<style scoped>
.cursor-pointer > img {
  height: 200px;
  width: 200px;
  overflow: hidden;
}
/* .cursor-pointer {
  align-content: center;
  height: 300px;
  width: 300px;
  border-radius: 70%;
    overflow: hidden;
}
.col > img {
  height: 300px;
  width: 300px;
  border-radius: 70%;
  overflow: hidden;
} */
</style>