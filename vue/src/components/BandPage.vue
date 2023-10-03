<template>
  <div id="content">
    <div class="bandInfo">
      <img id="coverPhoto" src="../images/dancingJukeBox.gif" alt="Loading" v-show="isLoading" />
      <div class="bandPageBox" v-show="bandFound && !isLoading" 
      v-bind:class="{
      'bandPageBox-generic': bandGenreId == 0,
      'bandPageBox-pop': bandGenreId == 1,
      'bandPageBox-rock': bandGenreId == 2,
      'bandPageBox-country': bandGenreId == 3,
      'bandPageBox-jazz': bandGenreId == 4,
      'bandPageBox-electronic': bandGenreId == 5,
      'bandPageBox-hiphop': bandGenreId == 6,
      'bandPageBox-world': bandGenreId == 7,
      'bandPageBox-experimental': bandGenreId == 8,
      'bandPageBox-latin': bandGenreId == 9,
      'bandPageBox-metal': bandGenreId == 10,
    }">
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
        <label for="file" id="fileButton" class="select-file-button" v-if="userIsOwner">Select Cover Image</label>
        <input class="button" type="file" id="file" ref="fileInput" v-show="false" />
        <label for="uploadButton" id="uploadLabel" class="select-file-button" v-if="userIsOwner">Upload Image</label>
        <button class="select-file-button" id="uploadButton" v-on:click="uploadImage()" v-show="false">Upload Image</button>
        <button id="galleryButton" v-on:click="navToPhotoGallery()">View Band Photo Gallery</button>
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
      bandOwnerId: ""
    };
  },
  computed: {
    bandGenreId() {
      if (this.$route.name == "bandPageView") {
        return this.$store.state.currentBand.genreId;
      } else {
        return 0;
      }
    },
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
    userIsOwner() {
      return (this.bandOwnerId === this.$store.state.user.id);
    }
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
        BandService.getBandOwnerIdByBandId(this.band.bandId)
        .then( (response) => {
          this.bandOwnerId = response.data;
        })
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
    navToPhotoGallery() {
        this.$router.push({ name: "gallery", params: { bandId: this.band.bandId } })
    }
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
}
.bandPageBox-generic {
  background-color: rgba(73, 218, 198, 0.6);
}
.bandPageBox-pop {
  background-color: red;
}
.bandPageBox-rock {
  background-color: green;
}
.bandPageBox-country {
  background-color: yellow ;
}
.bandPageBox-jazz {
  background-color: purple;
}
.bandPageBox-electronic {
  background-color: pink;
}
.bandPageBox-hiphop {
  background-color: fuchsia;
}
.bandPageBox-world {
  background-color: turquoise;
}
.bandPageBox-experimental {
  background-color: cyan;
}
.bandPageBox-latin {
  background-color: burlywood;
}
.bandPageBox-metal {
  background-color: hotpink;
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

#description {
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