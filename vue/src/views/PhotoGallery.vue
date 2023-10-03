<template>
    <div class="background">
        <title-bar id="titlebar" />
        <sidebar id="sidebar" />
        <photo-display id="photoDisplay" />
    </div>
</template>

<script>
import Sidebar from '../components/sidebar.vue'
import TitleBar from '../components/TitleBar.vue'
import PhotoDisplay from '../components/PhotoDisplay.vue'


export default {
    components: { Sidebar, TitleBar, PhotoDisplay },
    
}
</script>

<style>
    .background {
  background: url("../images/search-results.jpg");
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
    grid-area: navigation;
}
</style>