package br.una.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.una.prova.entity.Email;

@RestController
public class EmailController {


    @Autowired private JavaMailSender mailSender;


    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public void sendMail(Email Email) {
        SimpleMailMessage message = new SimpleMailMessage();
       
        message.setSubject("Contato Suporte Mercado Fácil");
        message.setText("teste de envio");
        message.setTo("suportemercadofacil@gmail.com");
        message.setFrom("suportemercadofacil@gmail.com");
        
        
        try {    	
        	mailSender.send(message);  
   //     	 return "enviado o email.";
        } catch (Exception e) {
            e.printStackTrace();
     //       return "Erro ao enviar email.";
        }
       
    }
    
}
	
	
	

