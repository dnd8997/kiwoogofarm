<template>
    <div class="section">
        <div>
            <v-icon icon="mdi-chevron-left" style="font-size: 50px; cursor: pointer;" @click="goBack">
            </v-icon>
        </div>
        <div class="section-1" style="margin-left: 10%; margin-top: 2%;">
            <div>
                <div style="width: 360px; height: 300px;">

                    <img referrerpolicy="no-referrer" :src="recipeData.image"
                        style="width: 100%; height: 100%; object-fit: cover;">
                </div>
            </div>
            <div class="crop" style="width: 50%;">
                <div class="flex flex-row">
                    <div class="crop-title">
                        {{ recipeData.name }}
                    </div>
                    <div class="mt-2 ml-2">
                        <v-icon v-if="heartCheck" @click="checkcheck" style="cursor: pointer; color: #FF4081;"
                            icon="mdi-heart">
                        </v-icon>

                        <v-icon v-else @click="checkcheck" style="cursor: pointer; color: #FF4081;"
                            icon="mdi-heart-outline">
                        </v-icon>
                    </div>
                </div>
                <div class="whitespace-pre-wrap mt-6 text-[#4F4F4F] ">
                    <div>
                        {{ recipeData.info }}
                    </div>
                </div>

                <div class="window-frame">
                    <div class="quadrant top-left">
                        <div>
                            <v-icon icon="mdi-watering-can-outline"
                                style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            난이도
                        </div>
                        <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">
                            {{ recipeData.difficulty }}
                        </div>
                    </div>
                    <div class="quadrant top-right">
                        <div>
                            <v-icon icon="mdi-white-balance-sunny"
                                style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            요리 방법
                        </div>
                        <div class="whitespace-pre-wrap  text-[#4F4F4F] text-sm">
                            {{ recipeData.cook }}
                        </div>
                    </div>
                    <div class="quadrant2 bottom-left">
                        <div>
                            <v-icon icon="mdi-water" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                        </div>
                        <div class="frame-title">
                            재료
                        </div>
                        <div class="whitespace-pre-wrap  text-[#4F4F4F] text-sm">
                            <!-- {{ recipeData.ingredients }} -->
                            이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것,
                            이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것,
                            이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것,
                            이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것, 이것저것,이것저것,
                            이것저것
                        </div>
                    </div>
                </div>
                <div style="margin-top: 20%;">
                    H!@
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import * as Recipe from '@/js/Recipe';
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter()
const recipeData = ref([])

const heartCheck = ref(false)

const goBack = () => {
  router.back();
}

function checkcheck () {
    heartCheck.value = !heartCheck.value
}

onMounted(() => {
    Recipe.getRecipeById(
        route.params.recipeId,
        (success) => {
            recipeData.value = success.data
            console.log("여기",recipeData.value)
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
    width: 100%;
    height: 100vh;
    margin-top: 15%;
    white-space: nowrap;
    flex-direction: column;
    font-family: 'Noto Sans KR', sans-serif;
}

.section-1 {
    display: inline-flex;
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
    height: 600px;
    border-block: 1px solid #E0E0E0;
}

.window-frame::before,
.window-frame::after {
    content: '';
    position: absolute;
    background: #E0E0E0;
}

.window-frame::before {
    /* 세로선 */
    left: 50%;
    width: 1px;
    height: 50%;
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

.quadrant2 {
    position: absolute;
    width: 100%;
    /* height: 50%; */
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
    bottom: 20%;
    left: 3%;
    padding-right: 5%;
}

/* .bottom-right {
    bottom: -1%;
    right: -3%;
    padding-right: 5%;
} */

.frame-title {
    font-weight: bold;
    margin-bottom: 5px;
}
</style>