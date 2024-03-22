import { createRouter, createWebHistory } from 'vue-router'
import { useSearchStore } from '@/stores/search'
import { useAuthStore } from '@/stores/auth'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: () => import('../views/MyPageView.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('../views/SearchView.vue')
    },
    {
      path: '/recommendation',
      name: 'recommendation',
      component: () => import('../views/RecommendationView.vue')
    },
    {
      path: '/recommendselect',
      name: 'recommendselect',
      component: () => import('../views/RecommendationSelectSurveyView.vue')
    },
    {
      path: '/recommendsurvey/1',
      name: 'insiderecommendsurvey',
      component: () => import('../views/InsideRecommedSurveyView.vue')
    },
    {
      path: '/recommendsurvey/2',
      name: 'outsiderecommendsurvey',
      component: () => import('../views/OutsideRecommendSurveyView.vue')
    },
    {
      path: '/recommendresult',
      name: 'recommendresult',
      component: () => import('../views/RecommendResultView.vue')
    },
    {
      path: '/recipe',
      name: 'recipe',
      component: () => import('../views/RecipeView.vue')
    },
    {
      path: '/recipeselect',
      name: 'recipeselect',
      component: () => import('../views/RecipeSelectView.vue')
    },
    {
      path: '/garden',
      name: 'garden',
      component: () => import('../views/GardenView.vue')
    },
     {
      path: '/success',
      name: 'success',
      component: () => import('../views/SuccessView.vue')
    },
  ]
})


router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const { accessToken, refreshToken } = to.query
  if ( to.name === 'success' && accessToken && refreshToken ) {
    authStore.setToken( accessToken, refreshToken )
    next('/')
  } else {
    next()
  }

})

router.afterEach((to, from) => {
  window.scrollTo(0, 0)

  if (from.path === '/search') {
    const searchStore = useSearchStore()
    searchStore.setSearchBox('')
  }
})


export default router
