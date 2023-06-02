package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Destination;
import com.techelevator.model.Itinerary;
import com.techelevator.model.Landmark;
import com.techelevator.services.GooglePlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AppController {


    @Autowired
    private LandmarkDao landmarkDao;

    @Autowired
    private ItineraryDao itineraryDao;

    @Autowired
    private JdbcItineraryDao jdbcItineraryDao;

    @Autowired
    private TripDao tripDao;

    @Autowired
    private DestinationDao destinationDao;

    @Autowired
    private GooglePlacesService googlePlacesService;

    //Get Requests Landmarks | Itineraries

    @RequestMapping(path = "/landmarks", method = RequestMethod.GET)
    public List<Landmark> getAllLandmarks(){
        List<Landmark> allLandmarksList = landmarkDao.getAllLandmarks();
        return allLandmarksList;
    }

    @RequestMapping(path = "/itinerary/{itineraryId}/landmarks", method = RequestMethod.GET)
    public List<Landmark> getLandmarksByItineraryId(@PathVariable int itineraryId){
        return landmarkDao.getLandmarksOnItinerary(itineraryId);
    }

    @RequestMapping(path = "/landmarks/{landmarkId}", method = RequestMethod.GET)
    public Landmark getLandmarkById(@PathVariable int landmarkId){
        return landmarkDao.getSpecificLandmarkById(landmarkId);
    }


    @RequestMapping(path = "/itineraries", method = RequestMethod.GET)
    public List<Itinerary> getItinerariesByUser(Principal principal){
        List<Itinerary> itinerariesByUser = itineraryDao.getAllItineraries(jdbcItineraryDao.getUserIdByUsername(principal.getName()));
        return itinerariesByUser;
    }


    @RequestMapping(path = "/itinerary/{itineraryId}", method = RequestMethod.GET)
    public Itinerary getItineraryById(@PathVariable int itineraryId, Principal user){
        //Itinerary empty = null;
        Itinerary itineraryById = itineraryDao.getSpecificItinerary(itineraryId);
        //We add this to try to display only the itineraries of the principal
//        List<Itinerary> itinerariesByUser = itineraryDao.getAllItinerariesByUserName(user.getName());
//        if(itinerariesByUser.contains(itineraryById)) {
//            return itineraryById;
//        }
       // for(Itinerary itinerary: itinerariesByUser){
       //     if(!itinerary.equals(itineraryById)){
       //         return empty;
       //     }
        return itineraryById;
    }

//    @RequestMapping(path = "/itineraries", method = RequestMethod.GET)
//    public List<Itinerary> getAllItineraries(int userId){
//        return itineraryDao.getAllItineraries(userId);
//    }

    // CRUD Operations here

    //CREATE

    @RequestMapping(path = "/addItinerary", method = RequestMethod.POST)
    public void createNewItinerary(@Valid @RequestBody Itinerary itinerary, Principal user){
        itineraryDao.createNewItinerary(itinerary, user.getName());
    }

    @RequestMapping(path = "/trips", method = RequestMethod.POST)
    public void addTrip(int userId, int itineraryId){
        tripDao.addTrip(userId,itineraryId);
    }

    @RequestMapping(path = "/destinations", method = RequestMethod.POST)
    public void addDestination(@Valid @RequestBody Destination destination){
        destinationDao.addDestination(destination.getItineraryId(), destination.getLandmarkId());
    }
    //UPDATE
    @RequestMapping(path = "/itinerary/{itineraryId}/edit", method = RequestMethod.PUT)
    public void updatedItinerary(@Valid @RequestBody Itinerary itinerary, @PathVariable int itineraryId){
        itineraryDao.updateItinerary(itinerary);
    }

    //DELETE
    @RequestMapping(path = "/itinerary/{itineraryId}", method = RequestMethod.DELETE)
    public void deleteItinerary(@PathVariable int itineraryId){
        itineraryDao.deleteItinerary(itineraryId);
    }


    @RequestMapping(path = "/itinerary/{itineraryId}/landmarks/{landmarkId}", method = RequestMethod.DELETE)
    public void deleteDestination(@PathVariable int itineraryId,@PathVariable int landmarkId){
        destinationDao.deleteDestination(itineraryId,landmarkId);

    }

    //TEST API FOR LANDMARKS
    @GetMapping(path = "/test")
    public List<Landmark> test(){
        return googlePlacesService.getApiResults();

}
