/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.jms.mdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Patricia
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/projeto"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class JmsConsumidor implements MessageListener {

    public JmsConsumidor() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            File file = new File("C:/Temp/logProj.txt");
            //File file = new File("C:\\Users\\Public\\Videos\\logProj.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            String txt = "";
            txt += carregar(file);

            TextMessage txtMessage = (TextMessage) message;
            txt += "\n" + txtMessage.getText();

            salvar(file, txt);

        } catch (Exception ex) {

        }
    }

    private String carregar(File arquivo) throws Exception {
        FileInputStream dispositivoDeEntrada = new FileInputStream(arquivo);
        byte[] conteudo = new byte[dispositivoDeEntrada.available()];
        dispositivoDeEntrada.read(conteudo);
        return new String(conteudo);

    }

    private void salvar(File arquivo, String conteudo) throws IOException, Exception {
        FileOutputStream streamDeSaida = new FileOutputStream(arquivo);
        streamDeSaida.write(conteudo.getBytes());
        streamDeSaida.close();
    }

}
