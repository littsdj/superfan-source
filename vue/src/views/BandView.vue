<template>
<div class="bandInfo">
    <img src="../images/dancingJukeBox.gif" alt="Loading" v-show="isLoading">
    <div v-show="bandFound && !isLoading" >

        <h1>{{ band.bandName }}</h1>
        <h2>About {{ band.bandName }}</h2>
        <p> {{ band.description }}</p>
    </div>
    <h1 v-show="!bandFound && !isLoading">BAND PAGE NOT FOUND</h1>

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
            isLoading: false
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
        
    }
}
</script>

<style>
    .bandInfo{
        background-color: hotpink;
        opacity: .9;
    }
</style>