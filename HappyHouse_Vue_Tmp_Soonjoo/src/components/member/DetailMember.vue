<template>
  <div class="container-table-cart pos-relative">
    <div class="wrap-table-shopping-cart bgwhite">
      <h1 class="mt-4 mb-4" style="text-align:center;">{{ member.userid }} 님의 MyPage</h1>
      <div class="addnoticediv">
      <table class="table-shopping-cart" style="width: 60%;">
          <tr class="table-head">
            <th class="notice-1"></th>
            <th class="notice-2"></th>
            <th class="notice-3"></th>
          </tr>

          <template>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">UserId</td>
              <td></td>
              <td>{{member.userid}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">UserName</td>
              <td></td>
              <td>{{member.username}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">PassWord</td>
              <td></td>
              <td>{{member.userpwd}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">Email</td>
              <td></td>
              <td>{{member.email}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">Number</td>
              <td></td>
              <td>{{member.number}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">Address</td>
              <td></td>
              <td>{{member.address}}</td>
            </tr>
            <tr class="table-row">
              <td style="text-align:right; font-weight: bold;">JoinDate</td>
              <td></td>
              <td>{{member.joindate}}</td>
            </tr>
          </template>
        </table>
      </div>
      <div class="contianer mt-4 mb-4 addnoticediv">
          <div class="header-cart-wrapbtn addnoticedivright">
            <button type="button" @click="show_detail(member.userid)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">정보 수정하기</button>
          </div>
          <!-- <div class="header-cart-wrapbtn addnoticedivleft">
            <button type="button" @click="delete_member(member.userid)" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">탈퇴하기</button>
          </div> -->
          <div class="header-cart-wrapbtn addnoticedivleft">
            <button type="button" v-b-modal.modal-prevent-closing class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">탈퇴하기</button>
          </div>
      </div>
    </div>
      <b-modal
        id="modal-prevent-closing"
        ref="modal"
        title="탈퇴를 위해 비밀번호를 입력해주세요"
        @show="resetModal"
        @hidden="resetModal"
        @ok="handleOk"
      >
        <form ref="form" @submit.stop.prevent="delete_member">
          <b-form-group
            label="PassWord"
            label-for="password-input"
          >
            <b-form-input
              id="password-input"
              v-model="password"
              required
            ></b-form-input>
          </b-form-group>
        </form>
      </b-modal>
  </div>
</template>

<script>
import http from "@/http-common";
import { mapState } from 'vuex';

export default {
  name: "detail-member",
  props: ["id"],
  data() {
    return {
      member: {},
      loading: true,
      errored: false,
      password: ''
    };
  },
  filters: {
    salarydecimal(value) {
      var a = parseInt(value);
      return a.toFixed(2);
    }
  },
  methods: {
    resetModal() {
      this.password = ''
    },
    handleOk(bvModalEvt) {
      bvModalEvt.preventDefault()
      this.delete_member()
    },
    show_detail: function(userid) {
      alert(userid + "수정하기");
      //아래를 수정했다.
      this.$router.push("/updatemember/" + userid);
    },
    show_init: function() {
      http
        .get("/findMemberById/" + this.id)
        .then(response => (this.member = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    delete_member() {
      this.$nextTick(() => {
        this.$bvModal.hide('modal-prevent-closing')
      })
      alert(this.password);
      if(this.password === this.member.userpwd || (this.loginmember.userid === 'admin' && this.password === 'admin')) {
        alert(this.member.userid + "를 삭제합니다.");
        http
          .delete("/deleteMember/" + this.member.userid)
          .then(response => {
            if (response.data.state == "succ") {
              alert("삭제를 성공했습니다.");
            } else {
              alert("삭제를 실패했습니다.");
            }
          })
          .catch(() => {
            this.errored = true;
          })
          .finally(() => (this.loading = false));
          if(this.loginmember.userid !== 'admin') {
            this.$store.dispatch('member/logoutMember');
            this.$router.push("/");
          } else {
            this.$router.push("/memberlist");
          }
      } else {
        alert('비밀번호를 다시 입력해주세요!');
      }
    },
  },
  mounted() {
    this.show_init();
  },
  computed: {
    ...mapState('member', {
            loginmember: state => state.member
    }),
    noticecontent: function() {
      return this.notice.content.replace(/\n/g, '<br />');
    }
  }
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
    width: 45%;
  }

  .notice-2 {
    width: 10%;
  }

  .notice-3 {
    width: 45%;
  }

  #modal-prevent-closing___BV_modal_outer_ {
    display:flex; 
    align-items:center;
    justify-content:center;
  }

  #modal-prevent-closing___BV_modal_content_{
    display:flex; 
    align-items:center;
    justify-content:center;
  }

  .modal-dialog modal-md {
    display:flex; 
    align-items:center;
    justify-content:center;
  }

  #modal-prevent-closing___BV_modal_content_ {
    display:flex; 
    align-items:center;
    justify-content:center;
  }
</style>