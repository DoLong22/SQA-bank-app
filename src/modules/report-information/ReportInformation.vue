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
          Thông tin liên lạc
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="3"> Thông tin người thân </v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card class="mb-12" min-height="200px">
            <PersonalInformation
              :informPersonal="information.informPersonal"
              ref="personalInform"
            />
          </v-card>
          <v-btn text> Cancel </v-btn>
          <v-btn color="primary" @click="isValidPersonalInform">
            Continue
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-12" min-height="200px">
            <ContactInformation
              :informContact="information.informContact"
              ref="contactInform"
            />
          </v-card>
          <v-btn text @click="e1 = 1"> Back </v-btn>
          <v-btn color="primary" @click="isValidContactInform">
            Continue
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-12">
            <ProtectorsInformation
              :informProtectors="information.informProtectors"
              ref="protectorsInform"
            />
          </v-card>
          <v-btn text @click="e1 = 2"> Back </v-btn>
          <v-btn color="primary" @click="isValidProtectorsInform">
            Continue
          </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </v-card>
</template>

<script>
import PersonalInformation from "./components/PersonalInformation";
import ContactInformation from "./components/ContactInformation";
import ProtectorsInformation from "./components/ProtectorsInformation";
export default {
  components: {
    PersonalInformation,
    ContactInformation,
    ProtectorsInformation,
  },
  name: "ReportInformation",
  data() {
    return {
      e1: 1,
      information: {
        informPersonal: {
          name: "Do Long",
          birthday: "1999-02-12",
          gender: "Male",
          job: "IT",
          nameCompany: "ttlab",
          department: "it",
          salary: "Từ 5 đến 10 triệu",
          identity: {
            code: "123456789",
            createdFrom: "Ha Noi",
            createdAt: "1999-02-12",
          },
        },
        informContact: {
          email: "test@gmail.com",
          phone: "0312341245",
          address: "Ha Noi",
          hometown: "Tp Ho Chi Minh",
        },
        informProtectors: [
          {
            name: "Lam thon",
            phone: "0912331234",
            address: "Ba Vi",
          },
          {
            name: "Dance",
            phone: "0912331234",
            address: "Bac Ninh",
          },
        ],
      },
    };
  },
  methods: {
    isValidPersonalInform() {
      if (this.$refs.personalInform.$refs.form.validate()) {
        this.e1 = 2;
      }
    },
    isValidContactInform() {
      console.log(this.$refs.contactInform.informContact);
      if (this.$refs.contactInform.$refs.form.validate()) {
        this.e1 = 3;
      }
    },
    isValidProtectorsInform() {
      if (this.$refs.protectorsInform.$refs.form.validate()) {
        this.e1 = 3;
      }
    },
  },
};
</script>

<style>
.v-sheet.v-card:not(.v-sheet--outlined) {
  box-shadow: none;
}
label.v-label.v-label--active.theme--light {
  top: 15px;
  background-color: white;
}
</style>