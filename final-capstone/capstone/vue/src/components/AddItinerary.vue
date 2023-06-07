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
        type="date"
        v-model="itinerary.itineraryDate"
      />
       <br />
      <label for="starting-point">Starting Point: </label>
      <input id="autocomplete"
        ref="startingPoint"
        class="starting-point-input"
        type="text"
        placeholder="Starting Address"
        v-model="itinerary.itineraryStartingPoint"
      />
      <br />
      <button>Add Itinerary</button>
      <p><router-link :to="{ name: 'home' }">Cancel</router-link></p>
    </form>
  </div>
</template>

<script>
import itineraryService from "../services/ItineraryService.js";
import moment from "moment";

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
  mounted() {
    const autocomplete = new window.google.maps.places.Autocomplete(
      this.$refs["startingPoint"]);
      autocomplete.setComponentRestrictions({
        contry: ["us"]
      })
    autocomplete.addListener("place_changed", () => {
      const place = autocomplete.getPlace();
      this.itinerary.itineraryStartingPoint = place.formatted_address;
    })
  },
  created() {},
  methods: {
    addItinerary() {
     if(this.itinerary.itineraryName === ''){
        window.alert("Please fill in the name for your new itinerary")
      } 
      else if(this.itinerary.itineraryDate === '' || moment(this.itinerary.itineraryDate).isBefore(moment())){
        window.alert("Please fill in a correct date for your new itinerary")
      }  
     else if(this.itinerary.itineraryStartingPoint === ''){
        window.alert("Please fill in an address as starting point for your new itinerary")
      }
      else {
      window.alert("Successfully saved it!");
      itineraryService
        .createNewItinerary(this.itinerary)
        .then((response) => {
          console.log("response", response)
          if (response.status === 200) {
            this.itinerary.itineraryName = " ";
            this.itinerary.itineraryDate = "";
            this.itinerary.itineraryStartingPoint = "";
            this.$router.back();
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
      }
    }
  }
};
</script>

<style scoped>
input, button{
margin: 10px 0;
}
</style>