/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */
package net.sourceforge.plantuml.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.code.Transcoder;
import net.sourceforge.plantuml.code.TranscoderUtil;

/**
 * Common service servlet to produce diagram from compressed UML source
 * contained in the end part of the requested URI.
 */
@SuppressWarnings("serial")
public abstract class UmlDiagramService extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // build the UML source from the compressed request parameter
        String text = URLDecoder.decode( getSource(request.getRequestURI()), "UTF-8");
        Transcoder transcoder = getTranscoder();
        text = transcoder.decode(text);
        
        // encapsulate the UML syntax if necessary 
        String uml;
        if (text.startsWith("@start")) {
            uml = text;
        } else {
            StringBuilder plantUmlSource = new StringBuilder();
            plantUmlSource.append( "@startuml\n");
            plantUmlSource.append( text);
            if (text.endsWith( "\n") == false) {
                plantUmlSource.append( "\n");
            }
            plantUmlSource.append( "@enduml");
            uml = plantUmlSource.toString();
        }

        // generate the response
        DiagramResponse dr = new DiagramResponse( response, getOutputFormat());
        dr.sendDiagram(uml);
        dr = null;
    }
    
    /**
     * Extracts the compressed UML source from the HTTP URI.
     * @param uri the complete URI as returned by request.getRequestURI()
     * @return the compressed UML source
     */
    abstract public String getSource( String uri);
    
    /**
     * Gives the wished output format of the diagram.
     * This value is used by the DiagramResponse class. 
     * @return the format
     */
    abstract public FileFormat getOutputFormat();
    
    private Transcoder getTranscoder() {
        return TranscoderUtil.getDefaultTranscoder();
    }
}
