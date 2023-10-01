<template>
  <div>
    <h1>{{ band.bandName }}</h1>
    <h2>{{ genre }}</h2>
    <button>View Page</button>
  </div>
</template>

<script>
export default {
  props: { band: {} },
  computed: {
    genre() {
      const genres = ["", "Pop", "Rock", "Country", "Jazz", "Electronic", "Hip-Hop", "World", "Experimental", "Latin", "Metal"];
      return genres[this.band.genreId];
    },
  },
};
</script>

<style scoped>

</style>