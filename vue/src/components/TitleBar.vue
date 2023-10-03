<template>
  <div id="titleBar" 
  v-bind:class="{
      'title-bar-generic': bandGenreId == 0,
      'title-bar-pop': bandGenreId == 1,
      'title-bar-rock': bandGenreId == 2,
      'title-bar-country': bandGenreId == 3,
      'title-bar-jazz': bandGenreId == 4,
      'title-bar-electronic': bandGenreId == 5,
      'title-bar-hiphop': bandGenreId == 6,
      'title-bar-world': bandGenreId == 7,
      'title-bar-experimental': bandGenreId == 8,
      'title-bar-latin': bandGenreId == 9,
      'title-bar-metal': bandGenreId == 10,
    }">
    <h1 id="header">SUPERFAN SOURCE</h1>
  <div id="titleBar">
    <h1 id="header"> SUPERFAN SOURCE </h1>
    <div class="searchElement">
      <input type="text" id="searchBar" class="default" v-model="search" v-on:click="clearDefaultSearch" v-on:blur="restoreDefaultSearch" />
      <label for="searchButton" id="searchLabel" class="select-file-button"> SEARCH </label>
      <button class="button" id="searchButton" v-on:click.stop.prevent="executeSearch()" v-show="false"> Search </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "Search Bands",
    };
  },
  methods: {
    clearDefaultSearch() {
      if (this.search === "Search Bands") {
        this.search = "";
        const searchBar = document.querySelector("#searchBar");
        searchBar.classList.remove("default");
      }
    },
    restoreDefaultSearch() {
      if (this.search === "") {
        this.search = "Search Bands";
        const searchBar = document.querySelector("#searchBar");
        searchBar.classList.add("default");
      }
    },
    executeSearch() {
      this.$router.push({
        name: "search",
        params: { searchTerms: this.search.toLowerCase() },
      });
      location.reload();
    },
  },
  computed: {
    bandGenreId() {
      if (this.$route.name == "bandPageView") {
        return this.$store.state.currentBand.genreId;
      } else {
        return 0;
      }
    },
  }
};
</script>

<style scoped>
#titleBar {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  margin: 0;
  height: 100px;
}
.title-bar-generic {
  background-color: rgba(73, 218, 198, 0.6);
}
.title-bar-pop {
  background-color: red;
}
.title-bar-rock {
  background-color: green;
}
.title-bar-country {
  background-color: yellow ;
}
.title-bar-jazz {
  background-color: purple;
}
.title-bar-electronic {
  background-color: pink;
}
.title-bar-hiphop {
  background-color: fuchsia;
}
.title-bar-world {
  background-color: turquoise;
}
.title-bar-experimental {
  background-color: cyan;
}
.title-bar-latin {
  background-color: burlywood;
}
.title-bar-metal {
  background-color: hotpink;
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

#header {
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
  font-style: italic;
  font-size: 64px;
}
</style>