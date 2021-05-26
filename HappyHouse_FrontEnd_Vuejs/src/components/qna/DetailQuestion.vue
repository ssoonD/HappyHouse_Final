<template>
  <div class="container-table-cart pos-relative">
    <div class="wrap-table-shopping-cart bgwhite">
      <h1 class="mt-4 mb-4" style="text-align:center;">Detail Question</h1>
      <div class="contianer mt-4 mb-4 addnoticediv" v-if="checklogin()">
        <div class="header-cart-wrapbtn addnoticedivright">
          <button
            type="button"
            @click="show_detail(question.idx)"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            수정하기
          </button>
        </div>
        <div class="header-cart-wrapbtn addnoticedivleft">
          <button
            type="button"
            @click="delete_question(question.idx)"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            삭제하기
          </button>
        </div>
      </div>
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

        <template>
          <tr class="table-row">
            <td class="column-1 notice-1"></td>
            <td class="notice-8" v-if="question.secret == 0"></td>
            <td class="notice-8" v-else>🔒</td>
            <td class="column-2 notice-2">{{ question.idx }}</td>
            <td class="column-3 notice-3">{{ question.username }}</td>
            <td class="column-4 notice-4">{{ question.title }}</td>
            <td class="column-5 notice-5">{{ question.date }}</td>
            <td class="notice-6">{{ question.count }}</td>
            <td class="notice-7"></td>
          </tr>
          <tr class="table-row">
            <td colspan="3"></td>
            <td colspan="4">
              <div id="questioncontent" v-html="questioncontent"></div>
            </td>
            <td class="notice-1"></td>
          </tr>
        </template>
      </table>
    </div>
    <!-- 응답 -->
    <div class="wrap-table-shopping-cart bgwhite">
      <h1 class="mt-4 mb-4" style="text-align:center;">Answers</h1>
      <table class="table-shopping-cart">
        <tr class="table-head">
          <th class="answer-1"></th>
          <th class="answer-2">Username</th>
          <th class="answer-3">Contnent</th>
          <th class="answer-4"></th>
          <th class="answer-5"></th>
          <th class="answer-6"></th>
        </tr>

        <template v-for="item in answer">
          <tr class="table-row">
            <td class="answer-1"></td>
            <td class="answer-2">{{ item.username }}</td>
            <td class="answer-3" v-if="!updateanswercheck || answeridx !== item.idx">
              {{ item.content }}
            </td>
            <td class="answer-3" v-else>
              <b-form
                @submit.prevent="updateAnswer(item.idx, item.content)"
                @reset.prevent="onReset"
                class="mt-4 mb-4"
              >
                <b-form-group id="updatecontent" label="Answer Content" label-for="updatecontent">
                  <b-form-textarea
                    id="answercontent"
                    v-model="item.content"
                    placeholder="Enter Answer Content."
                    rows="5"
                    required
                  ></b-form-textarea>
                </b-form-group>
                <div class="contianer mt-4 mb-4 addnoticediv">
                  <div class="header-cart-wrapbtn addnoticedivright">
                    <button
                      type="submit"
                      class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
                    >
                      Submit
                    </button>
                  </div>
                  <div class="header-cart-wrapbtn addnoticedivleft">
                    <button
                      type="reset"
                      class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
                    >
                      reset
                    </button>
                  </div>
                </div>
              </b-form>
            </td>
            <td class="answer-4">
              <button
                v-if="checkanswerlogin(item.username) && answeridx !== item.idx"
                type="button"
                @click="update_answer(item.idx)"
                class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4"
              >
                수정
              </button>
            </td>
            <td class="answer-5">
              <button
                v-if="checkanswerlogin(item.username) && answeridx !== item.idx"
                type="button"
                @click="delete_answer(item.idx)"
                class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4"
              >
                삭제
              </button>
            </td>
            <td class="answer-6"></td>
          </tr>
        </template>
      </table>

      <!-- 응답 제출 -->
      <div class="container border mt-4 mb-4" style="width: 70%;">
        <b-form @submit.prevent="addAnswer" @reset.prevent="onReset" class="mt-4 mb-4">
          <b-form-group id="answercontent" label="Answer Content" label-for="answercontent">
            <b-form-textarea
              id="answercontent"
              v-model="answercontent"
              placeholder="Enter Answer Content."
              rows="5"
              required
            ></b-form-textarea>
          </b-form-group>
          <div class="contianer mt-4 mb-4 addnoticediv">
            <div class="header-cart-wrapbtn addnoticedivright">
              <button
                type="submit"
                class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
              >
                Submit
              </button>
            </div>
            <div class="header-cart-wrapbtn addnoticedivleft">
              <button
                type="reset"
                class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
              >
                reset
              </button>
            </div>
          </div>
          <!-- <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button> -->
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/http-common";
import { mapState } from "vuex";

