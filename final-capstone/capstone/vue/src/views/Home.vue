<template>
  <div class="home">
    <div class="home-header">
      <h1>Welcome to Philadelphia</h1>
      <p>Select a Landmark to visit</p>
    </div>
    <search-landmark class="search-bar"/>
     <div class="carousel-container">
       <b-carousel
    id="carousel-fade"
    style="text-shadow: 0px 0px 2px #000"
    fade
    img-width="1024"
    img-height="200"
  >
    <b-carousel-slide
      img-src="https://cdn.pixabay.com/photo/2012/12/19/04/08/philadelphia-70850_1280.jpg"
    ></b-carousel-slide>
    <b-carousel-slide
      img-src="https://cdn.pixabay.com/photo/2013/03/01/17/55/philadelphia-87217_1280.jpg"
    ></b-carousel-slide>
    <b-carousel-slide
      img-src="https://cdn.pixabay.com/photo/2012/02/17/14/53/sculpture-14130_1280.jpg"
    ></b-carousel-slide>
    <b-carousel-slide
      img-src="https://cdn.pixabay.com/photo/2018/04/04/21/23/street-3291171_1280.jpg"
    ></b-carousel-slide>
    <b-carousel-slide
      img-src="https://cdn.pixabay.com/photo/2018/04/04/21/23/street-3291171_1280.jpg"
    ></b-carousel-slide>
  </b-carousel>
    </div>
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

  .create-link {
    margin-left: 6.5vw;
  }

  #link-button {
    margin-bottom: 1rem;
  }
}
</style>