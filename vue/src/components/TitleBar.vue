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
      <button id="button">Search</button>
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
  height: 40%;
  width: 75px;
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