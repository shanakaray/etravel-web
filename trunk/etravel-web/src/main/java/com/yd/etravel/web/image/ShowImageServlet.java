/**
 * 
 */
package com.yd.etravel.web.image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yd.etravel.domain.cmt.content.Image;
import com.yd.etravel.util.ServiceHelper;

/**
 * @author yohan
 * 
 */
public class ShowImageServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req,
	    final HttpServletResponse response) throws ServletException,
	    IOException {
	OutputStream out = null;
	FileInputStream in = null;
	try {

	    final Image image = ServiceHelper.getInstance().getContentService()
		    .getImage(Long.valueOf(req.getParameter("id")));
	    response.setContentType(image.getType());
	    out = response.getOutputStream();
	    in = new FileInputStream(image.getFile());
	    final int size = in.available();
	    final byte[] content = new byte[size];
	    in.read(content);
	    out.write(content);

	} catch (final Exception e) {
	    e.printStackTrace();
	} finally {
	    in.close();
	    out.close();
	}
    }

}
