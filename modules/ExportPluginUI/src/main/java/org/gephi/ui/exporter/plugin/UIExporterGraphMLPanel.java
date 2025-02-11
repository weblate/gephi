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

package org.gephi.ui.exporter.plugin;

import org.gephi.io.exporter.plugin.ExporterGraphML;

/**
 * @author Mathieu Bastian
 */
public class UIExporterGraphMLPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox attributesExportCheckbox;
    private javax.swing.JCheckBox colorsExportCheckbox;
    private javax.swing.JLabel labelExport;
    private javax.swing.JLabel labelNormalize;
    private javax.swing.JCheckBox normalizeCheckbox;
    private javax.swing.JCheckBox positionExportCheckbox;
    private javax.swing.JCheckBox sizeExportCheckbox;
    /**
     * Creates new form UIExporterGEXFPanel
     */
    public UIExporterGraphMLPanel() {
        initComponents();
    }

    public void setup(ExporterGraphML exporterGraphML) {
        colorsExportCheckbox.setSelected(exporterGraphML.isExportColors());
        positionExportCheckbox.setSelected(exporterGraphML.isExportPosition());
        sizeExportCheckbox.setSelected(exporterGraphML.isExportSize());
        attributesExportCheckbox.setSelected(exporterGraphML.isExportAttributes());
        normalizeCheckbox.setSelected(exporterGraphML.isNormalize());
    }

    public void unsetup(ExporterGraphML exporterGraphML) {
        exporterGraphML.setExportAttributes(attributesExportCheckbox.isSelected());
        exporterGraphML.setExportColors(colorsExportCheckbox.isSelected());
        exporterGraphML.setExportSize(sizeExportCheckbox.isSelected());
        exporterGraphML.setExportPosition(positionExportCheckbox.isSelected());
        exporterGraphML.setNormalize(normalizeCheckbox.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelExport = new javax.swing.JLabel();
        positionExportCheckbox = new javax.swing.JCheckBox();
        colorsExportCheckbox = new javax.swing.JCheckBox();
        attributesExportCheckbox = new javax.swing.JCheckBox();
        sizeExportCheckbox = new javax.swing.JCheckBox();
        labelNormalize = new javax.swing.JLabel();
        normalizeCheckbox = new javax.swing.JCheckBox();

        labelExport.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.labelExport.text")); // NOI18N

        positionExportCheckbox.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.positionExportCheckbox.text")); // NOI18N

        colorsExportCheckbox.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.colorsExportCheckbox.text")); // NOI18N

        attributesExportCheckbox.setText(org.openide.util.NbBundle.getMessage(UIExporterGraphMLPanel.class,
            "UIExporterGraphMLPanel.attributesExportCheckbox.text")); // NOI18N

        sizeExportCheckbox.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.sizeExportCheckbox.text")); // NOI18N

        labelNormalize.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelNormalize.setForeground(new java.awt.Color(102, 102, 102));
        labelNormalize.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.labelNormalize.text")); // NOI18N

        normalizeCheckbox.setText(org.openide.util.NbBundle
            .getMessage(UIExporterGraphMLPanel.class, "UIExporterGraphMLPanel.normalizeCheckbox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelExport)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(attributesExportCheckbox)
                                .addComponent(sizeExportCheckbox)
                                .addComponent(colorsExportCheckbox)
                                .addComponent(positionExportCheckbox)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(normalizeCheckbox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(labelNormalize)))
                    .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelExport)
                        .addComponent(positionExportCheckbox))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(colorsExportCheckbox)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(sizeExportCheckbox)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(attributesExportCheckbox)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(normalizeCheckbox)
                        .addComponent(labelNormalize))
                    .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // End of variables declaration//GEN-END:variables
}
