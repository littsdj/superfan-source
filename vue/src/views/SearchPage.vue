<template>
  <div class="background"> <!-- background image is stored  -->
        <title-bar class="header"/>
        
        <sidebar class="sidebar"/>
        <div class="content">
          <div v-for="result in searchResults" v-bind:key="result.bandId">
            <searched-item v-bind:band="result" />
          </div>
        </div>
        
  </div>
</template>

<script>
import TitleBar from '../components/TitleBar.vue'
import BandService from '../services/BandService'
import SearchedItem from '../components/SearchedItemNew.vue'
import sidebar from '../components/sidebar.vue'
export default {

    components: {TitleBar, SearchedItem, sidebar },
    data() {
        return {
            searchResults:''
        }
    },
    created() {
        BandService.searchBands(this.$route.params.searchTerms)
        .then(results => {
            this.searchResults = results.data;
        })
    }
}
</script>

<style>

</style>