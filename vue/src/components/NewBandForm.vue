<template>
    <div>

        <form action="" class="newBand">
            <label for="bandName">Band Name</label>
            <input type="text" name="bandName">
            <textArea rows="8" cols="45" ></textArea>
            <select name="genre" id="genrePicker">
                <option value="">--select a genre--</option>
                <option value="pop">Pop</option>
                <option value="country">Country</option>
                <option value="rock">Rock</option>
                <option value="jazz">Jazz</option>
                <option value="electronic">Electronic</option>
                <option value="hip-hop">Hip-Hop</option>
                <option value="world">World</option>
                <option value="experimental">Experimental</option>
                <option value="latin">Latin</option>
                <option value="metal">Metal</option>
            </select>
        </form>
    </div>
</template>

<script>
export default {

}
</script>

<style>
    .newBand {
        background-color: white;
        padding: 20px;
    }
</style>