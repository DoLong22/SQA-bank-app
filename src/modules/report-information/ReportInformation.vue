<template>
  <v-card class="container">
    <v-card-title primary-title class="justify-center">
      <span class="font-weight-regular display-2">Khai báo thông tin</span>
    </v-card-title>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1">
          Thông tin cá nhân
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="e1 > 2" step="2">
          Thông tin tin liên lạc
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="3"> Thông tin việc làm </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="4"> Thông tin tín dụng </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card class="mb-12" min-height="200px">
            <PersonalInformation />
          </v-card>

          <v-btn color="primary" @click="e1 = 2"> Continue </v-btn>

          <v-btn text> Cancel </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-12" height="200px">
            <h1>Test</h1>
          </v-card>
          <v-btn text @click="e1 = 1"> Back </v-btn>
          <v-btn color="primary" @click="e1 = 3"> Continue </v-btn>
          <v-btn text> Cancel </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-12"></v-card>

          <v-btn color="primary" @click="e1 = 1"> Continue </v-btn>

          <v-btn text> Cancel </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-card>
</template>

<script>
import PersonalInformation from "./components/PersonalInformation";
export default {
  components: { PersonalInformation },
  name: "ReportInformation",
  data() {
    return {
      e1: 1,
      information: {
        informPersonal: {
          name: "",
          birthday: "",
          gender: "",
          job: "",
          salary: "",
          hometown: "",
          identity: {
            id: "",
            createdFrom: "",
            createdAt: "",
          },
        },
        informContact: {
          email: "",
          phone: "",
          address: "",
        },
        protector1:{
          name:"",
          phone:"",
          address:"",
        },
        protector2:{
          name:"",
          phone:"",
          address:"",
        }
      },
    };
  },
};
</script>

<style scoped>
.v-sheet.v-card:not(.v-sheet--outlined) {
  box-shadow: none;
}
</style>