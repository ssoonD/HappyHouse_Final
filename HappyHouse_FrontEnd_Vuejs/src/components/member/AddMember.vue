<template>
  <div>
    <div class="container mt-4 mb-4" v-if="!submitted" style="width: 70%;">
      <h1 class="mt-4 mb-4" style="text-align:center;">Join</h1>
      <div class="container border mt-4 mb-4">
        <b-form @submit.prevent="addMember" @reset.prevent="onReset" class="mt-4 mb-4">
          <b-form-group
            id="userid"
            label="Your Userid"
            label-for="userid"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackuserid"
            :state="useridState"
          >
            <b-form-input
              id="userid"
              v-model="member.userid"
              :state="useridState"
              placeholder="Your Userid"
              trim
            ></b-form-input>
          </b-form-group>
          <div class="contianer mt-4 mb-4 addnoticediv">
            <button
              type="button"
              class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice"
              @click="sameid"
            >
              아이디 중복 확인
            </button>
          </div>
          <b-form-group
            id="username"
            label="Your Username"
            label-for="username"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackusername"
            :state="usernameState"
          >
            <b-form-input
              id="username"
              v-model="member.username"
              :state="usernameState"
              placeholder="Your Username"
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="userpwd"
            label="Your Password"
            label-for="userpwd"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackuserpwd"
            :state="userpwdState"
          >
            <b-form-input
              id="userpwd"
              type="password"
              v-model="member.userpwd"
              :state="userpwdState"
              placeholder="Your Password"
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="userpwdcheck"
            label="Your Password Check"
            label-for="userpwdcheck"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackuserpwdcheck"
            :state="userpwdcheckState"
          >
            <b-form-input
              id="userpwdcheck"
              type="password"
              v-model="userpwdcheck"
              :state="userpwdcheckState"
              placeholder="Your Password Check. Please enter the same value."
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="email"
            label="Your Email"
            label-for="email"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackemail"
            :state="emailState"
          >
            <b-form-input
              id="email"
              v-model="member.email"
              :state="emailState"
              placeholder="example@example.com"
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="number"
            label="Your Number"
            label-for="number"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbacknumber"
            :state="numberState"
          >
            <b-form-input
              id="number"
              v-model="member.number"
              :state="numberState"
              placeholder="000-0000-0000"
              trim
            ></b-form-input>
          </b-form-group>
          <b-form-group
            id="address"
            label="Your Address"
            label-for="address"
            valid-feedback="Thank you!"
            :invalid-feedback="invalidFeedbackaddress"
            :state="addressState"
          >
            <b-form-input
              id="address"
              v-model="member.address"
              :state="addressState"
              placeholder="Your Address"
              trim
            ></b-form-input>
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
      </div>
    </div>
    <!-- <div v-else>
      <router-link to="/"></router-link>
    </div> -->
  </div>
</template>

<script>
import http from "@/http-common";

export default {
  name: "add-member",
  data() {
    return {
      member: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        number: "",
        address: "",
      },
      loading: true,
      errored: false,
      userpwdcheck: "",
      submitted: false,
    };
  },
  computed: {
    useridState() {
      let userIdCheck = /^[A-Za-z0-9_\-]{5,20}$/;
      return this.member.userid.match(userIdCheck) !== null;
    },
    invalidFeedbackuserid() {
      let userIdCheck = /^[A-Za-z0-9_\-]{5,20}$/;
      if (this.member.userid.match(userIdCheck) !== null) {
        return "";
      } else {
        return "영어 대소문자, 숫자, 5~20자리";
      }
    },
    usernameState() {
      let nameCheck = /^[가-힣a-zA-Z0-9]{2,10}$/;
      return this.member.username.match(nameCheck) !== null;
    },
    invalidFeedbackusername() {
      let nameCheck = /^[가-힣a-zA-Z0-9]{2,10}$/;
      if (this.member.username.match(nameCheck) !== null) {
        return "";
      } else {
        return "한글과 영어, 숫자, 2~10자리";
      }
    },
    userpwdState() {
      let passwdCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/;
      return this.member.userpwd.match(passwdCheck) !== null;
    },
    invalidFeedbackuserpwd() {
      let passwdCheck = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/;
      if (this.member.userpwd.match(passwdCheck) !== null) {
        return "";
      } else {
        return "영문 대문자와 소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자리";
      }
    },
    userpwdcheckState() {
      return this.userpwdcheck.length !== 0 && this.userpwdcheck === this.member.userpwd
        ? true
        : false;
    },
    invalidFeedbackuserpwdcheck() {
      if (this.userpwdcheck.length === 0) {
        return "입력하신 비밀번호와 같지 않습니다";
      } else if (this.userpwdcheck === this.member.userpwd) {
        return "";
      } else {
        return "입력하신 비밀번호와 같지 않습니다";
      }
    },
    emailState() {
      let emailCheck = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
      return this.member.email.match(emailCheck) !== null;
    },
    invalidFeedbackemail() {
      let emailCheck = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
      if (this.member.email.match(emailCheck) !== null) {
        return "";
      } else {
        return "이메일 형식에 맞게 입력해주세요";
      }
    },
    numberState() {
      let phonNumberCheck = /^\d{3}-\d{3,4}-\d{4}$/;
      return this.member.number.match(phonNumberCheck) !== null;
    },
    invalidFeedbacknumber() {
      let phonNumberCheck = /^\d{3}-\d{3,4}-\d{4}$/;
      if (this.member.number.match(phonNumberCheck) !== null) {
        return "";
      } else {
        return "번호 형식에 맞게 입력해주세요";
      }
    },
    addressState() {
      return this.member.address.length >= 10;
    },
    invalidFeedbackaddress() {
      if (this.member.address.length >= 10) {
        return "";
      } else {
        return "형식에 맞게 입력해주세요";
      }
    },
  },
  methods: {
    addMember() {
      http
        .post("/addMember", {
          userid: this.member.userid,
          username: this.member.username,
          userpwd: this.member.userpwd,
          email: this.member.email,
          number: this.member.number,
          address: this.member.address,
        })
        .then((response) => {
          // if (response.data.state == 'succ') {
          //   alert('회원 가입에 성공하였습니다.')
          // } else {
          //   alert('회원 가입에 실패하였습니다.')
          // }
          this.submitted = true;
          this.$router.push("/");
        });
    },
    onReset() {
      this.member.userid = "";
      this.member.username = "";
      this.member.userpwd = "";
      this.member.email = "";
      this.member.number = "";
      this.member.address = "";
      this.submitted = false;
    },
    async sameid() {
      const response = await http.get("/findMemberById/" + this.member.userid);
      const tmpmember = response.data;
      if (tmpmember.userid === this.member.userid) {
        alert("이미 사용중인 아이디입니다!\n다른 아이디를 선택해주세요!");
      } else {
        alert("사용 가능한 아이디입니다!");
      }
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
