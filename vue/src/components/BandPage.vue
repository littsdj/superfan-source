<template>
  <div id="content">
    <div class="bandInfo">
      <img
        id="coverPhoto"
        src="../images/dancingJukeBox.gif"
        alt="Loading"
        v-show="isLoading"
      />
      <div v-show="bandFound && !isLoading">
        <div id="bandTitleBox">
          <h1 id="bandName">{{ band.bandName }}</h1>
        </div>
        <div><img :src="imgSrcData" alt="" /></div>
        <h2>About {{ band.bandName }}</h2>
        <p id="description">{{ band.description }}</p>
        <input class="button" type="file" id="file" ref="fileInput" />
        <button class="button" id="uploadButton" v-on:click="uploadImage()">
          Upload Image
        </button>
      </div>
      <h1 v-show="!bandFound && !isLoading">BAND PAGE NOT FOUND</h1>
    </div>
  </div>
</template>

<script>
import BandService from "../services/BandService";
export default {
  data() {
    return {
      band: {
        bandName: "",
        bandId: "",
        description: "",
        genreId: "",
        subgenres: "",
        bandImage: {
          imageId: "",
          fileName: "",
          imageData: "",
        },
      },
      bandFound: true,
      isLoading: false,
    };
  },
  computed: {
    imgSrcData() {
      if (this.band.bandImage && this.band.bandImage.fileName) {
        const dotIndex = this.band.bandImage.fileName.lastIndexOf(".");
        const extension = this.band.bandImage.fileName.substring(dotIndex + 1);
        //return `data:image/${extension};base64, ${this.bandImage.imageData}`;
        return `data:image/${extension};base64, ${this.band.bandImage.imageData}`;
      }
      return "";
    },
  },

  created() {
    this.isLoading = true;
    const name = this.$route.params.bandName;
    BandService.getBand(name)
      .then((response) => {
        this.band = response.data;
        this.isLoading = false;
      })
      .catch((error) => {
        if (error.response) {
          this.bandFound = false;
          this.isLoading = false;
        }
      });
    // BandService.getBandCoverImage(this.band.bandId).then(response => {
    //     this.band.photo = response.data;
    // }).catch( error => {
    //     if (error.response) {
    //         this.band.photo = 'No Photo Available'
    //     }
    // })
  },
  methods: {
    uploadImage() {
      const fileInfo = this.$refs.fileInput.files[0];
      let formData = new FormData();
      formData.append("file", fileInfo);
      BandService.postBandCoverImage(this.band.bandId, formData).then(
        (response) => {
          this.band.bandImage = response.data;
        }
      );
    },
  },
};
</script>

<style>
#bandTitleBox {
  background-color: rgb(159, 207, 97);
  border-radius: 20px;
  width: 80%;
}
#bandName {
  text-align: center;
  font-size: 36pt;
}
.bandInfo {
  background-color: rgb(255, 105, 180, 0.9);
  display: flex;
  flex-direction: column;
  align-items: center;
}
#coverPhoto {
  width: 200px;
}
#content {
  overflow-y: auto;
}
</style>