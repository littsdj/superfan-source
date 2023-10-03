<template>
  <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />
    <sidebar class="sidebar" />
    <div class="content">
      <div id="searchHeader">
        <h1>Managed Bands</h1>
      </div>
      <div
        class="ownedBandCard"
        v-for="band in ownedBands"
        v-bind:key="band.bandId"
      >
        <owned-band v-bind:band="band" />
      </div>
    </div>
  </div>
</template>

<script>
import TitleBar from "../components/TitleBar.vue";
import BandService from "../services/BandService";
import OwnedBand from "../components/SearchedItemNew.vue";
import sidebar from "../components/sidebar.vue";

export default {
  components: { TitleBar, OwnedBand, sidebar },
  data() {
    return {
      bandOwnerId: "",
      ownedBands: []
    };
  },
  created() {
    BandService.getBandOwnerIdByBandId(this.band.bandId).then((response) => {
      this.bandOwnerId = response.data;
    });
    BandService.getBandsByOwnerId(this.bandOwnerId).then( (response) => {
      this.ownedBands = response.data;
    })
  },
};
</script>

<style scoped>
.background {
  background: url("../images/concert-2.jpg");
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

.searchResult {
  width: 60%;
  background-color: rgb(145, 250, 250, 0.7);
  color: darkgreen;
  text-align: center;
  border-radius: 50px;
  border-style: solid;
  border-color: pink;
  margin-top: 10px;
  margin-bottom: 10px;
}

#searchHeader {
  width: 60%;
  background-color: rgba(251, 146, 255, 0.7);
  color: rgb(105, 36, 36);
  text-align: center;
  border-radius: 50px;
  border-style: solid;
  border-color: rgb(118, 212, 255);
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>