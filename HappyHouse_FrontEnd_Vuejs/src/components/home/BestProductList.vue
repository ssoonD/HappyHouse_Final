<template>
  <section class="banner bgwhite p-t-40 p-b-40">
    <div class="container">
      <div class="row">
        <div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
          <template>
            <!-- block1 -->
            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go1.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/search" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  실거래가검색
                </router-link>
              </div>
            </div>

            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go2.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/hospital" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  선별진료소
                </router-link>
              </div>
            </div>
          </template>
        </div>

        <div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
          <template>
            <!-- block1 -->
            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go3.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/welfare" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  시설비교
                </router-link>
              </div>
            </div>

            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go4.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/questionlist" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  QnA
                </router-link>
              </div>
            </div>
          </template>
        </div>

        <div class="col-sm-10 col-md-8 col-lg-4 m-l-r-auto">
          <template>
            <!-- block1 -->
            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go5.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/noticelist" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  공지사항
                </router-link>
              </div>
            </div>

            <div class="block1 hov-img-zoom pos-relative m-b-30">
              <img src="/images/go6.jpg" alt="IMG-BENNER" />

              <div class="block1-wrapbtn w-size2">
                <!-- Button -->
                <router-link to="/detailmember" class="flex-c-m size2 m-text2 bg3 hov1 trans-0-4">
                  마이페이지
                </router-link>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState("product", {
      products: (state) => state.bestProducts,
    }),
    firstColumn() {
      if (this.products.length >= 2) {
        return this.products.slice(0, 2);
      }

      return [];
    },
    secondColumn() {
      if (this.products.length >= 4) {
        return this.products.slice(2, 4);
      }

      return [];
    },
    thirdColumn() {
      if (this.products.length >= 5) {
        return this.products[4];
      }

      return [];
    },
  },
  created() {
    this.$store.dispatch("product/setBestProducts");
  },
};
</script>
