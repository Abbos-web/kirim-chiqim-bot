package uz.pdp.repository;

import uz.pdp.entity.UserEntity;
import uz.pdp.service.AuthService;

import java.io.*;
import java.util.*;

public class AuthRepository {
    private static AuthRepository authRepo;
    private AuthRepository(){}
    public static AuthRepository getInstance(){
        if (authRepo == null){
            authRepo = new AuthRepository();
        }
        return authRepo;
    }


    public void save( UserEntity userEntity ) {
        List<UserEntity> list = getList();
        list.add(userEntity);
        saveList(list);
    }

    public Optional<UserEntity> getUserById(Long id ) {
        for ( UserEntity user: getList() ) {
            if ( user.getId().equals( id ) ) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    private void saveList(List<UserEntity> list) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/uz/pdp/repository/user_data.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<UserEntity> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/uz/pdp/repository/user_data.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<UserEntity> list = (List<UserEntity>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }



    {
        List<UserEntity> list1 = getList();
        if (list1.isEmpty()) {
            List<UserEntity> list = new ArrayList<>();
            list.add(new UserEntity(1L,"Ali Aliyev", "ali"));
            saveList(list);
        }
    }


}
