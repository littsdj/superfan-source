<template>
    <div>

        <form action="" class="newBand">
            <label for="bandName">Band Name</label>
            <input type="text" name="bandName" v-model="band.bandName"/>
            <br />
            <label for="description">Description</label>
            <textArea name="description" rows="8" cols="50"  v-model="band.description"></textArea>
            
            <!-- <input name="description" rows="8" cols="50"  v-model="band.description" /> -->
            <br/>
            <select name="genre" id="genrePicker" v-model="band.genreId">
                <option value="">--select a genre--</option>
                <option value="1">Pop</option>
                <option value="2">Rock</option>
                <option value="3">Country</option>
                <option value="4">Jazz</option>
                <option value="5">Electronic</option>
                <option value="6">Hip-Hop</option>
                <option value="7">World</option>
                <option value="8">Experimental</option>
                <option value="9">Latin</option>
                <option value="10">Metal</option>
            </select>
            <!-- <br/>
            <label for="bandImage">Band Image</label>
            <br/>
            <input name="bandImage" type="file" id="file" ref="fileInput"/> -->
            <button v-on:click.stop.prevent="makeBandPage()">CREATE PAGE</button>
        </form>
    </div>
</template>

<script>
import bandService from '../services/BandService.js';
export default {
    data() {
        return {
            band: {
                bandName: '',
                description: '',
                genreId: ''
            }
        }
    },
    methods: {
        makeBandPage() {
            bandService.newBand(this.band).then( response => {
               if (response.status === 200 || response.status === 201){
                   this.$router.push({name: 'bandPageView', params : { bandName: this.band.bandName}})
               } 
            })
        }
    }
}
</script>

<style>
    .newBand {
        display: grid;
        background-color: white;
        padding: 20px;
    }

    button {
        width: 30%;
        margin-top: 20px;
        justify-self: center;
    }
</style>