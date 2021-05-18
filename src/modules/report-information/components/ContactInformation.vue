<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-card-text>
      <v-container>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informContact.email"
              label="Email *"
              required
              :rules="emailRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informContact.phoneNumber"
              :counter="10"
              label="Số điện thoại *"
              required
              :rules="phoneRules"
            />
          </v-col>
        </v-row>
        <v-row class="justify-space-between">
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informContact.hometown"
              :counter="255"
              label="Quê quán *"
              required
              :rules="stringRules"
            />
          </v-col>
          <v-col cols="12" sm="6" md="5">
            <v-text-field
              outlined
              v-model="informContact.address"
              :counter="255"
              label="Địa chỉ hiện tại *"
              required
              :rules="stringRules"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
  </v-form>
</template>

<script>
import * as check from "../../../helper/validation";

export default {
  name: "ContactInformation",
  props: {
    informContact: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      stringRules: [
        (value) => check.validRequired(value),
        (value) => check.validLengthMax(value, 255),
      ],
      phoneRules: [
        (value) => check.validRequired(value),
        (value) => check.validPhone(value),
      ],
      emailRules: [
        (value) => check.validRequired(value),
        (value) => check.validEmail(value),
      ],
      valid: false,
    };
  },
  methods: {
    close() {
      this.$refs.form.reset();
      this.$emit("close", false);
    },
    save() {
      if (this.$refs.form.validate()) {
        this.$emit("save", true);
      }
    },
    allowedDate(val) {
      return new Date(val) <= new Date();
    },
  },
};
</script>

<style scoped>
.subheader {
  font-size: 20px;
}
hr {
  margin-bottom: 3rem;
  margin-top: 0rem;
}
</style>