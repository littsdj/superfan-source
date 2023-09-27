<template>
  <div :class="layoutClass" :style="backgroundStyle">
    <router-view />
  </div>
</template>

<script>
export default {
  computed: {
    layoutClass() {
      const route = this.$route;
      return route.meta.layoutClass || ""; // Default to an empty string if no class is specified
    },
    backgroundStyle() {
      const route = this.$route;
      const backgroundImage = route.meta.backgroundImage || "";
      return backgroundImage ? `background-image: url(${require('@/assets/images/' + backgroundImage)})` : "";
    },
  },
};
</script>

<style scoped>
/* Define common layout styles here */
</style>
