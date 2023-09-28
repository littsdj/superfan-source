<template>
  <div id="titleBar">
        <h1>Connecting Fans and Their Favorite Bands!!!</h1>
        
        <input type="text" id="searchBar" class="default" v-model="search" v-on:click="clearDefaultSearch" v-on:blur="restoreDefaultSearch" />
        <button id="button">Search</button>
        
    </div>
</template>

<script>
export default {
    data() {
        return {
            search: 'search bands'
        }
    },
    methods: {
    clearDefaultSearch() {
      if (this.search === "search bands") {
        this.search = "";
        const searchBar = document.querySelector("#searchBar");
        searchBar.classList.remove("default");
      }
    },
    restoreDefaultSearch() {
      if (this.search === "") {
        this.search = "search bands";
        const searchBar = document.querySelector("#searchBar");
        searchBar.classList.add("default");
      }
    },
  },
}
</script>

<style >
#titleBar {
    display: flex;
    
    background: white;
    opacity: .8;
}
#searchBar{
  height: 30px;
  width: 300px;
} 
#button {
  justify-content: flex-end;
  height: 50px;
  width: 75px;
}

.default {
  color: gray;
}
</style>