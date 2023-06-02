<template>
  <div class="landmark-card"  @click="toggleShowHide" >
        <img :src='landmark.landmarkPhoto' alt="photo of landmark">
        
        <div id="landmark-info" :class="{ read: showHide }">
    
            <h1>{{landmark.landmarkName}}</h1>
            <div v-show="showHide">
            <p>{{landmark.landmarkDescription}}</p>
            <br>
            <p>{{landmark.landmarkAddress}}</p>
            <br>
            <p>{{landmark.landmarkVenueType}}</p>
            </div>
            <h3>{{landmark.landmarkHoursOfOperation}}</h3>
            <div @click="addLandmarkId(landmark.landmarkId)" >
                <button :disabled="dropDownActive" @click="showDropDown">{{ itineraries.length > 0 ? 'Add' : 'Please Add an Itinerary' }}</button>
                <div @mouseleave="dropDownActive=false" class="dropdown" v-if="dropDownActive">
                    <div  v-for="itinerary in itineraries" :key="itinerary.itineraryId">
                        <p @click.prevent="addItineraryId(itinerary.itineraryId);toggleShowAddRemove()">{{itinerary.itineraryName}}</p>
                    </div>
                </div>
                <button v-on:click="removeDestination(pathId, landmark.landmarkId)" v-if="addRemove">Remove</button>
            </div>
        </div>
  </div>
</template>

<script>
import ItineraryService from "../services/ItineraryService"
import DestinationService from "../services/DestinationService"
export default {
    name: 'landmark-card',
    props: ['landmark'],
    data() {
        return {
            pathId: this.$route.params.id,
            itineraries: [],
            showHide: false,
            dropDownActive: false,
            addRemove: true,
            destination: {
                landmarkId: 0,
                itineraryId: 0
            }
        }
    },
    methods: {
        toggleShowAddRemove(){
            this.addRemove = !this.addRemove
        },
        toggleShowHide() {
            this.showHide = !this.showHide
        },
        addLandmarkId(id) {
            this.destination.landmarkId = id;
            this.showHide = true;
            // console.log(id)
        },
        addItineraryId(id) {
            this.destination.itineraryId = id;
            this.dropDownActive = false;
            DestinationService.addDestination(this.destination).then((r) => {
                console.log(r.status);
            })

        },
        removeDestination(itineraryId, landmarkId){
            let choice = confirm("Delete Destination?");
            if(choice){
                DestinationService.removeDestination(itineraryId, landmarkId).then(
                    (response) => {
                        console.log(response);
                        this.$router.go(`/itinerary/${itineraryId}`);
                    }
                )
            }
        },

        showDropDown() {
            this.dropDownActive = !this.dropDownActive
        }
    },
    created() {
    ItineraryService.getAllItineraries().then((response) => {
        this.itineraries = response.data
    })
    }

}
</script>

<style scoped>



@media only screen and (max-width: 800px) {

.landmark-card {
    
    border-radius: 10px;
    border: 2px solid;
    height: auto;
    margin: 5px;
    position: relative;
    
}
img {
    border-radius: inherit;
    position: absolute;
    height: 100%;
    width: 100%;
    object-fit: cover;
    
    
}

#landmark-info {
    padding: 5px;
    position: inherit;
    color: whitesmoke;
    text-shadow: 2px 2px 2px black;
    transition: height 5s;
}
.read {
    
    background: linear-gradient( rgba(0,0,0,1), rgba(0,0,0,.75), rgba(0,0,0,1));
}

.dropdown {
    border-radius:0 15px 15px 15px;
    border: 1px solid;
    text-shadow: none;
    color: black;
    background-color: whitesmoke;
    position: absolute;
    z-index: 1;
    width: auto;
    padding-right: 5px;
    padding-left: 5px;
    margin-top: -10px;
    transition: height 5s;
}
.dropdown>div>p {
    border-bottom: solid 1px gray;
    padding-bottom: 10px;
}
.dropdown>div>p:hover {
    font-size: 110%;
    cursor: pointer;
}
}
</style>