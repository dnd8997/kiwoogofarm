<template>
  <div class="mt-48 flex flex-row justify-center items-center">
    <button @click="prevPage" v-if="currentPage > 1" class="absolute w-40 h-40 left-96">&lt; </button>

      <component :is="currentPageComponent" class=""></component>

    <button @click="nextPage" v-if="currentPage < 7" class="absolute w-40 h-40 right-96"> &gt;</button>
  </div>
</template>

<script>
import SurveySection1 from "@/components/recommendation/sections/SurveySection1.vue";
import SurveySection2 from "@/components/recommendation/sections/SurveySection2.vue";

export default {
  data() {
    return {
      currentPage: 1,
    };
  },
  computed: {
    currentPageComponent() {
      switch (this.currentPage) {
        case 1:
          return SurveySection1;
        case 2:
          return SurveySection2;
        default:
          return null;
      }
    },
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

