<template>
  <section class="slide1">
    <div class="wrap-slick1">
      <div class="slick1" ref="slick">
        <template>
          <div class="item-slick1 item1-slick1" style="background-image: url('/images/main1.jpg')">
            <div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
              <span
                class="caption1-slide1 m-text1 t-center animated visible-false m-b-15"
                style="color:black ;font-weight:bold"
                data-appear="fadeInDown"
              >
                행정동과 이름을 통해 주택의 위치와 누적 실거래가를 검색합니다.
              </span>

              <h2
                class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37"
                data-appear="fadeInUp"
                style="color:black;font-weight:bold"
              >
                주택 실거래가 검색
              </h2>

              <div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
                <!-- Button -->
                <router-link to="/search" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  <a class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
                    Go Now
                  </a>
                </router-link>
              </div>
            </div>
          </div>

          <div class="item-slick1 item1-slick1" style="background-image: url('/images/main2.jpg')">
            <div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
              <span
                class="caption1-slide1 m-text1 t-center animated visible-false m-b-15"
                style="color:black ;font-weight:bold"
                data-appear="fadeInDown"
              >
                구/군별 확진자 추세와 선별진료소를 검색합니다.
              </span>

              <h2
                class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37"
                data-appear="fadeInUp"
                style="color:black;font-weight:bold"
              >
                선별진료소 검색
              </h2>

              <div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
                <!-- Button -->
                <router-link to="/hospital" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  <a class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
                    Go Now
                  </a>
                </router-link>
              </div>
            </div>
          </div>

          <div class="item-slick1 item1-slick1" style="background-image: url('/images/main3.jpg')">
            <div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
              <span
                class="caption1-slide1 m-text1 t-center animated visible-false m-b-15"
                style="color:black ;font-weight:bold"
                data-appear="fadeInDown"
              >
                두 구/군의 생활시설을 비교한 결과를 제공합니다.
              </span>

              <h2
                class="caption2-slide1 xl-text1 t-center animated visible-false m-b-37"
                data-appear="fadeInUp"
                style="color:black;font-weight:bold"
              >
                생활시설 비교
              </h2>

              <div class="wrap-btn-slide1 w-size1 animated visible-false" data-appear="zoomIn">
                <!-- Button -->
                <router-link to="/welfare" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  <a class="flex-c-m size2 bo-rad-23 s-text2 bgwhite hov1 trans-0-4">
                    Go Now
                  </a>
                </router-link>
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
  data() {
    return {};
  },
  computed: {
    ...mapState("banner", {
      banners: (state) => state.mainBanners,
    }),
  },
  created() {
    this.$store.dispatch("banner/setMainBanners").then(() => {
      $(this.$refs.slick).slick1();
    });
  },
};
</script>
