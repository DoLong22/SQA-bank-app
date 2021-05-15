<template>
    <v-alert :type="type" transition="fade-transition">
        {{ message }}
    </v-alert>
</template>
<script>
export default {
    computed: {
        type() {
            return this.$store.state.notification.type ?? 'success';
        },
        message() {
            return this.$store.state.notification.message;
        },
        timeOut() {
            return this.$store.state.notification.timeOut ?? 2000;
        },
    },
    mounted() {
        setTimeout(() => {
            this.$store.dispatch('hiddenNotification');
        }, this.timeOut);
    },
};
</script>

<style scoped>
.v-alert.v-sheet.theme--dark {
    position: absolute;
    z-index: 1000;
    max-width: max-content;
    min-width: 12.5rem;
    top: 40%;
    font-size: 1rem;
    left: 40%;
}
</style>
