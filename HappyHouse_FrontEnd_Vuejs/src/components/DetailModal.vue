<template>
  <div class="modal">
    <div class="overlay" @click="$emit('close-modal')"></div>
    <div class="modal-card" style="margin-top : 120px">
      <div style="width:100%;height:40vh;">
        <div style="width:100% ; height:20%; background-color:gray ;margin-bottom:50px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:56px; font-weight:bold;float:left ;margin :auto; color:white"
          >
            실거래가 정보
          </h3>
        </div>
        <div style="float:left;width:50%;height:100%; border:3px solid gray">
          <img src="/images/icons/happyhouselogo.png" alt="" style="width:33%;padding:10px" />
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:52px; font-weight:bold ;margin :auto;"
            class="mb-5 mt-3 ml-3"
          >
            {{ aptName }}
          </h3>
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; margin :auto; font-weight:bold"
            class="mt-5 ml-3"
          >
            지번주소
          </h3>
          <h3
            id="ad1"
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; margin :auto; color:gray"
            class="mb-4 ml-3"
          ></h3>
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; margin :auto; font-weight:bold;display:block"
            class="ml-3"
          >
            도로명주소
          </h3>
          <h3
            id="ad2"
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;margin :auto; color:gray"
            class="ml-3"
          ></h3>
          <slot />
        </div>
        <div style="float:left;width:50%;height:100%;">
          <div style="height:100%" id="roadview"></div>
        </div>
      </div>
      <br />
      <p></p>
      <h2 style="font-family: 'Nanum Gothic Coding', monospace; font-size:36px;margin-top:20px;">
        <p></p>
        <p></p>
        <p style="font-family: 'Nanum Gothic Coding', monospace; font-size:36px;margin-top:20px;">
          거래내역
        </p>
      </h2>
      <table
        class="table table-hover"
        style="font-family: 'Nanum Gothic Coding', monospace; font-size:24px"
      >
        <thead>
          <tr>
            <th>체결일자</th>
            <th>아파트이름</th>
            <th>전용면적(㎡)</th>
            <th>층</th>
            <th>거래가(만원)</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="apt in info" :key="apt.no">
            <td v-html="apt.dealYear + '-' + apt.dealMonth + '-' + apt.dealDay"></td>
            <td v-html="apt.aptName"></td>
            <td v-html="apt.area"></td>
            <td v-html="apt.floor"></td>
            <td v-html="apt.dealAmount"></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div></div>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  props: {
    aptName: "",
    latP: "",
    lngP: "",
  },
  data() {
    return {
      upHere: false,
      info: [],
      loading: true,
      errored: false,
      update: false,
    };
  },
  method: {},
  created() {},
  mounted() {
    http
      .get("/dealInfo/" + this.aptName)
      .then((response) => (this.info = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));

    //로드뷰
    var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
    var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

    var position = new kakao.maps.LatLng(this.latP, this.lngP);

    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 50, function(panoId) {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
    //로드뷰

    //주소찾기

    let lat = this.latP;
    let lng = this.lngP;
    let geocoder = new kakao.maps.services.Geocoder();
    getAddr(lat, lng);
    function getAddr(lat, lng) {
      let coord = new kakao.maps.LatLng(lat, lng);
      let callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          $("#ad1").html(result[0].address.address_name);
          if (result[0].road_address.address_name != null) {
            $("#ad2").html(result[0].road_address.address_name); //도로명
          }
        }
      };

      geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
    }
    //주소찾기
  },
  updated() {},
};
</script>
<style>
/* Modal */
.modal,
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
}
.overlay {
  opacity: 0.5;
  background-color: black;
}
.modal-card {
  width: 100vh;
  position: relative;
  max-width: 80%;
  margin: auto;
  margin-top: 30px;
  padding: 20px;
  background-color: white;
  min-height: 500px;
  z-index: 10;
  opacity: 1;
}
</style>
