<template>
    <div
        v-if="checkLogin"
        @click="logout"
    >
        logout
        {{ authStore.nickName }}
    </div>
    <div
        v-else
    >
        <v-btn
            @click="openLoginModal"
        >
            LOGIN
        </v-btn>


        <LoginModal 
            :logins="logins"
            @update:logins="logins = $event"
        />
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useAuthStore } from '@/stores/auth';
import { useRouter } from 'vue-router';
import LoginModal from '@/components/modal/LoginModal.vue'
const authStore = useAuthStore()
const router = useRouter()

const logins = ref(false)
const checkLogin = ref(false)

const logout = () => {
    authStore.clearToken()
    router.go(0)
}

const openLoginModal = () => {
    logins.value = !logins.value
}

const checkLoginHandler = () => {
    if ( localStorage.getItem('accessToken')) {
        checkLogin.value = true
    }
}

onMounted(() => {
    checkLoginHandler()
    console.log(checkLogin.value)
})

</script>

<style scoped>

</style>