<template>
  <div
    class="sidebar"
    v-bind:class="{
      'sidebar-generic': bandGenreId == 0,
      'sidebar-pop': bandGenreId == 1,
      'sidebar-rock': bandGenreId == 2,
      'sidebar-country': bandGenreId == 3,
      'sidebar-jazz': bandGenreId == 4,
      'sidebar-electronic': bandGenreId == 5,
      'sidebar-hiphop': bandGenreId == 6,
      'sidebar-world': bandGenreId == 7,
      'sidebar-experimental': bandGenreId == 8,
      'sidebar-latin': bandGenreId == 9,
      'sidebar-metal': bandGenreId == 10,
    }"
  >
    <div class="navLink">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{name: 'inbox' }">Messages</router-link>
    </div>
    <div class="navLink">
      <router-link
        v-bind:to="{
          name: 'managed',
          params: { userId: $store.state.user.id },
        }"
        >Managed Bands</router-link
      >
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'newBand' }">New Band</router-link>
    </div>
    <div class="navLink">
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    bandGenreId() {
      if (this.$route.name == "bandPageView") {
        return this.$store.state.currentBand.genreId;
      } else {
        return 0;
      }
    },
  },
};
</script>

<style scoped>
.sidebar {
  display: flex;
  flex: 1 0 0;

  padding: 20px;
  flex-direction: column;
  margin: 20px;
  justify-content: space-around;
  height: 60vh;
  border-radius: 20px;
}
.sidebar-generic {
  background-color: rgba(73, 218, 198, 0.6);
}
.sidebar-pop {
  background-color: #dd9bc6d7;
}
.sidebar-rock {
  background-color: #574158d0;

}
.sidebar-country {
  background-color: #9daf4ed7 ;
}
.sidebar-jazz {
  background-color: #493368d8;
}
.sidebar-electronic {
  background-color: rgba(251, 184, 195, 0.87);
}
.sidebar-hiphop {
  background-color: #562583e5;
}
.sidebar-world {
  background-color: rgba(87, 208, 230, 0.863);
}
.sidebar-experimental {
  background-color: #4f632ddf;
}
.sidebar-latin {
  background-color: rgba(73, 189, 175, 0.863);
}
.sidebar-metal {
  background-color: #4f1627cb;
}

.sidebar-generic > * {
  background-color: rgb(255, 105, 180, 1);
}
.sidebar-pop  * {
  background-color: #f04620;
  color: #f7e8c4;
}
.sidebar-rock  * {
  color: #c5ac8e;
  background-color: #882a40;
}
.sidebar-country * {
  color: #0b6e59d8;
  background-color: #d4a43c;
}
.sidebar-jazz * {
  color: rgb(219, 153, 30);
  background-color: grey;
}
.sidebar-electronic * {
  color: rgba(114, 5, 114, 0.924);
  background-color: whitesmoke;
}
.sidebar-hiphop * {
  color: #000000;
  background-color: #fdb927;
}
.sidebar-world * {
  color: black;
  background-color: yellow;
}
.sidebar-experimental * {
  color: rgb(211, 208, 198);
  background-color: #cd4f39;
}
.sidebar-latin * {
  color: rgb(253 241 227);
  background-color: rgba(215, 104, 24, 0.911);
}
.sidebar-metal * {
  color: rgb(173, 172, 182);
  background-color: rgb(92 54 49);
}

.navLink {
  padding-top: 15px;
  padding-bottom: 15px;
  text-align: center;
  font-size: 20pt;
  font-family: fantasy;
  opacity: 1;

  border-radius: 20px;
  border-color: black;
}

.sidebar * {
  text-align: center;
  align-items: center;
  text-decoration: none;
  /* flex-basis: 100% ; */
  max-height: 100px;
}
</style>