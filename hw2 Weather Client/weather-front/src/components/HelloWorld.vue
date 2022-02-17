<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <div class="hello">

       <v-form>
            <v-container>
              <v-row>
                <v-col cols="12" md="8">
                  <v-text-field
                    v-model="path"
                    label="Path"
                    required
                  ></v-text-field>
                </v-col>

              </v-row>
            </v-container>
          </v-form>



          <v-form>
            <v-container>
              <v-row>
                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="country"
                    label="Country"
                    required
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="city"
                    label="City"
                    required
                  ></v-text-field>
                </v-col>

                <v-col cols="12" md="4">
                  <v-text-field
                    v-model="date"
                    label="Date"
                    required
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-form>

          <v-btn class="mx-2" fab dark color="indigo" @click="more">
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>

          <v-btn
            v-if="arrCity.length != 0"
            color="blue-grey"
            class="ma-2 white--text"
            @click="doGet"
          >
            Send
            <v-icon right dark> mdi-cloud-upload </v-icon>
          </v-btn>

          <br />
          <br />
          <br />

          <v-card v-if="arrCity.length != 0" dense>
            <v-card-title class="subheading font-weight-bold">
              Locations to send.
            </v-card-title>

            <v-divider></v-divider>

            <v-list>
              <v-list-item>
                <v-list-item-content class="align-center">
                  <v-row no-gutters>
                    <v-col>
                      <v-card class="pa-2" outlined tile> Country </v-card>
                    </v-col>

                    <v-col>
                      <v-card class="pa-2" outlined tile> City </v-card>
                    </v-col>

                    <v-col>
                      <v-card class="pa-2" outlined tile> Date </v-card>
                    </v-col>
                  </v-row>
                </v-list-item-content>
              </v-list-item>

              <v-list-item v-for="(item, index) in arrCity" :key="index">
                <v-list-item-content class="align-center">
                  <v-row no-gutters>
                    <v-col>
                      <v-card class="pa-2" outlined tile>
                        {{ arrCountry[index] }}
                      </v-card>
                    </v-col>

                    <v-col>
                      <v-card class="pa-2" outlined tile>
                        {{ item }}
                      </v-card>
                    </v-col>

                    <v-col>
                      <v-card class="pa-2" outlined tile>
                        {{ arrDate[index] }}
                      </v-card>
                    </v-col>
                  </v-row>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>

          <div v-if="arrWeatherResponse.length > 0">
            <br />
            <hr />
            <v-data-table
              :headers="headers"
              :items="arrWeatherResponse"
              @click:row="handleClick"
              :items-per-page="10"
              class="elevation-1"
            ></v-data-table>
          </div>
          <div v-else>
            <p>Is empty</p>
          </div>

          <div v-if="dailyTemperature.length > 0">
            <br />
            <hr />
            <br />

            <ul>
              <li>
                <h2>Country: {{ countrySelec }}</h2>
              </li>
              <li>
                <h2>City: {{ citySelec }}</h2>
              </li>
              <li>
                <h2>Date: {{ dateSelec }}</h2>
              </li>
            </ul>
          </div>
          <v-simple-table v-if="dailyTemperature.length > 0">
            <template>
              <thead>
                <tr>
                  <th><h2>#</h2></th>
                  <th class="text-center"><h2>Temperature</h2></th>
                  <th class="text-center"><h2>Humidity</h2></th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="n in 24" :key="n">
                  <td>{{ n - 1 }}</td>
                  <td>{{ dailyTemperature[n - 1] }}</td>
                  <td>{{ dailyHumidity[n - 1] }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>

          <!-- <table>
            <thead>
              <tr>
                <th>city</th>
                <th>country</th>
                <th>date</th>
                <th>humidity</th>
                <th>maxtemperature</th>
                <th>mintemperature</th>
                <th>temperature</th>
                <th>weatherType</th>

                <th>dailyHumidity</th>
                <th>dailyTemperature</th>
              </tr>
            </thead>
            <tr v-for="(item, index) in arrWeatherResponse" :key="index">
              <td>{{ item.city }}</td>
              <td>{{ item.country }}</td>
              <td>{{ item.date }}</td>
              <td>{{ item.humidity }}</td>
              <td>{{ item.maxtemperature }}</td>
              <td>{{ item.mintemperature }}</td>
              <td>{{ item.temperature }}</td>
              <td>{{ item.weatherType }}</td>

              <td>
                <ul>
                  <li
                    v-for="(it, ind) in JSON.parse(item.dailyHumidity)"
                    :key="ind"
                  >
                    {{ it }}
                  </li>
                </ul>
              </td>
              <td>
                <ol>
                  <li
                    v-for="(it2, ind2) in JSON.parse(item.dailyTemperature)"
                    :key="ind2"
                  >
                    {{ it2 }}
                  </li>
                </ol>
              </td>
            </tr>
          </table> -->
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "HelloWorld",

  data: () => ({
    path : "http://localhost:8080/weatherRestServer/Weather", 
    headers: [
      { text: "City", value: "city" },
      { text: "Country", value: "country" },
      { text: "Date", value: "date" },
      { text: "Humidity", value: "humidity" },
      { text: "Max temperature", value: "maxtemperature" },
      { text: "Min temperature", value: "mintemperature" },
      { text: "Temperature", value: "temperature" },
      { text: "Weather Type", value: "weatherType" },

      // { text: "Daily Humidity", value: "dailyHumidity" },
      // { text: "Daily Temperature", value: "dailyTemperature" },
    ],
    dailyTemperature: [],
    dailyHumidity: [],
    arrCity: [],
    arrCountry: [],
    arrDate: [],
    arrWeatherResponse: [],
    city: "",
    country: "",
    date: "",

    dateSelec: "",
    countrySelec: "",
    citySelec: "",
  }),
  methods: {
    handleClick: function (value) {
      this.dailyTemperature = JSON.parse(value.dailyTemperature);
      this.dailyHumidity = JSON.parse(value.dailyHumidity);

      this.dateSelec = value.date;
      this.countrySelec = value.country;
      this.citySelec = value.city;
    },
    more: function () {
      this.arrCity.push(this.city);
      this.arrCountry.push(this.country);
      this.arrDate.push(this.date);

      this.city = "";
      this.country = "";
      this.date = "";
    },
    doGet: function () {
      var requestOptions = {
        method: "GET",
        redirect: "follow",
      };

      fetch(
        `${this.path}?countries=${this.arrCountry.join(
          ","
        )}&cities=${this.arrCity.join(",")}&dates=${this.arrDate.join(",")}`,
        requestOptions
      )
        .then((response) => response.json())
        .then((result) => {
          this.arrWeatherResponse = result;
          console.log(result);
        })
        .catch((error) => console.log("error", error));

      this.arrCity = [];
      this.arrCountry = [];
      this.arrDate = [];
    },
  },
};
</script>
