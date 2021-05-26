오후 9:52

<template>
  <section class="blog bgwhite p-t-94 p-b-65">
    <div class="container">
      <div class="sec-title p-b-52">
        <h3 class="m-text5 t-center">
          TEAM INTRODUCTION
        </h3>
      </div>

      <div class="row">
        <template v-for="article in articles">
          <div class="col-sm-10 col-md-4 p-b-30 m-l-r-auto">
            <!-- Block3 -->
            <div class="block3">
              <router-link :to="article.link" class="block3-img dis-block hov-img-zoom">
                <img :src="article.image" alt="IMG-BLOG" />
              </router-link>

              <div class="block3-txt p-t-14">
                <h4 class="p-b-7" style="text-align: center;">
                  {{ article.title }}
                </h4>
                <p class="s-text8 p-t-16" style="text-align: center;">
                  {{ article.content1 }}
                </p>
                <p class="s-text8 p-t-2" style="text-align: center;">
                  {{ article.content2 }}
                </p>
                <p class="s-text8 p-t-2" style="text-align: center;">
                  {{ article.content3 }}
                </p>
              </div>
            </div>
          </div>
        </template>
      </div>
    </div>
  </section>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState("blog", {
      articles: (state) => state.articles,
    }),
  },
  created() {
    this.$store.dispatch("blog/setArticles");
  },
};
</script>
