import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { jwtDecode } from 'jwt-decode'
import * as MyPage from '@/js/MyPage';

export const useAuthStore = defineStore('auth', () => {
    const email = ref(null)
    const nickName = ref(null)
    const userId = ref(null)
    
    const isAuthenticated = computed(() => !!accessToken.value)

    function setToken(accessToken, refreshToken) {
        try {
            const token = jwtDecode(accessToken)
            email.value = token.email
            localStorage.setItem('accessToken', accessToken)
            localStorage.setItem('refreshToken',  refreshToken)
            MyPage.getUserinfo(
                email.value,
                (success) => {
                    nickName.value = success.data.nickname
                    userId.value = success.data.Id
                },
                (error) => {
                    console.error(error)
                }
            )
        } catch (error) {
            console.error(error)
        }
    }

    function clearToken() {
        try {
            email.value = null
            nickName.value = null
            userId.value = null
            localStorage.removeItem('accessToken')
            localStorage.removeItem('refreshToken')
        }   catch (error) {
            console.error(error)
        }
    }
    
    return { email, isAuthenticated, nickName, setToken, clearToken, }
})