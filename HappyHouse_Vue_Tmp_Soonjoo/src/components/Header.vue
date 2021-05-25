<template>
    <header class="header1">
        <!-- Header desktop -->
        <div class="container-menu-header">
            <div class="topbar">
                <div class="topbar-social">
                  <!-- SNS -->
                </div>

                <span class="topbar-child1" v-if="isLogin">
					환영합니다. {{ loginmember.username }}님!
				</span>

                <div class="topbar-child2">
					<span class="topbar-email">
						{{ loginmember.email }}
					</span>

                    <div class="topbar-language rs1-select2">
                      <!-- 언어 -->
                    </div>
                </div>
            </div>

            <div class="wrap_header">
                <!-- Logo -->
                <a href="index.html" class="logo">
                    <img src="images/icons/happyhouselogo.png" alt="IMG-LOGO">
                </a>

                <!-- Menu -->
                <div class="wrap_menu">
                    <nav class="menu">
                        <ul class="main_menu">
                            <router-link :to="{name: 'home'}" tag="li" active-class="sale-noti" exact>
                                <a>Home</a>
                            </router-link>

                            <router-link :to="{name: 'questionlist'}" tag="li" active-class="sale-noti" exact>
                                <a>Shop</a>
                            </router-link>

                            <router-link :to="{name: 'noticelist'}" tag="li" active-class="sale-not" exact>
                                <a>Notice</a>
                            </router-link>
                        </ul>
                    </nav>
                </div>

                <!-- Header Icon -->
                <div class="header-icons">
                    <!-- <a href="#" class="header-wrapicon1 dis-block">
                        <img src="images/icons/icon-header-01.png" class="header-icon1" alt="ICON">
                    </a>

                    <span class="linedivide1"></span> -->

                    <div class="header-wrapicon2">
                        <img src="images/icons/icon-header-01.png" class="header-icon1 js-show-header-dropdown" alt="ICON">
                        <!-- <span class="header-icons-noti">{{ totalCartQty }}</span> -->

                        <!-- Header cart noti -->
                        <div class="header-cart header-dropdown">
                          <!-- 로그인 하기 전 -->
                            <div id="login" v-if="checklogin()">
                              <div class="login_title_warp">
                                <h2 class="mt-4 mb-4" style="text-align:center;">Login</h2>
                              </div>
                              <div id="login_wrap">
                                <form class="login" @submit.prevent="login">
                                  <b-input-group class="mb-2">
                                    <b-input-group-prepend is-text>
                                      <b-icon icon="person-fill"></b-icon>
                                    </b-input-group-prepend>
                                    <input
                                      type="text"
                                      id="_userid"
                                      name="userid"
                                      value
                                      data-msg="ID를"
                                      size="30"
                                      title="아이디"
                                      required
                                      v-model="userid"
                                      placeholder=" Enter your ID."
                                      style="border: 1px solid #dddddd;"
                                    />
                                  </b-input-group>
                                  <b-input-group class="mb-2">
                                    <b-input-group-prepend is-text>
                                      <b-icon icon="lock-fill"></b-icon>
                                    </b-input-group-prepend>
                                    <input
                                      type="password"
                                      id="_userpwd"
                                      name="userpwd"
                                      value
                                      required
                                      v-model="userpwd"
                                      placeholder=" Enter your Password."
                                      size="30"
                                      title="패스워드"
                                      style="border: 1px solid #dddddd;"
                                    />
                                  </b-input-group>
                                  <div class="header-cart-buttons mt-4">
                                    <div class="header-cart-wrapbtn">
                                      <button type="submit" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Login</button>
                                        <!-- <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Login
                                        </a> -->
                                    </div>
                                    <div class="header-cart-wrapbtn">
                                      <router-link to="/addmember" style="text-decoration:none" class="button flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Join</router-link>
                                        <!-- <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Join
                                        </a> -->
                                    </div>
                                  </div>
                                  <div class="mt-4" style="text-align: center;">
                                    <router-link to="/findpassword" style="text-decoration:none;">Forgot password ?</router-link>
                                  </div>
                                </form>
                              </div>
                            </div>
                            <!-- 로그인 성공 후 -->
                            <div id="logout" v-else>
                              <div class="logout_title_warp">
                                  <h2 class="mt-4 mb-4" style="text-align:center;">{{member.username}} 님</h2>
                              </div>
                              <!-- 일반 사용자 -->
                              <div id="logout_wrap" v-if="!admin">
                                <form class="logout" @submit.prevent="logout">
                                  <div class="header-cart-buttons mt-4">
                                    <div class="header-cart-wrapbtn">
                                      <button type="submit" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Logout</button>
                                        <!-- <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Login
                                        </a> -->
                                    </div>

                                    <div class="header-cart-wrapbtn">
                                      <button type="button" @click="show_detail(member.userid)" class="button flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Mypage</button>
                                        <!-- <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Join
                                        </a> -->
                                    </div>
                                  </div>
                                </form>
                              </div>
                              <!-- 관리자 -->
                              <div id="logout_wrap" v-else>
                                <form class="logout" @submit.prevent="logout">
                                  <div class="header-cart-buttons mt-4">
                                    <div class="header-cart-wrapbtn">
                                      <button type="submit" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">Logout</button>
                                        <!-- <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Login
                                        </a> -->
                                    </div>

                                    <div class="header-cart-wrapbtn">
                                      <button type="button" @click="show_list" class="button flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">UserList</button>
                                        <!-- <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                            Join
                                        </a> -->
                                    </div>
                                  </div>
                                </form>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Header Mobile -->
        <div class="wrap_header_mobile">
            <!-- Logo moblie -->
            <a href="index.html" class="logo-mobile">
                <img src="images/icons/logo.png" alt="IMG-LOGO">
            </a>

            <!-- Button show menu -->
            <div class="btn-show-menu">
                <!-- Header Icon mobile -->
                <div class="header-icons-mobile">
                    <a href="#" class="header-wrapicon1 dis-block">
                        <img src="images/icons/icon-header-01.png" class="header-icon1" alt="ICON">
                    </a>

                    <span class="linedivide2"></span>

                    <div class="header-wrapicon2">
                        <img src="images/icons/icon-header-02.png" class="header-icon1 js-show-header-dropdown" alt="ICON">
                        <span class="header-icons-noti">{{ totalCartQty }}</span>

                        <!-- Header cart noti -->
                        <div class="header-cart header-dropdown">
                            <ul class="header-cart-wrapitem">
                                <template v-for="product in cartItems">
                                    <li class="header-cart-item">
                                        <div class="header-cart-item-img">
                                            <img :src="product.image" alt="IMG">
                                        </div>

                                        <div class="header-cart-item-txt">
                                            <router-link to="/" class="header-cart-item-name">
                                                {{ product.title }}
                                            </router-link>

                                            <span class="header-cart-item-info">
                                              {{ product.qty }} x ${{ product.price }}
                                            </span>
                                        </div>
                                    </li>
                                </template>
                            </ul>

                            <div class="header-cart-total">
                                Total: ${{ totalCartPrice }}
                            </div>

                            <div class="header-cart-buttons">
                                <div class="header-cart-wrapbtn">
                                    <!-- Button -->
                                    <a href="cart.html" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                        View Cart
                                    </a>
                                </div>

                                <div class="header-cart-wrapbtn">
                                    <!-- Button -->
                                    <a href="#" class="flex-c-m size1 bg1 bo-rad-20 hov1 s-text1 trans-0-4">
                                        Check Out
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
					<span class="hamburger-box">
						<span class="hamburger-inner"></span>
					</span>
                </div>
            </div>
        </div>

        <!-- Menu Mobile -->
        <div class="wrap-side-menu" >
            <nav class="side-menu">
                <ul class="main-menu">
                    <li class="item-topbar-mobile p-l-20 p-t-8 p-b-8">
						<span class="topbar-child1">
							Free shipping for standard order over $100
						</span>
                    </li>

                    <li class="item-topbar-mobile p-l-20 p-t-8 p-b-8">
                        <div class="topbar-child2-mobile">
							<span class="topbar-email">
								fashe@example.com
							</span>

                            <div class="topbar-language rs1-select2">
                                <select class="selection-1" name="time">
                                    <option>USD</option>
                                    <option>EUR</option>
                                </select>
                            </div>
                        </div>
                    </li>

                    <li class="item-topbar-mobile p-l-10">
                        <div class="topbar-social-mobile">
                            <a href="#" class="topbar-social-item fa fa-facebook"></a>
                            <a href="#" class="topbar-social-item fa fa-instagram"></a>
                            <a href="#" class="topbar-social-item fa fa-pinterest-p"></a>
                            <a href="#" class="topbar-social-item fa fa-snapchat-ghost"></a>
                            <a href="#" class="topbar-social-item fa fa-youtube-play"></a>
                        </div>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="index.html">Home</a>
                        <ul class="sub-menu">
                            <li><a href="index.html">Homepage V1</a></li>
                            <li><a href="home-02.html">Homepage V2</a></li>
                            <li><a href="home-03.html">Homepage V3</a></li>
                        </ul>
                        <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="product.html">Shop</a>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="product.html">Sale</a>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="cart.html">Features</a>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="blog.html">Blog</a>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="about.html">About</a>
                    </li>

                    <li class="item-menu-mobile">
                        <a href="contact.html">Contact</a>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
