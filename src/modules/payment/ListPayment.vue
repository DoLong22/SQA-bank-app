<template>
  <v-card>
    <Dialog v-if="dialog" :message="message" @dialog-result="handleDialog" />
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Danh sách hóa đơn</span>
    </v-card-title>
    <!-- <div class="text--primary">
      well meaning and kindly.<br />
      "a benevolent smile"
    </div> -->
    <v-card-text>
      <v-data-table
        :headers="headers"
        :items="payments"
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
            <td>{{ item.dateOfPay }}</td>
            <td>{{ item.existence }}</td>
            <td>{{ item.interest }}</td>
            <td>{{ item.interest }}</td>
            <td>{{ item.totalPerMonth }}</td>
            <td>{{ item.pay }}</td>
            <td>{{ item.paymentDate }}</td>
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
import Dialog from "../../components/Dialog";
import { formatDate } from "../../helper/format-date";

export default {
  components: { Dialog },
  name: "ListLoan",
  data: () => ({
    title: "Danh sách các khoản vay",
    dialog: false,
    message: "",
    page: 1,
    payments: [],
    pageCount: 0,
    itemsPerPage: 10,
    search: "",
    headers: [
      {
        text: "Kỳ trả nợ",
        align: "start",
        value: "dateOfPay",
      },
      { text: "Nợ gốc còn lại (VNĐ)", value: "existence" },
      { text: "Lãi (VNĐ)", value: "interest" },
      { text: "Nợ gốc mỗi tháng (VNĐ)", value: "interest" },
      { text: "Tổng cộng (VNĐ)", value: "totalPerMonth" },
      { text: "Trạng thái", value: "pay" },
      { text: "Ngày thanh toán", value: "paymentDate" },
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
        const idLoan = this.$route.params.id;
        this.payments = await LoanService.getPayments(idLoan);
        this.payments.forEach((payment) =>
          payment.pay
            ? (payment.pay = "Đã thanh toán")
            : (payment.pay = "Chưa thanh toán")
        );
      } catch (error) {
        console.log(error);
      }
    },
    selectedRow(value) {
      this.editedItem = value;
    },
    getPayment() {
      this.dialog = true;
      this.message = "Bạn có muốn thanh toán hóa đơn vay này?";
    },
    async handleDialog(isAgree) {
      try {
        this.dialog = false;
        if (isAgree) {
          const idLoan = this.$route.params.id;
          this.editedItem.pay = true;
          this.editedItem.paymentDate = formatDate(new Date());
          await LoanService.updatePayment(this.editedItem, idLoan);
          this.initialize();
          this.$store.dispatch("displayNotification", {
            isDisplay: true,
            message: "Thanh toán thành công.",
          });
          return;
        }
      } catch (error) {
        console.log(error);
      }
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