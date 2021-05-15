<template>
  <v-card class="container">
    <v-card-title class="justify-center">
      <span class="font-weight-regular display-2">Đăng ký thông tin</span>
    </v-card-title>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card-text>
        <v-container fill-height>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.name"
                :counter="255"
                label="Họ tên *"
                required
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-menu
                v-model="menu"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    outlined
                    v-model="informPersonal.birthday"
                    label="Ngày sinh"
                    append-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  />
                </template>
                <v-date-picker
                  :allowed-dates="allowedDate"
                  v-model="informPersonal.birthday"
                />
              </v-menu>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.identity"
                :counter="12"
                label="Số chứng minh thư/ Căn cước công dân *"
                required
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                outlined
                v-model="informPersonal.job"
                label="Công việc hiện tại *"
                required
              />
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-form>
    <v-card-actions>
      <v-spacer />
      <v-btn elevation="2" text @click="close"> Hủy </v-btn>
      <v-spacer />
      <v-btn color="success" @click="save"> Đăng kí </v-btn>
      <v-spacer />
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "BorrowerInformation",
};
</script>

<style>
</style>