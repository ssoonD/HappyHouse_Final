import Vue from 'vue'
import Vuex from 'vuex'

import banner from './banner/index';
import product from './product/index';
import cart from './cart/index';
import blog from './blog/index';
import insta from './insta/index';
import member from './member/index';

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    banner,
    product,
    cart,
    blog,
    insta,
    member
  },
  state: {

  },
  mutations: {

  },
  actions: {

  }
})
