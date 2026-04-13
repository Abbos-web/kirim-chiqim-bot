package uz.pdp.controller;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import uz.pdp.MyBot;
import uz.pdp.entity.PlusMinusEntity;
import uz.pdp.enums.StepEnum;
import uz.pdp.enums.TypeEnum;
import uz.pdp.service.AuthService;
import uz.pdp.service.PlusMinusService;
import uz.pdp.utils.InlineButtonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainController {
    private static MainController mainController;
    private MainController(){}
    public static MainController getInstance(){
        if (mainController == null){
            mainController = new MainController();
        }
        return mainController;
    }


    private final AuthService authService = AuthService.getInstance();
    private final PlusMinusService plusMinusService = new PlusMinusService();

    private Map<Long, PlusMinusEntity> map = new HashMap<>();



    public SendMessage messageHandler(Message message) {

        Long chatId = message.getChatId();
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if ( text.equals("/start") ) {
            sendMessage.setText("Welcome");
            sendMessage.setReplyMarkup(InlineButtonUtil.menuButtons());
            authService.registration(message.getFrom(), chatId);
        }
        else if ( map.get( chatId ) != null ) {
           return createPlusMinus( message );
        }


        return sendMessage;
    }


    public SendMessage callbackHandler(CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();

        Long chatId = callbackQuery.getMessage().getChatId();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId( chatId );

        if ( data.startsWith("plus") ) {
            sendMessage.setText("Enter amount");

            PlusMinusEntity plusMinus = new PlusMinusEntity();
            plusMinus.setStep( StepEnum.AMOUNT );
            plusMinus.setId(UUID.randomUUID().toString());
            plusMinus.setUserId( chatId );
            plusMinus.setType( TypeEnum.PLUS );

            map.put( chatId , plusMinus );

        } else if (data.startsWith("minus")) {

        } else if (data.startsWith("income")) {
            String incomeList = plusMinusService.getIncomeList( callbackQuery.getMessage().getChatId() );
            sendMessage.setText( incomeList );
        } else if (data.startsWith("result")) {

        }

        return sendMessage;
    }


    public SendMessage createPlusMinus( Message message ) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId( message.getChatId() );

        PlusMinusEntity entity = map.get( message.getChatId() );

        if ( entity.getType().equals(TypeEnum.PLUS) && entity.getStep().equals(StepEnum.AMOUNT) ) {
            entity.setAmount( Double.parseDouble( message.getText() ) );
            entity.setStep( StepEnum.COMMENT );
            map.put( message.getChatId(), entity );
            sendMessage.setText("Enter comment");
        }
        else if ( entity.getType().equals(TypeEnum.PLUS) && entity.getStep().equals(StepEnum.COMMENT) ) {
            entity.setComment( message.getText() );
            plusMinusService.createPlus( entity );
            map.remove(message.getChatId());
            sendMessage.setText("Plus created");
            sendMessage.setReplyMarkup( InlineButtonUtil.menuButtons() );
        }

        return sendMessage;
    }



}
