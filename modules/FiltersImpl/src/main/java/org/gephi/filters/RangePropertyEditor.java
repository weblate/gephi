/*
 Copyright 2008-2010 Gephi
 Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
 Website : http://www.gephi.org

 This file is part of Gephi.

 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

 Copyright 2011 Gephi Consortium. All rights reserved.

 The contents of this file are subject to the terms of either the GNU
 General Public License Version 3 only ("GPL") or the Common
 Development and Distribution License("CDDL") (collectively, the
 "License"). You may not use this file except in compliance with the
 License. You can obtain a copy of the License at
 http://gephi.org/about/legal/license-notice/
 or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
 specific language governing permissions and limitations under the
 License.  When distributing the software, include this License Header
 Notice in each file and include the License files at
 /cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
 License Header, with the fields enclosed by brackets [] replaced by
 your own identifying information:
 "Portions Copyrighted [year] [name of copyright owner]"

 If you wish your version of this file to be governed by only the CDDL
 or only the GPL Version 3, indicate your decision by adding
 "[Contributor] elects to include this software in this distribution
 under the [CDDL or GPL Version 3] license." If you do not indicate a
 single choice of license, a recipient has the option to distribute
 your version of this file under either the CDDL, the GPL Version 3 or
 to extend the choice of license to its licensees as provided above.
 However, if you add GPL Version 3 code and therefore, elected the GPL
 Version 3 license, then the option applies only if the new code is
 made subject to such option by the copyright holder.

 Contributor(s):

 Portions Copyrighted 2011 Gephi Consortium.
 */

package org.gephi.filters;

import java.beans.PropertyEditorSupport;
import org.gephi.filters.api.Range;

/**
 * @author Mathieu Bastian
 */
public class RangePropertyEditor extends PropertyEditorSupport {

    private Range range;

    @Override
    public Object getValue() {
        return range;
    }

    @Override
    public void setValue(Object value) {
        this.range = (Range) value;
    }

    @Override
    public String getAsText() {
        if (range != null) {
            return range.getRangeType().getSimpleName() + " - " + range.toString();
        } else {
            return "null";
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.equals("null")) {
            String[] arr = text.split(" - ");
            if (arr[0].equals("Float")) {
                range = new Range(Float.parseFloat(arr[1]), Float.parseFloat(arr[2]));
            } else if (arr[0].equals("Double")) {
                range = new Range(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
            } else if (arr[0].equals("Integer")) {
                range = new Range(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            } else if (arr[0].equals("Long")) {
                range = new Range(Long.parseLong(arr[1]), Long.parseLong(arr[2]));
            } else if (arr[0].equals("Short")) {
                range = new Range(Short.parseShort(arr[1]), Short.parseShort(arr[2]));
            } else if (arr[0].equals("Byte")) {
                range = new Range(Byte.parseByte(arr[1]), Byte.parseByte(arr[2]));
            }
        }
    }
}
