<template>
<div class="register-container">
  <div class="form-group">
     <div id="register" class="text-center">
    <form class="register-form" @submit.prevent="register">
      <h1>Create your Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username"><font-awesome-icon icon="fa-solid fa-user" /></label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password"><font-awesome-icon icon="fa-solid fa-lock" /></label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword"><font-awesome-icon icon="fa-solid fa-check" /><font-awesome-icon icon="fa-solid fa-lock" /></label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link class="login-link" :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
  </div>
</div>

</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
/* CSS styling still a work in progress */
/* div#register.text-center {
 align-content: center;
 width: 50%;
} */

h1{
   font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
 }

.register-container {
  background-image: linear-gradient(#8AAAE5, #004AAD );
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 10px;
  margin-right: 25%;
  margin-left: 25%
}

#register { 
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
  .register-container {
    height: 70vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-image: linear-gradient(#8AAAE5, #004AAD );
    border-radius: 10px;
    margin: 3%
  }

  .register {
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

  .login-link {
    text-decoration: none;
    color: black;
  }

  
}
</style>
