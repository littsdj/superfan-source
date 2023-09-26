<template>
  <div id="app">
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
      <div class="sidebar">
        <!-- Content for the sidebar goes here -->
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    
      </div>
      <router-view />
    </div>
  </div>

</template>



<script>
    export default {
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
    padding: 20px;
    gap: 20px;
  }

  /* CSS styles for the sidebar go here */
.sidebar {
  display: flex;
  flex: 0 0 250px; /* Sidebar width */
  background-color: pink;
  padding: 20px;
  flex-direction: column;
}

.default {
  color: gray;
}

.body {
  display: grid;
}

#app {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
</style>
