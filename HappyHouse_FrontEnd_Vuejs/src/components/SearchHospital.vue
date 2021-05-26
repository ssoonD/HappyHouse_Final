<template>
  <div class="row" style="height: 80vh;width:100vw">
    <div class="col-md-2 p-0" style="height:100%">
      <div class="row row mr-auto ml-auto pt-auto pb-auto" style="width:85%;height:20%">
        <div class="mt-auto mb-auto" style="width:100%;height:50%">
          <button class="btn btn-secondary" style="float:left;width:25%;height:100%">
            주소
          </button>
          <form
            id="myForm"
            style="width:75%;float:left;height:100%"
            class="mt-auto mb-auto"
            @submit.prevent="search"
          >
            <select class="form-control" id="sel1" style="width:100%;height:50%;">
              <option selected>시</option>
              <option>서울특별시</option>
            </select>
            <select class="form-control" id="sel2" v-model="gugun" style="width:100%;height:50%">
              <option selected>군/구</option>
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
          </form>
        </div>
      </div>

      <div class="row mr-auto ml-auto" style="width:85%; height:80% ;overflow-y: auto;z-index:1">
        <div
          v-for="hos in info"
          class="mb-3"
          :key="hos.name"
          style="width:100%; border-bottom: 1px solid gray"
        >
          <h3
            v-html="hos.name"
            style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight:bold"
          ></h3>
          <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:20px;">
            {{ hos.address }}
          </h3>
          <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:20px;">
            Tel : {{ hos.num }}
          </h3>
          <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:12px; color:#b5b5b5">
            평일 진료시간 : {{ hos.week }}
          </h3>
          <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:12px; color:#b5b5b5">
            토요일 진료시간 : {{ hos.saturday }}
          </h3>
          <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:12px; color:#b5b5b5">
            일요일/공휴일 진료시간 : {{ hos.sunday }}
          </h3>
        </div>
      </div>
    </div>
    <!-- 검색-->

    <!-- 맵시작-->
    <div class="col-md-6 p-0" style="height:100%">
      <div id="map" style="width:100%;height:100%;float:left"></div>
    </div>
    <!-- 맵끝-->
    <!-- 코로나정보시작-->

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '종로구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataJONGNO"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JONGNOADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JONGNO._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].JONGNO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].JONGNO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].JONGNO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].JONGNO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].JONGNO._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '강남구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGANGNAM"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGNAMADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGNAM._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GANGNAM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GANGNAM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GANGNAM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GANGNAM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGNAM._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '중구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataJUNGGU"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGGUADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGGU._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].JUNGGU._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].JUNGGU._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].JUNGGU._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].JUNGGU._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGGU._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '용산구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataYONGSAN"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YONGSANADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YONGSAN._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].YONGSAN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].YONGSAN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].YONGSAN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].YONGSAN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].YONGSAN._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '성동구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataSEONGDONG"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGDONGADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGDONG._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].SEONGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].SEONGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].SEONGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].SEONGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGDONG._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '광진구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGWANGJIN"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANGJINADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANGJIN._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GWANGJIN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GWANGJIN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GWANGJIN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GWANGJIN._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANGJIN._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '동대문구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataDDM"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DDMADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DDM._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].DDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].DDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].DDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].DDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].DDM._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '중랑구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataJUNGNANG"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGNANGADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGNANG._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].JUNGNANG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].JUNGNANG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].JUNGNANG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].JUNGNANG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].JUNGNANG._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '성북구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataSEONGBUK"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGBUKADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGBUK._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].SEONGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].SEONGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].SEONGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].SEONGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].SEONGBUK._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '강북구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGANGBUK"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGBUKADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGBUK._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GANGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GANGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GANGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GANGBUK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGBUK._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '도봉구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataDOBONG"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DOBONGADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DOBONG._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].DOBONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].DOBONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].DOBONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].DOBONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].DOBONG._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '노원구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataNOWON"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].NOWONADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].NOWON._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].NOWON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].NOWON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].NOWON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].NOWON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].NOWON._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '은평구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataEP"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].EPADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].EP._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].EP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].EP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].EP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].EP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].EP._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '서대문구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataSDM"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SDMADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SDM._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].SDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].SDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].SDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].SDM._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].SDM._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '마포구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataMAPO"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].MAPOADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].MAPO._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].MAPO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].MAPO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].MAPO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].MAPO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].MAPO._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '양천구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataYANGCHEON"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YANGCHEONADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YANGCHEON._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].YANGCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].YANGCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].YANGCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].YANGCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].YANGCHEON._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '강서구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGANGSEO"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGSEOADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGSEO._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GANGSEO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GANGSEO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GANGSEO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GANGSEO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGSEO._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '구로구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGURO"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GUROADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GURO._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GURO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GURO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GURO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GURO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GURO._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '금천구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGEUMCHEON"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GEUMCHEONADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GEUMCHEON._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GEUMCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GEUMCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GEUMCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GEUMCHEON._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GEUMCHEON._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '영등포구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataYDP"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YDPADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].YDP._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].YDP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].YDP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].YDP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].YDP._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].YDP._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '동작구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataDONGJAK"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DONGJAKADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].DONGJAK._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].DONGJAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].DONGJAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].DONGJAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].DONGJAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].DONGJAK._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '관악구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGWANAK"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANAKADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANAK._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GWANAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GWANAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GWANAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GWANAK._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GWANAK._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '서초구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataSEOCHO"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEOCHOADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SEOCHO._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].SEOCHO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].SEOCHO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].SEOCHO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].SEOCHO._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].SEOCHO._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '송파구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataSONGPA"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SONGPAADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].SONGPA._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].SONGPA._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].SONGPA._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].SONGPA._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].SONGPA._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].SONGPA._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="col-md-4 p-0" style="height:100%" v-if="gugun === '강동구'">
      <div style="background-color:#b5b5b5 ; width:20%;height:14%;float:left">
        <img src="/images/corona.png" alt="" style="width:100%;height:100%" />
      </div>
      <div style="background-color:#b5b5b5 ; width:80%;height:14%;float:left;padding-left:10px">
        <h3
          style="font-family: 'Nanum Gothic Coding', monospace; font-size:32px; font-weight: bold;"
          class="pt-3"
        >
          {{ gugun }}확진자 정보
        </h3>
        <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px;">
          {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text }} 기준
        </h3>
      </div>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:18px; color:#b5b5b5">
        &nbsp;
      </h3>
      <h3 style="font-family: 'Nanum Gothic Coding', monospace; font-size:28px; color:#b5b5b5">
        최근 5일 변동 추이
      </h3>
      <GChart
        class="mb-2"
        style="height:35%"
        type="LineChart"
        :data="chartDataGANGDONG"
        :options="chartOptions"
      />
      <div style="float:left;width:100%;height:30%">
        <div style="float:left;width:50%;padding-left:10px;padding-right:5px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            신규 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGDONGADD._text }}
          </h3>
        </div>
        <div style="float:left;width:50%;padding-left:5px;padding-right:10px">
          <h3
            style="font-family: 'Nanum Gothic Coding', monospace; font-weight:bold;font-size:36px;text-align:center;background-color:#b5b5b5"
          >
            누적 : {{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGDONG._text }}
          </h3>
        </div>

        <table
          class="table table-striped"
          style="width:95%;margin-top:120px;margin-left:20px;margin-right:50px;font-family: 'Nanum Gothic Coding', monospace;;font-size:20px;text-align:center;"
        >
          <thead>
            <tr>
              <th>
                일자
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(5, 10) }}
              </th>
              <th>
                {{ coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(5, 10) }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>확진</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[4].GANGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[3].GANGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[2].GANGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[1].GANGDONG._text }}</td>
              <td>{{ coronainfo.TbCorona19CountStatusJCG.row[0].GANGDONG._text }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 코로나정보 끝-->
  </div>
</template>

<script>
import http from "../http-common";
import axios from "axios";
import { GChart } from "vue-google-charts";

export default {
  name: "hospital",
  data() {
    return {
      gugun: "",
      update: false,
      info: [],
      coronainfo: [],
      errored: false,
      loading: true,
      erroredC: false,
      loadingC: true,
      isView: false,
      chartDataJONGNO: [[], [], [], [], [], []],
      chartDataJUNGGU: [[], [], [], [], [], []],
      chartDataYONGSAN: [[], [], [], [], [], []],
      chartDataSEONGDONG: [[], [], [], [], [], []],
      chartDataGWANGJIN: [[], [], [], [], [], []],
      chartDataGANGNAM: [[], [], [], [], [], []],
      chartDataDDM: [[], [], [], [], [], []],
      chartDataJUNGNANG: [[], [], [], [], [], []],
      chartDataSEONGBUK: [[], [], [], [], [], []],
      chartDataGANGBUK: [[], [], [], [], [], []],
      chartDataDOBONG: [[], [], [], [], [], []],
      chartDataNOWON: [[], [], [], [], [], []],
      chartDataEP: [[], [], [], [], [], []],
      chartDataSDM: [[], [], [], [], [], []],
      chartDataMAPO: [[], [], [], [], [], []],
      chartDataYANGCHEON: [[], [], [], [], [], []],
      chartDataGANGSEO: [[], [], [], [], [], []],
      chartDataGURO: [[], [], [], [], [], []],
      chartDataGEUMCHEON: [[], [], [], [], [], []],
      chartDataYDP: [[], [], [], [], [], []],
      chartDataDONGJAK: [[], [], [], [], [], []],
      chartDataGWANAK: [[], [], [], [], [], []],
      chartDataSEOCHO: [[], [], [], [], [], []],
      chartDataSONGPA: [[], [], [], [], [], []],
      chartDataGANGDONG: [[], [], [], [], [], []],
      chartOptions: {
        chart: {
          title: "Company Performance",
          subtitle: "Sales, Expenses, and Profit: 2014-2017",
        },
      },
    };
  },
  components: {
    GChart,
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.info[0].lat, this.info[0].lng), // 지도의 중심좌표
          level: 7, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);
      //if (!this.info) {

      var imageSrc = "/images/hospital.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(48, 52), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption); // 마커가 표시될 위치입니다

      for (var i = 0; i < this.info.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        // 마커 이미지를 생성합니다
        //var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var markerPosition = new kakao.maps.LatLng(this.info[i].lat, this.info[i].lng);
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: markerPosition, // 마커를 표시할 위치
          title: this.info[i].name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        var content =
          '<div class="customoverlay" styel="width:70px;height:30px">' +
          "  <a " +
          ">" +
          '    <span class="title">' +
          this.info[i].name;
        "</span>" + "  </a>" + "</div>";

        // 커스텀 오버레이가 표시될 위치입니다
        var position = new kakao.maps.LatLng(this.info[i].lat, this.info[i].lng);

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          map: map,
          position: position,
          content: content,
          yAnchor: 1,
        });
      }
    },
    initMapFirst() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.5642135, 127.0016985), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      //if (!this.info) {
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    },
  },
  created() {},
  async mounted() {
    var convert = require("xml-js");
    await axios
      .get(
        "http://openapi.seoul.go.kr:8088/445973444770696e37386b6f6e5978/xml/TbCorona19CountStatusJCG/1/5/"
      )
      .then((response) => {
        var xml = response.data;
        var json = convert.xml2json(xml, { compact: true });
        this.coronainfo = JSON.parse(json);
        console.log(coronainfo + "크리에");
      })
      .catch(() => {
        this.erroredC = true;
      })
      .finally(() => (this.loadingC = false));

    if (window.kakao && window.kakao.maps) {
      this.initMapFirst();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMapFirst);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=edb4c5143cbe0a787e704009ee69320a&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }

    this.chartDataGANGNAM[0][0] = ")";
    this.chartDataGANGNAM[0][1] = "누적확진자";
    this.chartDataGANGNAM[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGNAM[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GANGNAM._text *= 1;
    this.chartDataGANGNAM[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGNAM[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GANGNAM._text *= 1;
    this.chartDataGANGNAM[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGNAM[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GANGNAM._text *= 1;
    this.chartDataGANGNAM[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGNAM[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GANGNAM._text *= 1;
    this.chartDataGANGNAM[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGNAM[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GANGNAM._text *= 1;

    this.chartDataJONGNO[0][0] = ")";
    this.chartDataJONGNO[0][1] = "누적확진자";
    this.chartDataJONGNO[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJONGNO[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JONGNO._text *= 1;
    this.chartDataJONGNO[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJONGNO[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JONGNO._text *= 1;
    this.chartDataJONGNO[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJONGNO[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JONGNO._text *= 1;
    this.chartDataJONGNO[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJONGNO[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JONGNO._text *= 1;
    this.chartDataJONGNO[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJONGNO[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JONGNO._text *= 1;

    this.chartDataJUNGGU[0][0] = ")";
    this.chartDataJUNGGU[0][1] = "누적확진자";
    this.chartDataJUNGGU[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGGU[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JUNGGU._text *= 1;
    this.chartDataJUNGGU[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGGU[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JUNGGU._text *= 1;
    this.chartDataJUNGGU[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGGU[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JUNGGU._text *= 1;
    this.chartDataJUNGGU[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGGU[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JUNGGU._text *= 1;
    this.chartDataJUNGGU[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGGU[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JUNGGU._text *= 1;

    this.chartDataYONGSAN[0][0] = ")";
    this.chartDataYONGSAN[0][1] = "누적확진자";
    this.chartDataYONGSAN[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYONGSAN[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].YONGSAN._text *= 1;
    this.chartDataYONGSAN[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYONGSAN[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].YONGSAN._text *= 1;
    this.chartDataYONGSAN[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYONGSAN[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].YONGSAN._text *= 1;
    this.chartDataYONGSAN[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYONGSAN[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].YONGSAN._text *= 1;
    this.chartDataYONGSAN[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYONGSAN[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].YONGSAN._text *= 1;

    this.chartDataSEONGDONG[0][0] = ")";
    this.chartDataSEONGDONG[0][1] = "누적확진자";
    this.chartDataSEONGDONG[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGDONG[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].SEONGDONG._text *= 1;
    this.chartDataSEONGDONG[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGDONG[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].SEONGDONG._text *= 1;
    this.chartDataSEONGDONG[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGDONG[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].SEONGDONG._text *= 1;
    this.chartDataSEONGDONG[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGDONG[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].SEONGDONG._text *= 1;
    this.chartDataSEONGDONG[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGDONG[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].SEONGDONG._text *= 1;

    this.chartDataGWANGJIN[0][0] = ")";
    this.chartDataGWANGJIN[0][1] = "누적확진자";
    this.chartDataGWANGJIN[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANGJIN[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GWANGJIN._text *= 1;
    this.chartDataGWANGJIN[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANGJIN[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GWANGJIN._text *= 1;
    this.chartDataGWANGJIN[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANGJIN[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GWANGJIN._text *= 1;
    this.chartDataGWANGJIN[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANGJIN[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GWANGJIN._text *= 1;
    this.chartDataGWANGJIN[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANGJIN[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GWANGJIN._text *= 1;

    this.chartDataDDM[0][0] = ")";
    this.chartDataDDM[0][1] = "누적확진자";
    this.chartDataDDM[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDDM[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].DDM._text *= 1;
    this.chartDataDDM[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDDM[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].DDM._text *= 1;
    this.chartDataDDM[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDDM[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].DDM._text *= 1;
    this.chartDataDDM[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDDM[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].DDM._text *= 1;
    this.chartDataDDM[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDDM[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].DDM._text *= 1;

    this.chartDataJUNGNANG[0][0] = ")";
    this.chartDataJUNGNANG[0][1] = "누적확진자";
    this.chartDataJUNGNANG[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGNANG[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JUNGNANG._text *= 1;
    this.chartDataJUNGNANG[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGNANG[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JUNGNANG._text *= 1;
    this.chartDataJUNGNANG[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGNANG[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JUNGNANG._text *= 1;
    this.chartDataJUNGNANG[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGNANG[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JUNGNANG._text *= 1;
    this.chartDataJUNGNANG[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataJUNGNANG[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JUNGNANG._text *= 1;

    this.chartDataSEONGBUK[0][0] = ")";
    this.chartDataSEONGBUK[0][1] = "누적확진자";
    this.chartDataSEONGBUK[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGBUK[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].SEONGBUK._text *= 1;
    this.chartDataSEONGBUK[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGBUK[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].SEONGBUK._text *= 1;
    this.chartDataSEONGBUK[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGBUK[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].SEONGBUK._text *= 1;
    this.chartDataSEONGBUK[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGBUK[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].SEONGBUK._text *= 1;
    this.chartDataSEONGBUK[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEONGBUK[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].SEONGBUK._text *= 1;

    this.chartDataGANGBUK[0][0] = ")";
    this.chartDataGANGBUK[0][1] = "누적확진자";
    this.chartDataGANGBUK[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGBUK[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GANGBUK._text *= 1;
    this.chartDataGANGBUK[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGBUK[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GANGBUK._text *= 1;
    this.chartDataGANGBUK[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGBUK[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GANGBUK._text *= 1;
    this.chartDataGANGBUK[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGBUK[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GANGBUK._text *= 1;
    this.chartDataGANGBUK[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGBUK[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GANGBUK._text *= 1;

    this.chartDataDOBONG[0][0] = ")";
    this.chartDataDOBONG[0][1] = "누적확진자";
    this.chartDataDOBONG[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDOBONG[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].DOBONG._text *= 1;
    this.chartDataDOBONG[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDOBONG[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].DOBONG._text *= 1;
    this.chartDataDOBONG[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDOBONG[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].DOBONG._text *= 1;
    this.chartDataDOBONG[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDOBONG[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].DOBONG._text *= 1;
    this.chartDataDOBONG[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDOBONG[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].DOBONG._text *= 1;

    this.chartDataNOWON[0][0] = ")";
    this.chartDataNOWON[0][1] = "누적확진자";
    this.chartDataNOWON[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataNOWON[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].NOWON._text *= 1;
    this.chartDataNOWON[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataNOWON[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].NOWON._text *= 1;
    this.chartDataNOWON[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataNOWON[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].NOWON._text *= 1;
    this.chartDataNOWON[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataNOWON[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].NOWON._text *= 1;
    this.chartDataNOWON[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataNOWON[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].NOWON._text *= 1;

    this.chartDataEP[0][0] = ")";
    this.chartDataEP[0][1] = "누적확진자";
    this.chartDataEP[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataEP[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].EP._text *= 1;
    this.chartDataEP[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataEP[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].EP._text *= 1;
    this.chartDataEP[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataEP[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].EP._text *= 1;
    this.chartDataEP[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataEP[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].EP._text *= 1;
    this.chartDataEP[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataEP[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].EP._text *= 1;

    this.chartDataSDM[0][0] = ")";
    this.chartDataSDM[0][1] = "누적확진자";
    this.chartDataSDM[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSDM[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].SDM._text *= 1;
    this.chartDataSDM[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSDM[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].SDM._text *= 1;
    this.chartDataSDM[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSDM[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].SDM._text *= 1;
    this.chartDataSDM[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSDM[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].SDM._text *= 1;
    this.chartDataSDM[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSDM[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].SDM._text *= 1;

    this.chartDataMAPO[0][0] = ")";
    this.chartDataMAPO[0][1] = "누적확진자";
    this.chartDataMAPO[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataMAPO[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].MAPO._text *= 1;
    this.chartDataMAPO[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataMAPO[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].MAPO._text *= 1;
    this.chartDataMAPO[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataMAPO[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].MAPO._text *= 1;
    this.chartDataMAPO[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataMAPO[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].MAPO._text *= 1;
    this.chartDataMAPO[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataMAPO[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].MAPO._text *= 1;

    this.chartDataYANGCHEON[0][0] = ")";
    this.chartDataYANGCHEON[0][1] = "누적확진자";
    this.chartDataYANGCHEON[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYANGCHEON[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].YANGCHEON._text *= 1;
    this.chartDataYANGCHEON[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYANGCHEON[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].YANGCHEON._text *= 1;
    this.chartDataYANGCHEON[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYANGCHEON[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].YANGCHEON._text *= 1;
    this.chartDataYANGCHEON[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYANGCHEON[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].YANGCHEON._text *= 1;
    this.chartDataYANGCHEON[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYANGCHEON[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].YANGCHEON._text *= 1;

    this.chartDataGANGSEO[0][0] = ")";
    this.chartDataGANGSEO[0][1] = "누적확진자";
    this.chartDataGANGSEO[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGSEO[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GANGSEO._text *= 1;
    this.chartDataGANGSEO[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGSEO[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GANGSEO._text *= 1;
    this.chartDataGANGSEO[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGSEO[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GANGSEO._text *= 1;
    this.chartDataGANGSEO[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGSEO[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GANGSEO._text *= 1;
    this.chartDataGANGSEO[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGSEO[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GANGSEO._text *= 1;

    this.chartDataGURO[0][0] = ")";
    this.chartDataGURO[0][1] = "누적확진자";
    this.chartDataGURO[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGURO[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GURO._text *= 1;
    this.chartDataGURO[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGURO[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GURO._text *= 1;
    this.chartDataGURO[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGURO[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GURO._text *= 1;
    this.chartDataGURO[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGURO[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GURO._text *= 1;
    this.chartDataGURO[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGURO[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GURO._text *= 1;

    this.chartDataGEUMCHEON[0][0] = ")";
    this.chartDataGEUMCHEON[0][1] = "누적확진자";
    this.chartDataGEUMCHEON[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGEUMCHEON[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GEUMCHEON._text *= 1;
    this.chartDataGEUMCHEON[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGEUMCHEON[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GEUMCHEON._text *= 1;
    this.chartDataGEUMCHEON[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGEUMCHEON[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GEUMCHEON._text *= 1;
    this.chartDataGEUMCHEON[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGEUMCHEON[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GEUMCHEON._text *= 1;
    this.chartDataGEUMCHEON[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGEUMCHEON[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GEUMCHEON._text *= 1;

    this.chartDataYDP[0][0] = ")";
    this.chartDataYDP[0][1] = "누적확진자";
    this.chartDataYDP[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYDP[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].YDP._text *= 1;
    this.chartDataYDP[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYDP[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].YDP._text *= 1;
    this.chartDataYDP[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYDP[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].YDP._text *= 1;
    this.chartDataYDP[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYDP[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].YDP._text *= 1;
    this.chartDataYDP[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataYDP[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].YDP._text *= 1;

    this.chartDataDONGJAK[0][0] = ")";
    this.chartDataDONGJAK[0][1] = "누적확진자";
    this.chartDataDONGJAK[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDONGJAK[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].DONGJAK._text *= 1;
    this.chartDataDONGJAK[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDONGJAK[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].DONGJAK._text *= 1;
    this.chartDataDONGJAK[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDONGJAK[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].DONGJAK._text *= 1;
    this.chartDataDONGJAK[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDONGJAK[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].DONGJAK._text *= 1;
    this.chartDataDONGJAK[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataDONGJAK[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].DONGJAK._text *= 1;

    this.chartDataGWANAK[0][0] = ")";
    this.chartDataGWANAK[0][1] = "누적확진자";
    this.chartDataGWANAK[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANAK[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GWANAK._text *= 1;
    this.chartDataGWANAK[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANAK[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GWANAK._text *= 1;
    this.chartDataGWANAK[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANAK[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GWANAK._text *= 1;
    this.chartDataGWANAK[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANAK[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GWANAK._text *= 1;
    this.chartDataGWANAK[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGWANAK[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GWANAK._text *= 1;

    this.chartDataSEOCHO[0][0] = ")";
    this.chartDataSEOCHO[0][1] = "누적확진자";
    this.chartDataSEOCHO[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEOCHO[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].SEOCHO._text *= 1;
    this.chartDataSEOCHO[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEOCHO[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].SEOCHO._text *= 1;
    this.chartDataSEOCHO[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEOCHO[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].SEOCHO._text *= 1;
    this.chartDataSEOCHO[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEOCHO[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].SEOCHO._text *= 1;
    this.chartDataSEOCHO[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSEOCHO[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].SEOCHO._text *= 1;

    this.chartDataSONGPA[0][0] = ")";
    this.chartDataSONGPA[0][1] = "누적확진자";
    this.chartDataSONGPA[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSONGPA[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].SONGPA._text *= 1;
    this.chartDataSONGPA[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSONGPA[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].SONGPA._text *= 1;
    this.chartDataSONGPA[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSONGPA[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].SONGPA._text *= 1;
    this.chartDataSONGPA[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSONGPA[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].SONGPA._text *= 1;
    this.chartDataSONGPA[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataSONGPA[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].SONGPA._text *= 1;

    this.chartDataGANGDONG[0][0] = ")";
    this.chartDataGANGDONG[0][1] = "누적확진자";
    this.chartDataGANGDONG[1][0] = this.coronainfo.TbCorona19CountStatusJCG.row[4].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGDONG[1][1] = this.coronainfo.TbCorona19CountStatusJCG.row[4].GANGDONG._text *= 1;
    this.chartDataGANGDONG[2][0] = this.coronainfo.TbCorona19CountStatusJCG.row[3].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGDONG[2][1] = this.coronainfo.TbCorona19CountStatusJCG.row[3].GANGDONG._text *= 1;
    this.chartDataGANGDONG[3][0] = this.coronainfo.TbCorona19CountStatusJCG.row[2].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGDONG[3][1] = this.coronainfo.TbCorona19CountStatusJCG.row[2].GANGDONG._text *= 1;
    this.chartDataGANGDONG[4][0] = this.coronainfo.TbCorona19CountStatusJCG.row[1].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGDONG[4][1] = this.coronainfo.TbCorona19CountStatusJCG.row[1].GANGDONG._text *= 1;
    this.chartDataGANGDONG[5][0] = this.coronainfo.TbCorona19CountStatusJCG.row[0].JCG_DT._text.substring(
      5,
      10
    );
    this.chartDataGANGDONG[5][1] = this.coronainfo.TbCorona19CountStatusJCG.row[0].GANGDONG._text *= 1;
    console.log(this.chartDataGANGNAM);
    console.log(this.chartDataDDM);
    //
  },

  watch: {
    gugun() {
      this.update = true;
    },
  },

  async updated() {
    if (this.update) {
      if (window.kakao && window.kakao.maps) {
        await http
          .get("/searchByGugun/" + this.gugun)
          .then((response) => (this.info = response.data))
          .catch(() => {
            this.errored = true;
          })
          .finally(() => (this.loading = false));
        this.update = false;
        this.initMap();
      }
    }
  },
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 20px;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
.custom-highlight-row {
  background-color: grey;
}
.modal {
  display: block;
}
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
.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
