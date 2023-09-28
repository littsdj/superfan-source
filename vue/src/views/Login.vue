<template>
  <div id="login">
    <form @submit.prevent="login">
      <div class="login-background"></div> <!-- Create a separate div for background -->
      <div class="login-box">
        <div class="login-content">
          <h1 class="login-title">Please Sign In</h1>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>
          <div class="form-input-group">
            <label for="username">Username</label>
            <input type="text" id="username" v-model="user.username" required autofocus />
          </div>
          <div class="form-input-group">
            <label for="password">Password</label>
            <input type="password" id="password" v-model="user.password" required />
          </div>
          <button type="submit" class="login-button">Sign in</button>
        </div>
        <div class="form-input-group">
          <button class="link-to-register">
            <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link>
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  position: relative; /* Add position relative to create a stacking context */
}

.login-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("../images/login-background.png"); /* Set the background image */
  background-size: cover;
  background-position: center;
  opacity: 0.9; /* Set the opacity for the background */
  z-index: -1; /* Send the background behind the content */
}

.login-box {
  background-color: rgba(0, 0, 0, 0.9); /* Black background color with opacity */
  color: hotpink; /* Hot pink text color */
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  
  max-width: 400px;
  text-align: center;
}

.login-title {
  color: hotpink; /* Hot pink title color */
}

.login-content {
  padding: 20px;
}

.login-button {
  background-color: hotpink; /* Hot pink button background color */
  color: black; /* Black button text color */
  border: 0;
  padding: 10px 0px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}
.link-to-register {
   background-color: hotpink; /* Hot pink button background color */
  color: black; /* Black button text color */
  border: 0;
  padding: 10px 0px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.form-input-group {
  margin-bottom: .5rem;
}

</style>
