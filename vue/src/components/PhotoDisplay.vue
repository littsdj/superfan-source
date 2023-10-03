<template>
  <div id="photoContainer">
    <h1>Band Photos</h1>
    
    <label for="uploadGalleryPhoto" v-if="userIsOwner"> Select Photo </label>  
    <input type="file" name="uploadGalleryPhoto" v-if="userIsOwner" />
<!--These are buttons made to be labels so we can keep them in line with universal styling -->
    <label for="upload-button" class="upload-label"> Upload Photo </label>
    <button class="select-file-button" name="upload-button" v-show="false" v-on:click="uploadImage()"/>

    <div class="galleryPhoto" v-for="image in galleryImages" v-bind:key="image.imageId">
        <img class="imageData" v-bind:src="generateImageSource(image)" alt=""/>
    </div>

  </div>
</template>

<script>
import PhotoService from "../services/PhotoService.js";
import BandService from "../services/BandService.js";

export default {
  data() {
    return {
      galleryImages: "",
      BandOwnerId: ''
    };
  },
  computed: {
      userIsOwner() {
          return (this.BandOwnerId === this.$store.state.user.id);
      }
  },
  created() {
    PhotoService.getAllBandPhotosByBandId(this.$route.params.bandId).then( (response) => {
        this.galleryImages = response.data;
      }
    );
    BandService.getBandOwnerIdByBandId(this.$route.params.bandId).then( (response) => {
        this.ownerId = response.data;
        }
    )
  },
  methods: {
      generateImageSource(image) {
          const dotIndex = image.fileName.lastIndexOf('.');
          const extension = image.filename.substring(dotIndex + 1);
          return `data:image/${extension};base64, ${image.imageData}`;
      },
      uploadImage() {
          const fileInfo = this.$refs.fileInput.files[0];
          let formData = new FormData();
          formData.append("file", fileInfo);
          PhotoService.addImageToBandGallery(this.route.params.bandId, formData);
      }
  }
};
</script>

<style>


</style>