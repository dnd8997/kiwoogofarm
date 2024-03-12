<template>
  <div>
    <button @click="prevPage">&lt; 이전 페이지</button>
    <button @click="nextPage">다음 페이지 &gt;</button>

    <div v-if="currentPage === 1">
      <h1>페이지 1의 내용</h1>
      <!-- 페이지 1의 내용 추가 -->
    </div>

    <div v-if="currentPage === 2">
      <h1>페이지 2의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>
    <div v-if="currentPage === 3">
      <h1>페이지 3의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>
    <div v-if="currentPage === 4">
      <h1>페이지 4의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>
    <div v-if="currentPage === 5">
      <h1>페이지 5의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>
    <div v-if="currentPage === 6">
      <h1>페이지 6의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>
    <div v-if="currentPage === 7">
      <h1>페이지 7의 내용</h1>
      <!-- 페이지 2의 내용 추가 -->
    </div>

    <!-- 나머지 페이지들도 동일한 패턴으로 추가 -->

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
      if (this.currentPage < 7) { // 전체 페이지 수에 맞게 수정
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