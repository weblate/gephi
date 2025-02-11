/*
Copyright 2008-2011 Gephi
Authors : Mathieu Bastian
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

package org.gephi.timeline.api;

import org.gephi.graph.api.GraphModel;

/**
 * Sparkline type chart visible in the timeline, for instance the number of
 * nodes over time.
 * <p>
 * Charts are usually created from the Statistics module and data are saved
 * within {@link GraphModel}. Columns can be accessed from the graph
 * table in the {@link GraphModel}.
 * </p>
 *
 * @author Mathieu Bastian
 * @see TimelineController#selectColumn(java.lang.String)
 */
public interface TimelineChart {

    /**
     * The attribute column used to create this chart.
     *
     * @return the attribute column
     */
    public String getColumn();

    /**
     * Returns the X values of this chart.
     *
     * @return the X values
     */
    public Number[] getX();

    /**
     * Returns the Y values of this chart.
     *
     * @return the Y values
     */
    public Number[] getY();

    /**
     * Return the Y value for the given <code>x</code> position. It returns the
     * closest value, as <code>x</code> may not exist.
     *
     * @param x the point in time
     * @return the Y value
     */
    public Number getY(Number x);

    /**
     * Returns the min Y value .This is the minimum value in the chart.
     *
     * @return the min Y value
     */
    public Number getMinY();

    /**
     * Returns the max Y value. This is the maximum value in the chart.
     *
     * @return the max Y value
     */
    public Number getMaxY();

    /**
     * Returns the min X value .This is the minimum interval in the chart.
     *
     * @return the min X value
     */
    public Number getMinX();

    /**
     * Returns the max X value. This is the maximum interval in the chart.
     *
     * @return the max X value
     */
    public Number getMaxX();
}
