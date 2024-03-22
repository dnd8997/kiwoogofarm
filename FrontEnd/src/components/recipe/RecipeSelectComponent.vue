<template>
  <div class="mt-40 flex flex-row justify-center items-center">
<!--왼쪽 이동 버튼-->
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

    <!-- 첫번째 질문 -->
    <div  v-if="currentPage === 1" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/3)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">요리에 사용할<br>농작물을 선택해주세요</span>
      <img alt="Survey1" class="w-80 h-80 mt-3 mb-3" src="../../assets/insideImage1.jpg">

      <!-- 재료 목록에서 이미지, 재료 이름을 불러와야함 -->
      <!-- v-for 사용? -->
      <!-- 5개씩 가로 정렬, 아래 스크롤 시 농작물 목록 표시 -->
      <!-- 선택 시 좌측 상단에 선택된 농작물 목록 표시, X 버튼 클릭 시 선택한 농작물 취소 -->
      <!-- 우측 상단 검색바에서 농작물 이름 검색 -->
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base !important"
           :class="isSelected['ingredients'].value === '쉬움' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('ingredients','쉬움')" >아니요, 처음입니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['ingredients'].value === '보통' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('ingredients','보통')" >많이는 아니지만, 소소하게 키워봤습니다.</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['ingredients'].value === '어려움' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('ingredients','어려움')">꽤 많이 키워봤습니다.</div>

      <div class="py-10"></div>
    </div>

    <!-- 두번째 질문 -->
    <!-- 6개씩 가로 정렬, 사진이 원 내부에만 있도록 설정 -->
    <div v-if="currentPage === 2" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/3)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">조리 방법을 선택해주세요</span>
      <img alt="Survey2" class="w-96 h-80" src="@/assets/insideImage2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '굽기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','굽기')" >
           <img src="../../assets/fry.png">
           굽기</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '끓이기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','끓이기')" >
           <img src="../../assets/boil.png">
           끓이기</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '데치기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','데치기')">
           <img src="../../assets/dachim.png">
           데치기</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '무침' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','무침')">
           <img src="../../assets/muchim.png">
           무침</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '볶음' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','볶음')">
           <img src="../../assets/boggeum.png">
           볶음</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '부침' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','부침')">
           <img src="../../assets/buchim.png">
           부침</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '비빔' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','비빔')">
           <img src="../../assets/bibim.png">
           비빔</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '삶기' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','삶기')">
           <img src="../../assets/samgi.png">
           삶기</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '절임' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','절임')">
           <img src="../../assets/jullim.png">
           절임</div>
            <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '조림' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','조림')">
           <img src="../../assets/jorim.png">
           조림</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '찜' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','찜')">
           <img src="../../assets/jjim.png">
           찜</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '튀김' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','튀김')">
           <img src="../../assets/fried.png">
           튀김</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '회' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','회')">
           <img src="../../assets/sushi.png">
           회</div>
      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  font-Notosans font-medium text-base"
           :class="isSelected['cookingmethod'].value === '기타' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('cookingmethod','기타')">
           <img src="../../assets/cook.png">
           기타</div>
      

      <div class="py-10"></div>
    </div>

    <!-- 세번째 질문 -->
    <!-- 가로 정렬 -->
    <div v-if="currentPage === 3" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(3/3)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">조리 난이도를 선택해주세요</span>

      <div class="flex flex-row mt-24" style="justify-content: space-evenly; margin-bottom: 3%;">
        <div class="cursor-pointer text-center font-Notosans font-medium text-base mx-5"
           :class="isSelected['difficulty'].value === '아무나' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','아무나')" >
           <img src="../../assets/everyone2.png">
           아무나</div>

      <div class="cursor-pointer text-center font-Notosans font-medium text-base mx-5"
           :class="isSelected['difficulty'].value === '초급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','초급')" >
           <img src="../../assets/beginner.png">
           초급</div>

      <div class="cursor-pointer text-center font-Notosans font-medium text-base mx-5"
           :class="isSelected['difficulty'].value === '중급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','중급')" >
           <img src="../../assets/intermediate.png">
           중급</div>

      <div class="cursor-pointer text-center font-Notosans font-medium text-base mx-5"
           :class="isSelected['difficulty'].value === '고급' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','고급')">
           <img src="../../assets/expert2.png">
           고급</div>

      <div class="cursor-pointer text-center font-Notosans font-medium text-base mx-5"
           :class="isSelected['difficulty'].value === '신의경지' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick('difficulty','신의경지')" >
           <img src="../../assets/god.png">
           신의경지</div>
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
    <button @click="nextPage" v-if="currentPage < 3" class="absolute w-40 h-40 right-96"> 다음 &gt;</button>
  </div>
  <!--    추천 받기 버튼-->
  <div>
    <button v-if="isAllSelected()&&currentPage === 3" @click="handleRecommendation" 
    class="font-Notosans font-bold text-l absolute w-40 h-20 right-60 cursor-pointer flex items-center justify-center"
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
import { ref  } from 'vue';
import axios from "axios";

const isSelected = {
  'ingredients': ref({ value: '0' }),
  'cookingmethod': ref({ value: '0' }),
  'difficulty': ref({ value: '0' }),
};

// 모든 질문지에 대한 대답이 완료되었는지 체크하는 함수
const isAllSelected = () => {
  for (let key in isSelected) {
    if (isSelected[key].value.value === '0') {
      return false;
    }
  }
  return Object.values(isSelected).every(item => item.value.value !== '0');
};


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