<template>
  <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />

    <sidebar class="sidebar" />

    <new-band-form class="content" />
  </div>
</template>

<script>
import NewBandForm from "../components/NewBandForm.vue";
import sidebar from "../components/sidebar.vue";
import TitleBar from "../components/TitleBar.vue";
export default {
  components: { TitleBar, NewBandForm, sidebar },
};
</script>

<style>
.background {
  background: url("../images/Yellow-lowrider.png");
  background-size: cover;
  background-position: center;
  min-height: 100vh;
}

/* CSS styles for the sidebar go here */

.default {
  color: gray;
}
.background {
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
  gap: 40xp;
}
/* .body {
  display: grid;
  align-items: center;
  justify-content: center;
  grid-template-columns: 1fr 2fr 1fr;
  gap: 2px;
} */

#app {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
.header {
  grid-area: header;
  /* position: fixed; */
}
.sidebar {
  /* position: fixed; */
  grid-area: navigation;
}
.content {
  grid-area: content;
}
</style>