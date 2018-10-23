package com.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

@Controller
public class UserController {
	

	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String init(@ModelAttribute("model") ModelMap model) {
		List<User> userList= new ArrayList<User>();
		userList.add(new User(1,"sai",12));
		userList.add(new User(2,"nithish",22));
		userList.add(new User(3,"reddy",32));
		userList.add(new User(4,"harry",42));
		
	    model.addAttribute("userList", userList);
	    return "index";
	}
	
	@RequestMapping(value = "/createPDF", method = RequestMethod.GET)
	public String createPDF() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, DocumentException, TemplateException {
		
		Configuration configuration = prepareConfiguration();
		 configuration.setClassForTemplateLoading(FreemarkPdfDemoApplication.class, "/templates");
		 Template template = configuration.getTemplate("index-pdf.ftl");

		 // get the String from the StringWriter
		 String processedHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, prepareData());

		 
		 FileOutputStream os = null;
		
		  String fileName = UUID.randomUUID().toString();
		  
	        try {
	            final File outputFile = File.createTempFile(fileName, ".pdf");
	            System.out.println("PDF Location"+outputFile.getPath());
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
	            System.out.println("PDF created successfully");
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
		
		return "done";
	}
	
	private Configuration prepareConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        return configuration;
    }

    private Map<String, Object> prepareData() {
    	List<User> userList= new ArrayList<User>();
		userList.add(new User(1,"sai",12));
		userList.add(new User(2,"nithish",22));
		userList.add(new User(3,"reddy",32));
		userList.add(new User(4,"harry",42));
		userList.add(new User(5,"sai",12));
		userList.add(new User(6,"nithish",22));
		userList.add(new User(7,"reddy",32));
		userList.add(new User(8,"harry",42));
		userList.add(new User(9,"sai",12));
		userList.add(new User(10,"nithish",22));
		userList.add(new User(11,"reddy",32));
		userList.add(new User(12,"harry",42));
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userList",userList);
        return data;
    }
}
