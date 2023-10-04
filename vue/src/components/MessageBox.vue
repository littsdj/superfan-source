<template>
    <div id="messageInfo">
        <h2> Message from {{}}</h2>
    </div>
</template>

<script>
import BandService from "../services/BandService.js";

export default {
    data() {

    },
    computed() {
        BandService.getBandByBandId
    }
}
</script>

<style>

</style>