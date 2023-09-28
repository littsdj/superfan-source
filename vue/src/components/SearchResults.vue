<template>
    <div>
        <h1>Search Results</h1>
        <div v-for="band in bandResults"></div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            bandResults: ''
        }
    }
}
</script>

<style>

</style>