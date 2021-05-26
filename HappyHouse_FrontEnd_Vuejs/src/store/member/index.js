export default {
  namespaced: true,
  state: {
    member: {},
    isLogin: false
  },
  getters: {
    loginMember(state) {
      return state.member;
    }
  },
  mutations: {
    loginMember(state, memberObj) {
      state.member = memberObj;
      state.isLogin = true;
    },
    logoutMember(state) {
      state.member = {};
      state.isLogin = false;
    }
  },
  actions: {
    loginMember({ commit }, memberObj) {
      commit('loginMember', memberObj);
    },
    logoutMember({ commit }) {
      commit('logoutMember');
    }
  }
}