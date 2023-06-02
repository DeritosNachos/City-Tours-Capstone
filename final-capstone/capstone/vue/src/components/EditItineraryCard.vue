<template>

  <form v-on:submit.prevent="submitForm" class="edit-form">
    <div>
      <label for="itinerary-name">Name: </label>
      <input
        class="name-input"
        type="text"
        v-model="itinerary.itineraryName"
      />
    </div>
    <div>
     <label for="date">Date: </label>
      <input
        class="date-input"
        type="text"
        v-model="itinerary.itineraryDate"
      />
    </div>
    <div>
      <label for="starting-point">Starting Point: </label>
      <input
        class="starting-point-input"
        type="text"
        v-model="itinerary.itineraryStartingPoint"
      />
    </div>
    <button>Submit</button>
  </form>
</template>

<script>
import ItineraryService from '../services/ItineraryService'
export default {
    data() {
        return {
            itinerary: {},
            pathId: this.$route.params.id,
        }
    },

    created() {
        ItineraryService.getSpecificItinerary(this.pathId)
        .then((response) => {
            this.itinerary = response.data;
        })
    },

    methods: {
        submitForm() {
            ItineraryService.updateItinerary(this.itinerary).then((response) => {
                if(response.status == 200){
                   this.$router.push(`/itinerary/${this.pathId}`);
                }
            }
            ) 
            .catch((error) => {
          this.handleErrorResponse(error, "submiting");
        });
        }
    }
};

</script>

<style>
</style>