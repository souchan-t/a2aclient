package souchan.a2a.service;

import souchan.a2a.models.AgentCard;
import souchan.a2a.models.Message;
import souchan.a2a.models.MessageSendResponseResult;

import java.util.List;
import java.util.Optional;

public interface Agent {
    AgentCard getAgentCard();
    //サーバとのセッションID.
    Optional<String> getId();
    MessageSendResponseResult sendMessage(Message message);
    
}
