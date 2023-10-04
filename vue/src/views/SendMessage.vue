<template>
    <div class="background">
    <!-- background image is stored  -->
    <title-bar class="header" />
    <sidebar class="sidebar" />
    <message-form class="content" />
  </div>
</template>

<script>
import MessageForm from "../components/MessageForm.vue";
export default {
    components: { MessageForm }
    
}
</script>

<style>

</style>