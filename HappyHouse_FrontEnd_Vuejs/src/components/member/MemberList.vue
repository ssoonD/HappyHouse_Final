<template>
  <div class="container-table-cart pos-relative">
    <div class="wrap-table-shopping-cart bgwhite">
      <h1 class="mt-4 mb-4" style="text-align:center;">UserList</h1>
      <table class="table-shopping-cart">
        <tr class="table-head">
          <th class="column-1 notice-1"></th>
          <th class="column-2 notice-2">UserId</th>
          <th class="column-3 notice-3">UserName</th>
          <th class="column-4 p-l-70 notice-4">PassWord</th>
          <th class="column-5 notice-5">Email</th>
          <th class="column-5 notice-6">Number</th>
          <th class="column-5 notice-7">Address</th>
          <th class="column-5 notice-8">JoinData</th>
          <th class="notice-9"></th>
        </tr>

        <template v-for="item in member">
          <tr class="table-row">
            <td class="column-1 notice-1"></td>
            <td class="column-2 notice-2" @click="show_detail(item.userid)">{{ item.userid }}</td>
            <td class="column-3 notice-3" @click="show_detail(item.userid)">{{ item.username }}</td>
            <td class="column-4 notice-4" @click="show_detail(item.userid)">{{ item.userpwd }}</td>
            <td class="column-5 notice-5" @click="show_detail(item.userid)">{{ item.email }}</td>
            <td class="column-5 notice-6" @click="show_detail(item.userid)">{{ item.number }}</td>
            <td class="column-5 notice-7" @click="show_detail(item.userid)">{{ item.address }}</td>
            <td class="column-5 notice-8" @click="show_detail(item.userid)">{{ item.joindate }}</td>
            <td class="notice-9"></td>
          </tr>
        </template>
      </table>

      <!-- <div class="contianer mt-4 mb-4 addnoticediv">
          <div class="header-cart-wrapbtn addnoticedivright">
            <button type="button" @click="show_detail(member.userid)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">정보 수정하기</button>
          </div>
          <div class="header-cart-wrapbtn addnoticedivleft">
            <button type="button" @click="delete_notice(member.userid)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">탈퇴하기</button>
          </div>
      </div> -->
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
      member: [],
      loading: true,
      errored: false,
    };
  },
  methods: {
    retrieveMember() {
      http
        .get("/findAllMember")
        .then((response) => (this.member = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    refreshList() {
      this.retrieveMember();
    },
    show_detail: function(userid) {
      // alert(userid + "상세보기");
      this.$router.push("/detailmember/" + userid);
    },
  },
  filters: {
    salarydecimal(value) {
      var a = parseInt(value);
      return a.toFixed(2);
    },
  },
  mounted() {
    this.retrieveMember();
  },
  updated() {
    this.retrieveMember();
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
  width: 5%;
  padding-left: 50px;
}

.notice-2 {
  width: 10%;
  padding-left: 50px;
}

.notice-3 {
  width: 10%;
  padding-left: 50px;
}

.notice-4 {
  width: 10%;
  padding-left: 50px;
}

.notice-5 {
  width: 10%;
  padding-left: 50px;
}

.notice-6 {
  width: 10%;
  padding-left: 50px;
}

.notice-7 {
  width: 20%;
  padding-left: 50px;
}

.notice-8 {
  width: 20%;
  padding-left: 50px;
}

.notice-9 {
  width: 5%;
  padding-left: 50px;
}
</style>
