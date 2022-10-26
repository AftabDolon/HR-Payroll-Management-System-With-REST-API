package com.example.hrms.controller;

import com.example.hrms.entity.Users;
import com.example.hrms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController implements BaseController<Users, Long> {
    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Users> save(@RequestBody Users users) {
        userService.save(users);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody Users users) throws Exception {
        try {
            userService.update(users);
            return ResponseEntity.ok("Successfully user information has been update");
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        userService.deleteByIds(ids);
        return ResponseEntity.ok("ID: " + Arrays.toString(ids) + " has been deleted successfully");
    }

    @Override
    public ResponseEntity<List<Users>> getDataByIds(@PathVariable("ids") Long... ids) {
        List<Users> usersList = userService.getDataByIds(ids);
        return ResponseEntity.ok(usersList);
    }

//    public ResponseEntity<Map> getDataByObjectSend(@PathVariable("ids") Long... ids) {
//        List<Users> usersList = userService.getDataByIds(ids);
//        Map<String, Object> param=new HashMap<>();
//        param.put("Status", usersList);
//        param.put("Status", 400);
//        param.put("Status", "");
//        return ResponseEntity.ok(param);
//    }

    @Override
    public List<Users> getData() {
        return userService.getData();
    }

    @PutMapping("/status-update/{id}/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status) {
        userService.updateStatusUser(status, id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity-update/{id}/{activity}")
    public ResponseEntity<?> updateActivity(@PathVariable("id") Long id, @PathVariable("activity") String activity) {

        userService.updateActivityUser(activity, id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/password-update/{id}/{password}")
    public ResponseEntity<?> passwordChange(@PathVariable("id") Long id, @PathVariable("password") String password) {
        userService.userPasswordChange(password, id);
        return ResponseEntity.ok(null);
    }
}
