<template>
  <div>
    <div class="container mt-4 mb-4" v-if="!submitted">
      <h1 class="mt-4 mb-4" style="text-align:center;">Update</h1>
      <div class="container border mt-4 mb-4">
        <b-form @submit.prevent="updateNotice" @reset.prevent="onReset" class="mt-4 mb-4">
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
              v-model="notice.title"
              :state="titleState"
              placeholder="Enter title"
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
              v-model="notice.content"
              :state="contentState"
              placeholder="Enter content"
              rows="10"
              required
            ></b-form-textarea>
          </b-form-group>
          <div class="contianer mt-4 mb-4 addnoticediv">
            <div class="header-cart-wrapbtn addnoticedivright">
              <button type="submit" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">Submit</button>
            </div>
            <div class="header-cart-wrapbtn addnoticedivleft">
              <button type="reset" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4 addnotice">reset</button>
            </div>
          </div>
        </b-form>
      </div>
    </div>
    <!-- <div v-else>
      <router-link to="/noticelist"></router-link>
    </div> -->
  </div>
</template>

<script>
import http from '@/http-common'

  export default {
    name: 'update-notice',
    props: ['id'],
    data() {
      return {
        errored: true,
        loading: false,
        notice: {
          title: '',
          content: ''
        },
        submitted: false,
      }
    },
    computed: {
      titleState() {
        return this.notice.title.length >= 4 ? true : false;
      },
      contentState() {
        return this.notice.content.length >= 10 ? true : false;
      },
      invalidFeedbacktitle() {
        if (this.notice.title.length >= 4) {
          return ''
        } else if(this.notice.title.length > 0) {
          return 'Enter at least 4 characters.'
        } else if(this.notice.title.length === 0) {
          return 'Please enter something.'
        }
      },
      invalidFeedbackcontent() {
        if (this.notice.content.length >= 10) {
          return ''
        } else if(this.notice.content.length > 0) {
          return 'Enter at least 10 characters.'
        } else if(this.notice.content.length === 0) {
          return 'Please enter something.'
        }
      }
    },
    mounted() {
    http
      .get('/findNoticeByIdx/' + this.id)
      .then((response) => {
        this.notice = response.data
      })
      .catch(() => {
        this.errored = true
      })
      .finally(() => (this.loading = false))
    },
    methods: {
      updateNotice() {
        http
          .put('/updateNotice', {
            idx: this.notice.idx,
            title: this.notice.title,
            content: this.notice.content
          })
          .then((response) => {
            if (response.data.state == 'succ') {
              alert('정보를 수정 하였습니다.')
            } else {
              alert('정보를 수정 하지 못했습니다.')
            }
          })
        this.submitted = true
        this.$router.push("/detailnotice/" + this.id);
      },
      newCustomer() {
        this.submitted = false
        this.loading = true
        this.errored = false
        this.notice = {
          title: '',
          content: ''
        }
      },
    },
  }
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