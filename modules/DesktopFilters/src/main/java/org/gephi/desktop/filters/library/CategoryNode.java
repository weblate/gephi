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

package org.gephi.desktop.filters.library;

import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import javax.swing.Action;
import org.gephi.desktop.filters.query.QueryNode;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.FilterLibrary;
import org.gephi.filters.spi.Category;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.PasteType;

/**
 * @author Mathieu Bastian
 */
public class CategoryNode extends AbstractNode {

    private Category category;

    public CategoryNode(FiltersExplorer.Utils utils, Category category) {
        super(
            utils.isLeaf(category) ? Children.LEAF : Children.create(new CategoryChildFactory(utils, category), true));
        this.category = category;
        if (category != null) {
            setName(category.getName());
        } else {
            setName(NbBundle.getMessage(CategoryNode.class, "RootNode.name"));
        }
    }

    @Override
    public Image getIcon(int type) {
        try {
            if (category.getIcon() != null) {
                return ImageUtilities.icon2Image(category.getIcon());
            }
        } catch (Exception e) {
        }
        if (category == null) {
            return ImageUtilities.loadImage("org/gephi/desktop/filters/library/resources/library.png");
        } else {
            return ImageUtilities.loadImage("org/gephi/desktop/filters/library/resources/folder.png");
        }
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public PasteType getDropType(final Transferable t, int action, int index) {
        if (category == null || !category.equals(FiltersExplorer.QUERIES)) {
            return null;
        }
        final Node dropNode = NodeTransfer.node(t, DnDConstants.ACTION_COPY_OR_MOVE);
        if (dropNode != null && dropNode instanceof QueryNode) {
            return new PasteType() {

                @Override
                public Transferable paste() throws IOException {
                    QueryNode queryNode = (QueryNode) dropNode;
                    FilterController filterController = Lookup.getDefault().lookup(FilterController.class);
                    FilterLibrary library = filterController.getModel().getLibrary();
                    library.saveQuery(queryNode.getQuery());
                    return null;
                }
            };

        }
        return null;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public Action[] getActions(boolean context) {
        return new Action[0];
    }
}
