/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.fop.render.rtf;

import org.apache.fop.datatypes.ColorType;
import org.apache.fop.datatypes.Length;
import org.apache.fop.fo.properties.ColorTypeProperty;
import org.apache.fop.render.rtf.rtflib.rtfdoc.RtfAttributes;
import org.apache.fop.render.rtf.rtflib.rtfdoc.RtfColorTable;


/**
 * A RtfAttributes subclass that adds some helper set methods. 
 */
public class FOPRtfAttributes extends RtfAttributes {

    /**
     * Set an attribute that has a Length value (internal units in twips)
     * @param name name of attribute
     * @param value value of attribute
     * @return this (which now contains the new entry)
     */
    public RtfAttributes setTwips(String name, Length value) {
        set(name, value.getValue() / (1000 / 20)); //Convert millipoints to twips
        return this;
    }

    /**
     * Set an attribute using a value in millipoints (internal units in twips)
     * @param name name of attribute
     * @param value value of attribute (in millipoints)
     * @return this (which now contains the new entry)
     */
    public RtfAttributes setTwips(String name, int value) {
        set(name, value / (1000 / 20)); //Convert millipoints to twips
        return this;
    }

    /**
     * Set an attribute that has a Length value (internal units in half-points)
     * @param name name of attribute
     * @param value value of attribute
     * @return this (which now contains the new entry)
     */
    public RtfAttributes setHalfPoints(String name, Length value) {
        set(name, value.getValue() / (1000 / 2)); //Convert millipoints to half-points
        return this;
    }

    /**
     * Set an attribute that has a Color value.
     * @param name name of attribute
     * @param color value of attribute
     * @return this (which now contains the new entry)
     */
    public RtfAttributes set(String name, ColorType color) {
        int redComponent = ColorTypeProperty.convertChannelToInteger (color.getRed());
        int greenComponent = ColorTypeProperty.convertChannelToInteger (color.getGreen());
        int blueComponent = ColorTypeProperty.convertChannelToInteger (color.getBlue());
        set(name, RtfColorTable.getInstance().getColorNumber(
                redComponent, greenComponent, blueComponent).intValue());
        return this;
    }
}
