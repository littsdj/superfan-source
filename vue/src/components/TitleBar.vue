<template>
  <div id="titleBar">
    <h1 id="header">SUPERFAN SOURCE</h1>
    <div class="searchElement">
      <input
        type="text"
        id="searchBar"
        class="default"
        v-model="search"
        v-on:click="clearDefaultSearch"
        v-on:blur="restoreDefaultSearch"
      />
      <button class="button" id="button" v-on:click.stop.prevent="executeSearch()">Search</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "search bands",
    };
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
    executeSearch(){

      this.$router.push({name: "search", params: {searchTerms : this.search.toLowerCase()}})
    }
  }
};
</script>

<style >
#titleBar {
  display: flex;
  align-items: center;
  background: rgb(
    166,
    228,
    43,
    0.6
  );
  justify-content: space-evenly;
  margin: 0;
  height: 100px;
}
#searchBar {
  height: 30px;
  width: 300px;
}
#button {
  justify-content: flex-end;
  height: 50px;
  width: 100px;
}

.default {
  color: gray;
}

#header{
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-style: italic;
  font-size: 64px;
}
</style>