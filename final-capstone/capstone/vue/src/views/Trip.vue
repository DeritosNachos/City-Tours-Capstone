<template>
  <div class="trips-container">
    <div class="itinerary-container">
      <h1>Here are your Itineraries!</h1>
      <div
        class="itinerary-list"
        v-for="itinerary in itineraries"
        :key="itinerary.itineraryId"
      >
        <!-- List of Itineraries - Click on itinerary Name and route to the itinerary card -->
        <ul>
          <li>
            <p>
              <router-link
                class="itinerary-link"
                v-bind:to="{
                  name: 'itinerary',
                  params: { id: itinerary.itineraryId },
                }"
              >
                {{ itinerary.itineraryName }}
              </router-link>
              &nbsp;&nbsp;&nbsp;
              

              <font-awesome-icon
                v-model="itinerary.itineraryId"
                v-on:click="deleteItinerary(itinerary.itineraryId)"
                icon="fa-solid fa-trash"
              />
            </p>
            
          </li>
        </ul>
      </div>
    </div>
    <div class="shared-itinerary-container">
      <h1>Itineraries Shared with You!</h1>
      <div
        class="shared-itinerary-list"
        v-for="sharedItinerary in filteredList"
        :key="sharedItinerary.itineraryId"
      >
        <!-- List of Itineraries - Click on itinerary Name and route to the itinerary card -->
        <ul>
          <li>
            <p>
              <router-link
                class="itinerary-link"
                v-bind:to="{
                  name: 'itinerary',
                  params: { id: sharedItinerary.itineraryId },
                }"
              >
                {{ sharedItinerary.itineraryName }}
              </router-link>
              &nbsp;&nbsp;&nbsp;<font-awesome-icon
                v-model="sharedItinerary.itineraryId"
                v-on:click="deleteItinerary(sharedItinerary.itineraryId)"
                icon="fa-solid fa-trash"
              />
            </p>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService";
export default {
  data() {
    return {
      itineraries: [],
      sharedItineraries: [],
    };
  },
  created() {
    ItineraryService.getAllItineraries().then((response) => {
      this.itineraries = response.data;
    }),
      ItineraryService.getSharedItineraries().then((response) => {
        this.sharedItineraries = response.data;
        // this.sharedItineraries = this.sharedItineraries.filter(item => !this.itineraries.some(item2 => item.itineraryId === item2.itineraryId))
      });
  },
  computed: {
    filteredList() {
      return this.sharedItineraries.filter(
        (item) =>
          !this.itineraries.some(
            (item2) => item.itineraryId === item2.itineraryId
          )
      );
    },
  },
  methods: {
    deleteItinerary(itineraryId) {
      let choice = confirm("Do you want to delete this itinerary?");
      if (choice) {
        ItineraryService.deleteItinerary(itineraryId).then((response) => {
          console.log(response);
          this.$router.go(`/trip`);
        });
      }
    },
  },
};
</script>

<style>
/* .trips-container{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  text-align: center;
} */
/* 
li {
  list-style: none;
} */

/* .itinerary-link{
  background-color: lightgray;
  border: 1px solid black;
  border-radius: 5px;
  color: black;
  text-decoration: none;
} */
</style>