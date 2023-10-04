<template>
  <form>
    <label for="message-body">Message</label>
    <textArea
      name="message-body"
      rows="8"
      cols="50"
      @input="messageBody = $event.target.value"
    ></textArea>
    <button v-on:click.stop.prevent="sendMessage()">Send Message</button>
  </form>
</template>

<script>
import MessageService from "../services/MessageService.js";
import BandService from "../services/BandService.js";

export default {
    data() {
        return {
            messageBody: ""

        }
    },
    methods: {
        sendMessage() {
            MessageService.sendMessage(this.messageBody, this.$route.params.bandId).then(() =>{
               BandService.getBandByBandId(this.$route.params.bandId).then(response => {
                    this.$router.push({ name: "bandPageView",
                    params: { bandName: response.bandName}})
               })
            })
        }
    }
}
</script>

<style>
</style>