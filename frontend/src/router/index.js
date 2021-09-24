import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/HelloWorld.vue'
import Admin from '../components/Admin.vue'
import Editor from '../components/Editor.vue'
import ExamDetail from '../components/ExamDetail.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/admin',
    name: 'Admin',
    component: Admin
  },
  {
    path: '/editor',
    name: 'Editor',
    component: Editor
  },
  {
    path: '/exam/:id',
    name: 'ExamDetail',
    component: ExamDetail
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
