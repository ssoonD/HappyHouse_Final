<template>
  <section class="instagram p-t-20">
    <div class="sec-title p-b-52 p-l-15 p-r-15">
      <h3 class="m-text5 t-center">
        News Collection
      </h3>
    </div>

    <div class="flex-w">
      <!-- Block4 -->
      <template>
        <div class="block4 wrap-pic-w" @click="movenews(0)">
          <img src="/images/news1.jpg" alt="IMG-INSTAGRAM" style="width:100%;height:100%" />

          <div class="block4-overlay sizefull ab-t-l trans-0-4">
            <span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
              <span class="p-t-2"> {{ news.rss.channel.item[0].title._cdata }}</span>
            </span>
            <div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
              <p class="s-text10 m-b-15 h-size1 of-hidden">
                {{ news.rss.channel.item[0].description._cdata }}
              </p>

              <span class="s-text9">
                {{ news.rss.channel.item[0].author._text }}
              </span>
            </div>
          </div>
        </div>

        <div class="block4 wrap-pic-w" @click="movenews(1)">
          <img src="/images/news2.png" alt="IMG-INSTAGRAM" style="width:100%;height:100%" />

          <div class="block4-overlay sizefull ab-t-l trans-0-4">
            <span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
              <span class="p-t-2"> {{ news.rss.channel.item[1].title._cdata }}</span>
            </span>
            <div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
              <p class="s-text10 m-b-15 h-size1 of-hidden">
                {{ news.rss.channel.item[1].description._cdata }}
              </p>

              <span class="s-text9">
                {{ news.rss.channel.item[1].author._text }}
              </span>
            </div>
          </div>
        </div>

        <div class="block4 wrap-pic-w" @click="movenews(2)">
          <img src="/images/news2.jpg" alt="IMG-INSTAGRAM" style="width:100%;height:100%" />

          <div class="block4-overlay sizefull ab-t-l trans-0-4">
            <span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
              <span class="p-t-2"> {{ news.rss.channel.item[2].title._cdata }}</span>
            </span>
            <div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
              <p class="s-text10 m-b-15 h-size1 of-hidden">
                {{ news.rss.channel.item[2].description._cdata }}
              </p>

              <span class="s-text9">
                {{ news.rss.channel.item[2].author._text }}
              </span>
            </div>
          </div>
        </div>

        <div class="block4 wrap-pic-w" @click="movenews(3)">
          <img src="/images/news4.jpg" alt="IMG-INSTAGRAM" style="width:100%;height:100%" />

          <div class="block4-overlay sizefull ab-t-l trans-0-4">
            <span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
              <span class="p-t-2"> {{ news.rss.channel.item[3].title._cdata }}</span>
            </span>
            <div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
              <p class="s-text10 m-b-15 h-size1 of-hidden">
                {{ news.rss.channel.item[3].description._cdata }}
              </p>

              <span class="s-text9">
                {{ news.rss.channel.item[3].author._text }}
              </span>
            </div>
          </div>
        </div>

        <div class="block4 wrap-pic-w" @click="movenews(4)">
          <img src="/images/news5.jpg" alt="IMG-INSTAGRAM" style="width:100%;height:100%" />

          <div class="block4-overlay sizefull ab-t-l trans-0-4">
            <span class="block4-overlay-heart s-text9 flex-m trans-0-4 p-l-40 p-t-25">
              <span class="p-t-2"> {{ news.rss.channel.item[4].title._cdata }}</span>
            </span>
            <div class="block4-overlay-txt trans-0-4 p-l-40 p-r-25 p-b-30">
              <p class="s-text10 m-b-15 h-size1 of-hidden">
                {{ news.rss.channel.item[4].description._cdata }}
              </p>

              <span class="s-text9">
                {{ news.rss.channel.item[4].author._text }}
              </span>
            </div>
          </div>
        </div>
      </template>
    </div>
  </section>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  computed: {
    ...mapState("insta", {
      articles: (state) => state.articles,
    }),
  },
  data() {
    return {
      news: [],
      errored: false,
      loading: true,
    };
  },
  async created() {
    this.$store.dispatch("insta/setArticles");

    var convert = require("xml-js");
    await axios
      .get("http://file.mk.co.kr/news/rss/rss_50300009.xml")
      .then((response) => {
        var xml = response.data;
        var json = convert.xml2json(xml, { compact: true });
        this.news = JSON.parse(json);
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    movenews(i) {
      console.log("doal");
      var link = this.news.rss.channel.item[i].link._cdata;
      location.href = link;
    },
  },
};
</script>
