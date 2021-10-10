import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/HelloWorld.vue'
import Admin from '../components/Admin.vue'
import Editor from '../components/Editor.vue'
import Index from '../components/index.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home, 
    children: [
      {
        path: '',
        component: Index
      }
    ]
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
