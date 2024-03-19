<template>
  <div class="mt-40 flex flex-row justify-center items-center">
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

    <!--    첫번째 질문-->
    <div  v-if="currentPage === 1" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(1/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">농작물을 키워본<br> 경험이 있나요?</span>
      <img alt="Survey1" class="w-96 h-80 mt-3 ml-6" src="../../assets/outsideImage1.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,1)" >아니요, 처음입니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,2)" >많이는 아니지만, 소소하게 키워봤습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[1].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(1,3)">꽤 많이 키워봤습니다</div>

      <div class="py-10"></div>
    </div>

    <!--    두번째 질문-->
    <div v-if="currentPage === 2" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(2/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">어느 계절에 <br> 재배하실 계획이신가요?</span>
      <img alt="Survey2" class="w-96 h-80 mt-5 mb-3" src="@/assets/outsideImage2.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,1)" >봄</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,2)" >여름</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,3)">가을</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[2].value === 4 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(2,4)">겨울</div>

      <div class="py-10"></div>
    </div>

    <!--    세번째 질문-->
    <div v-if="currentPage === 3" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(3/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">어느 정도의 재배 기간을<br>고려하고 계신가요?</span>
      <img alt="Survey3" class="w-96 h-72 mt-5" src="@/assets/outsideImage3.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,1)" >단기 재배(1~3개월)</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,2)" >중기 재배(3~6개월)</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[3].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(3,3)">장기 재배(6개월 이상)</div>

      <div class="py-10"></div>
    </div>

    <!--    네번째 질문-->
    <div v-if="currentPage === 4" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(4/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">당신의 텃밭은<br>하루에 얼마나 햇빛을 받나요?</span>
      <img alt="Survey4" class="w-80 h-72 mt-8" src="@/assets/outsideImage4.jpg">

      <div class="cursor-pointer mt-8 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,1)" >6시간 이상 직접적인 햇빛을 받습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,2)" >4~6시간 정도 햇빛을 받습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[4].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(4,3)">4시간 미만의 햇빛을 받습니다</div>

      <div class="py-10"></div>
    </div>

    <!--    다섯번째 질문-->
    <div v-if="currentPage === 5" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(5/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">일주일에 몇 번 <br>물을 주실수 있으신가요?</span>
      <img alt="Survey5" class="w-80 h-80 mt-3" src="../../assets/outsideImage5.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,1)" >매일 줄 수 있습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,2)" >2~3일에 한 번 줄 수 있습니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[5].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(5,3)">주 1회정도만 가능합니다</div>

      <div class="py-10"></div>
    </div>

    <!--    여섯번째 질문-->
    <div v-if="currentPage === 6" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(6/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">토양의 물빠짐 정도는<br>어느 정도인가요?</span>
      <img alt="Survey6" class="w-80 h-72 mt-6" src="@/assets/outsideImage6.jpg">

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,1)" >물빠짐이 빠릅니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,2)" >보통의 물빠짐을 가집니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,3)">물빠짐이 느립니다</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[6].value === 4 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(6,4)">잘 모르겠습니다</div>


      <div class="py-10"></div>
    </div>

    <!--    일곱번째 질문-->
    <div v-if="currentPage === 7" class="flex flex-col justify-center items-center">
      <span class="font-bold font-Notosans text-xl text-[#00B564]">(7/7)</span>
      <span class="mt-5 font-bold font-Notosans text-4xl text-center">수경재배를 활용하여<br>키우실 생각이신가요?</span>
      <img alt="Survey7" class="w-72 h-72 mt-6" src="@/assets/outsideImage7.jpg">

      <div class="cursor-pointer mt-6 rounded-full w-[400px] text-center py-5  text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === 1 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,1)" >네</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === 2 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,2)" >아니요</div>

      <div class="cursor-pointer mt-5 rounded-full w-[400px] text-center py-5 text-[#444444] font-Notosans font-medium text-base"
           :class="isSelected[7].value === 3 ? 'bg-[#00B562] text-white' : 'bg-[#F6F6F3] text-[#444444]'"
           @click="handleClick(7,3)">잘 모르겠습니다</div>

      <div class="py-10"></div>
    </div>

<!--오른쪽 이동버튼-->
    <button @click="nextPage" v-if="currentPage < 7" class="absolute w-40 h-40 right-96"> &gt;</button>

<!--추천 버튼-->
    <router-link v-if="isAllSelected()" to="/recommendresult">
      <div v-if="currentPage === 7" class="font-Notosans font-bold text-xl  absolute w-40 h-40 right-80 cursor-pointer flex items-center justify-center" >
      추천 받기
        <v-icon
            icon="mdi-arrow-right"
            start
        ></v-icon>
      </div>
    </router-link>
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
</script>

