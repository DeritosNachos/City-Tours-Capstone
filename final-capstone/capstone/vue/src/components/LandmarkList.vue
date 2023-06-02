<template>
  <div class="landmark-container">
      <landmark-card class="landmark-details" v-for="landmark in landmarks" :key="landmark.landmark_id" :landmark="landmark"/>
            
    
  </div>
</template>

<script>
import landmarkService from '../services/LandmarkService.js'
import LandmarkCard from '../components/LandmarkCard.vue'
export default {
    name: 'landmark-list',
    components: {
        LandmarkCard
    },
    data() {
        return {
            landmarks: [],
        }
    },
    created() {
        landmarkService.getAllLandmarks().then((r) => {
            this.landmarks = r.data;
        })
    }
}
</script>

<style>

</style>