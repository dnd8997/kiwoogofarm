import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
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
      path: '/recommendtaion',
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
      path: '/recipe',
      name: 'recipe',
      component: () => import('../views/RecipeView.vue')
    },
    {
      path: '/garden',
      name: 'garden',
      component: () => import('../views/GardenView.vue')
    },
  ]
})

export default router
