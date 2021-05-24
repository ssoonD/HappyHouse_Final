export default {
  namespaced: true,
  state: {
    mainMember: {}
  },
  mutations: {
    setMainMember(state, member) {
      state.mainMember = member;
    }
  },
  actions: {
    async setMainMember({ commit }) {
      const response = await bannerApi.getMainSlideBanners();

      commit('setMainMember', response.data);
    }
  }
}