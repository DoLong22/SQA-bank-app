<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-card-text>
      <v-container>
        <v-subheader class="subheader"> Thông tin cá nhân </v-subheader>
        <v-divider></v-divider>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informPersonal.name"
              :counter="255"
              label="Họ tên *"
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-menu
              v-model="menuBirthday"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  outlined
                  v-model="informPersonal.birthday"
                  label="Ngày sinh *"
                  append-icon="mdi-calendar"
                  required
                  :rules="requiredRules"
                  v-bind="attrs"
                  v-on="on"
                />
              </template>
              <v-date-picker
                :allowed-dates="allowedDate"
                v-model="informPersonal.birthday"
              />
            </v-menu>
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-select
              outlined
              v-model="informPersonal.gender"
              item-text="name"
              item-value="value"
              :items="itemsGender"
              label="Giới tính"
            />
          </v-col>
        </v-row>
        <v-subheader class="subheader"> Thông tin chứng minh thư </v-subheader>
        <v-divider></v-divider>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              readonly
              v-model="informPersonal.identity.code"
              :counter="12"
              label="Số chứng minh thư/thẻ căn cước * "
              required
              :rules="identityRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-menu
              v-model="menu"
              :close-on-content-click="false"
              :nudge-right="40"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  outlined
                  v-model="informPersonal.identity.createdAt"
                  label="Ngày cấp *"
                  required
                  :rules="requiredRules"
                  append-icon="mdi-calendar"
                  v-bind="attrs"
                  v-on="on"
                />
              </template>
              <v-date-picker
                :allowed-dates="allowedDate"
                v-model="informPersonal.identity.createdAt"
              />
            </v-menu>
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informPersonal.identity.createdFrom"
              label="Nơi cấp *"
              required
              :rules="nameRules"
            />
          </v-col>
        </v-row>
        <v-subheader class="subheader"> Thông tin công việc </v-subheader>
        <v-divider></v-divider>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informPersonal.job"
              :counter="255"
              label="Công việc *"
              readonly
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-select
              outlined
              readonly
              v-model="informPersonal.salary"
              item-text="name"
              item-value="value"
              :items="salarys"
              required
              :rules="requiredRules"
              label="Thu nhập hiện tại *"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informPersonal.nameCompany"
              label="Tên công ty *"
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informPersonal.department"
              label="Phòng ban"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
  </v-form>
</template>

<script>
import * as check from "../../../helper/Validation";

export default {
  name: "PersonalInformation",
  props: {
    informPersonal: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      salarys: [
        { name: "Dưới 3 triệu", value: "Dưới 3 triệu" },
        { name: "Từ 3 đến 5 triệu", value: "Từ 3 đến 5 triệu" },
        { name: "Từ 5 đến 10 triệu", value: "Từ 5 đến 10 triệu" },
        { name: "Từ 10 đến 15 triệu", value: "Từ 10 đến 15 triệu" },
        { name: "Từ 15 đến 20 triệu", value: "Từ 15 đến 20 triệu" },
        { name: "Trên 20 triệu", value: "Trên 20 triệu" },
      ],
      itemsGender: [
        { name: "Orther", value: "orther" },
        { name: "Female", value: "female" },
        { name: "Male", value: "male" },
      ],
      menu: false,
      menuBirthday: false,
      nameRules: [
        (value) => check.validRequired(value),
        (value) => check.validLengthMax(value, 255),
      ],
      identityRules: [
        (value) => check.validRequired(value),
        (value) => check.isNumber(value),
        (value) => check.validIdentity(value),
      ],
      requiredRules: [(value) => check.validRequired(value)],
      valid: false,
    };
  },
  methods: {
    close() {
      this.$refs.form.reset();
      this.$emit("close", false);
    },
    save() {
      if (this.$refs.form.validate()) {
        this.$emit("save", true);
      }
    },
    allowedDate(val) {
      return new Date(val) <= new Date();
    },
  },
};
</script>

<style scoped>
.subheader {
  font-size: 20px;
}
hr {
  margin-bottom: 3rem;
  margin-top: 0rem;
}
</style>
