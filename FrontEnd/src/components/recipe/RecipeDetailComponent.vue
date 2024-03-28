<template>
    <div class="section">
        <div>
            <v-icon icon="mdi-chevron-left" style="font-size: 50px; cursor: pointer;" @click="goBack">
            </v-icon>
        </div>
        <!-- 조리순서 제외한 정보 -->
        <div class="flex flex-row w-full" style="margin-left: 10%; margin-top: 2%;">
            <!-- //TODO 이미지 높이 설정 -->
            <img referrerpolicy="no-referrer" :src="recipeData.image" style="width: 360px; height: 300px;">
            <!-- 이미지 제외한 정보 -->
            <div class="ml-[8%] w-full">
                <div class="flex flex-row justify-between w-full">
                    <div class="font-extrabold text-[28px]">{{ recipeData.name }}</div>
                    <div class="flex flex-col items-center">
                        <div>
                            <v-icon v-if="heartCheck" @click="checkcheck()" style="cursor: pointer; color: #FF4081;"
                                icon="mdi-heart">
                            </v-icon>
                            <v-icon v-else @click="checkcheck()" style="cursor: pointer; color: #FF4081;"
                                icon="mdi-heart-outline">
                            </v-icon>
                        </div>
                        <div class="text-center text-[14px]">{{ recipeData.likes }}</div>
                    </div>
                </div>
                <div class="whitespace-pre-wrap mt-6 text-[#4F4F4F] ">{{ recipeData.info }}</div>

                <div class="window-frame">
                    <!-- 재료 제외한 정보 -->
                    <div>
                        <div class="quadrant top-left">
                            <v-icon icon=" mdi-creation-outline"
                                style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                            <div class="frame-title">난이도</div>
                            <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">{{ recipeData.difficulty }}</div>
                        </div>
                        <div class="quadrant top-right">
                            <v-icon icon="mdi-clock-time-four-outline"
                                style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                            <div class="frame-title">소요 시간</div>
                            <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">
                                30분(임의)
                                <!-- {{ recipeData.cookTime }} -->
                            </div>
                        </div>

                        <div class="quadrant bottom-left">
                            <v-icon icon="mdi-gas-burner" style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                            <div class="frame-title">
                                조리 방법
                            </div>
                            <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">
                                {{ recipeData.cook }}
                            </div>
                        </div>
                        <div class="quadrant bottom-right">
                            <v-icon icon="mdi-account-multiple"
                                style="font-size: 30px; margin-bottom: 3px; margin-top: 3px">
                            </v-icon>
                            <div class="frame-title">분량</div>
                            <div class="whitespace-pre-wrap text-[#4F4F4F] text-sm ">
                                1인분(임의)
                                <!-- {{ recipeData.servings }} -->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="w-full mt-3 ml-5">
                    <div class="frame-title">
                        재료
                    </div>
                    <div class="flex flex-row flex-wrap justify-items-start " style="white-space: wrap;">
                        <div v-for="(ingredient, index) in ingredients" :key="index">
                            <div class="mr-3 w-fit">{{ ingredient }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 조리 순서 -->
        <div v-for="step in recipeData.recipeDetailList" :key="step.id">
            <div>{{ step.recipe_order }}</div>
            <img :src="step.image_url" alt="Recipe Image" />
            <div>{{ step.info }}</div>
        </div>
    </div>
</template>

<script setup>
import * as Recipe from '@/js/Recipe';
import { jwtDecode } from 'jwt-decode';
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const route = useRoute()
const router = useRouter()
const recipeData = ref([])
const ingredients = ref([]);
const token = jwtDecode(localStorage.getItem('accessToken'))
const email = token.email
const recipeId = route.params.recipeId;
console.log(email)

const goBack = () => {
    router.back();
}

const heartCheck = ref(false)

function checkcheck() {
    Recipe.likeRecipe(recipeId, email,
        (success) => {
            console.log("좋아요 설정/해제 성공")
            heartCheck.value = !heartCheck.value
            Recipe.getRecipeById(
                recipeId,
                (success) => {
                    recipeData.value = success.data;
                },
                (error) => {
                    console.error(error);
                }
            );
        },
        (error) => {
            console.error(error)
        })
}


onMounted(() => {
    Recipe.getRecipeById(
        route.params.recipeId,
        (success) => {
            recipeData.value = success.data
            console.log(recipeData.value.ingredients?.replace(/\[([^\]]+)\]/g, '|'))
            ingredients.value = recipeData.value.ingredients?.replace(/\[([^\]]+)\]/g, '|').split("|")
            console.log("레시피 정보", recipeData.value)
        },
        (error) => {
            console.error(error)
        }
    )
    Recipe.isRecipeLiked(recipeId, email,
        (success) => {
            heartCheck.value = success.data
            console.log("좋아요 여부", heartCheck.value)
        },
        (error) => {
            console.error(error)
        })
})
</script>

<style scoped>
.section {
    display: flex;
    justify-content: left;
    height: 100vh;
    width: 1168px;
    margin-top: 15%;
    white-space: nowrap;
    flex-direction: column;
    font-family: 'Noto Sans KR', sans-serif;
}

.window-frame {
    position: relative;
    margin-top: 5%;
    width: 100%;
    height: 230px;
    border-block: 1px solid #E0E0E0;
}

.frame-title {
    font-weight: bold;
    margin-bottom: 5px;
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
    height: 100%;
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
    bottom: -1%;
    left: 3%;
    padding-right: 5%;
}

.bottom-right {
    bottom: -1%;
    right: -3%;
    padding-right: 5%;
}
</style>