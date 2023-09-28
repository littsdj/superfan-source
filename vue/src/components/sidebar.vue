<template>
  <div class="sidebar">
    <div class="navLink">  
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    </div>
    <div class="navLink">
      <a href="">Messages</a>
    </div>
    <div class="navLink">
      <a href="">My Bands</a>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'newBand'}">New Band</router-link>
    </div>
    <div class="navLink">
      <a href="">My account</a>
    </div>
    <div class="navLink">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
  </div>
    
</template>

<script>
export default {
    
}
</script>

<style scoped>
  .navLink{
    padding-top:15px;
    padding-bottom: 15px;
    border: ;
  }
  .sidebar {
  display: flex;
  flex: 1 0 0; 
  background-color: whitesmoke;
  padding: 20px;
  flex-direction: column;
  opacity: 0.8;
  margin: 20px;
  justify-content: around;
}
    .sidebar *{
      text-align: center;
      align-items:  center;

      text-decoration: none;
      /* flex-basis: 100% ; */
      max-height: 100px;

    }
</style>