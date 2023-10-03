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
      <a href="">My Account</a>
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
  background-color: #dd9bc6ee;
}
.sidebar-rock {
  background-color: green;
}
.sidebar-country {
  background-color: yellow ;
}
.sidebar-jazz {
  background-color: purple;
}
.sidebar-electronic {
  background-color: pink;
}
.sidebar-hiphop {
  background-color: fuchsia;
}
.sidebar-world {
  background-color: turquoise;
}
.sidebar-experimental {
  background-color: cyan;
}
.sidebar-latin {
  background-color: burlywood;
}
.sidebar-metal {
  background-color: hotpink;
}

.sidebar-generic > * {
  background-color: rgb(255, 105, 180, 1);
}
.sidebar-pop  * {
  background-color: #f04620;
  color: #f7e8c4
}
.sidebar-rock > * {
  background-color: yellow;
}
.sidebar-country > * {
  background-color: red;
}
.sidebar-jazz > * {
  background-color: grey;
}
.sidebar-electronic > * {
  background-color: whitesmoke;
}
.sidebar-hiphop > * {
  background-color: blue;
}
.sidebar-world > * {
  background-color: yellow;
}
.sidebar-experimental > * {
  background-color: aquamarine;
}
.sidebar-latin > * {
  background-color: pink;
}
.sidebar-metal > * {
  background-color: blue;
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