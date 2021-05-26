import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Shop from './views/Shop.vue'
import Features from './views/Features';

import MemberList from './components/member/MemberList.vue'
import DetailMember from './components/member/DetailMember.vue'
import AddMember from './components/member/AddMember.vue';
import UpdateMember from './components/member/UpdateMember.vue';
import FindAccount from './components/member/FindAccount.vue';

import NoticeList from './components/notice/NoticeList.vue';
import DetailNotice from './components/notice/DetailNotice.vue';
import AddNotice from './components/notice/AddNotice.vue';
import UpdateNotice from './components/notice/UpdateNotice.vue';

import QuestionList from './components/qna/QuestionList.vue';
import DetailQuestion from './components/qna/DetailQuestion.vue';
import AddQuestion from './components/qna/AddQuestion.vue';
import UpdateQuestion from './components/qna/UpdateQuestion.vue';

import Apt from "./components/Search.vue";
import Hospital from "./components/SearchHospital.vue";
import Welfare from "./components/Welfare.vue";

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
      path: '/memberlist',
      name: 'memberlist',
      component: MemberList
    },
    {
      path: '/detailmember/:id',
      name: 'detailmember',
      component: DetailMember,
      props: true,
    },
    {
      path: '/addmember',
      name: 'addmember',
      component: AddMember
    },
    {
      path: '/updatemember/:id',
      name: 'updatemember',
      component: UpdateMember,
      props: true,
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
    {
      path: '/questionlist',
      name: 'questionlist',
      component: QuestionList
    },
    {
      path: '/detailquestion/:id',
      name: 'detailquestion',
      component: DetailQuestion,
      props: true,
    },
    {
      path: '/addquestion',
      name: 'addquestion',
      component: AddQuestion
    },
    {
      path: '/updatequestion/:id',
      name: 'updatequestion',
      component: UpdateQuestion,
      props: true,
    },
    {
      path: '/findaccount',
      name: 'findaccount',
      component: FindAccount,
    },
    {
      path: "/search",
      name: "search",
      component: Apt,
    },
    {
      path: "/hospital",
      name: "hospital",
      component: Hospital,
    },
    {
      path: "/welfare",
      name: "welfare",
      component: Welfare,
    },
  ]
})
