<template>
  <div style="height: 80vh ;width:100vw">
    <div
      class="pt-auto pb-auto"
      style="width:30%;height:15%;float:left;background-color:#423f3f"
    ></div>
    <div class="pt-auto pb-auto" style="width:40%;height:15%;float:left;background-color:#423f3f">
      <form id="myForm" style="width:100%; height:70%" class="mt-3" @submit.prevent="search">
        <div style="float:left;width:70%;height:100%">
          <select class="form-control" id="sel1" v-model="guname1" style="width:100%;height:50%">
            <option selected>구 선택</option>
            <option value="강남구">강남구</option>
            <option value="강동구">강동구</option>
            <option value="강북구">강북구</option>
            <option value="강서구">강서구</option>
            <option value="관악구">관악구</option>
            <option value="광진구">광진구</option>
            <option value="구로구">구로구</option>
            <option value="금천구">금천구</option>
            <option value="노원구">노원구</option>
            <option value="도봉구">도봉구</option>
            <option value="동대문구">동대문구</option>
            <option value="동작구">동작구</option>
            <option value="마포구">마포구</option>
            <option value="서대문구">서대문구</option>
            <option value="서초구">서초구</option>
            <option value="성동구">성동구</option>
            <option value="성북구">성북구</option>
            <option value="송파구">송파구</option>
            <option value="양천구">양천구</option>
            <option value="영등포구">영등포구</option>
            <option value="용산구">용산구</option>
            <option value="은평구">은평구</option>
            <option value="종로구">종로구</option>
            <option value="중구">중구</option>
            <option value="중랑구">중랑구</option>
          </select>
          <select class="form-control" id="sel2" v-model="guname2" style="width:100%;height:50%">
            <option selected>구 선택</option>
            <option value="강남구">강남구</option>
            <option value="강동구">강동구</option>
            <option value="강북구">강북구</option>
            <option value="강서구">강서구</option>
            <option value="관악구">관악구</option>
            <option value="광진구">광진구</option>
            <option value="구로구">구로구</option>
            <option value="금천구">금천구</option>
            <option value="노원구">노원구</option>
            <option value="도봉구">도봉구</option>
            <option value="동대문구">동대문구</option>
            <option value="동작구">동작구</option>
            <option value="마포구">마포구</option>
            <option value="서대문구">서대문구</option>
            <option value="서초구">서초구</option>
            <option value="성동구">성동구</option>
            <option value="성북구">성북구</option>
            <option value="송파구">송파구</option>
            <option value="양천구">양천구</option>
            <option value="영등포구">영등포구</option>
            <option value="용산구">용산구</option>
            <option value="은평구">은평구</option>
            <option value="종로구">종로구</option>
            <option value="중구">중구</option>
            <option value="중랑구">중랑구</option>
          </select>
        </div>

        <div style="float:left;width:30%;height:100%;">
          <button
            type="button"
            class="btn btn-secondary"
            style="width:100%;height:100%"
            @click="submit()"
          >
            SUBMIT
          </button>
        </div>
      </form>
    </div>

    <div
      class="pt-auto pb-auto"
      style="width:30%;height:15%;float:left;background-color:#423f3f"
    ></div>

    <div
      v-if="isview"
      class="row mr-auto ml-auto pb-auto"
      style="width:85%;height:100%;margin-top:200px"
    >
      <div class="col-md-3" id="ch1">
        <div class="mb-3 mt-5" style="height:7%">
          <img src="/images/sport.png" alt="" style="height:100%;float:left" />
          <div style="height:100%;float:left;">
            <h1 style="margin:auto;font-family: 'Nanum Gothic Coding', monospace; font-size:50px;">
              체육시설
            </h1>
          </div>
        </div>
        <GChart
          type="ColumnChart"
          :data="chartdata1"
          :options="chartOptions"
          style="height:55%;align-self: flex-start; border-right : 1px solid #87878a"
        />
      </div>

      <div class="col-md-3" id="ch1">
        <div class="mb-3 mt-5" style="height:7%">
          <img src="/images/theater.png" alt="" style="height:100%;float:left" />
          <div style="height:100%;float:left;">
            <h1 style="margin:auto;font-family: 'Nanum Gothic Coding', monospace; font-size:50px;">
              영화관
            </h1>
          </div>
        </div>
        <GChart
          type="ColumnChart"
          :data="chartdata2"
          :options="chartOptions"
          style="height:55%;align-self: flex-start; border-right : 1px solid #87878a"
        />
      </div>

      <div class="col-md-3" id="ch1">
        <div class="mb-3 mt-5" style="height:7%">
          <img src="/images/lib.png" alt="" style="height:100%;float:left" />
          <div style="height:100%;float:left;">
            <h1 style="margin:auto;font-family: 'Nanum Gothic Coding', monospace; font-size:50px;">
              도서관
            </h1>
          </div>
        </div>
        <GChart
          type="ColumnChart"
          :data="chartdata3"
          :options="chartOptions"
          style="height:55%;align-self: flex-start; border-right : 1px solid #87878a"
        />
      </div>

      <div class="col-md-3" id="ch1">
        <div class="mb-3 mt-5" style="height:7%">
          <img src="/images/welfarecenter.png" alt="" style="height:100%;float:left" />
          <div style="height:100%;float:left;">
            <h1 style="margin:auto;font-family: 'Nanum Gothic Coding', monospace; font-size:50px;">
              복지시설
            </h1>
          </div>
        </div>
        <GChart
          type="ColumnChart"
          :data="chartdata4"
          :options="chartOptions"
          style="height:55%;align-self: flex-start;"
        />
      </div>
    </div>
  </div>
