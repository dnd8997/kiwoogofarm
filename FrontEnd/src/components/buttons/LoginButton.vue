<template>
    <div>
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
import { ref } from 'vue'
import LoginModal from '@/components/modal/LoginModal.vue'

const logins = ref(false)

const openLoginModal = () => {
    logins.value = !logins.value
}


</script>

<style scoped>

</style>