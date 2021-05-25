<template>
  <div class="container-table-cart pos-relative">
    <h1 class="mt-4 mb-4" style="text-align:center;">Notice</h1>
    <!-- <div class="container mt-4 mb-4" style="text-align:center;"> -->
      <b-container class="mt-4 mb-4">
        <b-row>
          <b-col sm="3"></b-col>
          <b-col sm="2">
            <b-form-select v-model="selected" :options="options"></b-form-select>
          </b-col>
          <b-col sm="4">
            <b-form-input placeholder="Enter search value" v-model="selectedValue" @keyup.enter="searchNotice" v-cloak />   
          </b-col>
          <b-col sm="3"></b-col>
        </b-row>
      </b-container>
    <!-- </div> -->
    <div class="wrap-table-shopping-cart bgwhite mt-4">
      <table class="table-shopping-cart">
        <tr class="table-head">
          <th class="column-1 notice-1"></th>
          <th class="column-2 notice-2">Idx</th>
          <th class="column-3 notice-3">Username</th>
          <th class="column-4 p-l-70 notice-4">Title</th>
          <th class="column-5 notice-5">Date</th>
          <th class="notice-6"></th>
        </tr>

        <template v-for="item in notice">
          <tr class="table-row">
            <td class="column-1 notice-1">
              <!-- <div class="cart-img-product b-rad-4 o-f-hidden" @click="delItem(item.id)">
                <img :src="item.image" alt="IMG-PRODUCT">
              </div> -->
            </td>
            <td class="column-2 notice-2" @click="show_detail(item.idx)">{{ item.idx }}</td>
            <td class="column-3 notice-3" @click="show_detail(item.idx)">{{ item.username }}</td>
            <td class="column-4 notice-4" @click="show_detail(item.idx)">
              <!-- <div class="flex-w bo5 of-hidden w-size17">
                <button class="btn-num-product-down color1 flex-c-m size7 bg8 eff2" @click="decrease(item.id)">
                  <i class="fs-12 fa fa-minus" aria-hidden="true"></i>
                </button>

                <input class="size8 m-text18 t-center num-product" type="number" name="num-product1" :value="item.qty">

                <button class="btn-num-product-up color1 flex-c-m size7 bg8 eff2" @click="increase(item.id)">
                  <i class="fs-12 fa fa-plus" aria-hidden="true"></i>
                </button>
              </div> -->{{ item.title }}
            </td>
            <td class="column-5 notice-5" @click="show_detail(item.idx)">{{ item.date }}</td>
            <td class="notice-6"></td>
          </tr>
        </template>

      </table>
      <div class="container mt-4 mb-4 addnoticediv">
        <router-link to="/addnotice" style="text-decoration:none" class="button flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">Add Notice</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/http-common";
export default {
  name: "notice-list",
  data() {
    return {
      upHere: false,
      notice: [],
      loading: true,
      ctitle: "",
      errored: false,
      selected: null,
      selectedValue: '',
      options: [
        {value: null, text: 'Please select an option'},
        {value: 'title', text: 'Title'},
        {value: 'username', text: 'Username'}
      ]
    };
  },
  methods: {
    retrieveNotice() {
      http
        .get("/findAllNotice")
        .then(response => (this.notice = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    refreshList() {
      this.retrieveNotice();
    },
    show_detail: function(idx) {
      alert(idx + "상세보기");
      this.$router.push("/detailnotice/" + idx);
    },
    searchNotice(e) {
      if (e.which == 13) {
        //enter
        if(this.selected === 'title') {
          http
            .get("/findNoticeByTitle/" + this.selectedValue)
            .then(response => (this.notice = response.data))
            .catch(() => {
              this.errored = true;
            })
            .finally(() => (this.loading = false));
        } else if (this.selected === 'username') {
          http
            .get("/findNoticeByUsername/" + this.selectedValue)
            .then(response => (this.notice = response.data))
            .catch(() => {
              this.errored = true;
            })
            .finally(() => (this.loading = false));
        } else {
          this.retrieveNotice();
        }
        this.selectedValue = '';
      }
    }
  },
  filters: {
    salarydecimal(value) {
      var a = parseInt(value);
      return a.toFixed(2);
    }
  },
  mounted() {
    this.retrieveNotice();
  }
};
</script>

<style scoped>
  .addnotice {
    width: 30%;
  }
  .addnoticediv {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
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