<template>
  <div class="container-table-cart pos-relative">
    <h1 class="mt-4 mb-4" style="text-align:center;">Question</h1>
    <!-- <div class="container mt-4 mb-4" style="text-align:center;"> -->
    <b-container class="mt-4 mb-4">
      <b-row>
        <b-col sm="3"></b-col>
        <b-col sm="2">
          <b-form-select v-model="selected" :options="options"></b-form-select>
        </b-col>
        <b-col sm="4">
          <b-form-input
            placeholder="Enter search value"
            v-model="selectedValue"
            @keyup.enter="searchQuestion"
            v-cloak
          />
        </b-col>
        <b-col sm="3"></b-col>
      </b-row>
    </b-container>
    <!-- </div> -->
    <div class="wrap-table-shopping-cart bgwhite mt-4">
      <table class="table-shopping-cart">
        <tr class="table-head">
          <th class="column-1 notice-1"></th>
          <th class="notice-8"></th>
          <th class="column-2 notice-2">Idx</th>
          <th class="column-3 notice-3">Username</th>
          <th class="column-4 p-l-70 notice-4">Title</th>
          <th class="column-5 notice-5">Date</th>
          <th class="notice-6">Answers</th>
          <th class="notice-7"></th>
        </tr>

        <template v-for="item in question">
          <tr class="table-row">
            <td class="column-1 notice-1"></td>
            <td class="notice-8" v-if="item.secret == 0"></td>
            <td class="notice-8" v-else>🔒</td>
            <td class="column-2 notice-2" @click="show_detail(item.idx)">{{ item.idx }}</td>
            <td class="column-3 notice-3" @click="show_detail(item.idx)">{{ item.username }}</td>
            <td class="column-4 notice-4" @click="show_detail(item.idx)">{{ item.title }}</td>
            <td class="column-5 notice-5" @click="show_detail(item.idx)">{{ item.date }}</td>
            <td class="notice-6" @click="show_detail(item.idx)">{{ item.count }}</td>
            <td class="notice-6"></td>
          </tr>
          <tr class="table-row" v-if="passcheck == item.idx">
            <td colspan="4"></td>
            <td colspan="1">
              <b-form-input
                type="password"
                v-model="password"
                placeholder="Enter PassWord"
              ></b-form-input>
            </td>
            <td colspan="1">
              <button
                type="button"
                @click="check_pass(item.idx)"
                class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice ml-4"
              >
                입력
              </button>
            </td>
            <td colspan="2"></td>
          </tr>
        </template>
      </table>

      <div class="container mt-4 mb-4 addnoticediv">
        <router-link
          to="/addquestion"
          style="text-decoration:none"
          class="button flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >Add Question</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/http-common";
import { mapState } from "vuex";

export default {
  name: "question-list",
  data() {
    return {
      upHere: false,
      question: [],
      loading: true,
      errored: false,
      selected: null,
      selectedValue: "",
      answer: {},
      passcheck: -1,
      password: "",
      passtoggle: false,
      options: [
        { value: null, text: "Please select an option" },
        { value: "title", text: "Title" },
        { value: "username", text: "Username" },
      ],
    };
  },
  methods: {
    async retrieveNotice() {
      try {
        const response = await http.get("/findAllQuestion");
        const questions = response.data;
        const answers = await Promise.all(
          questions.map((q) =>
            http.get("/getQuestionsTotalByIdx/" + q.idx).then((r) => {
              return {
                idx: q.idx,
                count: r.data.count || 0,
              };
            })
          )
        );
        this.question = questions.map((question) => {
          const answer = answers.find((a) => a.idx === question.idx);
          if (!answer) return;
          return { ...question, count: answer.count };
        });
        console.log(this.question);
      } catch (e) {
        this.errored = true;
      } finally {
        this.loading = false;
      }
    },
    refreshList() {
      this.retrieveNotice();
    },
    show_detail: function(idx) {
      // alert(idx + "상세보기");
      if (this.loginmember.userid === "admin") {
        this.$router.push("/detailquestion/" + idx);
      }
      const secretValue = this.question.find((q) => q.idx === idx).secret;
      if (secretValue === 1) {
        this.passcheck = idx;
      } else {
        this.passcheck = -1;
        this.$router.push("/detailquestion/" + idx);
      }
    },
    check_pass(idx) {
      const passValue = this.question.find((q) => q.idx === idx).password;
      if (this.password === passValue) {
        this.$router.push("/detailquestion/" + idx);
      } else {
        alert("비밀번호를 다시 입력해주세요!");
        this.password = "";
      }
    },
    searchQuestion(e) {
      if (e.which == 13) {
        //enter
        if (this.selected === "title") {
          this.searchTitle();
        } else if (this.selected === "username") {
          this.searchName();
        } else {
          this.retrieveNotice();
        }
      }
      this.selectedValue = "";
    },
    async searchTitle() {
      try {
        const response = await http.get("/findAllQuestionByTitle/" + this.selectedValue);
        const questions = response.data;
        if (questions === "") {
          this.question = [];
        } else {
          const answers = await Promise.all(
            questions.map((q) =>
              http.get("/getQuestionsTotalByIdx/" + q.idx).then((r) => {
                return {
                  idx: q.idx,
                  count: r.data.count || 0,
                };
              })
            )
          );
          this.question = questions.map((question) => {
            const answer = answers.find((a) => a.idx === question.idx);
            if (!answer) return;
            return { ...question, count: answer.count };
          });
        }
      } catch (e) {
        this.errored = true;
      } finally {
        this.loading = false;
      }
    },
    async searchName() {
      try {
        const response = await http.get("/findAllQuestionByName/" + this.selectedValue);
        const questions = response.data;
        if (questions === "") {
          this.question = [];
        } else {
          const answers = await Promise.all(
            questions.map((q) =>
              http.get("/getQuestionsTotalByIdx/" + q.idx).then((r) => {
                return {
                  idx: q.idx,
                  count: r.data.count || 0,
                };
              })
            )
          );
          this.question = questions.map((question) => {
            const answer = answers.find((a) => a.idx === question.idx);
            if (!answer) return;
            return { ...question, count: answer.count };
          });
        }
      } catch (e) {
        this.errored = true;
      } finally {
        this.loading = false;
      }
    },
  },
  filters: {
    salarydecimal(value) {
      var a = parseInt(value);
      return a.toFixed(2);
    },
  },
  created() {
    this.retrieveNotice();
  },
  mounted() {
    this.retrieveNotice();
  },
  computed: {
    ...mapState("member", {
      loginmember: (state) => state.member,
    }),
  },
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
  width: 7%;
  padding-left: 50px;
}

.notice-3 {
  width: 20%;
  padding-left: 50px;
}

.notice-4 {
  width: 20%;
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

.notice-7 {
  width: 10%;
  padding-left: 50px;
}

.notice-8 {
  width: 3%;
  padding-left: 50px;
}
</style>
