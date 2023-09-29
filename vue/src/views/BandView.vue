<template>
  <div class="background" v-bind:class="{'background-pop': bandGenre == 1, 'background-generic': bandGenre != 1}"> <!-- background image is stored  -->
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
  computed: {
    bandGenre() {
      return this.$store.state.currentBand.genreId ? this.$store.state.currentBand.genreId : {}},
  }
}
</script>

<style scoped>
 .background{
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
    gap: 40px;
  }
  .background-pop {
    background: url('../images/world.jpg');
  }
  .background-generic {
    background: url('../images/login-background.jpg');
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