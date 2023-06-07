<template>
  <div class="login-container">
    <div id="login">
    <form @submit.prevent="login">
      <h1 >Log in to start your journey!</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username"><font-awesome-icon icon="fa-solid fa-user" /></label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">
          <font-awesome-icon icon="fa-solid fa-lock" />
        </label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link class="register" :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
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
 h1{
   font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
 }
.login-container {
  background-image: linear-gradient(#8AAAE5, #004AAD );
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  margin-right: 25%;
  margin-left: 25%;
  
}
#login {
  background-color: rgba(255, 255, 255, 0.281);
  margin: 5em;
  border-radius: 10%;
  text-align: center;
  padding: 10px;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

@media only screen and (max-width: 800px) {
  .login-container {
  background-color: linear-gradient(#8AAAE5, #004AAD );
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  margin: 3%
}
  #login {
  background-color: rgba(255, 255, 255, 0.281);
  margin: 5em;
  border-radius: 10%;
  text-align: center;
  padding: 10px;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

.register {
  text-decoration: none;
  color: black;
}

}
</style>