<template>
  <div>
    <div
      class="itinerary-card"
    >
      <h2>
        {{itinerary.itineraryName}}
      </h2>
      <h3>Plan for: 
        {{itinerary.itineraryDate}}
      </h3>
      <!-- <div class="landmark-card" v-for="landmark in landmarks" :key="landmark.landmarkId">
        <h3> {{landmark.landmarkName}} </h3>
       <img :src='landmark.landmarkPhoto' alt="photo of landmark">
      <h4>{{landmark.landmarkHoursOfOperation}}</h4>
       
      </div> -->
      <div>
          <route-details/>
        <landmark-card class="landmark-details" v-for="landmark in landmarks" :key="landmark.landmark_id" :landmark="landmark"/>

      </div>
      <div>
          <route-details/>
      </div>
    </div>
  </div>
</template>

<script>
import LandmarkCard from "../components/LandmarkCard.vue"
import ItineraryService from "../services/ItineraryService.js";
import LandmarkService from "../services/LandmarkService.js";
import RouteDetails from '../components/RouteDetails.vue';
export default {
  components: {
          RouteDetails,
          LandmarkCard
          },
  data() {
    return {
      itinerary: {},
      landmarks: [],
      pathId: this.$route.params.id,
    };
  },
  computed: {
    // filteredItinerary() {
    //   return this.itinerary.filter(
    //     (itinerary) => itinerary.itineraryId === this.pathId
    //   );
    // },
  },
  created() {
    ItineraryService.getSpecificItinerary(this.pathId).then((response) => {
      this.itinerary = response.data;
    });

    LandmarkService.getLandmarksOnItinerary(this.pathId).then((response) => {
      this.landmarks = response.data;
    });
  },

};
</script>

<style>
.landmark-card {
    border-radius: 10px;
    border: 3px solid;
    height: auto;
    margin: 8px;
    position: relative;
}

</style>