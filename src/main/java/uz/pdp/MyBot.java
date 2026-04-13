package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.controller.MainController;

public class MyBot extends TelegramLongPollingBot {


    private final MainController mainController = MainController.getInstance();


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Message message = update.getMessage();

            if (message.hasText()) {
                SendMessage sendMessage = mainController.messageHandler(message);
                send(sendMessage);

            }

        } else if (update.hasCallbackQuery()) {

            CallbackQuery callbackQuery = update.getCallbackQuery();
            SendMessage sendMessage = mainController.callbackHandler(callbackQuery);
            send(sendMessage);

        }

    }


    @Override
    public String getBotUsername() {
        return "@java_g59_bot";
    }

    @Override
    public String getBotToken() {
        return "8542870976:AAGR6e3PsRLkPPB2S_WHW08ULM60ZBTofjM";
    }

    public void send(Object object) {

        try {
            if (object instanceof SendMessage sendMessage) {
                execute(sendMessage);
            } else if (object instanceof SendLocation send) {
                execute(send);
            } else if (object instanceof EditMessageReplyMarkup edit) {
                execute(edit);
            }

        } catch (TelegramApiException e) {
            e.getStackTrace();
        }
    }


}
