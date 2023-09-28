<template>
    <div>
        <h1>Search Results</h1>
        <div v-for="band in searchResults"></div>
    </div>
</template>

<script>
import BandService from '../services/BandService'
export default {
    data() {
        return {
            searchResults: ''
        }
    },
    props: { searchTerms },
    created() {
        BandService.searchBands(this.searchTerms).then( response => {
            this.searchResults = response.data;
        })
    }
}
</script>

<style>

</style>