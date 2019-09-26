//package edu.mum.cs.inventorymanager.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//@Service("emailService")
//	public class EmailService {
//	private static final String IM_THE_GUY = "templates/images/imtheguy.jpg";
//    
//    private static final String JPG_MIME = "image/jpg";
//    private static final String DOCX_MIME = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
//
// @Autowired
//   private JavaMailSender mailSender;
//
// @Autowired
//   private SpringTemplateEngine templateEngine;
//   
//   /* 
//    * Send HTML mail  
//    */
////   public void sendOrderReceivedMail(
////           final String recipientName, final String recipientEmail,Order order,String documentName,final Locale locale) 
////           throws MessagingException {
////       
////       // Prepare the Thymeleaf evaluation context
////       final Context thymeContext = new Context(locale);
////       thymeContext.setVariable("name", recipientName);
////       //thymeContext.setVariable("order", order);
////         
////       // Prepare message using a Spring helper
////       final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
////       final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
////       message.setSubject("Order Details");
////
////       // could have CC, BCC, will also take an array of Strings
////       message.setTo(recipientEmail);
////
////       // Create the HTML body using Thymeleaf..template is orderReceivedMail.html
////       final String htmlContent = this.templateEngine.process("orderReceivedMail", thymeContext);
////       message.setText(htmlContent, true /* isHtml */);
////  
////       // Add imtheguy.jpg
////       message.addInline("imtheguy", new ClassPathResource(IM_THE_GUY), JPG_MIME);
////       
////       
////       // Add attachment
////       String documentLocation = "templates/images/" + documentName ;
////        message.addAttachment(documentName, new ClassPathResource(documentLocation));
/////* 
////  // Alternative for attaching 
////       File file = new File(documentLocation);
////     message.addAttachment(documentName, file);
////*/ 
////        
////        
////       // Send email
////       this.mailSender.send(mimeMessage);
////
////   }
// 
//
//	Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	public void sendMail(String from, String to, String subject, String body) {
//		
//		SimpleMailMessage mail = new SimpleMailMessage();
//
//		mail.setFrom(from);
//		mail.setTo(to);
//		mail.setSubject(subject);
//		mail.setText(body);
//		
//		logger.info("Sending...");
//		
//		mailSender.send(mail);
//		
//		logger.info("Done!");
//	}
//
//	
//}
// 
//	
//
//
//
