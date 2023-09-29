<template>
  <div>
      <h1>{{ band.bandName }}</h1>
      <img :src="imgSrcData" alt="No Image Available">
  </div>
</template>

<script>
export default {
    props: { band :{}},
    computed: {
      imgSrcData() {
      if (this.band.bandImage && this.band.bandImage.fileName) {
        const dotIndex = this.band.bandImage.fileName.lastIndexOf(".");
        const extension = this.band.bandImage.fileName.substring(dotIndex + 1);
        //return `data:image/${extension};base64, ${this.bandImage.imageData}`;
        return `data:image/${extension};base64, ${this.band.bandImage.imageData}`;
      }
      return "";
    },
    }
}
</script>

<style scoped>

</style>