<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-card-text>
      <v-subheader class="subheader"> Thông tin cá nhân người thân</v-subheader>
      <v-divider></v-divider>
      <v-row class="justify-space-between">
        <v-col cols="12" sm="6" md="5">
          <v-text-field
            outlined
            v-model="informProtector.nameProtector"
            :counter="255"
            label="Họ tên *"
            required
            :rules="stringRules"
          />
        </v-col>
        <v-col cols="12" sm="6" md="5">
          <v-text-field
            outlined
            v-model="informProtector.phoneProtector"
            :counter="10"
            label="Số điện thoại *"
            required
            :rules="phoneRules"
          />
        </v-col>
        <v-col cols="12" sm="6" md="5">
          <v-text-field
            outlined
            v-model="informProtector.addressProtector"
            :counter="255"
            label="Địa chỉ hiện tại *"
            required
            :rules="stringRules"
          />
        </v-col>
      </v-row>
      <v-checkbox
        v-model="isConfirm"
        label="Tôi xác nhận rằng tôi đã đọc và đồng ý với các điều khoản?"
        required
        :rules="checkboxRules"
      ></v-checkbox>
    </v-card-text>
  </v-form>
</template>

<script>
import * as check from "../../../helper/validation";

export default {
  name: "ProtectorsInformation",
  props: {
    informProtector: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      checkboxRules: [
        (value) => {
          return (
            !!value || "Vui lòng xác nhận đã đọc và đồng ý với các điều khoản."
          );
        },
      ],
      stringRules: [
        (value) => check.validRequired(value),
        (value) => check.validLengthMax(value, 255),
      ],
      phoneRules: [
        (value) => check.validRequired(value),
        (value) => check.validPhone(value),
      ],
      valid: false,
      isConfirm: false,
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
