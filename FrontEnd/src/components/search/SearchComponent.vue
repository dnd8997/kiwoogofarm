<template>
    <div class="section">
      <p class="section-title">어떤 농작물을 찾으세요?</p>

      <div>
         <div style="display: flex; align-items: center;">

           <v-btn
             variant="outlined"
             style="margin-top: 5%;"
             class="mx-2"
             color="medium-emphasi"
             rounded
             v-for="(search, i) in searchCheckBox"
             :key="i"
             :active="search.isActivate"
             @click="checkBox(i)"
           >
             {{ search.name }}
           </v-btn>
           <v-icon 
            v-if="showIcon(searchCheckBox)"
            icon="mdi-close" 
            style="margin-top: 5%; cursor: pointer;"
            @click="resetCheckBox(searchCheckBox)"
          >
          </v-icon>
         </div>

        <p style="border-bottom: solid 1px black; margin-block: 2%;"></p>
        
        <div>
          <v-btn
            variant="outlined"
            class="mx-2"
            color="medium-emphasi"
            rounded
            v-for="(search1, i) in searchCheckBox2"
            :key="i"
            :active="search1.isActivate"
            @click="checkBox2(i)"
          >
            {{ search1.name }}
          </v-btn>
          <v-icon 
            v-if="showIcon(searchCheckBox2)"
            icon="mdi-close" 
            style="cursor: pointer;"
            @click="resetCheckBox(searchCheckBox2)"
          >
          </v-icon>
        </div>
        <div style="margin-top: 3%; border-bottom: solid 1px black;">
          <v-text-field
            v-model="searchText"
            variant="plain"
            style="height: 60px;"
            prepend-inner-icon="mdi-magnify"
            label="농작물 이름을 입력해 주세요."
            single-line
          >
  
          </v-text-field>
        </div>
        <div>
          <v-row>
            <v-col
              style="text-align: center;"
              cols="3"
              v-for="(crop, i) in filteredCropData"
              :key="i"
            >
              <div>
                {{ crop.thumbnailUrl }}
              </div>
              <div>
                {{ crop.name }}
              </div>
            </v-col>
          </v-row>
        </div>
      </div>
    </div>
</template>




<script setup>
import { onMounted, ref, watch, computed } from "vue"
import { useRoute } from "vue-router"
import { useSearchStore } from "@/stores/search"
import * as Crop from '@/js/Crop';

const route = useRoute()
const searchStore = useSearchStore()

const searchText = ref(searchStore.searchBox)
const cropData = ref([])

const filteredCropData = computed(() => {
  if (!searchText.value) {
    return cropData.value
  }
  return cropData.value.filter(crop => crop.name.includes(searchText.value))
})

const searchCheckBox = ref([
  { name: '채소류', isActivate: false }, 
  { name: '버섯류', isActivate: false }, 
  { name: '과수류', isActivate: false }, 
  { name: '특용작물', isActivate: false }, 
  { name: '식용작물', isActivate: false }, 
  { name: '사료작물', isActivate: false }, 
])

const searchCheckBox2 = ref([
  { name: '초보자용', isActivate: false },
  { name: '빛이 적어도 되는', isActivate: false },
  { name: '흙이 필요없는', isActivate: false },
  { name: '물주기가 긴', isActivate: false },
])

const checkBox = (i) => {
  searchCheckBox.value[i].isActivate = !searchCheckBox.value[i].isActivate
}

const checkBox2 = (i) => {
  searchCheckBox2.value[i].isActivate = !searchCheckBox2.value[i].isActivate
}

const resetCheckBox = (box) => {
  box.forEach(item => item.isActivate = false)
}

const showIcon = (box) => {
  if (box) {
    return box.some((item) => item.isActivate)
  }
  return false
}

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

watch(() => searchCheckBox.value.map(item => item.isActivate), (newVal, oldVal) => {
  console.log('searchCheckBox 변화 감지', newVal);
}, { deep: true });

watch(() => searchCheckBox2.value.map(item => item.isActivate), (newVal, oldVal) => {
  console.log('searchCheckBox2 변화 감지', newVal);
}, { deep: true });

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

.section-title {
  font-weight: 500;
  font-size: 40px;
  width: 150px;
}

button {
  font-weight: 400;
  
}

</style>