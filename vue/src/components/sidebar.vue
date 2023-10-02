<template>
  <div class="sidebar">
    <div class="navLink">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    </div>
    <div class="navLink">
      <a href="">Messages</a>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'managed', params: { userId: $store.state.user.id }}">Managed Bands</router-link>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'newBand' }">New Band</router-link>
    </div>
    <div class="navLink">
      <a href="">My Account</a>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>
.sidebar {
  display: flex;
  flex: 1 0 0;
  background-color: rgba(73, 218, 198, 0.6);
  padding: 20px;
  flex-direction: column;
  margin: 20px;
  justify-content: space-around;
  height: 60vh;
  border-radius: 20px;
}

.navLink {
  padding-top: 15px;
  padding-bottom: 15px;
  text-align: center;
  font-size: 20pt;
  font-family: fantasy;
  opacity: 1;
  background-color: rgb(255, 105, 180, 1);
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