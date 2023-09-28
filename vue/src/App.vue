
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
  body {
    margin: 0px;
  }
   .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 2fr 1fr;
    gap: 2px;
  }

  #app, .app-font {
    font-family: Verdana, Geneva, Tahoma, sans-serif;
  }
  button {
  margin-bottom: 20px;
  height: 50px;
  background-color: rgb(127, 255, 0);
  font-size: 13pt;
  border-radius: 20px;
  font-weight: bold;
  text-transform: uppercase;
  }
</style>

