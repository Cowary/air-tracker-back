//import org.cowary.arttrackerback.entity.security.AuthenticationReq;
//import org.cowary.arttrackerback.entity.security.AuthenticationResp;
//import org.cowary.arttrackerback.security.AuthController;
//import org.cowary.arttrackerback.security.JwtUtils;
//import org.cowary.arttrackerback.security.UserDetailsImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AuthControllerTest {
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private JwtUtils jwtUtils;
//
//    @InjectMocks
//    private AuthController authController;
//
//    @Test
//    void testAuthenticateUser() {
//        // arrange
//        AuthenticationReq loginRequest = new AuthenticationReq();
//        loginRequest.setUsername("user1");
//        loginRequest.setPassword("password");
//
//        Authentication authentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(any())).thenReturn(authentication);
//
//        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwtToken");
//
//        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
//        when(authentication.getPrincipal()).thenReturn(userDetails);
//
//        // act
//        ResponseEntity<AuthenticationResp> responseEntity = authController.authenticateUser(loginRequest);
//
//        // assert
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("jwtToken", responseEntity.getBody().getToken());
//        verify(authenticationManager, times(1)).authenticate(any());
//        verify(jwtUtils, times(1)).generateJwtToken(authentication);
//        //verify(userDetails, times(1)).getUsername();
//    }
//}
