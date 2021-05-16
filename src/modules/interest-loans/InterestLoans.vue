<template>
  <v-card>
    <Dialog v-if="dialog" :message="message" @dialog-result="handleDialog" />
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Danh sách khách hàng</span>
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
      <v-data-table
        :headers="headers"
        :items="customers"
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
            <td>{{ item.name }}</td>
            <td>{{ item.identityCode }}</td>
            <td>{{ item.phoneNumber }}</td>
            <td>{{ item.email }}</td>
            <td>{{ item.address }}</td>
          </tr>
        </template>
      </v-data-table>
    </v-card-text>
    <v-card-actions>
      <v-btn elevation="2" depressed color="primary" v-if="!isPayLoan" @click="handleLoan">
        Chọn
      </v-btn>
      <v-btn elevation="2" depressed color="primary" v-if="isPayLoan" @click="payLoan">
        Chọn
      </v-btn>
      <v-spacer></v-spacer>
      <!-- <v-pagination v-model="page" :length="pageCount"></v-pagination> -->
    </v-card-actions>
  </v-card>
</template>
<script>
import CustomerService from "../../service/customer.service";
import Dialog from "../../components/Dialog";

export default {
  name: "InterestLoans",
  components: { Dialog },
  data: () => ({
    isPayLoan: false,
    title: "Danh sách khách hàng",
    dialog: false,
    message: "",
    page: 1,
    customers: [],
    pageCount: 0,
    itemsPerPage: 10,
    search: "",
    headers: [
      {
        text: "Tên",
        align: "start",
        value: "name",
      },
      { text: "Số chứng minh thư", value: "identityCode" },
      { text: "Số điện thoại", value: "phoneNumber" },
      { text: "Email", value: "email" },
      { text: "Địa chỉ", value: "address" },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
  }),
  created() {
    if (this.$route.path.includes("/pay-the-loan")) {
      this.isPayLoan = true;
    }
    this.initialize();
  },
  methods: {
    async initialize() {
      try {
        this.customers = await CustomerService.getCustomers();
      } catch (error) {
        console.log(error);
      }
    },
    selectedRow(value) {
      this.editedItem = value;
    },
    handleLoan() {
      if (!this.editedItem.confirm) {
        this.dialog = true;
        this.message =
          "Bạn cần khai báo thông tin trước khi vay." +
          "Bạn có muốn khai báo thông tin lúc này không?";
      } else {
        this.$router.replace({
          path: `/interest-loans/report-information/${this.editedItem.id}`,
        });
      }
    },
    payLoan() {
      this.$router.push(`/customers/${this.editedItem.id}/loans`);
    },
    handleDialog(isAgree) {
      this.dialog = false;
      if (isAgree) {
        const { name, birthday, job, salary, identityCode } = this.editedItem;
        this.$store.commit("SET_INFORMATION_PERSONAL", {
          name,
          birthday,
          job,
          salary,
          identityCode,
        });
        this.$router.push("/report-information");
        return;
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