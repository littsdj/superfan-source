<template>
  <div id="content">
    <div class="inbox">
      <h1 id="pageHeaderTitle">Messages From Bands You Follow</h1>
      <div class="messageBox" v-for="message in messages" v-bind:key="message.messageId"> 
        <message-box v-bind:message="message" />
      </div>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService.js";
import MessageBox from "../components/MessageBox.vue";
export default {
  components: { MessageBox },
  data() {
    return {
      messages: []
    }
  },
  created() {
    MessageService.getAllMessagesToUser(this.$store.state.user.id).then( (response) => {
      this.messages = response.data
    } )
  }
};
</script>

<style scoped>
#content {
  overflow-y: auto;
}
.inbox {
  background-color: rgba(162, 6, 252, 0.856);
  border-radius: 50px;
  margin-top: 20px;
  margin-bottom: 20px;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 3px;
  padding-bottom: 3px;
  border: 15px;
  border-style: solid;
  border-color: rgba(255, 112, 212, 0.842);
}

#pageHeaderTitle {
  background-color: blueviolet;
  color: white;
  padding: 5px;
  border-radius: 50px;
  text-align: center;
}
</style>