export default {
  name: "detail-question",
  props: ["id"],
  data() {
    return {
      question: {},
      answer: [],
      loading: true,
      errored: false,
      answercontent: "",
      updateanswercheck: false,
      answeridx: -1,
      answercontenttmp: "",
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
      this.$router.push("/updatequestion/" + idx);
    },
    async show_init() {
      try {
        const response = await http.get("/findQuestionByIdx/" + this.id);
        const question = response.data;
        const answerResponse = await http.get("/getQuestionsTotalByIdx/" + question.idx);
        const count = answerResponse.data.count || 0;
        this.question = { ...question, count };
        const responseAnswer = await http.get("/findAllAnswer/" + this.id);
        const answertmp = responseAnswer.data;
        this.answer = answertmp;
      } catch {
        this.errored = true;
      } finally {
        this.loading = false;
      }
    },
    // async show_answer_list() {
    //   await http
    //     .get("/findAllAnswer/" + this.id)
    //     .then((response) => (this.answer = response.data))
    //     .catch(() => {
    //       this.errored = true;
    //     })
    //     .finally(() => (this.loading = false));
    // },
    delete_question(idx) {
      // alert(idx + "를 삭제합니다.");
      http
        .delete("/deleteQuestion/" + idx)
        .then((response) => {
          if (response.data.state == "succ") {
            // alert("삭제를 성공했습니다.");
          } else {
            // alert("삭제를 실패했습니다.");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      this.show_init();
      this.$router.push("/questionlist");
    },
    checklogin() {
      return (
        this.question.username === this.loginmember.username || this.loginmember.userid === "admin"
      );
    },
    checkanswerlogin(username) {
      return username === this.loginmember.username || this.loginmember.userid === "admin";
    },
    addAnswer() {
      http
        .post("/addAnswer", {
          qidx: this.id,
          username: this.loginmember.username,
          content: this.answercontent,
        })
        .then((response) => {
          // if (response.data.state == "succ") {
          //   alert("작성에 성공하였습니다.");
          // } else {
          //   alert("작성에 실패하였습니다.");
          // }
        });
      this.answercontent = "";
      this.show_init();
    },
    onReset() {
      this.answercontent = "";
    },
    delete_answer(idx) {
      http
        .delete("/deleteAnswer/" + idx)
        .then((response) => {
          if (response.data.state == "succ") {
            //   alert("삭제를 성공했습니다.");
          } else {
            //   alert("삭제를 실패했습니다.");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
      this.show_init();
    },
    update_answer(idx) {
      this.updateanswercheck = true;
      this.answeridx = idx;
    },
    updateAnswer(idx, content) {
      // const response = http.get("/findAnswerByIdx/" + idx);
      // this.answercontenttmp = response.data.content;
      http
        .put("/updateAnswer", {
          idx: idx,
          content: content,
        })
        .then((response) => {
          if (response.data.state == "succ") {
            //   alert("작성에 성공하였습니다.");
          } else {
            //   alert("작성에 실패하였습니다.");
          }
        });
      this.updateanswercheck = false;
      this.answeridx = -1;
      this.show_init();
    },
  },
  created() {
    this.show_init();
  },
  mounted() {
    this.show_init();
    // this.show_answer_list();
  },
  computed: {
    ...mapState("member", {
      loginmember: (state) => state.member,
    }),
    questioncontent: function() {
      return this.question.content.replace(/\n/g, "<br />");
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

.answer-1 {
  width: 10%;
  padding-left: 50px;
}
.answer-2 {
  width: 10%;
  padding-left: 50px;
}
.answer-3 {
  width: 56%;
  padding-left: 50px;
}
.answer-4 {
  width: 7%;
  padding-left: 50px;
}
.answer-5 {
  width: 7%;
  padding-left: 50px;
}
.answer-6 {
  width: 10%;
  padding-left: 50px;
}
</style>
