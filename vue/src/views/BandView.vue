<template>
  <div class="background"> <!-- background image is stored  -->
        <title-bar class="header"/>
        
        <sidebar class="sidebar"/>
      
        <band-page class="content"/>
        
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
    background-position: cover;
    position: fixed;
    height:100vh;
    width: 100vw;
    display:grid;
    grid-template-columns: 300px 1fr 100px;
    grid-template-rows: 100px 1fr 50px ;
    grid-template-areas: "header header header"
                          "navigation content . "
                          ". . .";
    gap: 40xp
  }
  
  /* .body {
    
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 5fr 1fr;
    gap: 2px;
    grid-template-areas: 
    "sidebar content .";
  } */
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