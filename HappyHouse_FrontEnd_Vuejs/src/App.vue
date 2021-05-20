<template>
  <div class="boxwrap">
    <div class="box" style="background-color:red;">1</div>
    <div class="box" style="background-color:orange;">2</div>
    <div class="box" style="background-color:yellow;">3</div>
    <div class="box" style="background-color:green;">4</div>
    <div class="box" style="background-color:blue;">5</div>
    <div class="box" style="background-color:indigo;">6</div>
    <div class="box" style="background-color:violet;">7</div>
    </div>
</template>

<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

</style>

<style type="text/css">
        html,body{ margin:0; padding:0; width:100%; height:100%;}
        .boxwrap{ display: table; table-layout: fixed; width: 700%; height: 100%; table-layout: fixed;}
        .box{ display: table-cell;position:relative; color:#ffffff; font-size:24pt;}
</style>

