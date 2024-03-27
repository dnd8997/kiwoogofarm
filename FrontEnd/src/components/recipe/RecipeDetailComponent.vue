<template>
    <div class="section">
        <!-- //TODO 이미지 높이 설정 -->
        <img referrerpolicy="no-referrer" :src="recipeData.image" class="w-1/4">
        <div class="">{{ recipeData.name }}</div>
        <div>{{ recipeData.info }}</div>
        난이도
        <div>{{ recipeData.difficulty }}</div>
        조리 방법
        <div> {{ recipeData.cook }}</div>
        재료
        <div>{{ recipeData.ingredients }}</div>



        조리 순서
        <div v-for="step in recipeData.recipeDetailList" :key="step.id">
            <div>{{ step.recipe_order }}</div>
            <img :src="step.image_url" alt="Recipe Image" />
            <div>{{ step.info }}</div>
        </div>

        좋아요 수
        <div>{{ recipeData.likes }}</div>
        <div class="mt-2 ml-2">
            <v-icon v-if="heartCheck" @click="checkcheck(recipeData.id)" style="cursor: pointer; color: #FF4081;" icon="mdi-heart">
            </v-icon>

            <v-icon v-else @click="checkcheck" style="cursor: pointer; color: #FF4081;" icon="mdi-heart-outline">
            </v-icon>
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
const token = jwtDecode(localStorage.getItem('accessToken'))
const email = token.email
const recipeId = route.params.recipeId;
console.log(email)

const goBack = () => {
    router.back();
}

const heartCheck = ref(false)

function checkcheck() {
    Recipe.likeRecipe(recipeId, email, (success) => {
        console.log("좋아요설정/해제 성공")
    },
        (error) => {
            console.error(error)
        })
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
    width: 100%;
    margin-top: 15%;
    font-family: 'Noto Sans KR', sans-serif;
}
</style>