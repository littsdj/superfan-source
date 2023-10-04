<template>
  <form>
    <label for="message-body">Message</label>
    <textArea
      name="message-body"
      rows="8"
      cols="50"
      @input="message.messageBody = $event.target.value"
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
            message: {
                senderBandId: this.$route.params.bandId,
                messageBody: ''
            },
            bandName: ''
        }
    },
    methods: {
        sendMessage() {
            MessageService.sendMessage(this.message).then(() =>{ 
                this.$router.push( 
                    { name: "bandPageView", params: { bandName: this.bandName } })
            })
        }
    },
    created() {
        BandService.getBandByBandId(this.$route.params.bandId).then(response => {
             this.bandName = response.data.bandName
        })
        
    }
}
</script>

<style>
</style>