<template>
  <div class="content">
    <h1>Messages From Bands You Follow</h1>
    <div v-if="messages.length === 0">
      <p>No messages yet.</p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#inbox {
  max-width: 600px;
  margin: 0 auto;
}

.message {
  border: 15px solid rgb(125, 87, 185);
  border-radius: 50px;
  margin-bottom: 25px;
}

.message-header {
  display: flex;
  justify-content: space-between;
}

.message-content {
  margin-top: 25px;
}
</style>