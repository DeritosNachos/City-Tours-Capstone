<template>
  <div>
    <div class="itinerary-card">
      <h2>
        {{ itinerary.itineraryName }}
      </h2>
      <div v-for="user in getUsername" :key="user.id">
        <!-- insert avatar image here || user 1 name -->
        <h5 >By {{user.username}} <avatar :fullname="user.username" :size="32" /></h5>
        <br/>
      </div>
      <h3>
        Plan for:
        {{ itinerary.itineraryDate }}
      </h3>
      <h4>Starting Point:</h4>
      <p>{{ itinerary.itineraryStartingPoint }}</p>
      <router-link :to="{ name: 'edit-itinerary', params: { id: pathId } }">
        <button>Edit this itinerary</button>
      </router-link>
      <div class="share-button">
        <button @click="toggleShowUsers">Share</button>
        <div v-for="user in filteredUsersList" :key="user.id">
          <div v-show="showUsers">
            <ul>
              <li>
                <h2>
                  {{ user.username }}
                </h2>
                <button
                  @click="
                    addUser2Id(user.id);
                    addSharedTripToUser();
                  "
                >
                  Add User to Trip
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div>
        <route-details />
        <div
          class="landmark-details"
          v-for="landmark in landmarks"
          :key="landmark.landmark_id"
        >
          <landmark-card :landmark="landmark" />
          <div class="button-container">
            <b-btn
              id="remove-button"
              variant="danger"
              v-on:click="removeDestination(pathId, landmark.landmarkId)"
            >
              <b-icon icon="x-lg"></b-icon>
            </b-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Avatar from 'vue-avatar-component'
import DestinationService from "../services/DestinationService";
import LandmarkCard from "../components/LandmarkCard.vue";
import ItineraryService from "../services/ItineraryService.js";
import LandmarkService from "../services/LandmarkService.js";
import RouteDetails from "../components/RouteDetails.vue";
import axios from "axios";
export default {
  components: {
          RouteDetails,
          LandmarkCard,
          Avatar
          },
  data() {
    return {
      itinerary: {},
      landmarks: [],
      users: [],
      showUsers: false,
      pathId: this.$route.params.id,
      sharedTrip: {
        tripId: parseInt(this.$route.params.id),
        user1Id: 0,
        user2Id: 0,
      },
    };
  },
  computed: {
    filteredUsersList() {
      return this.users.filter((user) => user.id != this.sharedTrip.user1Id);
    },
    getUsername() {
      return this.users.filter((user) => user.id === this.sharedTrip.user1Id);
    },

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
    ItineraryService.getUsers().then((response) => {
      this.users = response.data;
    });
  },
  methods: {
    toggleShowUsers() {
      this.showUsers = !this.showUsers;
    },
    addUser2Id(id) {
      this.sharedTrip.user2Id = id;
      console.log(id);
      console.log(this.sharedTrip.user2Id);
    },
    addSharedTripToUser() {
      console.log(this.sharedTrip);
      ItineraryService.addSharedTrip(this.sharedTrip)
        .then((r) => {
          console.log(r.status);
          alert("User was added to Trip!");
        })
        .catch((error) => {
          console.log(error.status);
        });
    },
        removeDestination(itineraryId, landmarkId) {
      let choice = confirm("Delete Destination?");
      if (choice) {
        DestinationService.removeDestination(itineraryId, landmarkId).then(
          (response) => {
            console.log(response);
            this.$router.go(`/itinerary/${itineraryId}`);
          }
        );
      }
    }
  },
  mounted() {
    axios
      .get("/api/current-user-id")
      .then((response) => {
        this.sharedTrip.user1Id = response.data.userId;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.landmark-details {
  display: flex;
  justify-content: center;
}

#remove-button {
  margin: 0 2rem 0 0;
  position: absolute;
}
</style>