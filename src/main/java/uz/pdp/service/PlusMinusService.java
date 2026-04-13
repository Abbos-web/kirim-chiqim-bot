package uz.pdp.service;

import uz.pdp.entity.PlusMinusEntity;
import uz.pdp.repository.PlusMinusRepo;

import java.util.List;

public class PlusMinusService {

    private final PlusMinusRepo plusMinusRepo = new PlusMinusRepo();

    public void createPlus(PlusMinusEntity entity) {
        plusMinusRepo.save( entity );
    }

    public String getIncomeList(Long chatId) {
        List<PlusMinusEntity> list = plusMinusRepo.getList();
        StringBuilder builder = new StringBuilder("---------------\n");
        for ( PlusMinusEntity entity: list ) {
            if ( entity.getUserId().equals(chatId) ) {
                builder.append(entity.getAmount()).append("  |  ");
                builder.append(entity.getComment()).append("\n");
            }
        }
        builder.append("---------------");
        return builder.toString();
    }

}
