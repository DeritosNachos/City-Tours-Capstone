<template>
  <div>
      <div class="itinerary-container">
          <h1>Here are your Itineraries!</h1>
          <div class="itinerary-list" v-for="itinerary in itineraries" :key='itinerary.itineraryId'>
            <!-- List of Itineraries - Click on itinerary Name and route to the itinerary card -->
            <ul>
              <li>
                <p>
                  <router-link v-bind:to="{name: 'itinerary', params: {id: itinerary.itineraryId}}"> {{itinerary.itineraryName}} </router-link>
                </p>
              </li>
            </ul>
          </div>
      </div>
  </div>
</template>

<script>
import ItineraryService from '../services/ItineraryService'
export default {
  data() {
    return {
      itineraries: []
    }
  }
  ,
  created(){
    ItineraryService.getAllItineraries().then(
      (response) => {
        this.itineraries = response.data
      }
    )
  }

}
</script>

<style>

</style>