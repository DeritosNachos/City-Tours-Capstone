<template>
<div id="app">
     
    <div id="nav"> 
      <img id="logo" src="./assets/logo.png" alt="city tours logo">
      <div>
        <Menu :no-overlay="true" :width="200" :right="true" ref="menu">
           <ul>
          <li>
          <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
          </li>
          <li>
            <router-link class="nav-link" v-bind:to="{ name: 'trip'}" > Trips </router-link>
          </li>
          <li>
          <router-link class="nav-link"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
        <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-else>Login</router-link>
          </li>
        </ul>
          </Menu>
      </div>
      <!-- <div class="burger-menu">

        <ul>
          <li>
          <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
          </li>
          <li>
          <router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
          </li>
        </ul>
      </div> -->
    </div>
    <!-- <img class="bg-img" src="./assets/philadelphia-skyline.jpg" alt="Philly skyline"> -->
    <div class="main">
   
    <router-view />
    </div>
  </div>
</template>

<script>
import { Slide } from 'vue-burger-menu'

export default {
  components: {
    Menu: Slide
  },
  mounted() {
    this.$refs.menu.$el.style.backgroundColor = '#fff'
     this.$refs.menu.$el.style.color = '#000'

  }
}
</script>


<style>
.app {
  font-family: Arial, Helvetica, sans-serif;
}

.bg-img{
  position: absolute;
  opacity: 50%;
}

.main {
  position: relative;
}

#logo {
  border-radius: 50%;
  height: 150px;
  border: 2px solid;
}
#nav {
  display: flex;
  justify-content: space-between;
}

.nav-link {
  border: 1px solid;
  
}

.burger-menu {
  background-color: aqua;
  color: black;
}

div.bm-menu {
  background-color: #8AAAE5;
}

nav.bm-item-list ul {
  list-style: none;
  display: flex;
  flex-direction: column;
}

.nav-link {
  border: none;
  text-decoration: none;
  color: white;
}


/* .burger-menu {
    color: var(--primary-color);
    font-size: 2rem;
    border: 0;
    background-color: transparent;
    cursor: pointer;
    display: none;
}
.burger-menu {
          display: block;
      }

nav ul.show {
          transform: translateX(0);
      }
nav ul {
          position: fixed;
          background-color: var(--bg-color);
          flex-direction: column;
          top: 86px;
          left: 10%;
          width: 80%;
          text-align: center;
          transform: translateX(120%);
          transition: transform 0.5s ease-in;
      } */
</style>