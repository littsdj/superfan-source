<template>
  <div id="app" class="hiphop">
    <div id="titleBar">  
      <h1> 
        Title Goes Here!!!!!!
      </h1>
      <form>
        <input type="text" id="searchBar" class="default" v-model="search" v-on:click="clearDefaultSearch" v-on:blur="restoreDefaultSearch">
        <button> SearchIcon </button>
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
    background-color: cadetblue;
    opacity: 0.7;
    padding: 20px;
    gap: 20px;
  }

  /* CSS styles for the sidebar go here */



.default {
  color: gray;
}

.body {
  display: grid;
  grid-template-columns: 1fr 5fr;
  gap: 20px;
  height: 90vh;
}

#app {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
.hiphop {
  background: url('./images/Yellow-lowrider.png')
}
</style>
