<template>
  <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />
    <sidebar class="sidebar" />
  </div>
</template>

<script>
import sidebar from "../components/sidebar.vue";
import TitleBar from "../components/TitleBar.vue";

export default {
  components: { sidebar, TitleBar },
  name: "inbox",
  data() {
    return {
      messages: [],
    };
  },
  methods: {},
};
</script>

<style scoped>
.background {
  background: url("../images/messages.jpg");
  background-size: cover;
  background-position: cover;
  position: fixed;
  height: 100vh;
  width: 100vw;
  display: grid;
  grid-template-columns: 300px 1fr 100px;
  grid-template-rows: 100px 1fr 50px;
  grid-template-areas:
    "header header header"
    "navigation content . "
    ". . .";
  gap: 40px;
}

.titlebar {
    grid-area: header;
}

.sidebar {
    grid-area: navigation;
}
</style>
