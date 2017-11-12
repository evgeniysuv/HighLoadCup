package com.esuvorov.service.extractor;

import com.esuvorov.model.Location;
import com.esuvorov.model.User;
import com.esuvorov.model.Visit;
import com.esuvorov.repository.LocationRepository;
import com.esuvorov.repository.UserRepository;
import com.esuvorov.repository.VisitRepository;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.esuvorov.utils.FileManager.readFilesInFolder;

@Service
public class EntityExtractor {
    private static final Logger LOGGER = Logger.getLogger(EntityExtractor.class);
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final VisitRepository visitRepository;

    @Value("${load}")
    private boolean loadData;

    @Value("${json.config.folder}")
    private String jsonDataConfigFolder;

    @Autowired
    public EntityExtractor(UserRepository userRepository,
                           LocationRepository locationRepository,
                           VisitRepository visitRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.visitRepository = visitRepository;
    }

    @PostConstruct
    public void init() throws IOException, ParseException {
        if (!loadData)
            return;

        File[] filesInFolder = readFilesInFolder(jsonDataConfigFolder);
        for (File jsonFile : filesInFolder) {
            if (checkFileType(jsonFile, "users")) {
                parseUserJson(jsonFile);
            } else if (checkFileType(jsonFile, "location")) {
                parseLocationJson(jsonFile);
            }
        }

        Arrays.stream(filesInFolder)
                .filter(file -> checkFileType(file, "visit"))
                .forEach(this::parseVisitJson);
    }

    private boolean checkFileType(File jsonFile, String entity) {
        return jsonFile.getName().contains(entity);
    }

    @SuppressWarnings("unchecked")
    private void parseUserJson(File userJsonFile) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject o = (JSONObject) jsonParser.parse(new FileReader(userJsonFile));
        List<JSONObject> jsonObjects = (List) o.get("users");
        for (JSONObject jsonObject : jsonObjects) {
            User user = new User();
            Long id = (Long) jsonObject.get("id");
            user.setId(id);
            user.setFirstName((String) jsonObject.get("first_name"));
            user.setLastName((String) jsonObject.get("last_name"));
            user.setBirthDate((Long) jsonObject.get("birth_date"));
            user.setGender((String) jsonObject.get("gender"));
            user.setEmail((String) jsonObject.get("email"));

            userRepository.save(user);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseLocationJson(File locationJsonFile) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject o = (JSONObject) jsonParser.parse(new FileReader(locationJsonFile));
        List<JSONObject> jsonObjects = (List) o.get("locations");
        for (JSONObject jsonObject : jsonObjects) {
            Location location = new Location();
            Long id = (Long) jsonObject.get("id");
            location.setId(id);
            location.setDistance((Long) jsonObject.get("distance"));
            location.setCity((String) jsonObject.get("city"));

            location.setCountry((String) jsonObject.get("country"));
            location.setPlace((String) jsonObject.get("place"));

            locationRepository.save(location);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseVisitJson(File visitJsonFile) {
        JSONParser jsonParser = new JSONParser();
        JSONObject o = null;
        try {
            o = (JSONObject) jsonParser.parse(new FileReader(visitJsonFile));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        List<JSONObject> locations = (List) o.get("visits");
        for (JSONObject jsonObject : locations) {
            Visit visit = new Visit();
            visit.setId((Long) jsonObject.get("id"));
            visit.setMark((Long) jsonObject.get("mark"));
            visit.setVisitedAt((Long) jsonObject.get("visited_at"));

            User user = userRepository.findById((Long) jsonObject.get("user")).orElse(null);
            Location location = locationRepository.findById((Long) jsonObject.get("location")).orElse(null);
            if (user == null || location == null) {
                throw new IllegalStateException();
            }
            visit.setUser(user);
            visit.setLocation(location);
            visitRepository.save(visit);
        }
    }
}
