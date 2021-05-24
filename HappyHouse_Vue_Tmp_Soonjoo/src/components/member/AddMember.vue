<template>
  <div>
    <div class="container border mt-4 mb-4" v-if="!submitted">
      <h1 class="mt-4 mb-4" style="text-align:center;">Join</h1>
      <div class="container border mt-4 mb-4">
        <b-form @submit.prevent="addMember" @reset.prevent="onReset">
          <b-form-group id="userid" label="Your userid:" label-for="userid">
            <b-form-input
              id="userid"
              v-model="member.userid"
              placeholder="Enter userid"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="username" label="Your username:" label-for="username">
            <b-form-input
              id="username"
              v-model="member.username"
              placeholder="Enter username"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="userpwd" label="Your userpwd:" label-for="userpwd">
            <b-form-input
              id="userpwd"
              v-model="member.userpwd"
              placeholder="Enter userpwd"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="email" label="Your email:" label-for="email">
            <b-form-input
              id="email"
              v-model="member.email"
              placeholder="Enter email"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="number" label="Your number:" label-for="number">
            <b-form-input
              id="number"
              v-model="member.number"
              placeholder="Enter number"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group id="address" label="Your address:" label-for="address">
            <b-form-input
              id="address"
              v-model="member.address"
              placeholder="Enter address"
              required
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </div>
    </div>
    <div v-else>
      <router-link to="/"></router-link>
    </div>
  </div>
</template>

<script>
import http from '@/http-common'

  export default {
    name: 'add-member',
    data() {
      return {
        member: {
          userid: '',
          username: '',
          userpwd: '',
          email: '',
          number: '',
          address: ''
        },
        submitted: false,
      }
    },
    methods: {
      addMember() {
        http
        .post('/addMember', {
          userid: this.member.userid,
          username: this.member.username,
          userpwd: this.member.userpwd,
          email: this.member.email,
          number: this.member.number,
          address: this.member.address
        })
        .then((response) => {
          if (response.data.state == 'succ') {
            alert('회원 가입에 성공하였습니다.')
          } else {
            alert('회원 가입에 실패하였습니다.')
          }
          this.submitted = true
        })
      },
      onReset() {
        this.member.userid = ''
        this.member.username = ''
        this.member.userpwd = ''
        this.member.email = ''
        this.member.number = ''
        this.member.address = ''
        this.submitted = false
      }
    }
  }
</script>