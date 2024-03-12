<template>
    <v-container>
        <v-row>
          <template
            v-for="(item, i) in items"
            :key="i"
        >
            <v-col
                cols="3"
                style="white-space: nowrap;"
                class="d-flex justify-center align-center"
            >
                <v-hover v-slot="{isHovering, props}">
                    <div 
                        class="v-text"
                        :elevation="isHovering ? 12 : 2"
                        :class="[{ 'on-hover': isHovering }]"
                        v-bind="props"
                        
                    >
                        {{ item }}
                    </div>
                </v-hover>
            </v-col>
        </template>
        </v-row>
      </v-container>
</template>

<script setup>
const items = (['농작물 정보', '농작물 추천', '요리 추천', '내 주변 텃밭'])

</script>

<style scoped>
.v-text {
    transition: opacity .25s ease-in-out;
    width: 60%;
    text-align: center;
    font-family: Noto Sans KR, sans-serif;
    font-weight: 700;
    font-size: 22px;
    line-height: 52px;
    cursor: pointer;
    -ms-user-select: none;
    -moz-user-select: -moz-none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    user-select: none;
  }

  .v-text:after {
    content: '';
    display: block;
    margin-top: 5px;
    border-bottom: solid 3px #00E676;
    transform: scaleX(0);
    transition: transform .25s ease-in-out;
  }

  .v-text:hover:after {
    transform: scaleX(1);
  }

  .v-text:not(.on-hover) {
    opacity: 0.4;
  }

  .v-text:hover, .v-text.on-hover {
    opacity: 1 !important;
    
  }
</style>