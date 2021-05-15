<template>
  <v-card class="container">
    <Dialog v-if="dialog" :message="message" @dialog-result="handleDialog" />
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Đăng ký thông tin</span>
    </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card-text>
        <v-container fill-height>
          <v-row>
            <v-col>
              <span>(Trường có dấu * là những trường thông tin bắt buộc)</span>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.name"
                :counter="255"
                label="Họ tên *"
                required
                :rules="nameRules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
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
                    v-model="informPersonal.birthday"
                    label="Ngày sinh *"
                    append-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    required
                    :rules="requiredRules"
                  />
                </template>
                <v-date-picker
                  :allowed-dates="allowedDate"
                  v-model="informPersonal.birthday"
                />
              </v-menu>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.identityCode"
                :counter="12"
                label="Số chứng minh thư/ Căn cước công dân *"
                required
                :rules="identityRules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.job"
                label="Công việc hiện tại *"
                required
                :rules="requiredRules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                outlined
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
        </v-container>
      </v-card-text>
    </v-form>
    <v-card-actions>
      <v-spacer />
      <v-btn elevation="2" text @click="close"> Hủy </v-btn>
      <v-spacer />
      <v-btn color="success" @click="register"> Đăng kí </v-btn>
      <v-spacer />
    </v-card-actions>
  </v-card>
</template>

<script>
import * as check from "../../helper/validation";
import  UserService  from "../../service/customer.service";
import Dialog from "../../components/Dialog";
export default {
  name: "Registration",
  components: { Dialog },
  data() {
    return {
      dialog: false,
      message: "",
      informPersonal: {
        name: "",
        birthday: "",
        job: "",
        salary: "",
        identityCode: "",
      },
      salarys: [
        { name: "Dưới 3 triệu", value: "Dưới 3 triệu" },
        { name: "Từ 3 đến 5 triệu", value: "Từ 3 đến 5 triệu" },
        { name: "Từ 5 đến 10 triệu", value: "Từ 5 đến 10 triệu" },
        { name: "Từ 10 đến 15 triệu", value: "Từ 10 đến 15 triệu" },
        { name: "Từ 15 đến 20 triệu", value: "Từ 15 đến 20 triệu" },
        { name: "Trên 20 triệu", value: "Trên 20 triệu" },
      ],
      menu: false,
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
    },
    handleDialog(isAgree) {
      this.dialog = false;
      if (isAgree) {
        this.$store.commit("SET_INFORMATION_PERSONAL", this.informPersonal);
        this.$router.push("/report-information");
        return;
      }
      this.$refs.form.reset();
    },
    async register() {
      try {
        if (this.$refs.form.validate()) {
          await UserService.registration(this.informPersonal);
          this.$store.dispatch("displayNotification", {
            isDisplay: true,
            message: "Đăng ký thành công.",
          });
          this.dialog = true;
          this.message = "Bạn có muốn khai báo thông tin ngay bây giờ không?";
        }
      } catch (error) {
        this.$store.dispatch("displayNotification", {
          isDisplay: true,
          type: "error",
          message: "Chứng minh thư đã được đăng ký.",
        });
      }
    },
    allowedDate(val) {
      return new Date(val) <= new Date();
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
}
</style>
