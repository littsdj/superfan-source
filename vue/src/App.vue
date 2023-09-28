<template id="app">
  <div> 
    <div class="app-font">
  <router-view/>
  </div>
  </div>

</template>

<script>
export default {

}
</script>

<style>
   .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 2fr 1fr;
    gap: 2px;
  }

  #app, .app-font {
    font-family: sans-serif;
  }
</style>

