<template>
  <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />
    <sidebar class="sidebar" />
    <div class="content">
      <div id="homeHeader"><h1>Bands You Follow</h1></div>
      <div id="noFollowed" v-if="!hasFollowed"><h2>Go discover some bands to see them here!</h2></div>
      <div class="followedBand" v-for='band in followedBands' v-bind:key="band.bandId">
        <searched-item v-bind:band="band" />
      </div>
    </div>
  </div>
</template>

<script>
import sidebar from "../components/sidebar.vue";
import TitleBar from "../components/TitleBar.vue";
import BandService from "../services/BandService.js";
import SearchedItem from "../components/SearchedItemNew.vue"

export default {
  components: { sidebar, TitleBar, SearchedItem },
  name: "home",
  data() {
    return {
      followedBands: ''
    }
  },
  created() {
    BandService.getAllUserFollowing(this.$store.state.user.id).then( (response) => {
      if (response.data) {
        this.followedBands = response.data
      } else {
        this.followedBands = ''
      }
    }).catch( (error) =>
      window.alert(error.status)
    )
  },
  computed: {
    hasFollowed() {
       return (this.followedBands.length > 0)
    }
  }
};
</script>

<style scoped>
.background {
  background: url("../images/boombox.jpg");
  background-size: cover;
  background-position: cover;
  position: fixed;
  height: 100vh;
  width: 100vw;
  display: grid;
  grid-template-columns: 300px 1fr 100px;
  grid-template-rows: 100px 1fr 50px;
  grid-template-areas:
    "header header header"
    "navigation content . "
    ". . .";
  gap: 40px;
}

.header {
  grid-area: header;
}

.sidebar {
  grid-area: navigation;
}

.content {
  grid-area: content;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow-y: auto;
}

.followedBand {
  background-color: rgb(172, 16, 102, 0.7);
  color: cyan;
  border-radius: 50px;
  border-style: solid;
  border-color: goldenrod;
  text-align: center;
  width: 60%;
  margin-top: 10px;
  margin-bottom: 10px;
}

#noFollowed {
  background-color: rgb(172, 16, 102, 0.7);
  color: cyan;
  border-radius: 50px;
  border-style: solid;
  border-color: goldenrod;
  text-align: center;
  width: 500px;
  margin-top: 10px;
  margin-bottom: 10px;
}

#homeHeader {
  background-color: rgb(172, 16, 102, 0.7);
  color: cyan;
  border-radius: 50px;
  border-style: groove;
  border-color: goldenrod;
  text-align: center;
  width: 60%;
  margin-top: 10px;
  margin-bottom: 10px;
}

/* .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 2fr 1fr;
    gap: 2px;
  } */
</style>