<template>
  <div class="background"> <!-- background image is stored  -->
        <title-bar/>
        <div class="body">
            <sidebar />
      
            <new-band-form/>
        </div>
    </div>
</template>

<script>
import NewBandForm from '../components/NewBandForm.vue'
import sidebar from '../components/sidebar.vue'
export default {
    components: {NewBandForm , sidebar}

}
</script>

<style>
    .background {
        background: url('../images/Yellow-lowrider.png');
        background-size: cover;
        background-position: center;
        min-height: 100vh;
    }


/* CSS styles for the sidebar go here */

.default {
  color: gray;
}

.body {
  display: grid;
  align-items: center;
  justify-content: center;
  grid-template-columns: 1fr 2fr 1fr;
  gap: 2px;
}

#app {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}
</style>