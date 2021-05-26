<template>
  <div>
    <h1 class="mt-4 mb-4" style="text-align: center;">Find Account</h1>
    <div class="container mt-4 mb-4">
      <div class="contianer mt-4 mb-4 addnoticediv">
        <div class="header-cart-wrapbtn addnoticedivright mt-4 mb-4">
          <button
            type="button"
            @click="find_id()"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            아이디 찾기
          </button>
        </div>
        <div class="header-cart-wrapbtn addnoticedivleft mt-4 mb-4">
          <button
            type="button"
            @click="find_password()"
            class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
          >
            비밀번호 찾기
          </button>
        </div>
      </div>
    </div>
    <div class="container mt-4 mb-4" v-if="idcheck" style="width: 50%;">
      <h1 class="mt-4 mb-4" style="text-align: center;">Find Id</h1>
      <b-form @submit.prevent="onIdSubmit" @reset.prevent="onReset">
        <b-form-group id="usernmae" label="Your Name:" label-for="username">
          <b-form-input
            id="usernmae"
            v-model="username"
            placeholder="Enter username"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="email" label="Your Email:" label-for="email">
          <b-form-input
            id="email"
            v-model="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>
        <div class="contianer mt-4 mb-4 addnoticediv">
          <div class="header-cart-wrapbtn addnoticedivright mt-4 mb-4">
            <button
              type="submit"
              class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
            >
              submit
            </button>
          </div>
          <div class="header-cart-wrapbtn addnoticedivleft mt-4 mb-4">
            <button
              type="reset"
              class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
            >
              reset
            </button>
          </div>
        </div>
      </b-form>
    </div>
    <div class="container mt-4 mb-4" v-else style="width: 50%;">
      <h1 class="mt-4 mb-4" style="text-align: center;">Find Password</h1>
      <b-form @submit.prevent="onPassSubmit" @reset.prevent="onReset">
        <b-form-group id="userid" label="Your Userid:" label-for="userid">
          <b-form-input
            id="userid"
            v-model="userid"
            placeholder="Enter userid"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group id="email" label="Your Email:" label-for="email">
          <b-form-input
            id="email"
            v-model="email"
            placeholder="Enter email"
            required
          ></b-form-input>
        </b-form-group>
        <div class="contianer mt-4 mb-4 addnoticediv">
          <div class="header-cart-wrapbtn addnoticedivright mt-4 mb-4">
            <button
              type="submit"
              class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
            >
              submit
            </button>
          </div>
          <div class="header-cart-wrapbtn addnoticedivleft mt-4 mb-4">
            <button
              type="reset"
              class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
            >
              reset
            </button>
          </div>
        </div>
      </b-form>
    </div>
  </div>
</template>

<script>
import emailjs from "emailjs-com";
import http from "@/http-common";

export default {
  name: "find-account",
  data() {
    return {
      member: {},
      idcheck: true,
      username: "",
      userid: "",
      email: "",
    };
  },
  created() {
    emailjs.init("user_0OrLf8T5VNn24KHN7FXFm");
  },
  mounted() {},
  methods: {
    find_id() {
      this.idcheck = true;
    },
    find_password() {
      this.idcheck = false;
    },
    async onIdSubmit() {
      const response = await http.get("/findMemberByName/" + this.username);
      this.member = response.data;

      const templateParams = {
        to_name: this.member.username,
        value: this.idcheck === true ? "Id" : "Password",
        message: this.member.userid,
        email: this.member.email,
      };

      emailjs.send("happyhouse", "template_rciwu38", templateParams).then(function() {
        alert("메일을 확인해주세요!");
      });

      this.username = "";
      this.email = "";
    },
    async onPassSubmit() {
      const response = await http.get("/findMemberById/" + this.userid);
      this.member = response.data;

      const templateParams = {
        to_name: this.member.username,
        value: this.idcheck === true ? "Id" : "Password",
        message: this.member.userpwd,
        email: this.member.email,
      };

      emailjs.send("happyhouse", "template_rciwu38", templateParams).then(function() {
        alert("메일을 확인해주세요!");
      });

      this.userid = "";
      this.email = "";
    },
    onReset() {
      this.username = "";
      this.userid = "";
      this.email = "";
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
