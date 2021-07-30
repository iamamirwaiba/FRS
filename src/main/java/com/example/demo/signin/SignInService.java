package com.example.demo.signin;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserService;
import com.example.demo.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class SignInService  {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String USER_NOT_FOUND_MSG =
            "user with PhoneNumber %s is not found";


    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserService appUserService;

    public Map<String,String> signIn(String phoneNumber,String password)throws Exception{
        AppUser appUser=loadUserByUsername(phoneNumber);

        Map<String,String> frs= new HashMap<>();
        frs.put("phoneNumber",appUser.getPhoneNumber());
        frs.put("id",appUser.getId()+"");
        frs.put("first_name",appUser.getFirstName());
        frs.put("last_name",appUser.getLastName());
        frs.put("user_image",appUser.getUserImage());




        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password));
        }
        catch (Exception ex){
            throw new Exception("Username or password Invalid");
        }

        String token=jwtUtil.generateToken(phoneNumber);
        frs.put("token",token);
        return frs;

        //if(phoneNumber.equals(appUser.getUsername())&&bCryptPasswordEncoder.matches(password,appUser.getPassword())){

          //  return frs;
        //}




        //Map<String,String> value=new HashMap<>();
        //value.put("message","Incorrect Password");

        //return value;
    }


    public AppUser loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

            return appUserRepository.findByPhoneNumber(phoneNumber).get();



                /*.orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, phoneNumber)));*/
    }
}
