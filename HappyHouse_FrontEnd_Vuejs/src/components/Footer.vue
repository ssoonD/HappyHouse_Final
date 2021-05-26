<template>
  <footer class="bg6 p-t-20 p-b-20 p-l-20 p-r-20">
    <div class="flex-w">
      <div class="foot-width-size-20 p-t-30 p-l-15 p-r-15 respon4"></div>
      <div class="foot-width-size-20 p-t-30 p-l-15 p-r-15 respon3">
        <h4 class="s-text12 p-b-30">
          Team Introduction
        </h4>

        <div>
          <p class="s-text3 w-size27">
            🙋🏻‍♂️&nbsp;팀장 &nbsp;&nbsp;김우현<br /><br />
            🙋🏻‍♀️&nbsp;팀원 &nbsp;&nbsp;권순주
          </p>
        </div>
      </div>

      <div class="foot-width-size-20 p-t-30 p-l-15 p-r-15 respon4">
        <h4 class="s-text12 p-b-30">
          Categories
        </h4>

        <router-link to="/noticelist" class="s-text7">
          Notice
        </router-link>
        <br />
        <router-link to="/questionlist" class="s-text7">
          QnA
        </router-link>
        <br />
      </div>

      <div class="foot-width-size-20 p-t-30 p-l-15 p-r-15 respon4">
        <h4 class="s-text12 p-b-30">
          Search
        </h4>

        <router-link to="/search" class="s-text7">
          House Search
        </router-link>
        <br />
        <router-link to="/hospital" class="s-text7">
          Screening Center
        </router-link>
        <br />
        <router-link to="/welfare" class="s-text7">
          Neighborhood Comparison
        </router-link>
        <br />
      </div>
      <div class="foot-width-size-20 p-t-30 p-l-15 p-r-15 respon4"></div>
    </div>

    <div class="t-center p-l-15 p-r-15">
      <div class="t-center s-text8 p-t-20">
        Copyright © 2018 All rights reserved. | This template is made with
        <i class="fa fa-heart-o" aria-hidden="true"></i> by
        <a href="https://colorlib.com" target="_blank">Colorlib</a>
      </div>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style>
.foot-width-size-10 {
  width: 10%;
}
.foot-width-size-20 {
  width: 20%;
  text-align: center;
}
</style>
