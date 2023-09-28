<template>
<div>
    
    <div class="bandInfo">
        <img src="../images/dancingJukeBox.gif" alt="Loading" v-show="isLoading">
        <div v-show="bandFound && !isLoading" >

            <h1>{{ band.bandName }}</h1>
            <h2>About {{ band.bandName }}</h2>
            <p> {{ band.description }}</p>
            <input type="file" id="file" ref="fileInput"/>
            <button id="uploadButton" v-on:click="uploadImage()">Upload Image</button>
        </div>
        <div><img :src="imgSrcData" alt=""></div>
        <h1 v-show="!bandFound && !isLoading">BAND PAGE NOT FOUND</h1>

    </div>
</div>
</template>

<script>
import BandService from '../services/BandService'
export default {
    data() {
        return {
            band: {
                bandName: '',
                bandId: '',
                description: '',
                genreId: '',
                subgenres: ''
            },
            bandFound: true,
            isLoading: false,
            bandImage: ''
        }
    },
    computed:{
        imgSrcData() {
            if (this.bandImage.fileName) {
                const dotIndex = this.bandImage.fileName.lastIndexOf('.');
                const extension = this.bandImage.fileName.substring(dotIndex + 1);
                return `data:image/${extension};base64, ${this.bandImage.imageData}`;
            }
            return '';
        }
    },
    
    created() {
        this.isLoading = true;
        const name = this.$route.params.bandName;
        BandService.getBand(name).then(response => {
            this.band = response.data
        }).catch((error) => {
            if(error.response){
                this.bandFound = false;
            }
        })
        setTimeout( () => {this.isLoading = false} , 1000);
        
    },
    methods: {
        uploadImage() {
            const fileInfo = this.$refs.fileInput.files[0];
            let formData = new FormData();
            formData.append('file', fileInfo);
            BandService.postBandCoverImage(this.band.bandId, formData)
            .then( response => {
                this.bandImage = response.data;
            })
        }
    }
}
</script>

<style>
    .bandInfo{
        background-color: rgb(255, 105, 180, .9);
        
    }
    #uploadButton {
        width: 100px;
    }
</style>