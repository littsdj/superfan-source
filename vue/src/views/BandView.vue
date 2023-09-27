<template>
  <h1>{{ band }}</h1>
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
            }
        }
    },
    //this is broken
    created() {
        const name = this.$route.params.bandName;
        BandService.getBand(name).then(response => {
            this.band = response.data.band
        })
        
    }
}
</script>

<style>

</style>