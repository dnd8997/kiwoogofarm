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
              <div v-bind="props">연우</div>
            </template>

            <v-card min-width="200" style="border-radius: 10%;">
                <v-list style="text-align: center;">
                    <v-list-item
                        prepend-avatar="https://cdn.vuetifyjs.com/images/john.jpg"
                    >
                        <div style="text-align: center;">
                            연우
                        </div>
                    </v-list-item>
                </v-list>

                <p style="border: solid 1px black;"></p>

                <v-list style="text-align: center;">
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
import { useRouter } from 'vue-router';
import LoginModal from '@/components/modal/LoginModal.vue'
const authStore = useAuthStore()
const router = useRouter()

const logins = ref(false)
const menu = ref(false)
const checkLogin = ref(false)

const logout = () => {
    authStore.clearToken()
    router.go(0)
}

const openLoginModal = () => {
    logins.value = !logins.value
}


watch(() => authStore.isAuthenticated, (newVal, oldVal) => {
    checkLogin.value = newVal
})

onMounted(() => {
    checkLogin.value = authStore.isAuthenticated
})

</script>

<style scoped>

</style>