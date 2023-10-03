<template>
  <div id="gallery-content">
    <h1>Band Photos</h1>

    <div id="button-holder">
    <label for="file-picker" id="fileButton" class="gallery-file-select" v-if="userIsOwner"> Select Photo </label>
    <input class="button" ref="fileInput" type="file" id="file-picker" name="uploadGalleryPhoto" v-show="userIsOwner" />
    <!--These are buttons made to be labels so we can keep them in line with universal styling -->
    <label for="upload-gallery-photo" class="upload-label" v-if="userIsOwner"> Upload Photo </label>
    <button id="upload-gallery-photo" v-on:click="uploadImage()" v-if="userIsOwner"> Click me to submit </button>
    </div>

    <div id="photoContainer">
      <div class="galleryPhoto" v-for="image in galleryImages" v-bind:key="image.imageId">
        <img class="imageData" v-bind:src="generateImageSource(image)" alt="" />
      </div>
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
      bandOwnerId: "",
    };
  },
  computed: {
    userIsOwner() {
      return this.bandOwnerId === this.$store.state.user.id;
    },
  },
  created() {
    PhotoService.getAllBandImagesByBandId(this.$route.params.bandId).then(
      (response) => {
        this.galleryImages = response.data;
      }
    );
    BandService.getBandOwnerIdByBandId(this.$route.params.bandId).then(
      (response) => {
        this.bandOwnerId = response.data;
      }
    );
  },
  methods: {
    generateImageSource(image) {
      const dotIndex = image.fileName.lastIndexOf(".");
      const extension = image.fileName.substring(dotIndex + 1);
      return `data:image/${extension};base64, ${image.imageData}`;
    },
    uploadImage() {
      const fileInfo = this.$refs.fileInput.files[0];
      let formData = new FormData();
      formData.append("file", fileInfo);
      PhotoService.addImageToBandGallery(this.$route.params.bandId, formData).then( (response) => {
        if (response.data){
          window.location.reload();
        }
      })
      
    },
  },
};
</script>

<style scoped>
#gallery-content {
  background-color: rgb(128, 128, 128, 0.8);
  display: flex;
  flex-direction: column;
  overflow-y: auto;
  border-radius: 50px;
  align-items: center;
}

#photoContainer {
  
  display: flex;
  flex-wrap: wrap;
  flex-basis: 100%;
  justify-content: space-around;
  align-items: center;
}

.imageData {
  max-width: 400px;
  max-height: 500px;
  margin: 10px;
  margin-top: 25px;
  border-radius: 50px;
  border: 15px;
  border-style: solid;
  border-color: orange;
}
</style>