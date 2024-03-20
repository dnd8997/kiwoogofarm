<template>
    <div>
        <v-dialog
            v-model="props.logins"
            width="auto"
            height="420px"
            @click:outside="closeModal"
        >
            <v-card
                class="login-card"
                style="border-radius: 10px;"
            >   
            <template
                v-slot:append
            >
                <v-icon icon="mdi-close" style="cursor: pointer;"></v-icon>
            </template>
            <v-sheet>
                    <v-card-text class="text-center">
                        <button>
                            <img src="https://static.nid.naver.com/oauth/big_g.PNG" width=320 style="border-radius: 20px;">
                        </button>
                    </v-card-text>
                    <v-card-text class="text-center">
                        <button @click="loginWithKakao">
                            <img src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg" width="320" style="border-radius: 20px;"/>
                        </button>
                    </v-card-text>
                </v-sheet>
               
            </v-card>
            
        </v-dialog>
    </div>
</template>

<script setup>
import { ref, defineProps, defineEmits, onMounted, watch } from 'vue'

const props = defineProps({
    logins: {
        type: Boolean,
        required: true,
    }
})

const emit = defineEmits(['update:logins'])

function closeModal() {
    emit('update:logins', false)
}

onMounted(() => {
    console.log(props.logins)

})

const loginWithKakao = () => {

    window.location.href = "http://192.168.31.57:8080/oauth2/authorization/kakao"
}


</script>

<style scoped>
.login-card {
    width: 500px;
    height: 420px;
    
}

</style>