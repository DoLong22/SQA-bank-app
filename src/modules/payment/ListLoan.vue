<template>
  <v-card>
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Danh sách các khoản vay</span>
    </v-card-title>
    <v-card-text>
      <v-text-field
        class="mb-10"
        v-model="search"
        append-icon="mdi-magnify"
        label="Tìm kiếm"
        outlined
        hide-details
      ></v-text-field>
      <div v-if="alert" class="alert alert-primary" role="alert">
        Khách hàng chưa có khoản vay nào
      </div>
      <v-data-table
        :headers="headers"
        :items="loans"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        class="elevation-1"
        @page-count="pageCount = $event"
        :search="search"
      >
        <template v-slot:item="{ item }">
          <tr
            :class="editedItem == item ? 'grey' : ''"
            @click="selectedRow(item)"
          >
            <td>{{ item.dateOfLoan }}</td>
            <td>{{ item.loan }}</td>
            <td>{{ item.interestRate }}</td>
            <td>{{ item.interestType }}</td>
            <td>{{ item.numOfMonths }}</td>
          </tr>
        </template>
      </v-data-table>
    </v-card-text>
    <v-card-actions>
      <v-btn elevation="2" text @click="$router.go(-1)"> Trở lại </v-btn>
      <v-btn elevation="2" depressed color="primary" @click="getPayment">
        Chọn
      </v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>
<script>
import LoanService from "../../service/loan.service";

export default {
  name: "ListPayment",
  data: () => ({
    title: "Danh sách các khoản vay",
    dialog: false,
    alert: false,
    page: 1,
    loans: [],
    pageCount: 0,
    itemsPerPage: 10,
    search: "",
    headers: [
      {
        text: "Ngày vay",
        align: "start",
        value: "dateOfLoan",
      },
      { text: "Khoản vay (VNĐ)", value: "loan" },
      { text: "Lãi suất (%)", value: "interestRate" },
      { text: "Kiểu tính lãi", value: "interestType" },
      { text: "Thời hạn (tháng)", value: "numOfMonths" },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
  }),
  created() {
    this.initialize();
  },
  methods: {
    async initialize() {
      try {
        const idCustomer = this.$route.params.id;
        console.log(idCustomer);
        this.loans = await LoanService.getLoans(idCustomer);
        if (this.loans.length == 0) {
          this.alert = true;
        } else {
          this.loans.forEach((loan) =>
            loan.interestType === 1
              ? (loan.interestType = "Tính theo dự nợ giảm dần")
              : (loan.interestType = "Tính theo dự nợ ban đầu")
          );
        }
      } catch (error) {
        console.log(error);
      }
    },
    selectedRow(value) {
      this.editedItem = value;
    },
    getPayment() {
      console.log("pay loan");
      this.$router.push(`/loans/${this.editedItem.id}/payments`);
    },
  },
};
</script>

<style scoped>
.v-card__actions {
  align-items: center;
  display: flex;
  padding: 1em;
}
</style>