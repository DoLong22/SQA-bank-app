<template>
  <v-bottom-navigation color="primary" horizontal>
    <!-- <v-app-bar> -->
    <v-btn href="/blog" disabled>
      <span>Home</span>
    </v-btn>
    <v-btn href="/blog" disabled>
      <span>Tư vấn</span>
    </v-btn>
    <v-btn href="/blog" disabled>
      <span>Tin tức</span>
    </v-btn>
    <v-btn href="/blog" disabled>
      <span>Tiết kiệm</span>
    </v-btn>
    <v-menu open-on-hover offset-y>
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" fab>
          <span>Vay vốn</span>
        </v-btn>
      </template>
      <v-card class="mx-auto" max-width="344" outlined>
        <v-list-item v-for="(option, index) in options" :key="index">
          <router-link :to="option.link"
            ><v-list-item-title class="select-item">{{
              option.title
            }}</v-list-item-title></router-link
          >
        </v-list-item>
      </v-card>
    </v-menu>
    <v-btn href="/blog" disabled>
      <span>Giới thiệu</span>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: "AppBar",
  data: () => ({
    options: [
      { title: "Đăng ký", link: "/registration" },
      { title: "Khai báo thông tin", link: "/report-information" },
      { title: "Vay lãi", link: "/interest-loans" },
      { title: "Trả tiền cho ngân hàng", link: "/pay-the-loan" },
      { title: "Tính lãi", link: "/interest-calculation" },
    ],
  }),
};
</script>

<style scoped>
a {
  text-decoration: none;
}
.select-item {
  color: #949494;
}
.select-item:hover {
  color: #3f3d3d;
}
</style>