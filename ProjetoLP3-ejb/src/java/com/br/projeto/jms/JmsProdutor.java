/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.jms;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;

/**
 *
 * @author 31449530
 */
@Stateless
public class JmsProdutor implements JmsProdutorLocal {

    @Resource(mappedName = "jms/projeto")
    private Queue projeto;
    @Inject
    @JMSConnectionFactory("jms/projetoFactory")
    private JMSContext context;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void sendJMSMessageToProjeto(String messageData) {
        context.createProducer().send(projeto, messageData);
    }

    @Override
    public void sendMessage(MsgType type ,String msg){
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = formatter.format(new Date());

        String msg2 = data;
        
         switch (type) {
            case LOGIN:
                msg2 += " Login: " + msg;
                break;

            case LOGOUT:
                msg2 += " Logout " + msg;
                break;
        }
        
        sendJMSMessageToProjeto(msg);
    }
}
