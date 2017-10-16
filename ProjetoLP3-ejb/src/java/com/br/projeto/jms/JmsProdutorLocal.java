/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projeto.jms;

import javax.ejb.Local;

/**
 *
 * @author 31449530
 */
@Local
public interface JmsProdutorLocal {
    public void sendMessage(MsgType Type ,String message);
    
}