</template>

<script>
import http from "../http-common";
import axios from "axios";
import { GChart } from "vue-google-charts";

export default {
  name: "welfare",
  data() {
    return {
      guname1: "",
      guname2: "",
      info1: {},
      info2: [],
      loading: true,
      errored: false,
      isview: false,
      change: false,
      chartdata1: [
        ["구", "체육시설", { role: "style" }],
        ["", 0, ""],
        ["", 0, ""],
      ],
      chartdata2: [
        ["구", "영화관", { role: "style" }],
        ["", 0, ""],
        ["", 0, ""],
      ],
      chartdata3: [
        ["구", "도서관", { role: "style" }],
        ["", 0, ""],
        ["", 0, ""],
      ],
      chartdata4: [
        ["구", "복지시설", { role: "style" }],
        ["", 0, ""],
        ["", 0, ""],
      ],
      chartOptions: {
        chart: {
          //title: "Company Performance",
          //subtitle: "Sales, Expenses, and Profit: 2014-2017",
        },
        hAxis: { title: "", titleTextStyle: { color: "gray" } },
        colors: ["#87CEEB", "#6600CC", "#0000CC", "#000000"],
        chartArea: { top: 30, left: 30, bottom: 30 },
        hAxis: {
          textStyle: {
            fontSize: 18, // or the number you want
            fontName: "Nanum Gothic Coding",
          },
        },
      },
    };
  },
  components: {
    GChart,
  },
  methods: {
    async submit(e) {
      this.isview = false;
      await http
        .get("/searchByGuName/" + this.guname1)
        .then((response) => (this.info1 = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));

      await http
        .get("/searchByGuName/" + this.guname2)
        .then((response) => (this.info2 = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));

      this.chartdata1[1][0] = this.guname1;
      console.log(this.info1.sports);
      console.log(this.info2.sports);
      this.chartdata1[1][1] = this.info1.sports;
      this.chartdata1[1][2] = "#87CEEB";
      this.chartdata1[2][0] = this.guname2;
      this.chartdata1[2][1] = this.info2.sports;
      this.chartdata1[2][2] = "#808080";

      this.chartdata2[1][0] = this.guname1;
      this.chartdata2[1][1] = this.info1.theater;
      this.chartdata2[1][2] = "#87CEEB";
      this.chartdata2[2][0] = this.guname2;
      this.chartdata2[2][1] = this.info2.theater;
      this.chartdata2[2][2] = "#808080";

      this.chartdata3[1][0] = this.guname1;
      this.chartdata3[1][1] = this.info1.lib;
      this.chartdata3[1][2] = "#87CEEB";
      this.chartdata3[2][0] = this.guname2;
      this.chartdata3[2][1] = this.info2.lib;
      this.chartdata3[2][2] = "#808080";

      this.chartdata4[1][0] = this.guname1;
      this.chartdata4[1][1] = this.info1.welfarecenter;
      this.chartdata4[1][2] = "#87CEEB";
      this.chartdata4[2][0] = this.guname2;
      this.chartdata4[2][1] = this.info2.welfarecenter;
      this.chartdata4[2][2] = "#808080";
      this.change = !this.change;
    },
  },
  watch: {
    change() {
      console.log("dd");
      this.isview = true;
    },
  },
  updated() {},
};
</script>
<style></style>
