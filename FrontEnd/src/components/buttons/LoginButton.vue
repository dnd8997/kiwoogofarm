<template>
    <div
        v-if="checkLogin"
        
    >
        <!-- logout -->
        <v-menu
            v-model="menu"
            transition="scale-transition"
            :close-on-content-click="false"
        >
            <template v-slot:activator="{ props }">
                <img :src="image" alt="">
              <div v-bind="props">{{ nickname }}</div>
            </template>

            <v-card min-width="200" style="border-radius: 10%;">
                <v-list style="text-align: center;">
                    <v-list-item
                        :prepend-avatar="image"
                    >
                        <div style="text-align: center;">
                            {{ nickname }}
                        </div>
                    </v-list-item>
                </v-list>

                <p style="border: solid 1px black;"></p>

                <v-list style="text-align: center; cursor: pointer;">
                    <v-list-item>
                        <div @click="router.push({ name: 'mypage' }), menu = false">
                            My Page
                        </div>
                    </v-list-item>

                    <v-list-item>
                        <div @click="logout">
                            로그아웃
                        </div>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-menu>
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
import { onMounted, ref, watch } from 'vue'
import { useAuthStore } from '@/stores/auth';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router';
import LoginModal from '@/components/modal/LoginModal.vue'
const authStore = useAuthStore()
const userStore = useUserStore()
const router = useRouter()

const logins = ref(false)
const menu = ref(false)
const checkLogin = ref(false)
const email = ref(null)
const nickname = ref(null)
const id = ref(null)
const image = ref(null)

const logout = () => {
    authStore.clearToken()
    router.push({ path: '/' })
}

const openLoginModal = () => {
    logins.value = !logins.value
}


watch(() => authStore.isAuthenticated, (newVal, oldVal) => {
    checkLogin.value = newVal
})

watch(() => userStore.email, (newVal) => {
    email.value = newVal
})
 
watch(() => userStore.nickname, (newVal) => {
    nickname.value = newVal
})
 
watch(() => userStore.id, (newVal) => {
    id.value = newVal
})

watch(() => userStore.image_url, (newVal) => {
    image.value = newVal
})
 
    

onMounted(() => {
    checkLogin.value = authStore.isAuthenticated
    nickname.value = userStore.nickname
    email.value = userStore.email
    id.value = userStore.id
    image.value = userStore.image_url
})

</script>

<style scoped>

</style>