<template>
  <div id="content">
    <div class="inbox">
      <h1>Messages From Bands You Follow</h1>
      <!-- <div v-if="messages.length === 0">
      <p>No Messages Yet.</p>
      </div> -->
    </div>
  </div>
</template>

<script>
// import MessageService from '../services/MessageService.js';
export default {
  
};
</script>

<style scoped>
#content {
  overflow-y: auto;
}
.inbox {
  background-color: rgba(162, 6, 252, 0.856);
  border-radius: 20px;
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
</style>