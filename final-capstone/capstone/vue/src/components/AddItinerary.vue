<template>
  <div>
    <form class="new-itinerary" v-on:submit.prevent="addItinerary">
      <label for="name">Name for the Itinerary: </label>
      <input
        class="name-input"
        type="text"
        placeholder="Name"
        v-model="itinerary.itineraryName"
      />
      <br />
      <label for="date">Date: </label>
      <input
        class="date-input"
        type="text"
        placeholder="YYYY-MM-DD"
        v-model="itinerary.itineraryDate"
      />
       <br />
      <label for="starting-point">Starting Point: </label>
      <input
        class="starting-point-input"
        type="text"
        placeholder="Starting Address"
        v-model="itinerary.itineraryStartingPoint"
      />
      <br />
      <button>Add Itinerary</button>
    </form>
  </div>
</template>

<script>
import itineraryService from "../services/ItineraryService.js";

export default {
  data() {
    return {
      itinerary: {
        itineraryName: "",
        itineraryDate: "",
        itineraryStartingPoint: "",
      },
    };
  },
  created() {},
  methods: {
    addItinerary() {
      const newItinerary = {
        itineraryName: this.itinerary.itineraryName,
        itineraryDate: this.itinerary.itineraryDate,
        itineraryStartingPoint: this.itinerary.itineraryStartingPoint,
      };
      window.alert("Successfully saved it!");

      itineraryService
        .createNewItinerary(newItinerary)
        .then((response) => {
          if (response.status === 201) {
            this.itinerary.itineraryName = " ";
            this.itinerary.itineraryDate = "";
            this.itinerary.itineraryStartingPoint = "";
            this.$router.push("/addItinerary");
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
  }
};
</script>

<style>
input, button{
margin: 10px 0;
}
</style>