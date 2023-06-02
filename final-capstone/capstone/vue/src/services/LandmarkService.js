import axios from "axios";

export default {
    getAllLandmarks() {
        return axios.get('/landmarks');
    },
    getLandmarksOnItinerary(itineraryId) {
        return axios.get(`/itinerary/${itineraryId}/landmarks`)
    }
}