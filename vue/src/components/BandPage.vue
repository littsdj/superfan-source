<template>
  <div id="content">
    <div class="bandInfo">
      <img id="coverPhoto" src="../images/dancingJukeBox.gif" alt="Loading" v-show="isLoading" />
      <div class="bandPageBox" v-show="bandFound && !isLoading">
        <div id="bandTitleBox">
          <h1 id="bandName">{{ band.bandName }}</h1>
        </div>

        <div class="follow_unfollow" v-if="$store.state.token !== ''">
          <label id="followLabel" class="followLabel" for="followButton" v-on:click="toggleFollow()" v-show="!isFollowing">Follow</label>
          <button name="followButton" v-show="false">Follow</button>
          <label id="unfollowLabel" class="followLabel" for="unfollowButton" v-on:click="toggleFollow()" v-show="isFollowing">Unfollow</label>
          <button name="unfollowButton" v-show="false">Unfollow</button>
        </div>
        <img id="coverPhoto" :src="imgSrcData" alt="" />
        <h1>About {{ band.bandName }}</h1>
        <p id="description">{{ band.description }}</p>
        <!-- <input class="button" type="file" id="file" ref="fileInput" />
        -->
        <label for="file" id="fileButton" class="select-file-button">Select Cover Image</label>
        <input class="button" type="file" id="file" ref="fileInput" v-show="false" />
        <label for="uploadButton" id="uploadLabel" class="select-file-button">Upload Image</label>
        <button class="select-file-button" id="uploadButton" v-on:click="uploadImage()" v-show="false">Upload Image</button>
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
      //   band: {
      //     bandName: "",
      //     bandId: "",
      //     description: "",
      //     genreId: "",
      //     subgenres: "",
      //     bandImage: {
      //       imageId: "",
      //       fileName: "",
      //       imageData: "",
      //     },
      //   },
      bandFound: true,
      isLoading: false,
      isFollowing: "",
    };
  },
  computed: {
    band() {
      return this.$store.state.currentBand.bandId ? this.$store.state.currentBand : {};
    },
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
        // this.band = response.data;
        this.$store.commit("SET_BAND", response.data);
        this.isLoading = false;
        BandService.isUserFollowing(this.band.bandId, this.$store.state.user.id)
        .then((response) => {
          this.isFollowing = response.data;
        });
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
      BandService.postBandCoverImage(this.band.bandId, formData)
      .then((response) => {
          this.band.bandImage = response.data;
        }
      );
    },
    toggleFollow() {
      const userId = this.$store.state.user.id;
      if (this.isFollowing) {
        BandService.unfollowGroup(this.band.bandId, userId)
          .then((response) => {
            if (response) {
              this.isFollowing = false;
            }
          })
          .catch((error) => window.alert(error.status));
      } else {
        BandService.followGroup(this.band.bandId, userId)
          .then((response) => {
            if (response) {
              this.isFollowing = true;
            }
          })
          .catch((error) => window.alert(error.status));
      }
    },
  },
};
</script>

<style scoped>
#bandTitleBox {
  background-color: rgb(0, 255, 98);
  border-radius: 20px;
  margin-top: 20px;
  margin-bottom: 20px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 3px;
  padding-bottom: 3px;
  border: 15px;
  border-style: solid;
  border-color: orange;
}

#bandName {
  text-align: center;
  font-size: 36pt;
}

.bandPageBox {
  background-color: rgb(255, 105, 180, 0.9);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
}


#coverPhoto {
  margin-top: 25px;
  border-radius: 50px;
  border: 15px;
  border-style: solid;
  border-color: orange;
  max-width: 60%;
  max-height: 300px;
}

#content {
  overflow-y: auto;
}

#roundedEdges {
    border-radius: 50px;
}

#description{
    padding: 40px;
    background-color: rgba(255, 173, 214, 0.9); 
    border-radius: 50px;
    border-style: solid;
    border: 5px;
    max-width: 80%;
    font-size: 18pt;
    text-align: center;
}
</style>