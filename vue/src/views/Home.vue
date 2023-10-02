<template>
  <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />
    <sidebar class="sidebar" />
    <div class="content">
      <p>WELCOME!</p>
    </div>
  </div>
</template>

<script>
import sidebar from "../components/sidebar.vue";
import TitleBar from "../components/TitleBar.vue";

export default {
  components: { sidebar, TitleBar },
  name: "home",
};
</script>

<style scoped>
.background {
  background: url("../images/boombox.jpg");
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

.content {
  display: flex;
  justify-content: center;
}

.header {
  grid-area: header;
}

.sidebar {
  grid-area: navigation;
}

.content {
  grid-area: content;
}

/* .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 2fr 1fr;
    gap: 2px;
  } */
</style>