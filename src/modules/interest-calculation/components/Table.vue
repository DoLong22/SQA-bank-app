<template>
  <v-data-table
    :headers="headers"
    :items="data"
    class="elevation-1"
  ></v-data-table>
</template>

<script>
export default {
  name: "Table",
  props: {
    data: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      headers: [
        {
          text: "STT",
          align: "start",
          value: "countMonth",
        },
        { text: "Ngày thanh toán", value: "dateOfPay" },
        { text: "Tiền nợ còn lại", value: "existence" },
        { text: "Tiền nợ gốc", value: "loan.loanPerMonth" },
        { text: "Tiền lãi", value: "interest" },
        { text: "Tổng", value: "totalPerMonth" },
      ],
    };
  },
  created() {
    console.log(this.data);
  },
};
</script>

<style>
</style>