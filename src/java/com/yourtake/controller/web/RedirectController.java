package com.yourtake.controller.web;



import com.yourtake.init.Init;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;



@Controller
public class RedirectController {

 
    
    @PostConstruct
    public void init()  {
        
        Init.main(null);
    }
    
}
