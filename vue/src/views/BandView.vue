<template>
  <div class="background"> <!-- background image is stored  -->
        <title-bar/>
        <div class="body">
            <sidebar class="sidebar"/>
      
            <band-page class="content"/>
        </div>
    </div>
</template>

<script>
import BandPage from '../components/BandPage.vue'
import sidebar  from '../components/sidebar.vue'
import TitleBar from '../components/TitleBar.vue'
export default {
  components: { BandPage, TitleBar, sidebar },

}
</script>

<style scoped>
 .background{
    background: url('../images/login-background.png');
    background-size: cover;
    background-position: center;
    min-height: 100vh;
  }
  
  .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 5fr 1fr;
    gap: 2px;
    grid-template-areas: 
    "sidebar content .";
  }
  .sidebar {
    display: fixed;
    grid-area: sidebar;
  }
  .content {
    grid-area: content;
  }
</style>