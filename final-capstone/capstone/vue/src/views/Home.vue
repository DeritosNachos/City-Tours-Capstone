<template>
  <div class="home">
    <div class="home-header">
    <h1>Welcome to Philadelphia</h1>
    <p>Select a Landmark to visit</p>
    </div>
    <p><router-link :to="{ name: 'add-itinerary' }">Create your Itinerary!</router-link></p>
    <landmark-list/>
  </div>
</template>

<script>

import LandmarkList from '../components/LandmarkList.vue';



export default {
  name: "home",
  components: {
    LandmarkList
  
  }
};
</script>

<style scoped>
.home {
  font-family: Arial, Helvetica, sans-serif;
}
.home-header {
  text-align: center;
}
</style>