</template>

<script>
import http from '../http-common'
import { mapState } from 'vuex';

    export default {
        name: 'login',
        data() {
          return {
            member: {},
            userid: '',
            userpwd: '',
            submitted: false,
            admin: false
          }
        },
        computed: {
          ...mapState('member', {
            loginmember: state => state.member,
            isLogin: state => state.isLogin
          })
        },
        methods: {
          login() {
            http
            .get('/findMemberById/' + this.userid)
            .then((response) => {
              this.member = response.data;
              if (this.member.userpwd === this.userpwd) {
                alert('로그인에 성공하였습니다.');
                this.username = this.member.username;
                this.email = this.member.email;
                this.submitted = true;
                this.userid = '';
                this.userpwd = '';
                this.$store.dispatch('member/loginMember', this.member);
                if(this.member.role === 1) {
                  this.admin = true;
                } else {
                  this.admin = false;
                }
              } else {
                alert('로그인에 실패하였습니다.')
              }
            })
          },
          logout() {
            this.member = {}
            this.userid = ''
            this.userpwd = ''
            this.submitted = false;
            this.$store.dispatch('member/logoutMember');
            this.$router.push("/");
          },
          show_detail(userid) {
            alert(userid + "상세보기");
            this.$router.push("/detailmember/" + userid);
          },
          show_list() {
            this.$router.push("/memberlist");
          },
          checklogin() {
            return !this.submitted || !this.isLogin;
          }
        }
    }
</script>