<template>
    <div id="content">
        <title-bar />
        <sidebar />
        <photo-display />
    </div>
</template>

<script>
import Sidebar from '../components/sidebar.vue'
import TitleBar from '../components/TitleBar.vue'
import PhotoDisplay from '../components/PhotoDisplay.vue'


export default {
    components: { Sidebar, TitleBar, PhotoDisplay },
    
}
</script>

<style>

</style>