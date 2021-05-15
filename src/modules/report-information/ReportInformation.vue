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
              :informPersonal="informationPersonal.informPersonal"
              ref="informPersonal"
            />
          </v-card>
          <v-btn elevation="2" text> Hủy </v-btn>
          <v-btn color="primary" @click="isValidPersonalInform">
            Tiếp tục
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-12" min-height="200px">
            <ContactInformation
              :informContact="informationPersonal.informContact"
              ref="informContact"
            />
          </v-card>
          <v-btn text @click="e1 = 1"> Trở lại </v-btn>
          <v-btn color="primary" @click="isValidContactInform">
            Tiếp tục
          </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-12">
            <ProtectorsInformation
              :informProtector="informationPersonal.informProtector"
              ref="informProtector"
            />
          </v-card>
          <v-btn text @click="e1 = 2"> Trở lại </v-btn>
          <v-btn color="primary" @click="isValidProtectorsInform">
            Tiếp tục
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
import  UserService  from "../../service/customer.service";

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
      informationPersonal: {
        informPersonal: {
          name: "",
          birthday: "",
          gender: "",
          job: "",
          nameCompany: "",
          department: "",
          salary: "",
          identityCode: "",
          identityCreatedFrom: "",
          identityCreatedAt: "",
        },
        informContact: {
          email: "",
          phone: "",
          address: "",
          hometown: "",
        },
        informProtector: {
          nameProtector: "",
          phoneProtector: "",
          addressProtector: "",
        },
      },
    };
  },
  created() {
    this.initialize();
  },
  methods: {
    initialize() {
      this.informationPersonal = { ...this.$store.state.informationPersonal };
    },
    isValidPersonalInform() {
      if (this.$refs.informPersonal.$refs.form.validate()) {
        this.e1 = 2;
      }
    },
    isValidContactInform() {
      console.log(this.$refs.informContact.$refs.form.validate());
      if (this.$refs.informContact.$refs.form.validate()) {
        this.e1 = 3;
      }
    },
    async isValidProtectorsInform() {
      try {
        if (this.$refs.informProtector.$refs.form.validate()) {
          await UserService.reportInformation({
            ...this.informationPersonal.informPersonal,
            ...this.informationPersonal.informContact,
            ...this.informationPersonal.informProtector,
          });
          this.$store.dispatch("displayNotification", {
            isDisplay: true,
            message: "Khai báo thông tin thành công.",
          });
          this.$store.dispatch("resetInformation");
          this.$refs.informProtector.$refs.form.reset();
          this.$refs.informContact.$refs.form.reset();
          this.$refs.informPersonal.$refs.form.reset();
        }
      } catch (error) {
        console.log(error);
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