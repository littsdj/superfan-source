<template>
  <div class="sidebar">
    <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    <a href="">Messages</a>
    <a href="">My Bands</a>
    <a href="">New Band</a>
    <a href="">My account</a>
    <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
  </div>
    
</template>

<script>
export default {
    
}
</script>

<style>
    .sidebar {
  display: flex;
  flex: 0 0 250px; /* Sidebar width */
  background-color: whitesmoke;
  padding: 20px;
  flex-direction: column;
  opacity: 0.8;
  margin: 20px;
}
    .sidebar * {
        
    }
</style>