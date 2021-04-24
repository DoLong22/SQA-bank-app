<template>
  <v-container>
    <v-row>
      <v-col></v-col>
      <v-col
        ><v-card class="container">
          <v-card-title class="justify-center">
            <span class="font-weight-regular display-2">Tính lãi</span>
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
                      :items="interestType"
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
                      v-model="month"
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
                      :value="principalPerMonth"
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
              </v-container>
            </v-card-text>
          </v-form>
          <v-card-actions>
            <v-spacer />
            <v-btn color="success" @click="interestCalculate">Kết quả</v-btn>
            <v-spacer />
          </v-card-actions> </v-card
      ></v-col>
    </v-row>
    <v-row> <Table /></v-row>
  </v-container>
</template>

<script>
import * as check from "../../helper/Validation";
import { interestRate } from "./constance";
import {
  principalPerMonthByOriginal,
  interestPerMonthByOriginal,
  totalPerMonthByOriginal,
} from "./services";
import Table from "../../components/Table.vue";

export default {
  components: { Table },
  name: "InterestCalcultion",
  data() {
    return {
      loan: "",
      month: 0,
      interestPerMonth: "",
      principalPerMonth: "",
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
      interestType: [
        { name: "Tính theo dự nợ giảm dần", value: 0 },
        { name: "Tính theo dự nợ ban đầu", value: 1 },
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
  computed: {
    interestRate() {
      console.log(interestRate[`${this.month}`]);
      return interestRate[`${this.month}`];
    },
  },
  methods: {
    interestCalculate() {
      const rate = interestRate[`${this.month}`];
      console.log(rate);
      this.principalPerMonth = principalPerMonthByOriginal(
        this.month,
        this.loan
      );
      this.interestPerMonth = interestPerMonthByOriginal(
        this.month,
        this.loan,
        rate
      );
      this.totalPerMonth = totalPerMonthByOriginal(this.loan, rate);
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 100%;
}
</style>
