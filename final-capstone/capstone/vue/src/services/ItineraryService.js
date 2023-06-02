import axios from "axios";

export default {
    getSpecificItinerary(itineraryId) {
        return axios.get(`/itinerary/${itineraryId}`)
    },
    getAllItineraries(){
        return axios.get("/itineraries");
    },
    createNewItinerary(itinerary){
        return axios.post('/addItinerary', itinerary);
    },
    updateItinerary(itinerary){
        return axios.put(`/itinerary/${itinerary.itineraryId}/edit`, itinerary)
    },
    deleteItinerary(itineraryId){
        return axios.delete(`/itinerary/${itineraryId}`)
    },
}