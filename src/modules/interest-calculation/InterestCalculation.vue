<template>
  <v-container>
    <v-row>
      <v-col md="6" sm="4" xm="1"
        ><img class="bg-image" src="../../assets/logo.png" alt=""
      /></v-col>
      <v-col md="6" sm="4" xm="2">
        <v-card class="container">
          <v-card-title class="justify-center">
            <span class="font-weight-regular display-2">{{ title }}</span>
          </v-card-title>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-card-text>
              <v-container fill-height>
                <v-row>
                  <v-col md="12" sm="12" xm="12">
                    <v-text-field
                      outlined
                      v-model="loan"
                      :counter="255"
                      label="Số tiền bạn cần vay"
                      required
                      :rules="loanRules"
                    />
                  </v-col>
                  <v-col md="12" sm="12" xm="12">
                    <v-select
                      outlined
                      v-model="interestType"
                      item-text="name"
                      item-value="value"
                      :items="interestTypes"
                      required
                      :rules="interestTypeRules"
                      label="Kiểu tính lãi"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-select
                      outlined
                      v-model="numOfMonths"
                      item-text="name"
                      item-value="month"
                      :items="loanTime"
                      required
                      :rules="loanTimeRules"
                      label="Thời hạn vay"
                    />
                  </v-col>
                  <v-col>
                    <v-text-field
                      outlined
                      readonly
                      :value="interestRate"
                      label="Lãi suất"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field
                      outlined
                      readonly
                      :value="loanPerMonth"
                      label="Tiền gốc phải trả hàng tháng"
                    />
                  </v-col>
                  <v-col>
                    <v-text-field
                      outlined
                      readonly
                      :value="interestPerMonth"
                      label="Tiền lãi phải trả hàng tháng"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field
                      outlined
                      readonly
                      :value="totalPerMonth"
                      label="Tổng tiền phải trả hàng tháng"
                    />
                  </v-col>
                </v-row>
                <v-checkbox
                  v-if="isBrrower"
                  v-model="isConfirm"
                  label="Tôi xác nhận rằng tôi đã đọc và đồng ý với các điều khoản?"
                  required
                  :rules="checkboxRules"
                ></v-checkbox>
              </v-container>
            </v-card-text>
          </v-form>
          <v-card-actions>
            <v-spacer />
            <v-btn elevation="2" color="success" @click="interestCalculate">Tính thử</v-btn>
            <v-spacer />
            <v-btn elevation="2" color="primary" v-if="isBrrower" @click="requestBorrower"
              >Xác nhận</v-btn
            >
            <v-spacer />
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-row> <Table v-if="isDisplayTable" :data="dataPayment" /></v-row>
  </v-container>
</template>

<script>
import * as check from "../../helper/validation";
import { interestRate } from "./constance";
import Table from "./components/Table.vue";
import LoanService from "../../service/loan.service";
import { formatDate } from "../../helper/format-date";

export default {
  components: { Table },
  name: "InterestCalculation",
  data() {
    return {
      title: "Tính lãi",
      isConfirm: false,
      isBrrower: false,
      dataPayment: [],
      isDisplayTable: false,
      loan: "",
      numOfMonths: 0,
      interestType: 1,
      interestPerMonth: "",
      loanPerMonth: "",
      totalPerMonth: "",
      loanTime: [
        { name: "1 tháng", month: 1 },
        { name: "3 tháng", month: 3 },
        { name: "6 tháng", month: 6 },
        { name: "9 tháng", month: 9 },
        { name: "12 tháng", month: 12 },
        { name: "24 tháng", month: 24 },
        { name: "36 tháng", month: 36 },
        { name: "48 tháng", month: 48 },
        { name: "60 tháng", month: 60 },
      ],
      interestTypes: [
        { name: "Tính theo dự nợ giảm dần", value: 1 },
        { name: "Tính theo dự nợ ban đầu", value: 2 },
      ],
      checkboxRules: [
        (value) => {
          return (
            !!value || "Vui lòng xác nhận đã đọc và đồng ý với các điều khoản."
          );
        },
      ],
      loanRules: [(value) => check.validMoney(value)],
      nameRules: [
        (value) => check.validRequired(value),
        (value) => check.validLengthMax(value, 255),
      ],
      identityRules: [
        (value) => check.validRequired(value),
        (value) => check.isNumber(value),
        (value) => check.validIdentity(value),
      ],
      interestTypeRules: [
        (value) => check.validRequired(value, "Bạn phải chọn phương thức vay!"),
      ],
      loanTimeRules: [
        (value) => check.validRequired(value, "Bạn phải chọn số tháng vay!"),
      ],
      menu: false,
      valid: false,
    };
  },
  created() {
    console.log(this.$route.params.id);
    if (this.$route.path.includes("/interest-loans/report-information")) {
      this.title = "Thông tin vay";
      this.isBrrower = true;
    }
  },
  computed: {
    interestRate() {
      return interestRate[`${this.numOfMonths}`];
    },
  },
  methods: {
    async interestCalculate() {
      if (this.$refs.form.validate()) {
        const data = {
          loan: this.loan,
          interestRate: this.interestRate,
          interestType: this.interestType,
          numOfMonths: this.numOfMonths,
          dateOfLoan: formatDate(new Date()),
        };
        try {
          if (this.interestType === 1) {
            this.dataPayment = await LoanService.calFollowDecreasing(data);
            this.isDisplayTable = true;
            return;
          } else {
            this.isDisplayTable = false;
            const response = await LoanService.calFollowOriginal(data);
            this.loanPerMonth = response.loan.loanPerMonth;
            this.totalPerMonth = response.totalPerMonth;
            this.interestPerMonth = response.interest;
          }
        } catch (error) {
          console.log(error);
        }
      }
    },
    async requestBorrower() {
      if (this.$refs.form.validate()) {
        const data = {
          loan: this.loan,
          interestRate: this.interestRate,
          interestType: this.interestType,
          numOfMonths: this.numOfMonths,
          dateOfLoan: formatDate(new Date()),
          customer: {
            id: this.$route.params.id,
          },
        };
        try {
          await LoanService.requestLoan(data);
          this.$store.dispatch("displayNotification", {
            isDisplay: true,
            message: "Vay thành công.",
          });
        } catch (error) {
          console.log(error);
        }
      }
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 100%;
}
.bg-image {
  max-width: 100%;
  height: 100%;
}
</style>
