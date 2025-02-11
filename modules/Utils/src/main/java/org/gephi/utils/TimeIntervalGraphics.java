/*
 Copyright 2008-2010 Gephi
 Authors : Eduardo Ramos
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

package org.gephi.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Class to draw time intervals as graphics, being able to indicate the colors
 * to use (or default colors).
 * <p>
 * The result graphics are like:
 * |{background color}|time-interval{fill color}|{background color}|
 *
 * @author Eduardo Ramos
 */
public class TimeIntervalGraphics {

    public static final Color DEFAULT_FILL = new Color(153, 255, 255);
    public static final Color DEFAULT_BORDER = new Color(2, 104, 255);
    private double min;
    private double max;
    private double range;

    /**
     * Create a new TimeIntervalGraphics with the given minimum and maximum
     * times to render intervals later.
     *
     * @param min Minimum time of all intervals
     * @param max Maximum time of all intervals
     */
    public TimeIntervalGraphics(double min, double max) {
        min = normalize(min);
        max = normalize(max);
        if (min > max) {
            throw new IllegalArgumentException("min should be less or equal than max");
        }
        this.min = min;
        this.max = max;
        calculateRange();
    }

    private void calculateRange() {
        range = max - min;
    }

    /**
     * Creates a time interval graphic representation with default colors. If
     * starts or ends are infinite, they will be normalized to the min or max
     * values range.
     *
     * @param start  Start of the interval (must be greater or equal than minimum
     *               time)
     * @param end    End of the interval (must be lesser or equal than maximum
     *               time)
     * @param width  Image width
     * @param height Image height
     * @return Generated image for the interval
     */
    public BufferedImage createTimeIntervalImage(double start, double end, int width, int height) {
        return createTimeIntervalImage(start, end, width, height, null, null, null);
    }

    /**
     * Creates a time interval graphic representation with the indicated fill
     * and border colors (or null to use default colors). If starts or ends are
     * infinite, they will be normalized to the min or max values range.
     *
     * @param start  Start of the interval (must be greater or equal than minimum
     *               time)
     * @param end    End of the interval (must be lesser or equal than maximum
     *               time)
     * @param width  Image width
     * @param height Image height
     * @param fill   Fill color for the interval
     * @param border Border color for the interval
     * @return Generated image for the interval
     */
    public BufferedImage createTimeIntervalImage(double start, double end, int width, int height, Color fill,
                                                 Color border) {
        return createTimeIntervalImage(start, end, width, height, fill, border, null);
    }

    /**
     * Creates a time interval graphic representation with the indicated fill
     * and border colors (or null to use default colors). If starts or ends are
     * infinite, they will be normalized to the min or max values range.
     *
     * @param start      Start of the interval (must be greater or equal than minimum
     *                   time)
     * @param end        End of the interval (must be lesser or equal than maximum
     *                   time)
     * @param width      Image width
     * @param height     Image height
     * @param fill       Fill color for the interval
     * @param border     Border color for the interval
     * @param background Background color
     * @return Generated image for the interval
     */
    public BufferedImage createTimeIntervalImage(double start, double end, int width, int height, Color fill,
                                                 Color border, Color background) {
        if (start > end) {
            throw new IllegalArgumentException("start should be less or equal than end");
        }
        return createTimeIntervalImage(new double[] {start}, new double[] {end}, width, height, fill, border,
            background);
    }

    /**
     * Creates a time interval graphic representation with the indicated fill,
     * border and background colors (or null to use default colors). If starts
     * or ends are infinite, they will be normalized to the min or max values
     * range.
     *
     * @param starts     Starts of the intervals (must be greater or equal than
     *                   minimum time)
     * @param ends       Ends of the intervals (must be lesser or equal than maximum
     *                   time)
     * @param width      Image width
     * @param height     Image height
     * @param fill       Fill color for the interval
     * @param border     Border color for the interval
     * @param background Background color
     * @return Generated image for the interval
     */
    public BufferedImage createTimeIntervalImage(double starts[], double ends[], int width, int height, Color fill,
                                                 Color border, Color background) {
        if (starts.length != ends.length) {
            throw new IllegalArgumentException("start and ends length should be equal");
        }
        if (fill == null) {
            fill = DEFAULT_FILL;
        }
        if (border == null) {
            border = DEFAULT_BORDER;
        }

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw brackground if any:
        if (background != null) {
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
        }

        g.translate(1, 0);//Start drawing at pixel 1

        width -= 2;//Reduce fill area in 2 pixels for the borders

        //Draw time interval filled parts:
        if (range == 0) {//No range, Min=Max
            //Fill all drawing area:
            g.setColor(fill);
            g.fillRect(0, 0, width, height);
            g.setColor(border);
            //Draw borders:
            g.drawLine(-1, 0, -1, height);
            g.drawLine(width, 0, width, height);
        } else {
            double xTickWidth = (double) width / range;

            int startPixel, endPixel;
            for (int i = 0; i < starts.length; i++) {
                g.setColor(fill);
                startPixel = (int) (xTickWidth * (normalizeToRange(starts[i]) - min));
                endPixel = (int) (xTickWidth * (normalizeToRange(ends[i]) - min));

                int rectWidth = endPixel - startPixel;
                if (rectWidth == 0) {
                    rectWidth = 1;//Draw at least 1 pixel if a range is small
                }
                g.fillRect(startPixel, 0, rectWidth, height);

                //Draw borders:
                g.setColor(border);
                g.drawLine(startPixel, 0, startPixel, height);
                g.drawLine(endPixel, 0, endPixel, height);
            }
        }

        return image;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        max = normalize(max);
        if (max < min) {
            throw new IllegalArgumentException("min should be less or equal than max");
        }
        this.max = max;
        calculateRange();
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        min = normalize(min);
        if (max < min) {
            throw new IllegalArgumentException("min should be less or equal than max");
        }
        this.min = min;
        calculateRange();
    }

    private double normalize(double d) {
        if (d == Double.NEGATIVE_INFINITY) {
            return -Double.MAX_VALUE;
        }
        if (d == Double.POSITIVE_INFINITY) {
            return Double.MAX_VALUE;
        }
        return d;
    }

    private double normalizeToRange(double d) {
        if (d == Double.NEGATIVE_INFINITY || d < min) {
            return min;
        }
        if (d == Double.POSITIVE_INFINITY || d > max) {
            return max;
        }
        return d;
    }

    public void setMinMax(double min, double max) {
        min = normalize(min);
        max = normalize(max);
        if (max < min) {
            throw new IllegalArgumentException("min should be less or equal than max");
        }
        this.min = min;
        this.max = max;

        calculateRange();
    }
}
