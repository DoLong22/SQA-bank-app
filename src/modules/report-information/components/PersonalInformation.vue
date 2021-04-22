<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-card-text>
      <v-container>
        <v-subheader class="subheader">
          Thông tin cá nhân
        </v-subheader>
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
            <v-select outlined
              v-model="informPersonal.gender"
              item-text="name"
              item-value="value"
              :items="itemsGender"
              label="Giới tính"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field filled
              v-model="informPersonal.birthday"
              :counter="255"
              label="Ngày sinh"
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Địa chỉ"
              required
              :rules="nameRules"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Công việc"
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Thu nhập"
              required
              :rules="nameRules"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Tên công ty"
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Phòng ban"
              required
              :rules="nameRules"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Số chứng minh thư/thẻ căn cước "
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Ngày cấp"
              required
              :rules="nameRules"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Nơi cấp "
              required
              :rules="nameRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              v-model="informPersonal.birthday"
              :counter="255"
              label="Ngày cấp"
              required
              :rules="nameRules"
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
    editedItem: {
      type: Object,
      default: () => {},
    },
    isUpdate: {
      type: Boolean,
      default: true,
    },
    cardTitle: {
      type: String,
      default: "New Item",
    },
    isDisplayDetail: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      informPersonal: {
        name: "",
        hometown: "",
        birthday: "",
        gender: "",
        job: "",
        salary: "",
        identity: {
          id: "",
          createdFrom: "",
          createdAt: "",
        },
      },
      itemsGender: [
        { name: "Orther", value: "orther" },
        { name: "Female", value: "female" },
        { name: "Male", value: "male" },
      ],
      menu: false,
      nameRules: [
        (value) => check.validRequired(value),
        (value) => check.validLengthMax(value, 255),
      ],
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
.subheader{
  font-size: 20px;
  text-decoration: underline;
}
</style>
