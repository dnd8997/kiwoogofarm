<template>
  <div class="mt-40 flex flex-row justify-center items-center">
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

    <!--    첫번째 질문-->
    <div  v-if="currentPage === 1" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br> 경험이 있나요?</span>
      <img alt="Survey1" class="w-96 h-80 mt-3 ml-6" src="../../assets/outsideImage1.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === '쉬움' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,'쉬움')" >아니요, 처음입니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === '보통' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,'보통')" >많이는 아니지만, 소소하게 키워봤습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === '어려움' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,'어려움')">꽤 많이 키워봤습니다</div>

      <div class="py-10"></div>
    </div>

    <!--    두번째 질문-->
    <div v-if="currentPage === 2" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">어느 계절에 <br> 재배하실 계획이신가요?</span>
      <img alt="Survey2" class="w-96 h-80 mt-5 mb-3" src="@/assets/outsideImage2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === '봄' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,'봄')" >봄</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === '여름' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,'여름')" >여름</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === '가을' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,'가을')">가을</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === '겨울' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,'겨울')">겨울</div>

      <div class="py-10"></div>
    </div>

    <!--    세번째 질문-->
    <div v-if="currentPage === 3" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(3/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">어느 정도의 재배 기간을<br>고려하고 계신가요?</span>
      <img alt="Survey3" class="w-96 h-72 mt-5" src="@/assets/outsideImage3.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === '하' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,'하')" >단기 재배(1~3개월)</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === '중' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,'중')" >중기 재배(3~6개월)</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === '상' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,'상')">장기 재배(6개월 이상)</div>

      <div class="py-10"></div>
    </div>

    <!--    네번째 질문-->
    <div v-if="currentPage === 4" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(4/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">당신의 텃밭은<br>하루에 얼마나 햇빛을 받나요?</span>
      <img alt="Survey4" class="w-80 h-72 mt-8" src="@/assets/outsideImage4.jpg">

      <div class="cursor-pointer mt-8 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === '상' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,'상')" >6시간 이상 직접적인 햇빛을 받습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === '중' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,'중')" >4~6시간 정도 햇빛을 받습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === '하' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,'하')">4시간 미만의 햇빛을 받습니다</div>

      <div class="py-10"></div>
    </div>

    <!--    다섯번째 질문-->
    <div v-if="currentPage === 5" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(5/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">일주일에 몇 번 <br>물을 주실수 있으신가요?</span>
      <img alt="Survey5" class="w-80 h-80 mt-3" src="../../assets/outsideImage5.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === '하' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,'하')" >매일 줄 수 있습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === '중' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,'중')" >2~3일에 한 번 줄 수 있습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === '상' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,'상')">주 1회정도만 가능합니다</div>

      <div class="py-10"></div>
    </div>

    <!--    여섯번째 질문-->
    <div v-if="currentPage === 6" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(6/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">토양의 물빠짐 정도는<br>어느 정도인가요?</span>
      <img alt="Survey6" class="w-80 h-72 mt-6" src="@/assets/outsideImage6.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === '상' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,'상')" >물빠짐이 빠릅니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === '중' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,'중')" >보통의 물빠짐을 가집니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === '하' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,'하')">물빠짐이 느립니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === '?' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,'?')">잘 모르겠습니다</div>


      <div class="py-10"></div>
    </div>

    <!--    일곱번째 질문-->
    <div v-if="currentPage === 7" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(7/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">수경재배를 활용하여<br>키우실 생각이신가요?</span>
      <img alt="Survey7" class="w-72 h-72 mt-6" src="@/assets/outsideImage7.jpg">

      <div class="cursor-pointer mt-6 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === 'TRUE' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,'TRUE')" >네</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === 'FALSE' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,'FALSE')" >아니요</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === '?' ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,'?')">잘 모르겠습니다</div>

      <div class="py-10"></div>
    </div>

<!--오른쪽 이동버튼-->
    <button @click="nextPage" v-if="currentPage < 7" class="absolute w-40 h-40 right-96"> &gt;</button>

<!--추천 버튼-->
    <div>
      <button v-if="isAllSelected()&&currentPage === 7" @click="handleRecommendation" class="font-Notosans font-bold text-xl absolute w-40 h-40 right-80 cursor-pointer flex items-center justify-center">
        추천 받기
        <v-icon icon="mdi-arrow-right" start></v-icon>
      </button>
    </div>
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
      if (this.currentPage < 7) {
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
  1: ref({ value: 0 }),
  2: ref({ value: 0 }),
  3: ref({ value: 0 }),
  4: ref({ value: 0 }),
  5: ref({ value: 0 }),
  6: ref({ value: 0 }),
  7: ref({ value: 0 }),
};

// 모든 질문지에 대한 대답이 완료되었는지 체크하는 함수
const isAllSelected = () => {
  for (let key in isSelected) {
    if (isSelected[key].value.value === 0) {
      return false;
    }
  }
  return Object.values(isSelected).every(item => item.value !== 0);
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
    const response = await axios.post('https://j10b303.p.ssafy.io/api/recommendation', requestData, {
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

