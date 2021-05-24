import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Shop from './views/Shop.vue'
import Features from './views/Features';
import AddMember from './components/member/AddMember.vue';
import NoticeList from './components/notice/NoticeList.vue';
import DetailNotice from './components/notice/DetailNotice.vue';
import AddNotice from './components/notice/AddNotice.vue';
import UpdateNotice from './components/notice/UpdateNotice.vue';

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop
    },
    {
      path: '/features',
      name: 'features',
      component: Features
    },
    {
      path: '/addmember',
      name: 'addmember',
      component: AddMember
    },
    {
      path: '/noticelist',
      name: 'noticelist',
      component: NoticeList
    },
    {
      path: '/detailnotice/:id',
      name: 'detailnotice',
      component: DetailNotice,
      props: true,
    },
    {
      path: '/addnotice',
      name: 'addnotice',
      component: AddNotice
    },
    {
      path: '/updatenotice/:id',
      name: 'updatenotice',
      component: UpdateNotice,
      props: true,
    },
  ]
})
