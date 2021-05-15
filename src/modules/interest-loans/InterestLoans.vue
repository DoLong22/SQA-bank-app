<template>
  <v-card>
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Cho vay</span>
    </v-card-title>

    <v-card-text>
      <!-- <v-container> -->
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
        :items="desserts"
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
            <td>{{ item.calories }}</td>
            <td>{{ item.fat }}</td>
            <td>{{ item.carbs }}</td>
            <td>{{ item.protein }}</td>
            <td>{{ item.iron }}</td>
          </tr>
        </template>
      </v-data-table>
    </v-card-text>

    <v-card-actions>
      <v-btn depressed color="primary" @click="save"> Save </v-btn>
      <v-spacer></v-spacer>
      <v-pagination v-model="page" :length="pageCount"></v-pagination>
    </v-card-actions>
  </v-card>
</template>
<script>
export default {
  name: "InterestLoans",
  data: () => ({
    page: 1,
    pageCount: 0,
    itemsPerPage: 10,
    search: "",
    headers: [
      {
        text: "Dessert (100g serving)",
        align: "start",
        sortable: false,
        value: "name",
      },
      { text: "Calories", value: "calories" },
      { text: "Fat (g)", value: "fat" },
      { text: "Carbs (g)", value: "carbs" },
      { text: "Protein (g)", value: "protein" },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      name: "",
      calories: 0,
      fat: 0,
      carbs: 0,
      protein: 0,
    },
  }),

  created() {
    this.initialize();
  },

  methods: {
    initialize() {
      this.desserts = [
        {
          name: "Frozen Yogurt",
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
        },
        {
          name: "Ice cream sandwich",
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
        },
        {
          name: "Eclair",
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
        },
        {
          name: "Cupcake",
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
        },
        {
          name: "Gingerbread",
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
        },
        {
          name: "Jelly bean",
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
        },
        {
          name: "Lollipop",
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
        },
        {
          name: "Honeycomb",
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
        },
        {
          name: "Donut",
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
        },
        {
          name: "KitKat",
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
        },
      ];
    },
    selectedRow(value) {
      this.editedItem = value;
      console.log(value);
    },
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      console.log(this.editedItem);
    },

    save() {
      this.$router.push("/interest-loans/register");
      // if (this.editedIndex > -1) {
      //   Object.assign(this.desserts[this.editedIndex], this.editedItem);
      // } else {
      //   this.desserts.push(this.editedItem);
      // }
      // this.close();
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