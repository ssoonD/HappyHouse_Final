<template>
  <div class="container-table-cart pos-relative">
    <div class="wrap-table-shopping-cart bgwhite">
      <h1 class="mt-4 mb-4" style="text-align:center;">Detail Notice</h1>
      <table class="table-shopping-cart">
        <tr class="table-head">
          <th class="column-1 notice-1"></th>
          <th class="column-2 notice-2">Idx</th>
          <th class="column-3 notice-3">Username</th>
          <th class="column-4 p-l-70 notice-4">Title</th>
          <th class="column-5 notice-5">Date</th>
          <th class="notice-6"></th>
        </tr>

        <template>
          <tr class="table-row">
            <td class="column-1 notice-1">
              <!-- <div class="cart-img-product b-rad-4 o-f-hidden" @click="delItem(item.id)">
                <img :src="item.image" alt="IMG-PRODUCT">
              </div> -->
            </td>
            <td class="column-2 notice-2">{{ notice.idx }}</td>
            <td class="column-3 notice-3">{{ notice.username }}</td>
            <td class="column-4 notice-4">
              <!-- <div class="flex-w bo5 of-hidden w-size17">
                <button class="btn-num-product-down color1 flex-c-m size7 bg8 eff2" @click="decrease(item.id)">
                  <i class="fs-12 fa fa-minus" aria-hidden="true"></i>
                </button>

                <input class="size8 m-text18 t-center num-product" type="number" name="num-product1" :value="item.qty">

                <button class="btn-num-product-up color1 flex-c-m size7 bg8 eff2" @click="increase(item.id)">
                  <i class="fs-12 fa fa-plus" aria-hidden="true"></i>
                </button>
              </div> -->{{ notice.title }}
            </td>
            <td class="column-5 notice-5">{{ notice.date }}</td>
            <td class="notice-6"></td>
          </tr>
          <tr class="table-row">
            <td class="notice-1"></td>
            <td class="notice-1"></td>
            <td colspan="3">
              <div id="noticecontent" v-html="noticecontent"></div>
            </td>
            <td class="notice-1"></td>
          </tr>
        </template>
      </table>

      <div class="contianer mt-4 mb-4 addnoticediv" v-if="checklogin()">
        <div class="header-cart-wrapbtn addnoticedivright">
          <button
            type="button"
            @click="show_detail(notice.idx)"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            수정하기
          </button>
        </div>
        <div class="header-cart-wrapbtn addnoticedivleft">
          <button
            type="button"
            @click="delete_notice(notice.idx)"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            삭제하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/http-common";
import { mapState } from "vuex";

export default {
  name: "detail-notice",
  props: ["id"],
  data() {
    return {
      notice: {},
      loading: true,
      errored: false,
    };
  },
  filters: {
    salarydecimal(value) {
      var a = parseInt(value);
      return a.toFixed(2);
    },
  },
  methods: {
    show_detail: function(idx) {
      // alert(idx + "수정하기");
      //아래를 수정했다.
      this.$router.push("/updatenotice/" + idx);
    },
    show_init: function() {
      http
        .get("/findNoticeByIdx/" + this.id)
        .then((response) => (this.notice = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    delete_notice(idx) {
      // alert(idx + "를 삭제합니다.");
      http
        .delete("/deleteNotice/" + idx)
        .then((response) => {
          if (response.data.state == "succ") {
            // alert("삭제를 성공했습니다.");
            this.$router.push("/noticelist");
          } else {
            // alert("삭제를 실패했습니다.");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    checklogin() {
      return (
        this.notice.username === this.loginmember.username || this.loginmember.userid === "admin"
      );
    },
  },
  mounted() {
    this.show_init();
  },
  updated() {
    this.show_init();
  },
  computed: {
    ...mapState("member", {
      loginmember: (state) => state.member,
    }),
    noticecontent: function() {
      return this.notice.content.replace(/\n/g, "<br />");
    },
  },
};
</script>

<style scoped>
.addnotice {
  width: 30%;
}

.addnoticediv {
  display: flex;
  justify-content: center;
}

.addnoticedivright {
  display: flex;
  justify-content: flex-end;
  margin-right: 5px;
}

.addnoticedivleft {
  display: flex;
  justify-content: flex-start;
  margin-left: 5px;
}

.notice-1 {
  width: 10%;
  padding-left: 50px;
}

.notice-2 {
  width: 10%;
  padding-left: 50px;
}

.notice-3 {
  width: 20%;
  padding-left: 50px;
}

.notice-4 {
  width: 30%;
  padding-left: 50px;
}

.notice-5 {
  width: 20%;
  padding-left: 50px;
}

.notice-6 {
  width: 10%;
  padding-left: 50px;
}
</style>
