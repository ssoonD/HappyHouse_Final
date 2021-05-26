<template>
  <div>
    <div class="container mt-4 mb-4" v-if="!submitted" style="width: 70%;">
      <h1 class="mt-4 mb-4" style="text-align:center;">Update Question</h1>
      <div class="container border mt-4 mb-4">
        <b-form @submit.prevent="updateQuestion" @reset.prevent="onReset" class="mt-4 mb-4">
          <b-form-group id="username" label="Username:" label-for="username">
            <b-form-input
              id="username"
              v-model="question.username"
              placeholder="Enter username"
              required
              readonly
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="title"
            label="Title"
            label-for="title"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbacktitle"
            :state="titleState"
          >
            <b-form-input
              id="title"
              v-model="question.title"
              :state="titleState"
              placeholder="Enter title"
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group id="secret" label="Secret" label-for="secret" :state="secretState">
            <b-form-radio-group
              v-model="selected"
              :options="options"
              class="mb-3"
              value-field="item"
              text-field="name"
            ></b-form-radio-group>
            <b-form-input
              id="password"
              v-if="selected === 'private'"
              v-model="question.password"
              :state="secretState"
              placeholder="Enter at least 1 password."
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="content"
            label="Content"
            label-for="content"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackcontent"
            :state="contentState"
          >
            <b-form-textarea
              id="content"
              v-model="question.content"
              :state="contentState"
              placeholder="Enter content"
              rows="10"
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
    <!-- <div v-else>
      <router-link to="/noticelist"></router-link>
    </div> -->
  </div>
</template>

<script>
import http from "@/http-common";
import { mapState } from "vuex";

export default {
  name: "update-question",
  props: ["id"],
  data() {
    return {
      question: {
        username: "",
        title: "",
        content: "",
        secret: 0,
        password: "",
      },
      submitted: false,
      selected: "public",
      options: [
        { item: "public", name: "Public 🔓" },
        { item: "private", name: "Private 🔒" },
      ],
    };
  },
  computed: {
    ...mapState("member", {
      loginmember: (state) => state.member,
    }),
    titleState() {
      return this.question.title.length >= 4 ? true : false;
    },
    invalidFeedbacktitle() {
      if (this.question.title.length >= 4) {
        return "";
      } else if (this.question.title.length > 0) {
        return "Enter at least 4 characters.";
      } else if (this.question.title.length === 0) {
        return "Please enter something.";
      }
    },
    contentState() {
      return this.question.content.length >= 10 ? true : false;
    },
    invalidFeedbackcontent() {
      if (this.question.content.length >= 10) {
        return "";
      } else if (this.question.content.length > 0) {
        return "Enter at least 10 characters.";
      } else if (this.question.content.length === 0) {
        return "Please enter something.";
      }
    },
    secretState() {
      return this.question.password.length >= 1 ? true : false;
    },
  },
  mounted() {
    http
      .get("/findQuestionByIdx/" + this.id)
      .then((response) => {
        this.question = response.data;
        if (this.question.secret === 0) {
          this.selected = "public";
        } else {
          this.selected = "private";
        }
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  },
  methods: {
    updateQuestion() {
      http
        .put("/updateQuestion", {
          idx: this.id,
          username: this.question.username,
          title: this.question.title,
          content: this.question.content,
          secret: this.selected === "public" ? 0 : 1,
          password: this.question.password,
        })
        .then((response) => {
          // if (response.data.state == "succ") {
          //   alert("작성에 성공하였습니다.");
          // } else {
          //   alert("작성에 실패하였습니다.");
          // }
        });
      this.submitted = true;
      this.$router.push("/detailquestion/" + this.id);
    },
    onReset() {
      this.question.username = "";
      this.question.title = "";
      this.question.content = "";
      this.question.password = "";
      this.question.selected = "public";
      this.submitted = false;
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
</style>
