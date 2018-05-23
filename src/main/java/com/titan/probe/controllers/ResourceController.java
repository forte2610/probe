/*
 *  ResourceController
 *
 *  Author: 1412093
 *
 *  This controller handles the return of static resources, such as images, to the browser
 */

package com.titan.probe.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ResourceController {
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/logos/{filename}", method = RequestMethod.GET)
    public void getLogo(HttpServletResponse response, @PathVariable(value="filename") String logo) throws IOException {
        File file = new File(getClass().getResource("/static/logos/" + logo).getFile());
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream image = new FileInputStream(file);
        IOUtils.copy(image, response.getOutputStream());
    }
}
