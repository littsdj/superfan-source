<template>
    <div id="photoContainer">
        <h1> Band Photos </h1>
        <label for="uploadGalleryPhoto">
            /* TODO: Properly implement v-if boolean here.  */
        <input type="file" name="uploadGalleryPhoto" v-if="userIsOwner">
            /* TODO: Properly implement v-for on galleryPhoto calling endpoint for band photos */
        <div class="galleryPhoto">
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>