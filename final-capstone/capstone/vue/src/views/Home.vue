<template>
  <div class="home">
    <div class="home-header">
      <h1>Welcome to Philadelphia</h1>
      <p>Select a Landmark to visit</p>
    </div>
    <search-landmark class="search-bar"/>
    <router-link class="create-link" :to="{ name: 'add-itinerary' }">
      <b-button id="link-button" block variant="primary">
      Create your Itinerary!
      </b-button>
    </router-link>
    <landmark-list class="tiles" />
  </div>
</template>

<script>
import LandmarkList from "../components/LandmarkList.vue";
import SearchLandmark from "../components/SearchLandmark.vue";

export default {
  name: "home",
  components: {
    LandmarkList,
    SearchLandmark,
  },
};
</script>

<style scoped>
.home {
  font-family: Arial, Helvetica, sans-serif;
}
.home-header {
  text-align: center;
}

.create-link {
  margin-left: 8vw;
  margin-top: 5rem;
  
}

.search-bar {
  margin-bottom: 2rem;
}

.tiles {
  justify-content: center;
}

@media only screen and (max-width: 800px) {

.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

  .create-link {
    margin-left: 6.5vw;
  }

  #link-button {
    margin-bottom: 1rem;
  }
}
</style>