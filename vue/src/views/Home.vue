<template>
  <div class="background"> <!-- background image is stored  -->
        <title-bar/>
        <div class="body">
            <sidebar />
      
            <p>if you see this, you are logged in.</p>
        </div>
    </div>
</template>

<script>
import sidebar from '../components/sidebar.vue';
import TitleBar from '../components/TitleBar.vue';
export default {
  components: {sidebar, TitleBar},

  name: "home"
};
</script>

<style scoped>
  .background{
    background: url('../images/Yellow-lowrider.png');
    background-size: cover;
    background-position: center;
    min-height: 100vh;
  }
  
  .body {
    display: grid;
    align-items: center;
    justify-content: center;
    grid-template-columns: 1fr 2fr 1fr;
    gap: 2px;
  }
</style>