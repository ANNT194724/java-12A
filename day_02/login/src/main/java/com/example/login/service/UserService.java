package com.example.login.service;

import com.example.login.dto.UserDto;
import com.example.login.model.User;
import com.example.login.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();

    public UserService() {
                                                                            //password before hash: "userpassword"
        User user = new User(1, "User", "user@example.com", "d440aed189a13ff970dac7e7e8f987b2", "user-avatar.jpg");
                                                                            //password before hash: "testpassword"
        User test = new User(2, "Test", "test@example.com", "e16b2ab8d12314bf4efbd6203906ea6c", "test-avatar.jpg");
                                                                               //password before hash: "adminpassword"
        User admin = new User(3, "Admin", "admin@example.com", "e3274be5c857fb42ab72d786e281b4b8", "admin-avatar.jpg");
        users.add(user);
        users.add(test);
        users.add(admin);
    }

    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        Optional<User> user = users.stream().filter(u -> loginRequest.getUsername().equals(u.getUsername())).findAny();
        if(user.isPresent()) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(loginRequest.getPassword().getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            if(user.get().getPassword().equals(hash)) {
                UserDto userDto = new UserDto(user.get().getUsername(), user.get().getEmail(), user.get().getAvatar());
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("username hoặc password chưa chính xác", HttpStatus.BAD_REQUEST);
    }
}
