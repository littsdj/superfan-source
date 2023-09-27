<template>
  <div id="app" class="hiphop">
    <div id="titleBar">  
      <h1> 
        Connecting Fans and Their Favorite Bands!!!
      </h1>
      <form>
        <input type="text" id="searchBar" class="default" v-model="search" v-on:click="clearDefaultSearch" v-on:blur="restoreDefaultSearch">
        <button> Search </button>
      </form>
    </div>

    <div class="body">
      <sidebar/>
      <router-view />
    </div>
  </div>

</template>



<script>
import sidebar from './components/sidebar.vue';

    export default {
  components: { sidebar },
      data() {  
        return {
          search: "search bands"
        }
      },
      methods: {
        clearDefaultSearch() {
          if (this.search === "search bands"){
            this.search = '';
            const searchBar = document.querySelector("#searchBar")
            searchBar.classList.remove('default');
          }
        },
        restoreDefaultSearch() {
          if (this.search === ""){
            this.search = "search bands";
            const searchBar = document.querySelector('#searchBar');
            searchBar.classList.add('default');
          }
        }
      }
    }
</script>

<style scoped>
  #titleBar {
    display: flex;
    justify-content: justify-left;
    align-items: center;
    background-color: rgb(252, 141, 6, 0.25);
    
    padding: 10px;
    gap: 50px;
  }

  /* CSS styles for the sidebar go here */


.default {
  color: gray;
}

.body {
  display: grid;
  align-items: center;
  justify-content: center;
  grid-template-columns: 1fr 2fr 1fr;
  gap: 2px;
  
}

#app {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
.hiphop {
  background: url('./images/Yellow-lowrider.png');
  background-size: cover;
  background-position: center;
  min-height: 100vh;
}
.login {
  background: url('./images/login-background.png')
}
</style>
