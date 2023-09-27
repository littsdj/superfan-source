<template>
<div>
    <div v-show="bandFound">

        <h1>{{ band.bandName }}</h1>
        <h2>About {{ band.bandName }}</h2>
        <p> {{ band.description }}</p>
    </div>
    <h1 v-show="!bandFound">BAND PAGE NOT FOUND</h1>

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
            bandFound: true
        }
    },
    //this is broken
    created() {
        const name = this.$route.params.bandName;
        BandService.getBand(name).then(response => {
            this.band = response.data
        }).catch((error) => {
            if(error.response){
                this.bandFound = false;
            }
        })
        
    }
}
</script>

<style>

</style>