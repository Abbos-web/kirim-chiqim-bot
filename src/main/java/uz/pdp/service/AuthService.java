package uz.pdp.service;

import org.telegram.telegrambots.meta.api.objects.User;
import uz.pdp.controller.MainController;
import uz.pdp.entity.UserEntity;
import uz.pdp.repository.AuthRepository;

import java.util.Optional;

public class AuthService {
    private static AuthService authService;
    private AuthService(){}
    public static AuthService getInstance(){
        if (authService == null){
            authService = new AuthService();
        }
        return authService;
    }


    private final AuthRepository authRepository = AuthRepository.getInstance();

    public void registration(User user, Long id) {

        Optional<UserEntity> optional = authRepository.getUserById( id );

        if ( optional.isPresent() ) return;

        UserEntity userEntity = new UserEntity();
        userEntity.setId( id );
        userEntity.setFullName( user.getFirstName() + " " + user.getLastName() );
        userEntity.setUserName( user.getUserName() );
        authRepository.save( userEntity );
    }


}
