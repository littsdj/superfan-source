<template>
    <div id="content">
        <title-bar id="titlebar" />
        <sidebar id="sidebar" />
        <photo-display id="photoDisplay" />
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