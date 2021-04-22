<template>
  <v-card class="container">
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Tính lãi</span>
    </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card-text>
        <v-container fill-height>
          <v-row>
            <v-col>
              <v-text-field
                v-model="loan"
                :counter="255"
                label="Số tiền bạn cần vay"
                required
                :rules="nameRules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="interestType"
                item-text="name"
                item-value="value"
                :items="interestType"
                required
                :rules="requiredRules"
                label="Kiểu tính lãi"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="informPersonal.salary"
                item-text="name"
                item-value="value"
                :items="salarys"
                required
                :rules="requiredRules"
                label="Thời hạn vay"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="interestRate"
                label="Lãi suất"
                required
                :rules="requiredRules"
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="informPersonal.salary"
                item-text="name"
                item-value="value"
                :items="salarys"
                required
                :rules="requiredRules"
                label="Thời hạn vay"
              />
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-form>
    <v-card-actions>
      <v-spacer />
      <v-btn color="success" @click="save">Kết quả</v-btn>
      <v-spacer />
    </v-card-actions>
  </v-card>
</template>

<script>
import * as check from "../../helper/Validation";

export default {
  name: "InterestCalcultion",
  data() {
    return {
      informPersonal: {
        loan: "",
        loanTime: "",
        interestRate: "",
        interestPerMonth: "",
        principalPerMonth: "",
        totalPerMonth: "",
      },
      loanTime: [
        { name: "3 tháng", value: 2.9 },
        { name: "6 tháng", value: 2.9 },
        { name: "12 tháng", value: 3.2 },
        { name: "18 tháng", value: 3.8 },
        { name: "24 tháng", value: 3.8 },
        { name: "36 tháng", value: 5.3 },
        { name: "48 tháng", value: 5.3 },
        { name: "60 tháng", value: 5.3 },
        { name: "24 tháng", value: 5.3 },
      ],
      interestType: [
        { name: "Tính theo dự nợ giảm dần", value: 0 },
        { name: "Tính theo dự nợ ban đầu", value: 1 },
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
      //   this.$emit("close", false);
    },
    save() {
      console.log(this.informPersonal);
      console.log(this.$refs.form.validate());
      //   if (this.$refs.form.validate()) {
      //     this.$emit("save", true);
      //   }
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
