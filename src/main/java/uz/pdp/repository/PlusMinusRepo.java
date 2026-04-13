package uz.pdp.repository;

import uz.pdp.entity.PlusMinusEntity;
import uz.pdp.entity.UserEntity;
import uz.pdp.enums.StepEnum;
import uz.pdp.enums.TypeEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlusMinusRepo {

    public void save( PlusMinusEntity entity ) {
        List<PlusMinusEntity> list = getList();
        list.add(entity);
        saveList(list);
    }


    private void saveList(List<PlusMinusEntity> list) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/uz/pdp/repository/plus_minus_data.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public List<PlusMinusEntity> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/uz/pdp/repository/plus_minus_data.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<PlusMinusEntity> list = (List<PlusMinusEntity>) objectInputStream.readObject();
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
        List<PlusMinusEntity> list1 = getList();
        if (list1.isEmpty()) {
            List<PlusMinusEntity> list = new ArrayList<>();
            list.add(new PlusMinusEntity("123", 1L,12.5, TypeEnum.PLUS, "test", StepEnum.AMOUNT));
            saveList(list);
        }
    }


}
