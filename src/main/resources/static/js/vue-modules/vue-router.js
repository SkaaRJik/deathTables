import Vue from 'vue'
import VueRouter from 'vue-router'
import AuthPage from 'pages/AuthPage.vue'
import ProjectPage from 'pages/ProjectPage.vue'
import News from 'pages/News.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: News},
    { path: '/auth', component: AuthPage},
    { path: '/project', component: ProjectPage},
    { path: '*', component: News },
]

export default new VueRouter({
        mode: 'history',
        routes
    })

