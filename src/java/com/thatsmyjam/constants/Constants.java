/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.constants;

/**
 *
 * @author cpournaras11
 */
public class Constants 
{
    public static final String TYPE_REP = "TYPE_REPLACE";
    public static final String HREF_VAL = "HREF_VALUE";
    public static final String HREF_REP = "href=\"/ThatsMyJam/info.jsp?" + TYPE_REP + "=" + HREF_VAL + "\"";
    public static final String SRC_REP = "SOURCE_REPLACE";
    public static final String ALT_REP = "ALT_REPLACE";
    public static final String PIXEL_REP = "IMAGE_PIXELS_REPLACE";
    public static final String SIZE_REP = "style=\"width:" + PIXEL_REP + ";height:" + PIXEL_REP + ";\"";
    
    /**
     * Replace HREF_REP with "" to not have the image link to another location
     *      Otherwise replace TYPE_REP with (album/artist) and HREF_VAL to the id of the desired type
     * 
     * Replace SRC_REP with the name of the image to display
     * 
     * Replace ALT_REP with the text to display in case the image isn't found
     * 
     * Replace SIZE_REP with "" to use the default size
     *      Otherwise replaceAll PIXEL_REP with the desired image size (pixels per side)
     */
    public static final String IMAGE = "<div class=\"col-lg-3 col-md-4 col-xs-6 thumb\">"
                         + "<a class=\"thumbnail\" " + HREF_REP + ">"
                         + "<img class=\"img-responsive\" src=\"images/" + SRC_REP + "\" "
                         + "alt=\"" + ALT_REP + "\" " + SIZE_REP + "></a></div>";
}
