package akademiakodu.nasaapp;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAppTest {

    private PasswordEncoder passwordEncoder;

    @Test
    public void getHash(){
        passwordEncoder = new BCryptPasswordEncoder();
        String passadmin = passwordEncoder.encode("admin");
        String passuser = passwordEncoder.encode("user");
        System.out.println(passadmin);
        System.out.println(passuser);
    }
}
