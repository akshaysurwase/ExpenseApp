package com.Expense.ExpenseApp.Controller;

import com.Expense.ExpenseApp.Entity.AuthModel;
import com.Expense.ExpenseApp.Entity.JWTResponse;
import com.Expense.ExpenseApp.Entity.User;
import com.Expense.ExpenseApp.Entity.UserModel;
import com.Expense.ExpenseApp.Security.CustomUserDetailService;
import com.Expense.ExpenseApp.Service.UserService;
import com.Expense.ExpenseApp.Util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailService cuserDetailService;


    @Autowired
    UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> Login(@RequestBody AuthModel authModel) throws Exception{

        authenticate(authModel.getEmail(),authModel.getPassword());
        //Generate JWT token
        final UserDetails userDetails= cuserDetailService.loadUserByUsername(authModel.getEmail());
        final String token= jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<JWTResponse> (new JWTResponse(token),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException e){
            throw new Exception("User Disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }

}
