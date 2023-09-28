<template>
  <div id="register" class="text-center">
    <div class="register-background"></div>
    
    <!-- Create a separate div for background -->
    <div>
      <div class="headerBox">
        <h1 id="header">SUPERFAN SOURCE</h1>
      </div>
      <form @submit.prevent="register" class="register-box">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username:</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password:</label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password:</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="user.confirmPassword"
            required
          />
        </div>
        <button type="submit" class="register-button">Create Account</button>
        <p>
          <router-link :to="{ name: 'login' }"
            >Already have an account? Log in.</router-link
          >
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";


export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.text-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  position: relative; /* Add position relative to create a stacking context */
}

.register-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url("../images/music-notes.png"); /* Set the background image */
  background-size: cover;
  background-position: center;
  opacity: 0.8; /* Set the opacity for the background */
  z-index: -1; /* Send the background behind the content */
}

.register-box {
  background-color: rgb(67, 235, 212, 0.8); /* Black background color */
  color: rgb(235, 39, 137); /* Hot pink text color */
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  padding: 20px;
  max-width: 400px;
  text-align: center;
}

.register-button {
  background-color: rgb(223, 42, 132); /* Hot pink button background color */
  color: rgb(0, 0, 0); /* Black button text color */
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 1rem;
}
.headerBox{
  border-radius: 20px;
  background: rgb(
    166,
    228,
    43,
    0.6
  );
}

label {
  display: block;
}

#header{
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
  font-style: italic;
  font-size: 50px;
}
</style>
