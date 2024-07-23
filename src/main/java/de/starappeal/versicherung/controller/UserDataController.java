package de.starappeal.versicherung.controller;

import de.starappeal.versicherung.entities.UserData;
import de.starappeal.versicherung.services.UserDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userdata")
public class UserDataController {

  private final UserDataService userDataService;

  @Autowired
  public UserDataController(UserDataService userDataService) {
    this.userDataService = userDataService;
  }

  @ResponseBody
  @GetMapping("/")
  public ResponseEntity<List<UserData>> getAllUserData() {
    List<UserData> result = userDataService.findAll();

    return ResponseEntity.ok(result);
  }

  @ResponseBody
  @GetMapping("/{id}")
  public ResponseEntity<UserData> getUserData(@PathVariable Long id) {
    UserData result = userDataService.findById(id);
    if (result == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);
  }
}
